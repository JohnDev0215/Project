package O;

import F0.C0198d0;
import F0.C0205h;
import F0.InterfaceC0222p0;
import F0.S0;
import K.C0331b0;
import K.G0;
import K.N0;
import K.X;
import N0.C0408c;
import N0.C0411f;
import N1.C0420a;
import T.C0735d;
import T.C0738e0;
import a.AbstractC0904a;
import android.view.ActionMode;
import l0.C1717h;
import l0.C1723n;
import u0.InterfaceC2487a;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final N0 f4912a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public T0.s f4913b = X.c;
    public A7.o c = y.f5005p;
    public C0331b0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0738e0 f4914e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public InterfaceC0222p0 f4915f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public S0 f4916g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public InterfaceC2487a f4917h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C1723n f4918i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0738e0 f4919j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0738e0 f4920k;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Integer f4921m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f4922n;
    public final C0738e0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C0738e0 f4923p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4924q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public T0.y f4925r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public t4.e f4926s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final I f4927t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0420a f4928u;

    public K(N0 n02) {
        this.f4912a = n02;
        T0.y yVar = new T0.y((String) null, 0L, 7);
        T.S s6 = T.S.f8224q;
        this.f4914e = C0735d.O(yVar, s6);
        Boolean bool = Boolean.TRUE;
        this.f4919j = C0735d.O(bool, s6);
        this.f4920k = C0735d.O(bool, s6);
        this.l = 0L;
        this.f4922n = 0L;
        this.o = C0735d.O(null, s6);
        this.f4923p = C0735d.O(null, s6);
        this.f4924q = -1;
        this.f4925r = new T0.y((String) null, 0L, 7);
        this.f4927t = new I(this, 1);
        this.f4928u = new C0420a(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:175:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0160  */
    /* JADX WARN: Type inference failed for: r4v5, types: [A7.o, z7.k] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long a(O.K r22, T0.y r23, long r24, boolean r26, boolean r27, B2.C0074n r28, boolean r29) {
        /*
            Method dump skipped, instruction units count: 794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: O.K.a(O.K, T0.y, long, boolean, boolean, B2.n, boolean):long");
    }

    public static T0.y c(C0411f c0411f, long j3) {
        return new T0.y(c0411f, j3, (N0.K) null);
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [A7.o, z7.k] */
    public final void b(boolean z6) {
        if (N0.K.b(j().f8482b)) {
            return;
        }
        InterfaceC0222p0 interfaceC0222p0 = this.f4915f;
        if (interfaceC0222p0 != null) {
            ((C0205h) interfaceC0222p0).a(com.bumptech.glide.c.h0(j()));
        }
        if (z6) {
            int iD = N0.K.d(j().f8482b);
            this.c.b(c(j().f8481a, AbstractC0904a.q(iD, iD)));
            n(K.P.l);
        }
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [A7.o, z7.k] */
    public final void d() {
        if (N0.K.b(j().f8482b)) {
            return;
        }
        InterfaceC0222p0 interfaceC0222p0 = this.f4915f;
        if (interfaceC0222p0 != null) {
            ((C0205h) interfaceC0222p0).a(com.bumptech.glide.c.h0(j()));
        }
        C0411f c0411fJ0 = com.bumptech.glide.c.j0(j(), j().f8481a.l.length());
        C0411f c0411fI0 = com.bumptech.glide.c.i0(j(), j().f8481a.l.length());
        C0408c c0408c = new C0408c(c0411fJ0);
        c0408c.b(c0411fI0);
        C0411f c0411fF = c0408c.f();
        int iE = N0.K.e(j().f8482b);
        this.c.b(c(c0411fF, AbstractC0904a.q(iE, iE)));
        n(K.P.l);
        this.f4912a.f3189f = true;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [A7.o, z7.k] */
    public final void e(m0.c cVar) {
        if (!N0.K.b(j().f8482b)) {
            C0331b0 c0331b0 = this.d;
            G0 g0D = c0331b0 != null ? c0331b0.d() : null;
            int iD = (cVar == null || g0D == null) ? N0.K.d(j().f8482b) : this.f4913b.a(g0D.b(cVar.f18880a, true));
            this.c.b(T0.y.a(j(), null, AbstractC0904a.q(iD, iD), 5));
        }
        n((cVar == null || j().f8481a.l.length() <= 0) ? K.P.l : K.P.f3195n);
        p(false);
    }

    public final void f(boolean z6) {
        C1723n c1723n;
        C0331b0 c0331b0 = this.d;
        if (c0331b0 != null && !c0331b0.b() && (c1723n = this.f4918i) != null) {
            c1723n.a(C1717h.f18335q);
        }
        this.f4925r = j();
        p(z6);
        n(K.P.f3194m);
    }

    public final m0.c g() {
        return (m0.c) this.f4923p.getValue();
    }

    public final boolean h() {
        return ((Boolean) this.f4920k.getValue()).booleanValue();
    }

    public final long i(boolean z6) {
        G0 g0D;
        long j3;
        C0331b0 c0331b0 = this.d;
        if (c0331b0 == null || (g0D = c0331b0.d()) == null) {
            return 9205357640488583168L;
        }
        N0.H h10 = g0D.f3161a;
        C0331b0 c0331b02 = this.d;
        C0411f c0411f = c0331b02 != null ? c0331b02.f3220a.f3338a : null;
        if (c0411f == null) {
            return 9205357640488583168L;
        }
        if (!A7.m.a(c0411f.l, h10.f4348a.f4340a.l)) {
            return 9205357640488583168L;
        }
        T0.y yVarJ = j();
        if (z6) {
            long j10 = yVarJ.f8482b;
            int i7 = N0.K.c;
            j3 = j10 >> 32;
        } else {
            long j11 = yVarJ.f8482b;
            int i10 = N0.K.c;
            j3 = j11 & 4294967295L;
        }
        int iB = this.f4913b.b((int) j3);
        boolean zF = N0.K.f(j().f8482b);
        int iF = h10.f(iB);
        N0.o oVar = h10.f4349b;
        if (iF >= oVar.f4394f) {
            return 9205357640488583168L;
        }
        float fD = h10.d(iB, h10.a(((!z6 || zF) && (z6 || !zF)) ? Math.max(iB + (-1), 0) : iB) == h10.j(iB));
        long j12 = h10.c;
        return h6.e.a(AbstractC2673a.u(fD, 0.0f, (int) (j12 >> 32)), AbstractC2673a.u(oVar.b(iF), 0.0f, (int) (j12 & 4294967295L)));
    }

    public final T0.y j() {
        return (T0.y) this.f4914e.getValue();
    }

    public final void k() {
        S0 s02 = this.f4916g;
        if ((s02 != null ? ((C0198d0) s02).d : 0) != 1 || s02 == null) {
            return;
        }
        C0198d0 c0198d0 = (C0198d0) s02;
        c0198d0.d = 2;
        ActionMode actionMode = c0198d0.f2190b;
        if (actionMode != null) {
            actionMode.finish();
        }
        c0198d0.f2190b = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0147  */
    /* JADX WARN: Type inference failed for: r2v12, types: [A7.o, z7.k] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l() {
        /*
            Method dump skipped, instruction units count: 787
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: O.K.l():void");
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [A7.o, z7.k] */
    public final void m() {
        T0.y yVarC = c(j().f8481a, AbstractC0904a.q(0, j().f8481a.l.length()));
        this.c.b(yVarC);
        this.f4925r = T0.y.a(this.f4925r, null, yVarC.f8482b, 5);
        f(true);
    }

    public final void n(K.P p10) {
        C0331b0 c0331b0 = this.d;
        if (c0331b0 != null) {
            if (c0331b0.a() == p10) {
                c0331b0 = null;
            }
            if (c0331b0 != null) {
                c0331b0.f3228k.setValue(p10);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0188  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o() {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: O.K.o():void");
    }

    public final void p(boolean z6) {
        C0331b0 c0331b0 = this.d;
        if (c0331b0 != null) {
            c0331b0.l.setValue(Boolean.valueOf(z6));
        }
        if (z6) {
            o();
        } else {
            k();
        }
    }
}
