package mvvm.bsv.vn.basemvvm.ui;

import android.os.Bundle;
import android.view.View;

import mvvm.bsv.vn.basemvvm.modelview.BaseModelView;

public abstract class BaseMVVMFragment <M extends BaseModelView> extends BaseFragment{
    protected M mvvmModelView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvvmModelView = createModelView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract M createModelView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvvmModelView != null){
            hideLoading();
            mvvmModelView.detachView();
        }
    }
}
