package com.android.car.developeroptions.datausage;

import android.net.NetworkPolicy;
import com.android.settingslib.net.DataUsageController;

/* JADX INFO: loaded from: classes.dex */
public class DataUsageInfoController {
    public void updateDataLimit(DataUsageController.DataUsageInfo dataUsageInfo, NetworkPolicy networkPolicy) {
        if (dataUsageInfo == null || networkPolicy == null) {
            return;
        }
        long j = networkPolicy.warningBytes;
        if (j >= 0) {
            dataUsageInfo.warningLevel = j;
        }
        long j2 = networkPolicy.limitBytes;
        if (j2 >= 0) {
            dataUsageInfo.limitLevel = j2;
        }
    }

    public long getSummaryLimit(DataUsageController.DataUsageInfo dataUsageInfo) {
        long j = dataUsageInfo.limitLevel;
        if (j <= 0) {
            j = dataUsageInfo.warningLevel;
        }
        long j2 = dataUsageInfo.usageLevel;
        return j2 > j ? j2 : j;
    }
}
