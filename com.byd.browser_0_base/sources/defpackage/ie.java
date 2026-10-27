package defpackage;

import defpackage.fe;
import defpackage.y11;

/* JADX INFO: compiled from: BatteryMonitor_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ie {
    public static final a a = new a();

    /* JADX INFO: compiled from: BatteryMonitor_Internal.java */
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

    /* JADX INFO: compiled from: BatteryMonitor_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public je b;

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

    /* JADX INFO: compiled from: BatteryMonitor_Internal.java */
    public static class d extends cm2 implements gh1 {
        public final fe.a e;

        public d(e eVar) {
            this.e = eVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(0, 2)) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(c.c);
                    je jeVarD = je.d(e70Var.t(8, false));
                    e70Var.a();
                    ((e) this.e).c(jeVarD);
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

    /* JADX INFO: compiled from: BatteryMonitor_Internal.java */
    public static class e implements fe.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public e(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(je jeVar) {
            c cVar = new c(0);
            cVar.b = jeVar;
            this.f.a(cVar.c(this.e, new yg1(0, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: BatteryMonitor_Internal.java */
    public static final class f extends y11.a implements fe {
        public f(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.fe
        public final void g1(e eVar) {
            b bVar = new b(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(bVar.c(c0171a.e, new yg1(0, 1, 0L)), new d(eVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: BatteryMonitor_Internal.java */
    public static final class g extends y11.d<fe> {
        public g(k00 k00Var, fe feVar) {
            super(k00Var, feVar);
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
                    return z11.a(k00Var, ie.a, wi2VarA, gh1Var);
                }
                if (i != 0) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(b.b);
                    e70Var.a();
                    ((fe) this.f).g1(new e(k00Var, gh1Var, yg1Var.d));
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

    /* JADX INFO: compiled from: BatteryMonitor_Internal.java */
    public class a extends y11.b<fe, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new f(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new g(k00Var, (fe) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "device.mojom.BatteryMonitor";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
