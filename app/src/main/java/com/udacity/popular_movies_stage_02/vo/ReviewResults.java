package com.udacity.popular_movies_stage_02.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewResults {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("author")
    @Expose
    public String author;

    @SerializedName("content")
    @Expose
    public String content;

    @SerializedName("url")
    @Expose
    public String url;

    public String getId() {
        return id;
    }

    public ReviewResults setId(String id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ReviewResults setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ReviewResults setContent(String content) {
        this.content = content;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ReviewResults setUrl(String url) {
        this.url = url;
        return this;
    }
}