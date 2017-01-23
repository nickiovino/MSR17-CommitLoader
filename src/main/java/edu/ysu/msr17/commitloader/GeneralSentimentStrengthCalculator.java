package edu.ysu.msr17.commitloader;

import edu.ysu.msr17.commitloader.data.tables.DataCommits;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jooq.Cursor;
import org.jooq.Record2;
import org.jooq.types.ULong;
import uk.ac.wlv.sentistrength.SentiStrength;

@Getter( AccessLevel.PRIVATE )
@Setter( AccessLevel.PRIVATE )
public class GeneralSentimentStrengthCalculator extends SentimentStrengthCalculator
{
    private final SentiStrength sentiStrength;
    
    public GeneralSentimentStrengthCalculator( DBManager db )
    {
        super( db );
        
        this.sentiStrength = new SentiStrength();
        this.getSentiStrength().initialise(  new String[]{ "sentidata", "./SSData", "trinary" } );
    }
    
    @Override
    public void run()
    {
        Logger.getLogger( GeneralSentimentStrengthCalculator.class.getName() ).log( Level.INFO, "Fetching records" );
        
        Cursor<Record2<ULong, String>> cursor = this.getDb().getContext().select( DataCommits.DATA_COMMITS.ID, DataCommits.DATA_COMMITS.MESSAGE )
                                                                         .from( DataCommits.DATA_COMMITS )
                                                                         .where( DataCommits.DATA_COMMITS.SS_GEN_NEUTRAL.isNull() )
                                                                         .fetchLazy();
        
        Logger.getLogger( GeneralSentimentStrengthCalculator.class.getName() ).log( Level.INFO, "Processing records" );
        
        while( cursor.hasNext() )
        {
            Record2<ULong, String> row = cursor.fetchOne();
            
            SentimentStrength ss = this.calculateSentimentStrength( row.get( DataCommits.DATA_COMMITS.MESSAGE ) );
            
            int affected = this.getDb().getContext().update( DataCommits.DATA_COMMITS )
                                                    .set( DataCommits.DATA_COMMITS.SS_GEN_POSITIVE, ss.getPositive() )
                                                    .set( DataCommits.DATA_COMMITS.SS_GEN_NEGATIVE, ss.getNegative() )
                                                    .set( DataCommits.DATA_COMMITS.SS_GEN_NEUTRAL, ss.getNeutral() )
                                                    .where( DataCommits.DATA_COMMITS.ID.eq( row.get( DataCommits.DATA_COMMITS.ID ) ) )
                                                    .execute();
            
            if( affected != 1 )
            {
                // This should be impossible unless the database isn't properly indexed.
                Logger.getLogger( GeneralSentimentStrengthCalculator.class.getName() ).log( Level.WARNING, "Updated affected {0} records (expected 1).  ID: {1}", new Object[]{ affected, row.get( DataCommits.DATA_COMMITS.ID ) } );
            }
        }
            
    }
    
    private SentimentStrength calculateSentimentStrength( String text )
    {
        String[] scores = this.getSentiStrength().computeSentimentScores( text ).split( " " );
        
        return new SentimentStrength( Byte.parseByte( scores[0] ), 
                                      Byte.parseByte( scores[1] ), 
                                      Byte.parseByte( scores[2] ) );
    }
}
