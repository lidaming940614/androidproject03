package feicui.mygitdroid.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import feicui.mygitdroid.R;
import feicui.mygitdroid.commons.ActivityUtils;
import feicui.mygitdroid.farvour.FavoriteFragment;
import feicui.mygitdroid.fragment.Fragment_hot_repo;
import feicui.mygitdroid.hotUser.HotUserFragment;
import feicui.mygitdroid.login.LoginActivity;
import feicui.mygitdroid.login.UserRepo;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigationView)
    NavigationView navigationView;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private Fragment_hot_repo fragment_hot_repo;
    private HotUserFragment hotUserFragment;
    private  FavoriteFragment favoriteFragment;
    private Button btnLogin;
    private ImageView ivIcon;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        navigationView.setNavigationItemSelectedListener(listener);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        fragment_hot_repo = new Fragment_hot_repo();
        replaceFragment(fragment_hot_repo);

        btnLogin = ButterKnife.findById(navigationView.getHeaderView(0), R.id.btnLogin);
        ivIcon = ButterKnife.findById(navigationView.getHeaderView(0), R.id.ivIcon);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (UserRepo.isEmpty()) {
            btnLogin.setText(R.string.login_github);
            return;
        }
        btnLogin.setText(R.string.switch_account);
        getSupportActionBar().setTitle(UserRepo.getUser().getName());
        String photoUrl = UserRepo.getUser().getAvatar();
        ImageLoader.getInstance().displayImage(photoUrl, ivIcon);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    private NavigationView.OnNavigationItemSelectedListener listener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.github_hot_repo:
                    if (fragment_hot_repo == null) {
                        fragment_hot_repo = new Fragment_hot_repo();
                        if (!fragment_hot_repo.isAdded()) {
                            replaceFragment(fragment_hot_repo);

                        }
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.github_hot_coder:
                    if (hotUserFragment == null) {
                        hotUserFragment = new HotUserFragment();
                        if (!hotUserFragment.isAdded()) {
                            replaceFragment(hotUserFragment);

                        }
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case  R.id.arsenal_my_repo:
                    if(favoriteFragment == null)favoriteFragment = new FavoriteFragment();
                    if(!favoriteFragment.isAdded()){
                        replaceFragment(favoriteFragment);
                    }
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    };
}
