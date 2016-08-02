package feicui.mygitdroid.network;

import feicui.mygitdroid.entity.RepoResult;
import feicui.mygitdroid.hotUser.UserResult;
import feicui.mygitdroid.login.AccessTokenResult;
import feicui.mygitdroid.login.User;
import feicui.mygitdroid.repoinfo.RepoContentResult;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 1099057173 on 2016/7/28.
 */
public interface GitHubApi {
    String CALL_BACK = "lidaming";
    String CLIENT_ID = "9625ad4e530bdda2dc88";
    String CLIENT_SECRET = "0b9cc81f748995367577c7681b4c274efde828f2";

    String AUTH_SCOPE = "user,public_repo,repo";
    // 授权登陆页面(用WebView来加载)
    String AUTH_RUL = "https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID + "&scope=" + AUTH_SCOPE;

    // 获取token api,通过设置Accept请求头来请求不同格式的响应
    @Headers("Accept: application/json")
    @FormUrlEncoded
    //使用此授权码换取访问令牌(access token):
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessTokenResult> getOAuthToken(
            @Field("client_id") String client,
            @Field("client_secret") String clientSecret,
            @Field("code") String code);
    @GET("user")
    Call<User> getUserInfo();
    /**
     * 获取仓库
     * @Param query 查询参数(language:java)
     * @Param pageId 查询页数据(从1开始)
     */
    @GET("/search/repositories")
    Call<RepoResult> searchRepos(
            @Query("q")String query,
            @Query("page")int pageId);

    @GET("/repos/{owner}/{repo}/readme")
    Call<RepoContentResult> getReadme(
            @Path("owner") String owner,
            @Path("repo") String repo);

    @Headers({
            "Content-Type:text/plain"
    })
    @POST("/markdown/raw")
    Call<ResponseBody> markDown(@Body RequestBody body);




    @GET("/search/users")
    Call<UserResult>  searchuUser(@Query("q")String query, @Query("page")int pageId);
}