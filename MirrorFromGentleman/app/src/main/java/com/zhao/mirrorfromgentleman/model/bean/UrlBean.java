package com.zhao.mirrorfromgentleman.model.bean;

/**
 * Created by dllo on 16/6/24.
 */
public class UrlBean {
    int id;
    String url;

    public UrlBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UrlBean(String url, int id) {

        this.url = url;
        this.id = id;
    }

    public UrlBean(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
