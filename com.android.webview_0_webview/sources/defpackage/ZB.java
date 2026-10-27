package defpackage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ZB {
    public final long a;
    public final int b;
    public final int c;
    public final int d;

    public ZB(long j, int i, int i2, int i3) {
        this.a = j;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static ZB a(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.clear();
        calendar.set(i, i2, i3);
        return new ZB(calendar.getTimeInMillis(), i, i2, i3);
    }

    public static ZB b(long j) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        gregorianCalendar.setTimeInMillis(j);
        return a(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5));
    }
}
