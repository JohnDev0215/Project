package defpackage;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
public class mi {
    public static final mi a = new mi(null, null);
    public final Long b;
    public final TimeZone c;

    public mi(Long l, TimeZone timeZone) {
        this.b = l;
        this.c = timeZone;
    }

    public static mi c() {
        return a;
    }

    public Calendar a() {
        return b(this.c);
    }

    public Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.b;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
