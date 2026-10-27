package m5;

import java.io.IOException;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1816A extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        int iQ = c2297a.Q();
        if (iQ != 9) {
            return iQ == 8 ? Boolean.toString(c2297a.C()) : c2297a.O();
        }
        c2297a.M();
        return null;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        c2298b.L((String) obj);
    }
}
