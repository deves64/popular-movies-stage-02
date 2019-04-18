package com.udacity.popular_movies_stage_02.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpokenLanguage {
    @SerializedName("iso_639_1")
    @Expose
    public String iso6391;
    @SerializedName("name")
    @Expose
    public String name;

    public String getIso6391() {
        return iso6391;
    }

    public SpokenLanguage setIso6391(String iso6391) {
        this.iso6391 = iso6391;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpokenLanguage setName(String name) {
        this.name = name;
        return this;
    }
}
