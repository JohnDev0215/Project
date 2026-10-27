package com.android.systemui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.inject.Inject;
import javax.inject.Singleton;

/* JADX INFO: loaded from: classes.dex */
@Singleton
public class UiOffloadThread {
    private static final UiOffloadThread sInstance = new UiOffloadThread();
    private final ExecutorService mExecutorService = Executors.newSingleThreadExecutor();

    public static UiOffloadThread get() {
        return sInstance;
    }

    @Inject
    public UiOffloadThread() {
    }

    public Future<?> submit(Runnable runnable) {
        return this.mExecutorService.submit(runnable);
    }
}
