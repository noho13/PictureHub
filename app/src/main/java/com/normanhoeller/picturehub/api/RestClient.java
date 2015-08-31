package com.normanhoeller.picturehub.api;

import android.util.Base64;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by norman on 31/08/15.
 */
public class RestClient {

    private static final String BASE_URL = "https://api.shutterstock.com/v2";
    private static RestClient restClient;
    private static ShutterStockService service;

    private RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        String authInfo = "944caf99dfc4aa6186b0:d459648217e840dc9ffc77aea5a001cd4a972e4b";
                        String auth = "Basic " + Base64.encodeToString(authInfo.getBytes(), Base64.NO_WRAP);
                        request.addHeader("Authorization", auth);
                    }
                })
                .build();
        service = restAdapter.create(ShutterStockService.class);
    }

    public static ShutterStockService getService() {
        return service;
    }
}
