package com.udacity.popular_movies_stage_02.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MoviePopularDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieDb> movieDbs);

    @Update
    void updateMovie(MovieDb movieDb);

    @Query("SELECT * FROM MovieDb")
    LiveData<List<MovieDb>> getAll();

    @Query("SELECT * FROM MovieDb WHERE id = :id LIMIT 1")
    LiveData<MovieDb> getMovieById(int id);

    @Query("SELECT * FROM MovieDb ORDER BY popularity DESC")
    LiveData<List<MovieDb>> getMoviesByPopularity();
}