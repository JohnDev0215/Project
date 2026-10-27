package com.byd.sing;

import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;

/* JADX INFO: compiled from: BydLogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static void a(String str) {
        Log.println(3, "Sing", h(str));
    }

    public static void b(String str, String str2) {
        Log.println(3, str, h(str2));
    }

    public static void c(String str, Throwable th) {
        Log.println(3, "Sing", i(str, th));
    }

    public static void d(String str, Object... objArr) {
        Log.println(3, "Sing", h(l(str, objArr)));
    }

    public static void e(String str, Object... objArr) {
        if (objArr != null) {
            for (Object obj : objArr) {
                String strM = m(obj);
                if (strM != null) {
                    str = str.replaceFirst("%s", strM);
                }
            }
        }
        Log.println(3, "Sing", h(str));
    }

    public static void f(String str) {
        Log.println(6, "Sing", h(str));
    }

    public static void g(String str, Throwable th) {
        Log.println(6, "Sing", i(str, th));
    }

    private static String h(String str) {
        return i(str, null);
    }

    private static String i(String str, Throwable th) {
        if (TextUtils.isEmpty(str)) {
            str = "NULL";
        }
        if (th == null) {
            return str + k();
        }
        return str + " : " + Log.getStackTraceString(th);
    }

    private static String j(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    private static String k() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return " (" + j(stackTrace[6].getClassName()) + "." + stackTrace[6].getMethodName() + ":" + stackTrace[6].getLineNumber() + ")";
    }

    private static String l(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(" : ");
        for (int i = 0; i < objArr.length; i++) {
            sb.append("arg");
            sb.append(i);
            sb.append(" = ");
            sb.append(m(objArr[i]));
            if (i < objArr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private static String m(Object obj) {
        return obj == null ? "null" : !obj.getClass().isArray() ? obj.toString() : obj instanceof boolean[] ? Arrays.toString((boolean[]) obj) : obj instanceof byte[] ? Arrays.toString((byte[]) obj) : obj instanceof char[] ? Arrays.toString((char[]) obj) : obj instanceof short[] ? Arrays.toString((short[]) obj) : obj instanceof int[] ? Arrays.toString((int[]) obj) : obj instanceof long[] ? Arrays.toString((long[]) obj) : obj instanceof float[] ? Arrays.toString((float[]) obj) : obj instanceof double[] ? Arrays.toString((double[]) obj) : obj instanceof Object[] ? Arrays.deepToString((Object[]) obj) : "Couldn't find a correct type for the object";
    }

    public static void n(String str) {
        Log.println(5, "Sing", h(str));
    }
}
