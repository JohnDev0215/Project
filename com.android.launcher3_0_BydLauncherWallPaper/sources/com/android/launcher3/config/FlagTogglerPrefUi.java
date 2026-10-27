package com.android.launcher3.config;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceGroup;
import androidx.preference.SwitchPreference;
import com.android.launcher3.R;
import com.android.launcher3.config.BaseFlags;

/* JADX INFO: loaded from: classes4.dex */
public final class FlagTogglerPrefUi {
    private static final String TAG = "FlagTogglerPrefFrag";
    private final Context mContext;
    private final PreferenceDataStore mDataStore = new PreferenceDataStore() { // from class: com.android.launcher3.config.FlagTogglerPrefUi.1
        @Override // androidx.preference.PreferenceDataStore
        public void putBoolean(String key, boolean value) {
            for (BaseFlags.TogglableFlag togglableFlag : FeatureFlags.getTogglableFlags()) {
                if (togglableFlag.getKey().equals(key)) {
                    boolean z = togglableFlag.get();
                    togglableFlag.updateStorage(FlagTogglerPrefUi.this.mContext, value);
                    FlagTogglerPrefUi.this.updateMenu();
                    if (togglableFlag.get() != z) {
                        Toast.makeText(FlagTogglerPrefUi.this.mContext, "Flag applied", 0).show();
                    }
                }
            }
        }

        @Override // androidx.preference.PreferenceDataStore
        public boolean getBoolean(String key, boolean defaultValue) {
            for (BaseFlags.TogglableFlag togglableFlag : FeatureFlags.getTogglableFlags()) {
                if (togglableFlag.getKey().equals(key)) {
                    return togglableFlag.getFromStorage(FlagTogglerPrefUi.this.mContext, defaultValue);
                }
            }
            return defaultValue;
        }
    };
    private final PreferenceFragment mFragment;
    private final SharedPreferences mSharedPreferences;

    public FlagTogglerPrefUi(PreferenceFragment fragment) {
        this.mFragment = fragment;
        Activity activity = fragment.getActivity();
        this.mContext = activity;
        this.mSharedPreferences = activity.getSharedPreferences("featureFlags", 0);
    }

    public void applyTo(PreferenceGroup parent) {
        for (BaseFlags.TogglableFlag togglableFlag : FeatureFlags.getTogglableFlags()) {
            SwitchPreference switchPreference = new SwitchPreference(this.mContext);
            switchPreference.setKey(togglableFlag.getKey());
            switchPreference.setDefaultValue(Boolean.valueOf(togglableFlag.getDefaultValue()));
            switchPreference.setChecked(getFlagStateFromSharedPrefs(togglableFlag));
            switchPreference.setTitle(togglableFlag.getKey());
            updateSummary(switchPreference, togglableFlag);
            switchPreference.setPreferenceDataStore(this.mDataStore);
            parent.addPreference(switchPreference);
        }
        updateMenu();
    }

    private void updateSummary(SwitchPreference switchPreference, BaseFlags.TogglableFlag flag) {
        String str = flag.getDefaultValue() ? "" : "<b>OVERRIDDEN</b><br>";
        String str2 = flag.getDefaultValue() ? "<b>OVERRIDDEN</b><br>" : "";
        switchPreference.setSummaryOn(Html.fromHtml(str + flag.getDescription()));
        switchPreference.setSummaryOff(Html.fromHtml(str2 + flag.getDescription()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMenu() {
        this.mFragment.setHasOptionsMenu(anyChanged());
        this.mFragment.getActivity().invalidateOptionsMenu();
    }

    public void onCreateOptionsMenu(Menu menu) {
        if (anyChanged()) {
            menu.add(0, R.id.menu_apply_flags, 0, "Apply").setShowAsAction(2);
        }
    }

    public void onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_apply_flags) {
            this.mSharedPreferences.edit().commit();
            Log.e(TAG, "Killing launcher process " + Process.myPid() + " to apply new flag values");
            System.exit(0);
        }
    }

    public void onStop() {
        if (anyChanged()) {
            Toast.makeText(this.mContext, "Flag won't be applied until you restart launcher", 1).show();
        }
    }

    private boolean getFlagStateFromSharedPrefs(BaseFlags.TogglableFlag flag) {
        return this.mDataStore.getBoolean(flag.getKey(), flag.getDefaultValue());
    }

    private boolean anyChanged() {
        for (BaseFlags.TogglableFlag togglableFlag : FeatureFlags.getTogglableFlags()) {
            if (getFlagStateFromSharedPrefs(togglableFlag) != togglableFlag.get()) {
                return true;
            }
        }
        return false;
    }
}
