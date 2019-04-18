package com.udacity.popular_movies_stage_02.ui;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.udacity.popular_movies_stage_02.MainActivity;
import com.udacity.popular_movies_stage_02.R;
import com.udacity.popular_movies_stage_02.db.MovieDb;
import com.udacity.popular_movies_stage_02.viewmodel.MainViewModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieDb> mMovieDbs;

    private final MainActivity mActivity;
    private final MainViewModel mMainViewModel;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView mMoviePosterImage;
        private MovieViewHolder(ConstraintLayout constraintLayout) {
            super(constraintLayout);
            mMoviePosterImage = constraintLayout.findViewById(R.id.movie_poster_image);
        }
    }

    public MovieAdapter(MainActivity activity, MainViewModel mainViewModel) {
        mActivity = activity;
        mMainViewModel = mainViewModel;
        startDatabaseObserver();
    }

    public void startDatabaseObserver() {
        mMainViewModel.getMovies().observe(mActivity, new Observer<List<MovieDb>>() {
            @Override
            public void onChanged(@Nullable List<MovieDb> movieDbs) {
                if (movieDbs == null) {
                    return;
                }
                Log.v("startDatabaseObserver", Integer.toString(movieDbs.size()));
                mMovieDbs = movieDbs;
                MovieAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public MovieDb getMovieAtPosition(int position) {
        return mMovieDbs.get(position);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_poster, parent, false);

        constraintLayout.setOnClickListener(mActivity);

        return new MovieViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Glide.with(mActivity)
                .load("https://image.tmdb.org/t/p/w185" + mMovieDbs.get(position).posterPath)
                .fitCenter()
                .into(holder.mMoviePosterImage);
    }

    @Override
    public int getItemCount() {
        if (mMovieDbs == null) {
            return 0;
        }

        return mMovieDbs.size();
    }
}