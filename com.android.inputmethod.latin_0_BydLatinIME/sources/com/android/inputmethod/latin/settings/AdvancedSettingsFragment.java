package com.android.inputmethod.latin.settings;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.ListPreference;
import com.android.inputmethod.latin.AudioAndHapticFeedbackManager;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.SystemBroadcastReceiver;
import com.android.inputmethod.latin.settings.SeekBarDialogPreference;

/* JADX INFO: loaded from: classes2.dex */
public final class AdvancedSettingsFragment extends SubScreenFragment {
    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs_screen_advanced);
        Resources resources = getResources();
        AudioAndHapticFeedbackManager.init(getActivity());
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        if (!Settings.isInternal(sharedPreferences)) {
            removePreference(Settings.SCREEN_DEBUG);
        }
        if (!AudioAndHapticFeedbackManager.getInstance().hasVibrator()) {
            removePreference(Settings.PREF_VIBRATION_DURATION_SETTINGS);
        }
        if (!Settings.readFromBuildConfigIfToShowKeyPreviewPopupOption(resources)) {
            removePreference(Settings.PREF_KEY_PREVIEW_POPUP_DISMISS_DELAY);
        } else {
            ListPreference listPreference = (ListPreference) findPreference(Settings.PREF_KEY_PREVIEW_POPUP_DISMISS_DELAY);
            String string = Integer.toString(resources.getInteger(R.integer.config_key_preview_linger_timeout));
            listPreference.setEntries(new String[]{resources.getString(R.string.key_preview_popup_dismiss_no_delay), resources.getString(R.string.key_preview_popup_dismiss_default_delay)});
            listPreference.setEntryValues(new String[]{"0", string});
            if (listPreference.getValue() == null) {
                listPreference.setValue(string);
            }
            listPreference.setEnabled(Settings.readKeyPreviewPopupEnabled(sharedPreferences, resources));
        }
        setupKeypressVibrationDurationSettings();
        setupKeypressSoundVolumeSettings();
        setupKeyLongpressTimeoutSettings();
        refreshEnablingsOfKeypressSoundAndVibrationSettings();
        removePreference(Settings.PREF_ENABLE_EMOJI_ALT_PHYSICAL_KEY);
        removePreference(Settings.PREF_SHOW_SETUP_WIZARD_ICON);
        removePreference(Settings.PREF_KEYPRESS_SOUND_VOLUME);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences();
        updateListPreferenceSummaryToCurrentValue(Settings.PREF_KEY_PREVIEW_POPUP_DISMISS_DELAY);
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key) {
        Resources resources = getResources();
        if (key.equals(Settings.PREF_POPUP_ON)) {
            setPreferenceEnabled(Settings.PREF_KEY_PREVIEW_POPUP_DISMISS_DELAY, Settings.readKeyPreviewPopupEnabled(prefs, resources));
        } else if (key.equals(Settings.PREF_SHOW_SETUP_WIZARD_ICON)) {
            SystemBroadcastReceiver.toggleAppIcon(getActivity());
        }
        updateListPreferenceSummaryToCurrentValue(Settings.PREF_KEY_PREVIEW_POPUP_DISMISS_DELAY);
        refreshEnablingsOfKeypressSoundAndVibrationSettings();
    }

    private void refreshEnablingsOfKeypressSoundAndVibrationSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        Resources resources = getResources();
        setPreferenceEnabled(Settings.PREF_VIBRATION_DURATION_SETTINGS, Settings.readVibrationEnabled(sharedPreferences, resources));
        setPreferenceEnabled(Settings.PREF_KEYPRESS_SOUND_VOLUME, Settings.readKeypressSoundEnabled(sharedPreferences, resources));
    }

    private void setupKeypressVibrationDurationSettings() {
        SeekBarDialogPreference seekBarDialogPreference = (SeekBarDialogPreference) findPreference(Settings.PREF_VIBRATION_DURATION_SETTINGS);
        if (seekBarDialogPreference == null) {
            return;
        }
        final SharedPreferences sharedPreferences = getSharedPreferences();
        final Resources resources = getResources();
        seekBarDialogPreference.setInterface(new SeekBarDialogPreference.ValueProxy() { // from class: com.android.inputmethod.latin.settings.AdvancedSettingsFragment.1
            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void writeValue(final int value, final String key) {
                sharedPreferences.edit().putInt(key, value).apply();
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void writeDefaultValue(final String key) {
                sharedPreferences.edit().remove(key).apply();
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public int readValue(final String key) {
                return Settings.readKeypressVibrationDuration(sharedPreferences, resources);
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public int readDefaultValue(final String key) {
                return Settings.readDefaultKeypressVibrationDuration(resources);
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void feedbackValue(final int value) {
                AudioAndHapticFeedbackManager.getInstance().vibrate(value);
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public String getValueText(final int value) {
                return value < 0 ? resources.getString(R.string.settings_system_default) : resources.getString(R.string.abbreviation_unit_milliseconds, Integer.valueOf(value));
            }
        });
    }

    private void setupKeypressSoundVolumeSettings() {
        SeekBarDialogPreference seekBarDialogPreference = (SeekBarDialogPreference) findPreference(Settings.PREF_KEYPRESS_SOUND_VOLUME);
        if (seekBarDialogPreference == null) {
            return;
        }
        final SharedPreferences sharedPreferences = getSharedPreferences();
        final Resources resources = getResources();
        final AudioManager audioManager = (AudioManager) getActivity().getSystemService("audio");
        seekBarDialogPreference.setInterface(new SeekBarDialogPreference.ValueProxy() { // from class: com.android.inputmethod.latin.settings.AdvancedSettingsFragment.2
            private static final float PERCENTAGE_FLOAT = 100.0f;

            private int getPercentageFromValue(final float floatValue) {
                return (int) (floatValue * 100.0f);
            }

            private float getValueFromPercentage(final int percentage) {
                return percentage / 100.0f;
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void writeValue(final int value, final String key) {
                sharedPreferences.edit().putFloat(key, getValueFromPercentage(value)).apply();
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void writeDefaultValue(final String key) {
                sharedPreferences.edit().remove(key).apply();
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public int readValue(final String key) {
                return getPercentageFromValue(Settings.readKeypressSoundVolume(sharedPreferences, resources));
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public int readDefaultValue(final String key) {
                return getPercentageFromValue(Settings.readDefaultKeypressSoundVolume(resources));
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public String getValueText(final int value) {
                if (value < 0) {
                    return resources.getString(R.string.settings_system_default);
                }
                return Integer.toString(value);
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void feedbackValue(final int value) {
                audioManager.playSoundEffect(5, getValueFromPercentage(value));
            }
        });
    }

    private void setupKeyLongpressTimeoutSettings() {
        final SharedPreferences sharedPreferences = getSharedPreferences();
        final Resources resources = getResources();
        SeekBarDialogPreference seekBarDialogPreference = (SeekBarDialogPreference) findPreference(Settings.PREF_KEY_LONGPRESS_TIMEOUT);
        if (seekBarDialogPreference == null) {
            return;
        }
        seekBarDialogPreference.setInterface(new SeekBarDialogPreference.ValueProxy() { // from class: com.android.inputmethod.latin.settings.AdvancedSettingsFragment.3
            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void feedbackValue(final int value) {
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void writeValue(final int value, final String key) {
                sharedPreferences.edit().putInt(key, value).apply();
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void writeDefaultValue(final String key) {
                sharedPreferences.edit().remove(key).apply();
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public int readValue(final String key) {
                return Settings.readKeyLongpressTimeout(sharedPreferences, resources);
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public int readDefaultValue(final String key) {
                return Settings.readDefaultKeyLongpressTimeout(resources);
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public String getValueText(final int value) {
                return resources.getString(R.string.abbreviation_unit_milliseconds, Integer.valueOf(value));
            }
        });
    }
}
