package feicui.mygitdroid.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by 1099057173 on 2016/7/27.
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setlayout();
        indata();
        getview();
        setlayout();
    }
/*
* 加载布局
* */
    public abstract void setlayout();
/*
* 加载数据*/
    public abstract void indata();
/*
* 获得控件*/
    public abstract void getview();
/*
* 控件设置*/
    public abstract void setview();
}
