package com.android.car.developeroptions.display;

import android.app.UiModeManager;
import android.content.Context;
import android.util.Log;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class NightModePreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "night_mode";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return false;
    }

    public NightModePreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        if (!isAvailable()) {
            setVisible(preferenceScreen, "night_mode", false);
            return;
        }
        ListPreference listPreference = (ListPreference) preferenceScreen.findPreference("night_mode");
        if (listPreference != null) {
            listPreference.setValue(String.valueOf(((UiModeManager) this.mContext.getSystemService("uimode")).getNightMode()));
            listPreference.setOnPreferenceChangeListener(this);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        try {
            ((UiModeManager) this.mContext.getSystemService("uimode")).setNightMode(Integer.parseInt((String) obj));
            return true;
        } catch (NumberFormatException e) {
            Log.e("NightModePrefContr", "could not persist night mode setting", e);
            return false;
        }
    }
}
