package mvvm.bsv.vn.basemvvm.ui.base;

import android.databinding.BaseObservable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvvm.bsv.vn.basemvvm.rx.SingleLiveEvent;

public abstract class BaseModelView   extends BaseObservable {
    private CompositeDisposable mCompositeDisposable;

    protected final SingleLiveEvent<Boolean> onShowLoading = new SingleLiveEvent<>();
    protected final SingleLiveEvent<Throwable> onLoadAPIFail = new SingleLiveEvent<>();
    protected final SingleLiveEvent<String> onLoadAPIError = new SingleLiveEvent<>();
    protected final SingleLiveEvent<Integer> onSentMessage = new SingleLiveEvent<>();

    public void loadAPIFail(Throwable throwable) {
        onLoadAPIFail.setValue(throwable);
    }


    public void loadAPIError(String message) {
        onLoadAPIError.setValue(message);
    }

    public void hideLoading(){
        onShowLoading.setValue(false);
    }

    public void showLoading(){
        onShowLoading.setValue(true);
    }

    public SingleLiveEvent<Boolean> getOnShowLoading() {
        return onShowLoading;
    }

    public SingleLiveEvent<Throwable> getOnLoadAPIFail() {
        return onLoadAPIFail;
    }

    public SingleLiveEvent<String> getOnLoadAPIError() {
        return onLoadAPIError;
    }

    public SingleLiveEvent<Integer> getOnSentMessage() {
        return onSentMessage;
    }

    public void detachView() {
        onUnsubscribe();
    }

    //RXjava
    public void onUnsubscribe() {
        if (mCompositeDisposable != null && mCompositeDisposable.size() > 0) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
        if(mCompositeDisposable != null && !mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
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
