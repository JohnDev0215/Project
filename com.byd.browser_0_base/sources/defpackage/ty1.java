package defpackage;

/* JADX INFO: compiled from: PaymentDetailsModifier.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ty1 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public kz1 b;
    public kz1[] c;
    public lz1 d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public ty1(int i) {
        super(32);
    }

    public static ty1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ty1 ty1Var = new ty1(e70Var.c(e).b);
            ty1Var.b = kz1.d(e70Var.t(8, true));
            e70 e70VarT = e70Var.t(16, false);
            int i = e70VarT.k(-1).b;
            ty1Var.c = new kz1[i];
            for (int i2 = 0; i2 < i; i2++) {
                ty1Var.c[i2] = kz1.d(C0173a.g(i2, 8, 8, e70VarT, false));
            }
            ty1Var.d = lz1.d(e70Var.t(24, false));
            return ty1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
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
            qh0VarT.q(16, false);
        }
        qh0VarT.k(this.d, 24, false);
    }

    public ty1() {
        this(0);
    }
}
