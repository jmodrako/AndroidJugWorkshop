package pl.jug.bydgoszcz.androidjugworkshop;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class JugFeedPresetner {

    private final Connection connection;
    private JugFeedView view;

    JugFeedPresetner(Connection connection) {
        this.connection = connection;
    }

    void attach(JugFeedView view) {
        this.view = view;
    }

    void detach() {
        this.view = null;
    }

    void loadPosts() {
        final Api api = connection.getApi();
        api.posts().enqueue(new Callback<List<JugPostModel>>() {
            @Override
            public void onResponse(
                    Call<List<JugPostModel>> call,
                    Response<List<JugPostModel>> response) {

                final List<JugPostModel> models = response.body();
                view.showNewPosts(models);
            }

            @Override
            public void onFailure(Call<List<JugPostModel>> call, Throwable t) {
            }
        });
    }
}
