package pl.jug.bydgoszcz.androidjugworkshop;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import pl.jug.bydgoszcz.androidjugworkshop.databinding.ActivityJugFeedBinding;

public class JugFeedActivity extends AppCompatActivity {

    private ActivityJugFeedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_jug_feed);

        final JugFeedAdapter adapter = new JugFeedAdapter();
        final LinearLayoutManager lm = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);

        binding.feedRecyclerView.setAdapter(adapter);
        binding.feedRecyclerView.setLayoutManager(lm);
    }
}
