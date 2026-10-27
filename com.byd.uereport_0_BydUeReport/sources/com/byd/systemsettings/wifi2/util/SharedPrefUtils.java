package com.byd.systemsettings.wifi2.util;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class SharedPrefUtils {
    private static final String TAG = "Cache_SharedPref";

    public static void clearSP(Context context) {
        context.getSharedPreferences(TAG, 0).edit().clear().apply();
    }

    public static String getSP(Context context, String str) {
        return context.getSharedPreferences(TAG, 0).getString(str, "");
    }

    public static void putSP(Context context, String str, String str2) {
        context.getSharedPreferences(TAG, 0).edit().putString(str, str2).apply();
    }
}
