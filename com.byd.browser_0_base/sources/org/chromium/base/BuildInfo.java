package org.chromium.base;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import defpackage.xz;
import defpackage.yj;
import defpackage.zj;
import defpackage.zt1;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class BuildInfo {
    public static ApplicationInfo n = null;
    public static String o = "";
    public final String a;
    public final long b;
    public final String c;
    public final long d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final boolean l;
    public final boolean m;

    public static class a {
        public static final BuildInfo a = new BuildInfo();
    }

    public BuildInfo() {
        String string;
        Context context = xz.a;
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        boolean zHasSystemFeature = false;
        PackageInfo packageInfoC = zt1.c(0, packageName);
        long longVersionCode = packageInfoC.getLongVersionCode();
        this.b = longVersionCode;
        this.c = packageName;
        this.d = longVersionCode;
        String str = packageInfoC.versionName;
        this.e = str == null ? "" : str.toString();
        n = context.getApplicationInfo();
        CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfoC.applicationInfo);
        this.a = applicationLabel == null ? "" : applicationLabel.toString();
        String installerPackageName = packageManager.getInstallerPackageName(packageName);
        this.f = installerPackageName != null ? installerPackageName.toString() : "";
        PackageInfo packageInfoC2 = zt1.c(0, "com.google.android.gms");
        this.g = packageInfoC2 != null ? String.valueOf(packageInfoC2.getLongVersionCode()) : "gms versionCode not available.";
        this.j = String.valueOf(zt1.e("projekt.substratum"));
        int i = zj.a;
        if (i != 0) {
            try {
                string = xz.a.getString(i);
            } catch (Exception unused) {
                string = "Not found";
            }
        } else {
            string = "Not Enabled";
        }
        this.k = string;
        this.h = TextUtils.join(", ", Build.SUPPORTED_ABIS);
        String str2 = Build.FINGERPRINT;
        this.i = str2.substring(0, Math.min(str2.length(), 128));
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        this.l = uiModeManager != null && uiModeManager.getCurrentModeType() == 4;
        try {
            zHasSystemFeature = packageManager.hasSystemFeature("android.hardware.type.automotive");
        } catch (SecurityException e) {
            Log.e("cr_BuildInfo", "Unable to query for Automotive system feature", e);
        }
        this.m = zHasSystemFeature;
    }

    @Deprecated
    public static boolean a() {
        return yj.a();
    }

    public static boolean b() {
        String str = Build.TYPE;
        return "eng".equals(str) || "userdebug".equals(str);
    }

    @CalledByNative
    public static String[] getAll() {
        BuildInfo buildInfo = a.a;
        buildInfo.getClass();
        String packageName = xz.a.getPackageName();
        String[] strArr = new String[28];
        strArr[0] = Build.BRAND;
        strArr[1] = Build.DEVICE;
        strArr[2] = Build.ID;
        strArr[3] = Build.MANUFACTURER;
        strArr[4] = Build.MODEL;
        strArr[5] = String.valueOf(Build.VERSION.SDK_INT);
        strArr[6] = Build.TYPE;
        strArr[7] = Build.BOARD;
        strArr[8] = packageName;
        strArr[9] = String.valueOf(buildInfo.b);
        strArr[10] = buildInfo.a;
        strArr[11] = buildInfo.c;
        strArr[12] = String.valueOf(buildInfo.d);
        strArr[13] = buildInfo.e;
        strArr[14] = buildInfo.i;
        strArr[15] = buildInfo.g;
        strArr[16] = buildInfo.f;
        strArr[17] = buildInfo.h;
        strArr[18] = o;
        strArr[19] = buildInfo.j;
        strArr[20] = buildInfo.k;
        strArr[21] = String.valueOf(xz.a.getApplicationInfo().targetSdkVersion);
        strArr[22] = b() ? "1" : "0";
        strArr[23] = buildInfo.l ? "1" : "0";
        strArr[24] = Build.VERSION.INCREMENTAL;
        strArr[25] = Build.HARDWARE;
        strArr[26] = yj.a() ? "1" : "0";
        strArr[27] = buildInfo.m ? "1" : "0";
        return strArr;
    }
}
