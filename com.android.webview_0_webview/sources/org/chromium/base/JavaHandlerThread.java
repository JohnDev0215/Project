package org.chromium.base;

import android.os.Handler;
import android.os.HandlerThread;
import defpackage.Ps;
import defpackage.Qs;
import defpackage.Rs;
import java.lang.Thread;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JavaHandlerThread {
    public final HandlerThread a;
    public Throwable b;

    public JavaHandlerThread(String str, int i) {
        this.a = new HandlerThread(str, i);
    }

    public static JavaHandlerThread create(String str, int i) {
        return new JavaHandlerThread(str, i);
    }

    private Throwable getUncaughtExceptionIfAny() {
        return this.b;
    }

    private boolean isAlive() {
        return this.a.isAlive();
    }

    private void joinThread() {
        boolean z = false;
        while (!z) {
            try {
                this.a.join();
                z = true;
            } catch (InterruptedException unused) {
            }
        }
    }

    private void listenForUncaughtExceptionsForTesting() {
        this.a.setUncaughtExceptionHandler(new Rs(this));
    }

    private void quitThreadSafely(long j) {
        new Handler(this.a.getLooper()).post(new Qs(this, j));
        this.a.getLooper().quitSafely();
    }

    private void startAndInitialize(long j, long j2) {
        a();
        new Handler(this.a.getLooper()).post(new Ps(this, j, j2));
    }

    public void a() {
        if (this.a.getState() != Thread.State.NEW) {
            return;
        }
        this.a.start();
    }
}
