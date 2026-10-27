package defpackage;

/* JADX INFO: compiled from: BitmapN32ImageInfo.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ye extends hv2 {
    public static final t50[] g;
    public static final t50 h;
    public int b;
    public int c;
    public int d;
    public float[] e;
    public float[] f;

    static {
        t50 t50Var = new t50(40, 0);
        g = new t50[]{t50Var};
        h = t50Var;
    }

    public ye(int i) {
        super(40);
    }

    public static ye d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ye yeVar = new ye(e70Var.c(g).b);
            int iP = e70Var.p(8);
            yeVar.b = iP;
            if (!(iP >= 0 && iP <= 3)) {
                iP = 0;
            }
            yeVar.b = iP;
            yeVar.c = e70Var.p(12);
            yeVar.d = e70Var.p(16);
            yeVar.e = e70Var.o(24, 7);
            yeVar.f = e70Var.o(32, 9);
            return yeVar;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(h);
        qh0VarT.b(this.b, 8);
        qh0VarT.b(this.c, 12);
        qh0VarT.b(this.d, 16);
        qh0VarT.e(24, 7, this.e);
        qh0VarT.e(32, 9, this.f);
    }
}
