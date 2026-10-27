package com.byd.cleardata.i;

import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(Object obj) {
        Log.println(3, "ClearData", i(o(obj)));
    }

    public static void b(String str) {
        Log.println(3, "ClearData", i(str));
    }

    public static void c(String str, String str2) {
        Log.println(3, str, i(str2));
    }

    public static void d(String str, Throwable th) {
        Log.println(3, "ClearData", j(str, th));
    }

    public static void e(String str, Object... objArr) {
        Log.println(3, "ClearData", i(n(str, objArr)));
    }

    public static void f(String str, Object... objArr) {
        if (objArr != null) {
            for (Object obj : objArr) {
                String strO = o(obj);
                if (strO != null) {
                    str = str.replaceFirst("%s", strO);
                }
            }
        }
        Log.println(3, "ClearData", i(str));
    }

    public static void g(String str) {
        Log.println(6, "ClearData", i(str));
    }

    public static void h(String str, Throwable th) {
        Log.println(6, "ClearData", j(str, th));
    }

    private static String i(String str) {
        return j(str, null);
    }

    private static String j(String str, Throwable th) {
        if (TextUtils.isEmpty(str)) {
            str = "NULL";
        }
        if (th == null) {
            return str + l();
        }
        return str + " : " + Log.getStackTraceString(th);
    }

    private static String k(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    private static String l() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return " (" + k(stackTrace[6].getClassName()) + "." + stackTrace[6].getMethodName() + ":" + stackTrace[6].getLineNumber() + ")";
    }

    public static void m(String str) {
        Log.println(4, "ClearData", i(str));
    }

    private static String n(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(" : ");
        for (int i = 0; i < objArr.length; i++) {
            sb.append("arg");
            sb.append(i);
            sb.append(" = ");
            sb.append(o(objArr[i]));
            if (i < objArr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private static String o(Object obj) {
        return obj == null ? "null" : !obj.getClass().isArray() ? obj.toString() : obj instanceof boolean[] ? Arrays.toString((boolean[]) obj) : obj instanceof byte[] ? Arrays.toString((byte[]) obj) : obj instanceof char[] ? Arrays.toString((char[]) obj) : obj instanceof short[] ? Arrays.toString((short[]) obj) : obj instanceof int[] ? Arrays.toString((int[]) obj) : obj instanceof long[] ? Arrays.toString((long[]) obj) : obj instanceof float[] ? Arrays.toString((float[]) obj) : obj instanceof double[] ? Arrays.toString((double[]) obj) : obj instanceof Object[] ? Arrays.deepToString((Object[]) obj) : "Couldn't find a correct type for the object";
    }
}
