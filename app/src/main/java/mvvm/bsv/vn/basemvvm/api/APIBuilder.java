package mvvm.bsv.vn.basemvvm.api;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tonkhanh on 11/6/18.
 */

public class APIBuilder {

    private static APIBuilder mIApiBuilder;
    /**
     * Get instance api builder.
     *
     * @return the api builder
     */
    public static APIBuilder getInstance(){
        if (mIApiBuilder == null){
            mIApiBuilder = new APIBuilder();
        }
        return mIApiBuilder;
    }

     /**
     * Create service s.
     *
     * @param <S> the type parameter
     * @param serviceClass the service class
     * @return the s
     */
    public <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }


}
