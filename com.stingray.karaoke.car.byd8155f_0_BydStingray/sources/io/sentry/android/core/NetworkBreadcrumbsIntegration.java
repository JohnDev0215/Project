package io.sentry.android.core;

import android.content.Context;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkBreadcrumbsIntegration implements io.sentry.Z, Closeable {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final B f16078m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.K f16079n;
    public final Object o = new Object();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile boolean f16080p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public F1 f16081q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile O f16082r;

    public NetworkBreadcrumbsIntegration(Context context, io.sentry.K k10, B b10) {
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
        this.f16078m = b10;
        h6.e.q(k10, "ILogger is required");
        this.f16079n = k10;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f16080p = true;
        try {
            F1 f12 = this.f16081q;
            h6.e.q(f12, "Options is required");
            f12.getExecutorService().submit(new A2.l(this, 18));
        } catch (Throwable th) {
            this.f16079n.s(EnumC1495p1.ERROR, "Error submitting NetworkBreadcrumbsIntegration task.", th);
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        Object[] objArr = {Boolean.valueOf(sentryAndroidOptions.isEnableNetworkEventBreadcrumbs())};
        io.sentry.K k10 = this.f16079n;
        k10.k(enumC1495p1, "NetworkBreadcrumbsIntegration enabled: %s", objArr);
        this.f16081q = f12;
        if (sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()) {
            this.f16078m.getClass();
            try {
                f12.getExecutorService().submit(new M(this, 0, f12));
            } catch (Throwable th) {
                k10.s(EnumC1495p1.ERROR, "Error submitting NetworkBreadcrumbsIntegration task.", th);
            }
        }
    }
}
