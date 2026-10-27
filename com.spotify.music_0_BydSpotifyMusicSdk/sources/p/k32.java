package p;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k32 {
    public final /* synthetic */ l32 a;
    public final /* synthetic */ bj2 b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ ch d;

    public /* synthetic */ k32(l32 l32Var, bj2 bj2Var, boolean z, ch chVar) {
        this.a = l32Var;
        this.b = bj2Var;
        this.c = z;
        this.d = chVar;
    }

    public final void a(Exception exc) throws Throwable {
        l32 l32Var = this.a;
        l32Var.getClass();
        bj2 bj2Var = this.b;
        if (exc != null) {
            bj2Var.a(exc);
            return;
        }
        if (this.c) {
            boolean z = true;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new ua(l32Var, 19, countDownLatch)).start();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            ExecutorService executorService = ks2.a;
            boolean z2 = false;
            try {
                long nanos = timeUnit.toNanos(2L);
                long jNanoTime = System.nanoTime() + nanos;
                while (true) {
                    try {
                        try {
                            countDownLatch.await(nanos, TimeUnit.NANOSECONDS);
                            break;
                        } catch (InterruptedException unused) {
                            nanos = jNanoTime - System.nanoTime();
                            z2 = true;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (z) {
                            Thread.currentThread().interrupt();
                        }
                        throw th;
                    }
                }
                if (z2) {
                    Thread.currentThread().interrupt();
                }
            } catch (Throwable th2) {
                th = th2;
                z = z2;
            }
        }
        bj2Var.b(this.d);
    }
}
