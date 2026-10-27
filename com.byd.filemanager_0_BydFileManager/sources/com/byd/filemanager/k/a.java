package com.byd.filemanager.k;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.text.TextUtils;
import com.byd.filemanager.FileApplication;
import com.byd.filemanager.bean.FileItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: AppUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final List<String> f3607a;

    static {
        ArrayList arrayList = new ArrayList();
        f3607a = arrayList;
        arrayList.add("com.android.bluetooth");
    }

    public static Locale a(Context context) {
        return context.getResources().getConfiguration().getLocales().get(0);
    }

    public static com.byd.filemanager.bean.a b(Context context, FileItem fileItem) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.setDataAndType(d.B(context, fileItem), d.r(fileItem.w()));
        PackageManager packageManager = context.getPackageManager();
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveInfoResolveActivity == null) {
            return null;
        }
        String str = resolveInfoResolveActivity.activityInfo.packageName;
        b.m("default app packageName = " + str);
        if ("android".equals(str) || "com.android.car.activityresolver".equals(str) || "com.android.car.frameworkpackagestubs".equals(str)) {
            return null;
        }
        com.byd.filemanager.bean.a aVar = new com.byd.filemanager.bean.a();
        aVar.g(resolveInfoResolveActivity.loadLabel(packageManager).toString());
        aVar.f(resolveInfoResolveActivity.loadIcon(packageManager));
        aVar.h(str);
        aVar.e(resolveInfoResolveActivity.activityInfo.name);
        return aVar;
    }

    public static List<com.byd.filemanager.bean.a> c(Context context, FileItem fileItem) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.setDataAndType(d.B(context, fileItem), d.r(fileItem.w()));
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 64)) {
            com.byd.filemanager.bean.a aVar = new com.byd.filemanager.bean.a();
            String str = resolveInfo.activityInfo.packageName;
            if (!"com.android.car.frameworkpackagestubs".equals(str)) {
                aVar.f(resolveInfo.loadIcon(packageManager));
                aVar.g(resolveInfo.loadLabel(packageManager).toString());
                aVar.h(str);
                aVar.e(resolveInfo.activityInfo.name);
                b.b("appInfo = " + aVar);
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public static List<com.byd.filemanager.bean.a> d(Context context, FileItem fileItem) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.SEND");
        String strR = d.r(fileItem.w());
        if (TextUtils.isEmpty(strR)) {
            strR = "application/octet-stream";
        }
        intent.setType(strR);
        intent.putExtra("android.intent.extra.STREAM", d.B(context, fileItem));
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 65536)) {
            com.byd.filemanager.bean.a aVar = new com.byd.filemanager.bean.a();
            String str = resolveInfo.activityInfo.packageName;
            if (!f3607a.contains(str)) {
                aVar.f(resolveInfo.loadIcon(packageManager));
                aVar.g(resolveInfo.loadLabel(packageManager).toString());
                aVar.h(str);
                aVar.e(resolveInfo.activityInfo.name);
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public static void e(Context context, FileItem fileItem) {
        g(true);
        d.J(context, fileItem);
    }

    public static boolean f(Context context, com.byd.filemanager.bean.a aVar, FileItem fileItem) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.setDataAndType(d.B(context, fileItem), d.r(fileItem.w()));
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
                intentFilter.addDataType(d.r(fileItem.w()));
                packageManager.addPreferredActivity(intentFilter, i, componentNameArr, componentName);
                return true;
            }
        } catch (Throwable th) {
            b.h("addPreferredActivity error", th);
        }
        return false;
    }

    public static void g(boolean z) {
        Settings.Secure.putInt(FileApplication.a().getContentResolver(), "install_non_market_apps", z ? 1 : 0);
    }
}
