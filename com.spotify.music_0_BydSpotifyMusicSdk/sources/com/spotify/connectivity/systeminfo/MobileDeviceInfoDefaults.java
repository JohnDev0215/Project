package com.spotify.connectivity.systeminfo;

import android.os.Build;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.core.core.DeviceType;

/* JADX INFO: loaded from: classes.dex */
public final class MobileDeviceInfoDefaults {
    private MobileDeviceInfoDefaults() {
    }

    public static MobileDeviceInfo defaults() {
        MobileDeviceInfo mobileDeviceInfo = new MobileDeviceInfo();
        mobileDeviceInfo.setOsVersion(Build.VERSION.RELEASE);
        mobileDeviceInfo.setApiLevel(Build.VERSION.SDK_INT);
        String str = Build.MODEL;
        mobileDeviceInfo.setName(str);
        mobileDeviceInfo.setModel(str);
        mobileDeviceInfo.setBrand(Build.BRAND);
        mobileDeviceInfo.setManufacturer(Build.MANUFACTURER);
        mobileDeviceInfo.setDeviceType(DeviceType.SMARTPHONE.getValue());
        return mobileDeviceInfo;
    }
}
