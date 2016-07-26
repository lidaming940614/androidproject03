package feicui.mygitdroid.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import feicui.mygitdroid.R;

/**
 * Created by 1099057173 on 2016/7/26.
 */
public class Pager0 extends FrameLayout {

    public Pager0(Context context) {
        super(context);
        init();
    }

//    public Pager0(Context context, AttributeSet attrs) {
//        super(context, attrs, 0);
//    }
//
//    public Pager0(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0, this, true);
    }


}
