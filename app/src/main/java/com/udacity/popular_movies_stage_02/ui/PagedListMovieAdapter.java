package com.udacity.popular_movies_stage_02.ui;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.udacity.popular_movies_stage_02.MainActivity;
import com.udacity.popular_movies_stage_02.R;
import com.udacity.popular_movies_stage_02.db.MovieDb;

public class PagedListMovieAdapter extends PagedListAdapter<MovieDb, PagedListMovieAdapter.MovieViewHolder> {
    private final MainActivity mMainActivity;

    public PagedListMovieAdapter(MainActivity mainActivity) {
        super(DIFF_CALLBACK);
        mMainActivity = mainActivity;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView mMoviePosterImage;
        private MovieViewHolder(ConstraintLayout constraintLayout) {
            super(constraintLayout);
            mMoviePosterImage = constraintLayout.findViewById(R.id.movie_poster_image);
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_poster, parent, false);

        constraintLayout.setOnClickListener(mMainActivity);

        return new MovieViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieDb movieDb = getItem(position);

        if(movieDb != null) {
            Glide.with(mMainActivity)
                    .load("https://image.tmdb.org/t/p/w185" + movieDb.getPosterPath())
                    .fitCenter()
                    .into(holder.mMoviePosterImage);
        }
    }

    private static DiffUtil.ItemCallback<MovieDb> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieDb>() {

                @Override
                public boolean areItemsTheSame(MovieDb oldItem, MovieDb newItem) {
                    // The ID property identifies when items are the same.
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(MovieDb oldItem, MovieDb newItem) {
                    // Don't use the "==" operator here. Either implement and use .equals(),
                    // or write custom data comparison logic here.
                    return oldItem.equals(newItem);
                }
            };
}