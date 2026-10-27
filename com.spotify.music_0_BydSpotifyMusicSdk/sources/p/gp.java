package p;

import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class gp {
    public static final /* synthetic */ int b = 0;
    public Long a;

    static {
        bl1 bl1VarA = bl1.a(1900, 0);
        Calendar calendarC = bs2.c(null);
        calendarC.setTimeInMillis(bl1VarA.n);
        bs2.a(calendarC).getTimeInMillis();
        bl1 bl1VarA2 = bl1.a(2100, 11);
        Calendar calendarC2 = bs2.c(null);
        calendarC2.setTimeInMillis(bl1VarA2.n);
        bs2.a(calendarC2).getTimeInMillis();
    }
}
