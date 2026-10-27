package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class u extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5429g = s.f5414i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5430h;

    public u() {
        this.f5430h = g.a.d.d.e.g();
    }

    public u(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5429g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192R1FieldElement");
        }
        this.f5430h = t.d(bigInteger);
    }

    public u(int[] iArr) {
        this.f5430h = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrG = g.a.d.d.e.g();
        t.a(this.f5430h, ((u) eVar).f5430h, iArrG);
        return new u(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrG = g.a.d.d.e.g();
        t.b(this.f5430h, iArrG);
        return new u(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrG = g.a.d.d.e.g();
        g.a.d.d.b.d(t.f5426a, ((u) eVar).f5430h, iArrG);
        t.e(iArrG, this.f5430h, iArrG);
        return new u(iArrG);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof u) {
            return g.a.d.d.e.l(this.f5430h, ((u) obj).f5430h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5429g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrG = g.a.d.d.e.g();
        g.a.d.d.b.d(t.f5426a, this.f5430h, iArrG);
        return new u(iArrG);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.e.s(this.f5430h);
    }

    public int hashCode() {
        return f5429g.hashCode() ^ g.a.f.a.C(this.f5430h, 0, 6);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.e.u(this.f5430h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrG = g.a.d.d.e.g();
        t.e(this.f5430h, ((u) eVar).f5430h, iArrG);
        return new u(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrG = g.a.d.d.e.g();
        t.g(this.f5430h, iArrG);
        return new u(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5430h;
        if (g.a.d.d.e.u(iArr) || g.a.d.d.e.s(iArr)) {
            return this;
        }
        int[] iArrG = g.a.d.d.e.g();
        int[] iArrG2 = g.a.d.d.e.g();
        t.j(iArr, iArrG);
        t.e(iArrG, iArr, iArrG);
        t.k(iArrG, 2, iArrG2);
        t.e(iArrG2, iArrG, iArrG2);
        t.k(iArrG2, 4, iArrG);
        t.e(iArrG, iArrG2, iArrG);
        t.k(iArrG, 8, iArrG2);
        t.e(iArrG2, iArrG, iArrG2);
        t.k(iArrG2, 16, iArrG);
        t.e(iArrG, iArrG2, iArrG);
        t.k(iArrG, 32, iArrG2);
        t.e(iArrG2, iArrG, iArrG2);
        t.k(iArrG2, 64, iArrG);
        t.e(iArrG, iArrG2, iArrG);
        t.k(iArrG, 62, iArrG);
        t.j(iArrG, iArrG2);
        if (g.a.d.d.e.l(iArr, iArrG2)) {
            return new u(iArrG);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrG = g.a.d.d.e.g();
        t.j(this.f5430h, iArrG);
        return new u(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrG = g.a.d.d.e.g();
        t.m(this.f5430h, ((u) eVar).f5430h, iArrG);
        return new u(iArrG);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.e.p(this.f5430h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.e.H(this.f5430h);
    }
}
