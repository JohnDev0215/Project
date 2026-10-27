package org.chromium.base;

import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class SysUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ANDROID_LOW_MEMORY_DEVICE_THRESHOLD_MB = 512;
    private static final String TAG = "SysUtils";
    private static Boolean sLowEndDevice;

    private SysUtils() {
    }

    private static int amountOfPhysicalMemoryKB() {
        FileReader fileReader;
        Pattern patternCompile = Pattern.compile("^MemTotal:\\s+([0-9]+) kB$");
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                fileReader = new FileReader("/proc/meminfo");
            } catch (Exception e) {
                android.util.Log.w(TAG, "Cannot get total physical size from /proc/meminfo", e);
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            android.util.Log.w(TAG, "/proc/meminfo lacks a MemTotal entry?");
                            break;
                        }
                        Matcher matcher = patternCompile.matcher(line);
                        if (matcher.find()) {
                            int i = Integer.parseInt(matcher.group(1));
                            if (i > 1024) {
                                return i;
                            }
                            android.util.Log.w(TAG, "Invalid /proc/meminfo total size in kB: " + matcher.group(1));
                        }
                    } finally {
                        bufferedReader.close();
                    }
                }
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                return 0;
            } finally {
                fileReader.close();
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public static boolean isLowEndDevice() {
        if (sLowEndDevice == null) {
            sLowEndDevice = Boolean.valueOf(detectLowEndDevice());
        }
        return sLowEndDevice.booleanValue();
    }

    private static boolean detectLowEndDevice() {
        if (CommandLine.getInstance().hasSwitch(BaseSwitches.ENABLE_LOW_END_DEVICE_MODE)) {
            return true;
        }
        if (CommandLine.getInstance().hasSwitch(BaseSwitches.DISABLE_LOW_END_DEVICE_MODE)) {
            return false;
        }
        int iAmountOfPhysicalMemoryKB = amountOfPhysicalMemoryKB();
        return iAmountOfPhysicalMemoryKB > 0 && iAmountOfPhysicalMemoryKB / 1024 <= 512;
    }
}
