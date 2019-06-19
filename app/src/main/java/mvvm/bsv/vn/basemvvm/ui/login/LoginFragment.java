package mvvm.bsv.vn.basemvvm.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import mvvm.bsv.vn.basemvvm.BR;
import mvvm.bsv.vn.basemvvm.R;
import mvvm.bsv.vn.basemvvm.ui.main.MainTabHostFragment;
import mvvm.bsv.vn.basemvvm.ui.base.BaseMVVMFragment;
import mvvm.bsv.vn.basemvvm.utils.MathUtils;
import mvvm.bsv.vn.basemvvm.utils.Utils;

public class LoginFragment extends BaseMVVMFragment<LoginModelView> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected LoginModelView createModelView() {
        return new LoginModelView();
    }

    @Override
    protected int getIDVariableBinding() {
        return BR.vm;
    }

    @Override
    protected void setupObserveModelView(LoginModelView mvvmModelView) {
        mvvmModelView.getOnLoginSuccessed().observe(this, s -> {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                mMainActivity.replaceFragment(MainTabHostFragment.newInstance(), true);
            }
        );

    }


    @Override
    protected void init(View view) {
        tvTitle.setText("MVVM");
        mvvmModelView.username.set("xyz");
        int sum = Utils.doAction(3,6, MathUtils::sum);


    }

    @Override
    public int getRootLayout() {
        return  R.layout.login_layout;
    }

}
