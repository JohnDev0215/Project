package defpackage;

import android.content.SharedPreferences;
import java.util.HashSet;

/* JADX INFO: compiled from: InstalledWebappPermissionStore.java */
/* JADX INFO: loaded from: classes.dex */
public final class a11 {
    public final SharedPreferences a;

    public a11() {
        ku2 ku2VarT = ku2.t();
        try {
            this.a = xz.a.getSharedPreferences("twa_permission_registry", 0);
            ku2VarT.close();
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public static String a(ft1 ft1Var) {
        return "all_delegate_apps." + ft1Var.toString();
    }

    public static String b(ft1 ft1Var) {
        return "app_name." + ft1Var.toString();
    }

    public static String c(ft1 ft1Var) {
        return "package_name." + ft1Var.toString();
    }

    public static String d(int i, ft1 ft1Var) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (i == 4) {
            str = "geolocation_permission.";
        } else {
            if (i != 5) {
                throw new IllegalStateException("Unsupported permission type.");
            }
            str = "notification_permission.";
        }
        sb.append(str);
        sb.append(ft1Var.toString());
        return sb.toString();
    }

    public static String e(int i, ft1 ft1Var) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (i == 4) {
            str = "geolocation_permission_setting.";
        } else {
            if (i != 5) {
                throw new IllegalStateException("Unsupported permission type.");
            }
            str = "notification_permission_setting.";
        }
        sb.append(str);
        sb.append(ft1Var.toString());
        return sb.toString();
    }

    public final Integer f(int i, ft1 ft1Var) {
        String strE = e(i, ft1Var);
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences.contains(strE)) {
            return Integer.valueOf(sharedPreferences.getInt(strE, 3));
        }
        String strD = d(i, ft1Var);
        if (sharedPreferences.contains(strD)) {
            return Integer.valueOf(sharedPreferences.getBoolean(strD, false) ? 1 : 2);
        }
        return null;
    }

    public final HashSet g() {
        ku2 ku2VarT = ku2.t();
        try {
            HashSet hashSet = new HashSet(this.a.getStringSet("origins", new HashSet()));
            ku2VarT.close();
            return hashSet;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
