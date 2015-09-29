package com.normanhoeller.picturehub.model;

/**
 * Created by norman on 29/09/15.
 */
public class ViewModelResult {

    private final String url;
    private final String description;

    public ViewModelResult(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
