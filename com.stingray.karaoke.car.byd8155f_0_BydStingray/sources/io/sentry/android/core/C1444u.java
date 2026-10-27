package io.sentry.android.core;

/* JADX INFO: renamed from: io.sentry.android.core.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1444u extends io.sentry.hints.c implements io.sentry.hints.b, io.sentry.hints.a {
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f16295p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f16296q;

    public C1444u(long j3, io.sentry.K k10, long j10, boolean z6, boolean z10) {
        super(j3, k10);
        this.o = j10;
        this.f16295p = z6;
        this.f16296q = z10;
    }

    @Override // io.sentry.hints.b
    public final boolean a() {
        return this.f16295p;
    }

    @Override // io.sentry.hints.a
    public final Long b() {
        return Long.valueOf(this.o);
    }

    @Override // io.sentry.hints.a
    public final boolean c() {
        return false;
    }

    @Override // io.sentry.hints.a
    public final String e() {
        return this.f16296q ? "anr_background" : "anr_foreground";
    }

    @Override // io.sentry.hints.c
    public final boolean f(io.sentry.protocol.t tVar) {
        return true;
    }

    @Override // io.sentry.hints.c
    public final void g(io.sentry.protocol.t tVar) {
    }
}
