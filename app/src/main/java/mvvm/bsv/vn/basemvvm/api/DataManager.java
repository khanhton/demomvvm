package mvvm.bsv.vn.basemvvm.api;

import io.reactivex.Observable;
import mvvm.bsv.vn.basemvvm.models.LoginResponse;

/**
 * Created by tonkhanh on 11/6/18.
 */

public class DataManager {
    private static ApiStores mApiStores;
    private static DataManager mDataManager;

    public static DataManager getInstall() {
        if (mDataManager == null) {
            mDataManager = new DataManager();
        }
        if (mApiStores == null) {
            mApiStores = APIBuilder.getInstance().createService(ApiStores.class);
        }
        return mDataManager;
    }

    public Observable<LoginResponse> login(String mail, String pass) {
        return mApiStores.login();
    }
}