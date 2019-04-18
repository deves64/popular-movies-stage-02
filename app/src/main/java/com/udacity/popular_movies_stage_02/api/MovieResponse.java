package com.udacity.popular_movies_stage_02.api;

import com.udacity.popular_movies_stage_02.db.MovieDb;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {

    public Integer page;

    public Integer totalResults;

    public Integer totalPages;

    public List<MovieDb> movieDbs = new ArrayList<>();

    public Integer getPage() {
        return page;
    }

    public MovieResponse setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public MovieResponse setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public MovieResponse setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public List<MovieDb> getMovieDbs() {
        return movieDbs;
    }

    public MovieResponse setMovieDbs(List<MovieDb> movieDbs) {
        this.movieDbs = movieDbs;
        return this;
    }
}