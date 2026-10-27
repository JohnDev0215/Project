package g.a.a;

import java.io.IOException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class f0 extends r {
    public f0() {
    }

    public f0(e eVar) {
        super(eVar);
    }

    public f0(f fVar) {
        super(fVar);
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.c(48);
        pVar.c(128);
        Enumeration enumerationR = r();
        while (enumerationR.hasMoreElements()) {
            pVar.j((e) enumerationR.nextElement());
        }
        pVar.c(0);
        pVar.c(0);
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        Enumeration enumerationR = r();
        int i2 = 0;
        while (enumerationR.hasMoreElements()) {
            i2 += ((e) enumerationR.nextElement()).c().i();
        }
        return i2 + 2 + 2;
    }
}
