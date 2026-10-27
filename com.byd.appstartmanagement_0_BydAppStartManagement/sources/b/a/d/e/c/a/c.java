package b.a.d.e.c.a;

import java.util.Calendar;
import java.util.Date;

/* JADX INFO: compiled from: CalendarUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    public static int a(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, 1);
        return calendar.getActualMaximum(5);
    }

    public static int b(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, i3);
        return calendar.get(7);
    }

    public static int[] c(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new int[]{calendar.get(1), calendar.get(2) + 1, calendar.get(5)};
    }
}
