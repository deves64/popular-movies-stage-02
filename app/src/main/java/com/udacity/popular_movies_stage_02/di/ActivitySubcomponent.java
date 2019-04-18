package com.udacity.popular_movies_stage_02.di;

import com.udacity.popular_movies_stage_02.DetailActivity;
import com.udacity.popular_movies_stage_02.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
public interface ActivitySubcomponent {
    void inject(MainActivity mainActivity);
    void inject(DetailActivity detailActivity);
}
