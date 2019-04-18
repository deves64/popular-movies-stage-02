package com.udacity.popular_movies_stage_02.di;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    public ActivitySubcomponent newActivitySubcomponent(ActivityModule activityModule);
}
