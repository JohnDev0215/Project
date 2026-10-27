package com.bugsnag.android;

import android.app.ActivityManager;
import android.content.Context;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
class ForegroundDetector {

    @Nullable
    private final ActivityManager activityManager;

    ForegroundDetector(Context context) {
        this.activityManager = ContextExtensionsKt.getActivityManagerFrom(context);
    }

    @Nullable
    Boolean isInForeground() {
        try {
            ActivityManager.RunningAppProcessInfo processInfo = getProcessInfo();
            if (processInfo != null) {
                return Boolean.valueOf(processInfo.importance <= 125);
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        }
    }

    private ActivityManager.RunningAppProcessInfo getProcessInfo() {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        return runningAppProcessInfo;
    }
}
