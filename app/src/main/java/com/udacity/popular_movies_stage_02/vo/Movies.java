package com.udacity.popular_movies_stage_02.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movies {
    @SerializedName("page")
    @Expose
    public Integer page;

    @SerializedName("results")
    @Expose
    public List<MovieResult> results = null;

    @SerializedName("total_results")
    @Expose
    public Integer totalResults;

    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public Movies setPage(Integer page) {
        this.page = page;
        return this;
    }

    public List<MovieResult> getResults() {
        return results;
    }

    public Movies setResults(List<MovieResult> results) {
        this.results = results;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Movies setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Movies setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }
}