package b.a.d.f;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: BydThreadPool.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ExecutorService f357a;

    public static void a(Runnable runnable) {
        if (f357a == null) {
            f357a = Executors.newFixedThreadPool(8);
        }
        f357a.execute(runnable);
    }
}
