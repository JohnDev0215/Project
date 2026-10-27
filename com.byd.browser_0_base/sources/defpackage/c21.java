package defpackage;

import defpackage.y11;

/* JADX INFO: compiled from: InterfaceProvider_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c21 {
    public static final a a = new a();

    /* JADX INFO: compiled from: InterfaceProvider_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public String b;
        public ch1 c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public b(int i) {
            super(24);
            this.c = l21.e;
        }

        public static b d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                b bVar = new b(e70VarC.c(d).b);
                bVar.b = e70VarC.w(8, false);
                bVar.c = e70VarC.x(16, false).S();
                return bVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.m(this.b, 8, false);
            qh0VarT.h(this.c, 16, false);
        }
    }

    /* JADX INFO: compiled from: InterfaceProvider_Internal.java */
    public static final class c extends y11.a implements b21 {
        public c(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.b21
        public final void O(String str, ch1 ch1Var) {
            b bVar = new b(0);
            bVar.b = str;
            bVar.c = ch1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(0)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: InterfaceProvider_Internal.java */
    public static final class d extends y11.d<b21> {
        public d(k00 k00Var, b21 b21Var) {
            super(k00Var, b21Var);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                if (yg1Var.b(yg1Var.a(4) ? 5 : 1) && yg1Var.b == -1) {
                    return z11.a(this.e, c21.a, wi2VarA, gh1Var);
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
                    ((b21) this.f).O(bVarD.b, bVarD.c);
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

    /* JADX INFO: compiled from: InterfaceProvider_Internal.java */
    public class a extends y11.b<b21, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new c(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new d(k00Var, (b21) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "service_manager.mojom.InterfaceProvider";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
