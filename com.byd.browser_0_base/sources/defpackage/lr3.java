package defpackage;

/* JADX INFO: compiled from: WebPage.java */
/* JADX INFO: loaded from: classes.dex */
public final class lr3 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public qj3 b;
    public String c;
    public uh0[] d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public lr3(int i) {
        super(32);
    }

    public static lr3 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            lr3 lr3Var = new lr3(e70Var.c(e).b);
            lr3Var.b = qj3.d(e70Var.t(8, false));
            lr3Var.c = e70Var.w(16, false);
            e70 e70VarT = e70Var.t(24, false);
            int i = e70VarT.k(-1).b;
            lr3Var.d = new uh0[i];
            for (int i2 = 0; i2 < i; i2++) {
                lr3Var.d[i2] = uh0.d(C0173a.g(i2, 8, 8, e70VarT, false));
            }
            return lr3Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        uh0[] uh0VarArr = this.d;
        if (uh0VarArr == null) {
            qh0VarT.q(24, false);
            return;
        }
        qh0 qh0VarR = qh0VarT.r(uh0VarArr.length, 24);
        int i = 0;
        while (true) {
            uh0[] uh0VarArr2 = this.d;
            if (i >= uh0VarArr2.length) {
                return;
            }
            qh0VarR.k(uh0VarArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public lr3() {
        this(0);
    }
}
