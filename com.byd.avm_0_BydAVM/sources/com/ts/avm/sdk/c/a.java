package com.ts.avm.sdk.c;

import android.util.Log;

/* JADX INFO: compiled from: LogManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    private static String a(String str, String str2) {
        return String.format("%s::%s", str, str2);
    }

    public static void b(String str, String str2) {
        Log.d("AVM_SDK", a(str, str2));
    }

    public static void c(String str, String str2) {
        Log.e("AVM_SDK", a(str, str2));
    }

    public static void d(String str, String str2, Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace.length == 0) {
            Log.e("AVM_SDK", a(str, str2));
            return;
        }
        Log.e("AVM_SDK", a(str, str2) + " [class name]: " + stackTrace[0].getClassName() + " [line number]:" + stackTrace[0].getLineNumber());
    }

    public static void e(String str, String str2) {
        Log.i("AVM_SDK", a(str, str2));
    }

    public static void f(String str, String str2) {
        Log.v("AVM_SDK", a(str, str2));
    }
}
