package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import io.sentry.F1;
import java.io.Closeable;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class CurrentActivityIntegration implements io.sentry.Z, Closeable, Application.ActivityLifecycleCallbacks {
    public final Application l;

    public CurrentActivityIntegration(Application application) {
        this.l = application;
    }

    public static void b(Activity activity) {
        B b10 = B.f16041b;
        WeakReference weakReference = (WeakReference) b10.f16042a;
        if (weakReference == null || weakReference.get() != activity) {
            b10.f16042a = new WeakReference(activity);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.l.unregisterActivityLifecycleCallbacks(this);
        B.f16041b.f16042a = null;
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        this.l.registerActivityLifecycleCallbacks(this);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        b(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        B b10 = B.f16041b;
        WeakReference weakReference = (WeakReference) b10.f16042a;
        if ((weakReference != null ? (Activity) weakReference.get() : null) == activity) {
            b10.f16042a = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        B b10 = B.f16041b;
        WeakReference weakReference = (WeakReference) b10.f16042a;
        if ((weakReference != null ? (Activity) weakReference.get() : null) == activity) {
            b10.f16042a = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        b(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        b(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        B b10 = B.f16041b;
        WeakReference weakReference = (WeakReference) b10.f16042a;
        if ((weakReference != null ? (Activity) weakReference.get() : null) == activity) {
            b10.f16042a = null;
        }
    }
}
