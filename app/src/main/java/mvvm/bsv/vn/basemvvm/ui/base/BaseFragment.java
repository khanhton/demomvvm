package mvvm.bsv.vn.basemvvm.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.ButterKnife;
import mvvm.bsv.vn.basemvvm.ui.MainActivity;
import mvvm.bsv.vn.basemvvm.utils.LogUtil;

public abstract class BaseFragment  extends Fragment {

    protected MainActivity mMainActivity;
    private KProgressHUD kProgressHUD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mMainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getRootLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init(view);
        setupActionBar();
    }

    protected abstract void init(View view);

    /**
     *
     * @return layout resource id
     */
    public abstract  @LayoutRes
    int getRootLayout();

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
    public void onDestroy() {
        hideLoading();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void hideLoading() {
        if(kProgressHUD != null && kProgressHUD.isShowing() && isAdded()){
            kProgressHUD.dismiss();
        }
    }

    public void showLoading() {
        if(kProgressHUD == null){
            kProgressHUD = KProgressHUD.create(mMainActivity)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f);

        }
        if(!kProgressHUD.isShowing() && isAdded()){
            kProgressHUD.show();
        }
    }

    public void loadAPIFail(String message) {
        LogUtil.log(message);
        showSnackbar(message);
    }

    public void loadAPIError(Throwable throwable) {
        LogUtil.log(throwable.getMessage());
        showSnackbar(throwable.getMessage());
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
