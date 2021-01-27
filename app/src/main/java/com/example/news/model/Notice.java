package com.example.news.model;

import android.widget.ImageView;

import com.example.news.constants.EnumCategory;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class Notice implements Serializable {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private ImageView image;
    EnumCategory enumCategory;

    public EnumCategory getEnumCategory() {
        return enumCategory;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setEnumCategory(EnumCategory enumCategory) {
        this.enumCategory = enumCategory;
    }


}
