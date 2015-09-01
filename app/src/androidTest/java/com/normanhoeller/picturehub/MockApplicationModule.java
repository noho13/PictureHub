package com.normanhoeller.picturehub;

import com.normanhoeller.picturehub.api.RestClient;


import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 01/09/15.
 */
@Module
public class MockApplicationModule {

    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return Mockito.mock(RestClient.getInstance().getClass());
    }
}
