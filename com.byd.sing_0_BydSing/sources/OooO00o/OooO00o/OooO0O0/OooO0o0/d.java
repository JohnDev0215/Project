package OooO00o.OooO00o.OooO0O0.OooO0o0;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Handler f36a = new Handler(Looper.getMainLooper());

    public static void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            f36a.post(runnable);
        }
    }
}
