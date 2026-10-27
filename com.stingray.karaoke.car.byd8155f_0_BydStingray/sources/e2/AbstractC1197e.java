package e2;

import android.os.Looper;
import android.os.SystemClock;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import g2.C1290c;
import h2.AbstractC1343A;
import java.util.List;
import n2.C1923x;

/* JADX INFO: renamed from: e2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1197e implements W {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f14069b;

    public AbstractC1197e() {
        this.f14068a = 0;
        this.f14069b = new e0();
    }

    @Override // e2.W
    public S A() {
        return ((W) this.f14069b).A();
    }

    @Override // e2.W
    public void A0(int i7, int i10) {
        ((W) this.f14069b).A0(i7, i10);
    }

    @Override // e2.W
    public long B() {
        return ((W) this.f14069b).B();
    }

    @Override // e2.W
    public void B0(int i7) {
        ((W) this.f14069b).B0(i7);
    }

    @Override // e2.W
    public boolean C() {
        return ((W) this.f14069b).C();
    }

    @Override // e2.W
    public void C0() {
        int iE;
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                if (c1923x.t0().p() || c1923x.s()) {
                    v();
                } else if (M()) {
                    f0 f0VarT0 = c1923x.t0();
                    if (f0VarT0.p()) {
                        iE = -1;
                    } else {
                        int iB0 = c1923x.b0();
                        c1923x.A1();
                        int i7 = c1923x.f19732G;
                        if (i7 == 1) {
                            i7 = 0;
                        }
                        c1923x.A1();
                        iE = f0VarT0.e(iB0, i7, c1923x.f19733H);
                    }
                    if (iE == -1) {
                        v();
                    } else if (iE != c1923x.b0()) {
                        W0(iE, -9223372036854775807L, false);
                    } else {
                        W0(c1923x.b0(), -9223372036854775807L, true);
                    }
                } else if (V0() && S0()) {
                    W0(c1923x.b0(), -9223372036854775807L, false);
                } else {
                    v();
                }
                break;
            default:
                ((W) this.f14069b).C0();
                break;
        }
    }

    @Override // e2.W
    public void D() {
        switch (this.f14068a) {
            case 0:
                ((C1923x) this).A0(0, Integer.MAX_VALUE);
                break;
            default:
                ((W) this.f14069b).D();
                break;
        }
    }

    @Override // e2.W
    public void D0(U u4) {
        ((W) this.f14069b).D0(new C1209q(this, u4));
    }

    @Override // e2.W
    public void E(F f10, long j3) {
        switch (this.f14068a) {
            case 0:
                ((C1923x) this).m(d5.I.B(f10), 0, j3);
                break;
            default:
                ((W) this.f14069b).E(f10, j3);
                break;
        }
    }

    @Override // e2.W
    public void E0() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                c1923x.A1();
                X0(c1923x.f19780w, 12);
                break;
            default:
                ((W) this.f14069b).E0();
                break;
        }
    }

    @Override // e2.W
    public void F(boolean z6) {
        ((W) this.f14069b).F(z6);
    }

    @Override // e2.W
    public void F0(TextureView textureView) {
        ((W) this.f14069b).F0(textureView);
    }

    @Override // e2.W
    public void G() {
        int iE;
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                f0 f0VarT0 = c1923x.t0();
                if (f0VarT0.p()) {
                    iE = -1;
                } else {
                    int iB0 = c1923x.b0();
                    c1923x.A1();
                    int i7 = c1923x.f19732G;
                    if (i7 == 1) {
                        i7 = 0;
                    }
                    c1923x.A1();
                    iE = f0VarT0.e(iB0, i7, c1923x.f19733H);
                }
                if (iE == -1) {
                    v();
                } else if (iE != c1923x.b0()) {
                    W0(iE, -9223372036854775807L, false);
                } else {
                    W0(c1923x.b0(), -9223372036854775807L, true);
                }
                break;
            default:
                ((W) this.f14069b).G();
                break;
        }
    }

    @Override // e2.W
    public void G0(I i7) {
        ((W) this.f14069b).G0(i7);
    }

    @Override // e2.W
    public void H(U u4) {
        ((W) this.f14069b).H(new C1209q(this, u4));
    }

    @Override // e2.W
    public void H0() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                c1923x.A1();
                X0(-c1923x.f19779v, 11);
                break;
            default:
                ((W) this.f14069b).H0();
                break;
        }
    }

    @Override // e2.W
    public void I(int i7) {
        ((W) this.f14069b).I(i7);
    }

    @Override // e2.W
    public void I0(long j3, int i7) {
        switch (this.f14068a) {
            case 0:
                W0(i7, j3, false);
                break;
            default:
                ((W) this.f14069b).I0(j3, i7);
                break;
        }
    }

    @Override // e2.W
    public n0 J() {
        return ((W) this.f14069b).J();
    }

    @Override // e2.W
    public void J0(float f10) {
        ((W) this.f14069b).J0(f10);
    }

    @Override // e2.W
    public int K() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                long jB = c1923x.B();
                long jS0 = c1923x.s0();
                if (jB == -9223372036854775807L || jS0 == -9223372036854775807L) {
                    return 0;
                }
                if (jS0 == 0) {
                    return 100;
                }
                return AbstractC1343A.i((int) ((jB * 100) / jS0), 0, 100);
            default:
                return ((W) this.f14069b).K();
        }
    }

    @Override // e2.W
    public I K0() {
        return ((W) this.f14069b).K0();
    }

    @Override // e2.W
    public long L() {
        return ((W) this.f14069b).L();
    }

    @Override // e2.W
    public void L0(List list) {
        ((W) this.f14069b).L0(list);
    }

    @Override // e2.W
    public boolean M() {
        int iE;
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                f0 f0VarT0 = c1923x.t0();
                if (f0VarT0.p()) {
                    iE = -1;
                } else {
                    int iB0 = c1923x.b0();
                    c1923x.A1();
                    int i7 = c1923x.f19732G;
                    if (i7 == 1) {
                        i7 = 0;
                    }
                    c1923x.A1();
                    iE = f0VarT0.e(iB0, i7, c1923x.f19733H);
                }
                return iE != -1;
            default:
                return ((W) this.f14069b).M();
        }
    }

    @Override // e2.W
    public void M0() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                if (c1923x.t0().p() || c1923x.s()) {
                    v();
                } else {
                    boolean zI0 = i0();
                    if (!V0() || P0()) {
                        if (zI0) {
                            long jN0 = c1923x.N0();
                            c1923x.A1();
                            if (jN0 <= c1923x.f19781x) {
                                Y0(7);
                            }
                        }
                        W0(c1923x.b0(), 0L, false);
                    } else if (!zI0) {
                        v();
                    } else {
                        Y0(7);
                    }
                }
                break;
            default:
                ((W) this.f14069b).M0();
                break;
        }
    }

    @Override // e2.W
    public I N() {
        return ((W) this.f14069b).N();
    }

    @Override // e2.W
    public long N0() {
        return ((W) this.f14069b).N0();
    }

    @Override // e2.W
    public boolean O() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                return c1923x.e() == 3 && c1923x.C() && c1923x.p0() == 0;
            default:
                return ((W) this.f14069b).O();
        }
    }

    @Override // e2.W
    public long O0() {
        return ((W) this.f14069b).O0();
    }

    @Override // e2.W
    public long P() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                f0 f0VarT0 = c1923x.t0();
                if (f0VarT0.p()) {
                    return -9223372036854775807L;
                }
                return AbstractC1343A.T(f0VarT0.m(c1923x.b0(), (e0) this.f14069b, 0L).f14094m);
            default:
                return ((W) this.f14069b).P();
        }
    }

    @Override // e2.W
    public boolean P0() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                f0 f0VarT0 = c1923x.t0();
                return !f0VarT0.p() && f0VarT0.m(c1923x.b0(), (e0) this.f14069b, 0L).f14090h;
            default:
                return ((W) this.f14069b).P0();
        }
    }

    @Override // e2.W
    public int Q() {
        return ((W) this.f14069b).Q();
    }

    @Override // e2.W
    public F Q0() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                f0 f0VarT0 = c1923x.t0();
                if (f0VarT0.p()) {
                    return null;
                }
                return f0VarT0.m(c1923x.b0(), (e0) this.f14069b, 0L).c;
            default:
                return ((W) this.f14069b).Q0();
        }
    }

    @Override // e2.W
    public C1290c R() {
        return ((W) this.f14069b).R();
    }

    @Override // e2.W
    public boolean R0(int i7) {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                c1923x.A1();
                return c1923x.f19739N.a(i7);
            default:
                return ((W) this.f14069b).R0(i7);
        }
    }

    @Override // e2.W
    public void S(TextureView textureView) {
        ((W) this.f14069b).S(textureView);
    }

    @Override // e2.W
    public boolean S0() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                f0 f0VarT0 = c1923x.t0();
                return !f0VarT0.p() && f0VarT0.m(c1923x.b0(), (e0) this.f14069b, 0L).f14091i;
            default:
                return ((W) this.f14069b).S0();
        }
    }

    @Override // e2.W
    public q0 T() {
        return ((W) this.f14069b).T();
    }

    @Override // e2.W
    public Looper T0() {
        return ((W) this.f14069b).T0();
    }

    @Override // e2.W
    public void U(C1195c c1195c, boolean z6) {
        ((W) this.f14069b).U(c1195c, z6);
    }

    @Override // e2.W
    public final boolean U0() {
        switch (this.f14068a) {
            case 0:
                return true;
            default:
                return ((W) this.f14069b).U0();
        }
    }

    @Override // e2.W
    public void V(F f10) {
        switch (this.f14068a) {
            case 0:
                ((C1923x) this).L0(d5.I.B(f10));
                break;
            default:
                ((W) this.f14069b).V(f10);
                break;
        }
    }

    @Override // e2.W
    public boolean V0() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                f0 f0VarT0 = c1923x.t0();
                return !f0VarT0.p() && f0VarT0.m(c1923x.b0(), (e0) this.f14069b, 0L).a();
            default:
                return ((W) this.f14069b).V0();
        }
    }

    @Override // e2.W
    public void W() {
        switch (this.f14068a) {
            case 0:
                Y0(6);
                break;
            default:
                ((W) this.f14069b).W();
                break;
        }
    }

    public abstract void W0(int i7, long j3, boolean z6);

    @Override // e2.W
    public float X() {
        return ((W) this.f14069b).X();
    }

    public void X0(long j3, int i7) {
        C1923x c1923x = (C1923x) this;
        long jN0 = c1923x.N0() + j3;
        long jS0 = c1923x.s0();
        if (jS0 != -9223372036854775807L) {
            jN0 = Math.min(jN0, jS0);
        }
        W0(c1923x.b0(), Math.max(jN0, 0L), false);
    }

    @Override // e2.W
    public void Y() {
        switch (this.f14068a) {
            case 0:
                W0(((C1923x) this).b0(), -9223372036854775807L, false);
                break;
            default:
                ((W) this.f14069b).Y();
                break;
        }
    }

    public void Y0(int i7) {
        int iK;
        C1923x c1923x = (C1923x) this;
        f0 f0VarT0 = c1923x.t0();
        if (f0VarT0.p()) {
            iK = -1;
        } else {
            int iB0 = c1923x.b0();
            c1923x.A1();
            int i10 = c1923x.f19732G;
            if (i10 == 1) {
                i10 = 0;
            }
            c1923x.A1();
            iK = f0VarT0.k(iB0, i10, c1923x.f19733H);
        }
        if (iK == -1) {
            v();
        } else if (iK == c1923x.b0()) {
            W0(c1923x.b0(), -9223372036854775807L, true);
        } else {
            W0(iK, -9223372036854775807L, false);
        }
    }

    @Override // e2.W
    public C1195c Z() {
        return ((W) this.f14069b).Z();
    }

    @Override // e2.W
    public void a() {
        ((W) this.f14069b).a();
    }

    @Override // e2.W
    public int a0() {
        return ((W) this.f14069b).a0();
    }

    @Override // e2.W
    public void b() {
        switch (this.f14068a) {
            case 0:
                ((C1923x) this).q(false);
                break;
            default:
                ((W) this.f14069b).b();
                break;
        }
    }

    @Override // e2.W
    public int b0() {
        return ((W) this.f14069b).b0();
    }

    @Override // e2.W
    public void c() {
        ((W) this.f14069b).c();
    }

    @Override // e2.W
    public void c0(int i7, boolean z6) {
        ((W) this.f14069b).c0(i7, z6);
    }

    @Override // e2.W
    public boolean d() {
        return ((W) this.f14069b).d();
    }

    @Override // e2.W
    public C1202j d0() {
        return ((W) this.f14069b).d0();
    }

    @Override // e2.W
    public int e() {
        return ((W) this.f14069b).e();
    }

    @Override // e2.W
    public void e0() {
        ((W) this.f14069b).e0();
    }

    @Override // e2.W
    public void f(P p10) {
        ((W) this.f14069b).f(p10);
    }

    @Override // e2.W
    public void f0(l0 l0Var) {
        ((W) this.f14069b).f0(l0Var);
    }

    @Override // e2.W
    public void g() {
        switch (this.f14068a) {
            case 0:
                ((C1923x) this).q(true);
                break;
            default:
                ((W) this.f14069b).g();
                break;
        }
    }

    @Override // e2.W
    public void g0(int i7, int i10) {
        ((W) this.f14069b).g0(i7, i10);
    }

    @Override // e2.W
    public void h(int i7) {
        ((W) this.f14069b).h(i7);
    }

    @Override // e2.W
    public void h0(boolean z6) {
        ((W) this.f14069b).h0(z6);
    }

    @Override // e2.W
    public int i() {
        return ((W) this.f14069b).i();
    }

    @Override // e2.W
    public boolean i0() {
        int iK;
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                f0 f0VarT0 = c1923x.t0();
                if (f0VarT0.p()) {
                    iK = -1;
                } else {
                    int iB0 = c1923x.b0();
                    c1923x.A1();
                    int i7 = c1923x.f19732G;
                    if (i7 == 1) {
                        i7 = 0;
                    }
                    c1923x.A1();
                    iK = f0VarT0.k(iB0, i7, c1923x.f19733H);
                }
                return iK != -1;
            default:
                return ((W) this.f14069b).i0();
        }
    }

    @Override // e2.W
    public P j() {
        return ((W) this.f14069b).j();
    }

    @Override // e2.W
    public void j0(int i7) {
        ((W) this.f14069b).j0(i7);
    }

    @Override // e2.W
    public void k(long j3) {
        switch (this.f14068a) {
            case 0:
                W0(((C1923x) this).b0(), j3, false);
                break;
            default:
                ((W) this.f14069b).k(j3);
                break;
        }
    }

    @Override // e2.W
    public int k0() {
        return ((W) this.f14069b).k0();
    }

    @Override // e2.W
    public void l(float f10) {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                c1923x.f(new P(f10, c1923x.j().f13996b));
                break;
            default:
                ((W) this.f14069b).l(f10);
                break;
        }
    }

    @Override // e2.W
    public void l0(SurfaceView surfaceView) {
        ((W) this.f14069b).l0(surfaceView);
    }

    @Override // e2.W
    public void m(List list, int i7, long j3) {
        ((W) this.f14069b).m(list, i7, j3);
    }

    @Override // e2.W
    public void m0(SurfaceView surfaceView) {
        ((W) this.f14069b).m0(surfaceView);
    }

    @Override // e2.W
    public O n() {
        return ((W) this.f14069b).n();
    }

    @Override // e2.W
    public void n0(int i7, int i10) {
        switch (this.f14068a) {
            case 0:
                if (i7 != i10) {
                    ((C1923x) this).o0(i7, i7 + 1, i10);
                }
                break;
            default:
                ((W) this.f14069b).n0(i7, i10);
                break;
        }
    }

    @Override // e2.W
    public int o() {
        return ((W) this.f14069b).o();
    }

    @Override // e2.W
    public void o0(int i7, int i10, int i11) {
        ((W) this.f14069b).o0(i7, i10, i11);
    }

    @Override // e2.W
    public void p(int i7, F f10) {
        switch (this.f14068a) {
            case 0:
                ((C1923x) this).q0(i7, i7 + 1, d5.I.B(f10));
                break;
            default:
                ((W) this.f14069b).p(i7, f10);
                break;
        }
    }

    @Override // e2.W
    public int p0() {
        return ((W) this.f14069b).p0();
    }

    @Override // e2.W
    public void q(boolean z6) {
        ((W) this.f14069b).q(z6);
    }

    @Override // e2.W
    public void q0(int i7, int i10, List list) {
        ((W) this.f14069b).q0(i7, i10, list);
    }

    @Override // e2.W
    public void r(Surface surface) {
        ((W) this.f14069b).r(surface);
    }

    @Override // e2.W
    public void r0(List list) {
        switch (this.f14068a) {
            case 0:
                ((C1923x) this).z(Integer.MAX_VALUE, list);
                break;
            default:
                ((W) this.f14069b).r0(list);
                break;
        }
    }

    @Override // e2.W
    public boolean s() {
        return ((W) this.f14069b).s();
    }

    @Override // e2.W
    public long s0() {
        return ((W) this.f14069b).s0();
    }

    @Override // e2.W
    public void stop() {
        ((W) this.f14069b).stop();
    }

    @Override // e2.W
    public void t(int i7) {
        switch (this.f14068a) {
            case 0:
                W0(i7, -9223372036854775807L, false);
                break;
            default:
                ((W) this.f14069b).t(i7);
                break;
        }
    }

    @Override // e2.W
    public f0 t0() {
        return ((W) this.f14069b).t0();
    }

    @Override // e2.W
    public long u() {
        return ((W) this.f14069b).u();
    }

    @Override // e2.W
    public boolean u0() {
        return ((W) this.f14069b).u0();
    }

    public void v() {
        ((C1923x) this).A1();
    }

    @Override // e2.W
    public void v0(int i7) {
        switch (this.f14068a) {
            case 0:
                ((C1923x) this).A0(i7, i7 + 1);
                break;
            default:
                ((W) this.f14069b).v0(i7);
                break;
        }
    }

    @Override // e2.W
    public long w() {
        switch (this.f14068a) {
            case 0:
                C1923x c1923x = (C1923x) this;
                f0 f0VarT0 = c1923x.t0();
                if (f0VarT0.p()) {
                    return -9223372036854775807L;
                }
                int iB0 = c1923x.b0();
                e0 e0Var = (e0) this.f14069b;
                if (f0VarT0.m(iB0, e0Var, 0L).f14088f == -9223372036854775807L) {
                    return -9223372036854775807L;
                }
                long j3 = e0Var.f14089g;
                return ((j3 == -9223372036854775807L ? System.currentTimeMillis() : j3 + SystemClock.elapsedRealtime()) - e0Var.f14088f) - c1923x.x();
            default:
                return ((W) this.f14069b).w();
        }
    }

    @Override // e2.W
    public void w0() {
        ((W) this.f14069b).w0();
    }

    @Override // e2.W
    public long x() {
        return ((W) this.f14069b).x();
    }

    @Override // e2.W
    public boolean x0() {
        return ((W) this.f14069b).x0();
    }

    @Override // e2.W
    public long y() {
        return ((W) this.f14069b).y();
    }

    @Override // e2.W
    public l0 y0() {
        return ((W) this.f14069b).y0();
    }

    @Override // e2.W
    public void z(int i7, List list) {
        ((W) this.f14069b).z(i7, list);
    }

    @Override // e2.W
    public long z0() {
        return ((W) this.f14069b).z0();
    }

    public AbstractC1197e(W w10) {
        this.f14068a = 1;
        this.f14069b = w10;
    }
}
