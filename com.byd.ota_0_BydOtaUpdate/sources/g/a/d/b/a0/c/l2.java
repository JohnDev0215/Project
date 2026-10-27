package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class l2 extends e.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f5373g;

    public l2() {
        this.f5373g = g.a.d.d.j.b();
    }

    public l2(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 409) {
            throw new IllegalArgumentException("x value invalid for SecT409FieldElement");
        }
        this.f5373g = k2.d(bigInteger);
    }

    public l2(long[] jArr) {
        this.f5373g = jArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        long[] jArrB = g.a.d.d.j.b();
        k2.a(this.f5373g, ((l2) eVar).f5373g, jArrB);
        return new l2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        long[] jArrB = g.a.d.d.j.b();
        k2.c(this.f5373g, jArrB);
        return new l2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        return j(eVar.g());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l2) {
            return g.a.d.d.j.d(this.f5373g, ((l2) obj).f5373g);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return 409;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        long[] jArrB = g.a.d.d.j.b();
        k2.j(this.f5373g, jArrB);
        return new l2(jArrB);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.j.f(this.f5373g);
    }

    public int hashCode() {
        return g.a.f.a.D(this.f5373g, 0, 7) ^ 4090087;
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.j.g(this.f5373g);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        long[] jArrB = g.a.d.d.j.b();
        k2.k(this.f5373g, ((l2) eVar).f5373g, jArrB);
        return new l2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e k(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        return l(eVar, eVar2, eVar3);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e l(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        long[] jArr = this.f5373g;
        long[] jArr2 = ((l2) eVar).f5373g;
        long[] jArr3 = ((l2) eVar2).f5373g;
        long[] jArr4 = ((l2) eVar3).f5373g;
        long[] jArrK = g.a.d.d.m.k(13);
        k2.l(jArr, jArr2, jArrK);
        k2.l(jArr3, jArr4, jArrK);
        long[] jArrB = g.a.d.d.j.b();
        k2.m(jArrK, jArrB);
        return new l2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        return this;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        long[] jArrB = g.a.d.d.j.b();
        k2.o(this.f5373g, jArrB);
        return new l2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        long[] jArrB = g.a.d.d.j.b();
        k2.p(this.f5373g, jArrB);
        return new l2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e p(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        long[] jArr = this.f5373g;
        long[] jArr2 = ((l2) eVar).f5373g;
        long[] jArr3 = ((l2) eVar2).f5373g;
        long[] jArrK = g.a.d.d.m.k(13);
        k2.q(jArr, jArrK);
        k2.l(jArr2, jArr3, jArrK);
        long[] jArrB = g.a.d.d.j.b();
        k2.m(jArrK, jArrB);
        return new l2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e q(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrB = g.a.d.d.j.b();
        k2.r(this.f5373g, i2, jArrB);
        return new l2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        return a(eVar);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return (this.f5373g[0] & 1) != 0;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.j.h(this.f5373g);
    }

    @Override // g.a.d.b.e.a
    public int u() {
        return k2.s(this.f5373g);
    }
}
