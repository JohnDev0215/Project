package p;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class p4 implements Application.ActivityLifecycleCallbacks {
    public Object c;
    public Activity e;
    public final int k;
    public boolean l = false;
    public boolean m = false;
    public boolean n = false;

    public p4(Activity activity) {
        this.e = activity;
        this.k = activity.hashCode();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (this.e == activity) {
            this.e = null;
            this.m = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (!this.m || this.n || this.l) {
            return;
        }
        Object obj = this.c;
        try {
            Object obj2 = q4.c.get(activity);
            if (obj2 == obj && activity.hashCode() == this.k) {
                q4.g.postAtFrontOfQueue(new yf1(q4.b.get(activity), obj2, 4));
                this.n = true;
                this.c = null;
            }
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        if (this.e == activity) {
            this.l = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
