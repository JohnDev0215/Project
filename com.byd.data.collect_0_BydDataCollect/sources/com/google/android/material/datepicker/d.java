package com.google.android.material.datepicker;

import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DateStrings.java */
/* JADX INFO: loaded from: classes.dex */
class d {
    static b.g.j.d<String, String> a(Long l, Long l2) {
        return b(l, l2, null);
    }

    static b.g.j.d<String, String> b(Long l, Long l2, SimpleDateFormat simpleDateFormat) {
        if (l == null && l2 == null) {
            return b.g.j.d.a(null, null);
        }
        if (l == null) {
            return b.g.j.d.a(null, d(l2.longValue(), simpleDateFormat));
        }
        if (l2 == null) {
            return b.g.j.d.a(d(l.longValue(), simpleDateFormat), null);
        }
        Calendar calendarP = p.p();
        Calendar calendarR = p.r();
        calendarR.setTimeInMillis(l.longValue());
        Calendar calendarR2 = p.r();
        calendarR2.setTimeInMillis(l2.longValue());
        if (simpleDateFormat != null) {
            return b.g.j.d.a(simpleDateFormat.format(new Date(l.longValue())), simpleDateFormat.format(new Date(l2.longValue())));
        }
        return calendarR.get(1) == calendarR2.get(1) ? calendarR.get(1) == calendarP.get(1) ? b.g.j.d.a(f(l.longValue(), Locale.getDefault()), f(l2.longValue(), Locale.getDefault())) : b.g.j.d.a(f(l.longValue(), Locale.getDefault()), j(l2.longValue(), Locale.getDefault())) : b.g.j.d.a(j(l.longValue(), Locale.getDefault()), j(l2.longValue(), Locale.getDefault()));
    }

    static String c(long j) {
        return d(j, null);
    }

    static String d(long j, SimpleDateFormat simpleDateFormat) {
        Calendar calendarP = p.p();
        Calendar calendarR = p.r();
        calendarR.setTimeInMillis(j);
        return simpleDateFormat != null ? simpleDateFormat.format(new Date(j)) : calendarP.get(1) == calendarR.get(1) ? e(j) : i(j);
    }

    static String e(long j) {
        return f(j, Locale.getDefault());
    }

    static String f(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? p.c(locale).format(new Date(j)) : p.j(locale).format(new Date(j));
    }

    static String g(long j) {
        return h(j, Locale.getDefault());
    }

    static String h(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? p.d(locale).format(new Date(j)) : p.h(locale).format(new Date(j));
    }

    static String i(long j) {
        return j(j, Locale.getDefault());
    }

    static String j(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? p.t(locale).format(new Date(j)) : p.i(locale).format(new Date(j));
    }

    static String k(long j) {
        return l(j, Locale.getDefault());
    }

    static String l(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? p.u(locale).format(new Date(j)) : p.h(locale).format(new Date(j));
    }
}
