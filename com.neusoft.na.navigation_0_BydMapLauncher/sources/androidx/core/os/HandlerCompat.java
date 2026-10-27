package androidx.core.os;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class HandlerCompat {
    private static final String TAG = "HandlerCompat";

    public static Handler createAsync(Looper looper) {
        return Handler.createAsync(looper);
    }

    public static Handler createAsync(Looper looper, Handler.Callback callback) {
        return Handler.createAsync(looper, callback);
    }

    public static boolean postDelayed(Handler handler, Runnable runnable, Object obj, long j) {
        return handler.postDelayed(runnable, obj, j);
    }

    private HandlerCompat() {
    }
}
