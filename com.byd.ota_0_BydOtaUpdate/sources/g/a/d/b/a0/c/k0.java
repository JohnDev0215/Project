package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class k0 extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5365g = i0.f5349i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5366h;

    public k0() {
        this.f5366h = g.a.d.d.g.h();
    }

    public k0(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5365g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256R1FieldElement");
        }
        this.f5366h = j0.d(bigInteger);
    }

    public k0(int[] iArr) {
        this.f5366h = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrH = g.a.d.d.g.h();
        j0.a(this.f5366h, ((k0) eVar).f5366h, iArrH);
        return new k0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrH = g.a.d.d.g.h();
        j0.b(this.f5366h, iArrH);
        return new k0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrH = g.a.d.d.g.h();
        g.a.d.d.b.d(j0.f5359a, ((k0) eVar).f5366h, iArrH);
        j0.e(iArrH, this.f5366h, iArrH);
        return new k0(iArrH);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof k0) {
            return g.a.d.d.g.m(this.f5366h, ((k0) obj).f5366h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5365g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrH = g.a.d.d.g.h();
        g.a.d.d.b.d(j0.f5359a, this.f5366h, iArrH);
        return new k0(iArrH);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.g.t(this.f5366h);
    }

    public int hashCode() {
        return f5365g.hashCode() ^ g.a.f.a.C(this.f5366h, 0, 8);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.g.v(this.f5366h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrH = g.a.d.d.g.h();
        j0.e(this.f5366h, ((k0) eVar).f5366h, iArrH);
        return new k0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrH = g.a.d.d.g.h();
        j0.g(this.f5366h, iArrH);
        return new k0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5366h;
        if (g.a.d.d.g.v(iArr) || g.a.d.d.g.t(iArr)) {
            return this;
        }
        int[] iArrH = g.a.d.d.g.h();
        int[] iArrH2 = g.a.d.d.g.h();
        j0.j(iArr, iArrH);
        j0.e(iArrH, iArr, iArrH);
        j0.k(iArrH, 2, iArrH2);
        j0.e(iArrH2, iArrH, iArrH2);
        j0.k(iArrH2, 4, iArrH);
        j0.e(iArrH, iArrH2, iArrH);
        j0.k(iArrH, 8, iArrH2);
        j0.e(iArrH2, iArrH, iArrH2);
        j0.k(iArrH2, 16, iArrH);
        j0.e(iArrH, iArrH2, iArrH);
        j0.k(iArrH, 32, iArrH);
        j0.e(iArrH, iArr, iArrH);
        j0.k(iArrH, 96, iArrH);
        j0.e(iArrH, iArr, iArrH);
        j0.k(iArrH, 94, iArrH);
        j0.j(iArrH, iArrH2);
        if (g.a.d.d.g.m(iArr, iArrH2)) {
            return new k0(iArrH);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrH = g.a.d.d.g.h();
        j0.j(this.f5366h, iArrH);
        return new k0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrH = g.a.d.d.g.h();
        j0.m(this.f5366h, ((k0) eVar).f5366h, iArrH);
        return new k0(iArrH);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.g.q(this.f5366h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.g.J(this.f5366h);
    }
}
