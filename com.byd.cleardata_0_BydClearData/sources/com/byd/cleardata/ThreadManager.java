package com.byd.cleardata;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.f;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ThreadManager implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile ThreadManager f2565a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f2567c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExecutorService f2566b = new ThreadPoolExecutor(2, Runtime.getRuntime().availableProcessors() * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());

    private ThreadManager() {
    }

    public static ThreadManager h() {
        if (f2565a == null) {
            synchronized (ThreadManager.class) {
                if (f2565a == null) {
                    f2565a = new ThreadManager();
                }
            }
        }
        return f2565a;
    }

    public void i(Runnable runnable) {
        this.f2567c.post(runnable);
    }

    public void j(Runnable runnable) {
        this.f2566b.execute(runnable);
    }

    public Future<?> k(Runnable runnable) {
        return this.f2566b.submit(runnable);
    }
}
