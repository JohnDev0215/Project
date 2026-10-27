package com.android.inputmethod.latin.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Process;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.TwoStatePreference;
import com.android.inputmethod.latin.DictionaryDumpBroadcastReceiver;
import com.android.inputmethod.latin.DictionaryFacilitatorImpl;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.settings.SeekBarDialogPreference;
import com.android.inputmethod.latin.utils.ApplicationUtils;
import com.android.inputmethod.latin.utils.ResourceUtils;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class DebugSettingsFragment extends SubScreenFragment implements Preference.OnPreferenceClickListener {
    private static final String PREF_KEY_DUMP_DICTS = "pref_key_dump_dictionaries";
    private static final String PREF_KEY_DUMP_DICT_PREFIX = "pref_key_dump_dictionaries";
    private TwoStatePreference mDebugMode;
    private boolean mServiceNeedsRestart = false;

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs_screen_debug);
        if (!Settings.SHOULD_SHOW_LXX_SUGGESTION_UI) {
            removePreference(DebugSettings.PREF_SHOULD_SHOW_LXX_SUGGESTION_UI);
        }
        PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference("pref_key_dump_dictionaries");
        Iterator<String> it = DictionaryFacilitatorImpl.DICT_TYPE_TO_CLASS.keySet().iterator();
        while (it.hasNext()) {
            DictDumpPreference dictDumpPreference = new DictDumpPreference(getActivity(), it.next());
            dictDumpPreference.setOnPreferenceClickListener(this);
            preferenceGroup.addPreference(dictDumpPreference);
        }
        Resources resources = getResources();
        setupKeyPreviewAnimationDuration(DebugSettings.PREF_KEY_PREVIEW_SHOW_UP_DURATION, resources.getInteger(R.integer.config_key_preview_show_up_duration));
        setupKeyPreviewAnimationDuration(DebugSettings.PREF_KEY_PREVIEW_DISMISS_DURATION, resources.getInteger(R.integer.config_key_preview_dismiss_duration));
        float floatFromFraction = ResourceUtils.getFloatFromFraction(resources, R.fraction.config_key_preview_show_up_start_scale);
        float floatFromFraction2 = ResourceUtils.getFloatFromFraction(resources, R.fraction.config_key_preview_dismiss_end_scale);
        setupKeyPreviewAnimationScale(DebugSettings.PREF_KEY_PREVIEW_SHOW_UP_START_X_SCALE, floatFromFraction);
        setupKeyPreviewAnimationScale(DebugSettings.PREF_KEY_PREVIEW_SHOW_UP_START_Y_SCALE, floatFromFraction);
        setupKeyPreviewAnimationScale(DebugSettings.PREF_KEY_PREVIEW_DISMISS_END_X_SCALE, floatFromFraction2);
        setupKeyPreviewAnimationScale(DebugSettings.PREF_KEY_PREVIEW_DISMISS_END_Y_SCALE, floatFromFraction2);
        setupKeyboardHeight(DebugSettings.PREF_KEYBOARD_HEIGHT_SCALE, 1.0f);
        this.mServiceNeedsRestart = false;
        this.mDebugMode = (TwoStatePreference) findPreference(DebugSettings.PREF_DEBUG_MODE);
        updateDebugMode();
    }

    private static class DictDumpPreference extends Preference {
        public final String mDictName;

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Preference another) {
            return super.compareTo(another);
        }

        public DictDumpPreference(final Context context, final String dictName) {
            super(context);
            setKey("pref_key_dump_dictionaries" + dictName);
            setTitle("Dump " + dictName + " dictionary");
            this.mDictName = dictName;
        }
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(final Preference pref) {
        Activity activity = getActivity();
        if (pref instanceof DictDumpPreference) {
            String str = ((DictDumpPreference) pref).mDictName;
            Intent intent = new Intent(DictionaryDumpBroadcastReceiver.DICTIONARY_DUMP_INTENT_ACTION);
            intent.putExtra(DictionaryDumpBroadcastReceiver.DICTIONARY_NAME_KEY, str);
            activity.sendBroadcast(intent);
        }
        return true;
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.mServiceNeedsRestart) {
            Process.killProcess(Process.myPid());
        }
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key) {
        TwoStatePreference twoStatePreference;
        if (key.equals(DebugSettings.PREF_DEBUG_MODE) && (twoStatePreference = this.mDebugMode) != null) {
            twoStatePreference.setChecked(prefs.getBoolean(DebugSettings.PREF_DEBUG_MODE, false));
            updateDebugMode();
            this.mServiceNeedsRestart = true;
        } else if (key.equals(DebugSettings.PREF_FORCE_NON_DISTINCT_MULTITOUCH)) {
            this.mServiceNeedsRestart = true;
        }
    }

    private void updateDebugMode() {
        boolean zIsChecked = this.mDebugMode.isChecked();
        String string = getString(R.string.version_text, new Object[]{ApplicationUtils.getVersionName(getActivity())});
        if (!zIsChecked) {
            this.mDebugMode.setTitle(string);
            this.mDebugMode.setSummary((CharSequence) null);
        } else {
            this.mDebugMode.setTitle(getString(R.string.prefs_debug_mode));
            this.mDebugMode.setSummary(string);
        }
    }

    private void setupKeyPreviewAnimationScale(final String prefKey, final float defaultValue) {
        final SharedPreferences sharedPreferences = getSharedPreferences();
        final Resources resources = getResources();
        SeekBarDialogPreference seekBarDialogPreference = (SeekBarDialogPreference) findPreference(prefKey);
        if (seekBarDialogPreference == null) {
            return;
        }
        seekBarDialogPreference.setInterface(new SeekBarDialogPreference.ValueProxy() { // from class: com.android.inputmethod.latin.settings.DebugSettingsFragment.1
            private static final float PERCENTAGE_FLOAT = 100.0f;

            private int getPercentageFromValue(final float floatValue) {
                return (int) (floatValue * 100.0f);
            }

            private float getValueFromPercentage(final int percentage) {
                return percentage / 100.0f;
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void feedbackValue(final int value) {
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
                return getPercentageFromValue(Settings.readKeyPreviewAnimationScale(sharedPreferences, key, defaultValue));
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public int readDefaultValue(final String key) {
                return getPercentageFromValue(defaultValue);
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public String getValueText(final int value) {
                return value < 0 ? resources.getString(R.string.settings_system_default) : String.format(Locale.ROOT, "%d%%", Integer.valueOf(value));
            }
        });
    }

    private void setupKeyPreviewAnimationDuration(final String prefKey, final int defaultValue) {
        final SharedPreferences sharedPreferences = getSharedPreferences();
        final Resources resources = getResources();
        SeekBarDialogPreference seekBarDialogPreference = (SeekBarDialogPreference) findPreference(prefKey);
        if (seekBarDialogPreference == null) {
            return;
        }
        seekBarDialogPreference.setInterface(new SeekBarDialogPreference.ValueProxy() { // from class: com.android.inputmethod.latin.settings.DebugSettingsFragment.2
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
                return Settings.readKeyPreviewAnimationDuration(sharedPreferences, key, defaultValue);
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public int readDefaultValue(final String key) {
                return defaultValue;
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public String getValueText(final int value) {
                return resources.getString(R.string.abbreviation_unit_milliseconds, Integer.valueOf(value));
            }
        });
    }

    private void setupKeyboardHeight(final String prefKey, final float defaultValue) {
        final SharedPreferences sharedPreferences = getSharedPreferences();
        SeekBarDialogPreference seekBarDialogPreference = (SeekBarDialogPreference) findPreference(prefKey);
        if (seekBarDialogPreference == null) {
            return;
        }
        seekBarDialogPreference.setInterface(new SeekBarDialogPreference.ValueProxy() { // from class: com.android.inputmethod.latin.settings.DebugSettingsFragment.3
            private static final float PERCENTAGE_FLOAT = 100.0f;

            private int getPercentageFromValue(final float floatValue) {
                return (int) (floatValue * 100.0f);
            }

            private float getValueFromPercentage(final int percentage) {
                return percentage / 100.0f;
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public void feedbackValue(final int value) {
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
                return getPercentageFromValue(Settings.readKeyboardHeight(sharedPreferences, defaultValue));
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public int readDefaultValue(final String key) {
                return getPercentageFromValue(defaultValue);
            }

            @Override // com.android.inputmethod.latin.settings.SeekBarDialogPreference.ValueProxy
            public String getValueText(final int value) {
                return String.format(Locale.ROOT, "%d%%", Integer.valueOf(value));
            }
        });
    }
}
