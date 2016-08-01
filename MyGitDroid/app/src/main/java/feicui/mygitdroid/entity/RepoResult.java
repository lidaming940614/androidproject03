package feicui.mygitdroid.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 1099057173 on 2016/8/1.
 */
public class RepoResult {
    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<Repo> repoList;

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<Repo> getRepoList() {
        return repoList;
    }
}
