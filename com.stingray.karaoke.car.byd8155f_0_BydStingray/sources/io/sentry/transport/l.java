package io.sentry.transport;

import io.sentry.A;
import io.sentry.AbstractC1453b1;
import io.sentry.EnumC1495p1;
import io.sentry.InterfaceC1456c1;
import io.sentry.K;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class l extends ThreadPoolExecutor {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public AbstractC1453b1 f16904m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final K f16905n;
    public final InterfaceC1456c1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final e7.f f16906p;

    public l(int i7, A a10, a aVar, K k10, InterfaceC1456c1 interfaceC1456c1) {
        super(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), a10, aVar);
        this.f16904m = null;
        this.f16906p = new e7.f(18, (byte) 0);
        this.l = i7;
        this.f16905n = k10;
        this.o = interfaceC1456c1;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public final void afterExecute(Runnable runnable, Throwable th) {
        e7.f fVar = this.f16906p;
        try {
            super.afterExecute(runnable, th);
        } finally {
            fVar.getClass();
            int i7 = n.l;
            ((n) fVar.f14480m).releaseShared(1);
        }
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final Future submit(Runnable runnable) {
        e7.f fVar = this.f16906p;
        if (n.a((n) fVar.f14480m) < this.l) {
            n.b((n) fVar.f14480m);
            return super.submit(runnable);
        }
        this.f16904m = this.o.a();
        this.f16905n.k(EnumC1495p1.WARNING, "Submit cancelled", new Object[0]);
        return new k();
    }
}
