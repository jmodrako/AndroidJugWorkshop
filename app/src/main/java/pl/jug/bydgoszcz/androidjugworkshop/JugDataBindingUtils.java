package pl.jug.bydgoszcz.androidjugworkshop;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class JugDataBindingUtils {

    @BindingAdapter("imageUrl")
    public static void loadImageFromXml(ImageView imageView, String imageUrl) {
        if (imageView == null || imageUrl == null) return;
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    @BindingAdapter("hide")
    public static void hideView(View view, boolean shouldBeHidden) {
        if (view != null) {
            view.setVisibility(shouldBeHidden ? View.GONE : View.VISIBLE);
        }
    }
}
