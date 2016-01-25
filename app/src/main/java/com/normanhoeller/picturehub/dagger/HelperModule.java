package com.normanhoeller.picturehub.dagger;

import com.normanhoeller.picturehub.api.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 12/01/16.
 */
@Module
public class HelperModule {

    @Provides
    @Singleton
    public RestClient provideRestClient(){
        return RestClient.getInstance();
    }
}
