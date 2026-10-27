package defpackage;

/* JADX INFO: compiled from: SafeBaseName.java */
/* JADX INFO: loaded from: classes3.dex */
public final class id2 extends hv2 {
    public static final t50[] c;
    public static final t50 d;
    public lm0 b;

    static {
        t50 t50Var = new t50(16, 0);
        c = new t50[]{t50Var};
        d = t50Var;
    }

    public id2(int i) {
        super(16);
    }

    public static id2 d(e70 e70Var) {
        lm0 lm0Var = null;
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            id2 id2Var = new id2(e70Var.c(c).b);
            e70 e70VarT = e70Var.t(8, false);
            if (e70VarT != null) {
                e70VarT.b();
                try {
                    lm0 lm0Var2 = new lm0(e70VarT.c(lm0.c).b);
                    lm0Var2.b = e70VarT.w(8, false);
                    e70VarT.a();
                    lm0Var = lm0Var2;
                } catch (Throwable th) {
                    e70VarT.a();
                    throw th;
                }
            }
            id2Var.b = lm0Var;
            return id2Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0Var.t(d).k(this.b, 8, false);
    }
}
