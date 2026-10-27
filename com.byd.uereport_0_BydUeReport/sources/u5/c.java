package u5;

import android.os.Build;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DateStrings.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static String a(long j8) {
        return b(j8, Locale.getDefault());
    }

    public static String b(long j8, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? l.b(locale).format(new Date(j8)) : l.f(locale).format(new Date(j8));
    }

    public static String c(long j8) {
        return d(j8, Locale.getDefault());
    }

    public static String d(long j8, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? l.n(locale).format(new Date(j8)) : l.f(locale).format(new Date(j8));
    }
}
