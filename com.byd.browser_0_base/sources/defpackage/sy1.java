package defpackage;

/* JADX INFO: compiled from: PaymentDetails.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sy1 extends hv2 {
    public static final t50[] k;
    public static final t50 l;
    public kz1 b;
    public kz1[] c;
    public wz1[] d;
    public ty1[] e;
    public String f;
    public d4 g;
    public String h;
    public String i;
    public boolean j;

    static {
        t50 t50Var = new t50(80, 0);
        k = new t50[]{t50Var};
        l = t50Var;
    }

    public sy1(int i) {
        super(80);
        this.f = "";
        this.j = false;
    }

    public static sy1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            sy1 sy1Var = new sy1(e70Var.c(k).b);
            sy1Var.b = kz1.d(e70Var.t(8, true));
            e70 e70VarT = e70Var.t(16, true);
            if (e70VarT == null) {
                sy1Var.c = null;
            } else {
                int i = e70VarT.k(-1).b;
                sy1Var.c = new kz1[i];
                for (int i2 = 0; i2 < i; i2++) {
                    sy1Var.c[i2] = kz1.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
            }
            e70 e70VarT2 = e70Var.t(24, true);
            if (e70VarT2 == null) {
                sy1Var.d = null;
            } else {
                int i3 = e70VarT2.k(-1).b;
                sy1Var.d = new wz1[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    sy1Var.d[i4] = wz1.d(C0173a.g(i4, 8, 8, e70VarT2, false));
                }
            }
            e70 e70VarT3 = e70Var.t(32, true);
            if (e70VarT3 == null) {
                sy1Var.e = null;
            } else {
                int i5 = e70VarT3.k(-1).b;
                sy1Var.e = new ty1[i5];
                for (int i6 = 0; i6 < i5; i6++) {
                    sy1Var.e[i6] = ty1.d(C0173a.g(i6, 8, 8, e70VarT3, false));
                }
            }
            sy1Var.f = e70Var.w(40, false);
            sy1Var.g = d4.d(e70Var.t(48, true));
            sy1Var.h = e70Var.w(56, true);
            sy1Var.i = e70Var.w(64, true);
            sy1Var.j = e70Var.d(72, 0);
            return sy1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(l);
        qh0VarT.k(this.b, 8, true);
        kz1[] kz1VarArr = this.c;
        if (kz1VarArr != null) {
            qh0 qh0VarR = qh0VarT.r(kz1VarArr.length, 16);
            int i = 0;
            while (true) {
                kz1[] kz1VarArr2 = this.c;
                if (i >= kz1VarArr2.length) {
                    break;
                }
                qh0VarR.k(kz1VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            qh0VarT.q(16, true);
        }
        wz1[] wz1VarArr = this.d;
        if (wz1VarArr != null) {
            qh0 qh0VarR2 = qh0VarT.r(wz1VarArr.length, 24);
            int i2 = 0;
            while (true) {
                wz1[] wz1VarArr2 = this.d;
                if (i2 >= wz1VarArr2.length) {
                    break;
                }
                qh0VarR2.k(wz1VarArr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            qh0VarT.q(24, true);
        }
        ty1[] ty1VarArr = this.e;
        if (ty1VarArr != null) {
            qh0 qh0VarR3 = qh0VarT.r(ty1VarArr.length, 32);
            int i3 = 0;
            while (true) {
                ty1[] ty1VarArr2 = this.e;
                if (i3 >= ty1VarArr2.length) {
                    break;
                }
                qh0VarR3.k(ty1VarArr2[i3], (i3 * 8) + 8, false);
                i3++;
            }
        } else {
            qh0VarT.q(32, true);
        }
        qh0VarT.m(this.f, 40, false);
        qh0VarT.k(this.g, 48, true);
        qh0VarT.m(this.h, 56, true);
        qh0VarT.m(this.i, 64, true);
        qh0VarT.d(72, 0, this.j);
    }
}
