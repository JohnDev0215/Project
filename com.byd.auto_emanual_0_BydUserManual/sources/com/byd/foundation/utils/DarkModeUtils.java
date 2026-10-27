package com.byd.foundation.utils;

import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;

/* JADX INFO: loaded from: classes.dex */
public class DarkModeUtils {
    public static final String KEY_MODE = "white_night_mode_sp";

    public static void init(Application application) {
        AppCompatDelegate.setDefaultNightMode(getNightModel(application));
    }

    public static void applyNightMode(Context context) {
        AppCompatDelegate.setDefaultNightMode(2);
        setNightModel(context, 2);
    }

    public static void applyDayMode(Context context) {
        AppCompatDelegate.setDefaultNightMode(1);
        setNightModel(context, 1);
    }

    public static void applySystemMode(Context context) {
        AppCompatDelegate.setDefaultNightMode(-1);
        setNightModel(context, -1);
    }

    public static boolean isDarkMode(Context context) {
        int nightModel = getNightModel(context);
        return nightModel == -1 ? (context.getResources().getConfiguration().uiMode & 48) == 32 : nightModel == 2;
    }

    private static int getNightModel(Context context) {
        return context.getSharedPreferences(KEY_MODE, 0).getInt(KEY_MODE, -1);
    }

    public static void setNightModel(Context context, int i) {
        context.getSharedPreferences(KEY_MODE, 0).edit().putInt(KEY_MODE, i).apply();
    }
}
