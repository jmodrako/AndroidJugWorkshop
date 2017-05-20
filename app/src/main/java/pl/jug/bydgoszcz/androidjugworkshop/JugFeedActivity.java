package pl.jug.bydgoszcz.androidjugworkshop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.List;

import pl.jug.bydgoszcz.androidjugworkshop.databinding.ActivityJugFeedBinding;

public class JugFeedActivity extends AppCompatActivity implements JugFeedView {

    private JugFeedAdapter adapter;
    private JugFeedPresenter presetner;
    private ActivityJugFeedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_jug_feed);

        presetner = new JugFeedPresenter(new Connection());

        adapter = new JugFeedAdapter(this);
        final LinearLayoutManager lm = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);

        binding.feedRecyclerView.setAdapter(adapter);
        binding.feedRecyclerView.setLayoutManager(lm);

        binding.feedSwipeRefresh.setOnRefreshListener(() -> presetner.loadPosts());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presetner.attach(this);
        presetner.loadPosts();
    }

    @Override
    protected void onPause() {
        presetner.detach();
        super.onPause();
    }

    @Override
    public void showNewPosts(List<JugPostModel> models) {
        adapter.setData(models);
        adapter.notifyDataSetChanged();
        binding.feedSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onPostsDownloadFailed() {
        Toast.makeText(this, "Can't download posts!", Toast.LENGTH_SHORT).show();
    }
}
