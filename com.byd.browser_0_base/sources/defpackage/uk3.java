package defpackage;

/* JADX INFO: compiled from: UvmEntry.java */
/* JADX INFO: loaded from: classes.dex */
public final class uk3 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public int b;
    public short c;
    public short d;

    static {
        t50 t50Var = new t50(16, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public uk3(int i) {
        super(16);
    }

    public static uk3 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            uk3 uk3Var = new uk3(e70Var.c(e).b);
            uk3Var.b = e70Var.p(8);
            uk3Var.c = e70Var.v(12);
            uk3Var.d = e70Var.v(14);
            return uk3Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.b(this.b, 8);
        qh0VarT.n(this.c, 12);
        qh0VarT.n(this.d, 14);
    }
}
