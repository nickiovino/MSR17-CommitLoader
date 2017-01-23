package edu.ysu.msr17.commitloader;

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
        }
    }
}
