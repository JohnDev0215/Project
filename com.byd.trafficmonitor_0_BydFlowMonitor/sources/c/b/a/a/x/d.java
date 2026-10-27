package c.b.a.a.x;

import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DateStrings.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static b.h.l.c<String, String> a(Long l, Long l2) {
        return b(l, l2, null);
    }

    public static b.h.l.c<String, String> b(Long l, Long l2, SimpleDateFormat simpleDateFormat) {
        if (l == null && l2 == null) {
            return b.h.l.c.a(null, null);
        }
        if (l == null) {
            return b.h.l.c.a(null, d(l2.longValue(), simpleDateFormat));
        }
        if (l2 == null) {
            return b.h.l.c.a(d(l.longValue(), simpleDateFormat), null);
        }
        Calendar calendarP = o.p();
        Calendar calendarR = o.r();
        calendarR.setTimeInMillis(l.longValue());
        Calendar calendarR2 = o.r();
        calendarR2.setTimeInMillis(l2.longValue());
        if (simpleDateFormat != null) {
            return b.h.l.c.a(simpleDateFormat.format(new Date(l.longValue())), simpleDateFormat.format(new Date(l2.longValue())));
        }
        return calendarR.get(1) == calendarR2.get(1) ? calendarR.get(1) == calendarP.get(1) ? b.h.l.c.a(f(l.longValue(), Locale.getDefault()), f(l2.longValue(), Locale.getDefault())) : b.h.l.c.a(f(l.longValue(), Locale.getDefault()), j(l2.longValue(), Locale.getDefault())) : b.h.l.c.a(j(l.longValue(), Locale.getDefault()), j(l2.longValue(), Locale.getDefault()));
    }

    public static String c(long j) {
        return d(j, null);
    }

    public static String d(long j, SimpleDateFormat simpleDateFormat) {
        Calendar calendarP = o.p();
        Calendar calendarR = o.r();
        calendarR.setTimeInMillis(j);
        return simpleDateFormat != null ? simpleDateFormat.format(new Date(j)) : calendarP.get(1) == calendarR.get(1) ? e(j) : i(j);
    }

    public static String e(long j) {
        return f(j, Locale.getDefault());
    }

    public static String f(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? o.c(locale).format(new Date(j)) : o.j(locale).format(new Date(j));
    }

    public static String g(long j) {
        return h(j, Locale.getDefault());
    }

    public static String h(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? o.d(locale).format(new Date(j)) : o.h(locale).format(new Date(j));
    }

    public static String i(long j) {
        return j(j, Locale.getDefault());
    }

    public static String j(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? o.t(locale).format(new Date(j)) : o.i(locale).format(new Date(j));
    }

    public static String k(long j) {
        return l(j, Locale.getDefault());
    }

    public static String l(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? o.u(locale).format(new Date(j)) : o.h(locale).format(new Date(j));
    }
}
