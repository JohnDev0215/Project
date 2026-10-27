package g.a.d.b.a0.c;

import g.a.d.b.e;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class s0 extends e.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5418g = q0.f5404i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f5419h;

    public s0() {
        this.f5419h = g.a.d.d.m.j(17);
    }

    public s0(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f5418g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP521R1FieldElement");
        }
        this.f5419h = r0.c(bigInteger);
    }

    public s0(int[] iArr) {
        this.f5419h = iArr;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e a(g.a.d.b.e eVar) {
        int[] iArrJ = g.a.d.d.m.j(17);
        r0.a(this.f5419h, ((s0) eVar).f5419h, iArrJ);
        return new s0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e b() {
        int[] iArrJ = g.a.d.d.m.j(17);
        r0.b(this.f5419h, iArrJ);
        return new s0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e d(g.a.d.b.e eVar) {
        int[] iArrJ = g.a.d.d.m.j(17);
        g.a.d.d.b.d(r0.f5412a, ((s0) eVar).f5419h, iArrJ);
        r0.f(iArrJ, this.f5419h, iArrJ);
        return new s0(iArrJ);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof s0) {
            return g.a.d.d.m.n(17, this.f5419h, ((s0) obj).f5419h);
        }
        return false;
    }

    @Override // g.a.d.b.e
    public int f() {
        return f5418g.bitLength();
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e g() {
        int[] iArrJ = g.a.d.d.m.j(17);
        g.a.d.d.b.d(r0.f5412a, this.f5419h, iArrJ);
        return new s0(iArrJ);
    }

    @Override // g.a.d.b.e
    public boolean h() {
        return g.a.d.d.m.v(17, this.f5419h);
    }

    public int hashCode() {
        return f5418g.hashCode() ^ g.a.f.a.C(this.f5419h, 0, 17);
    }

    @Override // g.a.d.b.e
    public boolean i() {
        return g.a.d.d.m.w(17, this.f5419h);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e j(g.a.d.b.e eVar) {
        int[] iArrJ = g.a.d.d.m.j(17);
        r0.f(this.f5419h, ((s0) eVar).f5419h, iArrJ);
        return new s0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e m() {
        int[] iArrJ = g.a.d.d.m.j(17);
        r0.g(this.f5419h, iArrJ);
        return new s0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e n() {
        int[] iArr = this.f5419h;
        if (g.a.d.d.m.w(17, iArr) || g.a.d.d.m.v(17, iArr)) {
            return this;
        }
        int[] iArrJ = g.a.d.d.m.j(17);
        int[] iArrJ2 = g.a.d.d.m.j(17);
        r0.k(iArr, 519, iArrJ);
        r0.j(iArrJ, iArrJ2);
        if (g.a.d.d.m.n(17, iArr, iArrJ2)) {
            return new s0(iArrJ);
        }
        return null;
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e o() {
        int[] iArrJ = g.a.d.d.m.j(17);
        r0.j(this.f5419h, iArrJ);
        return new s0(iArrJ);
    }

    @Override // g.a.d.b.e
    public g.a.d.b.e r(g.a.d.b.e eVar) {
        int[] iArrJ = g.a.d.d.m.j(17);
        r0.l(this.f5419h, ((s0) eVar).f5419h, iArrJ);
        return new s0(iArrJ);
    }

    @Override // g.a.d.b.e
    public boolean s() {
        return g.a.d.d.m.p(this.f5419h, 0) == 1;
    }

    @Override // g.a.d.b.e
    public BigInteger t() {
        return g.a.d.d.m.P(17, this.f5419h);
    }
}
