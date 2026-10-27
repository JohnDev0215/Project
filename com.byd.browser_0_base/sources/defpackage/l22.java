package defpackage;

/* JADX INFO: compiled from: PointF.java */
/* JADX INFO: loaded from: classes3.dex */
public final class l22 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public float b;
    public float c;

    static {
        t50 t50Var = new t50(16, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public l22(int i) {
        super(16);
    }

    public static l22 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            l22 l22Var = new l22(e70Var.c(d).b);
            l22Var.b = e70Var.n(8);
            l22Var.c = e70Var.n(12);
            return l22Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.a(this.b, 8);
        qh0VarT.a(this.c, 12);
    }

    public l22() {
        this(0);
    }
}
