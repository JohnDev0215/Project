package u5;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f8280a = new k(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Long f8281b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TimeZone f8282c;

    public k(Long l8, TimeZone timeZone) {
        this.f8281b = l8;
        this.f8282c = timeZone;
    }

    public static k c() {
        return f8280a;
    }

    public Calendar a() {
        return b(this.f8282c);
    }

    public Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l8 = this.f8281b;
        if (l8 != null) {
            calendar.setTimeInMillis(l8.longValue());
        }
        return calendar;
    }
}
