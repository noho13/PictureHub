package com.normanhoeller.picturehub.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.normanhoeller.picturehub.DetailActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by norman on 29/09/15.
 */
public class ViewModelResult {

    private static final String TAG = ViewModelResult.class.getSimpleName();

    private final String url;
    private final String description;

    public ViewModelResult(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    @BindingAdapter(value = {"android:src", "placeHolder"},
            requireAll = false)
    public static void setImageUrl(ImageView imageView, String url, int placeHolder) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Context context = imageView.getContext();
        RequestCreator requestCreator = Picasso.with(context).load(url);
        if (placeHolder != 0) {
            requestCreator.placeholder(placeHolder);
        }
        requestCreator.into(imageView);
    }

    public void onClick(View view) {
//        ImageView imageView = (ImageView) view.findViewById(R.id.iv_picture);
        Context context = view.getContext();

        startDetailActivity(context);

//        Intent intent = new Intent(context, DetailActivity.class);
//        intent.putExtra(DetailActivity.URL_KEY, url);
//        intent.putExtra(DetailActivity.DESCRIPTION_KEY, description);

//        String transitionName = context.getString(R.string.transition_name);
//        Pair<View, String> pair = new Pair<View, String>(imageView,
//                transitionName);
//
//        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((AppCompatActivity) context, pair);
//
//        ActivityCompat.startActivity((AppCompatActivity) context, intent, activityOptionsCompat.toBundle());

    }

    private void startDetailActivity(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.URL_KEY, url);
        intent.putExtra(DetailActivity.DESCRIPTION_KEY, description);

        context.startActivity(intent);
    }
}
