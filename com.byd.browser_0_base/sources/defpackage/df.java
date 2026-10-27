package defpackage;

import defpackage.y11;

/* JADX INFO: compiled from: BlobReaderClient_Internal.java */
/* JADX INFO: loaded from: classes.dex */
public final class df {
    public static final a a = new a();

    /* JADX INFO: compiled from: BlobReaderClient_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public long b;
        public long c;

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
                bVar.b = e70VarC.s(8);
                bVar.c = e70VarC.s(16);
                return bVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.f(8, this.b);
            qh0VarT.f(16, this.c);
        }
    }

    /* JADX INFO: compiled from: BlobReaderClient_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public int b;
        public long c;

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
                cVar.c = e70VarC.s(16);
                return cVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.b(this.b, 8);
            qh0VarT.f(16, this.c);
        }
    }

    /* JADX INFO: compiled from: BlobReaderClient_Internal.java */
    public static final class d extends y11.a implements cf {
        public d(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.cf
        public final void K0(int i, long j) {
            c cVar = new c(0);
            cVar.b = i;
            cVar.c = j;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(cVar.c(c0171a.e, new yg1(1)));
        }

        @Override // defpackage.cf
        public final void X(long j, long j2) {
            b bVar = new b(0);
            bVar.b = j;
            bVar.c = j2;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(0)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: BlobReaderClient_Internal.java */
    public static final class e extends y11.d<cf> {
        public e(k00 k00Var, cf cfVar) {
            super(k00Var, cfVar);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                if (yg1Var.b(yg1Var.a(4) ? 5 : 1) && yg1Var.b == -1) {
                    return z11.a(this.e, df.a, wi2VarA, gh1Var);
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
                if (i2 == -2) {
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
                }
                Object obj = this.f;
                if (i2 == 0) {
                    b bVarD = b.d(wi2VarA.f());
                    ((cf) obj).X(bVarD.b, bVarD.c);
                    return true;
                }
                if (i2 != 1) {
                    return false;
                }
                c cVarD = c.d(wi2VarA.f());
                ((cf) obj).K0(cVarD.b, cVarD.c);
                return true;
            } catch (q80 e) {
                System.err.println(e.toString());
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: BlobReaderClient_Internal.java */
    public class a extends y11.b<cf, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new d(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new e(k00Var, (cf) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "blink.mojom.BlobReaderClient";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
