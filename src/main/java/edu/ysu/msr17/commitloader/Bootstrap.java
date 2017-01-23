package edu.ysu.msr17.commitloader;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bootstrap
{
    public static void main( String[] args )
    {
        switch( args[0] )
        {
            case "fetch":
                if( args.length == 6 )
                {
                    new CommitLoader( new DBManager( args[1], args[2], args[3] ), args[4], args[5].split( "," ) ).run();
                }
                else if( args.length == 5 )
                {
                    new CommitLoader( new DBManager( args[1], args[2], args[3] ), args[4] ).run();
                }
                break;
            
            case "calculate":
                if( args.length == 5 )
                {
                    try
                    {
                        SentimentType.valueOf( args[4] ).getCls().getConstructor( DBManager.class ).newInstance( new DBManager( args[1], args[2], args[3] ) ).run();
                    }
                    catch( NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex )
                    {
                        Logger.getLogger( Bootstrap.class.getName() ).log( Level.SEVERE, null, ex );
                    }
                }
                break;
                
            default:
                Logger.getLogger( Bootstrap.class.getName() ).log( Level.SEVERE, "Invalid option or number of arguments." );
        }
    }
}
