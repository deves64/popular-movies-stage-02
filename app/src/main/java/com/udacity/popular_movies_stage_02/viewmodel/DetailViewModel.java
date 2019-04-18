package com.udacity.popular_movies_stage_02.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.udacity.popular_movies_stage_02.db.MovieDb;
import com.udacity.popular_movies_stage_02.db.MovieFavoriteDao;
import com.udacity.popular_movies_stage_02.repository.MovieRepository;
import com.udacity.popular_movies_stage_02.vo.Movie;
import com.udacity.popular_movies_stage_02.vo.ReviewResults;
import com.udacity.popular_movies_stage_02.vo.VideoResult;

import java.util.List;

public class DetailViewModel extends ViewModel {
    private final MutableLiveData<Movie> mMovieLiveData;
    private final MutableLiveData<List<VideoResult>> mVideosLiveData;
    private final MutableLiveData<List<ReviewResults>> mReviewsLiveData;
    private final LiveData<MovieDb> mMovieDbLiveData;

    public DetailViewModel(MovieRepository movieRepository, MovieFavoriteDao movieFavoriteDao, int id) {
        mMovieLiveData = movieRepository.getMovie(id);
        mVideosLiveData = movieRepository.getVideos(id);
        mReviewsLiveData = movieRepository.getReviews(id, 1);
        mMovieDbLiveData = movieFavoriteDao.getMovieById(id);

    }

    public LiveData<Movie> getMovie() {
        return  mMovieLiveData;
    }

    public LiveData<MovieDb> getMovieDb() {
        return mMovieDbLiveData;
    }

    public LiveData<List<VideoResult>> getVideos() {
        return  mVideosLiveData;
    }

    public LiveData<List<ReviewResults>> getReviews() {
        return mReviewsLiveData;
    }
}
