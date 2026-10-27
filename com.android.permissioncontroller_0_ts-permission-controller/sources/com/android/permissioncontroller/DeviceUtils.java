package com.android.permissioncontroller;

import android.content.Context;

/* JADX INFO: loaded from: classes8.dex */
public class DeviceUtils {
    public static boolean isTelevision(Context context) {
        int uiMode = context.getResources().getConfiguration().uiMode;
        return (uiMode & 15) == 4;
    }

    public static boolean isWear(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    public static boolean isAuto(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }
}
