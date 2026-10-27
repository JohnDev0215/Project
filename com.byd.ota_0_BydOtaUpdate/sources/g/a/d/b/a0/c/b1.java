package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class b1 extends e.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f5305g;

    public b1() {
        this.f5305g = g.a.d.d.e.h();
    }

    public b1(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 131) {
            throw new IllegalArgumentException("x value invalid for SecT131FieldElement");
        }
        this.f5305g = a1.d(bigInteger);
    }

    public b1(long[] jArr) {
        this.f5305g = jArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        long[] jArrH = g.a.d.d.e.h();
        a1.a(this.f5305g, ((b1) eVar).f5305g, jArrH);
        return new b1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        long[] jArrH = g.a.d.d.e.h();
        a1.c(this.f5305g, jArrH);
        return new b1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        return j(eVar.g());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b1) {
            return g.a.d.d.e.m(this.f5305g, ((b1) obj).f5305g);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return 131;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        long[] jArrH = g.a.d.d.e.h();
        a1.i(this.f5305g, jArrH);
        return new b1(jArrH);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.e.t(this.f5305g);
    }

    public int hashCode() {
        return g.a.f.a.D(this.f5305g, 0, 3) ^ 131832;
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.e.v(this.f5305g);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        long[] jArrH = g.a.d.d.e.h();
        a1.j(this.f5305g, ((b1) eVar).f5305g, jArrH);
        return new b1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e k(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        return l(eVar, eVar2, eVar3);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e l(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e eVar3) {
        long[] jArr = this.f5305g;
        long[] jArr2 = ((b1) eVar).f5305g;
        long[] jArr3 = ((b1) eVar2).f5305g;
        long[] jArr4 = ((b1) eVar3).f5305g;
        long[] jArrK = g.a.d.d.m.k(5);
        a1.k(jArr, jArr2, jArrK);
        a1.k(jArr3, jArr4, jArrK);
        long[] jArrH = g.a.d.d.e.h();
        a1.l(jArrK, jArrH);
        return new b1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        return this;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        long[] jArrH = g.a.d.d.e.h();
        a1.n(this.f5305g, jArrH);
        return new b1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        long[] jArrH = g.a.d.d.e.h();
        a1.o(this.f5305g, jArrH);
        return new b1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e p(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        long[] jArr = this.f5305g;
        long[] jArr2 = ((b1) eVar).f5305g;
        long[] jArr3 = ((b1) eVar2).f5305g;
        long[] jArrK = g.a.d.d.m.k(5);
        a1.p(jArr, jArrK);
        a1.k(jArr2, jArr3, jArrK);
        long[] jArrH = g.a.d.d.e.h();
        a1.l(jArrK, jArrH);
        return new b1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e q(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] jArrH = g.a.d.d.e.h();
        a1.q(this.f5305g, i2, jArrH);
        return new b1(jArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        return a(eVar);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return (this.f5305g[0] & 1) != 0;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.e.I(this.f5305g);
    }

    @Override // g.a.d.b.e.a
    public int u() {
        return a1.r(this.f5305g);
    }
}
