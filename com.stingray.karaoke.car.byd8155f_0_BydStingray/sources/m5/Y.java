package m5;

import java.io.IOException;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class Y extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        try {
            return Integer.valueOf(c2297a.G());
        } catch (NumberFormatException e10) {
            throw new j5.p(e10);
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        if (((Number) obj) == null) {
            c2298b.v();
        } else {
            c2298b.I(r4.intValue());
        }
    }
}
