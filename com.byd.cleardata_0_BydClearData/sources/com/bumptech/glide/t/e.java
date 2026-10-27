package com.bumptech.glide.t;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Executor f2542a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Executor f2543b = new b();

    class a implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Handler f2544a = new Handler(Looper.getMainLooper());

        a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f2544a.post(runnable);
        }
    }

    class b implements Executor {
        b() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    public static Executor a() {
        return f2543b;
    }

    public static Executor b() {
        return f2542a;
    }
}
