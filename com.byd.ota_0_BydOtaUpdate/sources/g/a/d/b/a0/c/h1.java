package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class h1 extends e.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f5346g;

    public h1() {
        this.f5346g = g.a.d.d.e.h();
    }

    public h1(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 163) {
            throw new IllegalArgumentException("x value invalid for SecT163FieldElement");
        }
        this.f5346g = g1.d(bigInteger);
    }

    public h1(long[] jArr) {
        this.f5346g = jArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        long[] jArrH = g.a.d.d.e.h();
        g1.a(this.f5346g, ((h1) eVar).f5346g, jArrH);
        return new h1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        long[] jArrH = g.a.d.d.e.h();
        g1.c(this.f5346g, jArrH);
        return new h1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        return j(eVar.g());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h1) {
            return g.a.d.d.e.m(this.f5346g, ((h1) obj).f5346g);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return 163;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        long[] jArrH = g.a.d.d.e.h();
        g1.i(this.f5346g, jArrH);
        return new h1(jArrH);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.e.t(this.f5346g);
    }

    public int hashCode() {
        return g.a.f.a.D(this.f5346g, 0, 3) ^ 163763;
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.e.v(this.f5346g);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        long[] jArrH = g.a.d.d.e.h();
        g1.j(this.f5346g, ((h1) eVar).f5346g, jArrH);
        return new h1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e k(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        return l(eVar, eVar2, eVar3);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e l(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        long[] jArr = this.f5346g;
        long[] jArr2 = ((h1) eVar).f5346g;
        long[] jArr3 = ((h1) eVar2).f5346g;
        long[] jArr4 = ((h1) eVar3).f5346g;
        long[] jArrJ = g.a.d.d.e.j();
        g1.k(jArr, jArr2, jArrJ);
        g1.k(jArr3, jArr4, jArrJ);
        long[] jArrH = g.a.d.d.e.h();
        g1.l(jArrJ, jArrH);
        return new h1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        return this;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        long[] jArrH = g.a.d.d.e.h();
        g1.n(this.f5346g, jArrH);
        return new h1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        long[] jArrH = g.a.d.d.e.h();
        g1.o(this.f5346g, jArrH);
        return new h1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e p(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        long[] jArr = this.f5346g;
        long[] jArr2 = ((h1) eVar).f5346g;
        long[] jArr3 = ((h1) eVar2).f5346g;
        long[] jArrJ = g.a.d.d.e.j();
        g1.p(jArr, jArrJ);
        g1.k(jArr2, jArr3, jArrJ);
        long[] jArrH = g.a.d.d.e.h();
        g1.l(jArrJ, jArrH);
        return new h1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e q(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrH = g.a.d.d.e.h();
        g1.q(this.f5346g, i2, jArrH);
        return new h1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        return a(eVar);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return (this.f5346g[0] & 1) != 0;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.e.I(this.f5346g);
    }

    @Override // g.a.d.b.e.a
    public int u() {
        return g1.r(this.f5346g);
    }
}
