package feicui.mygitdroid.hotUser;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import feicui.mygitdroid.R;

/**
 * Created by 1099057173 on 2016/8/2.
 */
public class UserAdapter extends BaseAdapter {
    private final ArrayList<HotUser> datas;

    public UserAdapter() {
        datas = new ArrayList<HotUser>();
    }

    public void addAll(Collection<HotUser> repos) {
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
    public Object getItem(int position) {
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
            convertView = inflater.inflate(R.layout.layout_item_user, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        } else {

            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            HotUser user = (HotUser) getItem(position);
            viewHolder.tvRepoName1.setText(user.getLogin());
            viewHolder.tvRepoInfo1.setText(user.getId() + "");
            viewHolder.tvRepoStars1.setText(user.getScore());
            ImageLoader.getInstance().displayImage(user.getAvatar_url(), viewHolder.ivIcon1);
//
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.ivIcon1)
        ImageView ivIcon1;
        @Bind(R.id.tvRepoName1)
        TextView tvRepoName1;
        @Bind(R.id.tvRepoInfo1)
        TextView tvRepoInfo1;
        @Bind(R.id.tvRepoStars1)
        TextView tvRepoStars1;
        @Bind(R.id.layoutTexts1)
        LinearLayout layoutTexts1;
        @Bind(R.id.imageView1)
        ImageView imageView1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
