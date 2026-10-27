package p;

import android.os.Build;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class no {
    static {
        int i = Build.VERSION.SDK_INT;
        mo moVar = mo.a;
        if (i >= 30) {
            moVar.a(30);
        }
        if (i >= 30) {
            moVar.a(31);
        }
        if (i >= 30) {
            moVar.a(33);
        }
        if (i >= 30) {
            moVar.a(1000000);
        }
    }

    public static final boolean a() {
        int i = Build.VERSION.SDK_INT;
        if (i < 33) {
            if (i >= 32) {
                String str = Build.VERSION.CODENAME;
                v03.i(str, "CODENAME");
                if (!v03.b("REL", str)) {
                    Locale locale = Locale.ROOT;
                    String upperCase = str.toUpperCase(locale);
                    v03.i(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    String upperCase2 = "Tiramisu".toUpperCase(locale);
                    v03.i(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    if (upperCase.compareTo(upperCase2) >= 0) {
                    }
                }
            }
            return false;
        }
        return true;
    }
}
