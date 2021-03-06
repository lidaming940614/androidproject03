package feicui.mygitdroid.splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import feicui.mygitdroid.R;
import feicui.mygitdroid.splash.pager.Pager2;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by 1099057173 on 2016/7/26.
 */
public class SplashPagerFragment extends Fragment {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;//指示器

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.ivPhoneBlank)
    ImageView ivPhoneBlank;
    @Bind(R.id.ivPhoneFont)
    ImageView ivPhoneFont;
    @Bind(R.id.layoutPhoneInner)
    FrameLayout layoutPhoneInner;
    @Bind(R.id.layoutPhone)
    FrameLayout layoutPhone;
    private SplashPagerAdapter adapter;
    @BindColor(R.color.colorRed)
    int colorRed;
    @BindColor(R.color.colorYellow)
    int colorYellow;
    @BindColor(R.color.colorGreen)
    int colorGreen;
    Pager2 pager2;
    int w, h;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        ButterKnife.bind(this, view);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        w = dm.widthPixels;
        h = dm.densityDpi;
        Log.i("msg", "" + w + "高" + h);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
//        colorGreen=getResources().getColor(R.color.colorGreen);
//        colorRed=getResources().getColor(R.color.colorRed);
//        colorYellow=getResources().getColor(R.color.colorYellow);
        // 添加ViewPager监听(为了动画)
        viewPager.addOnPageChangeListener(pageColorListener);
        viewPager.addOnPageChangeListener(phoneViewListener);
    }

    // 主要为了做背景颜色渐变处理
    private ViewPager.OnPageChangeListener pageColorListener = new ViewPager.OnPageChangeListener() {
        final ArgbEvaluator argbEvaluator = new ArgbEvaluator();

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == 0) {
                int color = (int) argbEvaluator.evaluate(positionOffset, colorGreen, colorRed);
                content.setBackgroundColor(color);
                return;
            }
            if (position == 1) {
                int color = (int) argbEvaluator.evaluate(positionOffset, colorRed, colorYellow);
                content.setBackgroundColor(color);
            }

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private ViewPager.OnPageChangeListener phoneViewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == 0) {//第一个页面到第二个页面的手机放缩
                float scale = 0.3f + positionOffset * 0.7f;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                //手机的平移
                int scroll = (int) ((positionOffset - 1) * 300);
                layoutPhone.setTranslationX(scroll);
                ivPhoneFont.setAlpha(positionOffset);
                return;
            }
            if (position == 1) {
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            // 当显示出最后一个pager时，播放它自己的动画
            if (position == 2) {
                Pager2 pager2View = (Pager2) adapter.getView(position);
                pager2View.showAnimation();
            }else  if (position==1){
                Pager2 pager2= (Pager2) adapter.getView(2);
                pager2.hideAnimation();
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
