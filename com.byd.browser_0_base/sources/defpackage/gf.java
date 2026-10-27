package defpackage;

/* JADX INFO: compiled from: BlockedByResponseReasonWrapper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class gf extends hv2 {
    public static final t50[] c;
    public static final t50 d;
    public int b;

    static {
        t50 t50Var = new t50(16, 0);
        c = new t50[]{t50Var};
        d = t50Var;
    }

    public gf(int i) {
        super(16);
    }

    public static gf d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            gf gfVar = new gf(e70Var.c(c).b);
            int iP = e70Var.p(8);
            gfVar.b = iP;
            if (!(iP >= 0 && iP <= 4)) {
                throw new q80("Invalid enum value.");
            }
            gfVar.b = iP;
            return gfVar;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0Var.t(d).b(this.b, 8);
    }
}
