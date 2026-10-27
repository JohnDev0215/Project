package com.android.car.developeroptions.notification;

import android.content.Context;
import android.os.UserHandle;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeBypassingAppsPreferenceController extends AbstractZenModePreferenceController {
    private NotificationBackend mNotificationBackend;

    public ZenModeBypassingAppsPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, "zen_mode_bypassing_apps", lifecycle);
        this.mNotificationBackend = new NotificationBackend();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mNotificationBackend.getNumAppsBypassingDnd(UserHandle.getCallingUserId()) != 0;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getSummary() {
        int numAppsBypassingDnd = this.mNotificationBackend.getNumAppsBypassingDnd(UserHandle.getCallingUserId());
        return this.mContext.getResources().getQuantityString(R.plurals.zen_mode_bypassing_apps_subtext, numAppsBypassingDnd, Integer.valueOf(numAppsBypassingDnd));
    }
}
