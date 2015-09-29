package com.normanhoeller.picturehub.api;

import com.normanhoeller.picturehub.model.SearchResult;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by norman on 31/08/15.
 */
public interface ShutterStockService {

    @GET("/images/search?per_page=30")
    Observable<SearchResult> getSearchResult(@Query("query") String query);
}
