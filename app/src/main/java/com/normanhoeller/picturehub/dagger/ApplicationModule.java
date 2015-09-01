package com.normanhoeller.picturehub.dagger;

import com.normanhoeller.picturehub.api.RestClient;
import com.normanhoeller.picturehub.api.ShutterStockService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 31/08/15.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public ShutterStockService provideRestClient() {
        return RestClient.getInstance().getService();
    }
}
