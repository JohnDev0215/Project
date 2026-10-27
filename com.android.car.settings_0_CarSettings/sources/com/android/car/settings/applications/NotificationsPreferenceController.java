package com.android.car.settings.applications;

import android.app.INotificationManager;
import android.app.NotificationChannel;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.ServiceManager;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class NotificationsPreferenceController extends PreferenceController<TwoStatePreference> {
    private static final Logger LOG = new Logger((Class<?>) NotificationsPreferenceController.class);
    INotificationManager mNotificationManager;
    private String mPackageName;
    private int mUid;

    public NotificationsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mNotificationManager = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.mPackageName = packageInfo.packageName;
        this.mUid = packageInfo.applicationInfo.uid;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        preference.setChecked(isNotificationsEnabled());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference preference, Object newValue) {
        boolean enabled = ((Boolean) newValue).booleanValue();
        try {
            if (this.mNotificationManager.onlyHasDefaultChannel(this.mPackageName, this.mUid)) {
                NotificationChannel defaultChannel = this.mNotificationManager.getNotificationChannelForPackage(this.mPackageName, this.mUid, "miscellaneous", (String) null, true);
                defaultChannel.setImportance(enabled ? NotificationManagerCompat.IMPORTANCE_UNSPECIFIED : 0);
                this.mNotificationManager.updateNotificationChannelForPackage(this.mPackageName, this.mUid, defaultChannel);
            }
            this.mNotificationManager.setNotificationsEnabledForPackage(this.mPackageName, this.mUid, enabled);
            return true;
        } catch (Exception e) {
            LOG.w("Error querying notification setting for package");
            return false;
        }
    }

    private boolean isNotificationsEnabled() {
        try {
            return this.mNotificationManager.areNotificationsEnabledForPackage(this.mPackageName, this.mUid);
        } catch (Exception e) {
            LOG.w("Error querying notification setting for package");
            return false;
        }
    }
}
