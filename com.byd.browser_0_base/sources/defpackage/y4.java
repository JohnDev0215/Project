package defpackage;

import defpackage.x4;
import defpackage.y11;

/* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class y4 {
    public static final a a = new a();

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
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

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
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

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public long b;

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
            qh0Var.t(d).f(8, this.b);
        }
    }

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
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

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
    public static final class f extends hv2 {
        public static final t50 b = new t50(8, 0);

        public f() {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(b);
        }
    }

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
    public static class g extends cm2 implements gh1 {
        public final x4.a e;

        public g(h hVar) {
            this.e = hVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                if (!gq0Var.a().d.c(2, 6)) {
                    return false;
                }
                h hVar = (h) this.e;
                hVar.getClass();
                hVar.b.a(new f().c(hVar.a, new yg1(2, 6, hVar.c)));
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
    public static class h implements x4.a {
        public final k00 a;
        public final gh1 b;
        public final long c;

        public h(k00 k00Var, gh1 gh1Var, long j) {
            this.a = k00Var;
            this.b = gh1Var;
            this.c = j;
        }
    }

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
    public static final class i extends y11.a implements x4.b {
        public i(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.x4
        public final void E() {
            b bVar = new b(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(1)));
        }

        @Override // defpackage.x4
        public final void J(h hVar) {
            e eVar = new e(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(eVar.c(c0171a.e, new yg1(2, 1, 0L)), new g(hVar));
        }

        @Override // defpackage.x4
        public final void o(long j) {
            d dVar = new d(0);
            dVar.b = j;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(dVar.c(c0171a.e, new yg1(0)));
        }

        @Override // defpackage.x4
        public final void onPowerEfficientState(boolean z) {
            c cVar = new c(0);
            cVar.b = z;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(cVar.c(c0171a.e, new yg1(3)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
    public static final class j extends y11.d<x4> {
        public j(k00 k00Var, x4 x4Var) {
            super(k00Var, x4Var);
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
                    return z11.a(k00Var, y4.a, wi2VarA, gh1Var);
                }
                if (i != 2) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(e.b);
                    e70Var.a();
                    ((x4) this.f).J(new h(k00Var, gh1Var, yg1Var.d));
                    return true;
                } catch (Throwable th) {
                    e70Var.a();
                    throw th;
                }
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
            if (i2 == -2) {
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
            if (i2 == 3) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(c.c);
                    boolean zD = e70Var.d(8, 0);
                    e70Var.a();
                    ((x4) obj).onPowerEfficientState(zD);
                    return true;
                } finally {
                }
            }
            if (i2 == 0) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(d.c);
                    long jS = e70Var.s(8);
                    e70Var.a();
                    ((x4) obj).o(jS);
                    return true;
                } finally {
                }
            }
            if (i2 != 1) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(b.b);
                e70Var.a();
                ((x4) obj).E();
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: AndroidOverlayClient_Internal.java */
    public class a extends y11.b<x4, x4.b> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new i(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new j(k00Var, (x4) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "media.mojom.AndroidOverlayClient";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
