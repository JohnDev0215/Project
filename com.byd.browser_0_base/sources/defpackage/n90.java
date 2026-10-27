package defpackage;

/* JADX INFO: compiled from: DevicePublicKeyResponse.java */
/* JADX INFO: loaded from: classes.dex */
public final class n90 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public byte[] b;
    public byte[] c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public n90(int i) {
        super(24);
    }

    public static n90 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            n90 n90Var = new n90(e70Var.c(d).b);
            n90Var.b = e70Var.f(8, 0, -1);
            n90Var.c = e70Var.f(16, 0, -1);
            return n90Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.c(8, 0, -1, this.b);
        qh0VarT.c(16, 0, -1, this.c);
    }
}
