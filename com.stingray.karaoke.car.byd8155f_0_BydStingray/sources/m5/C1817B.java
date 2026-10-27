package m5;

import h2.AbstractC1347d;
import java.io.IOException;
import java.math.BigDecimal;
import l5.AbstractC1776d;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1817B extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        String strO = c2297a.O();
        try {
            return AbstractC1776d.i(strO);
        } catch (NumberFormatException e10) {
            StringBuilder sbJ = AbstractC1347d.j("Failed parsing '", strO, "' as BigDecimal; at path ");
            sbJ.append(c2297a.q(true));
            throw new j5.p(sbJ.toString(), e10);
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        c2298b.K((BigDecimal) obj);
    }
}
