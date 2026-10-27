package org.chromium.base;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import defpackage.Es;
import defpackage.Is;
import defpackage.Lt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SysUtils {
    public static Boolean a;
    public static Integer b;

    public static boolean isCurrentlyLowMemory() {
        ActivityManager activityManager = (ActivityManager) Is.a.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    public static boolean isLowEndDevice() {
        int i;
        FileReader fileReader;
        if (a == null) {
            if (!Es.e().h("enable-low-end-device-mode")) {
                if (Es.e().h("disable-low-end-device-mode")) {
                    z = false;
                } else {
                    Pattern patternCompile = Pattern.compile("^MemTotal:\\s+([0-9]+) kB$");
                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        try {
                            fileReader = new FileReader("/proc/meminfo");
                        } catch (Exception e) {
                            Log.w("SysUtils", "Cannot get total physical size from /proc/meminfo", e);
                        }
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
                                        i = Integer.parseInt(matcher.group(1));
                                        if (i <= 1024) {
                                            Log.w("SysUtils", "Invalid /proc/meminfo total size in kB: " + matcher.group(1));
                                        } else {
                                            bufferedReader.close();
                                            fileReader.close();
                                        }
                                    }
                                } finally {
                                    bufferedReader.close();
                                }
                            }
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                            i = 0;
                            Integer numValueOf = Integer.valueOf(i);
                            b = numValueOf;
                            boolean z = numValueOf.intValue() > 0 && (Build.VERSION.SDK_INT < 26 ? b.intValue() / 1024 <= 512 : b.intValue() / 1024 <= 1024);
                            Context context = Is.a;
                            Lt.a.b("Android.SysUtilsLowEndMatches", z == (context != null ? ((ActivityManager) context.getSystemService("activity")).isLowRamDevice() : false));
                            z = z;
                        } finally {
                            fileReader.close();
                        }
                    } finally {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    }
                }
            }
            a = Boolean.valueOf(z);
        }
        return a.booleanValue();
    }
}
