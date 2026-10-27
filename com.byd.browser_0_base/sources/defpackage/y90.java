package defpackage;

import defpackage.w90;
import defpackage.y11;

/* JADX INFO: compiled from: DigitalGoodsFactory_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class y90 {
    public static final a a = new a();

    /* JADX INFO: compiled from: DigitalGoodsFactory_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public String b;

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
            qh0Var.t(d).m(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: DigitalGoodsFactory_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public int b;
        public t90 c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public c(int i) {
            super(24);
        }

        public static c d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                c cVar = new c(e70VarC.c(d).b);
                cVar.b = e70VarC.p(8);
                cVar.c = (t90) e70VarC.u(12, true, aa0.a);
                return cVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.b(this.b, 8);
            qh0VarT.i(this.c, 12, true, aa0.a);
        }
    }

    /* JADX INFO: compiled from: DigitalGoodsFactory_Internal.java */
    public static class d extends cm2 implements gh1 {
        public final w90.a e;

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
                ((e) this.e).a(Integer.valueOf(cVarD.b), cVarD.c);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: DigitalGoodsFactory_Internal.java */
    public static class e implements w90.a {
        public final k00 a;
        public final gh1 b;
        public final long c;

        public e(k00 k00Var, gh1 gh1Var, long j) {
            this.a = k00Var;
            this.b = gh1Var;
            this.c = j;
        }

        public final void a(Object obj, Object obj2) {
            c cVar = new c(0);
            cVar.b = ((Integer) obj).intValue();
            cVar.c = (t90) obj2;
            this.b.a(cVar.c(this.a, new yg1(0, 2, this.c)));
        }
    }

    /* JADX INFO: compiled from: DigitalGoodsFactory_Internal.java */
    public static final class f extends y11.a implements w90 {
        public f(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.w90
        public final void W0(String str, e eVar) {
            b bVar = new b(0);
            bVar.b = str;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(bVar.c(c0171a.e, new yg1(0, 1, 0L)), new d(eVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: DigitalGoodsFactory_Internal.java */
    public static final class g extends y11.d<w90> {
        public g(k00 k00Var, w90 w90Var) {
            super(k00Var, w90Var);
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
                    return z11.a(k00Var, y90.a, wi2VarA, gh1Var);
                }
                if (i != 0) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(b.c);
                    String strW = e70Var.w(8, false);
                    e70Var.a();
                    ((w90) this.f).W0(strW, new e(k00Var, gh1Var, yg1Var.d));
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

    /* JADX INFO: compiled from: DigitalGoodsFactory_Internal.java */
    public class a extends y11.b<w90, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new f(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new g(k00Var, (w90) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "payments.mojom.DigitalGoodsFactory";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
