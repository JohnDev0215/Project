package b.b.g.r;

import android.content.Context;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: CarAutoTypeUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class j {
    public static String a(Context context, int i, boolean z) {
        return context.getResources().getString(b.b.g.n.q.h.m().v() == 2 ? R.string.newenergy_mile_unit_miles : R.string.newenergy_mile_unit);
    }

    public static String b(int i, boolean z) {
        if (z) {
            int iH = b.b.g.n.q.o.m().h();
            if (iH < 0 || iH > 999) {
                return "--";
            }
            return iH + "";
        }
        int iJ = b.b.g.n.q.o.m().j();
        if (iJ < 31 || iJ > 2000) {
            return "--";
        }
        return iJ + "";
    }

    public static double c() {
        return b.b.g.n.q.h.m().v() == 2 ? 3379.5299999999997d : 3500.0d;
    }
}
