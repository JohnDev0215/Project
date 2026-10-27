package defpackage;

/* JADX INFO: compiled from: FaceDetectionResult.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rj0 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public y92 b;
    public a51[] c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public rj0(int i) {
        super(24);
    }

    public static rj0 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            rj0 rj0Var = new rj0(e70Var.c(d).b);
            rj0Var.b = y92.d(e70Var.t(8, false));
            e70 e70VarT = e70Var.t(16, false);
            int i = e70VarT.k(-1).b;
            rj0Var.c = new a51[i];
            for (int i2 = 0; i2 < i; i2++) {
                rj0Var.c[i2] = a51.d(C0173a.g(i2, 8, 8, e70VarT, false));
            }
            return rj0Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.k(this.b, 8, false);
        a51[] a51VarArr = this.c;
        if (a51VarArr == null) {
            qh0VarT.q(16, false);
            return;
        }
        qh0 qh0VarR = qh0VarT.r(a51VarArr.length, 16);
        int i = 0;
        while (true) {
            a51[] a51VarArr2 = this.c;
            if (i >= a51VarArr2.length) {
                return;
            }
            qh0VarR.k(a51VarArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public rj0() {
        this(0);
    }
}
