package mvvm.bsv.vn.basemvvm.ui;

import android.os.Bundle;
import android.view.View;
import mvvm.bsv.vn.basemvvm.R;

/**
 * Created by Khanh Ton on 2019-05-28.
 */
public class MainTabHostFragment extends BaseFragment {


    public static MainTabHostFragment newInstance() {

        Bundle args = new Bundle();

        MainTabHostFragment fragment = new MainTabHostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    public int getRootLayout() {
        return R.layout.main_tab_host_layout;
    }
}
