package com.normanhoeller.picturehub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by norman on 14/02/16.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String URL_KEY = "url_key";
    public static final String DESCRIPTION_KEY = "description_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            DetailFragment fragment = DetailFragment.createInstance(getIntent().getStringExtra(URL_KEY), getIntent().getStringExtra(DESCRIPTION_KEY));
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }

    public static class DetailFragment extends Fragment {

        public static DetailFragment createInstance(String url, String caption) {
            Bundle bundle = new Bundle();
            bundle.putString(URL_KEY, url);
            bundle.putString(DESCRIPTION_KEY, caption);
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

        @SuppressLint("NewApi")
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            String url = getArguments().getString(URL_KEY);
            ImageView imageView = (ImageView) getView().findViewById(R.id.image_view);
            Picasso.with(getActivity()).load(url).into(imageView);

            String caption = getArguments().getString(DESCRIPTION_KEY);
            TextView captionText = (TextView) getView().findViewById(R.id.image_caption);
            captionText.setText(caption);

//            Transition enterTransition = (Transition;
//            enterTransition.removeTarget(getView());
//            enterTransition.addTarget(captionText);
        }
    }

}
