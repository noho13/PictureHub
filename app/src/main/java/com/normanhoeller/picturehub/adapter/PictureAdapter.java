package com.normanhoeller.picturehub.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.normanhoeller.picturehub.DetailActivity;
import com.normanhoeller.picturehub.R;
import com.normanhoeller.picturehub.databinding.ItemPictureBinding;
import com.normanhoeller.picturehub.model.ViewModelResult;

import java.util.List;

/**
 * Created by norman on 31/08/15.
 */
public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = PictureAdapter.class.getSimpleName();
    private List<ViewModelResult> pictureDataList;

    public PictureAdapter(List<ViewModelResult> dataList) {
        pictureDataList = dataList;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ItemPictureBinding itemPictureBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_picture,
                parent,
                false);
        return new PictureViewHolder(itemPictureBinding);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewModelResult item = pictureDataList.get(position);
        ((PictureViewHolder) viewHolder).bind(item);
    }

    @Override
    public int getItemCount() {
        return pictureDataList.size();
    }


    static class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemPictureBinding itemPictureBinding;

        PictureViewHolder(ItemPictureBinding itemPictureBinding) {
            super(itemPictureBinding.cardView);
            this.itemPictureBinding = itemPictureBinding;
            itemPictureBinding.cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = itemView.getContext();
            itemPictureBinding.getResult().getUrl();
            String url = itemPictureBinding.getResult().getUrl();
            String text = itemPictureBinding.getResult().getDescription();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.URL_KEY, url);
            intent.putExtra(DetailActivity.DESCRIPTION_KEY, text);

            String transitionName = context.getString(R.string.transition_name);
            Pair<View, String> pair = new Pair<View, String>(itemPictureBinding.ivPicture,
                    transitionName);

            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((AppCompatActivity) context, pair);

            ActivityCompat.startActivity((AppCompatActivity) context, intent, activityOptionsCompat.toBundle());
        }

        void bind(ViewModelResult result) {
            itemPictureBinding.setResult(result);
        }
    }
}