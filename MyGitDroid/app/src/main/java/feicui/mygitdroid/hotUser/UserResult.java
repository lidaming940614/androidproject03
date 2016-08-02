package feicui.mygitdroid.hotUser;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import feicui.mygitdroid.login.User;

/**
 * Created by 1099057173 on 2016/8/2.
 */
public class UserResult {
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("incomplete_results")
    private boolean incompleteResults;
    @SerializedName("items")
    private List<HotUser> repoList;

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<HotUser> getRepoList() {
        return repoList;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
