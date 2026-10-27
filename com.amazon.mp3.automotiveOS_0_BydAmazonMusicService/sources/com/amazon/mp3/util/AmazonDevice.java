package com.amazon.mp3.util;

import android.content.pm.PackageManager;
import com.amazon.mp3.AmazonApplication;

/* JADX INFO: loaded from: classes4.dex */
public final class AmazonDevice {
    private static boolean sIsCanary = false;
    private static boolean sIsGrover = false;
    private static boolean sIsGroverCanarySet = false;

    private AmazonDevice() {
    }

    private static void setCanaryOrGrover() {
        if (sIsGroverCanarySet) {
            return;
        }
        PackageManager packageManager = AmazonApplication.getContext().getPackageManager();
        try {
            if (packageManager.getPackageInfo("com.amazon.fv", 0).versionCode < 2) {
                sIsGrover = false;
            } else {
                sIsGrover = true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            sIsGrover = false;
        }
        if (!sIsGrover) {
            try {
                packageManager.getPackageInfo("com.amazon.canary", 0);
                sIsCanary = true;
            } catch (PackageManager.NameNotFoundException unused2) {
                sIsCanary = false;
            }
        }
        sIsGroverCanarySet = true;
    }

    public static boolean isCanary() {
        setCanaryOrGrover();
        return sIsCanary;
    }

    public static boolean isGrover() {
        setCanaryOrGrover();
        return sIsGrover;
    }
}
