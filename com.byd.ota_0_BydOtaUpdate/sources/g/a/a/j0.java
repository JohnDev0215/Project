package g.a.a;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Exception;

/* JADX INFO: loaded from: classes.dex */
public class j0 extends x {
    public j0(boolean z, int i2, e eVar) {
        super(z, i2, eVar);
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        Enumeration enumerationS;
        pVar.k(160, this.f4165c);
        pVar.c(128);
        if (!this.f4166d) {
            if (this.s) {
                pVar.j(this.x);
            } else {
                e eVar = this.x;
                if (eVar instanceof n) {
                    enumerationS = eVar instanceof c0 ? ((c0) eVar).t() : new c0(((n) eVar).p()).t();
                } else if (eVar instanceof r) {
                    enumerationS = ((r) eVar).r();
                } else {
                    if (!(eVar instanceof t)) {
                        throw new ASN1Exception("not implemented: " + this.x.getClass().getName());
                    }
                    enumerationS = ((t) eVar).s();
                }
                while (enumerationS.hasMoreElements()) {
                    pVar.j((e) enumerationS.nextElement());
                }
            }
        }
        pVar.c(0);
        pVar.c(0);
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        int iB;
        if (this.f4166d) {
            return y1.b(this.f4165c) + 1;
        }
        int i2 = this.x.c().i();
        if (this.s) {
            iB = y1.b(this.f4165c) + y1.a(i2);
        } else {
            i2--;
            iB = y1.b(this.f4165c);
        }
        return iB + i2;
    }

    @Override // g.a.a.q
    public boolean k() {
        if (this.f4166d || this.s) {
            return true;
        }
        return this.x.c().l().k();
    }
}
