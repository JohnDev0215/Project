package defpackage;

import defpackage.y11;

/* JADX INFO: compiled from: AndroidOverlayProvider_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b5 {
    public static final a a = new a();

    /* JADX INFO: compiled from: AndroidOverlayProvider_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] e;
        public static final t50 f;
        public f21<w4> b;
        public x4 c;
        public z4 d;

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
                bVar.b = e70VarC.q(8);
                bVar.c = (x4) e70VarC.u(12, false, y4.a);
                bVar.d = z4.d(e70VarC.t(24, false));
                return bVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(f);
            qh0VarT.j(this.b, 8);
            qh0VarT.i(this.c, 12, false, y4.a);
            qh0VarT.k(this.d, 24, false);
        }
    }

    /* JADX INFO: compiled from: AndroidOverlayProvider_Internal.java */
    public static final class c extends y11.a implements a5 {
        public c(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.a5
        public final void h(f21<w4> f21Var, x4 x4Var, z4 z4Var) {
            b bVar = new b(0);
            bVar.b = f21Var;
            bVar.c = x4Var;
            bVar.d = z4Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(0)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: AndroidOverlayProvider_Internal.java */
    public static final class d extends y11.d<a5> {
        public d(k00 k00Var, a5 a5Var) {
            super(k00Var, a5Var);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                if (yg1Var.b(yg1Var.a(4) ? 5 : 1) && yg1Var.b == -1) {
                    return z11.a(this.e, b5.a, wi2VarA, gh1Var);
                }
                return false;
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
                if (!yg1Var.b(i)) {
                    return false;
                }
                int i2 = yg1Var.b;
                if (i2 != -2) {
                    if (i2 != 0) {
                        return false;
                    }
                    b bVarD = b.d(wi2VarA.f());
                    ((a5) this.f).h(bVarD.b, bVarD.c, bVarD.d);
                    return true;
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

    /* JADX INFO: compiled from: AndroidOverlayProvider_Internal.java */
    public class a extends y11.b<a5, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new c(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new d(k00Var, (a5) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "media.mojom.AndroidOverlayProvider";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
