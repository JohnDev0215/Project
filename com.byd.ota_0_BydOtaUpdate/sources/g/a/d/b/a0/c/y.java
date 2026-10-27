package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class y extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5449g = w.f5436i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f5450h = {868209154, -587542221, 579297866, -1014948952, -1470801668, 514782679, -1897982644};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f5451i;

    public y() {
        this.f5451i = g.a.d.d.f.e();
    }

    public y(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5449g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224K1FieldElement");
        }
        this.f5451i = x.c(bigInteger);
    }

    public y(int[] iArr) {
        this.f5451i = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.f.e();
        x.a(this.f5451i, ((y) eVar).f5451i, iArrE);
        return new y(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrE = g.a.d.d.f.e();
        x.b(this.f5451i, iArrE);
        return new y(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.f.e();
        g.a.d.d.b.d(x.f5446a, ((y) eVar).f5451i, iArrE);
        x.d(iArrE, this.f5451i, iArrE);
        return new y(iArrE);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof y) {
            return g.a.d.d.f.g(this.f5451i, ((y) obj).f5451i);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5449g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrE = g.a.d.d.f.e();
        g.a.d.d.b.d(x.f5446a, this.f5451i, iArrE);
        return new y(iArrE);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.f.k(this.f5451i);
    }

    public int hashCode() {
        return f5449g.hashCode() ^ g.a.f.a.C(this.f5451i, 0, 7);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.f.l(this.f5451i);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.f.e();
        x.d(this.f5451i, ((y) eVar).f5451i, iArrE);
        return new y(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrE = g.a.d.d.f.e();
        x.f(this.f5451i, iArrE);
        return new y(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5451i;
        if (g.a.d.d.f.l(iArr) || g.a.d.d.f.k(iArr)) {
            return this;
        }
        int[] iArrE = g.a.d.d.f.e();
        x.i(iArr, iArrE);
        x.d(iArrE, iArr, iArrE);
        x.i(iArrE, iArrE);
        x.d(iArrE, iArr, iArrE);
        int[] iArrE2 = g.a.d.d.f.e();
        x.i(iArrE, iArrE2);
        x.d(iArrE2, iArr, iArrE2);
        int[] iArrE3 = g.a.d.d.f.e();
        x.j(iArrE2, 4, iArrE3);
        x.d(iArrE3, iArrE2, iArrE3);
        int[] iArrE4 = g.a.d.d.f.e();
        x.j(iArrE3, 3, iArrE4);
        x.d(iArrE4, iArrE, iArrE4);
        x.j(iArrE4, 8, iArrE4);
        x.d(iArrE4, iArrE3, iArrE4);
        x.j(iArrE4, 4, iArrE3);
        x.d(iArrE3, iArrE2, iArrE3);
        x.j(iArrE3, 19, iArrE2);
        x.d(iArrE2, iArrE4, iArrE2);
        int[] iArrE5 = g.a.d.d.f.e();
        x.j(iArrE2, 42, iArrE5);
        x.d(iArrE5, iArrE2, iArrE5);
        x.j(iArrE5, 23, iArrE2);
        x.d(iArrE2, iArrE3, iArrE2);
        x.j(iArrE2, 84, iArrE3);
        x.d(iArrE3, iArrE5, iArrE3);
        x.j(iArrE3, 20, iArrE3);
        x.d(iArrE3, iArrE4, iArrE3);
        x.j(iArrE3, 3, iArrE3);
        x.d(iArrE3, iArr, iArrE3);
        x.j(iArrE3, 2, iArrE3);
        x.d(iArrE3, iArr, iArrE3);
        x.j(iArrE3, 4, iArrE3);
        x.d(iArrE3, iArrE, iArrE3);
        x.i(iArrE3, iArrE3);
        x.i(iArrE3, iArrE5);
        if (g.a.d.d.f.g(iArr, iArrE5)) {
            return new y(iArrE3);
        }
        x.d(iArrE3, f5450h, iArrE3);
        x.i(iArrE3, iArrE5);
        if (g.a.d.d.f.g(iArr, iArrE5)) {
            return new y(iArrE3);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrE = g.a.d.d.f.e();
        x.i(this.f5451i, iArrE);
        return new y(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.f.e();
        x.k(this.f5451i, ((y) eVar).f5451i, iArrE);
        return new y(iArrE);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.f.i(this.f5451i, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.f.u(this.f5451i);
    }
}
