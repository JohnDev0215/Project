package defpackage;

import defpackage.pz1;
import defpackage.y11;

/* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qz1 {
    public static final a a = new a();

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] e;
        public static final t50 f;
        public qj3 b;
        public qj3 c;
        public boolean d;

        static {
            t50 t50Var = new t50(32, 0);
            e = new t50[]{t50Var};
            f = t50Var;
        }

        public b(int i) {
            super(32);
        }

        public static b d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                b bVar = new b(e70VarC.c(e).b);
                bVar.b = qj3.d(e70VarC.t(8, false));
                bVar.c = qj3.d(e70VarC.t(16, false));
                bVar.d = e70VarC.d(24, 0);
                return bVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(f);
            qh0VarT.k(this.b, 8, false);
            qh0VarT.k(this.c, 16, false);
            qh0VarT.d(24, 0, this.d);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public boolean b;

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
            qh0Var.t(d).d(8, 0, this.b);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static class d extends cm2 implements gh1 {
        public final pz1.a e;

        public d(pz1.a aVar) {
            this.e = aVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(11, 2)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(c.c);
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

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static class e implements pz1.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public e(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(Boolean bool) {
            c cVar = new c(0);
            cVar.b = bool.booleanValue();
            this.f.a(cVar.c(this.e, new yg1(11, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class f extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public boolean b;

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
            qh0Var.t(d).d(8, 0, this.b);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class g extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public int b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public g(int i) {
            super(16);
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static qz1.g d(defpackage.gq0 r3) {
            /*
                e70 r3 = defpackage.gq2.c(r3)
                t50[] r0 = qz1.g.c     // Catch: java.lang.Throwable -> L30
                t50 r0 = r3.c(r0)     // Catch: java.lang.Throwable -> L30
                int r0 = r0.b     // Catch: java.lang.Throwable -> L30
                qz1$g r1 = new qz1$g     // Catch: java.lang.Throwable -> L30
                r1.<init>(r0)     // Catch: java.lang.Throwable -> L30
                r0 = 8
                int r0 = r3.p(r0)     // Catch: java.lang.Throwable -> L30
                r1.b = r0     // Catch: java.lang.Throwable -> L30
                if (r0 < 0) goto L1f
                r2 = 1
                if (r0 > r2) goto L1f
                goto L20
            L1f:
                r2 = 0
            L20:
                if (r2 == 0) goto L28
                r1.b = r0     // Catch: java.lang.Throwable -> L30
                r3.a()
                return r1
            L28:
                q80 r0 = new q80     // Catch: java.lang.Throwable -> L30
                java.lang.String r1 = "Invalid enum value."
                r0.<init>(r1)     // Catch: java.lang.Throwable -> L30
                throw r0     // Catch: java.lang.Throwable -> L30
            L30:
                r0 = move-exception
                r3.a()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: qz1.g.d(gq0):qz1$g");
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).b(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
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

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class i extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public int b;
        public String c;

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
                int iP = e70VarC.p(8);
                iVar.b = iP;
                if (!(iP >= 0 && iP <= 7)) {
                    throw new q80("Invalid enum value.");
                }
                iVar.b = iP;
                iVar.c = e70VarC.w(16, false);
                return iVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.b(this.b, 8);
            qh0VarT.m(this.c, 16, false);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class j extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public int b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public j(int i) {
            super(16);
        }

        public static j d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                j jVar = new j(e70VarC.c(c).b);
                int iP = e70VarC.p(8);
                jVar.b = iP;
                if (!(iP >= 0 && iP <= 4)) {
                    throw new q80("Invalid enum value.");
                }
                jVar.b = iP;
                return jVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).b(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class k extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public iy1 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public k(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class l extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public String b;
        public String c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public l(int i) {
            super(24);
        }

        public static l d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                l lVar = new l(e70VarC.c(d).b);
                lVar.b = e70VarC.w(8, false);
                lVar.c = e70VarC.w(16, false);
                return lVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.m(this.b, 8, false);
            qh0VarT.m(this.c, 16, false);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class m extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public vz1 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public m(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class n extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public ky1 b;

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
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class o extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public String b;

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
            qh0Var.t(d).m(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class p extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public p(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class q extends y11.a implements pz1 {
        public q(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.pz1
        public final void G(iy1 iy1Var) {
            k kVar = new k(0);
            kVar.b = iy1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(kVar.c(c0171a.e, new yg1(3)));
        }

        @Override // defpackage.pz1
        public final void G0(String str, String str2) {
            l lVar = new l(0);
            lVar.b = str;
            lVar.c = str2;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(lVar.c(c0171a.e, new yg1(0)));
        }

        @Override // defpackage.pz1
        public final void V(int i) {
            g gVar = new g(0);
            gVar.b = i;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(gVar.c(c0171a.e, new yg1(8)));
        }

        @Override // defpackage.pz1
        public final void V0() {
            p pVar = new p(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(pVar.c(c0171a.e, new yg1(10)));
        }

        @Override // defpackage.pz1
        public final void W(String str) {
            o oVar = new o(0);
            oVar.b = str;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(oVar.c(c0171a.e, new yg1(2)));
        }

        @Override // defpackage.pz1
        public final void b() {
            h hVar = new h(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(hVar.c(c0171a.e, new yg1(6)));
        }

        @Override // defpackage.pz1
        public final void e(ky1 ky1Var) {
            n nVar = new n(0);
            nVar.b = ky1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(nVar.c(c0171a.e, new yg1(1)));
        }

        @Override // defpackage.pz1
        public final void g(boolean z) {
            f fVar = new f(0);
            fVar.b = z;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(fVar.c(c0171a.e, new yg1(7)));
        }

        @Override // defpackage.pz1
        public final void i(int i) {
            j jVar = new j(0);
            jVar.b = i;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(jVar.c(c0171a.e, new yg1(9)));
        }

        @Override // defpackage.pz1
        public final void m0(vz1 vz1Var) {
            m mVar = new m(0);
            mVar.b = vz1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(mVar.c(c0171a.e, new yg1(4)));
        }

        @Override // defpackage.pz1
        public final void onError(int i, String str) {
            i iVar = new i(0);
            iVar.b = i;
            iVar.c = str;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(iVar.c(c0171a.e, new yg1(5)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }

        @Override // defpackage.pz1
        public final void v(qj3 qj3Var, qj3 qj3Var2, boolean z, pz1.a aVar) {
            b bVar = new b(0);
            bVar.b = qj3Var;
            bVar.c = qj3Var2;
            bVar.d = z;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(bVar.c(c0171a.e, new yg1(11, 1, 0L)), new d(aVar));
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public static final class r extends y11.d<pz1> {
        public r(k00 k00Var, pz1 pz1Var) {
            super(k00Var, pz1Var);
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
                    return z11.a(k00Var, qz1.a, wi2VarA, gh1Var);
                }
                if (i != 11) {
                    return false;
                }
                b bVarD = b.d(wi2VarA.f());
                ((pz1) this.f).v(bVarD.b, bVarD.c, bVarD.d, new e(k00Var, gh1Var, yg1Var.d));
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
            int i;
            e70 e70Var;
            try {
                wi2VarA = gq0Var.a();
                yg1Var = wi2VarA.d;
                i = 4;
                if (!yg1Var.a(4)) {
                    i = 0;
                }
            } catch (q80 e) {
                System.err.println(e.toString());
                return false;
            }
            if (!yg1Var.b(i)) {
                return false;
            }
            int i2 = yg1Var.b;
            Object obj = this.f;
            switch (i2) {
                case -2:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(ao0.d);
                        ad2 ad2VarB = ad2.b(e70Var);
                        e70Var.a();
                        return ad2VarB.a == 0 && ad2VarB.b.b <= 0;
                    } finally {
                    }
                case -1:
                default:
                    return false;
                case 0:
                    l lVarD = l.d(wi2VarA.f());
                    ((pz1) obj).G0(lVarD.b, lVarD.c);
                    return true;
                case 1:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(n.c);
                        ky1 ky1VarD = ky1.d(e70Var.t(8, false));
                        e70Var.a();
                        ((pz1) obj).e(ky1VarD);
                        return true;
                    } finally {
                    }
                case 2:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(o.c);
                        String strW = e70Var.w(8, false);
                        e70Var.a();
                        ((pz1) obj).W(strW);
                        return true;
                    } finally {
                    }
                case 3:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(k.c);
                        iy1 iy1VarD = iy1.d(e70Var.t(8, false));
                        e70Var.a();
                        ((pz1) obj).G(iy1VarD);
                        return true;
                    } finally {
                    }
                case 4:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(m.c);
                        vz1 vz1VarD = vz1.d(e70Var.t(8, false));
                        e70Var.a();
                        ((pz1) obj).m0(vz1VarD);
                        return true;
                    } finally {
                    }
                case 5:
                    i iVarD = i.d(wi2VarA.f());
                    ((pz1) obj).onError(iVarD.b, iVarD.c);
                    return true;
                case 6:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(h.b);
                        e70Var.a();
                        ((pz1) obj).b();
                        return true;
                    } finally {
                    }
                case 7:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(f.c);
                        boolean zD = e70Var.d(8, 0);
                        e70Var.a();
                        ((pz1) obj).g(zD);
                        return true;
                    } finally {
                    }
                case 8:
                    ((pz1) obj).V(g.d(wi2VarA.f()).b);
                    return true;
                case 9:
                    ((pz1) obj).i(j.d(wi2VarA.f()).b);
                    return true;
                case 10:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(p.b);
                        e70Var.a();
                        ((pz1) obj).V0();
                        return true;
                    } finally {
                    }
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: PaymentRequestClient_Internal.java */
    public class a extends y11.b<pz1, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new q(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new r(k00Var, (pz1) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "payments.mojom.PaymentRequestClient";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
