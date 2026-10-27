package defpackage;

/* JADX INFO: compiled from: ReadOnlyFile.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c92 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public ej3 b;
    public boolean c;

    static {
        t50 t50Var = new t50(16, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public c92(int i) {
        super(16);
        this.b = l21.e;
    }

    public static c92 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            c92 c92Var = new c92(e70Var.c(d).b);
            c92Var.b = e70Var.x(8, false);
            c92Var.c = e70Var.d(12, 0);
            return c92Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.h(this.b, 8, false);
        qh0VarT.d(12, 0, this.c);
    }

    public c92() {
        this(0);
    }
}
