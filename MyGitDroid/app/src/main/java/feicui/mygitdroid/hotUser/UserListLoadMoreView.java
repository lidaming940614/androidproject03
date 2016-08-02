package feicui.mygitdroid.hotUser;

import java.util.List;

import feicui.mygitdroid.login.User;

/**
 * Created by 1099057173 on 2016/8/2.
 */
public interface UserListLoadMoreView {
    void showLoadMoreLoading();
    void hideLoadMore();
    void showLoadMoreErro(String erroMsg);
    void addMoreData(List<HotUser> datas);

}
