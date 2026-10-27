package m5;

import java.io.IOException;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1822G extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() != 9) {
            return new StringBuffer(c2297a.O());
        }
        c2297a.M();
        return null;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        StringBuffer stringBuffer = (StringBuffer) obj;
        c2298b.L(stringBuffer == null ? null : stringBuffer.toString());
    }
}
