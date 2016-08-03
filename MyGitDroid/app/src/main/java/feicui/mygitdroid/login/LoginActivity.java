package feicui.mygitdroid.login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import feicui.mygitdroid.R;
import feicui.mygitdroid.activity.MainActivity;
import feicui.mygitdroid.commons.ActivityUtils;
import feicui.mygitdroid.network.GitHubApi;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by 1099057173 on 2016/7/29.
 */
public class LoginActivity extends AppCompatActivity implements LoginView{
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.gifImageView)
    GifImageView gifImageView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private LoginPresenter presenter;
    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_login);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new LoginPresenter(this);
        initWebView();
    }

    private void initWebView() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        webView.loadUrl(GitHubApi.AUTH_RUL);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);
    }

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress >= 100) {
                gifImageView.setVisibility(View.GONE);
            }
        }
    };

    private WebViewClient webViewClient = new WebViewClient() {
        @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri = Uri.parse(url);
            if (GitHubApi.CALL_BACK.equals(uri.getScheme())) {
                String code = uri.getQueryParameter("code");
                Log.i("msg2","走1");
                presenter.login(code);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };

    @Override public void showProgress() {
        gifImageView.setVisibility(View.VISIBLE);
    }

    @Override public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override public void resetWeb() {
        initWebView();
    }

    @Override public void navigateToMain() {
//        activityUtils.startActivity(MainActivity.class);
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        Log.i("msg2","走2");
        finish();
    }
}
