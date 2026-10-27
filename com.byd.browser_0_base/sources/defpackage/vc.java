package defpackage;

/* JADX INFO: compiled from: BarcodeDetectionResult.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vc extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public String b;
    public y92 c;
    public int d;
    public l22[] e;

    static {
        t50 t50Var = new t50(40, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public vc(int i) {
        super(40);
    }

    public static vc d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            vc vcVar = new vc(e70Var.c(f).b);
            vcVar.b = e70Var.w(8, false);
            vcVar.c = y92.d(e70Var.t(16, false));
            int iP = e70Var.p(24);
            vcVar.d = iP;
            a8.l0(iP);
            vcVar.d = vcVar.d;
            e70 e70VarT = e70Var.t(32, false);
            int i = e70VarT.k(-1).b;
            vcVar.e = new l22[i];
            for (int i2 = 0; i2 < i; i2++) {
                vcVar.e[i2] = l22.d(C0173a.g(i2, 8, 8, e70VarT, false));
            }
            return vcVar;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.k(this.c, 16, false);
        qh0VarT.b(this.d, 24);
        l22[] l22VarArr = this.e;
        if (l22VarArr == null) {
            qh0VarT.q(32, false);
            return;
        }
        qh0 qh0VarR = qh0VarT.r(l22VarArr.length, 32);
        int i = 0;
        while (true) {
            l22[] l22VarArr2 = this.e;
            if (i >= l22VarArr2.length) {
                return;
            }
            qh0VarR.k(l22VarArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public vc() {
        this(0);
    }
}
