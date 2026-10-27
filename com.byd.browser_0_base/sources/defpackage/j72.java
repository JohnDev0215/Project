package defpackage;

/* JADX INFO: compiled from: PublicKeyCredentialParameters.java */
/* JADX INFO: loaded from: classes.dex */
public final class j72 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public int b;
    public int c;

    static {
        t50 t50Var = new t50(16, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public j72(int i) {
        super(16);
    }

    public static j72 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            j72 j72Var = new j72(e70Var.c(d).b);
            int iP = e70Var.p(8);
            j72Var.b = iP;
            if (!(iP >= 0 && iP <= 0)) {
                throw new q80("Invalid enum value.");
            }
            j72Var.b = iP;
            j72Var.c = e70Var.p(12);
            return j72Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.b(this.b, 8);
        qh0VarT.b(this.c, 12);
    }
}
