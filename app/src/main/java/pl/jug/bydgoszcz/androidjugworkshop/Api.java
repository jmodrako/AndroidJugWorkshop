package pl.jug.bydgoszcz.androidjugworkshop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface Api {
    @GET("/posts")
    Call<List<JugPostModel>> posts();
}
