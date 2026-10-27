package c.a.b.d;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SpUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SharedPreferences f2341a;

    public static boolean a(String str, boolean z) {
        return d().getBoolean(str, z);
    }

    public static int b(String str, int i2) {
        return d().getInt(str, i2);
    }

    public static long c(String str, long j) {
        return d().getLong(str, j);
    }

    public static SharedPreferences d() {
        return f2341a;
    }

    public static String e(String str, String str2) {
        return d().getString(str, str2);
    }

    public static void f(Context context) {
        f2341a = context.getSharedPreferences("shared_data_file", 0);
    }

    public static void g(String str, Boolean bool) {
        d().edit().putBoolean(str, bool.booleanValue()).apply();
    }

    public static void h(String str, int i2) {
        d().edit().putInt(str, i2).apply();
    }

    public static void i(String str, long j) {
        d().edit().putLong(str, j).apply();
    }

    public static void j(String str, String str2) {
        d().edit().putString(str, str2).apply();
    }

    public static void k(String str) {
        d().edit().remove(str).apply();
    }
}
