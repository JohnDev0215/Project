package com.ts.appservice.engineeringmode.e;

import android.util.Log;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f57a = true;

    public static void a(String str, String str2) {
        if (!f57a) {
            Log.d(str, str2);
            return;
        }
        Log.d("EngineeringMode", str + " " + str2);
    }

    public static void b(String str, Exception exc) {
        c(str, f(exc));
    }

    public static void c(String str, String str2) {
        if (!f57a) {
            Log.e(str, str2);
            return;
        }
        Log.e("EngineeringMode", str + " " + str2);
    }

    public static void d(String str, String str2, Throwable th) {
        if (!f57a) {
            Log.e(str, str2, th);
            return;
        }
        Log.e("EngineeringMode", str + " " + str2, th);
    }

    public static void e(String str, String str2) {
        if (!f57a) {
            Log.i(str, str2);
            return;
        }
        Log.i("EngineeringMode", str + " " + str2);
    }

    public static String f(Exception exc) {
        if (exc == null) {
            return null;
        }
        return Log.getStackTraceString(exc);
    }

    public static void g(String str, String str2) {
        if (!f57a) {
            Log.w(str, str2);
            return;
        }
        Log.w("EngineeringMode", str + " " + str2);
    }
}
