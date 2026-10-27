package com.android.car.developeroptions.notification;

import android.R;
import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.RestrictedSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class LightsPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "lights";
    }

    public LightsPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return super.isAvailable() && this.mChannel != null && checkCanBeVisible(3) && canPulseLight() && !isDefaultChannel();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (this.mChannel != null) {
            RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
            restrictedSwitchPreference.setDisabledByAdmin(this.mAdmin);
            restrictedSwitchPreference.setEnabled(isChannelConfigurable() && !restrictedSwitchPreference.isDisabledByAdmin());
            restrictedSwitchPreference.setChecked(this.mChannel.shouldShowLights());
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mChannel == null) {
            return true;
        }
        this.mChannel.enableLights(((Boolean) obj).booleanValue());
        saveChannel();
        return true;
    }

    boolean canPulseLight() {
        return ((NotificationPreferenceController) this).mContext.getResources().getBoolean(R.bool.config_cecQuerySadDdDisabled_allowed) && Settings.System.getInt(((NotificationPreferenceController) this).mContext.getContentResolver(), "notification_light_pulse", 0) == 1;
    }
}
