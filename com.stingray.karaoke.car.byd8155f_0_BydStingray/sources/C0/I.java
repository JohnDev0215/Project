package C0;

import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class I implements r {
    public final E0.S l;

    public I(E0.S s6) {
        this.l = s6;
    }

    @Override // C0.r
    public final long B(long j3) {
        return m0.c.h(this.l.f1582w.B(j3), a());
    }

    @Override // C0.r
    public final boolean D() {
        return this.l.f1582w.Q0().f14695x;
    }

    @Override // C0.r
    public final void E(float[] fArr) {
        this.l.f1582w.E(fArr);
    }

    @Override // C0.r
    public final long L() {
        E0.S s6 = this.l;
        return H7.H.c(s6.l, s6.f871m);
    }

    @Override // C0.r
    public final long N(long j3) {
        return this.l.f1582w.N(m0.c.h(j3, a()));
    }

    public final long a() {
        E0.S s6 = this.l;
        E0.S sI = c0.i(s6);
        return m0.c.g(b(sI.f1585z, 0L), s6.f1582w.Y0(sI.f1582w, 0L));
    }

    public final long b(r rVar, long j3) {
        boolean z6 = rVar instanceof I;
        E0.S s6 = this.l;
        if (!z6) {
            E0.S sI = c0.i(s6);
            long jB = b(sI.f1585z, j3);
            E0.d0 d0Var = sI.f1582w;
            d0Var.getClass();
            return m0.c.h(jB, d0Var.Y0(rVar, 0L));
        }
        E0.S s10 = ((I) rVar).l;
        s10.f1582w.Z0();
        E0.S sO0 = s6.f1582w.M0(s10.f1582w).O0();
        if (sO0 != null) {
            long jC = a1.h.c(a1.h.d(s10.H0(sO0, false), AbstractC2673a.T(j3)), s6.H0(sO0, false));
            return h6.e.a((int) (jC >> 32), (int) (jC & 4294967295L));
        }
        E0.S sI2 = c0.i(s10);
        long jD = a1.h.d(a1.h.d(s10.H0(sI2, false), sI2.f1583x), AbstractC2673a.T(j3));
        E0.S sI3 = c0.i(s6);
        long jC2 = a1.h.c(jD, a1.h.d(s6.H0(sI3, false), sI3.f1583x));
        long jA = h6.e.a((int) (jC2 >> 32), (int) (jC2 & 4294967295L));
        E0.d0 d0Var2 = sI3.f1582w.f1650y;
        A7.m.c(d0Var2);
        E0.d0 d0Var3 = sI2.f1582w.f1650y;
        A7.m.c(d0Var3);
        return d0Var2.Y0(d0Var3, jA);
    }

    @Override // C0.r
    public final long d(long j3) {
        return m0.c.h(this.l.f1582w.d(j3), a());
    }

    @Override // C0.r
    public final void f(r rVar, float[] fArr) {
        this.l.f1582w.f(rVar, fArr);
    }

    @Override // C0.r
    public final long g(long j3) {
        return this.l.f1582w.g(m0.c.h(j3, a()));
    }

    @Override // C0.r
    public final r j() {
        E0.S sO0;
        if (!D()) {
            l9.d.h0("LayoutCoordinate operations are only valid when isAttached is true");
            throw null;
        }
        E0.d0 d0Var = ((E0.d0) this.l.f1582w.f1648w.f1469H.d).f1650y;
        if (d0Var == null || (sO0 = d0Var.O0()) == null) {
            return null;
        }
        return sO0.f1585z;
    }

    @Override // C0.r
    public final long k(r rVar, long j3) {
        return b(rVar, j3);
    }

    @Override // C0.r
    public final m0.d o(r rVar, boolean z6) {
        return this.l.f1582w.o(rVar, z6);
    }
}
