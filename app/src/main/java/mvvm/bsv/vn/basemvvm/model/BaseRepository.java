package mvvm.bsv.vn.basemvvm.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvvm.bsv.vn.basemvvm.notifimodelview.BaseNotifiModelview;

public class BaseRepository<V extends BaseNotifiModelview>{

    public V mvvmView;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(V mvvmView) {
        this.mvvmView = mvvmView;
    }


    protected V getView() {
        return mvvmView;
    }


    public void detachView() {
//        this.mvpView = null;
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeDisposable != null && mCompositeDisposable.size() > 0) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    public void addSubscription(Observable observable, Consumer success, Consumer<? super Throwable> throwable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, throwable));
    }
}
