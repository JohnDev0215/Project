package com.android.settingslib.deviceinfo;

import android.content.Context;
import android.os.UserManager;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes6.dex */
public abstract class AbstractSimStatusImeiInfoPreferenceController extends AbstractPreferenceController {
    public AbstractSimStatusImeiInfoPreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return ((UserManager) this.mContext.getSystemService(UserManager.class)).isAdminUser() && !Utils.isWifiOnly(this.mContext);
    }
}
