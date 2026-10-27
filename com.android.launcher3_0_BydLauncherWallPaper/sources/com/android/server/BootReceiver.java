package com.android.server;

import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.os.Build;
import android.os.DropBoxManager;
import android.os.Environment;
import android.os.FileObserver;
import android.os.FileUtils;
import android.os.RecoverySystem;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.provider.Downloads;
import android.provider.SettingsStringUtil;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.AtomicFile;
import android.util.EventLog;
import android.util.Slog;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.FastXmlSerializer;
import com.android.internal.util.FrameworkStatsLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public class BootReceiver extends BroadcastReceiver {
    private static final String FSCK_FS_MODIFIED = "FILE SYSTEM WAS MODIFIED";
    private static final String FSCK_PASS_PATTERN = "Pass ([1-9]E?):";
    private static final String FSCK_TREE_OPTIMIZATION_PATTERN = "Inode [0-9]+ extent tree.*could be shorter";
    private static final int FS_STAT_FS_FIXED = 1024;
    private static final String FS_STAT_PATTERN = "fs_stat,[^,]*/([^/,]+),(0x[0-9a-fA-F]+)";
    private static final int GMSCORE_LASTK_LOG_SIZE = 196608;
    private static final int LASTK_LOG_SIZE;
    private static final String LAST_HEADER_FILE = "last-header.txt";
    private static final String[] LAST_KMSG_FILES;
    private static final String LAST_SHUTDOWN_TIME_PATTERN = "powerctl_shutdown_time_ms:([0-9]+):([0-9]+)";
    private static final String LOG_FILES_FILE = "log-files.xml";
    private static final int LOG_SIZE;
    private static final String METRIC_SHUTDOWN_TIME_START = "begin_shutdown";
    private static final String METRIC_SYSTEM_SERVER = "shutdown_system_server";
    private static final String[] MOUNT_DURATION_PROPS_POSTFIX;
    private static final String OLD_UPDATER_CLASS = "com.google.android.systemupdater.SystemUpdateReceiver";
    private static final String OLD_UPDATER_PACKAGE = "com.google.android.systemupdater";
    private static final String SHUTDOWN_METRICS_FILE = "/data/system/shutdown-metrics.txt";
    private static final String SHUTDOWN_TRON_METRICS_PREFIX = "shutdown_";
    private static final String TAG = "BootReceiver";
    private static final String TAG_TOMBSTONE = "SYSTEM_TOMBSTONE";
    private static final String TAG_TRUNCATED = "[[TRUNCATED]]\n";
    private static final File TOMBSTONE_DIR;
    private static final int UMOUNT_STATUS_NOT_AVAILABLE = 4;
    private static final File lastHeaderFile;
    private static final AtomicFile sFile;
    private static FileObserver sTombstoneObserver;

    static {
        LOG_SIZE = SystemProperties.getInt("ro.debuggable", 0) == 1 ? 98304 : 65536;
        LASTK_LOG_SIZE = SystemProperties.getInt("ro.debuggable", 0) == 1 ? 196608 : 65536;
        TOMBSTONE_DIR = new File("/data/tombstones");
        sTombstoneObserver = null;
        sFile = new AtomicFile(new File(Environment.getDataSystemDirectory(), LOG_FILES_FILE), "log-files");
        lastHeaderFile = new File(Environment.getDataSystemDirectory(), LAST_HEADER_FILE);
        MOUNT_DURATION_PROPS_POSTFIX = new String[]{"early", "default", "late"};
        LAST_KMSG_FILES = new String[]{"/sys/fs/pstore/console-ramoops", "/proc/last_kmsg"};
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.server.BootReceiver$1] */
    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        new Thread() { // from class: com.android.server.BootReceiver.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    BootReceiver.this.logBootEvents(context);
                } catch (Exception e) {
                    Slog.e(BootReceiver.TAG, "Can't log boot events", e);
                }
                boolean zIsOnlyCoreApps = false;
                try {
                    try {
                        zIsOnlyCoreApps = IPackageManager.Stub.asInterface(ServiceManager.getService("package")).isOnlyCoreApps();
                    } catch (Exception e2) {
                        Slog.e(BootReceiver.TAG, "Can't remove old update packages", e2);
                        return;
                    }
                } catch (RemoteException unused) {
                }
                if (zIsOnlyCoreApps) {
                    return;
                }
                BootReceiver.this.removeOldUpdatePackages(context);
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeOldUpdatePackages(Context context) {
        Downloads.removeAllDownloadsByPackage(context, OLD_UPDATER_PACKAGE, OLD_UPDATER_CLASS);
    }

    private String getPreviousBootHeaders() {
        try {
            return FileUtils.readTextFile(lastHeaderFile, 0, null);
        } catch (IOException unused) {
            return null;
        }
    }

    private String getCurrentBootHeaders() throws IOException {
        return new StringBuilder(512).append("Build: ").append(Build.FINGERPRINT).append("\n").append("Hardware: ").append(Build.BOARD).append("\n").append("Revision: ").append(SystemProperties.get("ro.revision", "")).append("\n").append("Bootloader: ").append(Build.BOOTLOADER).append("\n").append("Radio: ").append(Build.getRadioVersion()).append("\n").append("Kernel: ").append(FileUtils.readTextFile(new File("/proc/version"), 1024, "...\n")).append("\n").toString();
    }

    private String getBootHeadersToLogAndUpdate() throws IOException {
        String previousBootHeaders = getPreviousBootHeaders();
        String currentBootHeaders = getCurrentBootHeaders();
        try {
            FileUtils.stringToFile(lastHeaderFile, currentBootHeaders);
        } catch (IOException e) {
            Slog.e(TAG, "Error writing " + lastHeaderFile, e);
        }
        if (previousBootHeaders == null) {
            return "isPrevious: false\n" + currentBootHeaders;
        }
        return "isPrevious: true\n" + previousBootHeaders;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logBootEvents(Context context) throws IOException {
        final DropBoxManager dropBoxManager = (DropBoxManager) context.getSystemService(Context.DROPBOX_SERVICE);
        final String bootHeadersToLogAndUpdate = getBootHeadersToLogAndUpdate();
        String str = SystemProperties.get("ro.boot.bootreason", null);
        String strHandleAftermath = RecoverySystem.handleAftermath(context);
        if (strHandleAftermath != null && dropBoxManager != null) {
            dropBoxManager.addText("SYSTEM_RECOVERY_LOG", bootHeadersToLogAndUpdate + strHandleAftermath);
        }
        String string = str != null ? new StringBuilder(512).append("\n").append("Boot info:\n").append("Last boot reason: ").append(str).append("\n").toString() : "";
        HashMap<String, Long> timestamps = readTimestamps();
        if (SystemProperties.getLong("ro.runtime.firstboot", 0L) == 0) {
            if (!StorageManager.inCryptKeeperBounce()) {
                SystemProperties.set("ro.runtime.firstboot", Long.toString(System.currentTimeMillis()));
            }
            if (dropBoxManager != null) {
                dropBoxManager.addText("SYSTEM_BOOT", bootHeadersToLogAndUpdate);
            }
            int i = LASTK_LOG_SIZE;
            String str2 = string;
            addLastkToDropBox(dropBoxManager, timestamps, bootHeadersToLogAndUpdate, str2, "/proc/last_kmsg", -i, "SYSTEM_LAST_KMSG");
            addLastkToDropBox(dropBoxManager, timestamps, bootHeadersToLogAndUpdate, str2, "/sys/fs/pstore/console-ramoops", -i, "SYSTEM_LAST_KMSG");
            addLastkToDropBox(dropBoxManager, timestamps, bootHeadersToLogAndUpdate, str2, "/sys/fs/pstore/console-ramoops-0", -i, "SYSTEM_LAST_KMSG");
            int i2 = LOG_SIZE;
            addFileToDropBox(dropBoxManager, timestamps, bootHeadersToLogAndUpdate, "/cache/recovery/log", -i2, "SYSTEM_RECOVERY_LOG");
            addFileToDropBox(dropBoxManager, timestamps, bootHeadersToLogAndUpdate, "/cache/recovery/last_kmsg", -i2, "SYSTEM_RECOVERY_KMSG");
            addAuditErrorsToDropBox(dropBoxManager, timestamps, bootHeadersToLogAndUpdate, -i2, "SYSTEM_AUDIT");
        } else if (dropBoxManager != null) {
            dropBoxManager.addText("SYSTEM_RESTART", bootHeadersToLogAndUpdate);
        }
        logFsShutdownTime();
        logFsMountTime();
        addFsckErrorsToDropBoxAndLogFsStat(dropBoxManager, timestamps, bootHeadersToLogAndUpdate, -LOG_SIZE, "SYSTEM_FSCK");
        logSystemServerShutdownTimeMetrics();
        File[] fileArrListFiles = TOMBSTONE_DIR.listFiles();
        for (int i3 = 0; fileArrListFiles != null && i3 < fileArrListFiles.length; i3++) {
            if (fileArrListFiles[i3].isFile()) {
                addFileToDropBox(dropBoxManager, timestamps, bootHeadersToLogAndUpdate, fileArrListFiles[i3].getPath(), LOG_SIZE, TAG_TOMBSTONE);
            }
        }
        writeTimestamps(timestamps);
        FileObserver fileObserver = new FileObserver(TOMBSTONE_DIR.getPath(), 256) { // from class: com.android.server.BootReceiver.2
            @Override // android.os.FileObserver
            public void onEvent(int i4, String str3) {
                HashMap timestamps2 = BootReceiver.readTimestamps();
                try {
                    File file = new File(BootReceiver.TOMBSTONE_DIR, str3);
                    if (file.isFile() && file.getName().startsWith("tombstone_")) {
                        BootReceiver.addFileToDropBox(dropBoxManager, timestamps2, bootHeadersToLogAndUpdate, file.getPath(), BootReceiver.LOG_SIZE, BootReceiver.TAG_TOMBSTONE);
                    }
                } catch (IOException e) {
                    Slog.e(BootReceiver.TAG, "Can't log tombstone", e);
                }
                BootReceiver.this.writeTimestamps(timestamps2);
            }
        };
        sTombstoneObserver = fileObserver;
        fileObserver.startWatching();
    }

    private static void addLastkToDropBox(DropBoxManager dropBoxManager, HashMap<String, Long> map, String str, String str2, String str3, int i, String str4) throws IOException {
        int length = str.length() + 14 + str2.length();
        if (LASTK_LOG_SIZE + length > 196608) {
            i = 196608 > length ? -(196608 - length) : 0;
        }
        addFileWithFootersToDropBox(dropBoxManager, map, str, str2, str3, i, str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addFileToDropBox(DropBoxManager dropBoxManager, HashMap<String, Long> map, String str, String str2, int i, String str3) throws IOException {
        addFileWithFootersToDropBox(dropBoxManager, map, str, "", str2, i, str3);
    }

    private static void addFileWithFootersToDropBox(DropBoxManager dropBoxManager, HashMap<String, Long> map, String str, String str2, String str3, int i, String str4) throws IOException {
        if (dropBoxManager == null || !dropBoxManager.isTagEnabled(str4)) {
            return;
        }
        File file = new File(str3);
        long jLastModified = file.lastModified();
        if (jLastModified <= 0) {
            return;
        }
        if (map.containsKey(str3) && map.get(str3).longValue() == jLastModified) {
            return;
        }
        map.put(str3, Long.valueOf(jLastModified));
        String textFile = FileUtils.readTextFile(file, i, TAG_TRUNCATED);
        String str5 = str + textFile + str2;
        if (str4.equals(TAG_TOMBSTONE) && textFile.contains(">>> system_server <<<")) {
            addTextToDropBox(dropBoxManager, "system_server_native_crash", str5, str3, i);
        }
        if (str4.equals(TAG_TOMBSTONE)) {
            FrameworkStatsLog.write(186);
        }
        addTextToDropBox(dropBoxManager, str4, str5, str3, i);
    }

    private static void addTextToDropBox(DropBoxManager dropBoxManager, String str, String str2, String str3, int i) {
        Slog.i(TAG, "Copying " + str3 + " to DropBox (" + str + ")");
        dropBoxManager.addText(str, str2);
        EventLog.writeEvent(DropboxLogTags.DROPBOX_FILE_COPY, str3, Integer.valueOf(i), str);
    }

    private static void addAuditErrorsToDropBox(DropBoxManager dropBoxManager, HashMap<String, Long> map, String str, int i, String str2) throws IOException {
        if (dropBoxManager == null || !dropBoxManager.isTagEnabled(str2)) {
            return;
        }
        Slog.i(TAG, "Copying audit failures to DropBox");
        File file = new File("/proc/last_kmsg");
        long jLastModified = file.lastModified();
        if (jLastModified <= 0) {
            file = new File("/sys/fs/pstore/console-ramoops");
            jLastModified = file.lastModified();
            if (jLastModified <= 0) {
                file = new File("/sys/fs/pstore/console-ramoops-0");
                jLastModified = file.lastModified();
            }
        }
        if (jLastModified <= 0) {
            return;
        }
        if (map.containsKey(str2) && map.get(str2).longValue() == jLastModified) {
            return;
        }
        map.put(str2, Long.valueOf(jLastModified));
        String textFile = FileUtils.readTextFile(file, i, TAG_TRUNCATED);
        StringBuilder sb = new StringBuilder();
        for (String str3 : textFile.split("\n")) {
            if (str3.contains("audit")) {
                sb.append(str3 + "\n");
            }
        }
        Slog.i(TAG, "Copied " + sb.toString().length() + " worth of audits to DropBox");
        dropBoxManager.addText(str2, str + sb.toString());
    }

    private static void addFsckErrorsToDropBoxAndLogFsStat(DropBoxManager dropBoxManager, HashMap<String, Long> map, String str, int i, String str2) throws IOException {
        boolean z = dropBoxManager != null && dropBoxManager.isTagEnabled(str2);
        Slog.i(TAG, "Checking for fsck errors");
        File file = new File("/dev/fscklogs/log");
        if (file.lastModified() <= 0) {
            return;
        }
        String textFile = FileUtils.readTextFile(file, i, TAG_TRUNCATED);
        Pattern patternCompile = Pattern.compile(FS_STAT_PATTERN);
        String[] strArrSplit = textFile.split("\n");
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        for (String str3 : strArrSplit) {
            if (str3.contains(FSCK_FS_MODIFIED)) {
                z2 = true;
            } else if (str3.contains("fs_stat")) {
                Matcher matcher = patternCompile.matcher(str3);
                if (matcher.find()) {
                    handleFsckFsStat(matcher, strArrSplit, i2, i3);
                    i2 = i3;
                } else {
                    Slog.w(TAG, "cannot parse fs_stat:" + str3);
                }
            }
            i3++;
        }
        if (z && z2) {
            addFileToDropBox(dropBoxManager, map, str, "/dev/fscklogs/log", i, str2);
        }
        file.delete();
    }

    private static void logFsMountTime() {
        int i;
        for (String str : MOUNT_DURATION_PROPS_POSTFIX) {
            int i2 = SystemProperties.getInt("ro.boottime.init.mount_all." + str, 0);
            if (i2 != 0) {
                str.hashCode();
                switch (str) {
                    case "late":
                        i = 12;
                        FrameworkStatsLog.write(239, i, i2);
                        break;
                    case "early":
                        i = 11;
                        FrameworkStatsLog.write(239, i, i2);
                        break;
                    case "default":
                        i = 10;
                        FrameworkStatsLog.write(239, i, i2);
                        break;
                }
            }
        }
    }

    private static void logSystemServerShutdownTimeMetrics() {
        String textFile;
        File file = new File(SHUTDOWN_METRICS_FILE);
        String str = null;
        if (file.exists()) {
            try {
                textFile = FileUtils.readTextFile(file, 0, null);
            } catch (IOException e) {
                Slog.e(TAG, "Problem reading " + file, e);
                textFile = null;
            }
        } else {
            textFile = null;
        }
        if (!TextUtils.isEmpty(textFile)) {
            String str2 = null;
            String str3 = null;
            String str4 = null;
            for (String str5 : textFile.split(SmsManager.REGEX_PREFIX_DELIMITER)) {
                String[] strArrSplit = str5.split(SettingsStringUtil.DELIMITER);
                if (strArrSplit.length != 2) {
                    Slog.e(TAG, "Wrong format of shutdown metrics - " + textFile);
                } else {
                    if (strArrSplit[0].startsWith(SHUTDOWN_TRON_METRICS_PREFIX)) {
                        logTronShutdownMetric(strArrSplit[0], strArrSplit[1]);
                        if (strArrSplit[0].equals(METRIC_SYSTEM_SERVER)) {
                            str4 = strArrSplit[1];
                        }
                    }
                    if (strArrSplit[0].equals("reboot")) {
                        str = strArrSplit[1];
                    } else if (strArrSplit[0].equals("reason")) {
                        str2 = strArrSplit[1];
                    } else if (strArrSplit[0].equals(METRIC_SHUTDOWN_TIME_START)) {
                        str3 = strArrSplit[1];
                    }
                }
            }
            logStatsdShutdownAtom(str, str2, str3, str4);
        }
        file.delete();
    }

    private static void logTronShutdownMetric(String str, String str2) {
        try {
            int i = Integer.parseInt(str2);
            if (i >= 0) {
                MetricsLogger.histogram(null, str, i);
            }
        } catch (NumberFormatException unused) {
            Slog.e(TAG, "Cannot parse metric " + str + " int value - " + str2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void logStatsdShutdownAtom(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "BootReceiver"
            if (r8 == 0) goto L2e
            java.lang.String r1 = "y"
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto Lf
            r8 = 1
            goto L34
        Lf:
            java.lang.String r1 = "n"
            boolean r1 = r8.equals(r1)
            if (r1 != 0) goto L33
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unexpected value for reboot : "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r8 = r1.append(r8)
            java.lang.String r8 = r8.toString()
            android.util.Slog.e(r0, r8)
            goto L33
        L2e:
            java.lang.String r8 = "No value received for reboot"
            android.util.Slog.e(r0, r8)
        L33:
            r8 = 0
        L34:
            r2 = r8
            if (r9 == 0) goto L38
            goto L3f
        L38:
            java.lang.String r8 = "No value received for shutdown reason"
            android.util.Slog.e(r0, r8)
            java.lang.String r9 = "<EMPTY>"
        L3f:
            r3 = r9
            r8 = 0
            if (r10 == 0) goto L60
            long r4 = java.lang.Long.parseLong(r10)     // Catch: java.lang.NumberFormatException -> L49
            goto L66
        L49:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "Cannot parse shutdown start time: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r10)
            java.lang.String r1 = r1.toString()
            android.util.Slog.e(r0, r1)
            goto L65
        L60:
            java.lang.String r1 = "No value received for shutdown start time"
            android.util.Slog.e(r0, r1)
        L65:
            r4 = r8
        L66:
            if (r11 == 0) goto L84
            long r8 = java.lang.Long.parseLong(r11)     // Catch: java.lang.NumberFormatException -> L6d
            goto L89
        L6d:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r1 = "Cannot parse shutdown duration: "
            java.lang.StringBuilder r11 = r11.append(r1)
            java.lang.StringBuilder r10 = r11.append(r10)
            java.lang.String r10 = r10.toString()
            android.util.Slog.e(r0, r10)
            goto L89
        L84:
            java.lang.String r10 = "No value received for shutdown duration"
            android.util.Slog.e(r0, r10)
        L89:
            r6 = r8
            r1 = 56
            com.android.internal.util.FrameworkStatsLog.write(r1, r2, r3, r4, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.BootReceiver.logStatsdShutdownAtom(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    private static void logFsShutdownTime() {
        File file;
        String[] strArr = LAST_KMSG_FILES;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                file = null;
                break;
            }
            file = new File(strArr[i]);
            if (file.exists()) {
                break;
            } else {
                i++;
            }
        }
        if (file == null) {
            return;
        }
        try {
            Matcher matcher = Pattern.compile(LAST_SHUTDOWN_TIME_PATTERN, 8).matcher(FileUtils.readTextFile(file, -16384, null));
            if (matcher.find()) {
                FrameworkStatsLog.write(239, 9, Integer.parseInt(matcher.group(1)));
                FrameworkStatsLog.write(242, 2, Integer.parseInt(matcher.group(2)));
                Slog.i(TAG, "boot_fs_shutdown," + matcher.group(1) + SmsManager.REGEX_PREFIX_DELIMITER + matcher.group(2));
            } else {
                FrameworkStatsLog.write(242, 2, 4);
                Slog.w(TAG, "boot_fs_shutdown, string not found");
            }
        } catch (IOException e) {
            Slog.w(TAG, "cannot read last msg", e);
        }
    }

    public static int fixFsckFsStat(String str, int i, String[] strArr, int i2, int i3) {
        String strTrim;
        boolean z;
        int i4;
        if ((i & 1024) != 0) {
            Pattern patternCompile = Pattern.compile(FSCK_PASS_PATTERN);
            Pattern patternCompile2 = Pattern.compile(FSCK_TREE_OPTIMIZATION_PATTERN);
            String strGroup = "";
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            int i5 = i2;
            while (i5 < i3) {
                String str2 = strArr[i5];
                if (str2.contains(FSCK_FS_MODIFIED)) {
                    break;
                }
                if (str2.startsWith("Pass ")) {
                    Matcher matcher = patternCompile.matcher(str2);
                    if (matcher.find()) {
                        strGroup = matcher.group(1);
                    }
                    i4 = 1;
                } else if (str2.startsWith("Inode ")) {
                    if (!patternCompile2.matcher(str2).find() || !strGroup.equals("1")) {
                        z = true;
                        strTrim = str2;
                        break;
                    }
                    Slog.i(TAG, "fs_stat, partition:" + str + " found tree optimization:" + str2);
                    i4 = 1;
                    z2 = true;
                } else if (str2.startsWith("[QUOTA WARNING]") && strGroup.equals("5")) {
                    Slog.i(TAG, "fs_stat, partition:" + str + " found quota warning:" + str2);
                    if (!z2) {
                        strTrim = str2;
                        z = false;
                        z3 = true;
                        break;
                    }
                    i4 = 1;
                    z3 = true;
                } else if (str2.startsWith("Update quota info") && strGroup.equals("5")) {
                    i4 = 1;
                } else if (str2.startsWith("Timestamp(s) on inode") && str2.contains("beyond 2310-04-04 are likely pre-1970") && strGroup.equals("1")) {
                    Slog.i(TAG, "fs_stat, partition:" + str + " found timestamp adjustment:" + str2);
                    int i6 = i5 + 1;
                    if (strArr[i6].contains("Fix? yes")) {
                        i5 = i6;
                    }
                    i4 = 1;
                    z4 = true;
                } else {
                    strTrim = str2.trim();
                    if (!strTrim.isEmpty() && !strGroup.isEmpty()) {
                        z = true;
                        break;
                    }
                    i4 = 1;
                }
                i5 += i4;
            }
            strTrim = null;
            z = false;
            if (z) {
                if (strTrim != null) {
                    Slog.i(TAG, "fs_stat, partition:" + str + " fix:" + strTrim);
                }
            } else if (z3 && !z2) {
                Slog.i(TAG, "fs_stat, got quota fix without tree optimization, partition:" + str);
            } else if ((z2 && z3) || z4) {
                Slog.i(TAG, "fs_stat, partition:" + str + " fix ignored");
                return i & (-1025);
            }
        }
        return i;
    }

    private static void handleFsckFsStat(Matcher matcher, String[] strArr, int i, int i2) {
        String strGroup = matcher.group(1);
        try {
            int iFixFsckFsStat = fixFsckFsStat(strGroup, Integer.decode(matcher.group(2)).intValue(), strArr, i, i2);
            if (AccountManager.KEY_USERDATA.equals(strGroup) || "data".equals(strGroup)) {
                FrameworkStatsLog.write(242, 3, iFixFsckFsStat);
            }
            Slog.i(TAG, "fs_stat, partition:" + strGroup + " stat:0x" + Integer.toHexString(iFixFsckFsStat));
        } catch (NumberFormatException unused) {
            Slog.w(TAG, "cannot parse fs_stat: partition:" + strGroup + " stat:" + matcher.group(2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x015e, code lost:
    
        if (r2 != false) goto L78;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d6 A[Catch: all -> 0x016a, PHI: r3
  0x00d6: PHI (r3v17 ??) = (r3v32 ??), (r3v33 ??), (r3v34 ??), (r3v35 ??), (r3v36 ??) binds: [B:55:0x00d4, B:62:0x00f9, B:67:0x0118, B:71:0x0135, B:76:0x015e] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #6 {, blocks: (B:4:0x0003, B:78:0x0162, B:56:0x00d6, B:81:0x0166, B:82:0x0169), top: B:88:0x0003 }] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.HashMap<java.lang.String, java.lang.Long> readTimestamps() {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.BootReceiver.readTimestamps():java.util.HashMap");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeTimestamps(HashMap<String, Long> map) {
        AtomicFile atomicFile = sFile;
        synchronized (atomicFile) {
            try {
                try {
                    FileOutputStream fileOutputStreamStartWrite = atomicFile.startWrite();
                    try {
                        FastXmlSerializer fastXmlSerializer = new FastXmlSerializer();
                        fastXmlSerializer.setOutput(fileOutputStreamStartWrite, StandardCharsets.UTF_8.name());
                        fastXmlSerializer.startDocument(null, true);
                        fastXmlSerializer.startTag(null, "log-files");
                        for (String str : map.keySet()) {
                            fastXmlSerializer.startTag(null, "log");
                            fastXmlSerializer.attribute(null, "filename", str);
                            fastXmlSerializer.attribute(null, "timestamp", map.get(str).toString());
                            fastXmlSerializer.endTag(null, "log");
                        }
                        fastXmlSerializer.endTag(null, "log-files");
                        fastXmlSerializer.endDocument();
                        sFile.finishWrite(fileOutputStreamStartWrite);
                    } catch (IOException e) {
                        Slog.w(TAG, "Failed to write timestamp file, using the backup: " + e);
                        sFile.failWrite(fileOutputStreamStartWrite);
                    }
                } catch (IOException e2) {
                    Slog.w(TAG, "Failed to write timestamp file: " + e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
