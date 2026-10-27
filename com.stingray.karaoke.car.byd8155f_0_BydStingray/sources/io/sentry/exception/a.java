package io.sentry.exception;

import h6.e;
import io.sentry.protocol.j;

/* JADX INFO: loaded from: classes.dex */
public final class a extends RuntimeException {
    public final j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Throwable f16529m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Thread f16530n;
    public final boolean o;

    public a(j jVar, Throwable th, Thread thread, boolean z6) {
        this.l = jVar;
        e.q(th, "Throwable is required.");
        this.f16529m = th;
        e.q(thread, "Thread is required.");
        this.f16530n = thread;
        this.o = z6;
    }
}
