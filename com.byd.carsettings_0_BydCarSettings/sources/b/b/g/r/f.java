package b.b.g.r;

import android.content.Context;
import android.icu.util.Calendar;
import b.b.g.p.d;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: BydDateFormat.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile Locale f1803a;

    public static String a(Context context) {
        Locale locale = f1803a;
        String strB = b(context, locale);
        if (d(locale) && Calendar.getInstance().get(2) + 1 == 5) {
            String str = d.g.a().S(context) == 0 ? "EEE., MMM dd, HH:mm:ss yyyy" : "EEE., MMM dd, hh:mm:ss a yyyy";
            b.b.m.c.a.a("BydDateFormat", "---getFormatTime--MONTH_5--format: " + str);
            strB = str;
        }
        return new SimpleDateFormat(strB, locale).format(new Date(System.currentTimeMillis()));
    }

    public static String b(Context context, Locale locale) {
        boolean z = d.g.a().S(context) == 0;
        return locale.equals(Locale.CHINA) ? z ? "yyyy年MM月dd日 HH:mm:ss" : "yyyy年MM月dd日 a hh:mm:ss" : d(locale) ? z ? "EEE., MMM. dd, HH:mm:ss yyyy" : "EEE., MMM. dd, hh:mm:ss a yyyy" : locale.equals(Locale.GERMANY) ? z ? "EEE, dd MMM, HH:mm:ss yyyy" : "EEE, dd MMM, hh:mm:ss a yyyy" : z ? "EEE., MMM. dd, HH:mm:ss yyyy" : "EEE., MMM. dd, hh:mm:ss a yyyy";
    }

    public static void c(Context context) {
        b.b.m.c.a.a("BydDateFormat", "---init---");
        e(context);
    }

    public static boolean d(Locale locale) {
        if (locale == null) {
            return false;
        }
        return locale.equals(Locale.US) || locale.equals(Locale.ENGLISH) || locale.equals(Locale.UK);
    }

    public static void e(Context context) {
        if (context == null) {
            return;
        }
        f1803a = context.getResources().getConfiguration().getLocales().get(0);
        b.b.m.c.a.a("BydDateFormat", "---onLanguageChanged---curLocale: " + f1803a.toString());
    }
}
