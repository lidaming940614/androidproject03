package feicui.mygitdroid.gank.network;

import feicui.mygitdroid.gank.modle.GankResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 1099057173 on 2016/8/5.
 */
interface GankApi {
    String ENDPOINT = "http://gank.io/api/";
    @GET("day/{year}/{month}/{day}")
    Call<GankResult> getDailyData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);
}
