package m5;

import java.io.IOException;
import java.net.InetAddress;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class J extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() != 9) {
            return InetAddress.getByName(c2297a.O());
        }
        c2297a.M();
        return null;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        InetAddress inetAddress = (InetAddress) obj;
        c2298b.L(inetAddress == null ? null : inetAddress.getHostAddress());
    }
}
