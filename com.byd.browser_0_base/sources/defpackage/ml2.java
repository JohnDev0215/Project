package defpackage;

/* JADX INFO: compiled from: SharedFile.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ml2 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public id2 b;
    public ti2 c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public ml2(int i) {
        super(24);
    }

    public static ml2 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ml2 ml2Var = new ml2(e70Var.c(d).b);
            ml2Var.b = id2.d(e70Var.t(8, false));
            ml2Var.c = ti2.d(e70Var.t(16, false));
            return ml2Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.k(this.c, 16, false);
    }
}
