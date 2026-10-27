package y;

import T.C0735d;
import T.C0738e0;
import android.content.Context;
import android.os.Build;
import android.widget.EdgeEffect;
import n0.AbstractC1861K;
import y0.C2738p;

/* JADX INFO: renamed from: y.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2709n implements r0 {
    public m0.c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2687H f23967m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0738e0 f23968n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f23969p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f23970q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C2738p f23971r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final g0.o f23972s;

    public C2709n(Context context, p0 p0Var) {
        C2687H c2687h = new C2687H(context, AbstractC1861K.E(p0Var.f23985a));
        this.f23967m = c2687h;
        m7.q qVar = m7.q.f19267a;
        this.f23968n = C0735d.O(qVar, T.S.f8222n);
        this.o = true;
        this.f23970q = 0L;
        this.f23972s = y0.v.a(g0.l.f14684a, qVar, new C2708m(this, null)).k(Build.VERSION.SDK_INT >= 31 ? new C2686G(this, c2687h) : new C2686G(this, c2687h, p0Var));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x014e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0152  */
    @Override // y.r0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long a(long r18, int r20, A.F r21) {
        /*
            Method dump skipped, instruction units count: 614
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y.C2709n.a(long, int, A.F):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ca  */
    @Override // y.r0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(long r20, A.Q0 r22, q7.InterfaceC2189c r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 541
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y.C2709n.b(long, A.Q0, q7.c):java.lang.Object");
    }

    @Override // y.r0
    public final boolean c() {
        C2687H c2687h = this.f23967m;
        EdgeEffect edgeEffect = c2687h.d;
        C2710o c2710o = C2710o.f23975a;
        if (edgeEffect != null) {
            if ((Build.VERSION.SDK_INT >= 31 ? c2710o.b(edgeEffect) : 0.0f) != 0.0f) {
                return true;
            }
        }
        EdgeEffect edgeEffect2 = c2687h.f23840e;
        if (edgeEffect2 != null) {
            if ((Build.VERSION.SDK_INT >= 31 ? c2710o.b(edgeEffect2) : 0.0f) != 0.0f) {
                return true;
            }
        }
        EdgeEffect edgeEffect3 = c2687h.f23841f;
        if (edgeEffect3 != null) {
            if ((Build.VERSION.SDK_INT >= 31 ? c2710o.b(edgeEffect3) : 0.0f) != 0.0f) {
                return true;
            }
        }
        EdgeEffect edgeEffect4 = c2687h.f23842g;
        if (edgeEffect4 != null) {
            return (Build.VERSION.SDK_INT >= 31 ? c2710o.b(edgeEffect4) : 0.0f) != 0.0f;
        }
        return false;
    }

    @Override // y.r0
    public final g0.o d() {
        return this.f23972s;
    }

    public final void e() {
        boolean zIsFinished;
        C2687H c2687h = this.f23967m;
        EdgeEffect edgeEffect = c2687h.d;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = edgeEffect.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = c2687h.f23840e;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished = edgeEffect2.isFinished() || zIsFinished;
        }
        EdgeEffect edgeEffect3 = c2687h.f23841f;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished = edgeEffect3.isFinished() || zIsFinished;
        }
        EdgeEffect edgeEffect4 = c2687h.f23842g;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished = edgeEffect4.isFinished() || zIsFinished;
        }
        if (zIsFinished) {
            g();
        }
    }

    public final long f() {
        m0.c cVar = this.l;
        long jK = cVar != null ? cVar.f18880a : h6.j.k(this.f23970q);
        return h6.e.a(m0.c.d(jK) / m0.f.d(this.f23970q), m0.c.e(jK) / m0.f.b(this.f23970q));
    }

    public final void g() {
        if (this.o) {
            this.f23968n.setValue(m7.q.f19267a);
        }
    }

    public final float h(long j3) {
        float fD = m0.c.d(f());
        float fE = m0.c.e(j3) / m0.f.b(this.f23970q);
        EdgeEffect edgeEffectB = this.f23967m.b();
        float fC = -fE;
        float f10 = 1 - fD;
        int i7 = Build.VERSION.SDK_INT;
        C2710o c2710o = C2710o.f23975a;
        if (i7 >= 31) {
            fC = c2710o.c(edgeEffectB, fC, f10);
        } else {
            edgeEffectB.onPull(fC, f10);
        }
        return (i7 >= 31 ? c2710o.b(edgeEffectB) : 0.0f) == 0.0f ? m0.f.b(this.f23970q) * (-fC) : m0.c.e(j3);
    }

    public final float i(long j3) {
        float fE = m0.c.e(f());
        float fD = m0.c.d(j3) / m0.f.d(this.f23970q);
        EdgeEffect edgeEffectC = this.f23967m.c();
        float f10 = 1 - fE;
        int i7 = Build.VERSION.SDK_INT;
        C2710o c2710o = C2710o.f23975a;
        if (i7 >= 31) {
            fD = c2710o.c(edgeEffectC, fD, f10);
        } else {
            edgeEffectC.onPull(fD, f10);
        }
        return (i7 >= 31 ? c2710o.b(edgeEffectC) : 0.0f) == 0.0f ? m0.f.d(this.f23970q) * fD : m0.c.d(j3);
    }

    public final float j(long j3) {
        float fE = m0.c.e(f());
        float fD = m0.c.d(j3) / m0.f.d(this.f23970q);
        EdgeEffect edgeEffectD = this.f23967m.d();
        float fC = -fD;
        int i7 = Build.VERSION.SDK_INT;
        C2710o c2710o = C2710o.f23975a;
        if (i7 >= 31) {
            fC = c2710o.c(edgeEffectD, fC, fE);
        } else {
            edgeEffectD.onPull(fC, fE);
        }
        return (i7 >= 31 ? c2710o.b(edgeEffectD) : 0.0f) == 0.0f ? m0.f.d(this.f23970q) * (-fC) : m0.c.d(j3);
    }

    public final float k(long j3) {
        float fD = m0.c.d(f());
        float fE = m0.c.e(j3) / m0.f.b(this.f23970q);
        EdgeEffect edgeEffectE = this.f23967m.e();
        int i7 = Build.VERSION.SDK_INT;
        C2710o c2710o = C2710o.f23975a;
        if (i7 >= 31) {
            fE = c2710o.c(edgeEffectE, fE, fD);
        } else {
            edgeEffectE.onPull(fE, fD);
        }
        return (i7 >= 31 ? c2710o.b(edgeEffectE) : 0.0f) == 0.0f ? m0.f.b(this.f23970q) * fE : m0.c.e(j3);
    }

    public final void l(long j3) {
        boolean zA = m0.f.a(this.f23970q, 0L);
        boolean zA2 = m0.f.a(j3, this.f23970q);
        this.f23970q = j3;
        if (!zA2) {
            long jC = H7.H.c(C7.a.W(m0.f.d(j3)), C7.a.W(m0.f.b(j3)));
            C2687H c2687h = this.f23967m;
            c2687h.c = jC;
            EdgeEffect edgeEffect = c2687h.d;
            if (edgeEffect != null) {
                edgeEffect.setSize((int) (jC >> 32), (int) (jC & 4294967295L));
            }
            EdgeEffect edgeEffect2 = c2687h.f23840e;
            if (edgeEffect2 != null) {
                edgeEffect2.setSize((int) (jC >> 32), (int) (jC & 4294967295L));
            }
            EdgeEffect edgeEffect3 = c2687h.f23841f;
            if (edgeEffect3 != null) {
                edgeEffect3.setSize((int) (jC & 4294967295L), (int) (jC >> 32));
            }
            EdgeEffect edgeEffect4 = c2687h.f23842g;
            if (edgeEffect4 != null) {
                edgeEffect4.setSize((int) (jC & 4294967295L), (int) (jC >> 32));
            }
            EdgeEffect edgeEffect5 = c2687h.f23843h;
            if (edgeEffect5 != null) {
                edgeEffect5.setSize((int) (jC >> 32), (int) (jC & 4294967295L));
            }
            EdgeEffect edgeEffect6 = c2687h.f23844i;
            if (edgeEffect6 != null) {
                edgeEffect6.setSize((int) (jC >> 32), (int) (jC & 4294967295L));
            }
            EdgeEffect edgeEffect7 = c2687h.f23845j;
            if (edgeEffect7 != null) {
                edgeEffect7.setSize((int) (jC & 4294967295L), (int) (jC >> 32));
            }
            EdgeEffect edgeEffect8 = c2687h.f23846k;
            if (edgeEffect8 != null) {
                edgeEffect8.setSize((int) (jC & 4294967295L), (int) (jC >> 32));
            }
        }
        if (zA || zA2) {
            return;
        }
        g();
        e();
    }
}
