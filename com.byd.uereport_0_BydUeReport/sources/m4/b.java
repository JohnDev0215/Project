package m4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: BydThreadPool.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ExecutorService f6367a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ExecutorService f6368b;

    public static void a(Runnable runnable) {
        if (f6367a == null) {
            f6367a = Executors.newCachedThreadPool();
        }
        f6367a.execute(runnable);
    }

    public static void b(Runnable runnable) {
        if (f6368b == null) {
            f6368b = Executors.newFixedThreadPool(8);
        }
        f6368b.execute(runnable);
    }
}
