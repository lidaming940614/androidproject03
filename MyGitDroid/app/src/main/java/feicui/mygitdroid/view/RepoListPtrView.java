package feicui.mygitdroid.view;

import android.view.View;

import java.util.List;

import feicui.mygitdroid.entity.Repo;

/**
 * Created by 1099057173 on 2016/7/28.
 */
public interface RepoListPtrView {

    public void showContentView();

    public void showErrorView(String errorMsg);

    public void showEmptyView();

    public void showMessage(String msg);

    public void stopRefresh();

    void refreshData(List<Repo> data);
}
