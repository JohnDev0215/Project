package org.chromium.content.browser;

import android.os.Handler;
import org.chromium.base.JavaHandlerThread;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class LauncherThread {
    public static final JavaHandlerThread a;
    public static final Handler b;
    public static Handler c;

    static {
        JavaHandlerThread javaHandlerThread = new JavaHandlerThread("Chrome_ProcessLauncherThread", 0);
        a = javaHandlerThread;
        javaHandlerThread.a();
        Handler handler = new Handler(a.a.getLooper());
        b = handler;
        c = handler;
    }

    public static JavaHandlerThread getHandlerThread() {
        return a;
    }
}
