package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class q extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5402g = o.f5389i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5403h;

    public q() {
        this.f5403h = g.a.d.d.e.g();
    }

    public q(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5402g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192K1FieldElement");
        }
        this.f5403h = p.c(bigInteger);
    }

    public q(int[] iArr) {
        this.f5403h = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrG = g.a.d.d.e.g();
        p.a(this.f5403h, ((q) eVar).f5403h, iArrG);
        return new q(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrG = g.a.d.d.e.g();
        p.b(this.f5403h, iArrG);
        return new q(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrG = g.a.d.d.e.g();
        g.a.d.d.b.d(p.f5398a, ((q) eVar).f5403h, iArrG);
        p.d(iArrG, this.f5403h, iArrG);
        return new q(iArrG);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof q) {
            return g.a.d.d.e.l(this.f5403h, ((q) obj).f5403h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5402g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrG = g.a.d.d.e.g();
        g.a.d.d.b.d(p.f5398a, this.f5403h, iArrG);
        return new q(iArrG);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.e.s(this.f5403h);
    }

    public int hashCode() {
        return f5402g.hashCode() ^ g.a.f.a.C(this.f5403h, 0, 6);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.e.u(this.f5403h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrG = g.a.d.d.e.g();
        p.d(this.f5403h, ((q) eVar).f5403h, iArrG);
        return new q(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrG = g.a.d.d.e.g();
        p.f(this.f5403h, iArrG);
        return new q(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5403h;
        if (g.a.d.d.e.u(iArr) || g.a.d.d.e.s(iArr)) {
            return this;
        }
        int[] iArrG = g.a.d.d.e.g();
        p.i(iArr, iArrG);
        p.d(iArrG, iArr, iArrG);
        int[] iArrG2 = g.a.d.d.e.g();
        p.i(iArrG, iArrG2);
        p.d(iArrG2, iArr, iArrG2);
        int[] iArrG3 = g.a.d.d.e.g();
        p.j(iArrG2, 3, iArrG3);
        p.d(iArrG3, iArrG2, iArrG3);
        p.j(iArrG3, 2, iArrG3);
        p.d(iArrG3, iArrG, iArrG3);
        p.j(iArrG3, 8, iArrG);
        p.d(iArrG, iArrG3, iArrG);
        p.j(iArrG, 3, iArrG3);
        p.d(iArrG3, iArrG2, iArrG3);
        int[] iArrG4 = g.a.d.d.e.g();
        p.j(iArrG3, 16, iArrG4);
        p.d(iArrG4, iArrG, iArrG4);
        p.j(iArrG4, 35, iArrG);
        p.d(iArrG, iArrG4, iArrG);
        p.j(iArrG, 70, iArrG4);
        p.d(iArrG4, iArrG, iArrG4);
        p.j(iArrG4, 19, iArrG);
        p.d(iArrG, iArrG3, iArrG);
        p.j(iArrG, 20, iArrG);
        p.d(iArrG, iArrG3, iArrG);
        p.j(iArrG, 4, iArrG);
        p.d(iArrG, iArrG2, iArrG);
        p.j(iArrG, 6, iArrG);
        p.d(iArrG, iArrG2, iArrG);
        p.i(iArrG, iArrG);
        p.i(iArrG, iArrG2);
        if (g.a.d.d.e.l(iArr, iArrG2)) {
            return new q(iArrG);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrG = g.a.d.d.e.g();
        p.i(this.f5403h, iArrG);
        return new q(iArrG);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrG = g.a.d.d.e.g();
        p.k(this.f5403h, ((q) eVar).f5403h, iArrG);
        return new q(iArrG);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.e.p(this.f5403h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.e.H(this.f5403h);
    }
}
