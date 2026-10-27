package io.sentry.android.core;

import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.Q0;
import io.sentry.R0;
import java.io.Closeable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
final class SendCachedEnvelopeIntegration implements io.sentry.Z, io.sentry.G, Closeable {
    public final R0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.util.d f16096m;
    public io.sentry.H o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public io.sentry.E f16098p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public SentryAndroidOptions f16099q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Q0 f16100r;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AtomicBoolean f16097n = new AtomicBoolean(false);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AtomicBoolean f16101s = new AtomicBoolean(false);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final AtomicBoolean f16102t = new AtomicBoolean(false);

    public SendCachedEnvelopeIntegration(R0 r02, io.sentry.util.d dVar) {
        this.l = r02;
        this.f16096m = dVar;
    }

    public final synchronized void b(io.sentry.E e10, SentryAndroidOptions sentryAndroidOptions) {
        try {
            try {
                Future futureSubmit = sentryAndroidOptions.getExecutorService().submit(new T(this, sentryAndroidOptions, e10, 0));
                if (((Boolean) this.f16096m.a()).booleanValue() && this.f16097n.compareAndSet(false, true)) {
                    sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "Startup Crash marker exists, blocking flush.", new Object[0]);
                    try {
                        futureSubmit.get(sentryAndroidOptions.getStartupCrashFlushTimeoutMillis(), TimeUnit.MILLISECONDS);
                    } catch (TimeoutException unused) {
                        sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "Synchronous send timed out, continuing in the background.", new Object[0]);
                    }
                }
                sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "SendCachedEnvelopeIntegration installed.", new Object[0]);
            } catch (Throwable th) {
                throw th;
            }
        } catch (RejectedExecutionException e11) {
            sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Failed to call the executor. Cached events will not be sent. Did you call Sentry.close()?", e11);
        } catch (Throwable th2) {
            sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Failed to call the executor. Cached events will not be sent", th2);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f16102t.set(true);
        io.sentry.H h10 = this.o;
        if (h10 != null) {
            h10.p(this);
        }
    }

    @Override // io.sentry.G
    public final void i(io.sentry.F f10) {
        SentryAndroidOptions sentryAndroidOptions;
        io.sentry.E e10 = this.f16098p;
        if (e10 == null || (sentryAndroidOptions = this.f16099q) == null) {
            return;
        }
        b(e10, sentryAndroidOptions);
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        io.sentry.E e10 = io.sentry.E.f15834a;
        this.f16098p = e10;
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16099q = sentryAndroidOptions;
        String cacheDirPath = f12.getCacheDirPath();
        io.sentry.K logger = f12.getLogger();
        this.l.getClass();
        if (!R0.b(cacheDirPath, logger)) {
            f12.getLogger().k(EnumC1495p1.ERROR, "No cache dir path is defined in options.", new Object[0]);
        } else {
            n7.z.c("SendCachedEnvelope");
            b(e10, this.f16099q);
        }
    }
}
