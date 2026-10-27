package com.android.inputmethod.latin.settings;

import android.os.Bundle;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.Constants;

/* JADX INFO: loaded from: classes2.dex */
public final class AppearanceSettingsFragment extends SubScreenFragment {
    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs_screen_appearance);
        if (Constants.isPhone(Settings.readScreenMetrics(getResources()))) {
            removePreference(Settings.PREF_ENABLE_SPLIT_KEYBOARD);
        }
        removePreference(Settings.PREF_ENABLE_SPLIT_KEYBOARD);
        removePreference(Settings.SCREEN_THEME);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        CustomInputStyleSettingsFragment.updateCustomInputStylesSummary(findPreference(Settings.PREF_CUSTOM_INPUT_STYLES));
    }
}
