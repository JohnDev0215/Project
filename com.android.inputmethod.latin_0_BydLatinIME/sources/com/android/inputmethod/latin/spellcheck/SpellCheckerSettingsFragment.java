package com.android.inputmethod.latin.spellcheck;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.text.TextUtils;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.permissions.PermissionsManager;
import com.android.inputmethod.latin.permissions.PermissionsUtil;
import com.android.inputmethod.latin.settings.SubScreenFragment;
import com.android.inputmethod.latin.settings.TwoStatePreferenceHelper;
import com.android.inputmethod.latin.utils.ApplicationUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class SpellCheckerSettingsFragment extends SubScreenFragment implements SharedPreferences.OnSharedPreferenceChangeListener, PermissionsManager.PermissionsResultCallback {
    private SwitchPreference mLookupContactsPreference;

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addPreferencesFromResource(R.xml.spell_checker_settings);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.setTitle(ApplicationUtils.getActivityTitleResId(getActivity(), SpellCheckerSettingsActivity.class));
        TwoStatePreferenceHelper.replaceCheckBoxPreferencesBySwitchPreferences(preferenceScreen);
        this.mLookupContactsPreference = (SwitchPreference) findPreference(AndroidSpellCheckerService.PREF_USE_CONTACTS_KEY);
        turnOffLookupContactsIfNoPermission();
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (TextUtils.equals(key, AndroidSpellCheckerService.PREF_USE_CONTACTS_KEY) && sharedPreferences.getBoolean(key, false) && !PermissionsUtil.checkAllPermissionsGranted(getActivity(), "android.permission.READ_CONTACTS")) {
            PermissionsManager.get(getActivity()).requestPermissions(this, getActivity(), "android.permission.READ_CONTACTS");
        }
    }

    @Override // com.android.inputmethod.latin.permissions.PermissionsManager.PermissionsResultCallback
    public void onRequestPermissionsResult(boolean allGranted) {
        turnOffLookupContactsIfNoPermission();
    }

    private void turnOffLookupContactsIfNoPermission() {
        if (PermissionsUtil.checkAllPermissionsGranted(getActivity(), "android.permission.READ_CONTACTS")) {
            return;
        }
        this.mLookupContactsPreference.setChecked(false);
    }
}
