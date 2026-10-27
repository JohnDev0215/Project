package b.a.a.h;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f255a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f257c;
    public static volatile ExecutorService d;
    public static volatile Handler e;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f258a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final AtomicInteger f259b = new AtomicInteger(1);

        public a(String str) {
            this.f258a = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, this.f258a + "#" + this.f259b.getAndIncrement());
        }
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        f255a = iAvailableProcessors;
        f256b = Math.max(4, Math.min(iAvailableProcessors - 1, 8));
        f257c = (iAvailableProcessors * 2) + 1;
    }

    public i() {
        throw new UnsupportedOperationException();
    }

    public static Handler a() {
        if (e == null) {
            synchronized (i.class) {
                if (e == null) {
                    e = new Handler(Looper.getMainLooper());
                }
            }
        }
        return e;
    }

    public static void b(Runnable runnable) {
        c().execute(runnable);
    }

    public static Executor c() {
        if (d == null) {
            synchronized (i.class) {
                if (d == null) {
                    d = new ThreadPoolExecutor(f256b, f257c, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new a("io-pool"));
                }
            }
        }
        return d;
    }

    public static void d(Runnable runnable) {
        a().post(runnable);
    }

    public static void e(Runnable runnable, long j) {
        a().postDelayed(runnable, j);
    }
}
