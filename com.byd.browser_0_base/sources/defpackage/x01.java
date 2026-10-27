package defpackage;

import defpackage.q01;
import defpackage.y11;

/* JADX INFO: compiled from: InstalledAppProvider_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class x01 {
    public static final a a = new a();

    /* JADX INFO: compiled from: InstalledAppProvider_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public ka2[] b;
        public qj3 c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public b(int i) {
            super(24);
        }

        public static b d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                b bVar = new b(e70VarC.c(d).b);
                e70 e70VarT = e70VarC.t(8, false);
                int i = e70VarT.k(-1).b;
                bVar.b = new ka2[i];
                for (int i2 = 0; i2 < i; i2++) {
                    bVar.b[i2] = ka2.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
                bVar.c = qj3.d(e70VarC.t(16, false));
                return bVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            ka2[] ka2VarArr = this.b;
            if (ka2VarArr != null) {
                qh0 qh0VarR = qh0VarT.r(ka2VarArr.length, 8);
                int i = 0;
                while (true) {
                    ka2[] ka2VarArr2 = this.b;
                    if (i >= ka2VarArr2.length) {
                        break;
                    }
                    qh0VarR.k(ka2VarArr2[i], (i * 8) + 8, false);
                    i++;
                }
            } else {
                qh0VarT.q(8, false);
            }
            qh0VarT.k(this.c, 16, false);
        }
    }

    /* JADX INFO: compiled from: InstalledAppProvider_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public ka2[] b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public c(int i) {
            super(16);
        }

        public static c d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                c cVar = new c(e70VarC.c(c).b);
                e70 e70VarT = e70VarC.t(8, false);
                int i = e70VarT.k(-1).b;
                cVar.b = new ka2[i];
                for (int i2 = 0; i2 < i; i2++) {
                    cVar.b[i2] = ka2.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
                return cVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(d);
            ka2[] ka2VarArr = this.b;
            if (ka2VarArr == null) {
                qh0VarT.q(8, false);
                return;
            }
            qh0 qh0VarR = qh0VarT.r(ka2VarArr.length, 8);
            int i = 0;
            while (true) {
                ka2[] ka2VarArr2 = this.b;
                if (i >= ka2VarArr2.length) {
                    return;
                }
                qh0VarR.k(ka2VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: InstalledAppProvider_Internal.java */
    public static class d extends cm2 implements gh1 {
        public final q01.a e;

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
                c cVarD = c.d(wi2VarA.f());
                ((e) this.e).c(cVarD.b);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: InstalledAppProvider_Internal.java */
    public static class e implements q01.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public e(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(ka2[] ka2VarArr) {
            c cVar = new c(0);
            cVar.b = ka2VarArr;
            this.f.a(cVar.c(this.e, new yg1(0, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: InstalledAppProvider_Internal.java */
    public static final class f extends y11.a implements q01 {
        public f(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.q01
        public final void Z0(ka2[] ka2VarArr, qj3 qj3Var, e eVar) {
            b bVar = new b(0);
            bVar.b = ka2VarArr;
            bVar.c = qj3Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(bVar.c(c0171a.e, new yg1(0, 1, 0L)), new d(eVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: InstalledAppProvider_Internal.java */
    public static final class g extends y11.d<q01> {
        public g(k00 k00Var, q01 q01Var) {
            super(k00Var, q01Var);
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
                    return z11.a(k00Var, x01.a, wi2VarA, gh1Var);
                }
                if (i != 0) {
                    return false;
                }
                b bVarD = b.d(wi2VarA.f());
                ((q01) this.f).Z0(bVarD.b, bVarD.c, new e(k00Var, gh1Var, yg1Var.d));
                return true;
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

    /* JADX INFO: compiled from: InstalledAppProvider_Internal.java */
    public class a extends y11.b<q01, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new f(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new g(k00Var, (q01) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "blink.mojom.InstalledAppProvider";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
