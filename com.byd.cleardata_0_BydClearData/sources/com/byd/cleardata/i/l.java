package com.byd.cleardata.i;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static SharedPreferences f2716a;

    public static int a(Context context, String str, int i) {
        if (f2716a == null) {
            f2716a = context.getSharedPreferences("config", 0);
        }
        return f2716a.getInt(str, i);
    }

    public static String b(Context context, String str, String str2) {
        if (f2716a == null) {
            f2716a = context.getSharedPreferences("config", 0);
        }
        return f2716a.getString(str, str2);
    }

    public static void c(Context context, String str, int i) {
        if (f2716a == null) {
            f2716a = context.getSharedPreferences("config", 0);
        }
        f2716a.edit().putInt(str, i).commit();
    }

    public static void d(Context context, String str, String str2) {
        if (f2716a == null) {
            f2716a = context.getSharedPreferences("config", 0);
        }
        f2716a.edit().putString(str, str2).commit();
    }
}
