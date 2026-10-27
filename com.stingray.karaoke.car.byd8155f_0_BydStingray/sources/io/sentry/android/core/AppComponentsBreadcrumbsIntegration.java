package io.sentry.android.core;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import io.sentry.C1457d;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class AppComponentsBreadcrumbsIntegration implements io.sentry.Z, Closeable, ComponentCallbacks2 {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.E f16037m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public SentryAndroidOptions f16038n;

    public AppComponentsBreadcrumbsIntegration(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
    }

    public final void b(long j3, Integer num) {
        if (this.f16037m != null) {
            C1457d c1457d = new C1457d(j3);
            if (num != null) {
                if (num.intValue() < 40) {
                    return;
                } else {
                    c1457d.c(num, "level");
                }
            }
            c1457d.o = "system";
            c1457d.f16515q = "device.event";
            c1457d.f16513n = "Low memory";
            c1457d.c("LOW_MEMORY", "action");
            c1457d.f16517s = EnumC1495p1.WARNING;
            this.f16037m.e(c1457d);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            this.l.unregisterComponentCallbacks(this);
        } catch (Throwable th) {
            SentryAndroidOptions sentryAndroidOptions = this.f16038n;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().p(EnumC1495p1.DEBUG, th, "It was not possible to unregisterComponentCallbacks", new Object[0]);
            }
        }
        SentryAndroidOptions sentryAndroidOptions2 = this.f16038n;
        if (sentryAndroidOptions2 != null) {
            sentryAndroidOptions2.getLogger().k(EnumC1495p1.DEBUG, "AppComponentsBreadcrumbsIntegration removed.", new Object[0]);
        }
    }

    public final void g(Runnable runnable) {
        SentryAndroidOptions sentryAndroidOptions = this.f16038n;
        if (sentryAndroidOptions != null) {
            try {
                sentryAndroidOptions.getExecutorService().submit(runnable);
            } catch (Throwable th) {
                this.f16038n.getLogger().p(EnumC1495p1.ERROR, th, "Failed to submit app components breadcrumb task", new Object[0]);
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        this.f16037m = io.sentry.E.f15834a;
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16038n = sentryAndroidOptions;
        io.sentry.K logger = sentryAndroidOptions.getLogger();
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        logger.k(enumC1495p1, "AppComponentsBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.f16038n.isEnableAppComponentBreadcrumbs()));
        if (this.f16038n.isEnableAppComponentBreadcrumbs()) {
            try {
                this.l.registerComponentCallbacks(this);
                f12.getLogger().k(enumC1495p1, "AppComponentsBreadcrumbsIntegration installed.", new Object[0]);
                n7.z.c("AppComponentsBreadcrumbs");
            } catch (Throwable th) {
                this.f16038n.setEnableAppComponentBreadcrumbs(false);
                f12.getLogger().p(EnumC1495p1.INFO, th, "ComponentCallbacks2 is not available.", new Object[0]);
            }
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        g(new RunnableC1447x(this, System.currentTimeMillis(), configuration));
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        g(new Runnable() { // from class: io.sentry.android.core.w
            @Override // java.lang.Runnable
            public final void run() {
                this.l.b(jCurrentTimeMillis, null);
            }
        });
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(final int i7) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        g(new Runnable() { // from class: io.sentry.android.core.y
            @Override // java.lang.Runnable
            public final void run() {
                this.l.b(jCurrentTimeMillis, Integer.valueOf(i7));
            }
        });
    }
}
