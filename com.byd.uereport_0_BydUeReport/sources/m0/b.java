package m0;

import android.icu.util.ULocale;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* JADX INFO: compiled from: ICUCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f6312a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f6313b;

    static {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 21) {
            if (i8 < 24) {
                try {
                    f6313b = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
                    return;
                } catch (Exception e8) {
                    throw new IllegalStateException(e8);
                }
            }
            return;
        }
        try {
            Class<?> cls = Class.forName("libcore.icu.ICU");
            f6312a = cls.getMethod("getScript", String.class);
            f6313b = cls.getMethod("addLikelySubtags", String.class);
        } catch (Exception e9) {
            f6312a = null;
            f6313b = null;
            Log.w("ICUCompat", e9);
        }
    }

    public static String a(Locale locale) {
        String string = locale.toString();
        try {
            Method method = f6313b;
            if (method != null) {
                return (String) method.invoke(null, string);
            }
        } catch (IllegalAccessException e8) {
            Log.w("ICUCompat", e8);
        } catch (InvocationTargetException e9) {
            Log.w("ICUCompat", e9);
        }
        return string;
    }

    public static String b(String str) {
        try {
            Method method = f6312a;
            if (method != null) {
                return (String) method.invoke(null, str);
            }
        } catch (IllegalAccessException e8) {
            Log.w("ICUCompat", e8);
        } catch (InvocationTargetException e9) {
            Log.w("ICUCompat", e9);
        }
        return null;
    }

    public static String c(Locale locale) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 24) {
            return ULocale.addLikelySubtags(ULocale.forLocale(locale)).getScript();
        }
        if (i8 < 21) {
            String strA = a(locale);
            if (strA != null) {
                return b(strA);
            }
            return null;
        }
        try {
            return ((Locale) f6313b.invoke(null, locale)).getScript();
        } catch (IllegalAccessException e8) {
            Log.w("ICUCompat", e8);
            return locale.getScript();
        } catch (InvocationTargetException e9) {
            Log.w("ICUCompat", e9);
            return locale.getScript();
        }
    }
}
