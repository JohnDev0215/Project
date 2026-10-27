package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class b2 extends e.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f5306g;

    public b2() {
        this.f5306g = g.a.d.d.g.i();
    }

    public b2(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 239) {
            throw new IllegalArgumentException("x value invalid for SecT239FieldElement");
        }
        this.f5306g = a2.d(bigInteger);
    }

    public b2(long[] jArr) {
        this.f5306g = jArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        long[] jArrI = g.a.d.d.g.i();
        a2.a(this.f5306g, ((b2) eVar).f5306g, jArrI);
        return new b2(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        long[] jArrI = g.a.d.d.g.i();
        a2.c(this.f5306g, jArrI);
        return new b2(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        return j(eVar.g());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b2) {
            return g.a.d.d.g.n(this.f5306g, ((b2) obj).f5306g);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return 239;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        long[] jArrI = g.a.d.d.g.i();
        a2.j(this.f5306g, jArrI);
        return new b2(jArrI);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.g.u(this.f5306g);
    }

    public int hashCode() {
        return g.a.f.a.D(this.f5306g, 0, 4) ^ 23900158;
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.g.w(this.f5306g);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        long[] jArrI = g.a.d.d.g.i();
        a2.k(this.f5306g, ((b2) eVar).f5306g, jArrI);
        return new b2(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e k(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        return l(eVar, eVar2, eVar3);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e l(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        long[] jArr = this.f5306g;
        long[] jArr2 = ((b2) eVar).f5306g;
        long[] jArr3 = ((b2) eVar2).f5306g;
        long[] jArr4 = ((b2) eVar3).f5306g;
        long[] jArrK = g.a.d.d.g.k();
        a2.l(jArr, jArr2, jArrK);
        a2.l(jArr3, jArr4, jArrK);
        long[] jArrI = g.a.d.d.g.i();
        a2.m(jArrK, jArrI);
        return new b2(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        return this;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        long[] jArrI = g.a.d.d.g.i();
        a2.o(this.f5306g, jArrI);
        return new b2(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        long[] jArrI = g.a.d.d.g.i();
        a2.p(this.f5306g, jArrI);
        return new b2(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e p(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        long[] jArr = this.f5306g;
        long[] jArr2 = ((b2) eVar).f5306g;
        long[] jArr3 = ((b2) eVar2).f5306g;
        long[] jArrK = g.a.d.d.g.k();
        a2.q(jArr, jArrK);
        a2.l(jArr2, jArr3, jArrK);
        long[] jArrI = g.a.d.d.g.i();
        a2.m(jArrK, jArrI);
        return new b2(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e q(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrI = g.a.d.d.g.i();
        a2.r(this.f5306g, i2, jArrI);
        return new b2(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        return a(eVar);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return (this.f5306g[0] & 1) != 0;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.g.K(this.f5306g);
    }

    @Override // g.a.d.b.e.a
    public int u() {
        return a2.s(this.f5306g);
    }
}
