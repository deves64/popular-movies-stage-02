package com.udacity.popular_movies_stage_02;

import com.udacity.popular_movies_stage_02.di.ApplicationComponent;
import com.udacity.popular_movies_stage_02.di.ApplicationModule;
import com.udacity.popular_movies_stage_02.di.DaggerApplicationComponent;

public class Application extends android.app.Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(
                        new ApplicationModule(this,
                                this.getApplicationContext())
                )
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
