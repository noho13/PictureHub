package com.normanhoeller.picturehub;

import com.normanhoeller.picturehub.api.ShutterStockService;
import com.normanhoeller.picturehub.model.SearchResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by norman on 01/09/15.
 */
@Module
public class MockApplicationModule {

    @Provides
    @Singleton
    public ShutterStockService provideRestClient() {
        return new ShutterStockService() {
            @Override
            public Observable<SearchResult> getSearchResult(@Query("query") String query) {
                SearchResult searchResult = new SearchResult();
                searchResult.setPage(2);
                SearchResult.Data data = new SearchResult.Data();
                SearchResult.Data.Assets assets = new SearchResult.Data.Assets();
                SearchResult.Data.Assets.Preview preview = new SearchResult.Data.Assets.Preview();
                preview.setUrl("");
                assets.setPreview(preview);
                data.setAssets(assets);
                List<SearchResult.Data> dataList = new ArrayList<>();
                dataList.add(data);
                searchResult.setData(dataList);
                return Observable.just(searchResult);
            }
        };
    }
}
