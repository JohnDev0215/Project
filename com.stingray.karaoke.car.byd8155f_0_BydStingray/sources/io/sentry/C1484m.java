package io.sentry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: io.sentry.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1484m implements io.sentry.hints.d, io.sentry.hints.g, io.sentry.hints.j, io.sentry.hints.f {
    public boolean l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f16609m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CountDownLatch f16610n = new CountDownLatch(1);
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final K f16611p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f16612q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Y1 f16613r;

    public C1484m(long j3, K k10, String str, Y1 y12) {
        this.o = j3;
        this.f16612q = str;
        this.f16613r = y12;
        this.f16611p = k10;
    }

    @Override // io.sentry.hints.g
    public final boolean a() {
        return this.l;
    }

    @Override // io.sentry.hints.j
    public final void b(boolean z6) {
        this.f16609m = z6;
        this.f16610n.countDown();
    }

    @Override // io.sentry.hints.j
    public final boolean c() {
        return this.f16609m;
    }

    @Override // io.sentry.hints.f
    public final boolean d() {
        try {
            return this.f16610n.await(this.o, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e10) {
            Thread.currentThread().interrupt();
            this.f16611p.s(EnumC1495p1.ERROR, "Exception while awaiting on lock.", e10);
            return false;
        }
    }

    @Override // io.sentry.hints.g
    public final void e(boolean z6) {
        this.l = z6;
    }
}
