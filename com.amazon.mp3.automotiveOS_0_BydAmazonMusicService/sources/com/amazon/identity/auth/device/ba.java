package com.amazon.identity.auth.device;

import android.os.Handler;
import android.util.Log;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ba {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f245a = "com.amazon.identity.auth.device.ba";

    /* JADX INFO: compiled from: DCP */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f246a;
        public final /* synthetic */ CountDownLatch b;

        public a(Runnable runnable, CountDownLatch countDownLatch) {
            this.f246a = runnable;
            this.b = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f246a.run();
            } finally {
                this.b.countDown();
            }
        }
    }

    public static void a(Handler handler, Runnable runnable) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        handler.post(new a(runnable, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Log.e(ga.a(f245a), "Latch was interrupted.", e);
        }
    }
}
