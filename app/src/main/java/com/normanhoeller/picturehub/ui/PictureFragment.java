package com.normanhoeller.picturehub.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.normanhoeller.picturehub.PictureActivity;
import com.normanhoeller.picturehub.PictureHubApplication;
import com.normanhoeller.picturehub.R;
import com.normanhoeller.picturehub.adapter.PictureAdapter;
import com.normanhoeller.picturehub.api.RestClient;
import com.normanhoeller.picturehub.model.SearchResult;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by norman on 31/08/15.
 */
public class PictureFragment extends Fragment {

    private static final String TAG = PictureFragment.class.getSimpleName();
    @Inject
    public RestClient restClient;
    private RecyclerView recyclerView;

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
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerGridView);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((PictureHubApplication) getActivity().getApplication()).getComponent().inject(this);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        String searchQuery = getArguments().getString(PictureActivity.SEARCH_QUERY);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setTitle("looking for: " + searchQuery.toLowerCase());
        queryShutterStock(searchQuery);

    }

    private void queryShutterStock(String query) {
        restClient.getService().getSearchResult(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SearchResult>() {
                    @Override
                    public void call(SearchResult searchResult) {
                        Log.d(TAG, "got searchresult: " + searchResult);
                        recyclerView.setAdapter(new PictureAdapter(searchResult.getData()));
                    }
                });
    }
}
