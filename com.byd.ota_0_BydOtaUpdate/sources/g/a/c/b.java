package g.a.c;

import g.a.a.r;
import g.a.a.v2.c;
import g.a.a.w2.z;
import java.io.IOException;
import java.security.Principal;

/* JADX INFO: loaded from: classes.dex */
public class b extends z implements Principal {
    public b(c cVar) {
        super((r) cVar.c());
    }

    @Override // g.a.a.l
    public byte[] e() {
        try {
            return f("DER");
        } catch (IOException e2) {
            throw new RuntimeException(e2.toString());
        }
    }

    @Override // java.security.Principal
    public String getName() {
        return toString();
    }
}
