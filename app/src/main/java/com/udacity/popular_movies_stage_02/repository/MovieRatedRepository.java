package com.udacity.popular_movies_stage_02.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.udacity.popular_movies_stage_02.db.MovieDb;
import com.udacity.popular_movies_stage_02.util.AppExecutors;
import com.udacity.popular_movies_stage_02.api.MovieResponse;
import com.udacity.popular_movies_stage_02.api.MovieService;
import com.udacity.popular_movies_stage_02.vo.MovieResult;
import com.udacity.popular_movies_stage_02.vo.Movies;
import com.udacity.popular_movies_stage_02.util.Resource;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieRatedRepository {
    private final MovieService mMovieService;
    private final AppExecutors mAppExecutors;
    private MutableLiveData<Resource<MovieResponse>> mMetadata;

    @Inject
    public MovieRatedRepository(MovieService movieService, AppExecutors appExecutors) {
        mMovieService = movieService;
        mAppExecutors = appExecutors;
    }

    public MutableLiveData<Resource<MovieResponse>> getMovies(final int page) {
        setLoadingMetaData();
        Log.v("LOADING NETWORK", "LOADING...");
        mAppExecutors.getmNetworkIo().execute(new Runnable() {
            @Override
            public void run() {
                Boolean internet = isInternetConnectionAvailable();

                if (!internet) {
                    mMetadata.postValue(null);
                    return;
                }

                try {
                    Movies originalMovies = mMovieService.listMovieByRating(page).execute().body();

                    if (originalMovies == null) {
                        return;
                    }

                    List<MovieDb> transformedMovieDbs = new ArrayList<>();

                    for (MovieResult movieResult : originalMovies.results) {
                        MovieDb movieDb = new MovieDb();
                        movieDb.setId(movieResult.getId());
                        movieDb.setAdult(movieResult.getAdult());
                        movieDb.setBackdropPath(movieResult.getBackdropPath());
                        movieDb.setOriginalLanguage(movieResult.getOriginalLanguage());
                        movieDb.setOriginalTitle(movieResult.getOriginalTitle());
                        movieDb.setOverview(movieResult.getOverview());
                        movieDb.setPopularity(movieResult.getPopularity());
                        movieDb.setPosterPath(movieResult.getPosterPath());
                        movieDb.setReleaseDate(movieResult.getReleaseDate());
                        movieDb.setTitle(movieResult.getTitle());
                        movieDb.setVideo(movieResult.getVideo());
                        movieDb.setVoteAverage(movieResult.getVoteAverage());
                        movieDb.setVoteCount(movieResult.getVoteCount());

                        transformedMovieDbs.add(movieDb);
                    }

                    setSuccessMetaData(originalMovies, transformedMovieDbs);
                } catch (Exception e) {

                }
            }
        });

        return getMetaData();
    }

    private void setSuccessMetaData(Movies originalMovies, List<MovieDb> transformedMovieDbs) {
        MovieResponse metadata = new MovieResponse()
                .setPage(originalMovies.getPage())
                .setTotalPages(originalMovies.getTotalPages())
                .setTotalResults(originalMovies.getTotalResults())
                .setMovieDbs(transformedMovieDbs);

        Resource<MovieResponse> resource = Resource.success(metadata);

        if(mMetadata == null) {
            mMetadata = new MutableLiveData<>();
        }

        mMetadata.postValue(resource);
    }

    private void setLoadingMetaData() {
        Resource<MovieResponse> resource = Resource.loading(null);

        if(mMetadata == null) {
            mMetadata = new MutableLiveData<>();
        }

        mMetadata.postValue(resource);
    }

    public MutableLiveData<Resource<MovieResponse>> getMetaData() {
        if(mMetadata == null) {
            mMetadata = new MutableLiveData<>();
        }

        return mMetadata;
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