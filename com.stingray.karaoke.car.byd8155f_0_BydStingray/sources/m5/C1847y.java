package m5;

import java.io.IOException;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1847y extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() != 9) {
            return Double.valueOf(c2297a.F());
        }
        c2297a.M();
        return null;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        Number number = (Number) obj;
        if (number == null) {
            c2298b.v();
        } else {
            c2298b.G(number.doubleValue());
        }
    }
}
