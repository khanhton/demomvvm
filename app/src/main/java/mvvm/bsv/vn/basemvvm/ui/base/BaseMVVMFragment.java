package mvvm.bsv.vn.basemvvm.ui.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

public abstract class BaseMVVMFragment <M extends BaseModelView> extends BaseFragment{
    protected M mvvmModelView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mvvmModelView = createModelView();
        DataBindingUtil.bind(view).setVariable(getIDVariableBinding(), mvvmModelView);
        setupObserveModelViewBase();
        setupObserveModelView(mvvmModelView);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mvvmModelView != null){
            hideLoading();
            mvvmModelView.detachView();
        }
        super.onDestroyView();
    }

    protected abstract M createModelView();
    protected abstract int getIDVariableBinding();
    protected abstract void setupObserveModelView(M mvvmModelView);

    private void setupObserveModelViewBase(){
        mvvmModelView.getOnLoadAPIFail().observe(this, msg -> loadAPIFail(msg));

        mvvmModelView.getOnLoadAPIError().observe(this, throwable -> loadAPIError(throwable));

        mvvmModelView.getOnShowLoading().observe(this, isShow -> {
            if(isShow) showLoading(); else hideLoading();
        });
    }
}
