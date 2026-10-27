package com.android.inputmethod.latin.settings;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import com.android.inputmethod.latin.AudioAndHapticFeedbackManager;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodManager;

/* JADX INFO: loaded from: classes2.dex */
public final class PreferencesSettingsFragment extends SubScreenFragment {
    private static final boolean VOICE_IME_ENABLED;

    static {
        VOICE_IME_ENABLED = Build.VERSION.SDK_INT >= 16;
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs_screen_preferences);
        Resources resources = getResources();
        RichInputMethodManager.init(getActivity());
        if (!resources.getBoolean(R.bool.config_enable_show_voice_key_option)) {
            removePreference(Settings.PREF_VOICE_INPUT_KEY);
        }
        if (!AudioAndHapticFeedbackManager.getInstance().hasVibrator()) {
            removePreference(Settings.PREF_VIBRATE_ON);
        }
        if (!Settings.readFromBuildConfigIfToShowKeyPreviewPopupOption(resources)) {
            removePreference(Settings.PREF_POPUP_ON);
        }
        refreshEnablingsOfKeypressSoundAndVibrationSettings();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        Preference preferenceFindPreference = findPreference(Settings.PREF_VOICE_INPUT_KEY);
        if (preferenceFindPreference != null) {
            RichInputMethodManager.getInstance().refreshSubtypeCaches();
            boolean z = VOICE_IME_ENABLED;
            preferenceFindPreference.setEnabled(z);
            preferenceFindPreference.setSummary(z ? null : getText(R.string.voice_input_disabled_summary));
        }
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key) {
        Resources resources = getResources();
        if (key.equals(Settings.PREF_POPUP_ON)) {
            setPreferenceEnabled(Settings.PREF_KEY_PREVIEW_POPUP_DISMISS_DELAY, Settings.readKeyPreviewPopupEnabled(prefs, resources));
        }
        refreshEnablingsOfKeypressSoundAndVibrationSettings();
    }

    private void refreshEnablingsOfKeypressSoundAndVibrationSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        Resources resources = getResources();
        setPreferenceEnabled(Settings.PREF_VIBRATION_DURATION_SETTINGS, Settings.readVibrationEnabled(sharedPreferences, resources));
        setPreferenceEnabled(Settings.PREF_KEYPRESS_SOUND_VOLUME, Settings.readKeypressSoundEnabled(sharedPreferences, resources));
    }
}
