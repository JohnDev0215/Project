package b.g.i;

import android.os.Build;
import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: compiled from: TextUtilsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Locale f1933a = new Locale("", "");

    private static int a(Locale locale) {
        byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        return (directionality == 1 || directionality == 2) ? 1 : 0;
    }

    public static int b(Locale locale) {
        if (Build.VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(locale);
        }
        if (locale == null || locale.equals(f1933a)) {
            return 0;
        }
        String strC = b.c(locale);
        return strC == null ? a(locale) : (strC.equalsIgnoreCase("Arab") || strC.equalsIgnoreCase("Hebr")) ? 1 : 0;
    }
}
