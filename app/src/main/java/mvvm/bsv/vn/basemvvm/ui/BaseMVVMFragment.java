package mvvm.bsv.vn.basemvvm.ui;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import mvvm.bsv.vn.basemvvm.BR;
import mvvm.bsv.vn.basemvvm.modelview.BaseModelView;

public abstract class BaseMVVMFragment <M extends BaseModelView> extends BaseFragment{
    protected M mvvmModelView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvvmModelView = createModelView();
        setupObserveModelViewBase();
        setupObserveModelView(mvvmModelView);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        DataBindingUtil.bind(view).setVariable(getIDVariableBinding(), mvvmModelView);
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract M createModelView();
    protected abstract int getIDVariableBinding();

    protected abstract void setupObserveModelView(M mvvmModelView);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvvmModelView != null){
            hideLoading();
            mvvmModelView.detachView();
        }
    }

    protected void setupObserveModelViewBase(){
        mvvmModelView.getOnLoadAPIFail().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {
                loadAPIFail(throwable);
            }
        });

        mvvmModelView.getOnLoadAPIError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String msg) {
                loadAPIError(msg);
            }
        });

        mvvmModelView.getShowLoadingEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isShow) {
                if(isShow) showLoading(); else hideLoading();
            }
        });
    }
}
