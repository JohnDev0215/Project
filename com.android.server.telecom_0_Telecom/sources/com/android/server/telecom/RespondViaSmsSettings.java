package com.android.server.telecom;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.telecom.Log;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;

/* JADX INFO: loaded from: classes.dex */
public class RespondViaSmsSettings extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    private SharedPreferences mPrefs;

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(this, "Settings: onCreate()...", new Object[0]);
        QuickResponseUtils.maybeMigrateLegacyQuickResponses(this);
        getPreferenceManager().setSharedPreferencesName("respond_via_sms_prefs");
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        this.mPrefs = sharedPreferences;
        QuickResponseUtils.maybeResetQuickResponses(this, sharedPreferences);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
        }
        addPreferencesFromResource(R.xml.respond_via_sms_settings);
        initPref(findPreference("canned_response_pref_1"));
        initPref(findPreference("canned_response_pref_2"));
        initPref(findPreference("canned_response_pref_3"));
        initPref(findPreference("canned_response_pref_4"));
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        Log.d(this, "onPreferenceChange: key = %s", new Object[]{preference.getKey()});
        Log.d(this, "  preference = '%s'", new Object[]{preference});
        Log.d(this, "  newValue = '%s'", new Object[]{obj});
        EditTextPreference editTextPreference = (EditTextPreference) preference;
        String str = (String) obj;
        if (str.length() != 0) {
            editTextPreference.setTitle(str);
            this.mPrefs.edit().putString(editTextPreference.getKey(), str).commit();
        }
        QuickResponseUtils.maybeResetQuickResponses(this, this.mPrefs);
        return true;
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            goUpToTopLevelSetting(this);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public static void goUpToTopLevelSetting(Activity activity) {
        activity.finish();
    }

    private void initPref(Preference preference) {
        final EditTextPreference editTextPreference = (EditTextPreference) preference;
        editTextPreference.setText(this.mPrefs.getString(editTextPreference.getKey(), editTextPreference.getText()));
        editTextPreference.setTitle(editTextPreference.getText());
        editTextPreference.getEditText().addTextChangedListener(new TextWatcher(this) { // from class: com.android.server.telecom.RespondViaSmsSettings.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                try {
                    Button button = ((AlertDialog) editTextPreference.getDialog()).getButton(-1);
                    if (editable.toString().length() == 0) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                } catch (NullPointerException e) {
                    Log.d(this, e.toString(), new Object[0]);
                }
            }
        });
        editTextPreference.setOnPreferenceChangeListener(this);
    }
}
