package com.facebook.react.modules.systeminfo;

import android.os.Build;

/* JADX INFO: loaded from: classes6.dex */
public class AndroidInfoHelpers {
    private static boolean isRunningOnGenymotion() {
        return Build.FINGERPRINT.contains("vbox");
    }

    public static String getFriendlyDeviceName() {
        if (isRunningOnGenymotion()) {
            return Build.MODEL;
        }
        return Build.MODEL + " - " + Build.VERSION.RELEASE + " - API " + Build.VERSION.SDK_INT;
    }
}
