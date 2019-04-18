package com.udacity.popular_movies_stage_02.di;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.udacity.popular_movies_stage_02.DetailActivity;
import com.udacity.popular_movies_stage_02.MainActivity;
import com.udacity.popular_movies_stage_02.db.MovieFavoriteDao;
import com.udacity.popular_movies_stage_02.repository.MoviePopularRepository;
import com.udacity.popular_movies_stage_02.repository.MovieRatedRepository;
import com.udacity.popular_movies_stage_02.repository.MovieRepository;
import com.udacity.popular_movies_stage_02.ui.PaginationScrollListener;
import com.udacity.popular_movies_stage_02.util.AppExecutors;
import com.udacity.popular_movies_stage_02.viewmodel.DetailViewModelFactory;
import com.udacity.popular_movies_stage_02.viewmodel.MainViewModel;
import com.udacity.popular_movies_stage_02.viewmodel.MainViewModelFactory;
import com.udacity.popular_movies_stage_02.api.MovieService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    MainViewModelFactory getMainViewModelFactoryInstance(
            MovieRatedRepository movieRatedRepository,
            MoviePopularRepository moviePopularRepository,
            MovieFavoriteDao movieFavoriteDao
    ) {

        return new MainViewModelFactory(
                movieRatedRepository,
                moviePopularRepository,
                movieFavoriteDao
        );
    }

    @Provides
    DetailViewModelFactory getDetailViewModelFactoryInstance(
            MovieRepository movieRepository,
            MovieFavoriteDao movieFavoriteDao) {

        DetailActivity activity = (DetailActivity) mActivity;
        return new DetailViewModelFactory(movieRepository, movieFavoriteDao, activity.movieId);
    }

    @Provides
    MovieService getMovieServiceInstance(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }


    @Provides
    MovieRatedRepository getMovieRatedRepositoryInstance(MovieService movieService,
                                                         AppExecutors appExecutors) {

        return new MovieRatedRepository(movieService, appExecutors);
    }


    @Provides
    MoviePopularRepository getMoviePopularRepositoryInstance(MovieService movieService,
                                                             AppExecutors appExecutors) {

        return new MoviePopularRepository(movieService, appExecutors);
    }

    @Provides
    MainViewModel getMainViewModelInstance(MainViewModelFactory mainViewModelFactory) {
        MainActivity mainActivity = (MainActivity) mActivity;

        return ViewModelProviders.of(mainActivity, mainViewModelFactory).get(MainViewModel.class);
    }

    @Provides
    PaginationScrollListener getPaginationScrollListenerInstance(
            MainViewModel mainViewModel,
            MovieFavoriteDao movieFavoriteDao
    ) {

        MainActivity mainActivity = (MainActivity) mActivity;
        return new PaginationScrollListener(
                mainViewModel.getmMovieRatedRepository(),
                mainViewModel.getmMoviePopularRepository(),
                movieFavoriteDao,
                mainViewModel,
                mainActivity
        );
    }
}