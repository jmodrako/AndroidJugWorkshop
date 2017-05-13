package pl.jug.bydgoszcz.androidjugworkshop.data;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.jug.bydgoszcz.androidjugworkshop.BuildConfig;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.PostModel;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.UserModel;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository implements JugApi {
    private final JugApi jugApi;

    public DataRepository() {
        jugApi = initializeApi();
    }

    @Override
    public Single<List<PostModel>> posts() {
        return jugApi.posts();
    }

    @Override
    public Single<List<UserModel>> users() {
        return jugApi.users();
    }

    @Override
    public Single<UserModel> user(long id) {
        return jugApi.user(id);
    }

    private JugApi initializeApi() {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        addDebugInterceptor(okHttpClientBuilder);

        final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();

        return new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build().create(JugApi.class);
    }

    private void addDebugInterceptor(OkHttpClient.Builder httpClient) {
        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor interceptor =
                    new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);
        }
    }
}
