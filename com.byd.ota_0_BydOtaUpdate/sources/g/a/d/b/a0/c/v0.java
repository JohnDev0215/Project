package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class v0 extends e.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f5434g;

    public v0() {
        this.f5434g = g.a.d.d.c.f();
    }

    public v0(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 113) {
            throw new IllegalArgumentException("x value invalid for SecT113FieldElement");
        }
        this.f5434g = u0.d(bigInteger);
    }

    public v0(long[] jArr) {
        this.f5434g = jArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        long[] jArrF = g.a.d.d.c.f();
        u0.a(this.f5434g, ((v0) eVar).f5434g, jArrF);
        return new v0(jArrF);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        long[] jArrF = g.a.d.d.c.f();
        u0.c(this.f5434g, jArrF);
        return new v0(jArrF);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        return j(eVar.g());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof v0) {
            return g.a.d.d.c.j(this.f5434g, ((v0) obj).f5434g);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return 113;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        long[] jArrF = g.a.d.d.c.f();
        u0.h(this.f5434g, jArrF);
        return new v0(jArrF);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.c.p(this.f5434g);
    }

    public int hashCode() {
        return g.a.f.a.D(this.f5434g, 0, 2) ^ 113009;
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.c.r(this.f5434g);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        long[] jArrF = g.a.d.d.c.f();
        u0.i(this.f5434g, ((v0) eVar).f5434g, jArrF);
        return new v0(jArrF);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e k(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        return l(eVar, eVar2, eVar3);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e l(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        long[] jArr = this.f5434g;
        long[] jArr2 = ((v0) eVar).f5434g;
        long[] jArr3 = ((v0) eVar2).f5434g;
        long[] jArr4 = ((v0) eVar3).f5434g;
        long[] jArrH = g.a.d.d.c.h();
        u0.j(jArr, jArr2, jArrH);
        u0.j(jArr3, jArr4, jArrH);
        long[] jArrF = g.a.d.d.c.f();
        u0.k(jArrH, jArrF);
        return new v0(jArrF);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        return this;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        long[] jArrF = g.a.d.d.c.f();
        u0.m(this.f5434g, jArrF);
        return new v0(jArrF);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        long[] jArrF = g.a.d.d.c.f();
        u0.n(this.f5434g, jArrF);
        return new v0(jArrF);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e p(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        long[] jArr = this.f5434g;
        long[] jArr2 = ((v0) eVar).f5434g;
        long[] jArr3 = ((v0) eVar2).f5434g;
        long[] jArrH = g.a.d.d.c.h();
        u0.o(jArr, jArrH);
        u0.j(jArr2, jArr3, jArrH);
        long[] jArrF = g.a.d.d.c.f();
        u0.k(jArrH, jArrF);
        return new v0(jArrF);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e q(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrF = g.a.d.d.c.f();
        u0.p(this.f5434g, i2, jArrF);
        return new v0(jArrF);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        return a(eVar);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return (this.f5434g[0] & 1) != 0;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.c.y(this.f5434g);
    }

    @Override // g.a.d.b.e.a
    public int u() {
        return u0.q(this.f5434g);
    }
}
