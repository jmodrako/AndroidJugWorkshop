package pl.jug.bydgoszcz.androidjugworkshop;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

interface Api {

    @GET("/posts")
    Single<List<JugPostModel>> posts();

    @GET("/users")
    Single<List<JugUserModel>> users();
}
