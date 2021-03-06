package com.normanhoeller.picturehub;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.normanhoeller.picturehub.api.ShutterStockService;
import com.normanhoeller.picturehub.model.SearchResult;
import com.normanhoeller.picturehub.model.ViewModelResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by norman on 02/09/15.
 */
public class WorkerFragment extends Fragment {

    public static final String FRAG_TAG = "frag_tag";
    private static final String TAG = WorkerFragment.class.getSimpleName();
    @Inject
    public ShutterStockService shutterstockService;
    private Callback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (Callback) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((PictureHubApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    public void queryShutterStockService(String query) {
        shutterstockService.getSearchResult(query)
                .map(new Func1<SearchResult, List<ViewModelResult>>() {
                    @Override
                    public List<ViewModelResult> call(SearchResult searchResult) {
                        List<ViewModelResult> results = new ArrayList<>();
                        for (SearchResult.Data result : searchResult.getData()) {
                            results.add(new ViewModelResult(result.getAssets().getPreview().getUrl(), result.getDescription()));
                        }
                        return results;
                    }
                })
                .subscribeOn(Schedulers.io()) // upwards runs on io
                .observeOn(AndroidSchedulers.mainThread()) // downwards runs on mainThread
                .subscribe(new Action1<List<ViewModelResult>>() {
                    @Override
                    public void call(List<ViewModelResult> searchResult) {
                        Log.d(TAG, "got searchresult: " + searchResult);
                        if (callback != null) {
                            callback.setResult(searchResult);
                        }
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public interface Callback {
        void setResult(List<ViewModelResult> searchResult);
    }
}
