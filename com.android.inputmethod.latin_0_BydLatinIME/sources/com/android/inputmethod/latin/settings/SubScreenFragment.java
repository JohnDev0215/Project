package com.android.inputmethod.latin.settings;

import android.app.Activity;
import android.app.backup.BackupManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SubScreenFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;

    public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key) {
    }

    static void setPreferenceEnabled(final String prefKey, final boolean enabled, final PreferenceScreen screen) {
        Preference preferenceFindPreference = screen.findPreference(prefKey);
        if (preferenceFindPreference != null) {
            preferenceFindPreference.setEnabled(enabled);
        }
    }

    static void removePreference(final String prefKey, final PreferenceScreen screen) {
        Preference preferenceFindPreference = screen.findPreference(prefKey);
        if (preferenceFindPreference != null) {
            screen.removePreference(preferenceFindPreference);
        }
    }

    static void updateListPreferenceSummaryToCurrentValue(final String prefKey, final PreferenceScreen screen) {
        ListPreference listPreference = (ListPreference) screen.findPreference(prefKey);
        if (listPreference == null) {
            return;
        }
        CharSequence[] entries = listPreference.getEntries();
        int iFindIndexOfValue = listPreference.findIndexOfValue(listPreference.getValue());
        listPreference.setSummary(iFindIndexOfValue < 0 ? null : entries[iFindIndexOfValue]);
    }

    final void setPreferenceEnabled(final String prefKey, final boolean enabled) {
        setPreferenceEnabled(prefKey, enabled, getPreferenceScreen());
    }

    final void removePreference(final String prefKey) {
        removePreference(prefKey, getPreferenceScreen());
    }

    final void updateListPreferenceSummaryToCurrentValue(final String prefKey) {
        updateListPreferenceSummaryToCurrentValue(prefKey, getPreferenceScreen());
    }

    final SharedPreferences getSharedPreferences() {
        return getPreferenceManager().getSharedPreferences();
    }

    final String getApplicationName() {
        return getResources().getString(getActivity().getApplicationInfo().labelRes);
    }

    @Override // android.preference.PreferenceFragment
    public void addPreferencesFromResource(final int preferencesResId) {
        super.addPreferencesFromResource(preferencesResId);
        TwoStatePreferenceHelper.replaceCheckBoxPreferencesBySwitchPreferences(getPreferenceScreen());
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.android.inputmethod.latin.settings.SubScreenFragment.1
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key) {
                SubScreenFragment subScreenFragment = SubScreenFragment.this;
                Activity activity = subScreenFragment.getActivity();
                if (activity == null || subScreenFragment.getPreferenceScreen() == null) {
                    Log.w(subScreenFragment.getClass().getSimpleName(), "onSharedPreferenceChanged called before activity starts.");
                } else {
                    new BackupManager(activity).dataChanged();
                    subScreenFragment.onSharedPreferenceChanged(prefs, key);
                }
            }
        };
        getSharedPreferences().registerOnSharedPreferenceChangeListener(this.mSharedPreferenceChangeListener);
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onDestroy() {
        getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this.mSharedPreferenceChangeListener);
        super.onDestroy();
    }
}
