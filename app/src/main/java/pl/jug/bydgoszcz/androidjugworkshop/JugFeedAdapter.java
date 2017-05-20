package pl.jug.bydgoszcz.androidjugworkshop;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.jug.bydgoszcz.androidjugworkshop.databinding.ListItemFeedPostBinding;

public class JugFeedAdapter extends RecyclerView.Adapter<JugFeedAdapter.JugPostViewHolder> {

    private List<JugPostModel> data = new ArrayList<>();

    @Override
    public JugPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater li = LayoutInflater.from(parent.getContext());

        final ListItemFeedPostBinding binding = DataBindingUtil.inflate(
                li, R.layout.list_item_feed_post, parent, false);

        return new JugPostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(JugPostViewHolder holder, int position) {
        final JugPostModel model = data.get(position);
        holder.binding.setModel(model);

        final Context context = holder.binding.getRoot().getContext();

        holder.binding.listItemFeedAuthorSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.binding.listItemFeedContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, model.getBody(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<JugPostModel> data) {
        this.data.clear();
        this.data.addAll(data);
    }

    /* Holder for  R.layout.list_item_feed_post*/
    static class JugPostViewHolder extends RecyclerView.ViewHolder {

        private ListItemFeedPostBinding binding;

        JugPostViewHolder(ListItemFeedPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
