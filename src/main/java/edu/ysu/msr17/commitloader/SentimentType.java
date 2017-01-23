package edu.ysu.msr17.commitloader;

import lombok.Getter;

@Getter
public enum SentimentType
{
    GENERAL( GeneralSentimentStrengthCalculator.class );
    
    public Class<? extends SentimentStrengthCalculator> cls;
    
    SentimentType( Class<? extends SentimentStrengthCalculator> cls )
    {
        this.cls = cls;
    }
}
