package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class u implements Runnable {
    public final Runnable c;
    public final w e;
    public final long k;

    public u(Runnable runnable, w wVar, long j) {
        this.c = runnable;
        this.e = wVar;
        this.k = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.e.l) {
            return;
        }
        w wVar = this.e;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        wVar.getClass();
        long jA = Scheduler.a(timeUnit);
        long j = this.k;
        if (j > jA) {
            try {
                Thread.sleep(j - jA);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                RxJavaPlugins.b(e);
                return;
            }
        }
        if (this.e.l) {
            return;
        }
        this.c.run();
    }
}
