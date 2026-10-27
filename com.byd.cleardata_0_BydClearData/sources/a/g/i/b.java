package a.g.i;

import android.icu.util.ULocale;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Method f340a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f341b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            if (i < 24) {
                try {
                    f341b = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
                    return;
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
            return;
        }
        try {
            Class<?> cls = Class.forName("libcore.icu.ICU");
            f340a = cls.getMethod("getScript", String.class);
            f341b = cls.getMethod("addLikelySubtags", String.class);
        } catch (Exception e2) {
            f340a = null;
            f341b = null;
            Log.w("ICUCompat", e2);
        }
    }

    private static String a(Locale locale) {
        String string = locale.toString();
        try {
            Method method = f341b;
            if (method != null) {
                return (String) method.invoke(null, string);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.w("ICUCompat", e);
        }
        return string;
    }

    private static String b(String str) {
        try {
            Method method = f340a;
            if (method != null) {
                return (String) method.invoke(null, str);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.w("ICUCompat", e);
        }
        return null;
    }

    public static String c(Locale locale) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return ULocale.addLikelySubtags(ULocale.forLocale(locale)).getScript();
        }
        if (i >= 21) {
            try {
                return ((Locale) f341b.invoke(null, locale)).getScript();
            } catch (IllegalAccessException | InvocationTargetException e) {
                Log.w("ICUCompat", e);
                return locale.getScript();
            }
        }
        String strA = a(locale);
        if (strA != null) {
            return b(strA);
        }
        return null;
    }
}
