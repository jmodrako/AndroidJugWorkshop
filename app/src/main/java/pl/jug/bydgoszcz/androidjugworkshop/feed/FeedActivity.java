package pl.jug.bydgoszcz.androidjugworkshop.feed;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.List;

import pl.jug.bydgoszcz.androidjugworkshop.R;
import pl.jug.bydgoszcz.androidjugworkshop.data.DataRepository;
import pl.jug.bydgoszcz.androidjugworkshop.databinding.ActivityFeedBinding;
import pl.jug.bydgoszcz.androidjugworkshop.feed.list.FeedAdapter;
import pl.jug.bydgoszcz.androidjugworkshop.feed.list.OnAuthorClickListener;
import pl.jug.bydgoszcz.androidjugworkshop.feed.list.OnPostClickListener;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.PostModel;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.UserModel;

public class FeedActivity extends AppCompatActivity implements
        FeedView, OnAuthorClickListener, OnPostClickListener {

    private FeedPresenter feedPresenter;
    private ActivityFeedBinding binding;
    private FeedAdapter feedAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);

        setupFeedList();
        setupPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        feedPresenter.attachView(this);
        feedPresenter.loadPosts();
    }

    @Override
    protected void onStop() {
        feedPresenter.detachView();
        super.onStop();
    }

    @Override
    public void showPosts(List<PostModel> postModels) {
        feedAdapter.updatePosts(postModels);
        feedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDownloadPostsError() {
        Toast.makeText(this, "Can't download posts...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthorClick(UserModel userModel) {
        // startActivity(UserDetailsActivity.forUserId(this, userModel.getId()));
    }

    @Override
    public void onPostClick(PostModel postModel) {
        Toast.makeText(this, "Post clicked: " + postModel.getTitle(), Toast.LENGTH_SHORT).show();
    }

    private void setupFeedList() {
        feedAdapter = new FeedAdapter(this);
        feedAdapter.setOnAuthorClickListener(this);
        feedAdapter.setOnPostClickListener(this);

        final LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        binding.feedRecyclerView.setLayoutManager(linearLayoutManager);
        binding.feedRecyclerView.setAdapter(feedAdapter);
    }

    private void setupPresenter() {
        feedPresenter = new FeedPresenter(new DataRepository());
    }
}
