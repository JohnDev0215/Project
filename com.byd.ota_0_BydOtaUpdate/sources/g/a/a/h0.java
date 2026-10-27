package g.a.a;

import java.io.IOException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class h0 extends t {
    public h0() {
    }

    public h0(e eVar) {
        super(eVar);
    }

    public h0(f fVar) {
        super(fVar, false);
    }

    public h0(e[] eVarArr) {
        super(eVarArr, false);
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.c(49);
        pVar.c(128);
        Enumeration enumerationS = s();
        while (enumerationS.hasMoreElements()) {
            pVar.j((e) enumerationS.nextElement());
        }
        pVar.c(0);
        pVar.c(0);
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        Enumeration enumerationS = s();
        int i2 = 0;
        while (enumerationS.hasMoreElements()) {
            i2 += ((e) enumerationS.nextElement()).c().i();
        }
        return i2 + 2 + 2;
    }
}
