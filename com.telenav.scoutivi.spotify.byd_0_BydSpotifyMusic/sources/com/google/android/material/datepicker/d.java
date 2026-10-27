package com.google.android.material.datepicker;

import android.content.Context;
import android.text.format.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: compiled from: DateStrings.java */
/* JADX INFO: loaded from: classes.dex */
class d {
    static h0.d<String, String> a(Long l7, Long l8) {
        return b(l7, l8, null);
    }

    static h0.d<String, String> b(Long l7, Long l8, SimpleDateFormat simpleDateFormat) {
        if (l7 == null && l8 == null) {
            return h0.d.a(null, null);
        }
        if (l7 == null) {
            return h0.d.a(null, d(l8.longValue(), simpleDateFormat));
        }
        if (l8 == null) {
            return h0.d.a(d(l7.longValue(), simpleDateFormat), null);
        }
        Calendar calendarJ = p.j();
        Calendar calendarL = p.l();
        calendarL.setTimeInMillis(l7.longValue());
        Calendar calendarL2 = p.l();
        calendarL2.setTimeInMillis(l8.longValue());
        if (simpleDateFormat != null) {
            return h0.d.a(simpleDateFormat.format(new Date(l7.longValue())), simpleDateFormat.format(new Date(l8.longValue())));
        }
        return calendarL.get(1) == calendarL2.get(1) ? calendarL.get(1) == calendarJ.get(1) ? h0.d.a(f(l7.longValue(), Locale.getDefault()), f(l8.longValue(), Locale.getDefault())) : h0.d.a(f(l7.longValue(), Locale.getDefault()), k(l8.longValue(), Locale.getDefault())) : h0.d.a(k(l7.longValue(), Locale.getDefault()), k(l8.longValue(), Locale.getDefault()));
    }

    static String c(long j7) {
        return d(j7, null);
    }

    static String d(long j7, SimpleDateFormat simpleDateFormat) {
        Calendar calendarJ = p.j();
        Calendar calendarL = p.l();
        calendarL.setTimeInMillis(j7);
        return simpleDateFormat != null ? simpleDateFormat.format(new Date(j7)) : calendarJ.get(1) == calendarL.get(1) ? e(j7) : j(j7);
    }

    static String e(long j7) {
        return f(j7, Locale.getDefault());
    }

    static String f(long j7, Locale locale) {
        return p.b(locale).format(new Date(j7));
    }

    static String g(long j7) {
        return h(j7, Locale.getDefault());
    }

    static String h(long j7, Locale locale) {
        return p.c(locale).format(new Date(j7));
    }

    static String i(Context context, long j7) {
        return DateUtils.formatDateTime(context, j7 - ((long) TimeZone.getDefault().getOffset(j7)), 36);
    }

    static String j(long j7) {
        return k(j7, Locale.getDefault());
    }

    static String k(long j7, Locale locale) {
        return p.n(locale).format(new Date(j7));
    }

    static String l(long j7) {
        return m(j7, Locale.getDefault());
    }

    static String m(long j7, Locale locale) {
        return p.o(locale).format(new Date(j7));
    }
}
