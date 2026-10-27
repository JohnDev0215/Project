package defpackage;

import defpackage.wj3;
import defpackage.y11;

/* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xj3 {
    public static final a a = new a();

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static final class b extends y11.a implements wj3 {
        public b(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.wj3
        public final void B0(long j, long j2, l lVar) {
            i iVar = new i(0);
            iVar.b = j;
            iVar.c = j2;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(iVar.c(c0171a.e, new yg1(3, 1, 0L)), new k(lVar));
        }

        @Override // defpackage.wj3
        public final void I(gf0 gf0Var) {
            e eVar = new e(0);
            eVar.b = gf0Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(eVar.c(c0171a.e, new yg1(0)));
        }

        @Override // defpackage.wj3
        public final void M0(yj3 yj3Var) {
            d dVar = new d(0);
            dVar.b = yj3Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(dVar.c(c0171a.e, new yg1(5)));
        }

        @Override // defpackage.wj3
        public final void f1(zj3 zj3Var, ak3 ak3Var) {
            f fVar = new f(0);
            fVar.b = zj3Var;
            fVar.c = ak3Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(fVar.c(c0171a.e, new yg1(2)));
        }

        @Override // defpackage.wj3
        public final void s(ak3 ak3Var, u50 u50Var, ne neVar) {
            g gVar = new g(0);
            gVar.b = ak3Var;
            gVar.c = u50Var;
            gVar.d = neVar;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(gVar.c(c0171a.e, new yg1(1)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }

        @Override // defpackage.wj3
        public final void x(int i) {
            h hVar = new h(0);
            hVar.b = i;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(hVar.c(c0171a.e, new yg1(4)));
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static final class c extends y11.d<wj3> {
        public c(k00 k00Var, wj3 wj3Var) {
            super(k00Var, wj3Var);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                if (!yg1Var.b(yg1Var.a(4) ? 5 : 1)) {
                    return false;
                }
                int i = yg1Var.b;
                k00 k00Var = this.e;
                if (i == -1) {
                    return z11.a(k00Var, xj3.a, wi2VarA, gh1Var);
                }
                if (i != 3) {
                    return false;
                }
                i iVarD = i.d(wi2VarA.f());
                ((wj3) this.f).B0(iVarD.b, iVarD.c, new l(k00Var, gh1Var, yg1Var.d));
                return true;
            } catch (q80 e) {
                System.err.println(e.toString());
                return false;
            }
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            wi2 wi2VarA;
            yg1 yg1Var;
            e70 e70Var;
            try {
                wi2VarA = gq0Var.a();
                yg1Var = wi2VarA.d;
            } catch (q80 e) {
                System.err.println(e.toString());
                return false;
            }
            if (!yg1Var.b(yg1Var.a(4) ? 4 : 0)) {
                return false;
            }
            int i = yg1Var.b;
            if (i == -2) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(ao0.d);
                    ad2 ad2VarB = ad2.b(e70Var);
                    e70Var.a();
                    return ad2VarB.a == 0 && ad2VarB.b.b <= 0;
                } finally {
                }
            }
            Object obj = this.f;
            if (i == 0) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(e.c);
                    gf0 gf0VarD = gf0.d(e70Var.t(8, false));
                    e70Var.a();
                    ((wj3) obj).I(gf0VarD);
                    return true;
                } finally {
                }
            }
            if (i == 1) {
                g gVarD = g.d(wi2VarA.f());
                ((wj3) obj).s(gVarD.b, gVarD.c, gVarD.d);
                return true;
            }
            if (i == 2) {
                f fVarD = f.d(wi2VarA.f());
                ((wj3) obj).f1(fVarD.b, fVarD.c);
                return true;
            }
            if (i == 4) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(h.c);
                    int iP = e70Var.p(8);
                    e70Var.a();
                    ((wj3) obj).x(iP);
                    return true;
                } finally {
                }
            }
            if (i != 5) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(d.c);
                yj3 yj3VarD = yj3.d(e70Var.t(8, false));
                e70Var.a();
                ((wj3) obj).M0(yj3VarD);
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public yj3 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public d(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static final class e extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public gf0 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public e(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static final class f extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public zj3 b;
        public ak3 c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public f(int i) {
            super(24);
        }

        public static f d(gq0 gq0Var) {
            zj3 zj3Var;
            e70 e70VarC = gq2.c(gq0Var);
            try {
                f fVar = new f(e70VarC.c(d).b);
                e70 e70VarT = e70VarC.t(8, false);
                if (e70VarT == null) {
                    zj3Var = null;
                } else {
                    e70VarT.b();
                    try {
                        zj3Var = new zj3(e70VarT.c(zj3.b).b);
                    } finally {
                        e70VarT.a();
                    }
                }
                fVar.b = zj3Var;
                ak3.d(e70VarC.t(16, false));
                fVar.c = null;
                return fVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.k(this.b, 8, false);
            qh0VarT.k(this.c, 16, false);
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static final class g extends hv2 {
        public static final t50[] e;
        public static final t50 f;
        public ak3 b;
        public u50 c;
        public ne d;

        static {
            t50 t50Var = new t50(40, 0);
            e = new t50[]{t50Var};
            f = t50Var;
        }

        public g(int i) {
            super(40);
            this.c = l21.e;
        }

        public static g d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                g gVar = new g(e70VarC.c(e).b);
                ak3.d(e70VarC.t(8, false));
                gVar.b = null;
                gVar.c = e70VarC.x(16, true).d1();
                gVar.d = ne.b(e70VarC, 24);
                return gVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(f);
            qh0VarT.k(this.b, 8, false);
            qh0VarT.h(this.c, 16, true);
            qh0VarT.l(this.d, 24, true);
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static final class h extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public int b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public h(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).b(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static final class i extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public long b;
        public long c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public i(int i) {
            super(24);
        }

        public static i d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                i iVar = new i(e70VarC.c(d).b);
                iVar.b = e70VarC.s(8);
                iVar.c = e70VarC.s(16);
                return iVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.f(8, this.b);
            qh0VarT.f(16, this.c);
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static final class j extends hv2 {
        public static final t50 b = new t50(8, 0);

        public j() {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(b);
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static class k extends cm2 implements gh1 {
        public final wj3.a e;

        public k(l lVar) {
            this.e = lVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                if (!gq0Var.a().d.c(3, 2)) {
                    return false;
                }
                l lVar = (l) this.e;
                lVar.getClass();
                lVar.b.a(new j().c(lVar.a, new yg1(3, 2, lVar.c)));
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public static class l implements wj3.a {
        public final k00 a;
        public final gh1 b;
        public final long c;

        public l(k00 k00Var, gh1 gh1Var, long j) {
            this.a = k00Var;
            this.b = gh1Var;
            this.c = j;
        }
    }

    /* JADX INFO: compiled from: UrlLoaderClient_Internal.java */
    public class a extends y11.b<wj3, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new b(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new c(k00Var, (wj3) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "network.mojom.URLLoaderClient";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
