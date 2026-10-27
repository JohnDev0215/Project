package g5;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SharedPreferencesUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SharedPreferences f5087a;

    public static boolean a(Context context, String str) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        return f5087a.contains(str);
    }

    public static boolean b(Context context, String str, boolean z7) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        return f5087a.getBoolean(str, z7);
    }

    public static double c(Context context, String str, double d8) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        return Double.longBitsToDouble(f5087a.getLong(str, Double.doubleToRawLongBits(d8)));
    }

    public static int d(Context context, String str, int i8) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        return f5087a.getInt(str, i8);
    }

    public static long e(Context context, String str, long j8) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        return f5087a.getLong(str, j8);
    }

    public static String f(Context context, String str, String str2) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        return f5087a.getString(str, str2);
    }

    public static void g(Context context, String str, boolean z7) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        f5087a.edit().putBoolean(str, z7).commit();
    }

    public static void h(Context context, String str, double d8) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        f5087a.edit().putLong(str, Double.doubleToRawLongBits(d8)).commit();
    }

    public static void i(Context context, String str, int i8) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        f5087a.edit().putInt(str, i8).commit();
    }

    public static void j(Context context, String str, long j8) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        f5087a.edit().putLong(str, j8).commit();
    }

    public static void k(Context context, String str, String str2) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        f5087a.edit().putString(str, str2).commit();
    }

    public static void l(Context context, String str) {
        if (f5087a == null) {
            f5087a = context.getSharedPreferences("config", 0);
        }
        f5087a.edit().remove(str).commit();
    }
}
