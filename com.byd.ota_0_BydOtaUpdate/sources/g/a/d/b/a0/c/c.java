package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class c extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5307g = a.f5290i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5308h;

    public c() {
        this.f5308h = g.a.d.d.c.e();
    }

    public c(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5307g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP128R1FieldElement");
        }
        this.f5308h = b.d(bigInteger);
    }

    public c(int[] iArr) {
        this.f5308h = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.c.e();
        b.a(this.f5308h, ((c) eVar).f5308h, iArrE);
        return new c(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrE = g.a.d.d.c.e();
        b.b(this.f5308h, iArrE);
        return new c(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.c.e();
        g.a.d.d.b.d(b.f5299a, ((c) eVar).f5308h, iArrE);
        b.e(iArrE, this.f5308h, iArrE);
        return new c(iArrE);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c) {
            return g.a.d.d.c.i(this.f5308h, ((c) obj).f5308h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5307g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrE = g.a.d.d.c.e();
        g.a.d.d.b.d(b.f5299a, this.f5308h, iArrE);
        return new c(iArrE);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.c.o(this.f5308h);
    }

    public int hashCode() {
        return f5307g.hashCode() ^ g.a.f.a.C(this.f5308h, 0, 4);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.c.q(this.f5308h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.c.e();
        b.e(this.f5308h, ((c) eVar).f5308h, iArrE);
        return new c(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrE = g.a.d.d.c.e();
        b.g(this.f5308h, iArrE);
        return new c(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5308h;
        if (g.a.d.d.c.q(iArr) || g.a.d.d.c.o(iArr)) {
            return this;
        }
        int[] iArrE = g.a.d.d.c.e();
        b.j(iArr, iArrE);
        b.e(iArrE, iArr, iArrE);
        int[] iArrE2 = g.a.d.d.c.e();
        b.k(iArrE, 2, iArrE2);
        b.e(iArrE2, iArrE, iArrE2);
        int[] iArrE3 = g.a.d.d.c.e();
        b.k(iArrE2, 4, iArrE3);
        b.e(iArrE3, iArrE2, iArrE3);
        b.k(iArrE3, 2, iArrE2);
        b.e(iArrE2, iArrE, iArrE2);
        b.k(iArrE2, 10, iArrE);
        b.e(iArrE, iArrE2, iArrE);
        b.k(iArrE, 10, iArrE3);
        b.e(iArrE3, iArrE2, iArrE3);
        b.j(iArrE3, iArrE2);
        b.e(iArrE2, iArr, iArrE2);
        b.k(iArrE2, 95, iArrE2);
        b.j(iArrE2, iArrE3);
        if (g.a.d.d.c.i(iArr, iArrE3)) {
            return new c(iArrE2);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrE = g.a.d.d.c.e();
        b.j(this.f5308h, iArrE);
        return new c(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.c.e();
        b.m(this.f5308h, ((c) eVar).f5308h, iArrE);
        return new c(iArrE);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.c.m(this.f5308h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.c.x(this.f5308h);
    }
}
