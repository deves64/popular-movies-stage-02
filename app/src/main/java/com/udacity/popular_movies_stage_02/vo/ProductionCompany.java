package com.udacity.popular_movies_stage_02.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductionCompany {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("logo_path")
    @Expose
    public String logoPath;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("origin_country")
    @Expose
    public String originCountry;

    public Integer getId() {
        return id;
    }

    public ProductionCompany setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public ProductionCompany setLogoPath(String logoPath) {
        this.logoPath = logoPath;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductionCompany setName(String name) {
        this.name = name;
        return this;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public ProductionCompany setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
        return this;
    }
}
