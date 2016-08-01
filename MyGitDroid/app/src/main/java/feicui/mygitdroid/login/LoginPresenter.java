package feicui.mygitdroid.login;

import feicui.mygitdroid.commons.LogUtils;
import feicui.mygitdroid.network.GitHubApi;
import feicui.mygitdroid.network.GitHubClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 1099057173 on 2016/7/29.
 */
public class LoginPresenter {

    private Call<AccessTokenResult> tokenCall;
    private Call<User> userCall;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void login(String code) {
        loginView.showProgress();
        if (tokenCall != null) tokenCall.cancel();

        tokenCall = GitHubClient.getInstance().getOAuthToken(
                GitHubApi.CLIENT_ID,
                GitHubApi.CLIENT_SECRET,
                code);
        tokenCall.enqueue(tokenCallback);
    }

    private Callback<AccessTokenResult> tokenCallback = new Callback<AccessTokenResult>() {
        @Override public void onResponse(Call<AccessTokenResult> call, Response<AccessTokenResult> response) {

            AccessTokenResult result = response.body();
            String token = result.getAccessToken();

            UserRepo.setAccessToken(token);
            if (userCall != null) userCall.cancel();
            userCall = GitHubClient.getInstance().getUserInfo();
            userCall.enqueue(userCallback);
        }

        @Override public void onFailure(Call<AccessTokenResult> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWeb();
        }
    };

    private Callback<User> userCallback = new Callback<User>() {
        @Override public void onResponse(Call<User> call, Response<User> response) {
            User user = response.body();
            UserRepo.setUser(user);
            loginView.showMessage("登陆成功");
            loginView.navigateToMain();
        }

        @Override public void onFailure(Call<User> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWeb();
        }
    };
}
