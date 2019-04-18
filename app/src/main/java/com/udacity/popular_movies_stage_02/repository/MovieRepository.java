package com.udacity.popular_movies_stage_02.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.udacity.popular_movies_stage_02.api.MovieService;
import com.udacity.popular_movies_stage_02.util.AppExecutors;
import com.udacity.popular_movies_stage_02.vo.Movie;
import com.udacity.popular_movies_stage_02.vo.ReviewResults;
import com.udacity.popular_movies_stage_02.vo.Reviews;
import com.udacity.popular_movies_stage_02.vo.VideoResult;
import com.udacity.popular_movies_stage_02.vo.Videos;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import javax.inject.Inject;

public class MovieRepository {
    private final MovieService mMovieService;
    private final AppExecutors mAppExecutors;

    @Inject
    public MovieRepository(MovieService movieService, AppExecutors appExecutors) {
        mMovieService = movieService;
        mAppExecutors = appExecutors;
    }

    public MutableLiveData<Movie> getMovie(final int id) {
        final MutableLiveData<Movie> movieMutableLiveData = new MutableLiveData<>();
        Log.v("LOADING NETWORK", "LOADING...");
        mAppExecutors.getmNetworkIo().execute(new Runnable() {
            @Override
            public void run() {
                Boolean internet = isInternetConnectionAvailable();

                if (!internet) {
                    movieMutableLiveData.postValue(null);
                    return;
                }

                try {
                    Movie movie = mMovieService.getMovie(id).execute().body();

                    if (movie == null) {
                        return;
                    }

                    movieMutableLiveData.postValue(movie);


                } catch (Exception e) {

                }
            }
        });

        return movieMutableLiveData;
    }

    public MutableLiveData<List<VideoResult>> getVideos(final int id) {
        final MutableLiveData<List<VideoResult>> videoMutableLiveData = new MutableLiveData<>();
        Log.v("LOADING NETWORK", "LOADING...");
        mAppExecutors.getmNetworkIo().execute(new Runnable() {
            @Override
            public void run() {
                Boolean internet = isInternetConnectionAvailable();

                if (!internet) {
                    videoMutableLiveData.postValue(null);
                    return;
                }

                try {
                    Videos videos = mMovieService.listVideos(id).execute().body();

                    if (videos == null || videos.getResults() == null) {
                        return;
                    }

                    videoMutableLiveData.postValue(videos.getResults());


                } catch (Exception e) {

                }
            }
        });

        return videoMutableLiveData;
    }

    public MutableLiveData<List<ReviewResults>> getReviews(final int id, final int page) {
        final MutableLiveData<List<ReviewResults>> reviewMutableLiveData = new MutableLiveData<>();
        Log.v("LOADING NETWORK", "LOADING...");
        mAppExecutors.getmNetworkIo().execute(new Runnable() {
            @Override
            public void run() {
                Boolean internet = isInternetConnectionAvailable();

                if (!internet) {
                    reviewMutableLiveData.postValue(null);
                    return;
                }

                try {
                    Reviews reviews = mMovieService.listReviews(id, page).execute().body();

                    if (reviews == null || reviews.getResults() == null) {
                        return;
                    }

                    reviewMutableLiveData.postValue(reviews.getResults());


                } catch (Exception e) {

                }
            }
        });

        return reviewMutableLiveData;
    }

    private Boolean isInternetConnectionAvailable() {
        try {
            Socket sock = new Socket();
            sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}