package io.sentry.android.core;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.ProcessLifecycleOwner;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class AppLifecycleIntegration implements io.sentry.Z, Closeable {
    public volatile L l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16039m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final B f16040n = new B();

    public final void b() {
        SentryAndroidOptions sentryAndroidOptions = this.f16039m;
        if (sentryAndroidOptions == null) {
            return;
        }
        this.l = new L(sentryAndroidOptions.getSessionTrackingIntervalMillis(), this.f16039m.isEnableAutoSessionTracking(), this.f16039m.isEnableAppLifecycleBreadcrumbs());
        try {
            ProcessLifecycleOwner.f11939s.f11943q.M0(this.l);
            this.f16039m.getLogger().k(EnumC1495p1.DEBUG, "AppLifecycleIntegration installed.", new Object[0]);
            n7.z.c("AppLifecycle");
        } catch (Throwable th) {
            this.l = null;
            this.f16039m.getLogger().s(EnumC1495p1.ERROR, "AppLifecycleIntegration failed to get Lifecycle and could not be installed.", th);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.l == null) {
            return;
        }
        if (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()) {
            g();
            return;
        }
        B b10 = this.f16040n;
        ((Handler) b10.f16042a).post(new RunnableC1449z(this, 0));
    }

    public final void g() {
        L l = this.l;
        if (l != null) {
            ProcessLifecycleOwner.f11939s.f11943q.V0(l);
            SentryAndroidOptions sentryAndroidOptions = this.f16039m;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "AppLifecycleIntegration removed.", new Object[0]);
            }
        }
        this.l = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0084 -> B:22:0x009b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0090 -> B:22:0x009b). Please report as a decompilation issue!!! */
    @Override // io.sentry.Z
    public final void k(F1 f12) {
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16039m = sentryAndroidOptions;
        io.sentry.K logger = sentryAndroidOptions.getLogger();
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        logger.k(enumC1495p1, "enableSessionTracking enabled: %s", Boolean.valueOf(this.f16039m.isEnableAutoSessionTracking()));
        this.f16039m.getLogger().k(enumC1495p1, "enableAppLifecycleBreadcrumbs enabled: %s", Boolean.valueOf(this.f16039m.isEnableAppLifecycleBreadcrumbs()));
        if (this.f16039m.isEnableAutoSessionTracking() || this.f16039m.isEnableAppLifecycleBreadcrumbs()) {
            try {
                ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.f11939s;
                if (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()) {
                    b();
                    f12 = f12;
                } else {
                    ((Handler) this.f16040n.f16042a).post(new RunnableC1449z(this, 1));
                    f12 = f12;
                }
            } catch (ClassNotFoundException e10) {
                io.sentry.K logger2 = f12.getLogger();
                logger2.s(EnumC1495p1.INFO, "androidx.lifecycle is not available, AppLifecycleIntegration won't be installed", e10);
                f12 = logger2;
            } catch (IllegalStateException e11) {
                io.sentry.K logger3 = f12.getLogger();
                logger3.s(EnumC1495p1.ERROR, "AppLifecycleIntegration could not be installed", e11);
                f12 = logger3;
            }
        }
    }
}
