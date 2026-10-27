package com.amazon.weblab.mobile.utils;

import android.preference.PreferenceManager;
import android.util.Log;
import com.amazon.weblab.mobile.metrics.ApplicationContextHolder;

/* JADX INFO: loaded from: classes5.dex */
public class TestUtils {
    private static volatile Boolean isUnderTestCached;

    public static boolean isUnderTest() {
        if (isUnderTestCached == null) {
            try {
                isUnderTestCached = Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(ApplicationContextHolder.getApplicationContext()).getBoolean("com.amazon.mobile.weblab.test.isTest", false));
            } catch (IllegalStateException unused) {
                return false;
            }
        }
        return isUnderTestCached.booleanValue();
    }

    public static void logDebugForAutomation(String str) {
        Log.d("MWAC", str);
    }
}
