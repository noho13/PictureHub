package com.normanhoeller.picturehub.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.normanhoeller.picturehub.PictureActivity;
import com.normanhoeller.picturehub.R;
import com.normanhoeller.picturehub.adapter.PictureAdapter;
import com.normanhoeller.picturehub.model.SearchResult;
import com.normanhoeller.picturehub.model.ViewModelResult;

import java.util.List;

/**
 * Created by norman on 31/08/15.
 */
public class PictureFragment extends Fragment {

    private static final String TAG = PictureFragment.class.getSimpleName();
    private static final String PROGRESS_BAR_VISIBILITY = "progress_bar_visibility";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView toolbarTitle;

    public static PictureFragment createInstance(String searchQuery) {
        PictureFragment fragment = new PictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PictureActivity.SEARCH_QUERY, searchQuery);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_picture, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_item_grid);
        progressBar = (ProgressBar) root.findViewById(R.id.pr_loading);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(android.R.color.holo_orange_light), android.graphics.PorterDuff.Mode.MULTIPLY);
        toolbarTitle = (TextView) root.findViewById(R.id.tv_toolbar_title);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (savedInstanceState != null) {
            int progressVisibility = savedInstanceState.getInt(PROGRESS_BAR_VISIBILITY);
            if (progressVisibility == View.VISIBLE) {
                recyclerView.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        String searchQuery = getArguments().getString(PictureActivity.SEARCH_QUERY);
        toolbarTitle.setText("looking for: " + searchQuery.toLowerCase());
        queryShutterStock(searchQuery);

    }

    private void queryShutterStock(String query) {
        ((PictureActivity) getActivity()).sendQuery(query);
    }

    private void showSnackBar(View view) {
        Snackbar.make(view, R.string.no_results, Snackbar.LENGTH_LONG)
                .setAction(R.string.hit_back, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().onBackPressed();
                    }
                })
                .show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PROGRESS_BAR_VISIBILITY, progressBar.getVisibility());
    }

    public void setResult(List<ViewModelResult> searchResult) {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(new PictureAdapter(searchResult));
        if (searchResult.size() == 0) {
            showSnackBar(recyclerView);
        }
    }
}
