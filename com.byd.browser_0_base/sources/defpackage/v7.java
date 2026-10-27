package defpackage;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Window;
import java.lang.reflect.Proxy;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: ApplicationStatus.java */
/* JADX INFO: loaded from: classes.dex */
public final class v7 implements Application.ActivityLifecycleCallbacks {
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        ApplicationStatus.a(activity, 1);
        activity.getWindow().setCallback((Window.Callback) Proxy.newProxyInstance(Window.Callback.class.getClassLoader(), new Class[]{Window.Callback.class}, new ApplicationStatus.f(activity, activity.getWindow().getCallback())));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        ApplicationStatus.a(activity, 6);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        ApplicationStatus.a(activity, 4);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        ApplicationStatus.a(activity, 3);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        ApplicationStatus.a(activity, 2);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        ApplicationStatus.a(activity, 5);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
