package com.android.car.developeroptions.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.os.UserManager;
import android.util.Log;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class NotificationPreferenceController extends AbstractPreferenceController {
    protected RestrictedLockUtils.EnforcedAdmin mAdmin;
    protected NotificationBackend.AppRow mAppRow;
    protected final NotificationBackend mBackend;
    protected NotificationChannel mChannel;
    protected NotificationChannelGroup mChannelGroup;
    protected final Context mContext;
    protected final UserManager mUm;

    public NotificationPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context);
        this.mContext = context;
        this.mBackend = notificationBackend;
        this.mUm = (UserManager) this.mContext.getSystemService("user");
        this.mContext.getPackageManager();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null || appRow.banned) {
            return false;
        }
        if (this.mChannelGroup != null) {
            return !r0.isBlocked();
        }
        NotificationChannel notificationChannel = this.mChannel;
        return notificationChannel == null || notificationChannel.getImportance() != 0;
    }

    protected void onResume(NotificationBackend.AppRow appRow, NotificationChannel notificationChannel, NotificationChannelGroup notificationChannelGroup, RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mAppRow = appRow;
        this.mChannel = notificationChannel;
        this.mChannelGroup = notificationChannelGroup;
        this.mAdmin = enforcedAdmin;
    }

    protected boolean checkCanBeVisible(int i) {
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel == null) {
            Log.w("ChannelPrefContr", "No channel");
            return false;
        }
        int importance = notificationChannel.getImportance();
        return importance == -1000 || importance >= i;
    }

    protected void saveChannel() {
        NotificationBackend.AppRow appRow;
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel == null || (appRow = this.mAppRow) == null) {
            return;
        }
        this.mBackend.updateChannel(appRow.pkg, appRow.uid, notificationChannel);
    }

    protected boolean isChannelConfigurable() {
        NotificationChannel notificationChannel;
        NotificationBackend.AppRow appRow = this.mAppRow;
        if ((appRow != null && appRow.lockedImportance) || (notificationChannel = this.mChannel) == null || this.mAppRow == null) {
            return false;
        }
        return !Objects.equals(notificationChannel.getId(), this.mAppRow.lockedChannelId);
    }

    protected boolean isChannelBlockable() {
        if (this.mChannel == null || this.mAppRow == null) {
            return false;
        }
        if (!isChannelConfigurable()) {
            return this.mChannel.getImportance() == 0;
        }
        if (this.mChannel.isImportanceLockedByOEM() || this.mChannel.isImportanceLockedByCriticalDeviceFunction()) {
            return false;
        }
        return this.mChannel.isBlockable() || !this.mAppRow.systemApp || this.mChannel.getImportance() == 0;
    }

    protected boolean isChannelGroupBlockable() {
        NotificationBackend.AppRow appRow;
        NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
        if (notificationChannelGroup == null || (appRow = this.mAppRow) == null) {
            return false;
        }
        if (appRow.systemApp) {
            return notificationChannelGroup.isBlocked();
        }
        return true;
    }

    protected boolean hasValidGroup() {
        return this.mChannelGroup != null;
    }

    protected final boolean isDefaultChannel() {
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel == null) {
            return false;
        }
        return Objects.equals("miscellaneous", notificationChannel.getId());
    }
}
