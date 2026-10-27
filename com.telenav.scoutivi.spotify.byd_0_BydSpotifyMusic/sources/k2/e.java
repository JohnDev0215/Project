package k2;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: Executors.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Executor f7246a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Executor f7247b = new b();

    /* JADX INFO: compiled from: Executors.java */
    class a implements Executor {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Handler f7248e = new Handler(Looper.getMainLooper());

        a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f7248e.post(runnable);
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
        return f7247b;
    }

    public static Executor b() {
        return f7246a;
    }
}
