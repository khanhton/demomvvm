package mvvm.bsv.vn.basemvvm.rx;

import mvvm.bsv.vn.basemvvm.model.BaseResponse;

/**
 * Created by Khanh Ton on 2019-05-30.
 */
public abstract class ApiConsumer<M extends BaseResponse>{

    public void onSuccess(Object o) {
        onSuccess((M) o);
    }

    public abstract void onSuccess(M response);

    public abstract void onFailure(Throwable throwable);

    public abstract void onLoading(boolean isLoading);

}
