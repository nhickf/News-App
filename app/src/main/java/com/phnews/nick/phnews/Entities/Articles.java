package com.phnews.nick.phnews.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "articles")
public class Articles {


    @ColumnInfo(name = "author")
    @SerializedName("author")
    @Expose
    private String author;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    private String title;
    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    private String description;
    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    private String url;
    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    public Articles(String author, @NonNull String title, String description, String url, String urlToImage, String publishedAt) {

        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() { return url; }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
