package com.byd.filemanager;

import android.os.Handler;
import android.os.Looper;
import d.a.j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ThreadManager implements androidx.lifecycle.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile ThreadManager f3517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExecutorService f3518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f3519c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private j f3520d;

    private ThreadManager() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, Runtime.getRuntime().availableProcessors() * 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue());
        this.f3518b = threadPoolExecutor;
        this.f3520d = d.a.p.a.b(threadPoolExecutor);
    }

    public static ThreadManager h() {
        if (f3517a == null) {
            synchronized (ThreadManager.class) {
                if (f3517a == null) {
                    f3517a = new ThreadManager();
                }
            }
        }
        return f3517a;
    }

    public j i() {
        return this.f3520d;
    }

    public void j(Runnable runnable) {
        this.f3519c.post(runnable);
    }

    public void k(Runnable runnable) {
        this.f3518b.execute(runnable);
    }
}
