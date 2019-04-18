package com.udacity.popular_movies_stage_02.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface VideosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVideos(List<Video> videos);

    @Query("SELECT * FROM video")
    LiveData<List<Video>> getAll();

    @Query("SELECT * FROM video WHERE id = :id LIMIT 1")
    LiveData<Video> getVideoById(int id);
}