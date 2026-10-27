package defpackage;

import defpackage.ol3;
import defpackage.y11;

/* JADX INFO: compiled from: VibrationManager_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pl3 {
    public static final a a = new a();

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static final class b extends y11.a implements ol3 {
        public b(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.ol3
        public final void O0(long j, k kVar) {
            h hVar = new h(0);
            hVar.b = j;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(hVar.c(c0171a.e, new yg1(0, 1, 0L)), new j(kVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }

        @Override // defpackage.ol3
        public final void x0(g gVar) {
            d dVar = new d(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(dVar.c(c0171a.e, new yg1(1, 1, 0L)), new f(gVar));
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static final class c extends y11.d<ol3> {
        public c(k00 k00Var, ol3 ol3Var) {
            super(k00Var, ol3Var);
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
                return z11.a(k00Var, pl3.a, wi2VarA, gh1Var);
            }
            Object obj = this.f;
            if (i == 0) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(h.c);
                    long jS = e70Var.s(8);
                    e70Var.a();
                    ((ol3) obj).O0(jS, new k(k00Var, gh1Var, yg1Var.d));
                    return true;
                } finally {
                }
            }
            if (i != 1) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(d.b);
                e70Var.a();
                ((ol3) obj).x0(new g(k00Var, gh1Var, yg1Var.d));
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                int i = 4;
                if (!yg1Var.a(4)) {
                    i = 0;
                }
                if (!yg1Var.b(i) || yg1Var.b != -2) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(ao0.d);
                    ad2 ad2VarB = ad2.b(e70Var);
                    e70Var.a();
                    return ad2VarB.a == 0 && ad2VarB.b.b <= 0;
                } catch (Throwable th) {
                    e70Var.a();
                    throw th;
                }
            } catch (q80 e) {
                System.err.println(e.toString());
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public d(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static final class e extends hv2 {
        public static final t50 b = new t50(8, 0);

        public e() {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(b);
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static class f extends cm2 implements gh1 {
        public final ol3.a e;

        public f(g gVar) {
            this.e = gVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                if (!gq0Var.a().d.c(1, 2)) {
                    return false;
                }
                g gVar = (g) this.e;
                gVar.getClass();
                gVar.b.a(new e().c(gVar.a, new yg1(1, 2, gVar.c)));
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static class g implements ol3.a {
        public final k00 a;
        public final gh1 b;
        public final long c;

        public g(k00 k00Var, gh1 gh1Var, long j) {
            this.a = k00Var;
            this.b = gh1Var;
            this.c = j;
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static final class h extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public long b;

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
            qh0Var.t(d).f(8, this.b);
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static final class i extends hv2 {
        public static final t50 b = new t50(8, 0);

        public i() {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(b);
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static class j extends cm2 implements gh1 {
        public final ol3.b e;

        public j(k kVar) {
            this.e = kVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                if (!gq0Var.a().d.c(0, 2)) {
                    return false;
                }
                k kVar = (k) this.e;
                kVar.getClass();
                kVar.b.a(new i().c(kVar.a, new yg1(0, 2, kVar.c)));
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public static class k implements ol3.b {
        public final k00 a;
        public final gh1 b;
        public final long c;

        public k(k00 k00Var, gh1 gh1Var, long j) {
            this.a = k00Var;
            this.b = gh1Var;
            this.c = j;
        }
    }

    /* JADX INFO: compiled from: VibrationManager_Internal.java */
    public class a extends y11.b<ol3, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new b(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new c(k00Var, (ol3) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "device.mojom.VibrationManager";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
