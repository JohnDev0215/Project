package com.android.inputmethod.latin.settings;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.text.TextUtils;
import com.android.inputmethod.dictionarypack.DictionarySettingsActivity;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.permissions.PermissionsManager;
import com.android.inputmethod.latin.permissions.PermissionsUtil;
import com.android.inputmethod.latin.userdictionary.UserDictionaryList;
import com.android.inputmethod.latin.userdictionary.UserDictionarySettings;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
public final class CorrectionSettingsFragment extends SubScreenFragment implements SharedPreferences.OnSharedPreferenceChangeListener, PermissionsManager.PermissionsResultCallback {
    private static final boolean DBG_USE_INTERNAL_PERSONAL_DICTIONARY_SETTINGS = false;
    private static final boolean USE_INTERNAL_PERSONAL_DICTIONARY_SETTINGS;
    private SwitchPreference mUseContactsPreference;

    static {
        USE_INTERNAL_PERSONAL_DICTIONARY_SETTINGS = Build.VERSION.SDK_INT <= 18;
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs_screen_correction);
        Activity activity = getActivity();
        PackageManager packageManager = activity.getPackageManager();
        Intent intent = findPreference(Settings.PREF_CONFIGURE_DICTIONARIES_KEY).getIntent();
        intent.setClassName(activity.getPackageName(), DictionarySettingsActivity.class.getName());
        if (packageManager.queryIntentActivities(intent, 0).size() <= 0) {
            removePreference(Settings.PREF_CONFIGURE_DICTIONARIES_KEY);
        }
        Preference preferenceFindPreference = findPreference(Settings.PREF_EDIT_PERSONAL_DICTIONARY);
        if ((USE_INTERNAL_PERSONAL_DICTIONARY_SETTINGS ? null : packageManager.resolveActivity(preferenceFindPreference.getIntent(), 65536)) == null) {
            overwriteUserDictionaryPreference(preferenceFindPreference);
        }
        this.mUseContactsPreference = (SwitchPreference) findPreference(Settings.PREF_KEY_USE_CONTACTS_DICT);
        removePreference(Settings.PREF_KEY_USE_CONTACTS_DICT);
        removePreference(Settings.PREF_KEY_USE_PERSONALIZED_DICTS);
        removePreference(Settings.PREF_EDIT_PERSONAL_DICTIONARY);
        turnOffUseContactsIfNoPermission();
    }

    private void overwriteUserDictionaryPreference(final Preference userDictionaryPreference) {
        TreeSet<String> userDictionaryLocalesSet = UserDictionaryList.getUserDictionaryLocalesSet(getActivity());
        if (userDictionaryLocalesSet == null) {
            getPreferenceScreen().removePreference(userDictionaryPreference);
            return;
        }
        if (userDictionaryLocalesSet.size() <= 1) {
            userDictionaryPreference.setFragment(UserDictionarySettings.class.getName());
            if (userDictionaryLocalesSet.size() == 1) {
                userDictionaryPreference.getExtras().putString("locale", (String) userDictionaryLocalesSet.toArray()[0]);
                return;
            }
            return;
        }
        userDictionaryPreference.setFragment(UserDictionaryList.class.getName());
    }

    @Override // com.android.inputmethod.latin.settings.SubScreenFragment, android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
        if (TextUtils.equals(key, Settings.PREF_KEY_USE_CONTACTS_DICT) && sharedPreferences.getBoolean(key, false) && !PermissionsUtil.checkAllPermissionsGranted(getActivity(), "android.permission.READ_CONTACTS")) {
            PermissionsManager.get(getActivity()).requestPermissions(this, getActivity(), "android.permission.READ_CONTACTS");
        }
    }

    @Override // com.android.inputmethod.latin.permissions.PermissionsManager.PermissionsResultCallback
    public void onRequestPermissionsResult(boolean allGranted) {
        turnOffUseContactsIfNoPermission();
    }

    private void turnOffUseContactsIfNoPermission() {
        if (PermissionsUtil.checkAllPermissionsGranted(getActivity(), "android.permission.READ_CONTACTS")) {
            return;
        }
        this.mUseContactsPreference.setChecked(false);
    }
}
