package com.normanhoeller.picturehub.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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


    static class PictureViewHolder extends RecyclerView.ViewHolder {
        ItemPictureBinding itemPictureBinding;

        PictureViewHolder(ItemPictureBinding itemPictureBinding) {
            super(itemPictureBinding.cardView);
            this.itemPictureBinding = itemPictureBinding;
        }

        void bind(ViewModelResult result) {
            itemPictureBinding.setResult(result);
            itemPictureBinding.executePendingBindings();
        }
    }
}