package com.android.car.developeroptions.development;

import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class AppsNotRespondingPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    static final int SETTING_VALUE_OFF = 0;
    static final int SETTING_VALUE_ON = 1;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "show_all_anrs";
    }

    public AppsNotRespondingPreferenceController(Context context) {
        super(context);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "anr_show_background", zBooleanValue ? 1 : 0, UserHandle.SYSTEM.getIdentifier());
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ((SwitchPreference) this.mPreference).setChecked(Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "anr_show_background", 0, UserHandle.SYSTEM.getIdentifier()) != 0);
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    protected void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "anr_show_background", 0, UserHandle.SYSTEM.getIdentifier());
        ((SwitchPreference) this.mPreference).setChecked(false);
    }
}
