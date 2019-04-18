package com.udacity.popular_movies_stage_02.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.util.Log;

import com.udacity.popular_movies_stage_02.MainActivity;
import com.udacity.popular_movies_stage_02.api.MovieResponse;
import com.udacity.popular_movies_stage_02.db.MovieDb;
import com.udacity.popular_movies_stage_02.db.MovieFavoriteDao;
import com.udacity.popular_movies_stage_02.repository.MoviePopularRepository;
import com.udacity.popular_movies_stage_02.repository.MovieRatedRepository;
import com.udacity.popular_movies_stage_02.util.Resource;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final MediatorLiveData<List<MovieDb>> mMovies;
    private final MutableLiveData<String> mLoadIdentifierData;

    private List<MovieDb> mListMoviesRating = new ArrayList<>();
    private List<MovieDb> mListMoviesPopularity = new ArrayList<>();
    private List<MovieDb> mListMoviesFavorites = new ArrayList<>();

    private final MovieRatedRepository mMovieRatedRepository;
    private final MoviePopularRepository mMoviePopularRepository;

    private String mLoadIdentifier = MainActivity.LOAD_MOVIES_BY_RATING;

    public MainViewModel(MovieRatedRepository movieRatedRepository,
                         MoviePopularRepository moviePopularRepository,
                         MovieFavoriteDao movieFavoriteDao) {

        mMovieRatedRepository = movieRatedRepository;
        mMoviePopularRepository = moviePopularRepository;

        MutableLiveData<Resource<MovieResponse>> moviesRating
                = movieRatedRepository.getMovies(1);

        MutableLiveData<Resource<MovieResponse>> moviesPopular
                = moviePopularRepository.getMovies(1);

        LiveData<List<MovieDb>> moviesFavorites = movieFavoriteDao.getAll();

        mLoadIdentifierData = new MutableLiveData<>();

        mMovies = new MediatorLiveData<>();
        mMovies.addSource(moviesRating, new Observer<Resource<MovieResponse>>() {
            @Override
            public void onChanged(@Nullable Resource<MovieResponse> movieResponseResource) {
                if (movieResponseResource == null ||
                        movieResponseResource.status == Resource.Status.LOADING) {
                    return;
                }

                if (movieResponseResource.data == null) {
                    return;
                }

                List<MovieDb> movieDbs = movieResponseResource.data.getMovieDbs();
                Log.v("moviesRating", Integer.toString(movieDbs.size()));
                mListMoviesRating.addAll(movieDbs);

                if (mLoadIdentifier == MainActivity.LOAD_MOVIES_BY_RATING) {
                    mMovies.postValue(mListMoviesRating);
                }
            }
        });

        mMovies.addSource(moviesPopular, new Observer<Resource<MovieResponse>>() {
            @Override
            public void onChanged(@Nullable Resource<MovieResponse> movieResponseResource) {
                if (movieResponseResource == null ||
                        movieResponseResource.status == Resource.Status.LOADING) {
                    Log.v("moviesPopular", "FALSE");
                    return;
                }

                if (movieResponseResource.data == null) {
                    return;
                }

                List<MovieDb> movieDbs = movieResponseResource.data.getMovieDbs();
                Log.v("moviesPopular01", Integer.toString(mListMoviesPopularity.size()));
                mListMoviesPopularity.addAll(movieDbs);
                Log.v("LOADING DATABASE", "LOADING...");

                if (mLoadIdentifier == MainActivity.LOAD_MOVIES_BY_POPULARITY) {
                    mMovies.postValue(mListMoviesPopularity);
                }

            }
        });

        mMovies.addSource(moviesFavorites, new Observer<List<MovieDb>>() {
            @Override
            public void onChanged(@Nullable List<MovieDb> movieDbs) {
                Log.v("moviesFavorites", Integer.toString(movieDbs.size()));
                mListMoviesFavorites = movieDbs;

                if (mLoadIdentifier == MainActivity.LOAD_FAVORITES) {
                    mMovies.postValue(movieDbs);
                }
            }
        });

        mMovies.addSource(mLoadIdentifierData, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String loadIdentifier) {
                if (loadIdentifier == null) {
                    return;
                }
                Log.v("mLoadIdentifierData", "TRUE");
                switch (loadIdentifier) {
                    case MainActivity.LOAD_MOVIES_BY_RATING:
                        Log.v("mLoadIdentifierData", Integer.toString(mListMoviesRating.size()));
                        mLoadIdentifier = MainActivity.LOAD_MOVIES_BY_RATING;
                        mMovies.postValue(mListMoviesRating);
                        Log.v("mLoadIdentifierData", MainActivity.LOAD_MOVIES_BY_RATING);
                        break;
                    case MainActivity.LOAD_MOVIES_BY_POPULARITY:
                        Log.v("mLoadIdentifierData", Integer.toString(mListMoviesRating.size()));
                        mLoadIdentifier = MainActivity.LOAD_MOVIES_BY_POPULARITY;
                        mMovies.postValue(mListMoviesPopularity);
                        Log.v("mLoadIdentifierData", MainActivity.LOAD_MOVIES_BY_POPULARITY);
                        break;
                    case MainActivity.LOAD_FAVORITES:
                        Log.v("mLoadIdentifierData", Integer.toString(mListMoviesRating.size()));
                        mLoadIdentifier = MainActivity.LOAD_FAVORITES;
                        mMovies.postValue(mListMoviesFavorites);
                        Log.v("mLoadIdentifierData", MainActivity.LOAD_FAVORITES);
                        break;
                }
            }
        });
    }

    public MediatorLiveData<List<MovieDb>> getMovies() {
        return mMovies;
    }

    public MutableLiveData<String> getLoadIdentifierData() {
        return mLoadIdentifierData;
    }

    public MovieRatedRepository getmMovieRatedRepository() {
        return mMovieRatedRepository;
    }

    public MoviePopularRepository getmMoviePopularRepository() {
        return mMoviePopularRepository;
    }
}