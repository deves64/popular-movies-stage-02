package com.udacity.popular_movies_stage_02.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.udacity.popular_movies_stage_02.db.MovieFavoriteDao;
import com.udacity.popular_movies_stage_02.repository.MovieRepository;

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final MovieRepository mMovieRepository;
    private final MovieFavoriteDao mMovieFavoriteDao;
    private final int mId;

    public DetailViewModelFactory(MovieRepository movieRepository, MovieFavoriteDao movieFavoriteDao, int id) {
        mMovieRepository = movieRepository;
        mMovieFavoriteDao = movieFavoriteDao;
        mId = id;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DetailViewModel(mMovieRepository,mMovieFavoriteDao, mId);
    }
}
