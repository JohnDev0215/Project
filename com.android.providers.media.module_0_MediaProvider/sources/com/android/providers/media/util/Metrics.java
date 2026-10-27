package com.android.providers.media.util;

import com.android.providers.media.MediaProviderStatsLog;

/* JADX INFO: loaded from: classes.dex */
public class Metrics {
    public static void logScan(String str, int i, long j, long j2, int i2, int i3, int i4) {
        Logging.logPersistent(String.format("Scanned %s due to %s, found %d items in %dms, %d inserts %d updates %d deletes", str, translateReason(i), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        float f = j;
        MediaProviderStatsLog.write(MediaProviderStatsLog.MEDIA_PROVIDER_SCAN_OCCURRED, translateVolumeName(str), i, j, j2 / f, i2 / f, i3 / f, i4 / f);
    }

    public static void logDeletion(String str, int i, String str2, int i2) {
        Logging.logPersistent(String.format("Deleted %3$d items on %1$s due to %2$s", str, str2, Integer.valueOf(i2)));
        MediaProviderStatsLog.write(MediaProviderStatsLog.MEDIA_CONTENT_DELETED, translateVolumeName(str), i, i2);
    }

    public static void logPermissionGranted(String str, int i, String str2, int i2) {
        Logging.logPersistent(String.format("Granted permission to %3$d items on %1$s to %2$s", str, str2, Integer.valueOf(i2)));
        MediaProviderStatsLog.write(MediaProviderStatsLog.MEDIA_PROVIDER_PERMISSION_REQUESTED, translateVolumeName(str), i, i2, 1);
    }

    public static void logPermissionDenied(String str, int i, String str2, int i2) {
        Logging.logPersistent(String.format("Denied permission to %3$d items on %1$s to %2$s", str, str2, Integer.valueOf(i2)));
        MediaProviderStatsLog.write(MediaProviderStatsLog.MEDIA_PROVIDER_PERMISSION_REQUESTED, translateVolumeName(str), i, i2, 3);
    }

    public static void logSchemaChange(String str, int i, int i2, long j, long j2) {
        Logging.logPersistent(String.format("Changed schema version on %s from %d to %d, %d items taking %dms", str, Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j), Long.valueOf(j2)));
        MediaProviderStatsLog.write(MediaProviderStatsLog.MEDIA_PROVIDER_SCHEMA_CHANGED, translateVolumeName(str), i, i2, j, j2 / j);
    }

    public static void logIdleMaintenance(String str, long j, long j2, int i, int i2) {
        Logging.logPersistent(String.format("Idle maintenance on %s, %d items taking %dms, %d stale, %d expired", str, Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i), Integer.valueOf(i2)));
        float f = j;
        MediaProviderStatsLog.write(MediaProviderStatsLog.MEDIA_PROVIDER_IDLE_MAINTENANCE_FINISHED, translateVolumeName(str), j, j2 / f, i / f, i2 / f);
    }

    public static String translateReason(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? String.valueOf(i) : "REASON_IDLE" : "REASON_DEMAND" : "REASON_MOUNTED" : "REASON_UNKNOWN";
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int translateVolumeName(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = -1921573490(0xffffffff8d771d8e, float:-7.6148327E-31)
            r2 = 0
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L2b
            r1 = -1820761141(0xffffffff937963cb, float:-3.147742E-27)
            if (r0 == r1) goto L21
            r1 = 570410685(0x21ffc6bd, float:1.7332078E-18)
            if (r0 == r1) goto L17
            goto L35
        L17:
            java.lang.String r0 = "internal"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L35
            r5 = r2
            goto L36
        L21:
            java.lang.String r0 = "external"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L35
            r5 = r4
            goto L36
        L2b:
            java.lang.String r0 = "external_primary"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L35
            r5 = r3
            goto L36
        L35:
            r5 = -1
        L36:
            if (r5 == 0) goto L40
            if (r5 == r4) goto L3f
            if (r5 == r3) goto L3e
            r5 = 3
            return r5
        L3e:
            return r3
        L3f:
            return r2
        L40:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.Metrics.translateVolumeName(java.lang.String):int");
    }
}
