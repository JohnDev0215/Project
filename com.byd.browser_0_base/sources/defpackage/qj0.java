package defpackage;

import defpackage.y11;

/* JADX INFO: compiled from: FaceDetectionProvider_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qj0 {
    public static final a a = new a();

    /* JADX INFO: compiled from: FaceDetectionProvider_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public f21<kj0> b;
        public tj0 c;

        static {
            t50 t50Var = new t50(24, 0);
            d = new t50[]{t50Var};
            e = t50Var;
        }

        public b(int i) {
            super(24);
        }

        public static b d(gq0 gq0Var) {
            tj0 tj0Var;
            e70 e70VarC = gq2.c(gq0Var);
            try {
                b bVar = new b(e70VarC.c(d).b);
                bVar.b = e70VarC.q(8);
                e70 e70VarT = e70VarC.t(16, false);
                if (e70VarT == null) {
                    tj0Var = null;
                } else {
                    e70VarT.b();
                    try {
                        tj0 tj0Var2 = new tj0(e70VarT.c(tj0.d).b);
                        tj0Var2.b = e70VarT.p(8);
                        tj0Var2.c = e70VarT.d(12, 0);
                        e70VarT.a();
                        tj0Var = tj0Var2;
                    } catch (Throwable th) {
                        e70VarT.a();
                        throw th;
                    }
                }
                bVar.c = tj0Var;
                return bVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(e);
            qh0VarT.j(this.b, 8);
            qh0VarT.k(this.c, 16, false);
        }
    }

    /* JADX INFO: compiled from: FaceDetectionProvider_Internal.java */
    public static final class c extends y11.a implements oj0 {
        public c(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.oj0
        public final void g0(f21<kj0> f21Var, tj0 tj0Var) {
            b bVar = new b(0);
            bVar.b = f21Var;
            bVar.c = tj0Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(0)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: FaceDetectionProvider_Internal.java */
    public static final class d extends y11.d<oj0> {
        public d(k00 k00Var, oj0 oj0Var) {
            super(k00Var, oj0Var);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                if (yg1Var.b(yg1Var.a(4) ? 5 : 1) && yg1Var.b == -1) {
                    return z11.a(this.e, qj0.a, wi2VarA, gh1Var);
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
                    ((oj0) this.f).g0(bVarD.b, bVarD.c);
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

    /* JADX INFO: compiled from: FaceDetectionProvider_Internal.java */
    public class a extends y11.b<oj0, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new c(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new d(k00Var, (oj0) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "shape_detection.mojom.FaceDetectionProvider";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
