package com.udacity.popular_movies_stage_02.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

public class AppExecutors {
    private final Executor mDiskIo;
    private final Executor mNetworkIo;
    private final Executor mMainThread;

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        mDiskIo = diskIO;
        mNetworkIo = networkIO;
        mMainThread = mainThread;
    }

    public Executor getmDiskIo() {
        return mDiskIo;
    }

    public Executor getmNetworkIo() {
        return mNetworkIo;
    }

    public Executor getmMainThread() {
        return mMainThread;
    }

    private class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}