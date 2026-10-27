package com.android.internal.os.logging;

import com.android.internal.util.FrameworkStatsLog;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsLoggerWrapper {
    public static void logAppOverlayEnter(int i, String str, boolean z, int i2, boolean z2) {
        if (z) {
            if (i2 != 2038) {
                FrameworkStatsLog.write(59, i, str, true, 1);
            } else {
                if (z2) {
                    return;
                }
                FrameworkStatsLog.write(59, i, str, false, 1);
            }
        }
    }

    public static void logAppOverlayExit(int i, String str, boolean z, int i2, boolean z2) {
        if (z) {
            if (i2 != 2038) {
                FrameworkStatsLog.write(59, i, str, true, 2);
            } else {
                if (z2) {
                    return;
                }
                FrameworkStatsLog.write(59, i, str, false, 2);
            }
        }
    }
}
