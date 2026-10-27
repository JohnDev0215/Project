package c.b.a.a.x;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f4142a = new n(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Long f4143b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TimeZone f4144c;

    public n(Long l, TimeZone timeZone) {
        this.f4143b = l;
        this.f4144c = timeZone;
    }

    public static n c() {
        return f4142a;
    }

    public Calendar a() {
        return b(this.f4144c);
    }

    public Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.f4143b;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
