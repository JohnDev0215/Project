package defpackage;

import defpackage.kj0;
import defpackage.y11;

/* JADX INFO: compiled from: FaceDetection_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sj0 {
    public static final a a = new a();

    /* JADX INFO: compiled from: FaceDetection_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public xe b;

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

    /* JADX INFO: compiled from: FaceDetection_Internal.java */
    public static final class c extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public rj0[] b;

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
                cVar.b = new rj0[i];
                for (int i2 = 0; i2 < i; i2++) {
                    cVar.b[i2] = rj0.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
                return cVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(d);
            rj0[] rj0VarArr = this.b;
            if (rj0VarArr == null) {
                qh0VarT.q(8, false);
                return;
            }
            qh0 qh0VarR = qh0VarT.r(rj0VarArr.length, 8);
            int i = 0;
            while (true) {
                rj0[] rj0VarArr2 = this.b;
                if (i >= rj0VarArr2.length) {
                    return;
                }
                qh0VarR.k(rj0VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: FaceDetection_Internal.java */
    public static class d extends cm2 implements gh1 {
        public final kj0.a e;

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

    /* JADX INFO: compiled from: FaceDetection_Internal.java */
    public static class e implements kj0.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public e(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(rj0[] rj0VarArr) {
            c cVar = new c(0);
            cVar.b = rj0VarArr;
            this.f.a(cVar.c(this.e, new yg1(0, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: FaceDetection_Internal.java */
    public static final class f extends y11.a implements kj0 {
        public f(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.kj0
        public final void D0(xe xeVar, e eVar) {
            b bVar = new b(0);
            bVar.b = xeVar;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(bVar.c(c0171a.e, new yg1(0, 1, 0L)), new d(eVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: FaceDetection_Internal.java */
    public static final class g extends y11.d<kj0> {
        public g(k00 k00Var, kj0 kj0Var) {
            super(k00Var, kj0Var);
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
                    return z11.a(k00Var, sj0.a, wi2VarA, gh1Var);
                }
                if (i != 0) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(b.c);
                    xe xeVarD = xe.d(e70Var.t(8, false));
                    e70Var.a();
                    ((kj0) this.f).D0(xeVarD, new e(k00Var, gh1Var, yg1Var.d));
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

    /* JADX INFO: compiled from: FaceDetection_Internal.java */
    public class a extends y11.b<kj0, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new f(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new g(k00Var, (kj0) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "shape_detection.mojom.FaceDetection";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
