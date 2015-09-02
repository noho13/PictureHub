package com.normanhoeller.picturehub;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.normanhoeller.picturehub.api.ShutterStockService;
import com.normanhoeller.picturehub.model.SearchResult;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by norman on 02/09/15.
 */
public class WorkerFragment extends Fragment {

    public static final String FRAG_TAG = "frag_tag";
    private static final String TAG = WorkerFragment.class.getSimpleName();
    private Callback callback;
    @Inject
    public ShutterStockService shutterstockService;

    public interface Callback {
        void setResult(SearchResult searchResult);
    }

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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SearchResult>() {
                    @Override
                    public void call(SearchResult searchResult) {
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
}
