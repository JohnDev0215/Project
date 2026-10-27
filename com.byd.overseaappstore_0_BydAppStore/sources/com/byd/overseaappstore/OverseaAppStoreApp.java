package com.byd.overseaappstore;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.byd.overseaappstore.ui.MainActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.e;
import spi.s;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/OverseaAppStoreApp;", "Landroid/app/Application;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class OverseaAppStoreApp extends Hilt_OverseaAppStoreApp implements Application.ActivityLifecycleCallbacks {
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (!(activity instanceof MainActivity)) {
            ct.b(e.d("OverseaAppStoreApp onActivityCreated class:", activity.getClass().getSimpleName(), " no intervene"), new Object[0]);
        } else {
            s.a.a.a().setCreate(true);
            ct.a(e.d("OverseaAppStoreApp onActivityCreated class:", activity.getClass().getSimpleName(), " "), new Object[0]);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (!(activity instanceof MainActivity)) {
            ct.b(e.d("OverseaAppStoreApp onActivityDestroyed class:", activity.getClass().getSimpleName(), " no intervene"), new Object[0]);
        } else {
            s.a.a.a().setDestroy(true);
            ct.a(e.d("OverseaAppStoreApp onActivityDestroyed class:", activity.getClass().getSimpleName(), " "), new Object[0]);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ct.a("OverseaAppStoreApp onActivityPaused", new Object[0]);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        s.a.a.a.setResume(true);
        s.a.a.a.setStop(false);
        s.a.a.a.setSaveInstanceState(false);
        ct.a("OverseaAppStoreApp onActivityResumed", new Object[0]);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle outState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(outState, "outState");
        s.a.a.a.setResume(false);
        s.a.a.a.setStop(true);
        s.a.a.a.setSaveInstanceState(true);
        ct.a("OverseaAppStoreApp onActivitySaveInstanceState", new Object[0]);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ct.a("OverseaAppStoreApp onActivityStarted", new Object[0]);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        s.a.a.a.setResume(false);
        s.a.a.a.setStop(true);
        ct.a("OverseaAppStoreApp onActivityStopped", new Object[0]);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.byd.overseaappstore.Hilt_OverseaAppStoreApp, android.app.Application
    public final void onCreate() {
        super.onCreate();
        ct.b bVar = new ct.b();
        List<ct.c> list = ct.a;
        if (bVar == ct.c) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        }
        List<ct.c> list2 = ct.a;
        synchronized (list2) {
            list2.add(bVar);
            ct.b = (ct.c[]) list2.toArray(new ct.c[list2.size()]);
        }
        registerActivityLifecycleCallbacks(this);
    }
}
