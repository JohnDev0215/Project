package com.byd.vrassistant.utils;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import com.byd.vrassistant.BydVoiceApplication;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class ForegroundAppUtil {
    private static final long END_TIME;
    private static final long START_TIME;
    private static final String TAG = "NEU_ForegroundAppUtil";
    private static final long TIME_INTERVAL = 604800000;

    static {
        long jCurrentTimeMillis = System.currentTimeMillis();
        END_TIME = jCurrentTimeMillis;
        START_TIME = jCurrentTimeMillis - TIME_INTERVAL;
    }

    public static String getForegroundActivityName(Context context) {
        RLog.i(TAG, "getForegroundActivityName:  other");
        UsageStats foregroundUsageStats = getForegroundUsageStats(context, START_TIME, END_TIME);
        String packageName = foregroundUsageStats != null ? foregroundUsageStats.getPackageName() : "";
        RLog.i(TAG, "getForegroundActivityName: currentClassName = " + packageName);
        return packageName;
    }

    public static boolean isForegroundApp(Context context) {
        return TextUtils.equals(getForegroundActivityName(context), context.getPackageName());
    }

    public static boolean isForegroundApp(Context context, String str) {
        return str.equals(getTopActivityPackage());
    }

    public static long getTotleForegroundTime(Context context) {
        getCurrentUsageStats(context, START_TIME, END_TIME);
        return 0L;
    }

    private static UsageStats getForegroundUsageStats(Context context, long j, long j2) {
        RLog.i(TAG, "getForegroundUsageStats: ");
        List<UsageStats> usageStatsList = getUsageStatsList(context, j, j2);
        UsageStats usageStats = null;
        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            for (UsageStats usageStats2 : usageStatsList) {
                if (usageStats == null || usageStats.getLastTimeUsed() < usageStats2.getLastTimeUsed()) {
                    RLog.i(TAG, "getForegroundUsageStats: getValues OK");
                    usageStats = usageStats2;
                }
            }
        }
        return usageStats;
    }

    public static UsageStats getCurrentUsageStats(Context context, long j, long j2) {
        List<UsageStats> usageStatsList = getUsageStatsList(context, j, j2);
        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            for (UsageStats usageStats : usageStatsList) {
                if (TextUtils.equals(usageStats.getPackageName(), context.getPackageName())) {
                    return usageStats;
                }
            }
        }
        return null;
    }

    public static List<UsageStats> getUsageStatsList(Context context, long j, long j2) {
        List<UsageStats> listQueryUsageStats = ((UsageStatsManager) context.getApplicationContext().getSystemService("usagestats")).queryUsageStats(4, j, j2);
        if (listQueryUsageStats != null && listQueryUsageStats.size() != 0) {
            return listQueryUsageStats;
        }
        Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
        intent.addFlags(268435456);
        context.getApplicationContext().startActivity(intent);
        return null;
    }

    public static String getTopActivity() {
        ComponentName componentName;
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) BydVoiceApplication.getContext().getSystemService("activity")).getRunningTasks(1);
        new AtomicBoolean(false);
        if (runningTasks.isEmpty() || (componentName = runningTasks.get(0).topActivity) == null) {
            return null;
        }
        RLog.e(TAG, "topActivity.getPackageName()=" + componentName.getPackageName() + ",cls = " + componentName.getClassName());
        return componentName.getClassName();
    }

    public static String getTopActivityPackage() {
        ComponentName componentName;
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) BydVoiceApplication.getContext().getSystemService("activity")).getRunningTasks(1);
        new AtomicBoolean(false);
        if (runningTasks.isEmpty() || (componentName = runningTasks.get(0).topActivity) == null) {
            return null;
        }
        RLog.e(TAG, "topActivity.getPackageName()=" + componentName.getPackageName() + ",cls = " + componentName.getClassName());
        return componentName.getPackageName();
    }

    public static boolean isStartedApp(String str) {
        RLog.i(TAG, "enter isStartedApp");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) BydVoiceApplication.getContext().getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            RLog.i(TAG, "runningApps is null");
            return false;
        }
        RLog.i(TAG, "runningApps size = " + runningAppProcesses.size());
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName != null && runningAppProcessInfo.processName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAppInstalled(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            Logger.error(TAG, "getApps: packageManager is null");
            return false;
        }
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        if (installedPackages == null || installedPackages.isEmpty()) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < installedPackages.size(); i++) {
            if (installedPackages.get(i).packageName.equals(str)) {
                Logger.debug(TAG, "isAppInstalled :" + str);
                z = true;
            }
        }
        return z;
    }

    public static boolean getAppEnabledResult(Context context, String str) {
        return context.getPackageManager().getApplicationEnabledSetting(str) == 1;
    }

    public static void killApp(Context context, String str) {
        RLog.i(TAG, "enter KillApp " + str);
        try {
            Class.forName("android.app.ActivityManager").getMethod("forceStopPackage", String.class).invoke((ActivityManager) context.getSystemService("activity"), str);
            RLog.i(TAG, "forceStop => " + str);
        } catch (Exception e) {
            RLog.e(TAG, "KillApp forceStopPackage fail", e);
        }
    }

    public static boolean startActivity(Context context, Intent intent) {
        RLog.i(TAG, "enter startActivity");
        if (context.getPackageManager().resolveActivity(intent, 65536) != null) {
            try {
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                RLog.i(TAG, "activity [" + intent.getComponent().getPackageName() + "] not found ", e);
                return false;
            }
        }
        RLog.i(TAG, "enter startActivity not found");
        return false;
    }
}
