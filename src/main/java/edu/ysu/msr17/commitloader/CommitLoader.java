package edu.ysu.msr17.commitloader;

import com.google.common.net.HttpHeaders;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.RepoCommit;
import com.jcabi.github.RtGithub;
import com.jcabi.github.wire.CarefulWire;
import edu.ysu.msr17.commitloader.data.tables.DataBuildsCondensed;
import edu.ysu.msr17.commitloader.data.tables.DataCommits;
import edu.ysu.msr17.commitloader.data.tables.DataJson;
import edu.ysu.msr17.commitloader.data.tables.DataUsers;
import edu.ysu.msr17.commitloader.data.tables.records.DataCommitsRecord;
import edu.ysu.msr17.commitloader.data.tables.records.DataUsersRecord;
import java.io.IOException;
import java.net.SocketException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jooq.Cursor;
import org.jooq.Record2;
import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;

@Getter( AccessLevel.PRIVATE )
@Setter( AccessLevel.PRIVATE )
public class CommitLoader
{
    public static final long MAX_DELAY = 3000000;
    
    public static final String USER_AGENT = "MSR17 Challenge Research (nickiovino nriovino@student.ysu.edu)";
    
    private final DBManager db;
    
    private final Collection<String> repos;
    
    private final Github github;
    
    private final Map<String, DataUsersRecord> userCache;
   
    public static void main( String[] args )
    {
        new CommitLoader( new DBManager( args[0], args[1], args[2] ), args[3], args[4].split( "," ) ).run();
    }
    
    public CommitLoader( DBManager db, String token, String[] repos )
    {
        this.db = db;
        
        this.getDb().getContext().query( "SET character_set_client = utf8mb4;" ).execute();
        this.getDb().getContext().query( "SET character_set_connection = utf8mb4;" ).execute();
        this.getDb().getContext().query( "SET character_set_results = utf8mb4;" ).execute();
        this.getDb().getContext().query( "SET collation_connection = utf8mb4_general_ci;" ).execute();
        Logger.getLogger( CommitLoader.class.getName() ).log( Level.INFO, "Connection-level encoding variables:\n{0}", this.getDb().getContext().fetch( "SHOW VARIABLES WHERE Variable_name LIKE 'character\\_set\\_%' OR Variable_name LIKE 'collation%';" ).toString() );
        
        this.repos = new HashSet<>();
        this.getRepos().addAll( Arrays.asList( repos ) );
        
        this.github = new RtGithub( new RtGithub().entry()
                                                  .header( HttpHeaders.USER_AGENT, CommitLoader.USER_AGENT )
                                                  .header( HttpHeaders.AUTHORIZATION, "token " + token )
                                                  .header( HttpHeaders.ACCEPT, "application/vnd.github.v3+json, application/vnd.github.cryptographer-preview+json" )
                                                  .through( CarefulWire.class, 50 ) );
        
        this.userCache = new HashMap<>();
        
        Logger.getLogger( CommitLoader.class.getName() ).log( Level.INFO, "Initialized CommitLoader" );
    }
    
