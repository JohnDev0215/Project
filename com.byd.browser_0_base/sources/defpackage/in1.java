package defpackage;

import defpackage.y11;

/* JADX INFO: compiled from: NfcProvider_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class in1 {
    public static final a a = new a();

    /* JADX INFO: compiled from: NfcProvider_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public int b;
        public f21<an1> c;

        static {
            t50 t50Var = new t50(16, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public b(int i) {
            super(16);
        }

        public static b d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                b bVar = new b(e70VarC.c(d).b);
                bVar.b = e70VarC.p(8);
                bVar.c = e70VarC.q(12);
                return bVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.b(this.b, 8);
            qh0VarT.j(this.c, 12);
        }
    }

    /* JADX INFO: compiled from: NfcProvider_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public c(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: NfcProvider_Internal.java */
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

    /* JADX INFO: compiled from: NfcProvider_Internal.java */
    public static final class e extends y11.a implements gn1 {
        public e(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.gn1
        public final void C0(int i, f21<an1> f21Var) {
            b bVar = new b(0);
            bVar.b = i;
            bVar.c = f21Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(0)));
        }

        @Override // defpackage.gn1
        public final void E0() {
            c cVar = new c(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(cVar.c(c0171a.e, new yg1(2)));
        }

        @Override // defpackage.gn1
        public final void l0() {
            d dVar = new d(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(dVar.c(c0171a.e, new yg1(1)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: NfcProvider_Internal.java */
    public static final class f extends y11.d<gn1> {
        public f(k00 k00Var, gn1 gn1Var) {
            super(k00Var, gn1Var);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                if (yg1Var.b(yg1Var.a(4) ? 5 : 1) && yg1Var.b == -1) {
                    return z11.a(this.e, in1.a, wi2VarA, gh1Var);
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
                b bVarD = b.d(wi2VarA.f());
                ((gn1) obj).C0(bVarD.b, bVarD.c);
                return true;
            }
            if (i2 == 1) {
                e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(d.b);
                    e70Var.a();
                    ((gn1) obj).l0();
                    return true;
                } finally {
                }
            }
            if (i2 != 2) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(c.b);
                e70Var.a();
                ((gn1) obj).E0();
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: NfcProvider_Internal.java */
    public class a extends y11.b<gn1, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new e(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new f(k00Var, (gn1) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "device.mojom.NFCProvider";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
