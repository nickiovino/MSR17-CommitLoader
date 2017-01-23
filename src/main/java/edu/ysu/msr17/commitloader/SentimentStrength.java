package edu.ysu.msr17.commitloader;

import lombok.Data;

@Data
public class SentimentStrength
{
    private final byte positive;
    
    private final byte negative;
    
    private final byte neutral;
}
