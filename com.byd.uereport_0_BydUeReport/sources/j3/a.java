package j3;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: HandlerUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Handler f5577a = new Handler(Looper.getMainLooper());

    public static void a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        f5577a.post(runnable);
    }

    public static void b(Runnable runnable, long j8) {
        if (runnable == null) {
            return;
        }
        f5577a.postDelayed(runnable, j8);
    }
}
