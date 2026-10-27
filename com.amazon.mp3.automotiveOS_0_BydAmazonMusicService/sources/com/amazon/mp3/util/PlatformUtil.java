package com.amazon.mp3.util;

import android.os.Build;
import com.amazon.mp3.AmazonApplication;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class PlatformUtil {
    public static final String TAG = "PlatformUtil";
    private static final Set<String> gen8And9Devices;

    static {
        HashSet hashSet = new HashSet();
        gen8And9Devices = hashSet;
        Collections.addAll(hashSet, "Thebes".toLowerCase(), "Memphis".toLowerCase(), "Ford".toLowerCase(), "Giza".toLowerCase());
    }

    public static boolean isVersionOrLower(final int versionCode) {
        return Build.VERSION.SDK_INT <= versionCode;
    }

    public static boolean isVersionOrGreater(final int versionCode) {
        return Build.VERSION.SDK_INT >= versionCode;
    }

    public static boolean isFireOS() {
        return AmazonApplication.getCapabilities().isFireOS(AmazonApplication.getContext());
    }

    public static boolean isFireOSGen10AndAbove() {
        if (!isFireOS()) {
            return true;
        }
        String lowerCase = Build.DEVICE.toLowerCase();
        if (!gen8And9Devices.contains(lowerCase)) {
            return true;
        }
        Log.debug(TAG, lowerCase + " FireTablet model is Gen8/Gen9 and does not support Videos");
        return false;
    }
}
