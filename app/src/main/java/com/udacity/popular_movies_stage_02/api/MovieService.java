package com.udacity.popular_movies_stage_02.api;

import com.udacity.popular_movies_stage_02.vo.Movie;
import com.udacity.popular_movies_stage_02.vo.Movies;
import com.udacity.popular_movies_stage_02.vo.Reviews;
import com.udacity.popular_movies_stage_02.vo.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/popular")
    Call<Movies> listMovieByPopularity(@Query("page") int page);

    @GET("movie/top_rated")
    Call<Movies> listMovieByRating(@Query("page") int page);

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/videos")
    Call<Videos> listVideos(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/reviews")
    Call<Reviews> listReviews(@Path("movie_id") int movieId, @Query("page") int page);
}