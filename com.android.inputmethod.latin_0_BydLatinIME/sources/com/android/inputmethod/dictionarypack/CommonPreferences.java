package com.android.inputmethod.dictionarypack;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes2.dex */
public final class CommonPreferences {
    private static final String COMMON_PREFERENCES_NAME = "LatinImeDictPrefs";

    public static SharedPreferences getCommonPreferences(final Context context) {
        return context.getSharedPreferences(COMMON_PREFERENCES_NAME, 0);
    }

    public static void enable(final SharedPreferences pref, final String id) {
        SharedPreferences.Editor editorEdit = pref.edit();
        editorEdit.putBoolean(id, true);
        editorEdit.apply();
    }

    public static void disable(final SharedPreferences pref, final String id) {
        SharedPreferences.Editor editorEdit = pref.edit();
        editorEdit.putBoolean(id, false);
        editorEdit.apply();
    }
}
