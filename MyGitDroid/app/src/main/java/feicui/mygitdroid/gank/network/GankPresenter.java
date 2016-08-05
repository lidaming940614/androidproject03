package feicui.mygitdroid.gank.network;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.List;

import feicui.mygitdroid.gank.modle.Ganklistview;
import feicui.mygitdroid.gank.modle.GankResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Date;
/**
 * Created by 1099057173 on 2016/8/5.
 */
public class GankPresenter  {
    private Call<GankResult> gankResultCall;
    private   GankView gankView;
    public GankPresenter(GankView gankView) {
        this.gankView = gankView;
    }
     interface GankView {
        void showEmptyView();

        void hideEmptyView();

        void showMessage(String msg);

        void setData(List<Ganklistview> ganklistviews);
    }
    public void getdata(Date data){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        int year = calendar.get(Calendar.YEAR);
        int monty = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        gankResultCall = GankClient.getInstance().getDailyData(year, monty, day);
        gankResultCall.enqueue(callback);
    }
    private Callback<GankResult> callback = new Callback<GankResult>() {
        @Override public void onResponse(Call<GankResult> call, Response<GankResult> response) {
            GankResult gankResult = response.body();
            if (gankResult == null) {
                gankView.showMessage("未知错误!");
                return;
            }
            if (gankResult.isError()
                    || gankResult.getResults() == null
                    || gankResult.getResults().getAndroidItems() == null
                    || gankResult.getResults().getAndroidItems().isEmpty()) {
                gankView.showEmptyView();
                return;
            }
            List<Ganklistview> ganklistviews = gankResult.getResults().getAndroidItems();
            gankView.hideEmptyView();
            gankView.setData(ganklistviews);
        }
        @Override public void onFailure(Call<GankResult> call, Throwable t) {
            gankView.showMessage("Error:" + t.getMessage());
        }
    };
}
