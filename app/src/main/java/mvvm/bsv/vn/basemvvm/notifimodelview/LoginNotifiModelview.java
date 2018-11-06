package mvvm.bsv.vn.basemvvm.notifimodelview;

import mvvm.bsv.vn.basemvvm.models.LoginResponse;

public interface LoginNotifiModelview extends BaseNotifiModelview {
    void loginSussessed(LoginResponse loginResponse);
}
