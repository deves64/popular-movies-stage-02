package com.udacity.popular_movies_stage_02.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.udacity.popular_movies_stage_02.api.MovieService;
import com.udacity.popular_movies_stage_02.db.MovieFavoriteDao;
import com.udacity.popular_movies_stage_02.db.MoviePopularDao;
import com.udacity.popular_movies_stage_02.db.MovieRatedDao;
import com.udacity.popular_movies_stage_02.repository.MoviePopularRepository;
import com.udacity.popular_movies_stage_02.repository.MovieRatedRepository;
import com.udacity.popular_movies_stage_02.util.AppExecutors;
import com.udacity.popular_movies_stage_02.util.MainThreadExecutor;
import com.udacity.popular_movies_stage_02.R;
import com.udacity.popular_movies_stage_02.db.MovieDatabase;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private final static String BASE_URL = "https://api.themoviedb.org/3/";

    private static final String API_KEY_PARAM = "api_key";

    private static final String LANGUAGE_PARAM = "language";

    private final static String DEFAULT_LANGUAGE = "en-US";

    private final Application mApplication;

    private final Context mContext;

    public ApplicationModule(Application application, Context context) {
        mApplication = application;
        mContext = context;
    }

    @Singleton
    @Provides
    Interceptor getInterceptorInstance() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                HttpUrl originalUrl = originalRequest.url();

                HttpUrl newUrl = originalUrl.newBuilder()
                        .addQueryParameter(API_KEY_PARAM, mContext.getString(R.string.movie_db_api_token))
                        .addQueryParameter(LANGUAGE_PARAM, DEFAULT_LANGUAGE)
                        .build();

                Request.Builder builder = originalRequest.newBuilder()
                        .url(newUrl);

                Request request = builder.build();

                return  chain.proceed(request);
            }
        };
    }

    @Singleton
    @Provides
    OkHttpClient getOkHttpClientInstance(Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);

        return  builder.build();
    }

    @Singleton
    @Provides
    Retrofit getRetrofitInstance(OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    @Named("diskIoExecutor")
    Executor getDiskIoExecutorInstance() {
        return Executors.newSingleThreadExecutor();
    }

    @Singleton
    @Provides
    @Named("networkIoExecutor")
    Executor getNetworkIoExecutorInstance() {
        return Executors.newFixedThreadPool(3);
    }

    @Singleton
    @Provides
    @Named("mainThread")
    Executor getMainThread() {
        return new MainThreadExecutor();
    }

    @Singleton
    @Provides
    AppExecutors getAppExecutorsInstance(@Named("diskIoExecutor") Executor diskIoExecutor,
                                         @Named("networkIoExecutor") Executor networkIoExecutor,
                                         @Named("mainThread") Executor mainThread) {

        return new AppExecutors(diskIoExecutor, networkIoExecutor, mainThread);
    }

    @Singleton
    @Provides
    MovieDatabase getMovieServiceInstance() {
        return Room.databaseBuilder(mApplication, MovieDatabase.class, "movieDbs")
                .build();
    }

    @Provides
    MoviePopularDao getMoviePopularDaoInstance(MovieDatabase movieDatabase) {
        return movieDatabase.moviePopularDao();
    }

    @Provides
    MovieRatedDao getMovieRatedDaoInstance(MovieDatabase movieDatabase) {
        return movieDatabase.movieRatedDao();
    }

    @Provides
    MovieFavoriteDao getMovieFavoriteDaoInstance(MovieDatabase movieDatabase) {
        return movieDatabase.movieFavoriteDao();
    }
}