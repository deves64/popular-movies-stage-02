package com.udacity.popular_movies_stage_02;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.udacity.popular_movies_stage_02.di.ActivityModule;
import com.udacity.popular_movies_stage_02.di.ActivitySubcomponent;
import com.udacity.popular_movies_stage_02.di.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {
    private boolean mIsInjectorUsed;

    @UiThread
    protected ActivitySubcomponent getActivitySubcomponent() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }

        mIsInjectorUsed = true;

        return getApplicationComponent().newActivitySubcomponent(new ActivityModule(this));

    }

    private ApplicationComponent getApplicationComponent() {
        return ((Application) getApplication()).getApplicationComponent();
    }
}
