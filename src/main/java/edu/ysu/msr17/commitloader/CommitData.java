package edu.ysu.msr17.commitloader;

import com.jcabi.github.RepoCommit;
import javax.json.JsonObject;
import lombok.Data;

@Data
public class CommitData
{
    private final RepoCommit commit;
    
    private final JsonObject json;
}
