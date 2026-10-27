package com.android.inputmethod.latin.settings;

import android.os.Bundle;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
public final class GestureSettingsFragment extends SubScreenFragment {
    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs_screen_gesture);
    }
}
