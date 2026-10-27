package defpackage;

import defpackage.an1;
import defpackage.y11;

/* JADX INFO: compiled from: Nfc_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nn1 {
    public static final a a = new a();

    /* JADX INFO: compiled from: Nfc_Internal.java */
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

    /* JADX INFO: compiled from: Nfc_Internal.java */
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

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public int b;

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
            qh0Var.t(d).b(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class e extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public e(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class f extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public ll1 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public f(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, true);
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static class g extends cm2 implements gh1 {
        public final an1.a e;

        public g(h hVar) {
            this.e = hVar;
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
                    e70Var.c(f.c);
                    ll1 ll1VarD = ll1.d(e70Var.t(8, true));
                    e70Var.a();
                    ((h) this.e).c(ll1VarD);
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

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static class h implements an1.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public h(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(ll1 ll1Var) {
            f fVar = new f(0);
            fVar.b = ll1Var;
            this.f.a(fVar.c(this.e, new yg1(3, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class i extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public ml1 b;
        public pl1 c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public i(int i) {
            super(24);
        }

        public static i d(gq0 gq0Var) {
            pl1 pl1Var;
            e70 e70VarC = gq2.c(gq0Var);
            try {
                i iVar = new i(e70VarC.c(d).b);
                iVar.b = ml1.d(e70VarC.t(8, false));
                e70 e70VarT = e70VarC.t(16, true);
                if (e70VarT == null) {
                    pl1Var = null;
                } else {
                    e70VarT.b();
                    try {
                        pl1 pl1Var2 = new pl1(e70VarT.c(pl1.c).b);
                        pl1Var2.b = e70VarT.d(8, 0);
                        e70VarT.a();
                        pl1Var = pl1Var2;
                    } catch (Throwable th) {
                        e70VarT.a();
                        throw th;
                    }
                }
                iVar.c = pl1Var;
                return iVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.k(this.b, 8, false);
            qh0VarT.k(this.c, 16, true);
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class j extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public ll1 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public j(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, true);
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static class k extends cm2 implements gh1 {
        public final an1.b e;

        public k(l lVar) {
            this.e = lVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(1, 2)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(j.c);
                    ll1 ll1VarD = ll1.d(e70Var.t(8, true));
                    e70Var.a();
                    ((l) this.e).c(ll1VarD);
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

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static class l implements an1.b {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public l(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(ll1 ll1Var) {
            j jVar = new j(0);
            jVar.b = ll1Var;
            this.f.a(jVar.c(this.e, new yg1(1, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class m extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public dn1 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public m(int i) {
            super(16);
        }

        public static m d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                m mVar = new m(e70VarC.c(c).b);
                mVar.b = (dn1) e70VarC.u(8, false, en1.a);
                return mVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).i(this.b, 8, false, en1.a);
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class n extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public int b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public n(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).b(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class o extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public ll1 b;

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
            qh0Var.t(d).k(this.b, 8, true);
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static class p extends cm2 implements gh1 {
        public final an1.c e;

        public p(q qVar) {
            this.e = qVar;
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
                    e70Var.c(o.c);
                    ll1 ll1VarD = ll1.d(e70Var.t(8, true));
                    e70Var.a();
                    ((q) this.e).c(ll1VarD);
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

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static class q implements an1.c {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public q(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(ll1 ll1Var) {
            o oVar = new o(0);
            oVar.b = ll1Var;
            this.f.a(oVar.c(this.e, new yg1(5, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class r extends y11.a implements an1 {
        public r(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.an1
        public final void H() {
            b bVar = new b(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(4)));
        }

        @Override // defpackage.an1
        public final void N0(int i, q qVar) {
            n nVar = new n(0);
            nVar.b = i;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(nVar.c(c0171a.e, new yg1(5, 1, 0L)), new p(qVar));
        }

        @Override // defpackage.an1
        public final void Q(dn1 dn1Var) {
            m mVar = new m(0);
            mVar.b = dn1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(mVar.c(c0171a.e, new yg1(0)));
        }

        @Override // defpackage.an1
        public final void U(int i) {
            d dVar = new d(0);
            dVar.b = i;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(dVar.c(c0171a.e, new yg1(6)));
        }

        @Override // defpackage.an1
        public final void Y() {
            c cVar = new c(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(cVar.c(c0171a.e, new yg1(2)));
        }

        @Override // defpackage.an1
        public final void Y0(ml1 ml1Var, pl1 pl1Var, l lVar) {
            i iVar = new i(0);
            iVar.b = ml1Var;
            iVar.c = pl1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(iVar.c(c0171a.e, new yg1(1, 1, 0L)), new k(lVar));
        }

        @Override // defpackage.an1
        public final void j(h hVar) {
            e eVar = new e(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(eVar.c(c0171a.e, new yg1(3, 1, 0L)), new g(hVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public static final class s extends y11.d<an1> {
        public s(k00 k00Var, an1 an1Var) {
            super(k00Var, an1Var);
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
                return z11.a(k00Var, nn1.a, wi2VarA, gh1Var);
            }
            Object obj = this.f;
            if (i == 1) {
                i iVarD = i.d(wi2VarA.f());
                ((an1) obj).Y0(iVarD.b, iVarD.c, new l(k00Var, gh1Var, yg1Var.d));
                return true;
            }
            if (i == 3) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(e.b);
                    e70Var.a();
                    ((an1) obj).j(new h(k00Var, gh1Var, yg1Var.d));
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
                e70Var.c(n.c);
                int iP = e70Var.p(8);
                e70Var.a();
                ((an1) obj).N0(iP, new q(k00Var, gh1Var, yg1Var.d));
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
                ((an1) obj).Q(m.d(wi2VarA.f()).b);
                return true;
            }
            if (i == 2) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(c.b);
                    e70Var.a();
                    ((an1) obj).Y();
                    return true;
                } finally {
                }
            }
            if (i == 4) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(b.b);
                    e70Var.a();
                    ((an1) obj).H();
                    return true;
                } finally {
                }
            }
            if (i != 6) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(d.c);
                int iP = e70Var.p(8);
                e70Var.a();
                ((an1) obj).U(iP);
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: Nfc_Internal.java */
    public class a extends y11.b<an1, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new r(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new s(k00Var, (an1) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "device.mojom.NFC";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
