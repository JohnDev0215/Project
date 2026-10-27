package defpackage;

import android.os.Build;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DateStrings.java */
/* JADX INFO: loaded from: classes.dex */
public class ei {
    public static String a(long j) {
        return b(j, Locale.getDefault());
    }

    public static String b(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? ni.b(locale).format(new Date(j)) : ni.f(locale).format(new Date(j));
    }

    public static String c(long j) {
        return d(j, Locale.getDefault());
    }

    public static String d(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? ni.n(locale).format(new Date(j)) : ni.f(locale).format(new Date(j));
    }
}
