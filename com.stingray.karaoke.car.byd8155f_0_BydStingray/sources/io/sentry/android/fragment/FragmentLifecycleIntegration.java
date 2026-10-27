package io.sentry.android.fragment;

import A7.m;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import io.sentry.C1489n1;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.Z;
import java.io.Closeable;
import java.util.Set;
import kotlin.Metadata;
import n7.w;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B%\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fB\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\rB!\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\u000f¨\u0006\u0010"}, d2 = {"Lio/sentry/android/fragment/FragmentLifecycleIntegration;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "Lio/sentry/Z;", "Ljava/io/Closeable;", "Landroid/app/Application;", "application", "", "Lio/sentry/android/fragment/b;", "filterFragmentLifecycleBreadcrumbs", "", "enableAutoFragmentLifecycleTracing", "<init>", "(Landroid/app/Application;Ljava/util/Set;Z)V", "(Landroid/app/Application;)V", "enableFragmentLifecycleBreadcrumbs", "(Landroid/app/Application;ZZ)V", "sentry-android-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FragmentLifecycleIntegration implements Application.ActivityLifecycleCallbacks, Z, Closeable {
    public final Application l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public F1 f16303m;

    public FragmentLifecycleIntegration(Application application, Set<? extends b> set, boolean z6) {
        m.f(application, "application");
        m.f(set, "filterFragmentLifecycleBreadcrumbs");
        this.l = application;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.l.unregisterActivityLifecycleCallbacks(this);
        F1 f12 = this.f16303m;
        if (f12 != null) {
            if (f12 != null) {
                f12.getLogger().k(EnumC1495p1.DEBUG, "FragmentLifecycleIntegration removed.", new Object[0]);
            } else {
                m.j("options");
                throw null;
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        this.f16303m = f12;
        this.l.registerActivityLifecycleCallbacks(this);
        f12.getLogger().k(EnumC1495p1.DEBUG, "FragmentLifecycleIntegration installed.", new Object[0]);
        z.c("FragmentLifecycle");
        C1489n1.s().g("maven:io.sentry:sentry-android-fragment");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        m.f(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        m.f(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        m.f(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        m.f(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        m.f(activity, "activity");
        m.f(bundle, "outState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        m.f(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        m.f(activity, "activity");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FragmentLifecycleIntegration(Application application) {
        this(application, (Set<? extends b>) b.states, false);
        m.f(application, "application");
        b.Companion.getClass();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FragmentLifecycleIntegration(Application application, boolean z6, boolean z10) {
        m.f(application, "application");
        b.Companion.getClass();
        Set set = z6 ? b.states : null;
        this(application, (Set<? extends b>) (set == null ? w.l : set), z10);
    }
}
