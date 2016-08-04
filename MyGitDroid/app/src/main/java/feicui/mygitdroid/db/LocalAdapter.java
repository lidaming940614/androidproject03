package feicui.mygitdroid.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import feicui.mygitdroid.R;
import feicui.mygitdroid.entity.Repo;

/**
 * Created by 1099057173 on 2016/8/4.
 */
public class LocalAdapter extends BaseAdapter {

    private final ArrayList<LocalRepo> datas;

    public LocalAdapter() {
        datas = new ArrayList<LocalRepo>();
    }

    public void addAll(List<LocalRepo> repos) {
        datas.clear();
        datas.addAll(repos);
        notifyDataSetChanged();
    }

    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public LocalRepo getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.layout_item_repo, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        LocalRepo repo = getItem(position);
        viewHolder.tvRepoName.setText(repo.getFullName());
        viewHolder.tvRepoInfo.setText(repo.getDescription());
        viewHolder.tvRepoStars.setText(String.format("stars : %d", repo.getStartCount()));
        ImageLoader.getInstance().displayImage(repo.getAvatar(), viewHolder.ivIcon);
        return convertView;
    }
}
//"id": 22374063,
//        "name": "android-best-practices",
//        "full_name": "futurice/android-best-practices",
//        "avatar_url": "https://avatars.githubusercontent.com/u/852157?v=3",
//        "description": "Do's and Don'ts for Android development, by Futurice developers",
//        "stargazers_count": 10469,
//        "forks_count": 1974

class ViewHolder {
    @Bind(R.id.ivIcon)
    ImageView ivIcon;
    @Bind(R.id.tvRepoName)
    TextView tvRepoName;
    @Bind(R.id.tvRepoInfo)
    TextView tvRepoInfo;
    @Bind(R.id.tvRepoStars)
    TextView tvRepoStars;
    @Bind(R.id.layoutTexts)
    LinearLayout layoutTexts;

    ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
