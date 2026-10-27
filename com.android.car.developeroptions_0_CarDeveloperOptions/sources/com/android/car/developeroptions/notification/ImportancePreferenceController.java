package com.android.car.developeroptions.notification;

import android.app.NotificationChannelGroup;
import android.content.Context;
import android.media.RingtoneManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.car.developeroptions.notification.NotificationSettingsBase;

/* JADX INFO: loaded from: classes.dex */
public class ImportancePreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private NotificationSettingsBase.ImportanceListener mImportanceListener;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "importance";
    }

    public ImportancePreferenceController(Context context, NotificationSettingsBase.ImportanceListener importanceListener, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
        this.mImportanceListener = importanceListener;
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null || appRow.banned || this.mChannel == null || isDefaultChannel()) {
            return false;
        }
        NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
        return notificationChannelGroup == null || !notificationChannelGroup.isBlocked();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (this.mAppRow == null || this.mChannel == null) {
            return;
        }
        preference.setEnabled(this.mAdmin == null && isChannelConfigurable());
        ImportancePreference importancePreference = (ImportancePreference) preference;
        importancePreference.setBlockable(isChannelBlockable());
        importancePreference.setConfigurable(isChannelConfigurable());
        importancePreference.setImportance(this.mChannel.getImportance());
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mChannel == null) {
            return true;
        }
        int iIntValue = ((Integer) obj).intValue();
        if (this.mChannel.getImportance() < 3 && !SoundPreferenceController.hasValidSound(this.mChannel) && iIntValue >= 3) {
            this.mChannel.setSound(RingtoneManager.getDefaultUri(2), this.mChannel.getAudioAttributes());
            this.mChannel.lockFields(32);
        }
        this.mChannel.setImportance(iIntValue);
        this.mChannel.lockFields(4);
        saveChannel();
        this.mImportanceListener.onImportanceChanged();
        return true;
    }
}
