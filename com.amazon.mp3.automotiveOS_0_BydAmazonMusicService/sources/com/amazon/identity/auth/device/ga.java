package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import com.fasterxml.jackson.core.JsonPointer;
import java.util.Collection;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ga {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile String f351a = "Unknown";

    public static void a(String str, String str2, Object... objArr) {
        String str3 = String.format(str2, objArr);
        int i = 0;
        while (i < str3.length()) {
            int i2 = i + 1000;
            str3.substring(i, i2 > str3.length() ? str3.length() : i2);
            i = i2;
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        Log.e(a(str), String.format(str2, objArr));
    }

    public static void c(String str, String str2, Object... objArr) {
        Log.i(a(str), String.format(str2, objArr));
    }

    public static void d(String str, String str2, Object... objArr) {
        Log.w(a(str), String.format(str2, objArr));
    }

    public static void b(String str, String str2) {
        Log.e(a(str), str2);
    }

    public static void c(String str, String str2) {
        Log.i(a(str), str2);
    }

    public static void a(String str, String str2) {
        a(str);
    }

    public static void a(String str, y5 y5Var, String str2, String str3) {
        Log.e(a(str), str2);
        if (y5Var != null) {
            y5Var.b(str3);
        } else {
            yd.a(str3, new String[0]);
        }
    }

    public static void a(String str, y5 y5Var, String str2, String str3, Throwable th) {
        Log.e(a(str), str2, th);
        if (y5Var != null) {
            y5Var.b(str3);
        } else {
            yd.a(str3, new String[0]);
        }
    }

    public static void a(String str, String str2, Collection<String> collection) {
        StringBuilder sb = new StringBuilder("Account not registered: ");
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append("***");
            sb.append(str2.substring(str2.length() - Math.min(2, str2.length())));
        }
        if (collection != null) {
            sb.append(". Registered accounts are:");
            for (String str3 : collection) {
                sb.append(' ');
                sb.append("***");
                sb.append(str3.substring(str3.length() - Math.min(2, str3.length())));
            }
        }
        Log.e(a(str), sb.toString(), new IllegalArgumentException());
    }

    public static String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "EmptyData";
        }
        if (str.length() < i) {
            Log.w(a("MAPLog"), "Not masking data as length to keep is longer than the original data.");
            return str;
        }
        return str.substring(0, i) + "***";
    }

    public static String a() {
        return f351a;
    }

    public static String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = f351a + "";
        } else {
            str2 = f351a + JsonPointer.SEPARATOR + str + "";
        }
        if (str2.length() <= 23) {
            return str2;
        }
        return "..." + str2.substring((str2.length() - 23) + 3);
    }
}
