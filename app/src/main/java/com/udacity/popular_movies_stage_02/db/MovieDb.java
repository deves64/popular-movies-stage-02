package com.udacity.popular_movies_stage_02.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MovieDb {
    @PrimaryKey
    public Integer id;

    @ColumnInfo(name = "poster_path")
    public String posterPath;

    public Boolean adult;

    public String overview;
    @ColumnInfo(name = "release_date")
    public String releaseDate;
    @ColumnInfo(name = "original_title")
    public String originalTitle;
    @ColumnInfo(name = "original_language")
    public String originalLanguage;

    public String title;
    @ColumnInfo(name = "backdrop_path")
    public String backdropPath;

    public Double popularity;
    @ColumnInfo(name = "vote_count")
    public Integer voteCount;

    public Boolean video;
    @ColumnInfo(name = "vote_average")
    public Double voteAverage;

    public Integer getId() {
        return id;
    }

    public MovieDb setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public MovieDb setPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public Boolean getAdult() {
        return adult;
    }

    public MovieDb setAdult(Boolean adult) {
        this.adult = adult;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public MovieDb setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public MovieDb setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public MovieDb setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public MovieDb setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MovieDb setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public MovieDb setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    public Double getPopularity() {
        return popularity;
    }

    public MovieDb setPopularity(Double popularity) {
        this.popularity = popularity;
        return this;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public MovieDb setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public Boolean getVideo() {
        return video;
    }

    public MovieDb setVideo(Boolean video) {
        this.video = video;
        return this;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public MovieDb setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }
}