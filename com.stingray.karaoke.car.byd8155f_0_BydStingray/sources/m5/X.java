package m5;

import java.io.IOException;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class X extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        try {
            int iG = c2297a.G();
            if (iG <= 65535 && iG >= -32768) {
                return Short.valueOf((short) iG);
            }
            StringBuilder sbJ = M5.b.j("Lossy conversion from ", iG, " to short; at path ");
            sbJ.append(c2297a.q(true));
            throw new j5.p(sbJ.toString());
        } catch (NumberFormatException e10) {
            throw new j5.p(e10);
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        if (((Number) obj) == null) {
            c2298b.v();
        } else {
            c2298b.I(r4.shortValue());
        }
    }
}
