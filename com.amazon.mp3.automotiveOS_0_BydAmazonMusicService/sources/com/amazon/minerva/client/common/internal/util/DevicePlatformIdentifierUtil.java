package com.amazon.minerva.client.common.internal.util;

import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public class DevicePlatformIdentifierUtil {
    private static DevicePlatformIdentifierUtil sDevicePlatformIdentifierUtil;
    private final boolean mIsDevicePlatformFireOS = "Amazon".equalsIgnoreCase(Build.MANUFACTURER);

    private DevicePlatformIdentifierUtil() {
    }

    public static DevicePlatformIdentifierUtil getInstance() {
        if (sDevicePlatformIdentifierUtil == null) {
            sDevicePlatformIdentifierUtil = new DevicePlatformIdentifierUtil();
        }
        return sDevicePlatformIdentifierUtil;
    }

    public boolean isDevicePlatformFireOS() {
        return this.mIsDevicePlatformFireOS;
    }
}
