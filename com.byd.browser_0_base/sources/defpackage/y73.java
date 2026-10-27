package defpackage;

/* JADX INFO: compiled from: TextDetectionResult.java */
/* JADX INFO: loaded from: classes3.dex */
public final class y73 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public String b;
    public y92 c;
    public l22[] d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public y73(int i) {
        super(32);
    }

    public static y73 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            y73 y73Var = new y73(e70Var.c(e).b);
            y73Var.b = e70Var.w(8, false);
            y73Var.c = y92.d(e70Var.t(16, false));
            e70 e70VarT = e70Var.t(24, false);
            int i = e70VarT.k(-1).b;
            y73Var.d = new l22[i];
            for (int i2 = 0; i2 < i; i2++) {
                y73Var.d[i2] = l22.d(C0173a.g(i2, 8, 8, e70VarT, false));
            }
            return y73Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.k(this.c, 16, false);
        l22[] l22VarArr = this.d;
        if (l22VarArr == null) {
            qh0VarT.q(24, false);
            return;
        }
        qh0 qh0VarR = qh0VarT.r(l22VarArr.length, 24);
        int i = 0;
        while (true) {
            l22[] l22VarArr2 = this.d;
            if (i >= l22VarArr2.length) {
                return;
            }
            qh0VarR.k(l22VarArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public y73() {
        this(0);
    }
}
