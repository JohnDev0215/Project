package defpackage;

/* JADX INFO: compiled from: BitmapN32.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xe extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public ye b;
    public ne c;

    static {
        t50 t50Var = new t50(32, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public xe(int i) {
        super(32);
    }

    public static xe d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            xe xeVar = new xe(e70Var.c(d).b);
            xeVar.b = ye.d(e70Var.t(8, false));
            xeVar.c = ne.b(e70Var, 16);
            return xeVar;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.l(this.c, 16, false);
    }
}
