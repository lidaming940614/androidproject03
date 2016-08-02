package feicui.mygitdroid.hotUser;

import java.util.List;

import feicui.mygitdroid.login.User;

/**
 * Created by 1099057173 on 2016/8/2.
 */
public interface UserListPtrView {
    void showContentView();
    void showErrorView(String errorMsg);
    void showEmptyView();
    void showMessage(String msg);
    void stopRefresh();
    void refreshData(List<HotUser> data);
}
