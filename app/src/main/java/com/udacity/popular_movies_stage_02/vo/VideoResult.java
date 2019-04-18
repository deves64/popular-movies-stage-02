package com.udacity.popular_movies_stage_02.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoResult {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("iso_639_1")
    @Expose
    public String iso6391;

    @SerializedName("iso_3166_1")
    @Expose
    public String iso31661;

    @SerializedName("key")
    @Expose
    public String key;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("site")
    @Expose
    public String site;

    @SerializedName("size")
    @Expose
    public Integer size;

    @SerializedName("type")
    @Expose
    public String type;

    public String getId() {
        return id;
    }

    public VideoResult setId(String id) {
        this.id = id;
        return this;
    }

    public String getIso6391() {
        return iso6391;
    }

    public VideoResult setIso6391(String iso6391) {
        this.iso6391 = iso6391;
        return this;
    }

    public String getIso31661() {
        return iso31661;
    }

    public VideoResult setIso31661(String iso31661) {
        this.iso31661 = iso31661;
        return this;
    }

    public String getKey() {
        return key;
    }

    public VideoResult setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public VideoResult setName(String name) {
        this.name = name;
        return this;
    }

    public String getSite() {
        return site;
    }

    public VideoResult setSite(String site) {
        this.site = site;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public VideoResult setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getType() {
        return type;
    }

    public VideoResult setType(String type) {
        this.type = type;
        return this;
    }
}