package g.a.a.x2;

import g.a.a.c1;
import g.a.a.n;
import g.a.a.o0;
import g.a.a.q;
import g.a.a.r;
import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class f extends g.a.a.l implements m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.d.b.d f4184c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4185d;
    public g.a.a.m s;

    public f(k kVar, BigInteger bigInteger, BigInteger bigInteger2, r rVar) {
        int iIntValue;
        int iIntValue2;
        int i2;
        g.a.d.b.d eVar;
        this.s = null;
        g.a.a.m mVarG = kVar.g();
        this.s = mVarG;
        if (mVarG.equals(m.r2)) {
            eVar = new d.f(((g.a.a.j) kVar.i()).q(), new BigInteger(1, n.n(rVar.q(0)).p()), new BigInteger(1, n.n(rVar.q(1)).p()), bigInteger, bigInteger2);
        } else {
            if (!this.s.equals(m.s2)) {
                throw new IllegalArgumentException("This type of ECCurve is not implemented");
            }
            r rVarN = r.n(kVar.i());
            int iIntValue3 = ((g.a.a.j) rVarN.q(0)).q().intValue();
            g.a.a.m mVar = (g.a.a.m) rVarN.q(1);
            if (mVar.equals(m.u2)) {
                iIntValue2 = g.a.a.j.n(rVarN.q(2)).q().intValue();
                i2 = 0;
                iIntValue = 0;
            } else {
                if (!mVar.equals(m.v2)) {
                    throw new IllegalArgumentException("This type of EC basis is not implemented");
                }
                r rVarN2 = r.n(rVarN.q(2));
                int iIntValue4 = g.a.a.j.n(rVarN2.q(0)).q().intValue();
                int iIntValue5 = g.a.a.j.n(rVarN2.q(1)).q().intValue();
                iIntValue = g.a.a.j.n(rVarN2.q(2)).q().intValue();
                iIntValue2 = iIntValue4;
                i2 = iIntValue5;
            }
            eVar = new d.e(iIntValue3, iIntValue2, i2, iIntValue, new BigInteger(1, n.n(rVar.q(0)).p()), new BigInteger(1, n.n(rVar.q(1)).p()), bigInteger, bigInteger2);
        }
        this.f4184c = eVar;
        if (rVar.size() == 3) {
            this.f4185d = ((o0) rVar.q(2)).p();
        }
    }

    public f(g.a.d.b.d dVar, byte[] bArr) {
        this.s = null;
        this.f4184c = dVar;
        this.f4185d = bArr;
        i();
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        if (this.s.equals(m.r2) || this.s.equals(m.s2)) {
            fVar.a(new j(this.f4184c.o()).c());
            j jVar = new j(this.f4184c.p());
            fVar.a(jVar.c());
        }
        if (this.f4185d != null) {
            fVar.a(new o0(this.f4185d));
        }
        return new c1(fVar);
    }

    public g.a.d.b.d g() {
        return this.f4184c;
    }

    public byte[] h() {
        return this.f4185d;
    }

    public final void i() {
        g.a.a.m mVar;
        if (g.a.d.b.b.m(this.f4184c)) {
            mVar = m.r2;
        } else {
            if (!g.a.d.b.b.k(this.f4184c)) {
                throw new IllegalArgumentException("This type of ECCurve is not implemented");
            }
            mVar = m.s2;
        }
        this.s = mVar;
    }
}
