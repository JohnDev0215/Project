package defpackage;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: UtcDates.java */
/* JADX INFO: loaded from: classes.dex */
public final class qk3 {
    public static final AtomicReference<sa3> a = new AtomicReference<>();

    public static long a(long j) {
        Calendar calendarD = d(null);
        calendarD.setTimeInMillis(j);
        return b(calendarD).getTimeInMillis();
    }

    public static Calendar b(Calendar calendar) {
        Calendar calendarD = d(calendar);
        Calendar calendarD2 = d(null);
        calendarD2.set(calendarD.get(1), calendarD.get(2), calendarD.get(5));
        return calendarD2;
    }

    public static Calendar c() {
        sa3 sa3Var = a.get();
        if (sa3Var == null) {
            sa3Var = sa3.c;
        }
        TimeZone timeZone = sa3Var.b;
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = sa3Var.a;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar;
    }

    public static Calendar d(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        if (calendar == null) {
            calendar2.clear();
        } else {
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
        }
        return calendar2;
    }
}
