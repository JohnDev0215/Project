package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class m extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5374g = k.f5361i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5375h;

    public m() {
        this.f5375h = g.a.d.d.d.d();
    }

    public m(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5374g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R2FieldElement");
        }
        this.f5375h = l.c(bigInteger);
    }

    public m(int[] iArr) {
        this.f5375h = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrD = g.a.d.d.d.d();
        l.a(this.f5375h, ((m) eVar).f5375h, iArrD);
        return new m(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrD = g.a.d.d.d.d();
        l.b(this.f5375h, iArrD);
        return new m(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrD = g.a.d.d.d.d();
        g.a.d.d.b.d(l.f5370a, ((m) eVar).f5375h, iArrD);
        l.d(iArrD, this.f5375h, iArrD);
        return new m(iArrD);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof m) {
            return g.a.d.d.d.f(this.f5375h, ((m) obj).f5375h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5374g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrD = g.a.d.d.d.d();
        g.a.d.d.b.d(l.f5370a, this.f5375h, iArrD);
        return new m(iArrD);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.d.j(this.f5375h);
    }

    public int hashCode() {
        return f5374g.hashCode() ^ g.a.f.a.C(this.f5375h, 0, 5);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.d.k(this.f5375h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrD = g.a.d.d.d.d();
        l.d(this.f5375h, ((m) eVar).f5375h, iArrD);
        return new m(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrD = g.a.d.d.d.d();
        l.f(this.f5375h, iArrD);
        return new m(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5375h;
        if (g.a.d.d.d.k(iArr) || g.a.d.d.d.j(iArr)) {
            return this;
        }
        int[] iArrD = g.a.d.d.d.d();
        l.i(iArr, iArrD);
        l.d(iArrD, iArr, iArrD);
        int[] iArrD2 = g.a.d.d.d.d();
        l.i(iArrD, iArrD2);
        l.d(iArrD2, iArr, iArrD2);
        int[] iArrD3 = g.a.d.d.d.d();
        l.i(iArrD2, iArrD3);
        l.d(iArrD3, iArr, iArrD3);
        int[] iArrD4 = g.a.d.d.d.d();
        l.j(iArrD3, 3, iArrD4);
        l.d(iArrD4, iArrD2, iArrD4);
        l.j(iArrD4, 7, iArrD3);
        l.d(iArrD3, iArrD4, iArrD3);
        l.j(iArrD3, 3, iArrD4);
        l.d(iArrD4, iArrD2, iArrD4);
        int[] iArrD5 = g.a.d.d.d.d();
        l.j(iArrD4, 14, iArrD5);
        l.d(iArrD5, iArrD3, iArrD5);
        l.j(iArrD5, 31, iArrD3);
        l.d(iArrD3, iArrD5, iArrD3);
        l.j(iArrD3, 62, iArrD5);
        l.d(iArrD5, iArrD3, iArrD5);
        l.j(iArrD5, 3, iArrD3);
        l.d(iArrD3, iArrD2, iArrD3);
        l.j(iArrD3, 18, iArrD3);
        l.d(iArrD3, iArrD4, iArrD3);
        l.j(iArrD3, 2, iArrD3);
        l.d(iArrD3, iArr, iArrD3);
        l.j(iArrD3, 3, iArrD3);
        l.d(iArrD3, iArrD, iArrD3);
        l.j(iArrD3, 6, iArrD3);
        l.d(iArrD3, iArrD2, iArrD3);
        l.j(iArrD3, 2, iArrD3);
        l.d(iArrD3, iArr, iArrD3);
        l.i(iArrD3, iArrD);
        if (g.a.d.d.d.f(iArr, iArrD)) {
            return new m(iArrD3);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrD = g.a.d.d.d.d();
        l.i(this.f5375h, iArrD);
        return new m(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrD = g.a.d.d.d.d();
        l.k(this.f5375h, ((m) eVar).f5375h, iArrD);
        return new m(iArrD);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.d.h(this.f5375h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.d.u(this.f5375h);
    }
}
