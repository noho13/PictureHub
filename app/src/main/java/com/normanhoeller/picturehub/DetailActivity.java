package com.normanhoeller.picturehub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by norman on 14/02/16.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String URL_KEY = "url_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            DetailFragment fragment = DetailFragment.createInstance(getIntent().getStringExtra(URL_KEY));
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }

    public static class DetailFragment extends Fragment {

        public static DetailFragment createInstance(String url) {
            Bundle bundle = new Bundle();
            bundle.putString(URL_KEY, url);
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_detail, container, false);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            String url = getArguments().getString(URL_KEY);
            ImageView imageView = (ImageView) getView().findViewById(R.id.image_view);
            Picasso.with(getActivity()).load(url).into(imageView);
        }
    }

}
