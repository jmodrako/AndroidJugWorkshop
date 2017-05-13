package pl.jug.bydgoszcz.androidjugworkshop.feed.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.jug.bydgoszcz.androidjugworkshop.R;
import pl.jug.bydgoszcz.androidjugworkshop.databinding.ListItemFeedPostBinding;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.PostModel;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.PostViewHolder> {

    private final Context context;

    private List<PostModel> postModelList;
    private OnAuthorClickListener onAuthorClickListener;
    private OnPostClickListener onPostClickListener;

    public FeedAdapter(Context context) {
        this.context = context;
        this.postModelList = new ArrayList<>();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ListItemFeedPostBinding viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.list_item_feed_post, parent, false);
        return new PostViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        final PostModel model = postModelList.get(position);
        final ListItemFeedPostBinding viewDataBinding = holder.viewDataBinding;

        viewDataBinding.setOnPostClickListener(onPostClickListener);
        viewDataBinding.setOnAuthorClickListener(onAuthorClickListener);
        viewDataBinding.setModel(model);

        viewDataBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public void updatePosts(List<PostModel> postModels) {
        postModelList.clear();
        postModelList.addAll(postModels);
    }

    public void setOnAuthorClickListener(OnAuthorClickListener onAuthorClickListener) {
        this.onAuthorClickListener = onAuthorClickListener;
    }

    public void setOnPostClickListener(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        private final ListItemFeedPostBinding viewDataBinding;

        PostViewHolder(ListItemFeedPostBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }
    }
}
