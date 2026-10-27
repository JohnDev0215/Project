package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class f2 extends e.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f5332g;

    public f2() {
        this.f5332g = g.a.d.d.h.b();
    }

    public f2(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 283) {
            throw new IllegalArgumentException("x value invalid for SecT283FieldElement");
        }
        this.f5332g = e2.d(bigInteger);
    }

    public f2(long[] jArr) {
        this.f5332g = jArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        long[] jArrB = g.a.d.d.h.b();
        e2.a(this.f5332g, ((f2) eVar).f5332g, jArrB);
        return new f2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        long[] jArrB = g.a.d.d.h.b();
        e2.c(this.f5332g, jArrB);
        return new f2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        return j(eVar.g());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f2) {
            return g.a.d.d.h.d(this.f5332g, ((f2) obj).f5332g);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return 283;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        long[] jArrB = g.a.d.d.h.b();
        e2.j(this.f5332g, jArrB);
        return new f2(jArrB);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.h.f(this.f5332g);
    }

    public int hashCode() {
        return g.a.f.a.D(this.f5332g, 0, 5) ^ 2831275;
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.h.g(this.f5332g);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        long[] jArrB = g.a.d.d.h.b();
        e2.k(this.f5332g, ((f2) eVar).f5332g, jArrB);
        return new f2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e k(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        return l(eVar, eVar2, eVar3);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e l(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        long[] jArr = this.f5332g;
        long[] jArr2 = ((f2) eVar).f5332g;
        long[] jArr3 = ((f2) eVar2).f5332g;
        long[] jArr4 = ((f2) eVar3).f5332g;
        long[] jArrK = g.a.d.d.m.k(9);
        e2.l(jArr, jArr2, jArrK);
        e2.l(jArr3, jArr4, jArrK);
        long[] jArrB = g.a.d.d.h.b();
        e2.m(jArrK, jArrB);
        return new f2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        return this;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        long[] jArrB = g.a.d.d.h.b();
        e2.o(this.f5332g, jArrB);
        return new f2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        long[] jArrB = g.a.d.d.h.b();
        e2.p(this.f5332g, jArrB);
        return new f2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e p(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        long[] jArr = this.f5332g;
        long[] jArr2 = ((f2) eVar).f5332g;
        long[] jArr3 = ((f2) eVar2).f5332g;
        long[] jArrK = g.a.d.d.m.k(9);
        e2.q(jArr, jArrK);
        e2.l(jArr2, jArr3, jArrK);
        long[] jArrB = g.a.d.d.h.b();
        e2.m(jArrK, jArrB);
        return new f2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e q(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrB = g.a.d.d.h.b();
        e2.r(this.f5332g, i2, jArrB);
        return new f2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        return a(eVar);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return (this.f5332g[0] & 1) != 0;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.h.h(this.f5332g);
    }

    @Override // g.a.d.b.e.a
    public int u() {
        return e2.s(this.f5332g);
    }
}
