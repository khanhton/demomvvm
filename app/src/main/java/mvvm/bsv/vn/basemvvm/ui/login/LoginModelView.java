package mvvm.bsv.vn.basemvvm.ui.login;

import android.databinding.ObservableField;

import mvvm.bsv.vn.basemvvm.api.DataManager;
import mvvm.bsv.vn.basemvvm.model.LoginResponse;
import mvvm.bsv.vn.basemvvm.model.User;
import mvvm.bsv.vn.basemvvm.rx.SingleLiveEvent;
import mvvm.bsv.vn.basemvvm.ui.base.BaseModelView;
import mvvm.bsv.vn.basemvvm.utils.StringUtil;

public class LoginModelView extends BaseModelView {

    public final ObservableField<String> username = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public final SingleLiveEvent<String> onLoginSuccessed = new SingleLiveEvent<>();

    public void login(){
        if (StringUtil.isEmpty(username.get()) || StringUtil.isEmpty(password.get())){
            onLoadAPIError.setValue("User name and password is required");
        }else{
            login(new User(username.get(), password.get()));
        }
    }


    public SingleLiveEvent<String> getOnLoginSuccessed() {
        return onLoginSuccessed;
    }

    public void login(User user) {
        showLoading();
        addSubscription(DataManager.getInstall().login(user.getUsername(), user.getPassword()), loginReponse -> {
            hideLoading();
            onLoginSuccessed.setValue(((LoginResponse) loginReponse).getTitle());
        }, throwable -> {
            hideLoading();
            loadAPIFail(throwable);
        });
    }

}
