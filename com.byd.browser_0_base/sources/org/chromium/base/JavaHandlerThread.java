package org.chromium.base;

import J.N;
import android.os.Handler;
import android.os.HandlerThread;
import java.lang.Thread;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class JavaHandlerThread {
    public final HandlerThread a;
    public Throwable b;

    public class a implements Runnable {
        public final /* synthetic */ long e;
        public final /* synthetic */ long f;

        public a(long j, long j2) {
            this.e = j;
            this.f = j2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            N.MJcct7gJ(this.e, this.f);
        }
    }

    public class b implements Runnable {
        public final /* synthetic */ long e;

        public b(long j) {
            this.e = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            JavaHandlerThread.this.a.quit();
            N.MYwg$x8E(this.e);
        }
    }

    public class c implements Thread.UncaughtExceptionHandler {
        public c() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable th) {
            JavaHandlerThread.this.b = th;
        }
    }

    public JavaHandlerThread(String str, int i) {
        this.a = new HandlerThread(str, i);
    }

    @CalledByNative
    public static JavaHandlerThread create(String str, int i) {
        return new JavaHandlerThread(str, i);
    }

    @CalledByNative
    public final Throwable getUncaughtExceptionIfAny() {
        return this.b;
    }

    @CalledByNative
    public final boolean isAlive() {
        return this.a.isAlive();
    }

    @CalledByNative
    public final void joinThread() {
        boolean z = false;
        while (!z) {
            try {
                this.a.join();
                z = true;
            } catch (InterruptedException unused) {
            }
        }
    }

    @CalledByNative
    public final void listenForUncaughtExceptionsForTesting() {
        this.a.setUncaughtExceptionHandler(new c());
    }

    @CalledByNative
    public final void quitThreadSafely(long j) {
        HandlerThread handlerThread = this.a;
        new Handler(handlerThread.getLooper()).post(new b(j));
        handlerThread.getLooper().quitSafely();
    }

    @CalledByNative
    public final void startAndInitialize(long j, long j2) {
        HandlerThread handlerThread = this.a;
        if (!(handlerThread.getState() != Thread.State.NEW)) {
            handlerThread.start();
        }
        new Handler(handlerThread.getLooper()).post(new a(j, j2));
    }
}
