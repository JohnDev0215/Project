package defpackage;

import defpackage.y11;

/* JADX INFO: compiled from: PaymentRequest_Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uz1 {
    public static final a a = new a();

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class b extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public b(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
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

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class d extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public int b;

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
                int iP = e70VarC.p(8);
                dVar.b = iP;
                if (!(iP >= 0 && iP <= 2)) {
                    throw new q80("Invalid enum value.");
                }
                dVar.b = iP;
                return dVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).b(this.b, 8);
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class e extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public e(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class f extends hv2 {
        public static final t50[] f;
        public static final t50 g;
        public pz1 b;
        public lz1[] c;
        public sy1 d;
        public nz1 e;

        static {
            t50 t50Var = new t50(40, 0);
            f = new t50[]{t50Var};
            g = t50Var;
        }

        public f(int i) {
            super(40);
        }

        public static f d(gq0 gq0Var) {
            e70 e70VarC = gq2.c(gq0Var);
            try {
                f fVar = new f(e70VarC.c(f).b);
                fVar.b = (pz1) e70VarC.u(8, false, qz1.a);
                e70 e70VarT = e70VarC.t(16, false);
                int i = e70VarT.k(-1).b;
                fVar.c = new lz1[i];
                for (int i2 = 0; i2 < i; i2++) {
                    fVar.c[i2] = lz1.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
                fVar.d = sy1.d(e70VarC.t(24, false));
                fVar.e = nz1.d(e70VarC.t(32, false));
                return fVar;
            } finally {
                e70VarC.a();
            }
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0 qh0VarT = qh0Var.t(g);
            qh0VarT.i(this.b, 8, false, qz1.a);
            lz1[] lz1VarArr = this.c;
            if (lz1VarArr != null) {
                qh0 qh0VarR = qh0VarT.r(lz1VarArr.length, 16);
                int i = 0;
                while (true) {
                    lz1[] lz1VarArr2 = this.c;
                    if (i >= lz1VarArr2.length) {
                        break;
                    }
                    qh0VarR.k(lz1VarArr2[i], (i * 8) + 8, false);
                    i++;
                }
            } else {
                qh0VarT.q(16, false);
            }
            qh0VarT.k(this.d, 24, false);
            qh0VarT.k(this.e, 32, false);
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class g extends hv2 {
        public static final t50[] b;
        public static final t50 c;

        static {
            t50 t50Var = new t50(8, 0);
            b = new t50[]{t50Var};
            c = t50Var;
        }

        public g(int i) {
            super(8);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(c);
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class h extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public xz1 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public h(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class i extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public boolean b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public i(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).d(8, 0, this.b);
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class j extends hv2 {
        public static final t50[] c;
        public static final t50 d;
        public sy1 b;

        static {
            t50 t50Var = new t50(16, 0);
            c = new t50[]{t50Var};
            d = t50Var;
        }

        public j(int i) {
            super(16);
        }

        @Override // defpackage.hv2
        public final void a(qh0 qh0Var) {
            qh0Var.t(d).k(this.b, 8, false);
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class k extends y11.a implements oz1 {
        public k(k00 k00Var, w9 w9Var) {
            super(k00Var, w9Var);
        }

        @Override // defpackage.oz1
        public final void L0(int i) {
            d dVar = new d(0);
            dVar.b = i;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(dVar.c(c0171a.e, new yg1(5)));
        }

        @Override // defpackage.oz1
        public final void M() {
            b bVar = new b(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(bVar.c(c0171a.e, new yg1(4)));
        }

        @Override // defpackage.oz1
        public final void c(boolean z) {
            i iVar = new i(0);
            iVar.b = z;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(iVar.c(c0171a.e, new yg1(1)));
        }

        @Override // defpackage.oz1
        public final void h0() {
            e eVar = new e(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(eVar.c(c0171a.e, new yg1(8)));
        }

        @Override // defpackage.oz1
        public final void j0(xz1 xz1Var) {
            h hVar = new h(0);
            hVar.b = xz1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(hVar.c(c0171a.e, new yg1(6)));
        }

        @Override // defpackage.oz1
        public final void t0(pz1 pz1Var, lz1[] lz1VarArr, sy1 sy1Var, nz1 nz1Var) {
            f fVar = new f(0);
            fVar.b = pz1Var;
            fVar.c = lz1VarArr;
            fVar.d = sy1Var;
            fVar.e = nz1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(fVar.c(c0171a.e, new yg1(0)));
        }

        @Override // y11.c
        public final y11.a.C0171a u0() {
            return this.e;
        }

        @Override // defpackage.oz1
        public final void w() {
            c cVar = new c(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(cVar.c(c0171a.e, new yg1(7)));
        }

        @Override // defpackage.oz1
        public final void y() {
            g gVar = new g(0);
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(gVar.c(c0171a.e, new yg1(3)));
        }

        @Override // defpackage.oz1
        public final void z(sy1 sy1Var) {
            j jVar = new j(0);
            jVar.b = sy1Var;
            y11.a.C0171a c0171a = this.e;
            c0171a.f.a(jVar.c(c0171a.e, new yg1(2)));
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public static final class l extends y11.d<oz1> {
        public l(k00 k00Var, oz1 oz1Var) {
            super(k00Var, oz1Var);
        }

        @Override // defpackage.hh1
        public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
            try {
                wi2 wi2VarA = gq0Var.a();
                yg1 yg1Var = wi2VarA.d;
                if (yg1Var.b(yg1Var.a(4) ? 5 : 1) && yg1Var.b == -1) {
                    return z11.a(this.e, uz1.a, wi2VarA, gh1Var);
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
            Object obj = this.f;
            switch (i2) {
                case -2:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(ao0.d);
                        ad2 ad2VarB = ad2.b(e70Var);
                        e70Var.a();
                        return ad2VarB.a == 0 && ad2VarB.b.b <= 0;
                    } finally {
                    }
                case -1:
                default:
                    return false;
                case 0:
                    f fVarD = f.d(wi2VarA.f());
                    ((oz1) obj).t0(fVarD.b, fVarD.c, fVarD.d, fVarD.e);
                    return true;
                case 1:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(i.c);
                        boolean zD = e70Var.d(8, 0);
                        e70Var.a();
                        ((oz1) obj).c(zD);
                        return true;
                    } finally {
                    }
                case 2:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(j.c);
                        sy1 sy1VarD = sy1.d(e70Var.t(8, false));
                        e70Var.a();
                        ((oz1) obj).z(sy1VarD);
                        return true;
                    } finally {
                    }
                case 3:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(g.b);
                        e70Var.a();
                        ((oz1) obj).y();
                        return true;
                    } finally {
                    }
                case 4:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(b.b);
                        e70Var.a();
                        ((oz1) obj).M();
                        return true;
                    } finally {
                    }
                case 5:
                    ((oz1) obj).L0(d.d(wi2VarA.f()).b);
                    return true;
                case 6:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(h.c);
                        xz1 xz1VarD = xz1.d(e70Var.t(8, false));
                        e70Var.a();
                        ((oz1) obj).j0(xz1VarD);
                        return true;
                    } finally {
                    }
                case 7:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(c.b);
                        e70Var.a();
                        ((oz1) obj).w();
                        return true;
                    } finally {
                    }
                case 8:
                    e70Var = new e70(wi2VarA.f());
                    e70Var.b();
                    try {
                        e70Var.c(e.b);
                        e70Var.a();
                        ((oz1) obj).h0();
                        return true;
                    } finally {
                    }
            }
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: compiled from: PaymentRequest_Internal.java */
    public class a extends y11.b<oz1, Object> {
        @Override // y11.b
        public final y11.c d(k00 k00Var, w9 w9Var) {
            return new k(k00Var, w9Var);
        }

        @Override // y11.b
        public final y11.d e(k00 k00Var, y11 y11Var) {
            return new l(k00Var, (oz1) y11Var);
        }

        @Override // y11.b
        public final String f() {
            return "payments.mojom.PaymentRequest";
        }

        @Override // y11.b
        public final void g() {
        }
    }
}
