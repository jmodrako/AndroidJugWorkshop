package pl.jug.bydgoszcz.androidjugworkshop;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Connection {
    private static final String API_URL = "https://jsonplaceholder.typicode.com";

    private Api api;

    Connection() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final GsonConverterFactory converterFactory = GsonConverterFactory.create();

        api = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .build()
                .create(Api.class);
    }

    Api getApi() {
        return api;
    }
}
