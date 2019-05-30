package mvvm.bsv.vn.basemvvm.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

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

    private void setupObserveModelViewBase(){
        mvvmModelView.getOnLoadAPIFail().observe(this, throwable -> loadAPIFail(throwable));

        mvvmModelView.getOnLoadAPIError().observe(this, msg -> loadAPIError(msg));

        mvvmModelView.getShowLoadingEvent().observe(this, isShow -> {
            if(isShow) showLoading(); else hideLoading();
        });
    }
}
