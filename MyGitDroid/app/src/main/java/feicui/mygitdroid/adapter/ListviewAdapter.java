package feicui.mygitdroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 1099057173 on 2016/7/27.
 */
public class ListviewAdapter extends BaseAdapter {
    List<String> list;
    Context context;
    public ListviewAdapter(Context context) {
        this.context = context;
    }

    public ListviewAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }
}
