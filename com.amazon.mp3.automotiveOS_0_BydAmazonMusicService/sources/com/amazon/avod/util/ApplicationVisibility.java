package com.amazon.avod.util;

/* JADX INFO: loaded from: classes2.dex */
public enum ApplicationVisibility {
    DEVICE_ACTIVE_IN_FOREGROUND(true, true),
    DEVICE_ACTIVE_IN_BACKGROUND(false, true),
    DEVICE_INACTIVE_IN_FOREGROUND(true, false),
    DEVICE_INACTIVE_IN_BACKGROUND(false, false);

    private final boolean mIsAppInForeground;
    private final boolean mIsDeviceActive;

    ApplicationVisibility(boolean z, boolean z2) {
        this.mIsAppInForeground = z;
        this.mIsDeviceActive = z2;
    }

    public boolean isDeviceActive() {
        return this.mIsDeviceActive;
    }

    public boolean isAppInForeground() {
        return this.mIsAppInForeground;
    }

    public static ApplicationVisibility fromValues(boolean z, boolean z2) {
        if (z && z2) {
            return DEVICE_ACTIVE_IN_FOREGROUND;
        }
        if (z && !z2) {
            return DEVICE_INACTIVE_IN_FOREGROUND;
        }
        if (!z && z2) {
            return DEVICE_ACTIVE_IN_BACKGROUND;
        }
        return DEVICE_INACTIVE_IN_BACKGROUND;
    }
}
