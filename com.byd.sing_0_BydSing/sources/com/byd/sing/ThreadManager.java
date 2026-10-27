package com.byd.sing;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ThreadManager implements androidx.lifecycle.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile ThreadManager f3239a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f3241c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExecutorService f3240b = new ThreadPoolExecutor(2, Runtime.getRuntime().availableProcessors() * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());

    private ThreadManager() {
    }

    public static ThreadManager h() {
        if (f3239a == null) {
            synchronized (ThreadManager.class) {
                if (f3239a == null) {
                    f3239a = new ThreadManager();
                }
            }
        }
        return f3239a;
    }

    public void i(Runnable runnable) {
        this.f3240b.execute(runnable);
    }
}
