package com.udacity.popular_movies_stage_02.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductionCountry {
    @SerializedName("iso_3166_1")
    @Expose
    public String iso31661;
    @SerializedName("name")
    @Expose
    public String name;

    public String getIso31661() {
        return iso31661;
    }

    public ProductionCountry setIso31661(String iso31661) {
        this.iso31661 = iso31661;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductionCountry setName(String name) {
        this.name = name;
        return this;
    }
}