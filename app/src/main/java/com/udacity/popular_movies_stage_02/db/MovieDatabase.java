package com.udacity.popular_movies_stage_02.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MovieDb.class, Video.class, Review.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MoviePopularDao moviePopularDao();
    public abstract MovieRatedDao movieRatedDao();
    public abstract MovieFavoriteDao movieFavoriteDao();
    public abstract VideosDao videosDao();
    public abstract ReviewsDao reviewsDao();
}
