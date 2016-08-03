package feicui.mygitdroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 1099057173 on 2016/7/27.
 */
public abstract class BaseFragment extends Fragment {

    public abstract void getview();

    public abstract void setview();
}
