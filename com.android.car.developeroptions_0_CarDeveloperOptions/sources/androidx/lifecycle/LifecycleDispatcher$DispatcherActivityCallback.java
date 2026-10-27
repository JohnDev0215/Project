package androidx.lifecycle;

import android.app.Activity;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
class LifecycleDispatcher$DispatcherActivityCallback extends EmptyActivityLifecycleCallbacks {
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    LifecycleDispatcher$DispatcherActivityCallback() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        ReportFragment.injectIfNeededIn(activity);
    }
}
