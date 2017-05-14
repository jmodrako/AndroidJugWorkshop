package pl.jug.bydgoszcz.androidjugworkshop.feed;

import java.util.List;

import pl.jug.bydgoszcz.androidjugworkshop.feed.model.PostModel;

interface FeedView {
    void showPosts(List<PostModel> postModels);

    void onDownloadPostsError();

    void onDownloadPostsStarted();

    void onDownloadPostsFinished();
}
