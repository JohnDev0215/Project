package com.android.car.developeroptions.notification;

import android.content.Context;
import android.os.Vibrator;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.RestrictedSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class VibrationPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private final Vibrator mVibrator;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "vibrate";
    }

    public VibrationPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        Vibrator vibrator;
        return super.isAvailable() && this.mChannel != null && checkCanBeVisible(3) && !isDefaultChannel() && (vibrator = this.mVibrator) != null && vibrator.hasVibrator();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (this.mChannel != null) {
            RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
            restrictedSwitchPreference.setDisabledByAdmin(this.mAdmin);
            restrictedSwitchPreference.setEnabled(!restrictedSwitchPreference.isDisabledByAdmin() && isChannelConfigurable());
            restrictedSwitchPreference.setChecked(this.mChannel.shouldVibrate());
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mChannel == null) {
            return true;
        }
        this.mChannel.enableVibration(((Boolean) obj).booleanValue());
        saveChannel();
        return true;
    }
}
