package com.udacity.popular_movies_stage_02.ui;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.udacity.popular_movies_stage_02.MainActivity;
import com.udacity.popular_movies_stage_02.api.MovieResponse;
import com.udacity.popular_movies_stage_02.db.MovieDb;
import com.udacity.popular_movies_stage_02.db.MovieFavoriteDao;
import com.udacity.popular_movies_stage_02.repository.MoviePopularRepository;
import com.udacity.popular_movies_stage_02.repository.MovieRatedRepository;
import com.udacity.popular_movies_stage_02.util.Resource;
import com.udacity.popular_movies_stage_02.viewmodel.MainViewModel;

import java.util.List;

public class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private final MovieRatedRepository mMovieRatedRepository;
    private final MoviePopularRepository mMoviePopularRepository;
    private final MovieFavoriteDao mMovieFavoriteDao;
    private final MainActivity mMainActivity;
    private final MainViewModel mMainViewModel;

    private Boolean mLoadingStatus = Boolean.FALSE;
    private String mLoadIdentifierStatus = MainActivity.LOAD_MOVIES_BY_POPULARITY;

    private final static int ITEMS_PER_PAGE = 20;

    private final static int REMAINING_ITEMS = 6;

    public PaginationScrollListener(MovieRatedRepository movieRatedRepository,
                                    MoviePopularRepository moviePopularRepository,
                                    MovieFavoriteDao movieFavoriteDao,
                                    MainViewModel mainViewModel,
                                    MainActivity mainActivity) {

        mMovieRatedRepository = movieRatedRepository;
        mMoviePopularRepository = moviePopularRepository;
        mMovieFavoriteDao = movieFavoriteDao;
        mMainViewModel = mainViewModel;
        mMainActivity = mainActivity;

        startMetadataObserver();
        startDatabaseObserver();
        startLoadIdentifierObserver();
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(!(recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager)) {
            return;
        }

        StaggeredGridLayoutManager layoutManager =
                (StaggeredGridLayoutManager) recyclerView.getLayoutManager();

        int[] lastVisiblePosition = new int[2];



        MovieAdapter movieAdapter = (MovieAdapter) recyclerView.getAdapter();

        if (movieAdapter == null || isLoading()) {
            return;
        }

        int itemCount = movieAdapter.getItemCount();

        Log.v("itemCount", Integer.toString(itemCount));

        if ((lastVisiblePosition[0] + 1) == (itemCount - REMAINING_ITEMS)
                || (lastVisiblePosition[1] + 1) == (itemCount - REMAINING_ITEMS)) {
            Log.v("isLoading", Boolean.toString(isLoading()));


            loadMovies(((itemCount / ITEMS_PER_PAGE) + 1));

        }
    }

    private void startMetadataObserver() {
        mMovieRatedRepository.getMetaData().observe(mMainActivity, new Observer<Resource<MovieResponse>>() {
            @Override
            public void onChanged(@Nullable Resource<MovieResponse> movieMetadataResource) {
                if (movieMetadataResource == null) {
                    return;
                }

                if (movieMetadataResource.status == Resource.Status.LOADING) {
                    setLoadingStatus(Boolean.TRUE);
                }
                else {
                    setLoadingStatus(Boolean.FALSE);
                }
            }
        });

        Log.v("OBJECT3",Integer.toString(mMoviePopularRepository.hashCode()));


        mMoviePopularRepository.getMetaData().observe(mMainActivity, new Observer<Resource<MovieResponse>>() {
            @Override
            public void onChanged(@Nullable Resource<MovieResponse> movieResponseResource) {
                if (movieResponseResource == null) {
                    return;
                }
                Log.v("moviesPopular02", "TRUE");
                if (movieResponseResource.status == Resource.Status.LOADING) {
                    setLoadingStatus(Boolean.TRUE);
                }
                else {
                    setLoadingStatus(Boolean.FALSE);
                }
            }
        });
    }

    private void startDatabaseObserver() {
        mMainViewModel.getMovies().observe(mMainActivity, new Observer<List<MovieDb>>() {
            @Override
            public void onChanged(@Nullable List<MovieDb> movieDbs) {
                if (movieDbs == null) {
                    return;
                }

                if (movieDbs.size() == 0) {
                    loadMovies(1);
                }
            }
        });
    }

    private void startLoadIdentifierObserver() {
        mMainViewModel.getLoadIdentifierData().observe(mMainActivity, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String status) {
                if (status == null) {
                    return;
                }

                setLoadIdentifierStatus(status);
            }
        });
    }

    private Boolean isLoading() {
       return mLoadingStatus;
    }

    private void setLoadingStatus(Boolean loading) {
        mLoadingStatus = loading;
    }

    private void setLoadIdentifierStatus(String status) {
        Log.v("setLoadIdentifierStatus", status);
        mLoadIdentifierStatus = status;
    }

    private void loadMovies(int page) {
        Log.v("loadMovies", Integer.toString(page));
        switch (mLoadIdentifierStatus) {
            case MainActivity.LOAD_MOVIES_BY_RATING:
                mMovieRatedRepository.getMovies(page);
                Log.v("loadMovies", "LOAD_MOVIES_BY_RATING");
                break;
            case MainActivity.LOAD_MOVIES_BY_POPULARITY:
                mMoviePopularRepository.getMovies(page);
                Log.v("loadMovies", "LOAD_MOVIES_BY_POPULARITY");
                break;
            case MainActivity.LOAD_FAVORITES:
                mMovieFavoriteDao.getAll();
                Log.v("LOADING DATABASE", "LOADING...");
                Log.v("loadMovies", "LOAD_FAVORITES");
                break;
        }
    }
}