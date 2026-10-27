package com.android.car.developeroptions.notification;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;

/* JADX INFO: loaded from: classes.dex */
public class NotificationsOffPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "block_desc";
    }

    public NotificationsOffPreferenceController(Context context) {
        super(context, null);
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        if (this.mAppRow == null) {
            return false;
        }
        return !super.isAvailable();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (this.mAppRow != null) {
            if (this.mChannel != null) {
                preference.setTitle(R.string.channel_notifications_off_desc);
            } else if (this.mChannelGroup != null) {
                preference.setTitle(R.string.channel_group_notifications_off_desc);
            } else {
                preference.setTitle(R.string.app_notifications_off_desc);
            }
        }
        preference.setSelectable(false);
    }
}
