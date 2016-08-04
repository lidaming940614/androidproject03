package feicui.mygitdroid.farvour;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import feicui.mygitdroid.R;
import feicui.mygitdroid.adapter.RepoListAdapter;
import feicui.mygitdroid.commons.ActivityUtils;
import feicui.mygitdroid.db.DBhelper;
import feicui.mygitdroid.db.LocalAdapter;
import feicui.mygitdroid.db.LocalRepo;
import feicui.mygitdroid.db.LocalRepoDao;
import feicui.mygitdroid.db.RepoDao;
import feicui.mygitdroid.db.RepoGroupTable;
import feicui.mygitdroid.entity.Repo;

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
    RepoDao repoDao;
    LocalAdapter ad;

    private LocalRepoDao localRepoDao; // 本地仓库DAO(数据的添删改查)
    List<LocalRepo> list;
    private int RepoGroupTableId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        repoDao = new RepoDao(DBhelper.getInstance(getContext()));
        localRepoDao = new LocalRepoDao(DBhelper.getInstance(getContext()));


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        list = new ArrayList<LocalRepo>();
        ad = new LocalAdapter();
        listView.setAdapter(ad);
        setData(R.id.repo_group_all);
        registerForContextMenu(listView);//注册上下文菜单

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String title = item.getTitle().toString();
        RepoGroupTableId = item.getItemId();
        tvGroupType.setText(title);
        setData(RepoGroupTableId);
        Log.i("msg2", "走了吗");
        return true;
    }

    private void setData(int groupId) {
        switch (groupId) {
            case R.id.repo_group_all:
                ad.addAll(localRepoDao.queryForAll());
                break;
            case R.id.repo_group_no:
                ad.addAll(localRepoDao.queryForNoGroup());
                break;
            default:
                ad.addAll(localRepoDao.queryForGroupId(groupId));
                break;
        }
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
        List<RepoGroupTable> repoGroups = repoDao.queryForAll();
        for (RepoGroupTable repoGroup : repoGroups) {
            menu.add(Menu.NONE, repoGroup.getId(), Menu.NONE, repoGroup.getName());
        }
        popupMenu.show();
    }

    private LocalRepo currentLocalRepo;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listView) {
            AdapterView.AdapterContextMenuInfo adapterMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
            int position = adapterMenuInfo.position;
            currentLocalRepo = ad.getItem(position);
            MenuInflater menuInflater = getActivity().getMenuInflater();
            menuInflater.inflate(R.menu.menu_context_favorite, menu);
            SubMenu subMenu = menu.findItem(R.id.sub_menu_move).getSubMenu();
            List<RepoGroupTable> repoGroups = repoDao.queryForAll();
            for (RepoGroupTable repoGroup : repoGroups) {
                subMenu.add(R.id.menu_group_move, repoGroup.getId(), Menu.NONE, repoGroup.getName());
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete) {
            localRepoDao.delete(currentLocalRepo);
            setData(RepoGroupTableId);
            return true;
        }
        int groupId = item.getGroupId();
        if (groupId == R.id.menu_group_move) {
            if (id == R.id.repo_group_no) {
                currentLocalRepo.setRepoGroup(null);
            } else {
                currentLocalRepo.setRepoGroup(repoDao.queryForId(id));
            }
            localRepoDao.createOrUpdate(currentLocalRepo);
            setData(RepoGroupTableId);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}