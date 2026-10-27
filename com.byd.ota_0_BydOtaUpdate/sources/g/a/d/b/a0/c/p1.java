package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class p1 extends e.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f5401g;

    public p1() {
        this.f5401g = g.a.d.d.g.i();
    }

    public p1(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 193) {
            throw new IllegalArgumentException("x value invalid for SecT193FieldElement");
        }
        this.f5401g = o1.d(bigInteger);
    }

    public p1(long[] jArr) {
        this.f5401g = jArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        long[] jArrI = g.a.d.d.g.i();
        o1.a(this.f5401g, ((p1) eVar).f5401g, jArrI);
        return new p1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        long[] jArrI = g.a.d.d.g.i();
        o1.c(this.f5401g, jArrI);
        return new p1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        return j(eVar.g());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof p1) {
            return g.a.d.d.g.n(this.f5401g, ((p1) obj).f5401g);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return 193;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        long[] jArrI = g.a.d.d.g.i();
        o1.j(this.f5401g, jArrI);
        return new p1(jArrI);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.g.u(this.f5401g);
    }

    public int hashCode() {
        return g.a.f.a.D(this.f5401g, 0, 4) ^ 1930015;
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.g.w(this.f5401g);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        long[] jArrI = g.a.d.d.g.i();
        o1.k(this.f5401g, ((p1) eVar).f5401g, jArrI);
        return new p1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e k(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        return l(eVar, eVar2, eVar3);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e l(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        long[] jArr = this.f5401g;
        long[] jArr2 = ((p1) eVar).f5401g;
        long[] jArr3 = ((p1) eVar2).f5401g;
        long[] jArr4 = ((p1) eVar3).f5401g;
        long[] jArrK = g.a.d.d.g.k();
        o1.l(jArr, jArr2, jArrK);
        o1.l(jArr3, jArr4, jArrK);
        long[] jArrI = g.a.d.d.g.i();
        o1.m(jArrK, jArrI);
        return new p1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        return this;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        long[] jArrI = g.a.d.d.g.i();
        o1.o(this.f5401g, jArrI);
        return new p1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        long[] jArrI = g.a.d.d.g.i();
        o1.p(this.f5401g, jArrI);
        return new p1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e p(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        long[] jArr = this.f5401g;
        long[] jArr2 = ((p1) eVar).f5401g;
        long[] jArr3 = ((p1) eVar2).f5401g;
        long[] jArrK = g.a.d.d.g.k();
        o1.q(jArr, jArrK);
        o1.l(jArr2, jArr3, jArrK);
        long[] jArrI = g.a.d.d.g.i();
        o1.m(jArrK, jArrI);
        return new p1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e q(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrI = g.a.d.d.g.i();
        o1.r(this.f5401g, i2, jArrI);
        return new p1(jArrI);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        return a(eVar);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return (this.f5401g[0] & 1) != 0;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.g.K(this.f5401g);
    }

    @Override // g.a.d.b.e.a
    public int u() {
        return o1.s(this.f5401g);
    }
}
