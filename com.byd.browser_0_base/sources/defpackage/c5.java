package defpackage;

import defpackage.y11;
import org.chromium.gfx.mojom.Rect;

/* JADX INFO: compiled from: AndroidOverlay_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c5 {
    public static final a a = new a();

    /* JADX INFO: compiled from: AndroidOverlay_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public Rect b;

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

    /* JADX INFO: compiled from: AndroidOverlay_Internal.java */
    public static final class c extends y11.a implements w4 {
        public c(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.w4
        public final void f(Rect rect) {
            b bVar = new b(0);
            bVar.b = rect;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(0)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: AndroidOverlay_Internal.java */
    public static final class d extends y11.d<w4> {
        public d(k00 k00Var, w4 w4Var) {
            super(k00Var, w4Var);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                if (yg1Var.b(yg1Var.a(4) ? 5 : 1) && yg1Var.b == -1) {
                    return z11.a(this.e, c5.a, wi2VarA, gh1Var);
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
            if (i2 != 0) {
                return false;
            }
            e70Var = new e70(wi2VarA.f());
            e70Var.b();
            try {
                e70Var.c(b.c);
                Rect rectD = Rect.d(e70Var.t(8, false));
                e70Var.a();
                ((w4) this.f).f(rectD);
                return true;
            } finally {
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: AndroidOverlay_Internal.java */
    public class a extends y11.b<w4, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new c(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new d(k00Var, (w4) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "media.mojom.AndroidOverlay";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