    public void run()
    {
        Logger.getLogger( CommitLoader.class.getName() ).log( Level.INFO, "Fetching records" );
        
        Cursor<Record2<String, String>> cursor = this.getDb().getContext().select( DataBuildsCondensed.DATA_BUILDS_CONDENSED.GH_PROJECT_NAME,
                                                                                   DataBuildsCondensed.DATA_BUILDS_CONDENSED.GIT_TRIGGER_COMMIT )
                                                                          .from( DataBuildsCondensed.DATA_BUILDS_CONDENSED )
                                                                          .leftJoin( DataCommits.DATA_COMMITS )
                                                                          .on( DataBuildsCondensed.DATA_BUILDS_CONDENSED.GH_PROJECT_NAME.eq( DataCommits.DATA_COMMITS.REPO ) )
                                                                          .and( DataBuildsCondensed.DATA_BUILDS_CONDENSED.GIT_TRIGGER_COMMIT.eq( DataCommits.DATA_COMMITS.SHA ) )
                                                                          .where( DataBuildsCondensed.DATA_BUILDS_CONDENSED.GH_PROJECT_NAME.in( this.getRepos() ) )
                                                                          .and( DataCommits.DATA_COMMITS.SHA.isNull() )
                                                                          .fetchLazy();
        
        Logger.getLogger( CommitLoader.class.getName() ).log( Level.INFO, "Processing records" );
        
        while( cursor.hasNext() )
        {
            Record2<String, String> row = cursor.fetchOne();
            
            CommitData commit;
            try
            {
                commit = this.getCommit( row.get( DataBuildsCondensed.DATA_BUILDS_CONDENSED.GH_PROJECT_NAME ),
                                         row.get( DataBuildsCondensed.DATA_BUILDS_CONDENSED.GIT_TRIGGER_COMMIT ) );
                
            }
            catch( IOException ex )
            {
                Logger.getLogger( CommitLoader.class.getName() ).log( Level.SEVERE, null, ex );
                break;
            }
            
            JsonObject json = commit.getJson();
            
            Logger.getLogger( CommitLoader.class.getName() ).log( Level.INFO, "Processing commit {0} -> {1}", new Object[]{ commit.getCommit().toString(), json.toString() } );
            
            JsonObject jsonCommit = json.getJsonObject( "commit" );
            JsonObject jsonCommitAuthor = jsonCommit.getJsonObject( "author" );
            JsonObject jsonCommitCommitter = jsonCommit.getJsonObject( "committer" );
            JsonObject jsonCommitVerification = jsonCommit.getJsonObject( "verification" );
            JsonObject jsonAuthor = json.isNull( "author" ) ? null : json.getJsonObject( "author" );
            JsonObject jsonCommitter = json.isNull( "committer" ) ? null : json.getJsonObject( "committer" );
            JsonObject jsonStats = json.getJsonObject( "stats" );
            
            DataCommitsRecord commitRecord = this.getDb().getContext().newRecord( DataCommits.DATA_COMMITS );
            commitRecord.setRepo( row.get( DataBuildsCondensed.DATA_BUILDS_CONDENSED.GH_PROJECT_NAME ) );
            commitRecord.setSha( json.getString( "sha" ) );
            commitRecord.setMessage( jsonCommit.getString( "message" ) );
            commitRecord.setAuthor( this.getUser( jsonCommitAuthor.getString( "email" ), 
                                                  jsonCommitAuthor.getString( "name" ), 
                                                  jsonAuthor != null && jsonAuthor.containsKey( "id" ) ? jsonAuthor.getInt( "id" ) : null ).getId() );
            commitRecord.setAuthorDate( Timestamp.valueOf( LocalDateTime.parse( jsonCommitAuthor.getString( "date" ).replaceFirst( "Z", "" ) ) ) );
            commitRecord.setCommitter( this.getUser( jsonCommitCommitter.getString( "email" ), 
                                                     jsonCommitCommitter.getString( "name" ), 
                                                     jsonCommitter != null && jsonCommitter.containsKey( "id" ) ? jsonCommitter.getInt( "id" ) : null ).getId() );
            commitRecord.setCommitDate( Timestamp.valueOf( LocalDateTime.parse( jsonCommitCommitter.getString( "date" ).replaceFirst( "Z", "" ) ) ) );
            commitRecord.setVerified( jsonCommitVerification.getBoolean( "verified" ) == true ? (byte) 1 : (byte) 0 );
            commitRecord.setVerifiedReason( jsonCommitVerification.getString( "reason" ) );
            commitRecord.setAdded( UInteger.valueOf( jsonStats.getInt( "additions" ) ) );
            commitRecord.setRemoved( UInteger.valueOf( jsonStats.getInt( "deletions" ) ) );
            commitRecord.insert();
            
            try
            {
                // Murder my server's disks.
                this.getDb().getContext().newRecord( DataJson.DATA_JSON )
                                         .setId( commitRecord.getId() )
                                         .setData( json.toString() )
                                         .insert();
            }
            catch( DataAccessException ex )
            {
                Logger.getLogger( CommitLoader.class.getName() ).log( Level.SEVERE, "Couldn't store GitHub API response", ex );
            }
        }
        
        Logger.getLogger( CommitLoader.class.getName() ).log( Level.INFO, "Finished processing records" );
    }
    
    @SneakyThrows( InterruptedException.class )
    public CommitData getCommit( String repo, String sha ) throws IOException
    {
        long delay = 60000;
        
        while( true )
        {
            try
            {
                RepoCommit commit = this.getGithub().repos().get( new Coordinates.Simple( repo ) ).commits().get( sha );
                return new CommitData( commit, commit.json() );
            }
            catch( IOException ex )
            {
                if( !( ex instanceof SocketException ) )
                {
                    // Can't catch SocketException specifically, as it's not declared
                    // to be thrown by the library.  Re-throw if we get something else.
                    throw ex;
                }
                
                Logger.getLogger( CommitLoader.class.getName() ).log( Level.WARNING, "Deferring next attempt to query GitHub API by " + delay/1000 + " seconds.", ex );
                Thread.sleep( delay );
                
                if( delay < CommitLoader.MAX_DELAY )
                {
                    delay *= 2;
                }
            }
        }
    }
    
    public DataUsersRecord getUser( String email, String name, Integer id )
    {
        if( this.getUserCache().containsKey( email ) )
        {
            return this.getUserCache().get( email );
        }
        
        DataUsersRecord user = this.getDb().getContext().selectFrom( DataUsers.DATA_USERS )
                                                        .where( DataUsers.DATA_USERS.EMAIL.eq( email ) )
                                                        .fetchOne();
        
        if( user == null )
        {
            user = this.getDb().getContext().newRecord( DataUsers.DATA_USERS )
                                            .setEmail( email )
                                            .setName( name )
                                            .setGhId( id != null ? UInteger.valueOf( id ) : null );
            user.store();
        }
        
        this.getUserCache().put( email, user );
        return user;
    }
}
