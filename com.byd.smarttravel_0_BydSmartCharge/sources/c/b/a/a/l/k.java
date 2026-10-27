package c.b.a.a.l;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f1730a = new k(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Long f1731b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TimeZone f1732c;

    public k(Long l, TimeZone timeZone) {
        this.f1731b = l;
        this.f1732c = timeZone;
    }

    public static k c() {
        return f1730a;
    }

    public Calendar a() {
        return b(this.f1732c);
    }

    public Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.f1731b;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
