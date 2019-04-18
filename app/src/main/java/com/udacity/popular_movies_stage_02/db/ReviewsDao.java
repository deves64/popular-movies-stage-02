package com.udacity.popular_movies_stage_02.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ReviewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReviews(List<Review> reviews);

    @Query("SELECT * FROM review")
    LiveData<List<Review>> getAll();
}
