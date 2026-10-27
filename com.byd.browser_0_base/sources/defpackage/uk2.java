package defpackage;

import defpackage.ok2;
import defpackage.y11;

/* JADX INFO: compiled from: ShareService_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uk2 {
    public static final a a = new a();

    /* JADX INFO: compiled from: ShareService_Internal.java */
    public static final class b extends y11.a implements ok2 {
        public b(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.ok2
        public final void r(String str, String str2, qj3 qj3Var, ml2[] ml2VarArr, f fVar) {
            c cVar = new c(0);
            cVar.b = str;
            cVar.c = str2;
            cVar.d = qj3Var;
            cVar.e = ml2VarArr;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(cVar.c(c0171a.e, new yg1(0, 1, 0L)), new e(fVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: ShareService_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] f;
        public static final t50 g;
        public String b;
        public String c;
        public qj3 d;
        public ml2[] e;

        static {
            t50 t50Var = new t50(40, 0);
            f = new t50[]{t50Var};
            g = t50Var;
        }

        public c(int i) {
            super(40);
        }

        public static c d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                c cVar = new c(e70VarC.c(f).b);
                cVar.b = e70VarC.w(8, false);
                cVar.c = e70VarC.w(16, false);
                cVar.d = qj3.d(e70VarC.t(24, false));
                e70 e70VarT = e70VarC.t(32, false);
                int i = e70VarT.k(-1).b;
                cVar.e = new ml2[i];
                for (int i2 = 0; i2 < i; i2++) {
                    cVar.e[i2] = ml2.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
                return cVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(g);
            qh0VarT.m(this.b, 8, false);
            qh0VarT.m(this.c, 16, false);
            qh0VarT.k(this.d, 24, false);
            ml2[] ml2VarArr = this.e;
            if (ml2VarArr == null) {
                qh0VarT.q(32, false);
                return;
            }
            qh0 qh0VarR = qh0VarT.r(ml2VarArr.length, 32);
            int i = 0;
            while (true) {
                ml2[] ml2VarArr2 = this.e;
                if (i >= ml2VarArr2.length) {
                    return;
                }
                qh0VarR.k(ml2VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: ShareService_Internal.java */
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

        public static d d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                d dVar = new d(e70VarC.c(c).b);
                int iP = e70VarC.p(8);
                dVar.b = iP;
                if (!(iP >= 0 && iP <= 3)) {
                    throw new q80("Invalid enum value.");
                }
                dVar.b = iP;
                return dVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).b(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: ShareService_Internal.java */
    public static class e extends cm2 implements gh1 {
        public final ok2.a e;

        public e(f fVar) {
            this.e = fVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(0, 2)) {
                    return false;
                }
                d dVarD = d.d(wi2VarA.f());
                ((f) this.e).c(Integer.valueOf(dVarD.b));
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: ShareService_Internal.java */
    public static class f implements ok2.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public f(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(Integer num) {
            d dVar = new d(0);
            dVar.b = num.intValue();
            this.f.a(dVar.c(this.e, new yg1(0, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: ShareService_Internal.java */
    public static final class g extends y11.d<ok2> {
        public g(k00 k00Var, ok2 ok2Var) {
            super(k00Var, ok2Var);
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
                    return z11.a(k00Var, uk2.a, wi2VarA, gh1Var);
                }
                if (i != 0) {
                    return false;
                }
                c cVarD = c.d(wi2VarA.f());
                ((ok2) this.f).r(cVarD.b, cVarD.c, cVarD.d, cVarD.e, new f(k00Var, gh1Var, yg1Var.d));
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

    /* JADX INFO: compiled from: ShareService_Internal.java */
    public class a extends y11.b<ok2, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new b(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new g(k00Var, (ok2) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "blink.mojom.ShareService";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
