package com.udacity.popular_movies_stage_02.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.udacity.popular_movies_stage_02.db.MovieFavoriteDao;
import com.udacity.popular_movies_stage_02.repository.MoviePopularRepository;
import com.udacity.popular_movies_stage_02.repository.MovieRatedRepository;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final MovieRatedRepository mMovieRatedRepository;
    private final MoviePopularRepository mMoviePopularRepository;
    private final MovieFavoriteDao mMovieFavoriteDao;

    public MainViewModelFactory(MovieRatedRepository movieRatedRepository,
                                MoviePopularRepository moviePopularRepository,
                                MovieFavoriteDao movieFavoriteDao) {

        mMovieRatedRepository = movieRatedRepository;
        mMoviePopularRepository = moviePopularRepository;
        mMovieFavoriteDao = movieFavoriteDao;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(
                mMovieRatedRepository,
                mMoviePopularRepository,
                mMovieFavoriteDao
        );
    }
}