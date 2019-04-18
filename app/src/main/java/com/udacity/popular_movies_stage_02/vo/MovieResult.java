package com.udacity.popular_movies_stage_02.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResult {
    @SerializedName("poster_path")
    @Expose
    public String posterPath;

    @SerializedName("adult")
    @Expose
    public Boolean adult;

    @SerializedName("overview")
    @Expose
    public String overview;

    @SerializedName("release_date")
    @Expose
    public String releaseDate;

    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = null;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("original_title")
    @Expose
    public String originalTitle;

    @SerializedName("original_language")
    @Expose
    public String originalLanguage;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;

    @SerializedName("popularity")
    @Expose
    public Double popularity;

    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;

    @SerializedName("video")
    @Expose
    public Boolean video;

    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public MovieResult setPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public Boolean getAdult() {
        return adult;
    }

    public MovieResult setAdult(Boolean adult) {
        this.adult = adult;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public MovieResult setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public MovieResult setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public MovieResult setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public MovieResult setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public MovieResult setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public MovieResult setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MovieResult setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public MovieResult setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    public Double getPopularity() {
        return popularity;
    }

    public MovieResult setPopularity(Double popularity) {
        this.popularity = popularity;
        return this;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public MovieResult setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public Boolean getVideo() {
        return video;
    }

    public MovieResult setVideo(Boolean video) {
        this.video = video;
        return this;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public MovieResult setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }
}