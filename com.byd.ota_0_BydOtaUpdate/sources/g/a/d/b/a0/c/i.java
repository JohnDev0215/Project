package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class i extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5347g = g.f5333i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5348h;

    public i() {
        this.f5348h = g.a.d.d.d.d();
    }

    public i(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5347g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R1FieldElement");
        }
        this.f5348h = h.c(bigInteger);
    }

    public i(int[] iArr) {
        this.f5348h = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrD = g.a.d.d.d.d();
        h.a(this.f5348h, ((i) eVar).f5348h, iArrD);
        return new i(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrD = g.a.d.d.d.d();
        h.b(this.f5348h, iArrD);
        return new i(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrD = g.a.d.d.d.d();
        g.a.d.d.b.d(h.f5343a, ((i) eVar).f5348h, iArrD);
        h.d(iArrD, this.f5348h, iArrD);
        return new i(iArrD);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof i) {
            return g.a.d.d.d.f(this.f5348h, ((i) obj).f5348h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5347g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrD = g.a.d.d.d.d();
        g.a.d.d.b.d(h.f5343a, this.f5348h, iArrD);
        return new i(iArrD);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.d.j(this.f5348h);
    }

    public int hashCode() {
        return f5347g.hashCode() ^ g.a.f.a.C(this.f5348h, 0, 5);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.d.k(this.f5348h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrD = g.a.d.d.d.d();
        h.d(this.f5348h, ((i) eVar).f5348h, iArrD);
        return new i(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrD = g.a.d.d.d.d();
        h.f(this.f5348h, iArrD);
        return new i(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5348h;
        if (g.a.d.d.d.k(iArr) || g.a.d.d.d.j(iArr)) {
            return this;
        }
        int[] iArrD = g.a.d.d.d.d();
        h.i(iArr, iArrD);
        h.d(iArrD, iArr, iArrD);
        int[] iArrD2 = g.a.d.d.d.d();
        h.j(iArrD, 2, iArrD2);
        h.d(iArrD2, iArrD, iArrD2);
        h.j(iArrD2, 4, iArrD);
        h.d(iArrD, iArrD2, iArrD);
        h.j(iArrD, 8, iArrD2);
        h.d(iArrD2, iArrD, iArrD2);
        h.j(iArrD2, 16, iArrD);
        h.d(iArrD, iArrD2, iArrD);
        h.j(iArrD, 32, iArrD2);
        h.d(iArrD2, iArrD, iArrD2);
        h.j(iArrD2, 64, iArrD);
        h.d(iArrD, iArrD2, iArrD);
        h.i(iArrD, iArrD2);
        h.d(iArrD2, iArr, iArrD2);
        h.j(iArrD2, 29, iArrD2);
        h.i(iArrD2, iArrD);
        if (g.a.d.d.d.f(iArr, iArrD)) {
            return new i(iArrD2);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrD = g.a.d.d.d.d();
        h.i(this.f5348h, iArrD);
        return new i(iArrD);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrD = g.a.d.d.d.d();
        h.k(this.f5348h, ((i) eVar).f5348h, iArrD);
        return new i(iArrD);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.d.h(this.f5348h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.d.u(this.f5348h);
    }
}
