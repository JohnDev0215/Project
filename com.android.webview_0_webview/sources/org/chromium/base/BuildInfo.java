package org.chromium.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import defpackage.AbstractC1898zs;
import defpackage.As;
import defpackage.C1405qt;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BuildInfo {
    public static PackageInfo m = null;
    public static boolean n = false;
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
    public final String l;

    public BuildInfo(AbstractC1898zs abstractC1898zs) {
        n = true;
        try {
            Context context = Is.a;
            String packageName = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            long jD = d(packageInfo);
            this.b = jD;
            PackageInfo packageInfo2 = null;
            if (m != null) {
                this.c = m.packageName;
                this.d = d(m);
                this.e = c(m.versionName);
                m = null;
            } else {
                this.c = packageName;
                this.d = jD;
                this.e = c(packageInfo.versionName);
            }
            this.a = c(packageManager.getApplicationLabel(packageInfo.applicationInfo));
            this.f = c(packageManager.getInstallerPackageName(this.c));
            try {
                packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
            this.g = packageInfo2 != null ? String.valueOf(d(packageInfo2)) : "gms versionCode not available.";
            String str = "true";
            try {
                packageManager.getPackageInfo("projekt.substratum", 0);
            } catch (PackageManager.NameNotFoundException unused2) {
                str = "false";
            }
            this.k = str;
            this.l = "Not Enabled";
            this.h = TextUtils.join(", ", Build.SUPPORTED_ABIS);
            this.j = String.format("@%x_%x", Long.valueOf(this.d), Long.valueOf(packageInfo.lastUpdateTime));
            this.i = Build.FINGERPRINT.substring(0, Math.min(Build.FINGERPRINT.length(), 128));
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public static boolean a() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static boolean b() {
        return "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
    }

    public static String c(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence.toString();
    }

    public static long d(PackageInfo packageInfo) {
        return Build.VERSION.SDK_INT >= 28 ? C1405qt.a(packageInfo) : packageInfo.versionCode;
    }

    public static boolean e() {
        return Is.a.getApplicationInfo().targetSdkVersion >= 30;
    }

    public static String[] getAll() {
        BuildInfo buildInfo = As.a;
        String packageName = Is.a.getPackageName();
        String[] strArr = new String[25];
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
        strArr[19] = buildInfo.k;
        strArr[20] = buildInfo.l;
        strArr[21] = buildInfo.j;
        strArr[22] = a() ? "1" : "0";
        strArr[23] = e() ? "1" : "0";
        strArr[24] = b() ? "1" : "0";
        return strArr;
    }
}
