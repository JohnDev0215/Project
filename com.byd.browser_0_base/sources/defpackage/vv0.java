package defpackage;

/* JADX INFO: compiled from: HttpRequestHeaders.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vv0 extends hv2 {
    public static final t50[] c;
    public static final t50 d;
    public uv0[] b;

    static {
        t50 t50Var = new t50(16, 0);
        c = new t50[]{t50Var};
        d = t50Var;
    }

    public vv0(int i) {
        super(16);
    }

    public static vv0 d(e70 e70Var) {
        uv0 uv0Var;
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            vv0 vv0Var = new vv0(e70Var.c(c).b);
            e70 e70VarT = e70Var.t(8, false);
            int i = e70VarT.k(-1).b;
            vv0Var.b = new uv0[i];
            for (int i2 = 0; i2 < i; i2++) {
                e70 e70VarG = C0173a.g(i2, 8, 8, e70VarT, false);
                uv0[] uv0VarArr = vv0Var.b;
                if (e70VarG == null) {
                    uv0Var = null;
                } else {
                    e70VarG.b();
                    try {
                        uv0Var = new uv0(e70VarG.c(uv0.d).b);
                        uv0Var.b = e70VarG.w(8, false);
                        uv0Var.c = e70VarG.w(16, false);
                    } finally {
                    }
                }
                uv0VarArr[i2] = uv0Var;
            }
            return vv0Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(d);
        uv0[] uv0VarArr = this.b;
        if (uv0VarArr == null) {
            qh0VarT.q(8, false);
            return;
        }
        qh0 qh0VarR = qh0VarT.r(uv0VarArr.length, 8);
        int i = 0;
        while (true) {
            uv0[] uv0VarArr2 = this.b;
            if (i >= uv0VarArr2.length) {
                return;
            }
            qh0VarR.k(uv0VarArr2[i], (i * 8) + 8, false);
            i++;
        }
    }
}
