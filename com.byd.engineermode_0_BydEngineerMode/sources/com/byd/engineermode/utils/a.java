package com.byd.engineermode.utils;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: compiled from: BydLogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static void a(String str) {
        Log.println(3, "Diagnose", e(str));
    }

    public static void b(String str, String str2) {
        Log.println(3, str, e(str2));
    }

    public static void c(String str) {
        Log.println(6, "Diagnose", e(str));
    }

    public static void d(String str, Throwable th) {
        Log.println(6, "Diagnose", f(str, th));
    }

    private static String e(String str) {
        return f(str, null);
    }

    private static String f(String str, Throwable th) {
        if (TextUtils.isEmpty(str)) {
            str = "NULL";
        }
        if (th == null) {
            return str + h();
        }
        return str + " : " + Log.getStackTraceString(th);
    }

    private static String g(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    private static String h() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return " --- [" + g(stackTrace[6].getClassName()) + "." + stackTrace[6].getMethodName() + "() line:" + stackTrace[6].getLineNumber() + "]";
    }

    public static void i(String str) {
        Log.println(4, "Diagnose", e(str));
    }

    public static void j(String str) {
        Log.println(5, "Diagnose", e(str));
    }
}
