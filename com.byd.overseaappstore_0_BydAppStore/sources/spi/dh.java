package spi;

import android.text.TextUtils;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public final class dh {
    public static boolean a = true;
    public static boolean b = true;
    public static boolean c = true;

    public static void a(String str, String str2) {
        if (c) {
            d(str, 6, str2, null);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (c) {
            d(str, 6, str2, th);
        }
    }

    public static void c(String str, String str2) {
        if (b) {
            d(str, 4, str2, null);
        }
    }

    public static void d(String str, int i, String str2, Throwable th) {
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            if (TextUtils.isEmpty(str)) {
                str = "CAR.LOG";
            }
            str2 = stringWriter.toString();
        } else if (TextUtils.isEmpty(str)) {
            str = "CAR.LOG";
        }
        Log.println(i, str, str2);
    }

    public static void e(String str, String str2) {
        if (a) {
            d(str, 5, str2, null);
        }
    }

    public static void f(String str, String str2, Throwable th) {
        if (a) {
            d(str, 5, str2, th);
        }
    }
}
