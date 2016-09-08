package com.normanhoeller.picturehub.model;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by norman on 29/09/15.
 */
public class ViewModelResult {

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
//        Picasso.with(context).load(url).into(imageView);
    }
}
