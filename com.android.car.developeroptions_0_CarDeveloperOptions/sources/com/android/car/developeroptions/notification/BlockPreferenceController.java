package com.android.car.developeroptions.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.widget.Switch;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.car.developeroptions.notification.NotificationSettingsBase;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class BlockPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, SwitchBar.OnSwitchChangeListener {
    private NotificationSettingsBase.ImportanceListener mImportanceListener;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "block";
    }

    public BlockPreferenceController(Context context, NotificationSettingsBase.ImportanceListener importanceListener, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
        this.mImportanceListener = importanceListener;
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null) {
            return false;
        }
        if (this.mChannel != null) {
            return isChannelBlockable();
        }
        if (this.mChannelGroup != null) {
            return isChannelGroupBlockable();
        }
        boolean z = appRow.systemApp;
        return !z || (z && appRow.banned);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        LayoutPreference layoutPreference = (LayoutPreference) preference;
        boolean z = false;
        layoutPreference.setSelectable(false);
        SwitchBar switchBar = (SwitchBar) layoutPreference.findViewById(R.id.switch_bar);
        if (switchBar != null) {
            switchBar.setSwitchBarText(R.string.notification_switch_label, R.string.notification_switch_label);
            switchBar.show();
            try {
                switchBar.addOnSwitchChangeListener(this);
            } catch (IllegalStateException unused) {
            }
            switchBar.setDisabledByAdmin(this.mAdmin);
            NotificationChannel notificationChannel = this.mChannel;
            if (notificationChannel != null) {
                if (!this.mAppRow.banned && notificationChannel.getImportance() != 0) {
                    z = true;
                }
                switchBar.setChecked(z);
                return;
            }
            NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
            if (notificationChannelGroup != null) {
                if (!this.mAppRow.banned && !notificationChannelGroup.isBlocked()) {
                    z = true;
                }
                switchBar.setChecked(z);
                return;
            }
            switchBar.setChecked(!this.mAppRow.banned);
        }
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r3, boolean z) {
        int i;
        boolean z2 = !z;
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel != null) {
            int importance = notificationChannel.getImportance();
            if (z2 || importance == 0) {
                if (z2) {
                    i = 0;
                } else {
                    i = isDefaultChannel() ? -1000 : 3;
                }
                this.mChannel.setImportance(i);
                saveChannel();
            }
            NotificationBackend notificationBackend = this.mBackend;
            NotificationBackend.AppRow appRow = this.mAppRow;
            if (notificationBackend.onlyHasDefaultChannel(appRow.pkg, appRow.uid)) {
                NotificationBackend.AppRow appRow2 = this.mAppRow;
                if (appRow2.banned != z2) {
                    appRow2.banned = z2;
                    this.mBackend.setNotificationsEnabledForPackage(appRow2.pkg, appRow2.uid, !z2);
                }
            }
        } else {
            NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
            if (notificationChannelGroup != null) {
                notificationChannelGroup.setBlocked(z2);
                NotificationBackend notificationBackend2 = this.mBackend;
                NotificationBackend.AppRow appRow3 = this.mAppRow;
                notificationBackend2.updateChannelGroup(appRow3.pkg, appRow3.uid, this.mChannelGroup);
            } else {
                NotificationBackend.AppRow appRow4 = this.mAppRow;
                if (appRow4 != null) {
                    appRow4.banned = z2;
                    this.mBackend.setNotificationsEnabledForPackage(appRow4.pkg, appRow4.uid, !z2);
                }
            }
        }
        this.mImportanceListener.onImportanceChanged();
    }
}
