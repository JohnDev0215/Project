package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class v1 extends e.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f5435g;

    public v1() {
        this.f5435g = g.a.d.d.g.i();
    }

    public v1(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 233) {
            throw new IllegalArgumentException("x value invalid for SecT233FieldElement");
        }
        this.f5435g = u1.d(bigInteger);
    }

    public v1(long[] jArr) {
        this.f5435g = jArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        long[] jArrI = g.a.d.d.g.i();
        u1.a(this.f5435g, ((v1) eVar).f5435g, jArrI);
        return new v1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        long[] jArrI = g.a.d.d.g.i();
        u1.c(this.f5435g, jArrI);
        return new v1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        return j(eVar.g());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof v1) {
            return g.a.d.d.g.n(this.f5435g, ((v1) obj).f5435g);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return 233;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        long[] jArrI = g.a.d.d.g.i();
        u1.j(this.f5435g, jArrI);
        return new v1(jArrI);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.g.u(this.f5435g);
    }

    public int hashCode() {
        return g.a.f.a.D(this.f5435g, 0, 4) ^ 2330074;
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.g.w(this.f5435g);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        long[] jArrI = g.a.d.d.g.i();
        u1.k(this.f5435g, ((v1) eVar).f5435g, jArrI);
        return new v1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e k(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        return l(eVar, eVar2, eVar3);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e l(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        long[] jArr = this.f5435g;
        long[] jArr2 = ((v1) eVar).f5435g;
        long[] jArr3 = ((v1) eVar2).f5435g;
        long[] jArr4 = ((v1) eVar3).f5435g;
        long[] jArrK = g.a.d.d.g.k();
        u1.l(jArr, jArr2, jArrK);
        u1.l(jArr3, jArr4, jArrK);
        long[] jArrI = g.a.d.d.g.i();
        u1.m(jArrK, jArrI);
        return new v1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        return this;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        long[] jArrI = g.a.d.d.g.i();
        u1.o(this.f5435g, jArrI);
        return new v1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        long[] jArrI = g.a.d.d.g.i();
        u1.p(this.f5435g, jArrI);
        return new v1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e p(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        long[] jArr = this.f5435g;
        long[] jArr2 = ((v1) eVar).f5435g;
        long[] jArr3 = ((v1) eVar2).f5435g;
        long[] jArrK = g.a.d.d.g.k();
        u1.q(jArr, jArrK);
        u1.l(jArr2, jArr3, jArrK);
        long[] jArrI = g.a.d.d.g.i();
        u1.m(jArrK, jArrI);
        return new v1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e q(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrI = g.a.d.d.g.i();
        u1.r(this.f5435g, i2, jArrI);
        return new v1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        return a(eVar);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return (this.f5435g[0] & 1) != 0;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.g.K(this.f5435g);
    }

    @Override // g.a.d.b.e.a
    public int u() {
        return u1.s(this.f5435g);
    }
}
