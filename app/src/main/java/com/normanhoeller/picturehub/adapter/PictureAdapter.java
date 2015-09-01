package com.normanhoeller.picturehub.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.normanhoeller.picturehub.R;
import com.normanhoeller.picturehub.model.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by norman on 31/08/15.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private static final String TAG = PictureAdapter.class.getSimpleName();
    private List<SearchResult.Data> pictureDataList;

    public PictureAdapter(List<SearchResult.Data> dataList) {
        pictureDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_picture);
        return new ViewHolder(view, imageView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        SearchResult.Data item = pictureDataList.get(position);
        String url = item.getAssets().getPreview().getUrl();
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(viewHolder.imageView.getContext()).load(url).into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return pictureDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView, ImageView imageView) {
            super(itemView);
            this.imageView = imageView;
        }
    }
}
