package defpackage;

/* JADX INFO: compiled from: Landmark.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a51 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public l22[] b;
    public int c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public a51(int i) {
        super(24);
    }

    public static a51 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            a51 a51Var = new a51(e70Var.c(d).b);
            boolean z = false;
            e70 e70VarT = e70Var.t(8, false);
            int i = e70VarT.k(-1).b;
            a51Var.b = new l22[i];
            for (int i2 = 0; i2 < i; i2++) {
                a51Var.b[i2] = l22.d(C0173a.g(i2, 8, 8, e70VarT, false));
            }
            int iP = e70Var.p(16);
            a51Var.c = iP;
            if (iP >= 0 && iP <= 2) {
                z = true;
            }
            if (!z) {
                throw new q80("Invalid enum value.");
            }
            a51Var.c = iP;
            return a51Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        l22[] l22VarArr = this.b;
        if (l22VarArr != null) {
            qh0 qh0VarR = qh0VarT.r(l22VarArr.length, 8);
            int i = 0;
            while (true) {
                l22[] l22VarArr2 = this.b;
                if (i >= l22VarArr2.length) {
                    break;
                }
                qh0VarR.k(l22VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            qh0VarT.q(8, false);
        }
        qh0VarT.b(this.c, 16);
    }

    public a51() {
        this(0);
    }
}
