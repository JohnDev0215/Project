package g.a.a.w2;

import g.a.a.c1;
import g.a.a.h1;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class b extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.n f4111c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public p f4112d;
    public g.a.a.j s;

    public b(g.a.a.r rVar) {
        this.f4111c = null;
        this.f4112d = null;
        this.s = null;
        Enumeration enumerationR = rVar.r();
        while (enumerationR.hasMoreElements()) {
            g.a.a.x xVarN = g.a.a.x.n(enumerationR.nextElement());
            int iQ = xVarN.q();
            if (iQ == 0) {
                this.f4111c = g.a.a.n.o(xVarN, false);
            } else if (iQ == 1) {
                this.f4112d = p.h(xVarN, false);
            } else {
                if (iQ != 2) {
                    throw new IllegalArgumentException("illegal tag");
                }
                this.s = g.a.a.j.o(xVarN, false);
            }
        }
    }

    public static b g(Object obj) {
        if (obj instanceof b) {
            return (b) obj;
        }
        if (obj != null) {
            return new b(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        if (this.f4111c != null) {
            fVar.a(new h1(false, 0, this.f4111c));
        }
        if (this.f4112d != null) {
            fVar.a(new h1(false, 1, this.f4112d));
        }
        if (this.s != null) {
            fVar.a(new h1(false, 2, this.s));
        }
        return new c1(fVar);
    }

    public byte[] h() {
        g.a.a.n nVar = this.f4111c;
        if (nVar != null) {
            return nVar.p();
        }
        return null;
    }

    public String toString() {
        return "AuthorityKeyIdentifier: KeyID(" + this.f4111c.p() + ")";
    }
}
