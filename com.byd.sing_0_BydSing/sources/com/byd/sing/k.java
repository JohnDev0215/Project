package com.byd.sing;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.RemoteException;
import android.text.TextUtils;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.android.volley.BuildConfig;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ThirdAppUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f3255a = {"com.stingray.karaoke.car", "com.huiaichang.byd.desktop", "com.tencentbyd.karaokecar", "com.kugou.android.ktvapp", "cn.banshenggua.aichang", "com.changba", "com.tencent.karaoke", "com.loostone.player", "com.stingray.karaoke.car.byd8155f"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f3256b = {86, 850, 375, 7};

    private static boolean a(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : f3255a) {
                if (str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int b() {
        ComponentName componentName;
        ComponentName componentName2;
        ActivityManager.StackInfo stackInfoC = c(true);
        if (stackInfoC != null && (componentName2 = stackInfoC.topActivity) != null) {
            String packageName = componentName2.getPackageName();
            a.a("mainPackageName = " + packageName);
            if (a(packageName)) {
                return 1;
            }
        }
        ActivityManager.StackInfo stackInfoC2 = c(false);
        if (stackInfoC2 != null && (componentName = stackInfoC2.topActivity) != null) {
            String packageName2 = componentName.getPackageName();
            a.a("subPackageName = " + packageName2);
            if (a(packageName2)) {
                return 2;
            }
        }
        String strD = d();
        a.a("topPackageName = " + strD);
        return a(strD) ? 0 : -1;
    }

    public static ActivityManager.StackInfo c(boolean z) {
        try {
            return ActivityTaskManager.getService().getStackInfo(z ? 3 : 4, 0);
        } catch (RemoteException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String d() {
        String packageName;
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) SingApplication.a().getSystemService("activity")).getRunningTasks(1);
        if (runningTasks == null || runningTasks.isEmpty()) {
            packageName = BuildConfig.FLAVOR;
        } else {
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
            runningTaskInfo.topActivity.getClassName();
            packageName = runningTaskInfo.topActivity.getPackageName();
        }
        a.a("packageName = " + packageName);
        return packageName;
    }

    public static boolean e() {
        if (a(d())) {
            return true;
        }
        boolean zF = f();
        a.a("isKaraokeAppsTopOnSpilt = " + zF);
        return zF;
    }

    public static boolean f() {
        boolean zA;
        ComponentName componentName;
        ComponentName componentName2;
        ActivityManager.StackInfo stackInfoC = c(true);
        if (stackInfoC == null || (componentName2 = stackInfoC.topActivity) == null) {
            zA = false;
        } else {
            String packageName = componentName2.getPackageName();
            a.a("mainPackageName = " + packageName);
            zA = a(packageName);
        }
        if (zA) {
            return true;
        }
        ActivityManager.StackInfo stackInfoC2 = c(false);
        if (stackInfoC2 == null || (componentName = stackInfoC2.topActivity) == null) {
            return false;
        }
        String packageName2 = componentName.getPackageName();
        a.a("subPackageName = " + packageName2);
        return a(packageName2);
    }

    public static boolean g() {
        int iA = b.a();
        int[] iArr = f3256b;
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            if (iA == Integer.valueOf(iArr[i]).intValue()) {
                a.a("countryCode = " + iA);
                break;
            }
            i++;
        }
        a.a("isKaraokeSupport = " + z);
        return z;
    }

    public static boolean h() {
        boolean zI = i("com.stingray.karaoke.car.byd8155f");
        a.a("isStingrayInstalled = " + zI);
        return zI;
    }

    public static boolean i(String str) {
        a.a("isThirdAppInstall");
        Iterator<ApplicationInfo> it = SingApplication.a().getPackageManager().getInstalledApplications(IdentityHashMap.DEFAULT_SIZE).iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().packageName)) {
                return true;
            }
        }
        return false;
    }

    public static void j() {
        if (h()) {
            k(SingApplication.a(), "com.stingray.karaoke.car.byd8155f");
        } else {
            k(SingApplication.a(), "com.byd.localmusic");
        }
    }

    public static void k(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                a.a("toOtherAppForRecent pkg:" + str);
                context.startActivity(launchIntentForPackage);
            } else {
                a.b("AppInfoUtil recent", str + ":intent --> null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
