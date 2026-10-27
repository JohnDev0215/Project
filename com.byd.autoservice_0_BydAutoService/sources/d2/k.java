package d2;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f2738a = new k(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Long f2739b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TimeZone f2740c;

    public k(Long l3, TimeZone timeZone) {
        this.f2739b = l3;
        this.f2740c = timeZone;
    }

    public static k c() {
        return f2738a;
    }

    public Calendar a() {
        return b(this.f2740c);
    }

    public Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l3 = this.f2739b;
        if (l3 != null) {
            calendar.setTimeInMillis(l3.longValue());
        }
        return calendar;
    }
}
