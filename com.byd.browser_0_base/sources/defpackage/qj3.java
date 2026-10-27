package defpackage;

/* JADX INFO: compiled from: Url.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qj3 extends hv2 {
    public static final t50[] c;
    public static final t50 d;
    public String b;

    static {
        t50 t50Var = new t50(16, 0);
        c = new t50[]{t50Var};
        d = t50Var;
    }

    public qj3(int i) {
        super(16);
    }

    public static qj3 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            qj3 qj3Var = new qj3(e70Var.c(c).b);
            qj3Var.b = e70Var.w(8, false);
            return qj3Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0Var.t(d).m(this.b, 8, false);
    }
}
