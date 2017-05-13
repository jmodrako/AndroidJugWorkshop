package pl.jug.bydgoszcz.androidjugworkshop.data;

import java.util.List;

import io.reactivex.Single;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.PostModel;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.UserModel;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface JugApi {
    @GET("/posts")
    Single<List<PostModel>> posts();

    @GET("/users")
    Single<List<UserModel>> users();

    @GET("/users/{id}")
    Single<UserModel> user(@Path("id") long id);
}
