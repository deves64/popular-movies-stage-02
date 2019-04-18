package com.udacity.popular_movies_stage_02.ui;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.popular_movies_stage_02.DetailActivity;
import com.udacity.popular_movies_stage_02.R;
import com.udacity.popular_movies_stage_02.viewmodel.DetailViewModel;
import com.udacity.popular_movies_stage_02.vo.ReviewResults;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<ReviewResults> mReviewResults;

    private final DetailActivity mActivity;
    private final DetailViewModel mDetailViewModel;

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView mMovieReviewAuthorTextView;
        private TextView mMovieReviewContentTextView;
        private ReviewViewHolder(ConstraintLayout constraintLayout) {
            super(constraintLayout);
            mMovieReviewAuthorTextView = constraintLayout.findViewById(R.id.movie_review_author);
            mMovieReviewContentTextView = constraintLayout.findViewById(R.id.movie_review_content);
        }
    }

    public ReviewAdapter(DetailActivity activity, DetailViewModel detailViewModel) {
        mActivity = activity;
        mDetailViewModel = detailViewModel;
        startReviewsObserver();
    }

    private void startReviewsObserver() {
        mDetailViewModel.getReviews().observe(mActivity, new Observer<List<ReviewResults>>() {
            @Override
            public void onChanged(@Nullable List<ReviewResults> reviewResults) {
                if (reviewResults == null) {
                    return;
                }
                mReviewResults = reviewResults;
                ReviewAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public ReviewResults getReviewAtPosition(int position) {
        return mReviewResults.get(position);
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_review, parent, false);

        return new ReviewViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.mMovieReviewAuthorTextView.setText(mReviewResults.get(position).getAuthor());
        holder.mMovieReviewContentTextView.setText(mReviewResults.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        if (mReviewResults == null) {
            return 0;
        }

        return mReviewResults.size();
    }
}