package com.amazon.music.media.playback.util.impl;

import androidx.annotation.NonNull;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/* JADX INFO: loaded from: classes4.dex */
public interface ThreadUtilsProvider {
    void cancelOnMainThread(@NonNull Runnable runnable);

    ExecutorService createBackgroundExecutor(String str);

    ScheduledExecutorService getBackgroundExecutor();

    Executor getMainThreadExecutor();

    boolean isOnMainThread();

    void postInBackground(@NonNull Runnable runnable);

    <T> ScheduledFuture<T> postInBackgroundDelayed(@NonNull Callable<T> callable, long j);

    void postOnMainThread(@NonNull Runnable runnable);

    void postOnMainThreadDelayed(@NonNull Runnable runnable, long j);
}
