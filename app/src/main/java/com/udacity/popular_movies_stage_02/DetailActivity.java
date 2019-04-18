package com.udacity.popular_movies_stage_02;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.udacity.popular_movies_stage_02.databinding.ActivityDetailBinding;

import com.udacity.popular_movies_stage_02.db.MovieDb;
import com.udacity.popular_movies_stage_02.db.MovieFavoriteDao;
import com.udacity.popular_movies_stage_02.ui.ReviewAdapter;
import com.udacity.popular_movies_stage_02.ui.VideoAdapter;
import com.udacity.popular_movies_stage_02.util.AppExecutors;
import com.udacity.popular_movies_stage_02.viewmodel.DetailViewModel;
import com.udacity.popular_movies_stage_02.viewmodel.DetailViewModelFactory;
import com.udacity.popular_movies_stage_02.vo.Movie;
import com.udacity.popular_movies_stage_02.vo.VideoResult;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity implements View.OnClickListener{
    @Inject
    DetailViewModelFactory detailViewModelFactory;
    @Inject
    MovieFavoriteDao movieFavoriteDao;
    @Inject
    AppExecutors appExecutors;

    ActivityDetailBinding mActivityDetailBinding;

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";

    public int movieId = 0;

    private Movie mMovieApi;

    private MovieDb mMovieDb;

    @SuppressWarnings("FieldCanBeLocal")
    private RecyclerView recyclerViewReviews;

    private RecyclerView recyclerViewVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mActivityDetailBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        final int id = intent.getIntExtra(EXTRA_MOVIE_ID, 0);

        if (id == 0) {
            closeOnError();
        }

        movieId = id;

        getActivitySubcomponent().inject(this);

        final DetailViewModel viewModel
                = ViewModelProviders.of(this, detailViewModelFactory)
                .get(DetailViewModel.class);

        recyclerViewReviews = (RecyclerView) mActivityDetailBinding.reviewsDetail;
        recyclerViewReviews.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerViewReviews.setLayoutManager(layoutManager);

        ReviewAdapter adapter = new ReviewAdapter(this, viewModel);

        recyclerViewReviews.setAdapter(adapter);


        recyclerViewVideos = (RecyclerView) mActivityDetailBinding.videosDetail;
        recyclerViewVideos.setHasFixedSize(true);
        LinearLayoutManager layoutManagerVideos = new LinearLayoutManager(this);

        recyclerViewVideos.setLayoutManager(layoutManagerVideos);

        VideoAdapter adapterVideos = new VideoAdapter(this, viewModel);

        recyclerViewVideos.setAdapter(adapterVideos);

        final LiveData<MovieDb> movieDbLiveData = viewModel.getMovieDb();

        movieDbLiveData.observe(this, new Observer<MovieDb>() {
            @Override
            public void onChanged(@Nullable MovieDb movieDb) {
                if (movieDb != null) {
                    mMovieDb = movieDb;
                    populateUI();
                }

                final LiveData<Movie> movieLiveData = viewModel.getMovie();

                movieLiveData.observe(DetailActivity.this, new Observer<Movie>() {
                    @Override
                    public void onChanged(@Nullable Movie movie) {
                        if (movie != null) {
                            mMovieApi = movie;
                            populateUI();
                        }
                    }
                });
            }
        });



        startMovieDbObserver(movieId);

        mActivityDetailBinding.markAsFavoriteDetail.setOnClickListener(this);
    }

    private void startMovieDbObserver(int id) {
        final LiveData<MovieDb> liveDataMovie = movieFavoriteDao.getMovieById(id);
        liveDataMovie.observe(this, new Observer<MovieDb>() {
            @Override
            public void onChanged(@Nullable MovieDb movieDb) {
                liveDataMovie.removeObservers(DetailActivity.this);
                if (movieDb == null) {
                    mActivityDetailBinding.markAsFavoriteDetail
                            .setText(R.string.mark_as_favorite_detail_text);
                    mMovieDb = null;
                    return;
                }

                mActivityDetailBinding.markAsFavoriteDetail
                        .setText(R.string.marked_as_favorite_detail_text);
                mMovieDb = movieDb;
            }
        });
    }

    private void populateUI()
    {
        if (mMovieDb != null) {
            mActivityDetailBinding.originalTitleDetail.setText(mMovieDb.getOriginalTitle());
            mActivityDetailBinding.releaseDateDetail
                    .setText(mMovieDb.getReleaseDate().substring(0, 4));
            mActivityDetailBinding.voteAverageDetail
                    .setText(mMovieDb.getVoteAverage() + getString(R.string.rating_out_of));
            mActivityDetailBinding.overviewDetail.setText(mMovieDb.getOverview());

            Glide.with(DetailActivity.this)
                    .load("https://image.tmdb.org/t/p/w185" + mMovieDb.getPosterPath())
                    .fitCenter()
                    .into(mActivityDetailBinding.thumbnailDetail);

            return;
        }

        mActivityDetailBinding.originalTitleDetail.setText(mMovieApi.getOriginalTitle());
        mActivityDetailBinding.releaseDateDetail
                .setText(mMovieApi.getReleaseDate().substring(0, 4));
        mActivityDetailBinding.voteAverageDetail
                .setText(mMovieApi.getVoteAverage() + getString(R.string.rating_out_of));
        mActivityDetailBinding.overviewDetail.setText(mMovieApi.getOverview());

        Glide.with(DetailActivity.this)
                .load("https://image.tmdb.org/t/p/w185" + mMovieApi.getPosterPath())
                .fitCenter()
                .into(mActivityDetailBinding.thumbnailDetail);
    }

    private void closeOnError() {
        finish();
    }

    @Override
    public void onClick(View v) {
        if (mMovieApi == null) {
            return;
        }

        if(v.findViewById(R.id.movie_video_name) != null) {
            int position = recyclerViewVideos.getChildAdapterPosition(v);
            VideoAdapter videoAdapter = (VideoAdapter) recyclerViewVideos.getAdapter();
            VideoResult videoResult = videoAdapter.getVideoAtPosition(position);
            if(videoResult.getSite().contentEquals("YouTube")) {
                startActivity(
                        new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("vnd.youtube://"+videoResult.getKey()
                                )
                        )
                );
            }
            return;
        }

        if (mMovieDb != null) {
            appExecutors.getmDiskIo().execute(new Runnable() {
                @Override
                public void run() {
                    movieFavoriteDao.deleteMovie(mMovieDb);
                    startMovieDbObserver(mMovieDb.getId());
                }
            });

            return;
        }

        final MovieDb movieDb = new MovieDb();
        movieDb.setId(mMovieApi.getId());
        movieDb.setAdult(mMovieApi.getAdult());
        movieDb.setBackdropPath(mMovieApi.getBackdropPath());
        movieDb.setOriginalLanguage(mMovieApi.getOriginalLanguage());
        movieDb.setOriginalTitle(mMovieApi.getOriginalTitle());
        movieDb.setOverview(mMovieApi.getOverview());
        movieDb.setPopularity(mMovieApi.getPopularity());
        movieDb.setPosterPath(mMovieApi.getPosterPath());
        movieDb.setReleaseDate(mMovieApi.getReleaseDate());
        movieDb.setTitle(mMovieApi.getTitle());
        movieDb.setVideo(mMovieApi.getVideo());
        movieDb.setVoteAverage(mMovieApi.getVoteAverage());
        movieDb.setVoteCount(mMovieApi.getVoteCount());

        appExecutors.getmDiskIo().execute(new Runnable() {
            @Override
            public void run() {
                movieFavoriteDao.insertMovie(movieDb);
                startMovieDbObserver(movieDb.getId());
            }
        });
    }
}