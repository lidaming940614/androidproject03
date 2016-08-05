package feicui.mygitdroid.gank.modle;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 1099057173 on 2016/8/5.
 */
@SuppressWarnings("unused")
public class GankResult {
    private boolean error;
    private List<String> categoy;
    private  Results results;

    public List<String> getCategoy() {
        return categoy;
    }

    public boolean isError() {
        return error;
    }

    public Results getResults() {
        return results;
    }

    public static class Results {
        @SerializedName("Android")
        private List<Ganklistview> androidItems;

        public List<Ganklistview> getAndroidItems() {
            return androidItems;
        }
    }
}
