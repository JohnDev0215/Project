package com.android.services.telephony;

/* JADX INFO: loaded from: classes.dex */
public final class Log {
    public static final boolean DEBUG = isLoggable(3);
    public static final boolean VERBOSE;

    static {
        isLoggable(4);
        VERBOSE = isLoggable(2);
        isLoggable(5);
        isLoggable(6);
    }

    public static boolean isLoggable(int i) {
        return android.util.Log.isLoggable("Telephony", i);
    }

    public static void d(Object obj, String str, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str = String.format(getPrefixFromObject(obj) + ": " + str, objArr);
        }
        android.util.Log.d("Telephony", str);
    }

    public static void i(String str, String str2, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str2 = String.format(str + ": " + str2, objArr);
        }
        android.util.Log.i("Telephony", str2);
    }

    public static void i(Object obj, String str, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str = String.format(getPrefixFromObject(obj) + ": " + str, objArr);
        }
        android.util.Log.i("Telephony", str);
    }

    public static void v(String str, String str2, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str2 = String.format(str + ": " + str2, objArr);
        }
        android.util.Log.v("Telephony", str2);
    }

    public static void v(Object obj, String str, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str = String.format(getPrefixFromObject(obj) + ": " + str, objArr);
        }
        android.util.Log.v("Telephony", str);
    }

    public static void w(String str, String str2, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str2 = String.format(str + ": " + str2, objArr);
        }
        android.util.Log.w("Telephony", str2);
    }

    public static void w(Object obj, String str, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str = String.format(getPrefixFromObject(obj) + ": " + str, objArr);
        }
        android.util.Log.w("Telephony", str);
    }

    public static void e(Object obj, Throwable th, String str, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str = String.format(getPrefixFromObject(obj) + ": " + str, objArr);
        }
        android.util.Log.e("Telephony", str, th);
    }

    public static void wtf(String str, String str2, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str2 = String.format(str + ": " + str2, objArr);
        }
        android.util.Log.wtf("Telephony", str2);
    }

    public static void wtf(Object obj, String str, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str = String.format(getPrefixFromObject(obj) + ": " + str, objArr);
        }
        android.util.Log.wtf("Telephony", str);
    }

    private static String getPrefixFromObject(Object obj) {
        return obj == null ? "<null>" : obj.getClass().getSimpleName();
    }
}
