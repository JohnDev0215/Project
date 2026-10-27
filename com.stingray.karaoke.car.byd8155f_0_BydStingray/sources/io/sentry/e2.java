package io.sentry;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class e2 extends io.sentry.hints.c implements io.sentry.hints.h, io.sentry.hints.k {
    public final AtomicReference o;

    public e2(long j3, K k10) {
        super(j3, k10);
        this.o = new AtomicReference();
    }

    @Override // io.sentry.hints.c
    public final boolean f(io.sentry.protocol.t tVar) {
        io.sentry.protocol.t tVar2 = (io.sentry.protocol.t) this.o.get();
        return tVar2 != null && tVar2.equals(tVar);
    }

    @Override // io.sentry.hints.c
    public final void g(io.sentry.protocol.t tVar) {
        this.o.set(tVar);
    }
}
