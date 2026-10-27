package g.a.d.b.a0.b;

import g.a.d.b.e;
import g.a.d.d.g;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class c extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5288g = a.f5282i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5289h;

    public c() {
        this.f5289h = g.h();
    }

    public c(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5288g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SM2P256V1FieldElement");
        }
        this.f5289h = b.d(bigInteger);
    }

    public c(int[] iArr) {
        this.f5289h = iArr;
    }

    @Override // g.a.d.b.e
    public e a(e eVar) {
        int[] iArrH = g.h();
        b.a(this.f5289h, ((c) eVar).f5289h, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e b() {
        int[] iArrH = g.h();
        b.b(this.f5289h, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e d(e eVar) {
        int[] iArrH = g.h();
        g.a.d.d.b.d(b.f5286a, ((c) eVar).f5289h, iArrH);
        b.e(iArrH, this.f5289h, iArrH);
        return new c(iArrH);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c) {
            return g.m(this.f5289h, ((c) obj).f5289h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5288g.bitLength();
    }

    @Override // g.a.d.b.e
    public e g() {
        int[] iArrH = g.h();
        g.a.d.d.b.d(b.f5286a, this.f5289h, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.t(this.f5289h);
    }

    public int hashCode() {
        return f5288g.hashCode() ^ g.a.f.a.C(this.f5289h, 0, 8);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.v(this.f5289h);
    }

    @Override // g.a.d.b.e
    public e j(e eVar) {
        int[] iArrH = g.h();
        b.e(this.f5289h, ((c) eVar).f5289h, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e m() {
        int[] iArrH = g.h();
        b.g(this.f5289h, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e n() {
        int[] iArr = this.f5289h;
        if (g.v(iArr) || g.t(iArr)) {
            return this;
        }
        int[] iArrH = g.h();
        b.j(iArr, iArrH);
        b.e(iArrH, iArr, iArrH);
        int[] iArrH2 = g.h();
        b.k(iArrH, 2, iArrH2);
        b.e(iArrH2, iArrH, iArrH2);
        int[] iArrH3 = g.h();
        b.k(iArrH2, 2, iArrH3);
        b.e(iArrH3, iArrH, iArrH3);
        b.k(iArrH3, 6, iArrH);
        b.e(iArrH, iArrH3, iArrH);
        int[] iArrH4 = g.h();
        b.k(iArrH, 12, iArrH4);
        b.e(iArrH4, iArrH, iArrH4);
        b.k(iArrH4, 6, iArrH);
        b.e(iArrH, iArrH3, iArrH);
        b.j(iArrH, iArrH3);
        b.e(iArrH3, iArr, iArrH3);
        b.k(iArrH3, 31, iArrH4);
        b.e(iArrH4, iArrH3, iArrH);
        b.k(iArrH4, 32, iArrH4);
        b.e(iArrH4, iArrH, iArrH4);
        b.k(iArrH4, 62, iArrH4);
        b.e(iArrH4, iArrH, iArrH4);
        b.k(iArrH4, 4, iArrH4);
        b.e(iArrH4, iArrH2, iArrH4);
        b.k(iArrH4, 32, iArrH4);
        b.e(iArrH4, iArr, iArrH4);
        b.k(iArrH4, 62, iArrH4);
        b.j(iArrH4, iArrH2);
        if (g.m(iArr, iArrH2)) {
            return new c(iArrH4);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public e o() {
        int[] iArrH = g.h();
        b.j(this.f5289h, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e r(e eVar) {
        int[] iArrH = g.h();
        b.m(this.f5289h, ((c) eVar).f5289h, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.q(this.f5289h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.J(this.f5289h);
    }
}
