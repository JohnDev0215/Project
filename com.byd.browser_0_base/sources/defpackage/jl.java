package defpackage;

import java.util.Calendar;

/* JADX INFO: compiled from: CalendarUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jl {

    /* JADX INFO: compiled from: CalendarUtils.java */
    public static final class a {
        public static final Calendar a = gl.a();

        static {
            gl.a();
        }
    }

    public static Calendar a(long j) {
        Calendar calendar = a.a;
        calendar.setTimeInMillis(j);
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        calendar.clear();
        calendar.set(i, i2, i3, 0, 0, 0);
        return calendar;
    }

    public static boolean b(Calendar calendar, Calendar calendar2) {
        return calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1);
    }
}
