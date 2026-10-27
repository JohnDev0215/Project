package org.chromium.base;

import android.app.ActivityManager;
import android.os.StrictMode;
import android.util.Log;
import defpackage.xz;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class SysUtils {
    public static Boolean a;
    public static Integer b;

    public static int a() {
        Pattern patternCompile = Pattern.compile("^MemTotal:\\s+([0-9]+) kB$");
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                FileReader fileReader = new FileReader("/proc/meminfo");
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                Log.w("SysUtils", "/proc/meminfo lacks a MemTotal entry?");
                                break;
                            }
                            Matcher matcher = patternCompile.matcher(line);
                            if (matcher.find()) {
                                int i = Integer.parseInt(matcher.group(1));
                                if (i > 1024) {
                                    return i;
                                }
                                Log.w("SysUtils", "Invalid /proc/meminfo total size in kB: " + matcher.group(1));
                            }
                        } finally {
                            bufferedReader.close();
                        }
                    }
                } finally {
                    fileReader.close();
                }
            } catch (Exception e) {
                Log.w("SysUtils", "Cannot get total physical size from /proc/meminfo", e);
            }
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            return 0;
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    @CalledByNative
    public static int amountOfPhysicalMemoryKB() {
        if (b == null) {
            b = Integer.valueOf(a());
        }
        return b.intValue();
    }

    @CalledByNative
    public static boolean isCurrentlyLowMemory() {
        ActivityManager activityManager = (ActivityManager) xz.a.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isLowEndDevice() {
        /*
            java.lang.Boolean r0 = org.chromium.base.SysUtils.a
            if (r0 != 0) goto L44
            iv r0 = defpackage.iv.e()
            java.lang.String r1 = "enable-low-end-device-mode"
            boolean r0 = r0.g(r1)
            r1 = 1
            if (r0 == 0) goto L12
            goto L3e
        L12:
            iv r0 = defpackage.iv.e()
            java.lang.String r2 = "disable-low-end-device-mode"
            boolean r0 = r0.g(r2)
            r2 = 0
            if (r0 == 0) goto L20
            goto L3d
        L20:
            int r0 = a()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            org.chromium.base.SysUtils.b = r0
            int r0 = r0.intValue()
            if (r0 > 0) goto L31
            goto L3d
        L31:
            java.lang.Integer r0 = org.chromium.base.SysUtils.b
            int r0 = r0.intValue()
            r3 = 1024(0x400, float:1.435E-42)
            int r0 = r0 / r3
            if (r0 > r3) goto L3d
            goto L3e
        L3d:
            r1 = r2
        L3e:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            org.chromium.base.SysUtils.a = r0
        L44:
            java.lang.Boolean r0 = org.chromium.base.SysUtils.a
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.SysUtils.isLowEndDevice():boolean");
    }
}
