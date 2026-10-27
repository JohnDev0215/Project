package com.amazon.client.metrics.thirdparty.utils;

import android.app.UiModeManager;
import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceCategoryUtil {
    private static String sDeviceCategory;

    public static String getDeviceCategory(Context context) {
        if (sDeviceCategory == null) {
            if (((UiModeManager) context.getSystemService("uimode")).getCurrentModeType() == 4) {
                sDeviceCategory = "SetTopBox";
            } else if (context.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
                sDeviceCategory = "Smartphone";
            } else {
                sDeviceCategory = "Tablet";
            }
        }
        return sDeviceCategory;
    }
}
