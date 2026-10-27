package defpackage;

/* JADX INFO: compiled from: NdefMessage.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ml1 extends hv2 {
    public static final t50[] c;
    public static final t50 d;
    public ol1[] b;

    static {
        t50 t50Var = new t50(16, 0);
        c = new t50[]{t50Var};
        d = t50Var;
    }

    public ml1(int i) {
        super(16);
    }

    public static ml1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ml1 ml1Var = new ml1(e70Var.c(c).b);
            e70 e70VarT = e70Var.t(8, false);
            int i = e70VarT.k(-1).b;
            ml1Var.b = new ol1[i];
            for (int i2 = 0; i2 < i; i2++) {
                ml1Var.b[i2] = ol1.d(C0173a.g(i2, 8, 8, e70VarT, false));
            }
            return ml1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(d);
        ol1[] ol1VarArr = this.b;
        if (ol1VarArr == null) {
            qh0VarT.q(8, false);
            return;
        }
        qh0 qh0VarR = qh0VarT.r(ol1VarArr.length, 8);
        int i = 0;
        while (true) {
            ol1[] ol1VarArr2 = this.b;
            if (i >= ol1VarArr2.length) {
                return;
            }
            qh0VarR.k(ol1VarArr2[i], (i * 8) + 8, false);
            i++;
        }
    }
}
