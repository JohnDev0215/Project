package E0;

import java.util.LinkedHashMap;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class S extends Q implements C0.J {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public C0.L f1581A;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final d0 f1582w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public LinkedHashMap f1584y;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f1583x = 0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C0.I f1585z = new C0.I(this);
    public final LinkedHashMap B = new LinkedHashMap();

    public S(d0 d0Var) {
        this.f1582w = d0Var;
    }

    public static final void E0(S s6, C0.L l) {
        m7.q qVar;
        LinkedHashMap linkedHashMap;
        if (l != null) {
            s6.n0(H7.H.c(l.e(), l.c()));
            qVar = m7.q.f19267a;
        } else {
            qVar = null;
        }
        if (qVar == null) {
            s6.n0(0L);
        }
        if (!A7.m.a(s6.f1581A, l) && l != null && ((((linkedHashMap = s6.f1584y) != null && !linkedHashMap.isEmpty()) || !l.a().isEmpty()) && !A7.m.a(l.a(), s6.f1584y))) {
            K k10 = s6.f1582w.f1648w.t().f1569s;
            A7.m.c(k10);
            k10.f1507C.f();
            LinkedHashMap linkedHashMap2 = s6.f1584y;
            if (linkedHashMap2 == null) {
                linkedHashMap2 = new LinkedHashMap();
                s6.f1584y = linkedHashMap2;
            }
            linkedHashMap2.clear();
            linkedHashMap2.putAll(l.a());
        }
        s6.f1581A = l;
    }

    @Override // E0.Q
    public final Q A0() {
        d0 d0Var = this.f1582w.f1650y;
        if (d0Var != null) {
            return d0Var.O0();
        }
        return null;
    }

    @Override // E0.Q
    public final long B0() {
        return this.f1583x;
    }

    @Override // E0.Q
    public final void D0() {
        l0(this.f1583x, 0.0f, null);
    }

    public void F0() {
        z0().b();
    }

    public final void G0(long j3) {
        if (!a1.h.b(this.f1583x, j3)) {
            this.f1583x = j3;
            d0 d0Var = this.f1582w;
            K k10 = d0Var.f1648w.t().f1569s;
            if (k10 != null) {
                k10.v0();
            }
            Q.C0(d0Var);
        }
        if (this.f1577s) {
            return;
        }
        t0(new m0(z0(), this));
    }

    public final long H0(S s6, boolean z6) {
        long jD = 0;
        while (!this.equals(s6)) {
            if (!this.f1575q || !z6) {
                jD = a1.h.d(jD, this.f1583x);
            }
            d0 d0Var = this.f1582w.f1650y;
            A7.m.c(d0Var);
            this = d0Var.O0();
            A7.m.c(this);
        }
        return jD;
    }

    @Override // a1.b
    public final float getDensity() {
        return this.f1582w.getDensity();
    }

    @Override // C0.InterfaceC0108o
    public final a1.k getLayoutDirection() {
        return this.f1582w.f1648w.f1465D;
    }

    @Override // C0.V, C0.J
    public final Object i() {
        return this.f1582w.i();
    }

    @Override // C0.V
    public final void l0(long j3, float f10, InterfaceC2782k interfaceC2782k) {
        G0(j3);
        if (this.f1576r) {
            return;
        }
        F0();
    }

    @Override // a1.b
    public final float p() {
        return this.f1582w.p();
    }

    @Override // E0.Q, C0.InterfaceC0108o
    public final boolean v() {
        return true;
    }

    @Override // E0.Q
    public final Q v0() {
        d0 d0Var = this.f1582w.f1649x;
        if (d0Var != null) {
            return d0Var.O0();
        }
        return null;
    }

    @Override // E0.Q
    public final C0.r w0() {
        return this.f1585z;
    }

    @Override // E0.Q
    public final boolean x0() {
        return this.f1581A != null;
    }

    @Override // E0.Q
    public final F y0() {
        return this.f1582w.f1648w;
    }

    @Override // E0.Q
    public final C0.L z0() {
        C0.L l = this.f1581A;
        if (l != null) {
            return l;
        }
        throw new IllegalStateException("LookaheadDelegate has not been measured yet when measureResult is requested.");
    }
}
