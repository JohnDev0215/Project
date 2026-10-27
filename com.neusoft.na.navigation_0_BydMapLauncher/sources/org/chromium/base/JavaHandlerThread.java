package org.chromium.base;

import android.os.Handler;
import android.os.HandlerThread;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
class JavaHandlerThread {
    final HandlerThread mThread;

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeInitializeThread(long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeStopThread(long j, long j2);

    private JavaHandlerThread(String str) {
        this.mThread = new HandlerThread(str);
    }

    private static JavaHandlerThread create(String str) {
        return new JavaHandlerThread(str);
    }

    private void start(final long j, final long j2) {
        this.mThread.start();
        new Handler(this.mThread.getLooper()).post(new Runnable() { // from class: org.chromium.base.JavaHandlerThread.1
            @Override // java.lang.Runnable
            public void run() {
                JavaHandlerThread.this.nativeInitializeThread(j, j2);
            }
        });
    }

    private void stop(final long j, final long j2) {
        final boolean z = true;
        new Handler(this.mThread.getLooper()).post(new Runnable() { // from class: org.chromium.base.JavaHandlerThread.2
            @Override // java.lang.Runnable
            public void run() {
                JavaHandlerThread.this.nativeStopThread(j, j2);
                if (z) {
                    return;
                }
                JavaHandlerThread.this.mThread.quit();
            }
        });
        this.mThread.quitSafely();
    }
}
