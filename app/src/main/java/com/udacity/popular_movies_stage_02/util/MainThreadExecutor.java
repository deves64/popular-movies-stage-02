package com.udacity.popular_movies_stage_02.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

public class MainThreadExecutor implements Executor {
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull Runnable command) {
        mainThreadHandler.post(command);
    }
}
