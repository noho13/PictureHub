package com.normanhoeller.picturehub.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.normanhoeller.picturehub.R;
import com.normanhoeller.picturehub.model.ViewModelResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by norman on 31/08/15.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private static final String TAG = PictureAdapter.class.getSimpleName();
    private List<ViewModelResult> pictureDataList;

    public PictureAdapter(List<ViewModelResult> dataList) {
        pictureDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ViewModelResult item = pictureDataList.get(position);
        String url = item.getUrl();
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(viewHolder.imageView.getContext()).load(url).into(viewHolder.imageView);
        }

        if (!TextUtils.isEmpty(item.getDescription())) {
            viewHolder.description.setText(item.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return pictureDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView description;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.iv_picture);
            this.description = (TextView) itemView.findViewById(R.id.tv_line1);
        }
    }
}
