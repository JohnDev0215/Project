package g5;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: compiled from: BydLogHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static void a(String str) {
        Log.println(3, "UeReport", d(str));
    }

    public static void b(String str, String str2) {
        Log.println(3, str, d(str2));
    }

    public static void c(String str) {
        Log.println(6, "UeReport", d(str));
    }

    public static String d(String str) {
        return e(str, null);
    }

    public static String e(String str, Throwable th) {
        if (TextUtils.isEmpty(str)) {
            str = "NULL";
        }
        if (th == null) {
            return str + g();
        }
        return str + " : " + Log.getStackTraceString(th);
    }

    public static String f(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    public static String g() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return " --- [" + f(stackTrace[6].getClassName()) + "." + stackTrace[6].getMethodName() + "() line:" + stackTrace[6].getLineNumber() + "]";
    }

    public static String h(String str) {
        if (str == null || str.length() < 5) {
            return str;
        }
        return "*****" + str.substring(5);
    }

    public static void i(String str) {
        Log.println(4, "UeReport", d(str));
    }

    public static void j(String str) {
        Log.println(5, "UeReport", d(str));
    }
}
