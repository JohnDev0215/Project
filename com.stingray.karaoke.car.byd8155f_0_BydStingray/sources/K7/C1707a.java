package k7;

import C1.m;
import g4.RunnableC1297a;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/* JADX INFO: renamed from: k7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1707a extends Thread {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static C1707a f18310n;
    public static ExecutorService o;
    public static final Logger l = Logger.getLogger(C1707a.class.getName());

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final m f18309m = new m(4);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static int f18311p = 0;

    public static void a(Runnable runnable) {
        if (Thread.currentThread() == f18310n) {
            runnable.run();
        } else {
            b(runnable);
        }
    }

    public static void b(Runnable runnable) {
        ExecutorService executorService;
        synchronized (C1707a.class) {
            try {
                f18311p++;
                if (o == null) {
                    o = Executors.newSingleThreadExecutor(f18309m);
                }
                executorService = o;
            } catch (Throwable th) {
                throw th;
            }
        }
        executorService.execute(new RunnableC1297a(runnable, 1));
    }
}
