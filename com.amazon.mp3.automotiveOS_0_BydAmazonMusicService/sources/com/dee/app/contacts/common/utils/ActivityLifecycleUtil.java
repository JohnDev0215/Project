package com.dee.app.contacts.common.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.dee.app.contacts.common.models.enums.ActivityLifecycle;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityLifecycleUtil implements Application.ActivityLifecycleCallbacks {
    private ActivityLifecycle activityLifecycle;
    private Activity mActivity;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    public ActivityLifecycleUtil(@NonNull Context context) {
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(this);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        this.mActivity = activity;
        this.activityLifecycle = ActivityLifecycle.ON_CREATE;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NonNull Activity activity) {
        this.mActivity = activity;
        this.activityLifecycle = ActivityLifecycle.ON_START;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NonNull Activity activity) {
        this.mActivity = activity;
        this.activityLifecycle = ActivityLifecycle.ON_RESUME;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NonNull Activity activity) {
        this.mActivity = activity;
        this.activityLifecycle = ActivityLifecycle.ON_PAUSE;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(@NonNull Activity activity) {
        this.mActivity = activity;
        this.activityLifecycle = ActivityLifecycle.ON_STOP;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NonNull Activity activity) {
        this.mActivity = null;
        this.activityLifecycle = ActivityLifecycle.ON_DESTROY;
    }
}
