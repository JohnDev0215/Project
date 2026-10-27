package x4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: compiled from: DateUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static String a() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
    }

    public static long b() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long c(TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static int d(String str, String str2) {
        return TimeZone.getTimeZone(str).getRawOffset() - TimeZone.getTimeZone(str2).getRawOffset();
    }
}
