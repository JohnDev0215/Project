package com.amazon.device.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* JADX INFO: loaded from: classes2.dex */
public class ApplicationDefaultPreferences {
    static SharedPreferences defaultPreferences;

    public static SharedPreferences getDefaultPreferences() {
        return defaultPreferences;
    }

    public static void initialize(Context context) {
        defaultPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
