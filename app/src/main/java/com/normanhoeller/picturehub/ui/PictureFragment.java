package com.normanhoeller.picturehub.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.normanhoeller.picturehub.PictureActivity;

/**
 * Created by norman on 31/08/15.
 */
public class PictureFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static PictureFragment createInstance(String searchQuery) {
        PictureFragment fragment = new PictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PictureActivity.SEARCH_QUERY, searchQuery);
        fragment.setArguments(bundle);
        return fragment;
    }
}
