package org.chromium.content.browser;

import android.os.Handler;
import android.os.HandlerThread;
import java.lang.Thread;
import org.chromium.base.JavaHandlerThread;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class LauncherThread {
    public static final JavaHandlerThread a;
    public static final Handler b;

    static {
        JavaHandlerThread javaHandlerThread = new JavaHandlerThread("Chrome_ProcessLauncherThread", 0);
        a = javaHandlerThread;
        HandlerThread handlerThread = javaHandlerThread.a;
        if (!(handlerThread.getState() != Thread.State.NEW)) {
            handlerThread.start();
        }
        b = new Handler(handlerThread.getLooper());
    }

    public static void a(Runnable runnable) {
        b.post(runnable);
    }

    @CalledByNative
    public static JavaHandlerThread getHandlerThread() {
        return a;
    }
}
