package feicui.mygitdroid.hotUser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import feicui.mygitdroid.R;
import feicui.mygitdroid.commons.ActivityUtils;
import feicui.mygitdroid.login.User;
import feicui.mygitdroid.view.FooterView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by 1099057173 on 2016/8/2.
 */
public class HotUserFragment extends Fragment implements UserListView {
    @Bind(R.id.userlistview)
    ListView userlistview;
    @Bind(R.id.FrameLayout2)
    PtrClassicFrameLayout FrameLayout2;
    @Bind(R.id.user_emptyView)
    TextView userEmptyView;
    @Bind(R.id.user_errorView)
    TextView userErrorView;
    private UserAdapter ad;
    private UserListPresenter presenter;
    private FooterView footerView; // 上拉加载更多的视图
    private ActivityUtils activityUtils;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_user, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        activityUtils = new ActivityUtils(this);
        presenter = new UserListPresenter(this);
        ad = new UserAdapter();
        userlistview.setAdapter(ad);
        initPullToRefresh();
        initLoadMoreScroll();
        if (ad.getCount() == 0) {
            FrameLayout2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FrameLayout2.autoRefresh();
                }
            }, 300);
        }
    }

    private void initLoadMoreScroll() {
        footerView = new FooterView(getContext());
        Mugen.with(userlistview, new MugenCallbacks() {
            // listview，滚动到底部,将触发此方法
            @Override
            public void onLoadMore() {
                // 执行上拉加载数据的业务处理
                presenter.loadMore();
            }

            // 是否正在加载中
            // 其内部将用此方法来判断是否触发onLoadMore
            @Override
            public boolean isLoading() {
                return userlistview.getFooterViewsCount() > 0 && footerView.isLoading();
            }

            // 是否已加载完成所有数据
            // 其内部将用此方法来判断是否触发onLoadMore
            @Override
            public boolean hasLoadedAllItems() {
                return userlistview.getFooterViewsCount() > 0 && footerView.isComplete();
            }
        }).start();
    }

    private void initPullToRefresh() {
        FrameLayout2.setLastUpdateTimeRelateObject(this);
        // 关闭header所用时长
        FrameLayout2.setDurationToCloseHeader(1500);
        // 下拉刷新监听处理
        FrameLayout2.setPtrHandler(new PtrDefaultHandler() {
            // 当你"下拉时",将触发此方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                // 去做数据的加载，做具体的业务
                // 也就是说，你要抛开视图，到后台线程去做你的业务处理(数据刷新加载)
                presenter.refresh();
            }
        });
        // 以下代码（只是修改了header样式）
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("I LIKE " + "ANDROID");
        header.setPadding(0, 60, 0, 60);
        // 修改Ptr的HeaderView效果
        FrameLayout2.setHeaderView(header);
        FrameLayout2.addPtrUIHandler(header);
        FrameLayout2.setBackgroundResource(R.color.colorRefresh);
    }

    @Override
    public void showLoadMoreLoading() {
        if (userlistview.getFooterViewsCount() == 0) {
            userlistview.addFooterView(footerView);
        }
        footerView.showLoading();
    }

    @Override
    public void hideLoadMore() {
        userlistview.removeFooterView(footerView);
    }

    @Override
    public void showLoadMoreErro(String erroMsg) {
        if (userlistview.getFooterViewsCount() == 0) {
            userlistview.addFooterView(footerView);
        }
        footerView.showError(erroMsg);
    }

    @Override
    public void addMoreData(List<HotUser> datas) {
        ad.addAll(datas);
    }


    @Override
    public void showContentView() {
        FrameLayout2.setVisibility(View.VISIBLE);
        userEmptyView.setVisibility(View.GONE);
        userErrorView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView(String errorMsg) {
        FrameLayout2.setVisibility(View.GONE);
        userEmptyView.setVisibility(View.GONE);
        userErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        FrameLayout2.setVisibility(View.GONE);
        userEmptyView.setVisibility(View.VISIBLE);
        userErrorView.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void stopRefresh() {
        FrameLayout2.refreshComplete();
    }

    @Override
    public void refreshData(List<HotUser> data) {
        ad.clear();
        ad.addAll(data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
