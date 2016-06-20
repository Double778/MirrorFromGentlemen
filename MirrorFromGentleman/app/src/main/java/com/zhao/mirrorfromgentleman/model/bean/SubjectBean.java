package com.zhao.mirrorfromgentleman.model.bean;

/**
 * Created by 华哥哥 on 16/6/17.
 */
public class SubjectBean {
    private String title;
    private String name;
    private String content;
    private String url;

    public SubjectBean() {
    }

    public SubjectBean(String title, String name, String content, String url) {
        this.title = title;
        this.name = name;
        this.content = content;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
