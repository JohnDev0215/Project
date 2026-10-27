package com.android.car.developeroptions.enterprise;

import android.content.Context;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class BugReportsPreferenceController extends AdminActionPreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "bug_reports";
    }

    public BugReportsPreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.car.developeroptions.enterprise.AdminActionPreferenceControllerBase
    protected Date getAdminActionTimestamp() {
        return this.mFeatureProvider.getLastBugReportRequestTime();
    }
}
