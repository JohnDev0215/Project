package com.core.system.service.common;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;
import com.ts.car.sdk.core.Logger;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    private static boolean isStartAccessibilityServiceEnable(Context context, String packageName) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        List<AccessibilityServiceInfo> accessibilityServices = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        for (AccessibilityServiceInfo info : accessibilityServices) {
            if (info.getId().contains(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static void openAccessibilityService(Context context, String packageName, String className) {
        if (!isStartAccessibilityServiceEnable(context, packageName)) {
            Settings.Secure.putString(context.getContentResolver(), "enabled_accessibility_services", className);
            Settings.Secure.putInt(context.getContentResolver(), "accessibility_enabled", 1);
        }
    }

    public static String getPackageName(Context context, int pid) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            Logger.warning("getPackageName, activityManager is null! pid:" + pid);
            return null;
        }
        List<ActivityManager.RunningAppProcessInfo> processesList = activityManager.getRunningAppProcesses();
        if (processesList == null || processesList.size() == 0) {
            return null;
        }
        for (int i = 0; i < processesList.size(); i++) {
            try {
                ActivityManager.RunningAppProcessInfo info = processesList.get(i);
                if (info.pid == pid) {
                    String processName = info.processName;
                    return processName;
                }
                continue;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static boolean isSameSignatures(Context context, int callingUid) {
        PackageManager packageManager = context.getPackageManager();
        int signatures = packageManager.checkSignatures(callingUid, Process.myUid());
        Logger.debug("isSameSignatures callingUid : " + callingUid + " signatures : " + signatures);
        return signatures == 0;
    }
}
