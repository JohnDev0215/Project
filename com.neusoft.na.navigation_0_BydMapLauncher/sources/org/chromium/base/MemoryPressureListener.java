package org.chromium.base;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;

/* JADX INFO: loaded from: classes3.dex */
public class MemoryPressureListener {
    private static final String ACTION_LOW_MEMORY = "org.chromium.base.ACTION_LOW_MEMORY";
    private static final String ACTION_TRIM_MEMORY = "org.chromium.base.ACTION_TRIM_MEMORY";
    private static final String ACTION_TRIM_MEMORY_MODERATE = "org.chromium.base.ACTION_TRIM_MEMORY_MODERATE";
    private static final String ACTION_TRIM_MEMORY_RUNNING_CRITICAL = "org.chromium.base.ACTION_TRIM_MEMORY_RUNNING_CRITICAL";

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnMemoryPressure(int i);

    private static void registerSystemCallback(Context context) {
        context.registerComponentCallbacks(new ComponentCallbacks2() { // from class: org.chromium.base.MemoryPressureListener.1
            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration configuration) {
            }

            @Override // android.content.ComponentCallbacks2
            public void onTrimMemory(int i) {
                MemoryPressureListener.maybeNotifyMemoryPresure(i);
            }

            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
                MemoryPressureListener.nativeOnMemoryPressure(2);
            }
        });
    }

    public static boolean handleDebugIntent(Activity activity, String str) {
        if (ACTION_LOW_MEMORY.equals(str)) {
            simulateLowMemoryPressureSignal(activity);
            return true;
        }
        if (ACTION_TRIM_MEMORY.equals(str)) {
            simulateTrimMemoryPressureSignal(activity, 80);
            return true;
        }
        if (ACTION_TRIM_MEMORY_RUNNING_CRITICAL.equals(str)) {
            simulateTrimMemoryPressureSignal(activity, 15);
            return true;
        }
        if (!ACTION_TRIM_MEMORY_MODERATE.equals(str)) {
            return false;
        }
        simulateTrimMemoryPressureSignal(activity, 60);
        return true;
    }

    public static void maybeNotifyMemoryPresure(int i) {
        if (i >= 80) {
            nativeOnMemoryPressure(2);
        } else if (i >= 40 || i == 15) {
            nativeOnMemoryPressure(1);
        }
    }

    private static void simulateLowMemoryPressureSignal(Activity activity) {
        activity.getApplication().onLowMemory();
        activity.onLowMemory();
    }

    private static void simulateTrimMemoryPressureSignal(Activity activity, int i) {
        activity.getApplication().onTrimMemory(i);
        activity.onTrimMemory(i);
    }
}
