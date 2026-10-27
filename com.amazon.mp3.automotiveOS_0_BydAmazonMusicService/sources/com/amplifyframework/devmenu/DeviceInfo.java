package com.amplifyframework.devmenu;

import android.os.Build;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public final class DeviceInfo {
    public boolean isEmulator() {
        return Build.DEVICE.toLowerCase(Locale.getDefault()).contains("generic") && Build.MODEL.toLowerCase(Locale.getDefault()).contains("sdk");
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[5];
        objArr[0] = Build.MANUFACTURER;
        objArr[1] = Build.MODEL;
        objArr[2] = Build.VERSION.RELEASE;
        objArr[3] = Integer.valueOf(Build.VERSION.SDK_INT);
        objArr[4] = isEmulator() ? "Yes" : "No";
        return String.format(locale, "Device Manufacturer: %s\nDevice Model: %s\nAndroid System Version: %s\nSDK Version: %d\nDevice is an Emulator: %s", objArr);
    }
}
