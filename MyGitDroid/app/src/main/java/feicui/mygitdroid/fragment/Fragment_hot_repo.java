package feicui.mygitdroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import feicui.mygitdroid.R;
import feicui.mygitdroid.adapter.HotRepoAdapter;
import feicui.mygitdroid.base.BaseFragment;

/**
 * Created by 1099057173 on 2016/7/27.
 */
public class Fragment_hot_repo extends BaseFragment {
    View view;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    HotRepoAdapter ad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot_repo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getview();
        setview();
    }

    @Override
    public void getview() {
        ad = new HotRepoAdapter(getChildFragmentManager(),getContext());
    }

    @Override
    public void setview() {
        viewPager.setAdapter(ad);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
