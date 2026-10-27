package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class r2 extends e.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f5413g;

    public r2() {
        this.f5413g = g.a.d.d.l.b();
    }

    public r2(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 571) {
            throw new IllegalArgumentException("x value invalid for SecT571FieldElement");
        }
        this.f5413g = q2.g(bigInteger);
    }

    public r2(long[] jArr) {
        this.f5413g = jArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        long[] jArrB = g.a.d.d.l.b();
        q2.b(this.f5413g, ((r2) eVar).f5413g, jArrB);
        return new r2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        long[] jArrB = g.a.d.d.l.b();
        q2.f(this.f5413g, jArrB);
        return new r2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        return j(eVar.g());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof r2) {
            return g.a.d.d.l.d(this.f5413g, ((r2) obj).f5413g);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return 571;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        long[] jArrB = g.a.d.d.l.b();
        q2.k(this.f5413g, jArrB);
        return new r2(jArrB);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.l.f(this.f5413g);
    }

    public int hashCode() {
        return g.a.f.a.D(this.f5413g, 0, 9) ^ 5711052;
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.l.g(this.f5413g);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        long[] jArrB = g.a.d.d.l.b();
        q2.l(this.f5413g, ((r2) eVar).f5413g, jArrB);
        return new r2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e k(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        return l(eVar, eVar2, eVar3);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e l(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        long[] jArr = this.f5413g;
        long[] jArr2 = ((r2) eVar).f5413g;
        long[] jArr3 = ((r2) eVar2).f5413g;
        long[] jArr4 = ((r2) eVar3).f5413g;
        long[] jArrC = g.a.d.d.l.c();
        q2.m(jArr, jArr2, jArrC);
        q2.m(jArr3, jArr4, jArrC);
        long[] jArrB = g.a.d.d.l.b();
        q2.q(jArrC, jArrB);
        return new r2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        return this;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        long[] jArrB = g.a.d.d.l.b();
        q2.s(this.f5413g, jArrB);
        return new r2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        long[] jArrB = g.a.d.d.l.b();
        q2.t(this.f5413g, jArrB);
        return new r2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e p(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        long[] jArr = this.f5413g;
        long[] jArr2 = ((r2) eVar).f5413g;
        long[] jArr3 = ((r2) eVar2).f5413g;
        long[] jArrC = g.a.d.d.l.c();
        q2.u(jArr, jArrC);
        q2.m(jArr2, jArr3, jArrC);
        long[] jArrB = g.a.d.d.l.b();
        q2.q(jArrC, jArrB);
        return new r2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e q(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrB = g.a.d.d.l.b();
        q2.v(this.f5413g, i2, jArrB);
        return new r2(jArrB);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        return a(eVar);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return (this.f5413g[0] & 1) != 0;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.l.h(this.f5413g);
    }

    @Override // g.a.d.b.e.a
    public int u() {
        return q2.w(this.f5413g);
    }
}
