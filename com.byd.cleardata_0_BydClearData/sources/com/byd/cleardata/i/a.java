package com.byd.cleardata.i;

import android.annotation.SuppressLint;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final List<String> f2692a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final List<String> f2693b = Arrays.asList("com.byd.datashared", "vendor.qti.hardware.cacert.server", "com.byd.uereport", "android", "com.byd.data.collect", "com.byd.vrassistant", "com.byd.autoservice", "com.qualcomm.location", "com.byd.ota", "com.byd.card", "com.byd.sing", "com.byd.water.wallpaper", "com.byd.logtools", "com.byd.secretcode", "com.spotify.music", "com.byd.engineermode", "com.byd.globalnotification", "com.byd.cleardata", "com.idd.hidps");

    static {
        ArrayList arrayList = new ArrayList();
        f2692a = arrayList;
        arrayList.add("com.android.bluetooth");
    }

    @SuppressLint({"NewApi"})
    public static long[] a(Context context, PackageInfo packageInfo) {
        StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService("storagestats");
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        PackageManager packageManager = context.getPackageManager();
        storageManager.getStorageVolumes();
        long[] jArr = new long[3];
        try {
            StorageStats storageStatsQueryStatsForPackage = storageStatsManager.queryStatsForPackage(StorageManager.UUID_DEFAULT, packageInfo.packageName, UserHandle.getUserHandleForUid(packageManager.getApplicationInfo(packageInfo.packageName, 128).uid));
            jArr[0] = storageStatsQueryStatsForPackage.getAppBytes();
            jArr[1] = storageStatsQueryStatsForPackage.getCacheBytes();
            jArr[2] = storageStatsQueryStatsForPackage.getDataBytes();
        } catch (PackageManager.NameNotFoundException | IOException | RuntimeException e) {
            c.g(", e: " + e.toString());
        }
        return jArr;
    }

    public static Locale b(Context context) {
        return context.getResources().getConfiguration().getLocales().get(0);
    }

    public static com.byd.cleardata.d.a c(Context context, com.byd.cleardata.d.d dVar) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.setDataAndType(e.C(context, dVar), e.r(dVar.w()));
        PackageManager packageManager = context.getPackageManager();
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveInfoResolveActivity == null) {
            return null;
        }
        String str = resolveInfoResolveActivity.activityInfo.packageName;
        c.b("packageName = " + str);
        if ("android".equals(str) || "com.android.car.activityresolver".equals(str) || "com.android.car.frameworkpackagestubs".equals(str)) {
            return null;
        }
        com.byd.cleardata.d.a aVar = new com.byd.cleardata.d.a();
        aVar.g(resolveInfoResolveActivity.loadLabel(packageManager).toString());
        aVar.f(resolveInfoResolveActivity.loadIcon(packageManager));
        aVar.h(str);
        aVar.e(resolveInfoResolveActivity.activityInfo.name);
        return aVar;
    }

    public static List<com.byd.cleardata.d.b> d(Context context, boolean z) {
        String string;
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
            boolean z2 = (packageInfo.applicationInfo.flags & 1) > 0;
            if (!f2693b.contains(packageInfo.packageName) && ((string = packageInfo.applicationInfo.loadLabel(packageManager).toString()) == null || (!string.contains("com.") && !string.contains("ts.")))) {
                if (!packageInfo.packageName.contains("com.ts.") && !packageInfo.packageName.contains("com.android.") && !packageInfo.packageName.contains("com.core.") && !packageInfo.packageName.contains("com.google.android.") && !packageInfo.packageName.contains("android.ext.") && !packageInfo.packageName.contains("com.qualcomm.")) {
                    com.byd.cleardata.d.b bVar = new com.byd.cleardata.d.b(packageInfo, packageInfo.applicationInfo.loadIcon(packageManager), string, false);
                    bVar.F(packageInfo.versionName);
                    bVar.D(packageInfo.firstInstallTime);
                    long[] jArrA = a(context, packageInfo);
                    bVar.A(jArrA[0]);
                    bVar.z(jArrA[1]);
                    bVar.B(jArrA[2]);
                    bVar.E(z2);
                    bVar.C(packageManager.getApplicationEnabledSetting(packageInfo.packageName) == 2);
                    arrayList.add(bVar);
                }
            }
        }
        c.b("list size = " + arrayList.size());
        return arrayList;
    }

    public static List<com.byd.cleardata.d.a> e(Context context, com.byd.cleardata.d.d dVar) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.setDataAndType(e.C(context, dVar), e.r(dVar.w()));
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 64)) {
            com.byd.cleardata.d.a aVar = new com.byd.cleardata.d.a();
            String str = resolveInfo.activityInfo.packageName;
            if (!"com.android.car.frameworkpackagestubs".equals(str)) {
                aVar.f(resolveInfo.loadIcon(packageManager));
                aVar.g(resolveInfo.loadLabel(packageManager).toString());
                aVar.h(str);
                aVar.e(resolveInfo.activityInfo.name);
                c.b("appInfo = " + aVar);
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public static List<com.byd.cleardata.d.a> f(Context context, com.byd.cleardata.d.d dVar) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.SEND");
        String strR = e.r(dVar.w());
        if (TextUtils.isEmpty(strR)) {
            strR = "application/octet-stream";
        }
        intent.setType(strR);
        intent.putExtra("android.intent.extra.STREAM", e.C(context, dVar));
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 65536)) {
            com.byd.cleardata.d.a aVar = new com.byd.cleardata.d.a();
            String str = resolveInfo.activityInfo.packageName;
            if (!f2692a.contains(str)) {
                aVar.f(resolveInfo.loadIcon(packageManager));
                aVar.g(resolveInfo.loadLabel(packageManager).toString());
                aVar.h(str);
                aVar.e(resolveInfo.activityInfo.name);
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public static String g(long j) {
        return String.valueOf(j.a(2, (j * 1.0f) / r0.g)) + h(j).name();
    }

    private static n h(long j) {
        float f = j * 1.0f;
        n nVar = n.GB;
        if (f / nVar.g >= 1.0f) {
            return nVar;
        }
        n nVar2 = n.MB;
        if (f / nVar2.g >= 1.0f) {
            return nVar2;
        }
        n nVar3 = n.KB;
        return f / ((float) nVar3.g) >= 1.0f ? nVar3 : n.B;
    }

    public static boolean i() {
        String str = SystemProperties.get("persist.vendor.byd.cfg.huc.steering.wheel.position", "0");
        c.b("isRightRudder position = " + str);
        return Integer.parseInt(str) == 1;
    }

    public static boolean j(Context context, com.byd.cleardata.d.a aVar, com.byd.cleardata.d.d dVar) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.setDataAndType(e.C(context, dVar), e.r(dVar.w()));
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        int size = listQueryIntentActivities.size();
        ComponentName componentName = new ComponentName(aVar.d(), aVar.a());
        ComponentName[] componentNameArr = new ComponentName[size];
        try {
            if (!listQueryIntentActivities.isEmpty()) {
                int i = -1;
                for (int i2 = 0; i2 < size; i2++) {
                    ResolveInfo resolveInfo = listQueryIntentActivities.get(i2);
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    String str = activityInfo.packageName;
                    String str2 = activityInfo.name;
                    componentNameArr[i2] = new ComponentName(str, str2);
                    packageManager.clearPackagePreferredActivities(str);
                    if (str.equals(componentName.getPackageName()) && str2.equals(componentName.getClassName())) {
                        i = resolveInfo.match;
                    }
                }
                if (i == -1) {
                    return false;
                }
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.VIEW");
                intentFilter.addCategory("android.intent.category.DEFAULT");
                intentFilter.addDataType(e.r(dVar.w()));
                packageManager.addPreferredActivity(intentFilter, i, componentNameArr, componentName);
                return true;
            }
        } catch (Throwable th) {
            c.h("addPreferredActivity error", th);
        }
        return false;
    }
}
