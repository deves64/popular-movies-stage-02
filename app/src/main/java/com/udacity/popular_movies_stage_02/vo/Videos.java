package com.udacity.popular_movies_stage_02.vo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videos {
    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("results")
    @Expose
    public List<VideoResult> results = null;

    public Integer getId() {
        return id;
    }

    public Videos setId(Integer id) {
        this.id = id;
        return this;
    }

    public List<VideoResult> getResults() {
        return results;
    }

    public Videos setResults(List<VideoResult> results) {
        this.results = results;
        return this;
    }
}