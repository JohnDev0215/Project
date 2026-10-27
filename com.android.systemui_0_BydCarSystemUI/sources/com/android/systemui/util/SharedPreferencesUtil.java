package com.android.systemui.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.tencent.mmkv.MMKV;

/* JADX INFO: loaded from: classes.dex */
public class SharedPreferencesUtil {
    private static SharedPreferences sharedPreferences;
    private static MMKV sMMKV = MMKV.mmkvWithID("sys_ui_config");
    private static String FILE_NAME = "sys_ui_config";

    public static void putBoolean(Context context, String key, Boolean value) {
        BydLogHelper.logD("putBoolean key : " + key + " -- value : " + value + " -- context : " + context);
        sMMKV.encode(key, value.booleanValue());
    }

    public static boolean getBoolean(Context context, String key, Boolean value) {
        return sMMKV.decodeBool(key, value.booleanValue());
    }

    public static void putString(Context context, String key, String value) {
        sMMKV.encode(key, value);
    }

    public static String getString(Context context, String key, String defValue) {
        return sMMKV.decodeString(key, defValue);
    }

    public static void putInt(Context context, String key, int value) {
        sMMKV.encode(key, value);
    }

    public static int getInt(Context context, String key, int defValue) {
        return sMMKV.decodeInt(key, defValue);
    }

    public static void remove(Context context, String key) {
        sMMKV.remove(key);
    }
}
