package mvvm.bsv.vn.basemvvm.ui.base;

import android.databinding.BaseObservable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvvm.bsv.vn.basemvvm.model.LoginResponse;
import mvvm.bsv.vn.basemvvm.rx.ApiConsumer;
import mvvm.bsv.vn.basemvvm.rx.SingleLiveEvent;
import mvvm.bsv.vn.basemvvm.utils.LogUtil;

public abstract class BaseModelView extends BaseObservable {
    private CompositeDisposable mCompositeDisposable;

    protected final SingleLiveEvent<Boolean> onShowLoading = new SingleLiveEvent<>();
    protected final SingleLiveEvent<String> onLoadAPIFail = new SingleLiveEvent<>();
    protected final SingleLiveEvent<Throwable> onLoadAPIError = new SingleLiveEvent<>();
    protected final SingleLiveEvent<Integer> onSentMessage = new SingleLiveEvent<>();


    public void hideLoading() {
        LogUtil.log("Hide loading");
        onShowLoading.setValue(false);
    }

    public void showLoading() {
        onShowLoading.setValue(true);
    }

    public void loading(boolean isLoading){
        onShowLoading.setValue(isLoading);
    }

    public SingleLiveEvent<Boolean> getOnShowLoading() {
        return onShowLoading;
    }

    public SingleLiveEvent<String> getOnLoadAPIFail() {
        return onLoadAPIFail;
    }

    public SingleLiveEvent<Throwable> getOnLoadAPIError() {
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
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public void addSubscription(Observable observable,  ApiConsumer response ){
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        response.onLoading(true);
        mCompositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        success -> {
                            response.onLoading(false);
                            response.onSuccess(success);
                        },
                        throwable ->{
                            response.onLoading(false);
                            response.onFailure((Throwable) throwable);
                        })
        );
    };
}
