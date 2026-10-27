package com.android.car.developeroptions.development;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public final class EnableUserSwitchMessageController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "enable_user_switch_message";
    }

    public EnableUserSwitchMessageController(Context context) {
        super(context);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        Settings.Global.putString(this.mContext.getContentResolver(), "android.car.ENABLE_USER_SWITCH_DEVELOPER_MESSAGE", ((Boolean) obj).booleanValue() ? "true" : "false");
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ((SwitchPreference) this.mPreference).setChecked("true".equals(Settings.Global.getString(this.mContext.getContentResolver(), "android.car.ENABLE_USER_SWITCH_DEVELOPER_MESSAGE")));
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    protected void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        Settings.Global.putString(this.mContext.getContentResolver(), "android.car.ENABLE_USER_SWITCH_DEVELOPER_MESSAGE", "false");
        ((SwitchPreference) this.mPreference).setChecked(false);
    }
}
