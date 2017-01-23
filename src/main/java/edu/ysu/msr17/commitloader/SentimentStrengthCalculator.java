package edu.ysu.msr17.commitloader;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter( AccessLevel.PROTECTED )
@Setter( AccessLevel.PROTECTED )
public abstract class SentimentStrengthCalculator
{
    private final DBManager db;
    
    public SentimentStrengthCalculator( DBManager db )
    {
        this.db = db;
    }
    
    public abstract void run();
}
