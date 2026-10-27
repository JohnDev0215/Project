package m5;

import java.io.IOException;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class U extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        int iQ = c2297a.Q();
        if (iQ != 9) {
            return iQ == 6 ? Boolean.valueOf(Boolean.parseBoolean(c2297a.O())) : Boolean.valueOf(c2297a.C());
        }
        c2297a.M();
        return null;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            c2298b.v();
            return;
        }
        c2298b.N();
        c2298b.b();
        c2298b.l.write(bool.booleanValue() ? "true" : "false");
    }
}
