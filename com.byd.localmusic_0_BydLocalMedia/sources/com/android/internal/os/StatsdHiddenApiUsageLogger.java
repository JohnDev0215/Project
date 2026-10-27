package com.android.internal.os;

import com.android.internal.logging.MetricsLogger;
import dalvik.system.VMRuntime;

/* JADX INFO: loaded from: classes4.dex */
class StatsdHiddenApiUsageLogger implements VMRuntime.HiddenApiUsageLogger {
    private static final StatsdHiddenApiUsageLogger sInstance = new StatsdHiddenApiUsageLogger();
    private final MetricsLogger mMetricsLogger = new MetricsLogger();
    private int mHiddenApiAccessLogSampleRate = 0;
    private int mHiddenApiAccessStatslogSampleRate = 0;

    StatsdHiddenApiUsageLogger() {
    }

    static void setHiddenApiAccessLogSampleRates(int i, int i2) {
        StatsdHiddenApiUsageLogger statsdHiddenApiUsageLogger = sInstance;
        statsdHiddenApiUsageLogger.mHiddenApiAccessLogSampleRate = i;
        statsdHiddenApiUsageLogger.mHiddenApiAccessStatslogSampleRate = i2;
    }

    static StatsdHiddenApiUsageLogger getInstance() {
        return sInstance;
    }

    public void hiddenApiUsed(int i, String str, String str2, int i2, boolean z) {
        if (i < this.mHiddenApiAccessLogSampleRate) {
            logUsage(str, str2, i2, z);
        }
        if (i < this.mHiddenApiAccessStatslogSampleRate) {
            newLogUsage(str2, i2, z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void logUsage(java.lang.String r5, java.lang.String r6, int r7, boolean r8) {
        /*
            r4 = this;
            r0 = 3
            r1 = 2
            r2 = 0
            r3 = 1
            if (r7 == 0) goto Lc
            if (r7 == r3) goto L10
            if (r7 == r1) goto Le
            if (r7 == r0) goto L11
        Lc:
            r0 = r2
            goto L11
        Le:
            r0 = r1
            goto L11
        L10:
            r0 = r3
        L11:
            android.metrics.LogMaker r7 = new android.metrics.LogMaker
            r1 = 1391(0x56f, float:1.949E-42)
            r7.<init>(r1)
            android.metrics.LogMaker r5 = r7.setPackageName(r5)
            r7 = 1394(0x572, float:1.953E-42)
            android.metrics.LogMaker r5 = r5.addTaggedData(r7, r6)
            r6 = 1392(0x570, float:1.95E-42)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            android.metrics.LogMaker r5 = r5.addTaggedData(r6, r7)
            if (r8 == 0) goto L37
            r6 = 1393(0x571, float:1.952E-42)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
            r5.addTaggedData(r6, r7)
        L37:
            com.android.internal.logging.MetricsLogger r6 = r4.mMetricsLogger
            r6.write(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.StatsdHiddenApiUsageLogger.logUsage(java.lang.String, java.lang.String, int, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void newLogUsage(java.lang.String r5, int r6, boolean r7) {
        /*
            r4 = this;
            r0 = 3
            r1 = 2
            r2 = 1
            r3 = 0
            if (r6 == 0) goto Lc
            if (r6 == r2) goto L10
            if (r6 == r1) goto Le
            if (r6 == r0) goto L11
        Lc:
            r0 = r3
            goto L11
        Le:
            r0 = r1
            goto L11
        L10:
            r0 = r2
        L11:
            int r6 = android.os.Process.myUid()
            r1 = 178(0xb2, float:2.5E-43)
            com.android.internal.util.FrameworkStatsLog.write(r1, r6, r5, r0, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.StatsdHiddenApiUsageLogger.newLogUsage(java.lang.String, int, boolean):void");
    }
}
