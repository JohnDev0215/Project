package org.chromium.base;

import J.N;
import java.lang.Thread;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JavaExceptionReporter implements Thread.UncaughtExceptionHandler {
    public final Thread.UncaughtExceptionHandler a;
    public final boolean b;
    public boolean c;

    public JavaExceptionReporter(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, boolean z) {
        this.a = uncaughtExceptionHandler;
        this.b = z;
    }

    public static void installHandler(boolean z) {
        Thread.setDefaultUncaughtExceptionHandler(new JavaExceptionReporter(Thread.getDefaultUncaughtExceptionHandler(), z));
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (!this.c) {
            this.c = true;
            N.MLlibBXh(this.b, th);
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.a;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
