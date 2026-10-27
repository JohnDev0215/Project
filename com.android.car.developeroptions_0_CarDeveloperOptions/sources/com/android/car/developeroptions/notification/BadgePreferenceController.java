package com.android.car.developeroptions.notification;

import android.app.NotificationChannel;
import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.settingslib.RestrictedSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class BadgePreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "badge";
    }

    public BadgePreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        if (!super.isAvailable()) {
            return false;
        }
        if ((this.mAppRow == null && this.mChannel == null) || Settings.Secure.getInt(((NotificationPreferenceController) this).mContext.getContentResolver(), "notification_badging", 1) == 0) {
            return false;
        }
        if (this.mChannel == null || isDefaultChannel()) {
            return true;
        }
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null) {
            return false;
        }
        return appRow.showBadge;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (this.mAppRow != null) {
            RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
            restrictedSwitchPreference.setDisabledByAdmin(this.mAdmin);
            NotificationChannel notificationChannel = this.mChannel;
            if (notificationChannel != null) {
                restrictedSwitchPreference.setChecked(notificationChannel.canShowBadge());
                restrictedSwitchPreference.setEnabled(isChannelConfigurable() && !restrictedSwitchPreference.isDisabledByAdmin());
            } else {
                restrictedSwitchPreference.setChecked(this.mAppRow.showBadge);
            }
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel != null) {
            notificationChannel.setShowBadge(zBooleanValue);
            saveChannel();
            return true;
        }
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null) {
            return true;
        }
        appRow.showBadge = zBooleanValue;
        this.mBackend.setShowBadge(appRow.pkg, appRow.uid, zBooleanValue);
        return true;
    }
}
