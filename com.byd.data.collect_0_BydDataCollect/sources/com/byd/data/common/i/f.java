package com.byd.data.common.i;

import android.annotation.SuppressLint;
import android.util.Log;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: LogUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3426a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3427b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f3428c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f3429d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @SuppressLint({"SimpleDateFormat"})
    private static SimpleDateFormat f3430e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f3427b);
        stringBuffer.append("(");
        stringBuffer.append(f3428c);
        stringBuffer.append(":");
        stringBuffer.append(f3429d);
        stringBuffer.append(")");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static void b(String str, String str2) {
        if (Log.isLoggable(str, 3)) {
            Log.d(str, str2);
        }
    }

    public static void c(String str, String str2) {
        i(str, str2);
    }

    public static void d(String str, String str2) {
        e(str, str2);
    }

    public static void e(String str, String str2) {
        if (Log.isLoggable(str, 6)) {
            d.a.a.f.b.b.a.g(str, a(str2));
        }
    }

    private static void f(StackTraceElement[] stackTraceElementArr) {
        f3427b = stackTraceElementArr[1].getFileName();
        f3428c = stackTraceElementArr[1].getMethodName();
        f3429d = stackTraceElementArr[1].getLineNumber();
    }

    public static void g(String str, String str2) {
        if (Log.isLoggable(str, 4)) {
            Log.i(str, str2);
        }
    }

    public static void h(String str, String str2, Object... objArr) {
        g(str, String.format(str2, objArr));
    }

    public static void i(String str, String str2) {
        if (Log.isLoggable(str, 4)) {
            f(new Throwable().getStackTrace());
            Log.i(str, a(str2));
        }
    }

    public static void j(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str2);
        if (str3 != null && str3.length() > 4) {
            sb.append(str3.substring(str3.length() - 4));
        }
        g(str, sb.toString());
    }

    public static void k(String str, String str2) {
        if (Log.isLoggable(str, 5)) {
            Log.w(str, str2);
        }
    }
}
