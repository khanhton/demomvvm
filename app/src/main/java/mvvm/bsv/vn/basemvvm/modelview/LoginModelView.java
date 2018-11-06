package mvvm.bsv.vn.basemvvm.modelview;

import android.databinding.ObservableField;

import mvvm.bsv.vn.basemvvm.model.UsersRepository;
import mvvm.bsv.vn.basemvvm.models.LoginResponse;
import mvvm.bsv.vn.basemvvm.models.User;
import mvvm.bsv.vn.basemvvm.utils.StringUtil;
import mvvm.bsv.vn.basemvvm.view.LoginView;
import mvvm.bsv.vn.basemvvm.notifimodelview.LoginNotifiModelview;

public class LoginModelView extends BaseModelView<LoginView, UsersRepository> implements LoginNotifiModelview {

    public final ObservableField<String> username = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public  LoginModelView(LoginView loginNavigator){
       this.mvvmNavigator = loginNavigator;
       this.baseRepository = new UsersRepository(this);
    }

    public void login(){
        if (StringUtil.isEmpty(username.get()) || StringUtil.isEmpty(password.get())){
            mvvmNavigator.loadAPIError("User name and password is required");
        }else{
            baseRepository.login(new User(username.get(), password.get()));
        }
    }

    @Override
    public void loginSussessed(LoginResponse loginResponse) {
        mvvmNavigator.gotoMain(loginResponse.getTitle());
    }
}
