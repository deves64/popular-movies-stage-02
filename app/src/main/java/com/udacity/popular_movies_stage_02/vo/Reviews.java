package com.udacity.popular_movies_stage_02.vo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reviews {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("page")
    @Expose
    public Integer page;

    @SerializedName("results")
    @Expose
    public List<ReviewResults> results = null;

    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;

    @SerializedName("total_results")
    @Expose
    public Integer totalResults;


    public Integer getId() {
        return id;
    }

    public Reviews setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public Reviews setPage(Integer page) {
        this.page = page;
        return this;
    }

    public List<ReviewResults> getResults() {
        return results;
    }

    public Reviews setResults(List<ReviewResults> results) {
        this.results = results;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Reviews setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Reviews setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }
}