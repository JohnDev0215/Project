package com.bumptech.glide.o;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: Executors.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Executor f3456a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Executor f3457b = new b();

    /* JADX INFO: compiled from: Executors.java */
    class a implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Handler f3458a = new Handler(Looper.getMainLooper());

        a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f3458a.post(runnable);
        }
    }

    /* JADX INFO: compiled from: Executors.java */
    class b implements Executor {
        b() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    public static Executor a() {
        return f3457b;
    }

    public static Executor b() {
        return f3456a;
    }
}
