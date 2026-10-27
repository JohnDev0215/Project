package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class o0 extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5393g = m0.f5376i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5394h;

    public o0() {
        this.f5394h = g.a.d.d.m.j(12);
    }

    public o0(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5393g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP384R1FieldElement");
        }
        this.f5394h = n0.e(bigInteger);
    }

    public o0(int[] iArr) {
        this.f5394h = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrJ = g.a.d.d.m.j(12);
        n0.a(this.f5394h, ((o0) eVar).f5394h, iArrJ);
        return new o0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrJ = g.a.d.d.m.j(12);
        n0.c(this.f5394h, iArrJ);
        return new o0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrJ = g.a.d.d.m.j(12);
        g.a.d.d.b.d(n0.f5386a, ((o0) eVar).f5394h, iArrJ);
        n0.f(iArrJ, this.f5394h, iArrJ);
        return new o0(iArrJ);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof o0) {
            return g.a.d.d.m.n(12, this.f5394h, ((o0) obj).f5394h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5393g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrJ = g.a.d.d.m.j(12);
        g.a.d.d.b.d(n0.f5386a, this.f5394h, iArrJ);
        return new o0(iArrJ);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.m.v(12, this.f5394h);
    }

    public int hashCode() {
        return f5393g.hashCode() ^ g.a.f.a.C(this.f5394h, 0, 12);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.m.w(12, this.f5394h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrJ = g.a.d.d.m.j(12);
        n0.f(this.f5394h, ((o0) eVar).f5394h, iArrJ);
        return new o0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrJ = g.a.d.d.m.j(12);
        n0.g(this.f5394h, iArrJ);
        return new o0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5394h;
        if (g.a.d.d.m.w(12, iArr) || g.a.d.d.m.v(12, iArr)) {
            return this;
        }
        int[] iArrJ = g.a.d.d.m.j(12);
        int[] iArrJ2 = g.a.d.d.m.j(12);
        int[] iArrJ3 = g.a.d.d.m.j(12);
        int[] iArrJ4 = g.a.d.d.m.j(12);
        n0.j(iArr, iArrJ);
        n0.f(iArrJ, iArr, iArrJ);
        n0.k(iArrJ, 2, iArrJ2);
        n0.f(iArrJ2, iArrJ, iArrJ2);
        n0.j(iArrJ2, iArrJ2);
        n0.f(iArrJ2, iArr, iArrJ2);
        n0.k(iArrJ2, 5, iArrJ3);
        n0.f(iArrJ3, iArrJ2, iArrJ3);
        n0.k(iArrJ3, 5, iArrJ4);
        n0.f(iArrJ4, iArrJ2, iArrJ4);
        n0.k(iArrJ4, 15, iArrJ2);
        n0.f(iArrJ2, iArrJ4, iArrJ2);
        n0.k(iArrJ2, 2, iArrJ3);
        n0.f(iArrJ, iArrJ3, iArrJ);
        n0.k(iArrJ3, 28, iArrJ3);
        n0.f(iArrJ2, iArrJ3, iArrJ2);
        n0.k(iArrJ2, 60, iArrJ3);
        n0.f(iArrJ3, iArrJ2, iArrJ3);
        n0.k(iArrJ3, 120, iArrJ2);
        n0.f(iArrJ2, iArrJ3, iArrJ2);
        n0.k(iArrJ2, 15, iArrJ2);
        n0.f(iArrJ2, iArrJ4, iArrJ2);
        n0.k(iArrJ2, 33, iArrJ2);
        n0.f(iArrJ2, iArrJ, iArrJ2);
        n0.k(iArrJ2, 64, iArrJ2);
        n0.f(iArrJ2, iArr, iArrJ2);
        n0.k(iArrJ2, 30, iArrJ);
        n0.j(iArrJ, iArrJ2);
        if (g.a.d.d.m.n(12, iArr, iArrJ2)) {
            return new o0(iArrJ);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrJ = g.a.d.d.m.j(12);
        n0.j(this.f5394h, iArrJ);
        return new o0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrJ = g.a.d.d.m.j(12);
        n0.m(this.f5394h, ((o0) eVar).f5394h, iArrJ);
        return new o0(iArrJ);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.m.p(this.f5394h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.m.P(12, this.f5394h);
    }
}
