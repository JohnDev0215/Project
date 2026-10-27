package com.amazon.music.voice.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaLocaleUtil {
    public static void saveLocale(Context context, Locale locale) {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().putString("voice.manager.locale.language", locale.getLanguage()).putString("voice.manager.locale.country", locale.getCountry()).apply();
    }

    public static Locale getSavedLocale(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String string = defaultSharedPreferences.getString("voice.manager.locale.language", null);
        String string2 = defaultSharedPreferences.getString("voice.manager.locale.country", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new Locale(string, string2);
    }
}
