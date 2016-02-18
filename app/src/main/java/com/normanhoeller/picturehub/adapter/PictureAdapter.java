package com.normanhoeller.picturehub.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.normanhoeller.picturehub.DetailActivity;
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
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = holder.itemView.getContext();

                String url = getItem(holder.getAdapterPosition()).getUrl();
                String text = getItem(holder.getAdapterPosition()).getDescription();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.URL_KEY, url);
                intent.putExtra(DetailActivity.DESCRIPTION_KEY, text);

                String transitionName = context.getString(R.string.transition_name);
                Pair<View, String> pair = new Pair<View, String>(holder.imageView,
                        transitionName);

                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((AppCompatActivity) context, pair);

                ActivityCompat.startActivity((AppCompatActivity) context, intent, activityOptionsCompat.toBundle());
//                holder.itemView.getContext().startActivity(intent);
            }
        });
        return holder;
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

    public ViewModelResult getItem(int position) {
        return pictureDataList.get(position);
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
