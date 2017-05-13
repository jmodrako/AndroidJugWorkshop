package pl.jug.bydgoszcz.androidjugworkshop.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DataBindingUtils {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        if (imageView == null) return;
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}
