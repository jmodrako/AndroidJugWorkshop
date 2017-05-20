package pl.jug.bydgoszcz.androidjugworkshop;

import java.util.List;

public interface JugFeedView {
    void showNewPosts(List<JugPostModel> models);

    void onPostsDownloadFailed();
}
