package io.sentry.hints;

import io.sentry.EnumC1495p1;
import io.sentry.K;
import io.sentry.protocol.t;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class c implements f {
    public final CountDownLatch l = new CountDownLatch(1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f16549m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final K f16550n;

    public c(long j3, K k10) {
        this.f16549m = j3;
        this.f16550n = k10;
    }

    @Override // io.sentry.hints.f
    public final boolean d() {
        try {
            return this.l.await(this.f16549m, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e10) {
            Thread.currentThread().interrupt();
            this.f16550n.s(EnumC1495p1.ERROR, "Exception while awaiting for flush in BlockingFlushHint", e10);
            return false;
        }
    }

    public abstract boolean f(t tVar);

    public abstract void g(t tVar);
}
