package defpackage;

import android.util.Log;
import java.util.Locale;
import org.chromium.build.annotations.DoNotInline;

/* JADX INFO: compiled from: Log.java */
/* JADX INFO: loaded from: classes.dex */
public final class fa1 {
    public static void a(String str, String str2) {
        Log.e("cr_".concat(str), str2);
    }

    @DoNotInline
    public static void b(String str, String str2, Object obj) {
        d(str, str2, obj);
    }

    @DoNotInline
    public static void c(String str, String str2, Object obj, Object obj2) {
        d(str, str2, obj, obj2);
    }

    public static void d(String str, String str2, Object... objArr) {
        Throwable thF = f(objArr);
        String strE = e(str2, thF, objArr);
        String strJ = C0175b.j("cr_", str);
        if (thF != null) {
            Log.e(strJ, strE, thF);
        } else {
            Log.e(strJ, strE);
        }
    }

    public static String e(String str, Throwable th, Object... objArr) {
        return ((th != null || objArr.length <= 0) && objArr.length <= 1) ? str : String.format(Locale.US, str, objArr);
    }

    public static Throwable f(Object[] objArr) {
        if (objArr.length == 0) {
            return null;
        }
        Object obj = objArr[objArr.length - 1];
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        return null;
    }

    @DoNotInline
    public static void g(String str, String str2, Object obj) {
        i(str, str2, obj);
    }

    @DoNotInline
    public static void h(String str, String str2, Object obj, Object obj2) {
        i(str, str2, obj, obj2);
    }

    public static void i(String str, String str2, Object... objArr) {
        Throwable thF = f(objArr);
        String strE = e(str2, thF, objArr);
        String strJ = C0175b.j("cr_", str);
        if (thF != null) {
            Log.i(strJ, strE, thF);
        } else {
            Log.i(strJ, strE);
        }
    }

    public static void j() {
        Log.w("cr_BarcodeProviderImpl", "Google Play Services not available");
    }

    @DoNotInline
    public static void k(String str, String str2, Object obj) {
        m(str, str2, obj);
    }

    @DoNotInline
    public static void l(String str, String str2, Object obj, Object obj2) {
        m(str, str2, obj, obj2);
    }

    public static void m(String str, String str2, Object... objArr) {
        Throwable thF = f(objArr);
        String strE = e(str2, thF, objArr);
        String strJ = C0175b.j("cr_", str);
        if (thF != null) {
            Log.w(strJ, strE, thF);
        } else {
            Log.w(strJ, strE);
        }
    }

    public static void n(String str, String str2, Object... objArr) {
        Throwable thF = f(objArr);
        String strE = e(str2, thF, objArr);
        String strConcat = "cr_".concat(str);
        if (thF != null) {
            Log.wtf(strConcat, strE, thF);
        } else {
            Log.wtf(strConcat, strE);
        }
    }
}
