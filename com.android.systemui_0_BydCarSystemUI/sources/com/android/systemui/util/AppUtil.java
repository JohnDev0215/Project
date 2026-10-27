package com.android.systemui.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.android.systemui.Constants;
import com.android.systemui.SystemUIApplication;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppUtil {
    public static void toOtherAppActivity(Context context, String pkg, String activity) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(pkg, activity));
            BydLogHelper.logD("toOtherApp pkg:" + pkg);
            intent.setFlags(270532608);
            context.startActivity(intent);
        } catch (Exception e) {
            BydLogHelper.logD("AppInfoUtil", activity + " :" + e);
            e.printStackTrace();
        }
    }

    public static void toOtherApp(Context context, String pkg) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(pkg);
            if (launchIntentForPackage != null) {
                BydLogHelper.logD("toOtherApp pkg:" + pkg);
                launchIntentForPackage.setFlags(270532608);
                context.startActivity(launchIntentForPackage);
            } else {
                BydLogHelper.logD("AppInfoUtil", pkg + ":intent --> null");
            }
        } catch (Exception e) {
            BydLogHelper.logD("AppInfoUtil", pkg + " :" + e);
            e.printStackTrace();
        }
    }

    public static void toOtherAppForRecent(Context context, String pkg) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(pkg);
            if (launchIntentForPackage != null) {
                BydLogHelper.logD("toOtherAppForRecent pkg:" + pkg);
                context.startActivity(launchIntentForPackage);
            } else {
                BydLogHelper.logD("AppInfoUtil recent", pkg + ":intent --> null");
            }
        } catch (Exception e) {
            BydLogHelper.logD("AppInfoUtil recent", pkg + " :" + e);
            e.printStackTrace();
        }
    }

    public static void toOtherAppForRecent(Context context, ComponentName componentName) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER").setComponent(componentName);
            context.startActivity(intent);
        } catch (Exception e) {
            BydLogHelper.logD("AppInfoUtil recent", componentName.getPackageName() + " :" + e);
            e.printStackTrace();
        }
    }

    public static void toOtherApp(Context context, String pkg, String action) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(pkg, action));
            intent.setFlags(270532608);
            context.startActivity(intent);
        } catch (Exception e) {
            BydLogHelper.logD("AppInfoUtil", pkg + " --> 未安装");
            e.printStackTrace();
        }
    }

    public static void startAc(Context context, String module) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.byd.hvac", Constants.PackageInfo.PKG_AIR_MAIN));
            intent.setFlags(270532608);
            intent.putExtra("module", module);
            context.startActivity(intent);
        } catch (Exception e) {
            BydLogHelper.logD("AppInfoUtil", "e:" + e);
            e.printStackTrace();
        }
    }

    public static void startCarSettings(String activity) {
        PackageManager packageManager = SystemUIApplication.getInstance().getPackageManager();
        String str = "byd_pad://byd_car_settings:2935/launcher?action=" + activity;
        BydLogHelper.logD("scheme : " + str);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        BydLogHelper.logD("in : " + intent);
        BydLogHelper.logD("resolveInfos : " + listQueryIntentServices);
        BydLogHelper.logD("resolveInfos isEmpty : " + listQueryIntentServices.isEmpty());
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        SystemUIApplication.getInstance().startService(intent);
    }

    public static void finishScreenSaver() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=finish_screen_saver"));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        if (SystemUIApplication.getInstance().getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
            return;
        }
        SystemUIApplication.getInstance().startService(intent);
    }

    public static boolean isForeground(Context context, String className) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        if (context == null || TextUtils.isEmpty(className) || (runningTasks = ((ActivityManager) context.getSystemService(Constants.PackageInfo.ACTIVITY)).getRunningTasks(1)) == null || runningTasks.size() <= 0) {
            return false;
        }
        return className.equals(runningTasks.get(0).topActivity.getClassName());
    }

    public static boolean isAppInstalled(Context context, String pkgName) {
        try {
        } catch (Exception e) {
            Log.w("AppUtil", "pkgName:" + pkgName + " :" + e);
        }
        return context.getPackageManager().getApplicationInfo(pkgName, 0) != null;
    }

    public static boolean isVrInstalled(Context context) {
        for (String str : Constants.VR_PACKAGE_ARRAY) {
            if (isAppInstalled(context, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDvrInstalled(Context context) {
        boolean zIsAppInstalled = isAppInstalled(context, "com.byd.dvr");
        Log.d("AppUtil", "isDvrInstalled: " + zIsAppInstalled);
        return zIsAppInstalled;
    }

    public static int getScreenWidth() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) SystemUIApplication.getInstance().getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            return isLandscape(SystemUIApplication.getInstance()) ? Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels) : Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static boolean isUKE(Context context) {
        int autoType;
        try {
            autoType = BYDAutoBodyworkDevice.getInstance(context).getAutoType();
        } catch (Throwable th) {
            th.printStackTrace();
            autoType = 0;
        }
        BydLogHelper.logD("carType = " + autoType);
        return autoType == 182 || autoType == 208;
    }

    public static boolean isMC(Context context) {
        int autoType;
        try {
            autoType = BYDAutoBodyworkDevice.getInstance(context).getAutoType();
        } catch (Throwable th) {
            th.printStackTrace();
            autoType = 0;
        }
        BydLogHelper.logD("carType = " + autoType);
        return autoType == 180;
    }
}
