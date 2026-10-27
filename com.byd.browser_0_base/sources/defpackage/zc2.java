package defpackage;

/* JADX INFO: compiled from: RunInput.java */
/* JADX INFO: loaded from: classes3.dex */
public final class zc2 extends ui3 {
    public s82 b;
    public ao0 c;

    public static final zc2 b(e70 e70Var) {
        t50 t50VarL = e70Var.l(8);
        s82 s82Var = null;
        ao0 ao0Var = null;
        if (t50VarL.a == 0) {
            return null;
        }
        zc2 zc2Var = new zc2();
        int i = t50VarL.b;
        if (i == 0) {
            e70 e70VarT = e70Var.t(16, false);
            if (e70VarT != null) {
                e70VarT.b();
                try {
                    s82Var = new s82(e70VarT.c(s82.b).b);
                } finally {
                }
            }
            zc2Var.b = s82Var;
            zc2Var.a = 0;
        } else if (i == 1) {
            e70 e70VarT2 = e70Var.t(16, false);
            if (e70VarT2 != null) {
                e70VarT2.b();
                try {
                    ao0Var = new ao0(e70VarT2.c(ao0.b).b);
                } finally {
                }
            }
            zc2Var.c = ao0Var;
            zc2Var.a = 1;
        }
        return zc2Var;
    }

    @Override // defpackage.ui3
    public final void a(qh0 qh0Var, int i) {
        qh0Var.b(16, i);
        qh0Var.b(this.a, i + 4);
        int i2 = this.a;
        if (i2 == 0) {
            qh0Var.k(this.b, i + 8, false);
        } else {
            if (i2 != 1) {
                return;
            }
            qh0Var.k(this.c, i + 8, false);
        }
    }
}
