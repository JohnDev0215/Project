package defpackage;

/* JADX INFO: compiled from: AuthenticatorSelectionCriteria.java */
/* JADX INFO: loaded from: classes.dex */
public final class t9 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public int b;
    public int c;
    public int d;

    static {
        t50 t50Var = new t50(24, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public t9(int i) {
        super(24);
    }

    public static t9 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            t9 t9Var = new t9(e70Var.c(e).b);
            int iP = e70Var.p(8);
            t9Var.b = iP;
            n3.t1(iP);
            t9Var.b = t9Var.b;
            int iP2 = e70Var.p(12);
            t9Var.c = iP2;
            boolean z = true;
            if (!(iP2 >= 0 && iP2 <= 2)) {
                throw new q80("Invalid enum value.");
            }
            t9Var.c = iP2;
            int iP3 = e70Var.p(16);
            t9Var.d = iP3;
            if (iP3 < 0 || iP3 > 2) {
                z = false;
            }
            if (!z) {
                throw new q80("Invalid enum value.");
            }
            t9Var.d = iP3;
            return t9Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.b(this.b, 8);
        qh0VarT.b(this.c, 12);
        qh0VarT.b(this.d, 16);
    }
}
