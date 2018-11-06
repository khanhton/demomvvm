package mvvm.bsv.vn.basemvvm.modelview;

import android.databinding.BaseObservable;

import mvvm.bsv.vn.basemvvm.model.BaseRepository;
import mvvm.bsv.vn.basemvvm.view.BaseView;

public abstract class BaseModelView<N extends BaseView, R extends BaseRepository>   extends BaseObservable {

    public N mvvmNavigator;
    protected R baseRepository;

    public void loadAPIFail(Throwable throwable) {
        mvvmNavigator.loadAPIFail(throwable);
    }


    public void loadAPIError(String message) {
        mvvmNavigator.loadAPIError(message);
    }

    public void detachView() {
        baseRepository.detachView();
    }

    public void hideLoading(){
        mvvmNavigator.hideLoading();
    }

    public void showLoading(){
        mvvmNavigator.showLoading();
    }
}
