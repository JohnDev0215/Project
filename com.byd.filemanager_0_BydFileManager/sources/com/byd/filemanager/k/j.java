package com.byd.filemanager.k;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SharedPreferencesUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static SharedPreferences f3633a;

    public static int a(Context context, String str, int i) {
        if (f3633a == null) {
            f3633a = context.getSharedPreferences("config", 0);
        }
        return f3633a.getInt(str, i);
    }

    public static String b(Context context, String str, String str2) {
        if (f3633a == null) {
            f3633a = context.getSharedPreferences("config", 0);
        }
        return f3633a.getString(str, str2);
    }

    public static void c(Context context, String str, int i) {
        if (f3633a == null) {
            f3633a = context.getSharedPreferences("config", 0);
        }
        f3633a.edit().putInt(str, i).commit();
    }

    public static void d(Context context, String str, String str2) {
        if (f3633a == null) {
            f3633a = context.getSharedPreferences("config", 0);
        }
        f3633a.edit().putString(str, str2).commit();
    }
}
