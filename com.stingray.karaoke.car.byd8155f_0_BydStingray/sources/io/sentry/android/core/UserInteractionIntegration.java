package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Window;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.W1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class UserInteractionIntegration implements io.sentry.Z, Closeable, Application.ActivityLifecycleCallbacks {
    public final Application l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.E f16119m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public SentryAndroidOptions f16120n;
    public final boolean o = h6.j.m(this.f16120n, "androidx.core.view.GestureDetectorCompat");

    public UserInteractionIntegration(Application application) {
        this.l = application;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.l.unregisterActivityLifecycleCallbacks(this);
        SentryAndroidOptions sentryAndroidOptions = this.f16120n;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "UserInteractionIntegration removed.", new Object[0]);
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        io.sentry.E e10 = io.sentry.E.f15834a;
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16120n = sentryAndroidOptions;
        this.f16119m = e10;
        boolean z6 = sentryAndroidOptions.isEnableUserInteractionBreadcrumbs() || this.f16120n.isEnableUserInteractionTracing();
        io.sentry.K logger = this.f16120n.getLogger();
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        logger.k(enumC1495p1, "UserInteractionIntegration enabled: %s", Boolean.valueOf(z6));
        if (z6) {
            if (!this.o) {
                f12.getLogger().k(EnumC1495p1.INFO, "androidx.core is not available, UserInteractionIntegration won't be installed", new Object[0]);
                return;
            }
            this.l.registerActivityLifecycleCallbacks(this);
            this.f16120n.getLogger().k(enumC1495p1, "UserInteractionIntegration installed.", new Object[0]);
            n7.z.c("UserInteraction");
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            SentryAndroidOptions sentryAndroidOptions = this.f16120n;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().k(EnumC1495p1.INFO, "Window was null in stopTracking", new Object[0]);
                return;
            }
            return;
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof io.sentry.android.core.internal.gestures.g) {
            io.sentry.android.core.internal.gestures.g gVar = (io.sentry.android.core.internal.gestures.g) callback;
            gVar.f16189n.e(W1.CANCELLED);
            Window.Callback callback2 = gVar.f16188m;
            if (callback2 instanceof io.sentry.android.core.internal.gestures.b) {
                window.setCallback(null);
            } else {
                window.setCallback(callback2);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            SentryAndroidOptions sentryAndroidOptions = this.f16120n;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().k(EnumC1495p1.INFO, "Window was null in startTracking", new Object[0]);
                return;
            }
            return;
        }
        if (this.f16119m == null || this.f16120n == null) {
            return;
        }
        Window.Callback callback = window.getCallback();
        if (callback == null) {
            callback = new io.sentry.android.core.internal.gestures.b();
        }
        window.setCallback(new io.sentry.android.core.internal.gestures.g(callback, activity, new io.sentry.android.core.internal.gestures.f(activity, this.f16119m, this.f16120n), this.f16120n));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
