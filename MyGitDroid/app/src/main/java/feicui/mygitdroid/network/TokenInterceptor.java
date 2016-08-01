package feicui.mygitdroid.network;

import java.io.IOException;

import feicui.mygitdroid.login.CurrentUser;
import feicui.mygitdroid.login.UserRepo;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 1099057173 on 2016/7/31.
 */
public class TokenInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        if (UserRepo.hasAccessToken()) {
            builder.header("Authorization", "token " + UserRepo.getAccessToken());
        }
        Response response = chain.proceed(builder.build());
        if (response.isSuccessful()) {
            return response;
        }
        if (response.code() == 401 || response.code() == 403) {
            throw new IOException("未经授权的！限制是每分钟10次！");
        } else {
            throw new IOException("响应码:" + response.code());
        }
    }
}
