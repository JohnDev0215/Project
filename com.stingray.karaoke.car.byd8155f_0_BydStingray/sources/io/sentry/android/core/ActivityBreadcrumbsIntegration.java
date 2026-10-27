package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import io.sentry.C1457d;
import io.sentry.C1523x;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityBreadcrumbsIntegration implements io.sentry.Z, Closeable, Application.ActivityLifecycleCallbacks {
    public final Application l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.E f16015m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f16016n;

    public ActivityBreadcrumbsIntegration(Application application) {
        this.l = application;
    }

    public final void b(Activity activity, String str) {
        if (this.f16015m == null) {
            return;
        }
        C1457d c1457d = new C1457d();
        c1457d.o = "navigation";
        c1457d.c(str, "state");
        c1457d.c(activity.getClass().getSimpleName(), "screen");
        c1457d.f16515q = "ui.lifecycle";
        c1457d.f16517s = EnumC1495p1.INFO;
        C1523x c1523x = new C1523x();
        c1523x.c(activity, "android:activity");
        this.f16015m.k(c1457d, c1523x);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f16016n) {
            this.l.unregisterActivityLifecycleCallbacks(this);
            io.sentry.E e10 = this.f16015m;
            if (e10 != null) {
                e10.q().getLogger().k(EnumC1495p1.DEBUG, "ActivityBreadcrumbsIntegration removed.", new Object[0]);
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        io.sentry.E e10 = io.sentry.E.f15834a;
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16015m = e10;
        this.f16016n = sentryAndroidOptions.isEnableActivityLifecycleBreadcrumbs();
        io.sentry.K logger = f12.getLogger();
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        logger.k(enumC1495p1, "ActivityBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.f16016n));
        if (this.f16016n) {
            this.l.registerActivityLifecycleCallbacks(this);
            f12.getLogger().k(enumC1495p1, "ActivityBreadcrumbIntegration installed.", new Object[0]);
            n7.z.c("ActivityBreadcrumbs");
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityCreated(Activity activity, Bundle bundle) {
        b(activity, "created");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityDestroyed(Activity activity) {
        b(activity, "destroyed");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityPaused(Activity activity) {
        b(activity, "paused");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityResumed(Activity activity) {
        b(activity, "resumed");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        b(activity, "saveInstanceState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityStarted(Activity activity) {
        b(activity, "started");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityStopped(Activity activity) {
        b(activity, "stopped");
    }
}
