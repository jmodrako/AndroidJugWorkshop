package pl.jug.bydgoszcz.androidjugworkshop.feed;

import java.util.Collections;

import io.reactivex.Observable;
import pl.jug.bydgoszcz.androidjugworkshop.BasePresenter;
import pl.jug.bydgoszcz.androidjugworkshop.data.DataRepository;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.PostModel;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.UserModel;
import pl.jug.bydgoszcz.androidjugworkshop.util.RxUtil;

class FeedPresenter extends BasePresenter<FeedView> {

    private final DataRepository dataRepository;

    FeedPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    void loadPosts() {
        // First download users "dataRepository.users().flatMap(userModels"
        // Then download posts "dataRepository.posts().flatMap(postModels"
        // At the end we need to match post.userId to proper user model.
        dataRepository.users().flatMap(userModels -> dataRepository.posts().flatMap(postModels -> {
            final Observable<UserModel> usersObservable = Observable.fromIterable(userModels);
            final Observable<PostModel> postsObservable = Observable.fromIterable(postModels);

            // Iterate over all posts and all users to find matching data, post.userId == user.id
            return postsObservable.flatMap(post ->
                    usersObservable.flatMap(user -> {
                        if (post.getUserId() == user.getId()) {
                            post.setUser(user);
                            return Observable.just(post);
                        } else {
                            return Observable.empty();
                        }
                    })
            ).toList();
        })).compose(RxUtil.Transformers.schedulersIoAndroidSingle())
                .map(postModels -> {
                    // Just shuffling for random posts.
                    Collections.shuffle(postModels);
                    return postModels;
                })
                .doOnSubscribe(disposable -> view().onDownloadPostsStarted())
                .subscribe(result -> {
                    view().onDownloadPostsFinished();
                    view().showPosts(result);
                }, error -> {
                    view().onDownloadPostsFinished();
                    view().onDownloadPostsError();
                });
    }

    @Override
    protected Class viewClass() {
        return FeedView.class;
    }
}
