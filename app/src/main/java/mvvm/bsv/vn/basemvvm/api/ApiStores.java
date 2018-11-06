package mvvm.bsv.vn.basemvvm.api;

import io.reactivex.Observable;
import mvvm.bsv.vn.basemvvm.models.LoginResponse;
import retrofit2.http.GET;

/**
 * Created by tonkhanh on 11/6/18.
 */

public interface ApiStores {
    @GET("/posts/1")
    Observable<LoginResponse> login();
}