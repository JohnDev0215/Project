package m5;

import java.io.IOException;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1820E extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() != 9) {
            return new StringBuilder(c2297a.O());
        }
        c2297a.M();
        return null;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        StringBuilder sb = (StringBuilder) obj;
        c2298b.L(sb == null ? null : sb.toString());
    }
}
