package m5;

import java.io.IOException;
import java.net.URL;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.H, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1823H extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        String strO = c2297a.O();
        if (strO.equals("null")) {
            return null;
        }
        return new URL(strO);
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        URL url = (URL) obj;
        c2298b.L(url == null ? null : url.toExternalForm());
    }
}
