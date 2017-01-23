package edu.ysu.msr17.commitloader;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

@Getter( AccessLevel.PRIVATE )
@Setter( AccessLevel.PRIVATE )
public class DBManager
{
    private Configuration jooqConfig;
    
    private BoneCPDataSource pool;
    
    public DBManager( String url, String username, String password )
    {
        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException ex )
        {
            Logger.getLogger( DBManager.class.getName() ).log( Level.SEVERE, "MySQL JDBC driver not found.", ex );
        }
        
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl( url );
        config.setUsername( username );
        config.setPassword( password );
        config.setMinConnectionsPerPartition( 1 );
        config.setMaxConnectionsPerPartition( 10 );
        config.setPartitionCount( 1 );
        
        this.setPool( new BoneCPDataSource( config ) );
        
        this.setJooqConfig( new DefaultConfiguration().set( this.getPool() )
                                                      .set( SQLDialect.MYSQL ) );
        
        // Forcibly set the connection-level character set variables to utf8mb4.
        // SET NAMES is not used due to an issue with the MySQL Java Connector.
        this.getContext().query( "SET character_set_client = utf8mb4;" ).execute();
        this.getContext().query( "SET character_set_connection = utf8mb4;" ).execute();
        this.getContext().query( "SET character_set_results = utf8mb4;" ).execute();
        this.getContext().query( "SET collation_connection = utf8mb4_general_ci;" ).execute();
        Logger.getLogger( DBManager.class.getName() ).log( Level.INFO, "Connection-level encoding variables:\n{0}", this.getContext().fetch( "SHOW VARIABLES WHERE Variable_name LIKE 'character\\_set\\_%' OR Variable_name LIKE 'collation%';" ).toString() );
        
    }
    
    public final DSLContext getContext()
    {
        return DSL.using( this.getJooqConfig() );
    }
    
    public void shutdown()
    {
        this.getPool().close();
    }
}
