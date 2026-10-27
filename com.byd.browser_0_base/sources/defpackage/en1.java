package defpackage;

import defpackage.y11;

/* JADX INFO: compiled from: NfcClient_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class en1 {
    public static final a a = new a();

    /* JADX INFO: compiled from: NfcClient_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public ll1 b;

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
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: NfcClient_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] e;
        public static final t50 f;
        public int[] b;
        public String c;
        public ml1 d;

        static {
            t50 t50Var = new t50(32, 0);
            e = new t50[]{t50Var};
            f = t50Var;
        }

        public c(int i) {
            super(32);
        }

        public static c d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                c cVar = new c(e70VarC.c(e).b);
                cVar.b = e70VarC.r(8, 0);
                cVar.c = e70VarC.w(16, true);
                cVar.d = ml1.d(e70VarC.t(24, false));
                return cVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(f);
            qh0VarT.o(this.b, 8, 0);
            qh0VarT.m(this.c, 16, true);
            qh0VarT.k(this.d, 24, false);
        }
    }

    /* JADX INFO: compiled from: NfcClient_Internal.java */
    public static final class d extends y11.a implements dn1 {
        public d(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.dn1
        public final void e0(int[] iArr, String str, ml1 ml1Var) {
            c cVar = new c(0);
            cVar.b = iArr;
            cVar.c = str;
            cVar.d = ml1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(cVar.c(c0171a.e, new yg1(0)));
        }

        @Override // defpackage.dn1
        public final void q0(ll1 ll1Var) {
            b bVar = new b(0);
            bVar.b = ll1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(1)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: NfcClient_Internal.java */
    public static final class e extends y11.d<dn1> {
        public e(k00 k00Var, dn1 dn1Var) {
            super(k00Var, dn1Var);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                if (yg1Var.b(yg1Var.a(4) ? 5 : 1) && yg1Var.b == -1) {
                    return z11.a(this.e, en1.a, wi2VarA, gh1Var);
                }
                return false;
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
            if (i2 == 0) {
                c cVarD = c.d(wi2VarA.f());
                ((dn1) obj).e0(cVarD.b, cVarD.c, cVarD.d);
                return true;
            }
            if (i2 != 1) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(b.c);
                ll1 ll1VarD = ll1.d(e70Var.t(8, false));
                e70Var.a();
                ((dn1) obj).q0(ll1VarD);
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: NfcClient_Internal.java */
    public class a extends y11.b<dn1, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new d(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new e(k00Var, (dn1) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "device.mojom.NFCClient";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
