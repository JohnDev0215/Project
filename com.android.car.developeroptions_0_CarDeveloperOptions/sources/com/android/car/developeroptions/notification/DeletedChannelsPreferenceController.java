package com.android.car.developeroptions.notification;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.NotificationBackend;

/* JADX INFO: loaded from: classes.dex */
public class DeletedChannelsPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "deleted";
    }

    public DeletedChannelsPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        if (!super.isAvailable() || this.mChannel != null || hasValidGroup()) {
            return false;
        }
        NotificationBackend notificationBackend = this.mBackend;
        NotificationBackend.AppRow appRow = this.mAppRow;
        return notificationBackend.getDeletedChannelCount(appRow.pkg, appRow.uid) > 0;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow != null) {
            int deletedChannelCount = this.mBackend.getDeletedChannelCount(appRow.pkg, appRow.uid);
            preference.setTitle(((NotificationPreferenceController) this).mContext.getResources().getQuantityString(R.plurals.deleted_channels, deletedChannelCount, Integer.valueOf(deletedChannelCount)));
        }
        preference.setSelectable(false);
    }
}
