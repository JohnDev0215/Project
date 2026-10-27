package g.a.a;

import java.io.IOException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class p1 extends t {
    public int s;

    public p1() {
        this.s = -1;
    }

    public p1(e eVar) {
        super(eVar);
        this.s = -1;
    }

    public p1(f fVar) {
        super(fVar, false);
        this.s = -1;
    }

    public p1(e[] eVarArr) {
        super(eVarArr, false);
        this.s = -1;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        p pVarB = pVar.b();
        int iW = w();
        pVar.c(49);
        pVar.i(iW);
        Enumeration enumerationS = s();
        while (enumerationS.hasMoreElements()) {
            pVarB.j((e) enumerationS.nextElement());
        }
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        int iW = w();
        return y1.a(iW) + 1 + iW;
    }

    public final int w() throws IOException {
        if (this.s < 0) {
            int i2 = 0;
            Enumeration enumerationS = s();
            while (enumerationS.hasMoreElements()) {
                i2 += ((e) enumerationS.nextElement()).c().m().i();
            }
            this.s = i2;
        }
        return this.s;
    }
}
