package defpackage;

/* JADX INFO: compiled from: RemoteDesktopClientOverride.java */
/* JADX INFO: loaded from: classes.dex */
public final class na2 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public et1 b;
    public boolean c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public na2(int i) {
        super(24);
    }

    public static na2 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            na2 na2Var = new na2(e70Var.c(d).b);
            na2Var.b = et1.d(e70Var.t(8, false));
            na2Var.c = e70Var.d(16, 0);
            return na2Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.d(16, 0, this.c);
    }
}
