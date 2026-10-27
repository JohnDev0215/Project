package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class g0 extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5337g = e0.f5321i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5338h;

    public g0() {
        this.f5338h = g.a.d.d.g.h();
    }

    public g0(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5337g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256K1FieldElement");
        }
        this.f5338h = f0.c(bigInteger);
    }

    public g0(int[] iArr) {
        this.f5338h = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrH = g.a.d.d.g.h();
        f0.a(this.f5338h, ((g0) eVar).f5338h, iArrH);
        return new g0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrH = g.a.d.d.g.h();
        f0.b(this.f5338h, iArrH);
        return new g0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrH = g.a.d.d.g.h();
        g.a.d.d.b.d(f0.f5329a, ((g0) eVar).f5338h, iArrH);
        f0.d(iArrH, this.f5338h, iArrH);
        return new g0(iArrH);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g0) {
            return g.a.d.d.g.m(this.f5338h, ((g0) obj).f5338h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5337g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrH = g.a.d.d.g.h();
        g.a.d.d.b.d(f0.f5329a, this.f5338h, iArrH);
        return new g0(iArrH);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.g.t(this.f5338h);
    }

    public int hashCode() {
        return f5337g.hashCode() ^ g.a.f.a.C(this.f5338h, 0, 8);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.g.v(this.f5338h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrH = g.a.d.d.g.h();
        f0.d(this.f5338h, ((g0) eVar).f5338h, iArrH);
        return new g0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrH = g.a.d.d.g.h();
        f0.f(this.f5338h, iArrH);
        return new g0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5338h;
        if (g.a.d.d.g.v(iArr) || g.a.d.d.g.t(iArr)) {
            return this;
        }
        int[] iArrH = g.a.d.d.g.h();
        f0.i(iArr, iArrH);
        f0.d(iArrH, iArr, iArrH);
        int[] iArrH2 = g.a.d.d.g.h();
        f0.i(iArrH, iArrH2);
        f0.d(iArrH2, iArr, iArrH2);
        int[] iArrH3 = g.a.d.d.g.h();
        f0.j(iArrH2, 3, iArrH3);
        f0.d(iArrH3, iArrH2, iArrH3);
        f0.j(iArrH3, 3, iArrH3);
        f0.d(iArrH3, iArrH2, iArrH3);
        f0.j(iArrH3, 2, iArrH3);
        f0.d(iArrH3, iArrH, iArrH3);
        int[] iArrH4 = g.a.d.d.g.h();
        f0.j(iArrH3, 11, iArrH4);
        f0.d(iArrH4, iArrH3, iArrH4);
        f0.j(iArrH4, 22, iArrH3);
        f0.d(iArrH3, iArrH4, iArrH3);
        int[] iArrH5 = g.a.d.d.g.h();
        f0.j(iArrH3, 44, iArrH5);
        f0.d(iArrH5, iArrH3, iArrH5);
        int[] iArrH6 = g.a.d.d.g.h();
        f0.j(iArrH5, 88, iArrH6);
        f0.d(iArrH6, iArrH5, iArrH6);
        f0.j(iArrH6, 44, iArrH5);
        f0.d(iArrH5, iArrH3, iArrH5);
        f0.j(iArrH5, 3, iArrH3);
        f0.d(iArrH3, iArrH2, iArrH3);
        f0.j(iArrH3, 23, iArrH3);
        f0.d(iArrH3, iArrH4, iArrH3);
        f0.j(iArrH3, 6, iArrH3);
        f0.d(iArrH3, iArrH, iArrH3);
        f0.j(iArrH3, 2, iArrH3);
        f0.i(iArrH3, iArrH);
        if (g.a.d.d.g.m(iArr, iArrH)) {
            return new g0(iArrH3);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrH = g.a.d.d.g.h();
        f0.i(this.f5338h, iArrH);
        return new g0(iArrH);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrH = g.a.d.d.g.h();
        f0.k(this.f5338h, ((g0) eVar).f5338h, iArrH);
        return new g0(iArrH);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.g.q(this.f5338h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.g.J(this.f5338h);
    }
}
