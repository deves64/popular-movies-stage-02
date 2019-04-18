package com.udacity.popular_movies_stage_02.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Video {
    @PrimaryKey
    public Integer id;


    public String iso6391;


    public String iso31661;


    public String key;


    public String name;


    public String site;


    public Integer size;


    public String type;

    public Integer getId() {
        return id;
    }

    public Video setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getIso6391() {
        return iso6391;
    }

    public Video setIso6391(String iso6391) {
        this.iso6391 = iso6391;
        return this;
    }

    public String getIso31661() {
        return iso31661;
    }

    public Video setIso31661(String iso31661) {
        this.iso31661 = iso31661;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Video setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public Video setName(String name) {
        this.name = name;
        return this;
    }

    public String getSite() {
        return site;
    }

    public Video setSite(String site) {
        this.site = site;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public Video setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getType() {
        return type;
    }

    public Video setType(String type) {
        this.type = type;
        return this;
    }
}
