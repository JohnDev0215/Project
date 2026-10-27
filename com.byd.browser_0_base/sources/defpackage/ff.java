package defpackage;

import defpackage.bf;
import defpackage.y11;

/* JADX INFO: compiled from: Blob_Internal.java */
/* JADX INFO: loaded from: classes.dex */
public final class ff {
    public static final a a = new a();

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public f21<Object> b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public b(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).j(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public c(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public long b;
        public oa3 c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public d(int i) {
            super(24);
        }

        public static d d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                d dVar = new d(e70VarC.c(d).b);
                dVar.b = e70VarC.s(8);
                dVar.c = oa3.d(e70VarC.t(16, true));
                return dVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.f(8, this.b);
            qh0VarT.k(this.c, 16, true);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static class e extends cm2 implements gh1 {
        public final bf.a e;

        public e(f fVar) {
            this.e = fVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(6, 6)) {
                    return false;
                }
                d dVarD = d.d(wi2VarA.f());
                bf.a aVar = this.e;
                Long lValueOf = Long.valueOf(dVarD.b);
                oa3 oa3Var = dVarD.c;
                f fVar = (f) aVar;
                fVar.getClass();
                d dVar = new d(0);
                dVar.b = lValueOf.longValue();
                dVar.c = oa3Var;
                fVar.b.a(dVar.c(fVar.a, new yg1(6, 6, fVar.c)));
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static class f implements bf.a {
        public final k00 a;
        public final gh1 b;
        public final long c;

        public f(k00 k00Var, gh1 gh1Var, long j) {
            this.a = k00Var;
            this.b = gh1Var;
            this.c = j;
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class g extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public f21<bf> b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public g(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).j(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class h extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public h(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class i extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public String b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public i(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).m(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static class j extends cm2 implements gh1 {
        public final bf.b e;

        public j(k kVar) {
            this.e = kVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(7, 2)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(i.c);
                    String strW = e70Var.w(8, false);
                    e70Var.a();
                    ((k) this.e).c(strW);
                    return true;
                } catch (Throwable th) {
                    e70Var.a();
                    throw th;
                }
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static class k implements bf.b {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public k(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(String str) {
            i iVar = new i(0);
            iVar.b = str;
            this.f.a(iVar.c(this.e, new yg1(7, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class l extends hv2 {
        public static final t50[] f;
        public static final t50 g;
        public f21<Object> b;
        public String c;
        public vv0 d;
        public wj3 e;

        static {
            t50 t50Var = new t50(40, 0);
            f = new t50[]{t50Var};
            g = t50Var;
        }

        public l(int i) {
            super(40);
        }

        public static l d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                l lVar = new l(e70VarC.c(f).b);
                lVar.b = e70VarC.q(8);
                lVar.c = e70VarC.w(16, false);
                lVar.d = vv0.d(e70VarC.t(24, false));
                lVar.e = (wj3) e70VarC.u(32, false, xj3.a);
                return lVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(g);
            qh0VarT.j(this.b, 8);
            qh0VarT.m(this.c, 16, false);
            qh0VarT.k(this.d, 24, false);
            qh0VarT.i(this.e, 32, false, xj3.a);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class m extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public w50 b;
        public cf c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public m(int i) {
            super(24);
            this.b = l21.e;
        }

        public static m d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                m mVar = new m(e70VarC.c(d).b);
                mVar.b = e70VarC.x(8, false).P();
                mVar.c = (cf) e70VarC.u(12, true, df.a);
                return mVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.h(this.b, 8, false);
            qh0VarT.i(this.c, 12, true, df.a);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class n extends hv2 {
        public static final t50[] f;
        public static final t50 g;
        public long b;
        public long c;
        public w50 d;
        public cf e;

        static {
            t50 t50Var = new t50(40, 0);
            f = new t50[]{t50Var};
            g = t50Var;
        }

        public n(int i) {
            super(40);
            this.d = l21.e;
        }

        public static n d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                n nVar = new n(e70VarC.c(f).b);
                nVar.b = e70VarC.s(8);
                nVar.c = e70VarC.s(16);
                nVar.d = e70VarC.x(24, false).P();
                nVar.e = (cf) e70VarC.u(28, true, df.a);
                return nVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(g);
            qh0VarT.f(8, this.b);
            qh0VarT.f(16, this.c);
            qh0VarT.h(this.d, 24, false);
            qh0VarT.i(this.e, 28, true, df.a);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class o extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public o(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class p extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public ne b;

        static {
            t50 t50Var = new t50(24, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public p(int i) {
            super(24);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).l(this.b, 8, true);
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static class q extends cm2 implements gh1 {
        public final bf.c e;

        public q(r rVar) {
            this.e = rVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(5, 2)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(p.c);
                    ne neVarB = ne.b(e70Var, 8);
                    e70Var.a();
                    ((r) this.e).c(neVarB);
                    return true;
                } catch (Throwable th) {
                    e70Var.a();
                    throw th;
                }
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static class r implements bf.c {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public r(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(ne neVar) {
            p pVar = new p(0);
            pVar.b = neVar;
            this.f.a(pVar.c(this.e, new yg1(5, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class s extends y11.a implements bf {
        public s(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.bf
        public final void F0(f21<bf> f21Var) {
            g gVar = new g(0);
            gVar.b = f21Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(gVar.c(c0171a.e, new yg1(0)));
        }

        @Override // defpackage.bf
        public final void J0(k kVar) {
            h hVar = new h(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(hVar.c(c0171a.e, new yg1(7, 1, 0L)), new j(kVar));
        }

        @Override // defpackage.bf
        public final void S0(f21<Object> f21Var) {
            b bVar = new b(0);
            bVar.b = f21Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(1)));
        }

        @Override // defpackage.bf
        public final void Z(w50 w50Var, cf cfVar) {
            m mVar = new m(0);
            mVar.b = w50Var;
            mVar.c = cfVar;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(mVar.c(c0171a.e, new yg1(2)));
        }

        @Override // defpackage.bf
        public final void a1(f fVar) {
            c cVar = new c(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(cVar.c(c0171a.e, new yg1(6, 1, 0L)), new e(fVar));
        }

        @Override // defpackage.bf
        public final void p0(r rVar) {
            o oVar = new o(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(oVar.c(c0171a.e, new yg1(5, 1, 0L)), new q(rVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }

        @Override // defpackage.bf
        public final void y0(long j, long j2, w50 w50Var, cf cfVar) {
            n nVar = new n(0);
            nVar.b = j;
            nVar.c = j2;
            nVar.d = w50Var;
            nVar.e = cfVar;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(nVar.c(c0171a.e, new yg1(3)));
        }

        @Override // defpackage.bf
        public final void z0(f21<Object> f21Var, String str, vv0 vv0Var, wj3 wj3Var) {
            l lVar = new l(0);
            lVar.b = f21Var;
            lVar.c = str;
            lVar.d = vv0Var;
            lVar.e = wj3Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(lVar.c(c0171a.e, new yg1(4)));
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public static final class t extends y11.d<bf> {
        public t(k00 k00Var, bf bfVar) {
            super(k00Var, bfVar);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
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
            if (!yg1Var.b(yg1Var.a(4) ? 5 : 1)) {
                return false;
            }
            int i = yg1Var.b;
            k00 k00Var = this.e;
            if (i == -1) {
                return z11.a(k00Var, ff.a, wi2VarA, gh1Var);
            }
            Object obj = this.f;
            if (i == 5) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(o.b);
                    e70Var.a();
                    ((bf) obj).p0(new r(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i == 6) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(c.b);
                    e70Var.a();
                    ((bf) obj).a1(new f(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i != 7) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(h.b);
                e70Var.a();
                ((bf) obj).J0(new k(k00Var, gh1Var, yg1Var.d));
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
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
                    e70Var.c(g.c);
                    f21<bf> f21VarQ = e70Var.q(8);
                    e70Var.a();
                    ((bf) obj).F0(f21VarQ);
                    return true;
                } finally {
                }
            }
            if (i == 1) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(b.c);
                    f21<Object> f21VarQ2 = e70Var.q(8);
                    e70Var.a();
                    ((bf) obj).S0(f21VarQ2);
                    return true;
                } finally {
                }
            }
            if (i == 2) {
                m mVarD = m.d(wi2VarA.f());
                ((bf) obj).Z(mVarD.b, mVarD.c);
                return true;
            }
            if (i == 3) {
                n nVarD = n.d(wi2VarA.f());
                ((bf) obj).y0(nVarD.b, nVarD.c, nVarD.d, nVarD.e);
                return true;
            }
            if (i != 4) {
                return false;
            }
            l lVarD = l.d(wi2VarA.f());
            ((bf) obj).z0(lVarD.b, lVarD.c, lVarD.d, lVarD.e);
            return true;
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: Blob_Internal.java */
    public class a extends y11.b<bf, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new s(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new t(k00Var, (bf) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "blink.mojom.Blob";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
