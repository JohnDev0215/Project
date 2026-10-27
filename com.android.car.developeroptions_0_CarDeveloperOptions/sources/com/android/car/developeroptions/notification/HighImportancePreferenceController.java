package com.android.car.developeroptions.notification;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.NotificationSettingsBase;
import com.android.settingslib.RestrictedSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class HighImportancePreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private NotificationSettingsBase.ImportanceListener mImportanceListener;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "high_importance";
    }

    public HighImportancePreferenceController(Context context, NotificationSettingsBase.ImportanceListener importanceListener, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
        this.mImportanceListener = importanceListener;
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return super.isAvailable() && this.mChannel != null && !isDefaultChannel() && this.mChannel.getImportance() >= 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (this.mAppRow == null || this.mChannel == null) {
            return;
        }
        preference.setEnabled(this.mAdmin == null && isChannelConfigurable());
        ((RestrictedSwitchPreference) preference).setChecked(this.mChannel.getImportance() >= 4);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mChannel == null) {
            return true;
        }
        this.mChannel.setImportance(((Boolean) obj).booleanValue() ? 4 : 3);
        this.mChannel.lockFields(4);
        saveChannel();
        this.mImportanceListener.onImportanceChanged();
        return true;
    }
}
