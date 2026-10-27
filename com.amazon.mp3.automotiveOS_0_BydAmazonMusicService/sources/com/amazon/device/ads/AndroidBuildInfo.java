package com.amazon.device.ads;

import android.os.Build;

/* JADX INFO: loaded from: classes2.dex */
class AndroidBuildInfo {
    private String make = Build.MANUFACTURER;
    private String model = Build.MODEL;
    private String osVersion = Build.VERSION.RELEASE;
    private int sdkInt = Build.VERSION.SDK_INT;

    AndroidBuildInfo() {
    }

    public int getSDKInt() {
        return this.sdkInt;
    }
}
