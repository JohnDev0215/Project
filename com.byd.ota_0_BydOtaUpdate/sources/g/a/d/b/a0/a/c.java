package g.a.d.b.a0.a;

import g.a.d.b.e;
import g.a.d.d.g;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class c extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5279g = a.f5273i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f5280h = {1242472624, -991028441, -1389370248, 792926214, 1039914919, 726466713, 1338105611, 730014848};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f5281i;

    public c() {
        this.f5281i = g.h();
    }

    public c(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5279g) >= 0) {
            throw new IllegalArgumentException("x value invalid for Curve25519FieldElement");
        }
        this.f5281i = b.d(bigInteger);
    }

    public c(int[] iArr) {
        this.f5281i = iArr;
    }

    @Override // g.a.d.b.e
    public e a(e eVar) {
        int[] iArrH = g.h();
        b.a(this.f5281i, ((c) eVar).f5281i, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e b() {
        int[] iArrH = g.h();
        b.b(this.f5281i, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e d(e eVar) {
        int[] iArrH = g.h();
        g.a.d.d.b.d(b.f5277a, ((c) eVar).f5281i, iArrH);
        b.e(iArrH, this.f5281i, iArrH);
        return new c(iArrH);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c) {
            return g.m(this.f5281i, ((c) obj).f5281i);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5279g.bitLength();
    }

    @Override // g.a.d.b.e
    public e g() {
        int[] iArrH = g.h();
        g.a.d.d.b.d(b.f5277a, this.f5281i, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.t(this.f5281i);
    }

    public int hashCode() {
        return f5279g.hashCode() ^ g.a.f.a.C(this.f5281i, 0, 8);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.v(this.f5281i);
    }

    @Override // g.a.d.b.e
    public e j(e eVar) {
        int[] iArrH = g.h();
        b.e(this.f5281i, ((c) eVar).f5281i, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e m() {
        int[] iArrH = g.h();
        b.g(this.f5281i, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e n() {
        int[] iArr = this.f5281i;
        if (g.v(iArr) || g.t(iArr)) {
            return this;
        }
        int[] iArrH = g.h();
        b.j(iArr, iArrH);
        b.e(iArrH, iArr, iArrH);
        b.j(iArrH, iArrH);
        b.e(iArrH, iArr, iArrH);
        int[] iArrH2 = g.h();
        b.j(iArrH, iArrH2);
        b.e(iArrH2, iArr, iArrH2);
        int[] iArrH3 = g.h();
        b.k(iArrH2, 3, iArrH3);
        b.e(iArrH3, iArrH, iArrH3);
        b.k(iArrH3, 4, iArrH);
        b.e(iArrH, iArrH2, iArrH);
        b.k(iArrH, 4, iArrH3);
        b.e(iArrH3, iArrH2, iArrH3);
        b.k(iArrH3, 15, iArrH2);
        b.e(iArrH2, iArrH3, iArrH2);
        b.k(iArrH2, 30, iArrH3);
        b.e(iArrH3, iArrH2, iArrH3);
        b.k(iArrH3, 60, iArrH2);
        b.e(iArrH2, iArrH3, iArrH2);
        b.k(iArrH2, 11, iArrH3);
        b.e(iArrH3, iArrH, iArrH3);
        b.k(iArrH3, 120, iArrH);
        b.e(iArrH, iArrH2, iArrH);
        b.j(iArrH, iArrH);
        b.j(iArrH, iArrH2);
        if (g.m(iArr, iArrH2)) {
            return new c(iArrH);
        }
        b.e(iArrH, f5280h, iArrH);
        b.j(iArrH, iArrH2);
        if (g.m(iArr, iArrH2)) {
            return new c(iArrH);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public e o() {
        int[] iArrH = g.h();
        b.j(this.f5281i, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public e r(e eVar) {
        int[] iArrH = g.h();
        b.n(this.f5281i, ((c) eVar).f5281i, iArrH);
        return new c(iArrH);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.q(this.f5281i, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.J(this.f5281i);
    }
}
