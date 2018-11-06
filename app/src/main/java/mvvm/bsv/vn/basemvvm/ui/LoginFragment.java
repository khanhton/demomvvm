package mvvm.bsv.vn.basemvvm.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import mvvm.bsv.vn.basemvvm.R;
import mvvm.bsv.vn.basemvvm.databinding.LoginLayoutBinding;
import mvvm.bsv.vn.basemvvm.modelview.LoginModelView;
import mvvm.bsv.vn.basemvvm.utils.LogUtil;
import mvvm.bsv.vn.basemvvm.view.LoginView;

public class LoginFragment extends BaseMVVMFragment<LoginModelView> implements LoginView {

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected LoginModelView createModelView() {
        return new LoginModelView(this);
    }

    @Override
    protected void init(View view) {
    }

    @Override
    public View getRootLayout() {
        LoginLayoutBinding loginLayoutBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.login_layout,null, false);
        LoginModelView loginModelView = new LoginModelView(this);
        loginLayoutBinding.setVm(loginModelView);
        return loginLayoutBinding.getRoot();
    }

    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void gotoMain(String message) {
        LogUtil.log("Go to main: "+message);
        showSnackbar("Login successed!");
    }
}
