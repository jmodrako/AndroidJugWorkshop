package pl.jug.bydgoszcz.androidjugworkshop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import pl.jug.bydgoszcz.androidjugworkshop.databinding.ActivityJugFeedBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JugFeedActivity extends AppCompatActivity {

    private JugFeedAdapter adapter = new JugFeedAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityJugFeedBinding binding = DataBindingUtil.
                setContentView(this, R.layout.activity_jug_feed);

        final LinearLayoutManager lm = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);

        binding.feedRecyclerView.setAdapter(adapter);
        binding.feedRecyclerView.setLayoutManager(lm);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Connection connection = new Connection();
        final Api api = connection.getApi();

        api.posts().enqueue(new Callback<List<JugPostModel>>() {
            @Override
            public void onResponse(
                    Call<List<JugPostModel>> call,
                    Response<List<JugPostModel>> response) {

                final List<JugPostModel> models = response.body();
                adapter.setData(models);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<JugPostModel>> call, Throwable t) {
            }
        });
    }
}
