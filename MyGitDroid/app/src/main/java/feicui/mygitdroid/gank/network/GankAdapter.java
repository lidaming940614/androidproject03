package feicui.mygitdroid.gank.network;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import feicui.mygitdroid.R;
import feicui.mygitdroid.gank.modle.Ganklistview;

/**
 * Created by 1099057173 on 2016/8/5.
 */
public class GankAdapter extends BaseAdapter {
    private final List<Ganklistview> datas;

    public GankAdapter() {
        datas = new ArrayList<Ganklistview>();
    }

    public void addDatall(List<Ganklistview> list) {
        datas.clear();
        datas.addAll(list);
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
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.layout_item_gank, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        Ganklistview ganklistview= (Ganklistview) getItem(position);
        viewHolder.gank_item.setText(ganklistview.getDesc());
        return convertView;
    }

    class ViewHolder {
        TextView gank_item;
        public ViewHolder(View view) {
            gank_item = (TextView) view.findViewById(R.id.gank_tv);
        }
    }
}
