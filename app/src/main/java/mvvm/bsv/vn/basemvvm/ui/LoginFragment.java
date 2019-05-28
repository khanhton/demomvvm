package mvvm.bsv.vn.basemvvm.ui;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import mvvm.bsv.vn.basemvvm.BR;
import mvvm.bsv.vn.basemvvm.R;
import mvvm.bsv.vn.basemvvm.modelview.LoginModelView;

public class LoginFragment extends BaseMVVMFragment<LoginModelView>{

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
        mvvmModelView.getOnLoginSuccessed().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void init(View view) {
    }

    @Override
    public int getRootLayout() {
        return  R.layout.login_layout;
    }

    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

}
