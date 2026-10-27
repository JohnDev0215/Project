package com.byd.auto_emanual.util;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public class SharedPrefUtil {
    private static final String NAME = "preference";

    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(NAME, 0);
    }

    public static void save(Context context, String key, String value) {
        getPref(context).edit().putString(key, value).apply();
    }

    public static String load(Context context, String key) {
        return getPref(context).getString(key, "");
    }

    public static void remove(Context context, String key) {
        getPref(context).edit().remove(key).apply();
    }

    public static void clear(Context context) {
        getPref(context).edit().clear().apply();
    }

    public static boolean contains(Context context, String key) {
        return getPref(context).contains(key);
    }
}
