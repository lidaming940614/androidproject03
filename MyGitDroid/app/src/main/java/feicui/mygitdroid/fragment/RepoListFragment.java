package feicui.mygitdroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import feicui.mygitdroid.R;
import feicui.mygitdroid.adapter.ListviewAdapter;
import feicui.mygitdroid.db.BaseFragment;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;


/**
 * Created by 1099057173 on 2016/7/27.
 */
public class RepoListFragment extends BaseFragment {
    View view;
    @Bind(R.id.lvRepos)
    ListView lvRepos;
    ListviewAdapter ad;
    List<String> list;
    @Bind(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @Bind(R.id.emptyView)
    TextView emptyView;
    @Bind(R.id.errorView)
    TextView errorView;
    // 用来做当前页面业务逻辑及视图更新的
    private RepoListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);
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
        list = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            list.add("doubi" + i);
        }
        ad = new ListviewAdapter(list);
    }

    @Override
    public void setview() {
        lvRepos.setAdapter(ad);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initPullToRefresh() {
        // 使用当前对象做为key，来记录上一次的刷新时间,如果两次下拉太近，将不会触发新刷新
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
        // 关闭header所用时长
        ptrClassicFrameLayout.setDurationToCloseHeader(1500);
        // 下拉刷新监听处理
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
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
        header.initWithString("I LIKE " + " JAVA");
        header.setPadding(0, 60, 0, 60);
        // 修改Ptr的HeaderView效果
        ptrClassicFrameLayout.setHeaderView(header);
        ptrClassicFrameLayout.addPtrUIHandler(header);
        ptrClassicFrameLayout.setBackgroundResource(R.color.colorRefresh);
    }

    // 刷新的方法
    // 视图上:
    // 显示内容 or 错误 or 空白 , 三选一
    public void showContentView() {
        ptrClassicFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    public void showErrorView(String errorMsg) {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    public void showEmptyView() {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    //
    // 显示提示信息
    // 如：Toast， 直接在当前页面上页面
    public void showMessage(String msg) {

    }

    //
    public void stopRefresh() {
        ptrClassicFrameLayout.refreshComplete();
    }

    //
    // 刷新数据
    // 将后台线程更新加载到的数据，刷新显示到视图(listview)上来显示给用户看
    public void refreshData(List<String> data) {
//        ad.clear();
//        ad.addAll(data);
//        ad.notifyDataSetChanged();
    }

}
