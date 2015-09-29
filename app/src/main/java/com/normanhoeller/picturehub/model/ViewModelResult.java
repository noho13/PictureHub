package com.normanhoeller.picturehub.model;

/**
 * Created by norman on 29/09/15.
 */
public class ViewModelResult {

    private String url;
    private String description;

    public ViewModelResult(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
