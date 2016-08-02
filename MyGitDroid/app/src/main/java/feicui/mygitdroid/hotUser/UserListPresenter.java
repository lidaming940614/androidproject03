package feicui.mygitdroid.hotUser;

import java.util.List;

import feicui.mygitdroid.network.GitHubClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 1099057173 on 2016/8/2.
 */
public class UserListPresenter {
    private UserListView userListView;
    private int nextPage = 0;
    private Call<UserResult> resultCall;

    public UserListPresenter(UserListView userListView) {
        this.userListView = userListView;
    }

    // 下拉刷新处理
    public void refresh() {
        // 隐藏loadmore
        userListView.hideLoadMore();
        userListView.showContentView();
        nextPage = 1; // 永远刷新最新数据
        resultCall = GitHubClient.getInstance().searchuUser("followers:"+">1000",nextPage);
        resultCall.enqueue(repoCallback);
    }

    // 加载更多处理
    public void loadMore() {
        userListView.showLoadMoreLoading();
        resultCall = GitHubClient.getInstance().searchuUser("followers:"+">1000",nextPage);
        resultCall.enqueue(loadMoreCallback);
    }

    private final Callback<UserResult> loadMoreCallback = new Callback<UserResult>() {
        @Override
        public void onResponse(Call<UserResult> call, Response<UserResult> response) {
            UserResult userResult = response.body();
            if (userResult == null) {
                userListView.showLoadMoreErro("结果为空");
                return;
            }
            List<HotUser> userList = userResult.getRepoList();
            userListView.addMoreData(userList);
            nextPage++;
        }

        @Override
        public void onFailure(Call<UserResult> call, Throwable t) {
            userListView.hideLoadMore();
            userListView.showMessage("repoCallback onFailure" + t.getMessage());
        }
    };
    private  final  Callback<UserResult> repoCallback=new Callback<UserResult>() {
        @Override
        public void onResponse(Call<UserResult> call, Response<UserResult> response) {
            userListView.stopRefresh();
            UserResult userResult=response.body();
            if (userResult==null){
                userListView.showLoadMoreErro("结果为空");
                return;
            }
            if (userResult.getTotalCount() <= 0) {
                userListView.refreshData(null);
                userListView.showEmptyView();
                return;
            }
            List<HotUser> userList = userResult.getRepoList();
            userListView.refreshData(userList);
            // 下拉刷新成功(1), 下一面则更新为2
            nextPage = 2;

        }

        @Override
        public void onFailure(Call<UserResult> call, Throwable t) {
            userListView.stopRefresh();
            userListView.showMessage("repoCallback onFailure" + t.getMessage());
        }
    };

}
