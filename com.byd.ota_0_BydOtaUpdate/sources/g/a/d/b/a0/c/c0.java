package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class c0 extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5309g = a0.f5294i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5310h;

    public c0() {
        this.f5310h = g.a.d.d.f.e();
    }

    public c0(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5309g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224R1FieldElement");
        }
        this.f5310h = b0.d(bigInteger);
    }

    public c0(int[] iArr) {
        this.f5310h = iArr;
    }

    public static void u(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6, int[] iArr7) {
        b0.e(iArr5, iArr3, iArr7);
        b0.e(iArr7, iArr, iArr7);
        b0.e(iArr4, iArr2, iArr6);
        b0.a(iArr6, iArr7, iArr6);
        b0.e(iArr4, iArr3, iArr7);
        g.a.d.d.f.d(iArr6, iArr4);
        b0.e(iArr5, iArr2, iArr5);
        b0.a(iArr5, iArr7, iArr5);
        b0.j(iArr5, iArr6);
        b0.e(iArr6, iArr, iArr6);
    }

    public static void v(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5) {
        g.a.d.d.f.d(iArr, iArr4);
        int[] iArrE = g.a.d.d.f.e();
        int[] iArrE2 = g.a.d.d.f.e();
        for (int i2 = 0; i2 < 7; i2++) {
            g.a.d.d.f.d(iArr2, iArrE);
            g.a.d.d.f.d(iArr3, iArrE2);
            int i3 = 1 << i2;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    w(iArr2, iArr3, iArr4, iArr5);
                }
            }
            u(iArr, iArrE, iArrE2, iArr2, iArr3, iArr4, iArr5);
        }
    }

    public static void w(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        b0.e(iArr2, iArr, iArr2);
        b0.n(iArr2, iArr2);
        b0.j(iArr, iArr4);
        b0.a(iArr3, iArr4, iArr);
        b0.e(iArr3, iArr4, iArr3);
        b0.i(g.a.d.d.m.G(7, iArr3, 2, 0), iArr3);
    }

    public static boolean x(int[] iArr) {
        int[] iArrE = g.a.d.d.f.e();
        int[] iArrE2 = g.a.d.d.f.e();
        g.a.d.d.f.d(iArr, iArrE);
        for (int i2 = 0; i2 < 7; i2++) {
            g.a.d.d.f.d(iArrE, iArrE2);
            b0.k(iArrE, 1 << i2, iArrE);
            b0.e(iArrE, iArrE2, iArrE);
        }
        b0.k(iArrE, 95, iArrE);
        return g.a.d.d.f.k(iArrE);
    }

    public static boolean y(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrE = g.a.d.d.f.e();
        g.a.d.d.f.d(iArr2, iArrE);
        int[] iArrE2 = g.a.d.d.f.e();
        iArrE2[0] = 1;
        int[] iArrE3 = g.a.d.d.f.e();
        v(iArr, iArrE, iArrE2, iArrE3, iArr3);
        int[] iArrE4 = g.a.d.d.f.e();
        int[] iArrE5 = g.a.d.d.f.e();
        for (int i2 = 1; i2 < 96; i2++) {
            g.a.d.d.f.d(iArrE, iArrE4);
            g.a.d.d.f.d(iArrE2, iArrE5);
            w(iArrE, iArrE2, iArrE3, iArr3);
            if (g.a.d.d.f.l(iArrE)) {
                g.a.d.d.b.d(b0.f5302a, iArrE5, iArr3);
                b0.e(iArr3, iArrE4, iArr3);
                return true;
            }
        }
        return false;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.f.e();
        b0.a(this.f5310h, ((c0) eVar).f5310h, iArrE);
        return new c0(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrE = g.a.d.d.f.e();
        b0.b(this.f5310h, iArrE);
        return new c0(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.f.e();
        g.a.d.d.b.d(b0.f5302a, ((c0) eVar).f5310h, iArrE);
        b0.e(iArrE, this.f5310h, iArrE);
        return new c0(iArrE);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c0) {
            return g.a.d.d.f.g(this.f5310h, ((c0) obj).f5310h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5309g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrE = g.a.d.d.f.e();
        g.a.d.d.b.d(b0.f5302a, this.f5310h, iArrE);
        return new c0(iArrE);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.f.k(this.f5310h);
    }

    public int hashCode() {
        return f5309g.hashCode() ^ g.a.f.a.C(this.f5310h, 0, 7);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.f.l(this.f5310h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.f.e();
        b0.e(this.f5310h, ((c0) eVar).f5310h, iArrE);
        return new c0(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrE = g.a.d.d.f.e();
        b0.g(this.f5310h, iArrE);
        return new c0(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5310h;
        if (g.a.d.d.f.l(iArr) || g.a.d.d.f.k(iArr)) {
            return this;
        }
        int[] iArrE = g.a.d.d.f.e();
        b0.g(iArr, iArrE);
        int[] iArrE2 = g.a.d.d.b.e(b0.f5302a);
        int[] iArrE3 = g.a.d.d.f.e();
        if (!x(iArr)) {
            return null;
        }
        while (!y(iArrE, iArrE2, iArrE3)) {
            b0.b(iArrE2, iArrE2);
        }
        b0.j(iArrE3, iArrE2);
        if (g.a.d.d.f.g(iArr, iArrE2)) {
            return new c0(iArrE3);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrE = g.a.d.d.f.e();
        b0.j(this.f5310h, iArrE);
        return new c0(iArrE);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrE = g.a.d.d.f.e();
        b0.m(this.f5310h, ((c0) eVar).f5310h, iArrE);
        return new c0(iArrE);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.f.i(this.f5310h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.f.u(this.f5310h);
    }
}
