package com.normanhoeller.picturehub.model;

import java.util.List;

/**
 * Created by norman on 31/08/15.
 */
public class SearchResult {

    private int page;
    private int per_page;
    private int total_count;

    private List<Data> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String id;
        private Assets assets;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Assets getAssets() {
            return assets;
        }

        public void setAssets(Assets assets) {
            this.assets = assets;
        }

        public static class Assets {
            private Preview preview;

            public Preview getPreview() {
                return preview;
            }

            public void setPreview(Preview preview) {
                this.preview = preview;
            }

            public static class Preview {
                private int height;
                private int width;
                private String url;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
