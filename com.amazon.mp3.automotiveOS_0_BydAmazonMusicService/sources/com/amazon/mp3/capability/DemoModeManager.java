package com.amazon.mp3.capability;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public class DemoModeManager {
    private static DemoModeManager mDemoModeManager;

    public String getDemoWarningString() {
        return "";
    }

    private DemoModeManager() {
    }

    public static DemoModeManager get(Context context) {
        if (mDemoModeManager == null) {
            mDemoModeManager = new DemoModeManager();
        }
        return mDemoModeManager;
    }

    public synchronized boolean isDemoMode() {
        return false;
    }
}
