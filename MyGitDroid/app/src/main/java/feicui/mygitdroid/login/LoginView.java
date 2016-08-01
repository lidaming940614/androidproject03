package feicui.mygitdroid.login;

/**
 * Created by 1099057173 on 2016/7/31.
 */
public interface LoginView {

    void showProgress();
    void showMessage(String msg);
    void resetWeb();
    void navigateToMain();
}
