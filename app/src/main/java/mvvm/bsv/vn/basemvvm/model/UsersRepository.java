package mvvm.bsv.vn.basemvvm.model;

import mvvm.bsv.vn.basemvvm.api.DataManager;
import mvvm.bsv.vn.basemvvm.models.LoginResponse;
import mvvm.bsv.vn.basemvvm.models.User;
import mvvm.bsv.vn.basemvvm.notifimodelview.LoginNotifiModelview;

public class UsersRepository extends BaseRepository<LoginNotifiModelview>{

    public UsersRepository(LoginNotifiModelview loginView){
        attachView(loginView);
    }

    public void login(User user) {
        mvvmView.showLoading();
        addSubscription(DataManager.getInstall().login(user.getUsername(), user.getPassword()), loginReponse -> {
            mvvmView.hideLoading();
            mvvmView.loginSussessed((LoginResponse) loginReponse);
        }, throwable -> {
            mvvmView.hideLoading();
            mvvmView.loadAPIFail(throwable);
        });
    }
}
