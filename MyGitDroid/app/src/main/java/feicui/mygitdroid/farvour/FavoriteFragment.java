package feicui.mygitdroid.farvour;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import feicui.mygitdroid.R;
import feicui.mygitdroid.commons.ActivityUtils;
import feicui.mygitdroid.db.DBhelper;
import feicui.mygitdroid.db.LocalRepoDao;
import feicui.mygitdroid.db.RepoGroupTable;

/**
 * Created by 1099057173 on 2016/8/3.
 */
public class FavoriteFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {
    @Bind(R.id.tvGroupType)
    TextView tvGroupType;
    @Bind(R.id.btnFilter)
    ImageButton btnFilter;
    @Bind(R.id.listView)
    ListView listView;
    private ActivityUtils activityUtils;
    LocalRepoDao localRepoDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        localRepoDao = new LocalRepoDao(DBhelper.getInstance(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String title = item.getTitle().toString();
        tvGroupType.setText(title);
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnFilter)
    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_popup_repo_groups);
        Menu menu = popupMenu.getMenu();
        List<RepoGroupTable> repoGroups = localRepoDao.queryForAll();
        for (RepoGroupTable repoGroup : repoGroups) {
            menu.add(Menu.NONE, repoGroup.getId(), Menu.NONE, repoGroup.getName());
        }
        popupMenu.show();
    }

}