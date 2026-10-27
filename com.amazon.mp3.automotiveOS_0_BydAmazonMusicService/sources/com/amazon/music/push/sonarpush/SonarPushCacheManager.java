package com.amazon.music.push.sonarpush;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class SonarPushCacheManager {
    private static final String TAG = "SonarPushCacheManager";

    public static synchronized void putString(Context context, String str, String str2) {
        getSharedPreferences(context).edit().putString(str, str2).apply();
    }

    public static synchronized void putBoolean(Context context, String str, boolean z) {
        getSharedPreferences(context).edit().putBoolean(str, z).apply();
    }

    public static synchronized void putInt(Context context, String str, int i) {
        getSharedPreferences(context).edit().putInt(str, i).apply();
    }

    public static synchronized void putMap(Context context, String str, Map<String, Boolean> map) {
        getSharedPreferences(context).edit().putString(str, new Gson().toJson(map)).apply();
    }

    public static synchronized Map<String, Boolean> getMap(Context context, String str) {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(context);
        try {
        } catch (Exception unused) {
            Log.w(TAG, "Error during retrieval of push subscription map from cache");
            return new HashMap();
        }
        return (Map) new Gson().fromJson(sharedPreferences.getString(str, ""), new TypeToken<Map<String, Boolean>>() { // from class: com.amazon.music.push.sonarpush.SonarPushCacheManager.1
        }.getType());
    }

    public static synchronized String getString(Context context, String str) {
        return getSharedPreferences(context).getString(str, "");
    }

    public static synchronized boolean getBoolean(Context context, String str) {
        return getSharedPreferences(context).getBoolean(str, false);
    }

    public static synchronized int getInt(Context context, String str) {
        return getSharedPreferences(context).getInt(str, 0);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("sonarPushPreferences", 0);
    }
}
