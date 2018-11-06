package mvvm.bsv.vn.basemvvm.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import mvvm.bsv.vn.basemvvm.MainActivity;
import mvvm.bsv.vn.basemvvm.databinding.LoginLayoutBinding;
import mvvm.bsv.vn.basemvvm.utils.LogUtil;

public abstract class BaseFragment  extends Fragment {

    protected MainActivity mMainActivity;
    CompositeDisposable disposable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mMainActivity = (MainActivity) getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getRootLayout();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        bindView(view);
        init(view);
        setupActionBar();
    }

    protected abstract void init(View view);

    public abstract View getRootLayout();

    public abstract void bindView(View view);

    public void setupActionBar() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideLoading();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void hideLoading() {
        LogUtil.log("hideLoading");
    }

    public void showLoading() {
        LogUtil.log("showLoading");
    }

    public void loadAPIFail(Throwable throwable) {
        LogUtil.log(throwable.getMessage());
        showSnackbar(throwable.getMessage());
    }

    public void loadAPIError(String message) {
        LogUtil.log(message);
        showSnackbar(message);
    }

    public void showSnackbar(String message){
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }

}
