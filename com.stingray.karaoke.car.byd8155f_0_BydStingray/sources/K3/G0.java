package k3;

import android.os.RemoteException;
import e2.C1195c;
import e2.C1202j;
import g2.C1290c;
import h2.AbstractC1346c;
import h3.C1350a;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class G0 implements e2.U {
    public final WeakReference l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final WeakReference f17780m;

    public G0(I0 i02, u1 u1Var) {
        this.l = new WeakReference(i02);
        this.f17780m = new WeakReference(u1Var);
    }

    @Override // e2.U
    public final void A(C1202j c1202j) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(q1Var.f18071a, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, c1202j, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, q1Var.f18092z, q1Var.f18067A, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.o();
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void D(C1195c c1195c) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.a(c1195c);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.n(c1195c);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void F(e2.I i7) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(q1Var.f18071a, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, i7, q1Var.f18067A, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.v();
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void G(e2.P p10) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.e(p10);
        i0A.c.a(true, true);
        try {
            Q0 q02 = (Q0) i0A.f17801h.f17845h.f15752p;
            q02.L(q02.f17843f.f17811t);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void I(boolean z6) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.k(z6);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.t(z6);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void J(e2.n0 n0Var) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.b(n0Var);
        i0A.c.a(true, false);
        i0A.c(new C1350a(23));
    }

    @Override // e2.U
    public final void L(int i7, e2.F f10) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(q1Var.f18071a, i7, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, q1Var.f18092z, q1Var.f18067A, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.p(f10);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void M(e2.q0 q0Var) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(q1Var.f18071a, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q0Var, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, q1Var.f18092z, q1Var.f18067A, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.getClass();
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void N(e2.f0 f0Var, int i7) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        u1 u1Var = (u1) this.f17780m.get();
        if (u1Var == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.n(f0Var, u1Var.c1(), i7);
        i0A.c.a(false, true);
        try {
            i0A.f17801h.f17845h.u(f0Var);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void Q(e2.S s6) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        i0A.e(s6);
    }

    @Override // e2.U
    public final void R(C1290c c1290c) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.O o = q1Var.f18071a;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(o, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, c1290c, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, q1Var.f18092z, q1Var.f18067A, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
    }

    @Override // e2.U
    public final void S(int i7, boolean z6) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.c(i7, z6);
        i0A.c.a(true, true);
        try {
            s1 s1Var = ((Q0) i0A.f17801h.f17845h.f15752p).f17849m;
            if (s1Var != null) {
                if (z6) {
                    i7 = 0;
                }
                s1Var.d = i7;
                s1Var.a().setCurrentVolume(i7);
            }
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void T(boolean z6) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(q1Var.f18071a, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, z6, q1Var.f18089w, q1Var.f18092z, q1Var.f18067A, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
        try {
            Q0 q02 = (Q0) i0A.f17801h.f17845h.f15752p;
            q02.L(q02.f17843f.f17811t);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
        i0A.s();
    }

    public final I0 a() {
        return (I0) this.l.get();
    }

    @Override // e2.U
    public final void d(int i7) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.i(i7);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.s(i7);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void e(int i7) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        i0A.f17810s = q1Var.d(q1Var.f18087u, i7, q1Var.f18086t);
        i0A.c.a(true, true);
        try {
            Q0 q02 = (Q0) i0A.f17801h.f17845h.f15752p;
            q02.L(q02.f17843f.f17811t);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void f(e2.O o) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(o, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, q1Var.f18092z, q1Var.f18067A, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
        try {
            Q0 q02 = (Q0) i0A.f17801h.f17845h.f15752p;
            q02.L(q02.f17843f.f17811t);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void g(long j3) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(q1Var.f18071a, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, q1Var.f18092z, j3, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.getClass();
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void j(long j3) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(q1Var.f18071a, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, q1Var.f18092z, q1Var.f18067A, j3, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.getClass();
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void k(boolean z6) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(q1Var.f18071a, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, z6, q1Var.f18092z, q1Var.f18067A, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.getClass();
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
        i0A.s();
    }

    @Override // e2.U
    public final void l() {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        i0A.c(new C1350a(25));
    }

    @Override // e2.U
    public final void q(int i7, boolean z6) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        i0A.f17810s = q1Var.d(i7, q1Var.f18090x, z6);
        i0A.c.a(true, true);
        try {
            Q0 q02 = (Q0) i0A.f17801h.f17845h.f15752p;
            q02.L(q02.f17843f.f17811t);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void r(float f10) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        i0A.f17810s = i0A.f17810s.p(f10);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.getClass();
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void t(int i7, e2.V v10, e2.V v11) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.h(i7, v10, v11);
        i0A.c.a(true, true);
        try {
            Q0 q02 = (Q0) i0A.f17801h.f17845h.f15752p;
            q02.L(q02.f17843f.f17811t);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void w(e2.l0 l0Var) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.o(l0Var);
        i0A.c.a(true, true);
        i0A.c(new C1350a(24));
    }

    @Override // e2.U
    public final void x(int i7) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        u1 u1Var = (u1) this.f17780m.get();
        if (u1Var == null) {
            return;
        }
        i0A.f17810s = i0A.f17810s.f(i7, u1Var.n());
        i0A.c.a(true, true);
        try {
            i4.d dVar = i0A.f17801h.f17845h;
            u1Var.n();
            Q0 q02 = (Q0) dVar.f15752p;
            q02.L(q02.f17843f.f17811t);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    @Override // e2.U
    public final void y(long j3) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        if (((u1) this.f17780m.get()) == null) {
            return;
        }
        q1 q1Var = i0A.f17810s;
        e2.f0 f0Var = q1Var.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = q1Var.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        i0A.f17810s = new q1(q1Var.f18071a, q1Var.f18072b, b12, q1Var.d, q1Var.f18073e, q1Var.f18074f, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, q1Var.f18092z, q1Var.f18067A, q1Var.B, j3, q1Var.f18069D, q1Var.f18070E);
        i0A.c.a(true, true);
    }

    @Override // e2.U
    public final void z(e2.I i7) {
        I0 i0A = a();
        if (i0A == null) {
            return;
        }
        i0A.t();
        i0A.f17810s = i0A.f17810s.g(i7);
        i0A.c.a(true, true);
        try {
            i0A.f17801h.f17845h.r(i7);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }
}
