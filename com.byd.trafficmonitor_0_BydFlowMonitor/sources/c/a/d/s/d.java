package c.a.d.s;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: DateUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static long a(TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }
}
