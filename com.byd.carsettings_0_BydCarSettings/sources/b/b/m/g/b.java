package b.b.m.g;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: BydThreadPool.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ExecutorService f2169a;

    public static void a(Runnable runnable) {
        if (f2169a == null) {
            f2169a = Executors.newFixedThreadPool(8);
        }
        f2169a.execute(runnable);
    }
}
