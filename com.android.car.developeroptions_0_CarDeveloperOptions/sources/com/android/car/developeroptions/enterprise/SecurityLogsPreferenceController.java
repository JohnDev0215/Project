package com.android.car.developeroptions.enterprise;

import android.content.Context;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class SecurityLogsPreferenceController extends AdminActionPreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "security_logs";
    }

    public SecurityLogsPreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.car.developeroptions.enterprise.AdminActionPreferenceControllerBase
    protected Date getAdminActionTimestamp() {
        return this.mFeatureProvider.getLastSecurityLogRetrievalTime();
    }

    @Override // com.android.car.developeroptions.enterprise.AdminActionPreferenceControllerBase, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mFeatureProvider.isSecurityLoggingEnabled() || this.mFeatureProvider.getLastSecurityLogRetrievalTime() != null;
    }
}
