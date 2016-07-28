package feicui.mygitdroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import feicui.mygitdroid.fragment.Fragment_hot_repo;

/**
 * Created by 1099057173 on 2016/7/27.
 */
public class HotRepoAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    List<String> stringList;

    public HotRepoAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList<Fragment>();
        this.stringList = new ArrayList<String>();
    }

    public HotRepoAdapter(FragmentManager fm, List<String> stringList) {
        super(fm);
        this.stringList = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
//        return fragmentList.size();
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return stringList.get(position);
        return "Java"+position;
    }
}
