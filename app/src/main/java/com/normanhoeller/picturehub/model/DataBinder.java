package com.normanhoeller.picturehub.model;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by norman on 29/09/15.
 */
public class DataBinder {

    private DataBinder() {
        //NO-OP
    }

    @BindingAdapter("bind:imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        Context context = imageView.getContext();
        Picasso.with(context).load(url).into(imageView);
    }
}
