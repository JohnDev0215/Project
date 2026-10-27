package org.chromium.components.crash;

import defpackage.xz;
import java.lang.Thread;
import org.chromium.base.BundleUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.base.SplitCompatApplication;

/* JADX INFO: loaded from: classes2.dex */
public class PureJavaExceptionHandler implements Thread.UncaughtExceptionHandler {
    public static boolean d = true;
    public final Thread.UncaughtExceptionHandler a;
    public boolean b;
    public final b c;

    public interface a {
        void a(Throwable th);
    }

    public interface b {
    }

    public PureJavaExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, SplitCompatApplication.a aVar) {
        this.a = uncaughtExceptionHandler;
        this.c = aVar;
    }

    @CalledByNative
    public static void uninstallHandler() {
        d = false;
        CrashKeys.getInstance().flushToNative();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        if (!this.b && d) {
            this.b = true;
            ((SplitCompatApplication.a) this.c).getClass();
            ((a) BundleUtils.e(SplitCompatApplication.b(xz.a), "org.chromium.chrome.browser.crash.ChromePureJavaExceptionReporter")).a(th);
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.a;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
