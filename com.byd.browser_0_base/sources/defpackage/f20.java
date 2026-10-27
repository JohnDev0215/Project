package defpackage;

/* JADX INFO: compiled from: CspTrustedTypes.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f20 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public String[] b;
    public boolean c;
    public boolean d;

    static {
        t50 t50Var = new t50(24, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public f20(int i) {
        super(24);
        this.c = false;
        this.d = false;
    }

    public static f20 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            f20 f20Var = new f20(e70Var.c(e).b);
            e70 e70VarT = e70Var.t(8, false);
            int i = e70VarT.k(-1).b;
            f20Var.b = new String[i];
            for (int i2 = 0; i2 < i; i2++) {
                f20Var.b[i2] = C0175b.g(i2, 8, 8, e70VarT, false);
            }
            f20Var.c = e70Var.d(16, 0);
            f20Var.d = e70Var.d(16, 1);
            return f20Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        String[] strArr = this.b;
        if (strArr != null) {
            qh0 qh0VarR = qh0VarT.r(strArr.length, 8);
            int iF = 0;
            while (true) {
                String[] strArr2 = this.b;
                if (iF >= strArr2.length) {
                    break;
                }
                iF = C0175b.f(iF, 8, 8, qh0VarR, strArr2[iF], false, iF, 1);
            }
        } else {
            qh0VarT.q(8, false);
        }
        qh0VarT.d(16, 0, this.c);
        qh0VarT.d(16, 1, this.d);
    }
}
