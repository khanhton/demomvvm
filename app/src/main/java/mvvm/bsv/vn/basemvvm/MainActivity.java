package mvvm.bsv.vn.basemvvm;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import mvvm.bsv.vn.basemvvm.ui.BaseFragment;
import mvvm.bsv.vn.basemvvm.ui.LoginFragment;
import mvvm.bsv.vn.basemvvm.utils.FragmentUtil;

public class MainActivity extends AppCompatActivity{


    public FragmentManager mFragmentManager;
    protected FragmentUtil mFragmentUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mFragmentUtil = FragmentUtil.getInstance();
        replaceFragment(LoginFragment.newInstance());
    }

    public void replaceFragment(BaseFragment fragment) {
        mFragmentUtil.replaceFragment(mFragmentManager, fragment, R.id.main_activity_container);
    }

}
