package m5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class I extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        try {
            String strO = c2297a.O();
            if (strO.equals("null")) {
                return null;
            }
            return new URI(strO);
        } catch (URISyntaxException e10) {
            throw new j5.p(e10);
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        URI uri = (URI) obj;
        c2298b.L(uri == null ? null : uri.toASCIIString());
    }
}
