package com.android.launcher3.home.pageutil;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.launcher3.home.Constants;
import com.android.launcher3.util.Logger;

/* JADX INFO: loaded from: classes4.dex */
public class PreferencesUtils {
    public static String PREFERENCE_NAME = "WallPaperCommon";

    private PreferencesUtils() {
        throw new AssertionError();
    }

    public static boolean putString(Context context, String key, String value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        editorEdit.putString(key, value);
        return editorEdit.commit();
    }

    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    public static String getString(Context context, String key, String defaultValue) {
        return context.getSharedPreferences(PREFERENCE_NAME, 0).getString(key, defaultValue);
    }

    public static boolean putInt(Context context, String key, int value, int flag) {
        if (key.equals(Constants.WALLPAPER_TYPE)) {
            Logger.e("pengbin", "putInt......" + value + " === " + flag);
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        editorEdit.putInt(key, value);
        return editorEdit.commit();
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return context.getSharedPreferences(PREFERENCE_NAME, 0).getInt(key, defaultValue);
    }

    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        editorEdit.putLong(key, value);
        return editorEdit.commit();
    }

    public static long getLong(Context context, String key) {
        return getLong(context, key, -1L);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return context.getSharedPreferences(PREFERENCE_NAME, 0).getLong(key, defaultValue);
    }

    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        editorEdit.putFloat(key, value);
        return editorEdit.commit();
    }

    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1.0f);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return context.getSharedPreferences(PREFERENCE_NAME, 0).getFloat(key, defaultValue);
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        editorEdit.putBoolean(key, value);
        return editorEdit.commit();
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return context.getSharedPreferences(PREFERENCE_NAME, 0).getBoolean(key, defaultValue);
    }
}
