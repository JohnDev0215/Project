package k4;

import android.os.Looper;

/* JADX INFO: compiled from: ThreadUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Thread f7263a;

    public static void a() {
        if (!b()) {
            throw new IllegalStateException("Must be called on the Main thread.");
        }
    }

    public static boolean b() {
        if (f7263a == null) {
            f7263a = Looper.getMainLooper().getThread();
        }
        return Thread.currentThread() == f7263a;
    }
}
