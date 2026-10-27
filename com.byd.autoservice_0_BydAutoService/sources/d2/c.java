package d2;

import android.os.Build;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DateStrings.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(long j3) {
        return b(j3, Locale.getDefault());
    }

    public static String b(long j3, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? l.b(locale).format(new Date(j3)) : l.f(locale).format(new Date(j3));
    }

    public static String c(long j3) {
        return d(j3, Locale.getDefault());
    }

    public static String d(long j3, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? l.n(locale).format(new Date(j3)) : l.f(locale).format(new Date(j3));
    }
}
