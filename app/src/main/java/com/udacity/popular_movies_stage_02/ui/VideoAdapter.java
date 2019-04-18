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
import com.udacity.popular_movies_stage_02.vo.VideoResult;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<VideoResult> mVideoResults;

    private final DetailActivity mActivity;
    private final DetailViewModel mDetailViewModel;

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        private TextView mMovieVideoName;
        private VideoViewHolder(ConstraintLayout constraintLayout) {
            super(constraintLayout);
            mMovieVideoName = constraintLayout.findViewById(R.id.movie_video_name);
        }
    }

    public VideoAdapter(DetailActivity activity, DetailViewModel detailViewModel) {
        mActivity = activity;
        mDetailViewModel = detailViewModel;
        startVideoObserver();
    }

    private void startVideoObserver() {
        mDetailViewModel.getVideos().observe(mActivity, new Observer<List<VideoResult>>() {
            @Override
            public void onChanged(@Nullable List<VideoResult> videoResults) {
                if (videoResults == null) {
                    return;
                }
                mVideoResults = videoResults;
                VideoAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public VideoResult getVideoAtPosition(int position) {
        return mVideoResults.get(position);
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_video, parent, false);

        constraintLayout.setOnClickListener(mActivity);

        return new VideoViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.mMovieVideoName.setText(mVideoResults.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mVideoResults == null) {
            return 0;
        }

        return mVideoResults.size();
    }
}