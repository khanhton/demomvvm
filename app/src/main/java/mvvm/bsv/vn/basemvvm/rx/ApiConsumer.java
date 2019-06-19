package mvvm.bsv.vn.basemvvm.rx;

import mvvm.bsv.vn.basemvvm.model.BaseResponse;

/**
 * Created by Khanh Ton on 2019-05-30.
 */

public interface  ApiConsumer<M extends BaseResponse>{

    default void onSuccess(Object o) {
        onSuccess((M) o);
    }

    void onSuccess(M response);

    void onFailure(Throwable throwable);

    void onLoading(boolean isLoading);

}
