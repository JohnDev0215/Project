package defpackage;

import defpackage.w73;
import defpackage.y11;

/* JADX INFO: compiled from: TextDetection_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class z73 {
    public static final a a = new a();

    /* JADX INFO: compiled from: TextDetection_Internal.java */
    public static final class b extends y11.a implements w73 {
        public b(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.w73
        public final void X0(xe xeVar, g gVar) {
            d dVar = new d(0);
            dVar.b = xeVar;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.R0(dVar.c(c0171a.e, new yg1(0, 1, 0L)), new f(gVar));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: TextDetection_Internal.java */
    public static final class c extends y11.d<w73> {
        public c(k00 k00Var, w73 w73Var) {
            super(k00Var, w73Var);
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
                    return z11.a(k00Var, z73.a, wi2VarA, gh1Var);
                }
                if (i != 0) {
                    return false;
                }
                e70 e70Var = new e70(wi2VarA.f());
                e70Var.b();
                try {
                    e70Var.c(d.c);
                    xe xeVarD = xe.d(e70Var.t(8, false));
                    e70Var.a();
                    ((w73) this.f).X0(xeVarD, new g(k00Var, gh1Var, yg1Var.d));
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

    /* JADX INFO: compiled from: TextDetection_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public xe b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public d(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: TextDetection_Internal.java */
    public static final class e extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public y73[] b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public e(int i) {
            super(16);
        }

        public static e d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                e eVar = new e(e70VarC.c(c).b);
                e70 e70VarT = e70VarC.t(8, false);
                int i = e70VarT.k(-1).b;
                eVar.b = new y73[i];
                for (int i2 = 0; i2 < i; i2++) {
                    eVar.b[i2] = y73.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
                return eVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(d);
            y73[] y73VarArr = this.b;
            if (y73VarArr == null) {
                qh0VarT.q(8, false);
                return;
            }
            qh0 qh0VarR = qh0VarT.r(y73VarArr.length, 8);
            int i = 0;
            while (true) {
                y73[] y73VarArr2 = this.b;
                if (i >= y73VarArr2.length) {
                    return;
                }
                qh0VarR.k(y73VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: TextDetection_Internal.java */
    public static class f extends cm2 implements gh1 {
        public final w73.a e;

        public f(g gVar) {
            this.e = gVar;
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                if (!wi2VarA.d.c(0, 2)) {
                    return false;
                }
                e eVarD = e.d(wi2VarA.f());
                ((g) this.e).c(eVarD.b);
                return true;
            } catch (q80 unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: TextDetection_Internal.java */
    public static class g implements w73.a {
        public final k00 e;
        public final gh1 f;
        public final long g;

        public g(k00 k00Var, gh1 gh1Var, long j) {
            this.e = k00Var;
            this.f = gh1Var;
            this.g = j;
        }

        @Override // defpackage.sl
        public final void c(y73[] y73VarArr) {
            e eVar = new e(0);
            eVar.b = y73VarArr;
            this.f.a(eVar.c(this.e, new yg1(0, 2, this.g)));
        }
    }

    /* JADX INFO: compiled from: TextDetection_Internal.java */
    public class a extends y11.b<w73, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new b(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new c(k00Var, (w73) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "shape_detection.mojom.TextDetection";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
