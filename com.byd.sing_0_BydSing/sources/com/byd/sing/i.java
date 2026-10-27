package com.byd.sing;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SharedPreferencesUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static SharedPreferences f3250a;

    public static boolean a(Context context, String str, boolean z) {
        if (f3250a == null) {
            f3250a = context.getSharedPreferences("config", 0);
        }
        return f3250a.getBoolean(str, z);
    }

    public static boolean b(String str, boolean z) {
        return a(SingApplication.a(), str, z);
    }

    public static int c(Context context, String str, int i) {
        if (f3250a == null) {
            f3250a = context.getSharedPreferences("config", 0);
        }
        return f3250a.getInt(str, i);
    }

    public static int d(String str, int i) {
        return c(SingApplication.a(), str, i);
    }

    public static void e(Context context, String str, boolean z) {
        if (f3250a == null) {
            f3250a = context.getSharedPreferences("config", 0);
        }
        f3250a.edit().putBoolean(str, z).commit();
    }

    public static void f(String str, boolean z) {
        e(SingApplication.a(), str, z);
    }

    public static void g(Context context, String str, int i) {
        if (f3250a == null) {
            f3250a = context.getSharedPreferences("config", 0);
        }
        f3250a.edit().putInt(str, i).commit();
    }

    public static void h(String str, int i) {
        g(SingApplication.a(), str, i);
    }
}
