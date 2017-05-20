package pl.jug.bydgoszcz.androidjugworkshop;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

        final Observable<List<JugPostModel>> singlePosts = api.posts().toObservable();
        final Observable<List<JugUserModel>> singleUsers = api.users().toObservable();

        Observable.zip(singlePosts, singleUsers,
                (posts, users) -> {
                    for (JugPostModel post : posts) {
                        for (JugUserModel user : users) {
                            if (post.getUserId() == user.getId()) {
                                post.setUser(user);
                                break;
                            }
                        }
                    }
                    return posts;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> view.showNewPosts(models));

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final Handler handler = new Handler(Looper.getMainLooper());
//                api.posts().subscribe(models -> handler.post(() -> view.showNewPosts(models)));
//            }
//        }).start();

        // how to not do, lets rxify.
        // download posts
//        api.posts().enqueue(new Callback<List<JugPostModel>>() {
//            @Override
//            public void onResponse(
//                    Call<List<JugPostModel>> call,
//                    Response<List<JugPostModel>> responsePosts) {
//
//                // download users
//                api.users().enqueue(new Callback<List<JugUserModel>>() {
//                    @Override
//                    public void onResponse(
//                            Call<List<JugUserModel>> call,
//                            Response<List<JugUserModel>> responseUsers) {
//
//                        handlePostsWithusers(responsePosts.body(), responseUsers.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<JugUserModel>> call, Throwable t) {
//
//                    }
//                });
//
//            }
//
//            @Override
//            public void onFailure(Call<List<JugPostModel>> call, Throwable t) {
//            }
//        });
    }

    private void handlePostsWithusers(List<JugPostModel> posts, List<JugUserModel> users) {

    }
}
