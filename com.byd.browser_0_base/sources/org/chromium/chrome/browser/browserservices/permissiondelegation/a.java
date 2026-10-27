package org.chromium.chrome.browser.browserservices.permissiondelegation;

import android.app.NotificationChannel;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Base64;
import defpackage.a11;
import defpackage.fa1;
import defpackage.ft1;
import defpackage.jb3;
import defpackage.jg3;
import defpackage.jo1;
import defpackage.lb3;
import defpackage.oj2;
import defpackage.p61;
import defpackage.pp;
import defpackage.s50;
import defpackage.so1;
import defpackage.xn2;
import defpackage.xz;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: InstalledWebappPermissionManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final a11 a;
    public final PackageManager b;
    public final jg3 c;
    public final p61<jo1> d;

    public a(Context context, a11 a11Var, p61<jo1> p61Var, jg3 jg3Var) {
        this.b = context.getPackageManager();
        this.a = a11Var;
        this.d = p61Var;
        this.c = jg3Var;
    }

    public static a a() {
        return ((s50) pp.d()).g();
    }

    public static Boolean c(String str) {
        if (str == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = xz.a.getPackageManager().getPackageInfo(str, 4096);
            String[] strArr = packageInfo.requestedPermissions;
            int[] iArr = packageInfo.requestedPermissionsFlags;
            if (strArr != null) {
                boolean z = false;
                for (int i = 0; i < strArr.length; i++) {
                    if ("android.permission.ACCESS_COARSE_LOCATION".equals(strArr[i]) || "android.permission.ACCESS_FINE_LOCATION".equals(strArr[i])) {
                        if ((iArr[i] & 2) != 0) {
                            return Boolean.TRUE;
                        }
                        z = true;
                    }
                }
                if (z) {
                    return Boolean.FALSE;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            fa1.b("PermissionManager", "Couldn't find name for client package: %s", str);
        }
        return null;
    }

    public final HashSet b(ft1 ft1Var) {
        a11 a11Var = this.a;
        a11Var.getClass();
        HashSet hashSet = null;
        Set<String> stringSet = a11Var.a.getStringSet(a11.a(ft1Var), null);
        if (stringSet != null) {
            hashSet = new HashSet();
            Iterator<String> it = stringSet.iterator();
            while (it.hasNext()) {
                hashSet.add(new jb3(new lb3(Base64.decode(it.next(), 3))));
            }
        }
        return hashSet;
    }

    public final void d(int i, ft1 ft1Var) {
        this.a.a.edit().remove(a11.d(i, ft1Var)).remove(a11.e(i, ft1Var)).apply();
        InstalledWebappBridge.a(i);
    }

    public final void e(ft1 ft1Var, String str, int i, int i2) {
        String string;
        Boolean boolValueOf;
        Integer num;
        char c = 0;
        try {
            PackageManager packageManager = xz.a.getPackageManager();
            string = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            fa1.b("PermissionManager", "Couldn't find name for client package: %s", str);
        }
        if (TextUtils.isEmpty(string)) {
            fa1.b("PermissionManager", "Invalid details for client package: %s", string);
            string = null;
        }
        if (string == null) {
            return;
        }
        a11 a11Var = this.a;
        if (i == 4) {
            boolean z = i2 == 1;
            Integer numF = a11Var.f(i, ft1Var);
            if (numF == null) {
                boolValueOf = null;
            } else {
                boolValueOf = Boolean.valueOf(numF.intValue() == 1);
            }
            this.c.getClass();
            if (boolValueOf == null) {
                num = z ? 1 : 0;
            } else {
                num = (boolValueOf.booleanValue() || !z) ? (!boolValueOf.booleanValue() || z) ? null : 2 : 3;
            }
            if (num != null) {
                oj2.U(num.intValue(), 4, "TrustedWebActivity.LocationPermissionChanged");
            }
        }
        boolean z2 = !a11Var.g().contains(ft1Var.toString());
        SharedPreferences sharedPreferences = a11Var.a;
        if (!z2) {
            z2 = (i2 != sharedPreferences.getInt(a11.e(i, ft1Var), 3)) || (str.equals(sharedPreferences.getString(a11.c(ft1Var), null)) ^ true) || (string.equals(sharedPreferences.getString(a11.b(ft1Var), null)) ^ true);
        }
        HashSet hashSetG = a11Var.g();
        hashSetG.add(ft1Var.toString());
        sharedPreferences.edit().putStringSet("origins", hashSetG).apply();
        sharedPreferences.edit().putInt(a11.e(i, ft1Var), i2).putString(a11.c(ft1Var), str).putString(a11.b(ft1Var), string).apply();
        if (i == 5) {
            jo1 jo1Var = this.d.get();
            jo1Var.getClass();
            String string2 = ft1Var.toString();
            xn2 xn2Var = jo1Var.b;
            String strB = xn2Var.b(string2);
            if (!"sites".equals(strB)) {
                so1 so1Var = xn2Var.a;
                NotificationChannel notificationChannelG = so1Var.g(strB);
                if (notificationChannelG == null) {
                    c = 2;
                } else if (notificationChannelG.getImportance() == 0) {
                    c = 1;
                }
                if (c != 2) {
                    int i3 = c != 0 ? 2 : 1;
                    jo1Var.a.a.edit().putInt("pre_twa_notification_permission_setting." + ft1Var.toString(), i3).apply();
                    so1Var.a(strB);
                }
            }
        }
        if (z2) {
            InstalledWebappBridge.a(i);
        }
    }
}
