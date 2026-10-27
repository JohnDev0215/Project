package defpackage;

import android.util.Log;
import java.util.Locale;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Ts {
    public static void a(String str, String str2, Object... objArr) {
        Throwable thC = c(objArr);
        String strB = b(str2, thC, objArr);
        if (thC != null) {
            Log.e(e(str), strB, thC);
        } else {
            Log.e(e(str), strB);
        }
    }

    public static String b(String str, Throwable th, Object... objArr) {
        return objArr != null ? ((th != null || objArr.length <= 0) && objArr.length <= 1) ? str : String.format(Locale.US, str, objArr) : str;
    }

    public static Throwable c(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        Object obj = objArr[objArr.length - 1];
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        return null;
    }

    public static void d(String str, String str2, Object... objArr) {
        Throwable thC = c(objArr);
        String strB = b(str2, thC, objArr);
        if (thC != null) {
            Log.i(e(str), strB, thC);
        } else {
            Log.i(e(str), strB);
        }
    }

    public static String e(String str) {
        if (str.startsWith("cr_")) {
            return str;
        }
        int i = str.startsWith("cr.") ? 3 : 0;
        StringBuilder sbF = Z7.f("cr_");
        sbF.append(str.substring(i, str.length()));
        return sbF.toString();
    }

    public static void f(String str, String str2, Object... objArr) {
        Throwable thC = c(objArr);
        String strB = b(str2, thC, objArr);
        if (thC != null) {
            Log.w(e(str), strB, thC);
        } else {
            Log.w(e(str), strB);
        }
    }
}
