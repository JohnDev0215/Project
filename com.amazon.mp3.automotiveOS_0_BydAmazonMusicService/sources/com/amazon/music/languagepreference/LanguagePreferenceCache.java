package com.amazon.music.languagepreference;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes4.dex */
final class LanguagePreferenceCache {
    LanguagePreferenceCache() {
    }

    public static boolean sharedPreferenceShow(Context context) {
        return context.getSharedPreferences("com.amazon.mp3_LanguagePreferenceCache", 0).contains("LANGUAGE_PREFERENCE_KEY");
    }

    public static void write(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.amazon.mp3_LanguagePreferenceCache", 0).edit();
        editorEdit.putBoolean("LANGUAGE_PREFERENCE_KEY", true);
        editorEdit.commit();
    }

    public static void clear(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.amazon.mp3_LanguagePreferenceCache", 0).edit();
        editorEdit.clear();
        editorEdit.commit();
    }
}
