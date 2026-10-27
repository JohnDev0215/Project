package h4;

import java.util.Calendar;
import java.util.Date;

/* JADX INFO: compiled from: CalendarUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    public static int a(int i8, int i9) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i8, i9 - 1, 1);
        return calendar.getActualMaximum(5);
    }

    public static int b(int i8, int i9, int i10) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i8, i9 - 1, i10);
        return calendar.get(7);
    }

    public static int[] c(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new int[]{calendar.get(1), calendar.get(2) + 1, calendar.get(5)};
    }
}
