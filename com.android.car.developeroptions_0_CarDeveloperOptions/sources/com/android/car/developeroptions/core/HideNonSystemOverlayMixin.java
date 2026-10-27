package com.android.car.developeroptions.core;

import android.app.Activity;
import android.os.Build;
import android.util.EventLog;
import android.view.Window;
import android.view.WindowManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/* JADX INFO: loaded from: classes.dex */
public class HideNonSystemOverlayMixin implements LifecycleObserver {
    private final Activity mActivity;

    public HideNonSystemOverlayMixin(Activity activity) {
        this.mActivity = activity;
    }

    boolean isEnabled() {
        return !Build.IS_DEBUGGABLE;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        if (this.mActivity == null || !isEnabled()) {
            return;
        }
        this.mActivity.getWindow().addSystemFlags(524288);
        EventLog.writeEvent(1397638484, "120484087", -1, "");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        if (this.mActivity == null || !isEnabled()) {
            return;
        }
        Window window = this.mActivity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.privateFlags &= -524289;
        window.setAttributes(attributes);
    }
}
