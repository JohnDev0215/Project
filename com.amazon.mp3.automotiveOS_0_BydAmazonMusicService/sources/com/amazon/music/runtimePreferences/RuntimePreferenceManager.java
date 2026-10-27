package com.amazon.music.runtimePreferences;

import android.preference.PreferenceCategory;

/* JADX INFO: loaded from: classes4.dex */
public final class RuntimePreferenceManager {
    public void loadPreferencesForCategory(String str, PreferenceCategory preferenceCategory) {
        PreferenceLoader preferenceLoader = PreferenceLoaderRegistry.getInstance().getPreferenceLoader(str);
        if (preferenceLoader != null) {
            try {
                preferenceCategory.addPreference(preferenceLoader.getPreference());
            } catch (HidePreferenceException unused) {
            }
        }
    }
}
