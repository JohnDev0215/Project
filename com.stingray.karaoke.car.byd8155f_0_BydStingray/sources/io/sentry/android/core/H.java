package io.sentry.android.core;

import io.sentry.EnumC1495p1;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class H implements io.sentry.hints.d, io.sentry.hints.g, io.sentry.hints.j, io.sentry.hints.f {
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.K f16058p;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CountDownLatch f16057n = new CountDownLatch(1);
    public boolean l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f16056m = false;

    public H(long j3, io.sentry.K k10) {
        this.o = j3;
        h6.e.q(k10, "ILogger is required.");
        this.f16058p = k10;
    }

    @Override // io.sentry.hints.g
    public final boolean a() {
        return this.l;
    }

    @Override // io.sentry.hints.j
    public final void b(boolean z6) {
        this.f16056m = z6;
        this.f16057n.countDown();
    }

    @Override // io.sentry.hints.j
    public final boolean c() {
        return this.f16056m;
    }

    @Override // io.sentry.hints.f
    public final boolean d() {
        try {
            return this.f16057n.await(this.o, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e10) {
            Thread.currentThread().interrupt();
            this.f16058p.s(EnumC1495p1.ERROR, "Exception while awaiting on lock.", e10);
            return false;
        }
    }

    @Override // io.sentry.hints.g
    public final void e(boolean z6) {
        this.l = z6;
    }
}
