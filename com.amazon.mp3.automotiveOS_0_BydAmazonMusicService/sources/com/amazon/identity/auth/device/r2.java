package com.amazon.identity.auth.device;

import android.util.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class r2 implements Runnable {
    public static final String c = r2.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile CountDownLatch f522a = new CountDownLatch(1);
    public AtomicBoolean b = new AtomicBoolean(false);

    public void asyncOperationComplete() {
        this.f522a.countDown();
    }

    public void interruptAsyncOperation() {
    }

    public void onTimeout() {
    }

    @Override // java.lang.Runnable
    public synchronized void run() {
        run(null, null, null);
    }

    public abstract void startAsyncOperation();

    public synchronized void run(Long l, TimeUnit timeUnit, String str) {
        if (this.b.getAndSet(true)) {
            Log.e(ga.a(c), "Attempted to call run() more than once on the same object.");
            return;
        }
        ga.a(c, "Starting the operation %s and waiting for it to finish", str);
        startAsyncOperation();
        do {
            if (l != null) {
                try {
                    if (!this.f522a.await(l.longValue(), timeUnit)) {
                        onTimeout();
                    }
                } catch (InterruptedException unused) {
                    interruptAsyncOperation();
                }
            } else {
                this.f522a.await();
            }
        } while (this.f522a.getCount() > 0);
        ga.a(c, "Completed the operation %s", str);
    }
}
