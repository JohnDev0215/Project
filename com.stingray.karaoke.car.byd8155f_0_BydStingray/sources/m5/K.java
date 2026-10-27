package m5;

import h2.AbstractC1347d;
import java.io.IOException;
import java.util.UUID;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class K extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        String strO = c2297a.O();
        try {
            return UUID.fromString(strO);
        } catch (IllegalArgumentException e10) {
            StringBuilder sbJ = AbstractC1347d.j("Failed parsing '", strO, "' as UUID; at path ");
            sbJ.append(c2297a.q(true));
            throw new j5.p(sbJ.toString(), e10);
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        UUID uuid = (UUID) obj;
        c2298b.L(uuid == null ? null : uuid.toString());
    }
}
