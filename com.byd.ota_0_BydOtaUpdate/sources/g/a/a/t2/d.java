package g.a.a.t2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.m;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class d extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f4063c = {-87, -42, -21, 69, -15, 60, 112, -126, -128, -60, -106, 123, 35, 31, 94, -83, -10, 88, -21, -92, -64, 55, 41, 29, 56, -39, 107, -16, 37, -54, 78, 23, -8, -23, 114, 13, -58, 21, -76, 58, 40, -105, 95, 11, -63, -34, -93, 100, 56, -75, 100, -22, 44, 23, -97, -48, 18, 62, 109, -72, -6, -59, 121, 4};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m f4064d;
    public b s;
    public byte[] x = f4063c;

    public d(m mVar) {
        this.f4064d = mVar;
    }

    public d(b bVar) {
        this.s = bVar;
    }

    public static byte[] h() {
        return f4063c;
    }

    public static d j(Object obj) {
        if (obj instanceof d) {
            return (d) obj;
        }
        if (obj == null) {
            throw new IllegalArgumentException("object parse error");
        }
        r rVarN = r.n(obj);
        d dVar = rVarN.q(0) instanceof m ? new d(m.s(rVarN.q(0))) : new d(b.k(rVarN.q(0)));
        if (rVarN.size() == 2) {
            byte[] bArrP = n.n(rVarN.q(1)).p();
            dVar.x = bArrP;
            if (bArrP.length != f4063c.length) {
                throw new IllegalArgumentException("object parse error");
            }
        }
        return dVar;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        g.a.a.e eVar = this.f4064d;
        if (eVar == null) {
            eVar = this.s;
        }
        fVar.a(eVar);
        if (!g.a.f.a.a(this.x, f4063c)) {
            fVar.a(new y0(this.x));
        }
        return new c1(fVar);
    }

    public byte[] g() {
        return this.x;
    }

    public b i() {
        return this.s;
    }

    public m k() {
        return this.f4064d;
    }

    public boolean l() {
        return this.f4064d != null;
    }
}
