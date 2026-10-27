package com.android.car.developeroptions.enterprise;

import android.content.Context;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class NetworkLogsPreferenceController extends AdminActionPreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "network_logs";
    }

    public NetworkLogsPreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.car.developeroptions.enterprise.AdminActionPreferenceControllerBase
    protected Date getAdminActionTimestamp() {
        return this.mFeatureProvider.getLastNetworkLogRetrievalTime();
    }

    @Override // com.android.car.developeroptions.enterprise.AdminActionPreferenceControllerBase, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mFeatureProvider.isNetworkLoggingEnabled() || this.mFeatureProvider.getLastNetworkLogRetrievalTime() != null;
    }
}
