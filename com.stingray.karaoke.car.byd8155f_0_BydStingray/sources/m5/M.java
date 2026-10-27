package m5;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class M extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        int iG;
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        c2297a.g();
        int i7 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (c2297a.Q() != 4) {
            String strK = c2297a.K();
            iG = c2297a.G();
            strK.getClass();
            switch (strK) {
                case "dayOfMonth":
                    i11 = iG;
                    break;
                case "minute":
                    i13 = iG;
                    break;
                case "second":
                    i14 = iG;
                    break;
                case "year":
                    i7 = iG;
                    break;
                case "month":
                    i10 = iG;
                    break;
                case "hourOfDay":
                    i12 = iG;
                    break;
            }
        }
        c2297a.m();
        return new GregorianCalendar(i7, i10, i11, i12, i13, i14);
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        if (((Calendar) obj) == null) {
            c2298b.v();
            return;
        }
        c2298b.i();
        c2298b.o("year");
        c2298b.I(r4.get(1));
        c2298b.o("month");
        c2298b.I(r4.get(2));
        c2298b.o("dayOfMonth");
        c2298b.I(r4.get(5));
        c2298b.o("hourOfDay");
        c2298b.I(r4.get(11));
        c2298b.o("minute");
        c2298b.I(r4.get(12));
        c2298b.o("second");
        c2298b.I(r4.get(13));
        c2298b.m();
    }
}
