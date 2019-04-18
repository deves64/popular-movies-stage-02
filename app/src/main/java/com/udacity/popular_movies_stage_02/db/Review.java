package com.udacity.popular_movies_stage_02.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Review {
    @PrimaryKey
    public Integer id;

    public String author;

    public String content;

    public String url;

    public Integer getId() {
        return id;
    }

    public Review setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Review setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Review setContent(String content) {
        this.content = content;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Review setUrl(String url) {
        this.url = url;
        return this;
    }
}
