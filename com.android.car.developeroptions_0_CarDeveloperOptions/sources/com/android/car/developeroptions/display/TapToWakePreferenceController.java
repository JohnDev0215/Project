package com.android.car.developeroptions.display;

import android.R;
import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class TapToWakePreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "tap_to_wake";
    }

    public TapToWakePreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_cecRcProfileSourceContentsMenuNotHandled_default);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ((SwitchPreference) preference).setChecked(Settings.Secure.getInt(this.mContext.getContentResolver(), "double_tap_to_wake", 0) != 0);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "double_tap_to_wake", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }
}
