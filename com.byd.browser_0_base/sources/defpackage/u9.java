package defpackage;

import defpackage.p9;
import defpackage.y11;

/* JADX INFO: compiled from: Authenticator_Internal.java */
/* JADX INFO: loaded from: classes.dex */
public final class u9 {
    public static final a a = new a();

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public b(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public k72 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public c(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] e;
        public static final t50 f;
        public int b;
        public ps0 c;
        public jq3 d;

        static {
            t50 t50Var = new t50(32, 0);
            e = new t50[]{t50Var};
            f = t50Var;
        }

        public d(int i) {
            super(32);
        }

        public static d d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                d dVar = new d(e70VarC.c(e).b);
                int iP = e70VarC.p(8);
                dVar.b = iP;
                if (!(iP >= 0 && iP <= 24)) {
                    throw new q80("Invalid enum value.");
                }
                dVar.b = iP;
                dVar.c = ps0.d(e70VarC.t(16, true));
                dVar.d = jq3.d(e70VarC.t(24, true));
                return dVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(f);
            qh0VarT.b(this.b, 8);
            qh0VarT.k(this.c, 16, true);
            qh0VarT.k(this.d, 24, true);
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static class e extends cm2 implements gh1 {
        public final p9.a e;

        public e(f fVar) {
            this.e = fVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(1, 2)) {
                    return false;
                }
                d dVarD = d.d(wi2VarA.f());
                this.e.d(Integer.valueOf(dVarD.b), dVarD.c, dVarD.d);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static class f implements p9.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public f(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.tl
        public final void d(Integer num, ps0 ps0Var, jq3 jq3Var) {
            d dVar = new d(0);
            dVar.b = num.intValue();
            dVar.c = ps0Var;
            dVar.d = jq3Var;
            this.f.a(dVar.c(this.e, new yg1(1, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class g extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public g(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class h extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public boolean b;

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
            qh0Var.t(d).d(8, 0, this.b);
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static class i extends cm2 implements gh1 {
        public final p9.b e;

        public i(j jVar) {
            this.e = jVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(3, 2)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(h.c);
                    boolean zD = e70Var.d(8, 0);
                    e70Var.a();
                    ((j) this.e).c(Boolean.valueOf(zD));
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

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static class j implements p9.b {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public j(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(Boolean bool) {
            h hVar = new h(0);
            hVar.b = bool.booleanValue();
            this.f.a(hVar.c(this.e, new yg1(3, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class k extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public k(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class l extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public boolean b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public l(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).d(8, 0, this.b);
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static class m extends cm2 implements gh1 {
        public final p9.c e;

        public m(n nVar) {
            this.e = nVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(2, 2)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(l.c);
                    boolean zD = e70Var.d(8, 0);
                    e70Var.a();
                    this.e.c(Boolean.valueOf(zD));
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

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static class n implements p9.c {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public n(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(Boolean bool) {
            l lVar = new l(0);
            lVar.b = bool.booleanValue();
            this.f.a(lVar.c(this.e, new yg1(2, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class o extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public h72 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public o(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class p extends hv2 {
        public static final t50[] e;
        public static final t50 f;
        public int b;
        public ib1 c;
        public jq3 d;

        static {
            t50 t50Var = new t50(32, 0);
            e = new t50[]{t50Var};
            f = t50Var;
        }

        public p(int i) {
            super(32);
        }

        public static p d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                p pVar = new p(e70VarC.c(e).b);
                int iP = e70VarC.p(8);
                pVar.b = iP;
                if (!(iP >= 0 && iP <= 24)) {
                    throw new q80("Invalid enum value.");
                }
                pVar.b = iP;
                pVar.c = ib1.d(e70VarC.t(16, true));
                pVar.d = jq3.d(e70VarC.t(24, true));
                return pVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(f);
            qh0VarT.b(this.b, 8);
            qh0VarT.k(this.c, 16, true);
            qh0VarT.k(this.d, 24, true);
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static class q extends cm2 implements gh1 {
        public final p9.d e;

        public q(r rVar) {
            this.e = rVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(0, 2)) {
                    return false;
                }
                p pVarD = p.d(wi2VarA.f());
                this.e.d(Integer.valueOf(pVarD.b), pVarD.c, pVarD.d);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static class r implements p9.d {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public r(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.tl
        public final void d(Integer num, ib1 ib1Var, jq3 jq3Var) {
            p pVar = new p(0);
            pVar.b = num.intValue();
            pVar.c = ib1Var;
            pVar.d = jq3Var;
            this.f.a(pVar.c(this.e, new yg1(0, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class s extends y11.a implements p9 {
        public s(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.p9
        public final void cancel() {
            b bVar = new b(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(4)));
        }

        @Override // defpackage.p9
        public final void e1(k72 k72Var, p9.a aVar) {
            c cVar = new c(0);
            cVar.b = k72Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(cVar.c(c0171a.e, new yg1(1, 1, 0L)), new e((f) aVar));
        }

        @Override // defpackage.p9
        public final void i0(n nVar) {
            k kVar = new k(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(kVar.c(c0171a.e, new yg1(2, 1, 0L)), new m(nVar));
        }

        @Override // defpackage.p9
        public final void n(h72 h72Var, p9.d dVar) {
            o oVar = new o(0);
            oVar.b = h72Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(oVar.c(c0171a.e, new yg1(0, 1, 0L)), new q((r) dVar));
        }

        @Override // defpackage.p9
        public final void o0(j jVar) {
            g gVar = new g(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(gVar.c(c0171a.e, new yg1(3, 1, 0L)), new i(jVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public static final class t extends y11.d<p9> {
        public t(k00 k00Var, p9 p9Var) {
            super(k00Var, p9Var);
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
                return z11.a(k00Var, u9.a, wi2VarA, gh1Var);
            }
            Object obj = this.f;
            if (i == 0) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(o.c);
                    h72 h72VarD = h72.d(e70Var.t(8, false));
                    e70Var.a();
                    ((p9) obj).n(h72VarD, new r(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i == 1) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(c.c);
                    k72 k72VarD = k72.d(e70Var.t(8, false));
                    e70Var.a();
                    ((p9) obj).e1(k72VarD, new f(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i == 2) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(k.b);
                    e70Var.a();
                    ((p9) obj).i0(new n(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i != 3) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(g.b);
                e70Var.a();
                ((p9) obj).o0(new j(k00Var, gh1Var, yg1Var.d));
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
            if (i != 4) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(b.b);
                e70Var.a();
                ((p9) this.f).cancel();
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: Authenticator_Internal.java */
    public class a extends y11.b<p9, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new s(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new t(k00Var, (p9) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "blink.mojom.Authenticator";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
