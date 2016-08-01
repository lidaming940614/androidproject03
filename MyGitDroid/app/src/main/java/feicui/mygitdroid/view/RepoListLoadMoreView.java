package feicui.mygitdroid.view;

import java.util.List;

import feicui.mygitdroid.entity.Repo;

/**
 * Created by 1099057173 on 2016/7/28.
 */
public interface  RepoListLoadMoreView {
    void showLoadMoreLoading();
    void hideLoadMore();
    void showLoadMoreErro(String erroMsg);
    void addMoreData(List<Repo> datas);
}
