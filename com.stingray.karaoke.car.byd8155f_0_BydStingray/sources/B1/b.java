package B1;

import A7.m;
import android.os.Build;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    static {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 30) {
            a.b(30);
        }
        if (i7 >= 30) {
            a.b(31);
        }
        if (i7 >= 30) {
            a.b(33);
        }
        if (i7 >= 30) {
            a.b(1000000);
        }
    }

    public static final boolean a() {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 < 35) {
            if (i7 >= 34) {
                String str = Build.VERSION.CODENAME;
                m.e(str, "CODENAME");
                if (!"REL".equals(str)) {
                    Locale locale = Locale.ROOT;
                    String upperCase = str.toUpperCase(locale);
                    m.e(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    String upperCase2 = "VanillaIceCream".toUpperCase(locale);
                    m.e(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    if (upperCase.compareTo(upperCase2) >= 0) {
                    }
                }
            }
            return false;
        }
        return true;
    }
}
