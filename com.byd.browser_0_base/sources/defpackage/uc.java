package defpackage;

import defpackage.sc;
import defpackage.y11;

/* JADX INFO: compiled from: BarcodeDetectionProvider_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uc {
    public static final a a = new a();

    /* JADX INFO: compiled from: BarcodeDetectionProvider_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] d;
        public static final t50 e;
        public f21<qc> b;
        public xc c;

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
                bVar.b = e70VarC.q(8);
                bVar.c = xc.d(e70VarC.t(16, false));
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

    /* JADX INFO: compiled from: BarcodeDetectionProvider_Internal.java */
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

    /* JADX INFO: compiled from: BarcodeDetectionProvider_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public int[] b;

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
                int i = 0;
                dVar.b = e70VarC.r(8, 0);
                while (true) {
                    int[] iArr = dVar.b;
                    if (i >= iArr.length) {
                        return dVar;
                    }
                    a8.l0(iArr[i]);
                    i++;
                }
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).o(this.b, 8, 0);
        }
    }

    /* JADX INFO: compiled from: BarcodeDetectionProvider_Internal.java */
    public static class e extends cm2 implements gh1 {
        public final sc.a e;

        public e(f fVar) {
            this.e = fVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(1, 2)) {
                    return false;
                }
                d dVarD = d.d(wi2VarA.f());
                ((f) this.e).c(dVarD.b);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: BarcodeDetectionProvider_Internal.java */
    public static class f implements sc.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public f(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(int[] iArr) {
            d dVar = new d(0);
            dVar.b = iArr;
            this.f.a(dVar.c(this.e, new yg1(1, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: BarcodeDetectionProvider_Internal.java */
    public static final class g extends y11.a implements sc {
        public g(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.sc
        public final void N(f21<qc> f21Var, xc xcVar) {
            b bVar = new b(0);
            bVar.b = f21Var;
            bVar.c = xcVar;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(0)));
        }

        @Override // defpackage.sc
        public final void m(f fVar) {
            c cVar = new c(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(cVar.c(c0171a.e, new yg1(1, 1, 0L)), new e(fVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: BarcodeDetectionProvider_Internal.java */
    public static final class h extends y11.d<sc> {
        public h(k00 k00Var, sc scVar) {
            super(k00Var, scVar);
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
                    return z11.a(k00Var, uc.a, wi2VarA, gh1Var);
                }
                if (i != 1) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(c.b);
                    e70Var.a();
                    ((sc) this.f).m(new f(k00Var, gh1Var, yg1Var.d));
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
                if (!yg1Var.b(i)) {
                    return false;
                }
                int i2 = yg1Var.b;
                if (i2 != -2) {
                    if (i2 != 0) {
                        return false;
                    }
                    b bVarD = b.d(wi2VarA.f());
                    ((sc) this.f).N(bVarD.b, bVarD.c);
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

    /* JADX INFO: compiled from: BarcodeDetectionProvider_Internal.java */
    public class a extends y11.b<sc, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new g(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new h(k00Var, (sc) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "shape_detection.mojom.BarcodeDetectionProvider";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
