package io.sentry.android.core;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import io.sentry.C1521w0;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends io.sentry.android.core.performance.a {
    public final WeakHashMap l = new WeakHashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ io.sentry.android.core.performance.e f16124m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AtomicBoolean f16125n;
    public final /* synthetic */ SentryPerformanceProvider o;

    public Y(SentryPerformanceProvider sentryPerformanceProvider, io.sentry.android.core.performance.e eVar, AtomicBoolean atomicBoolean) {
        this.o = sentryPerformanceProvider;
        this.f16124m = eVar;
        this.f16125n = atomicBoolean;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        io.sentry.android.core.performance.e eVar = this.f16124m;
        if (eVar.l == io.sentry.android.core.performance.d.UNKNOWN) {
            eVar.l = bundle == null ? io.sentry.android.core.performance.d.COLD : io.sentry.android.core.performance.d.WARM;
        }
    }

    @Override // io.sentry.android.core.performance.a, android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.l.remove(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostCreated(Activity activity, Bundle bundle) {
        io.sentry.android.core.performance.b bVar;
        if (this.f16124m.f16278n.c() || (bVar = (io.sentry.android.core.performance.b) this.l.get(activity)) == null) {
            return;
        }
        io.sentry.android.core.performance.f fVar = bVar.l;
        fVar.e();
        fVar.l = activity.getClass().getName().concat(".onCreate");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostStarted(Activity activity) {
        io.sentry.android.core.performance.b bVar = (io.sentry.android.core.performance.b) this.l.remove(activity);
        io.sentry.android.core.performance.e eVar = this.f16124m;
        if (eVar.f16278n.c() || bVar == null) {
            return;
        }
        io.sentry.android.core.performance.f fVar = bVar.f16272m;
        fVar.e();
        fVar.l = activity.getClass().getName().concat(".onStart");
        eVar.f16281r.add(bVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreCreated(Activity activity, Bundle bundle) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.f16124m.f16278n.c()) {
            return;
        }
        io.sentry.android.core.performance.b bVar = new io.sentry.android.core.performance.b();
        bVar.l.d(jUptimeMillis);
        this.l.put(activity, bVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreStarted(Activity activity) {
        io.sentry.android.core.performance.b bVar;
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.f16124m.f16278n.c() || (bVar = (io.sentry.android.core.performance.b) this.l.get(activity)) == null) {
            return;
        }
        bVar.f16272m.d(jUptimeMillis);
    }

    @Override // io.sentry.android.core.performance.a, android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        AtomicBoolean atomicBoolean = this.f16125n;
        if (atomicBoolean.get()) {
            return;
        }
        r rVar = new r(this, 2, atomicBoolean);
        B b10 = new B(C1521w0.l);
        Window window = activity.getWindow();
        if (window != null) {
            View viewPeekDecorView = window.peekDecorView();
            if (viewPeekDecorView != null) {
                viewPeekDecorView.getViewTreeObserver().addOnDrawListener(new io.sentry.android.core.internal.util.f(viewPeekDecorView, rVar));
            } else {
                Window.Callback callback = window.getCallback();
                window.setCallback(new io.sentry.android.core.performance.g(callback != null ? callback : new io.sentry.android.core.internal.gestures.b(), new T1.k(window, callback, rVar, b10)));
            }
        }
    }
}
