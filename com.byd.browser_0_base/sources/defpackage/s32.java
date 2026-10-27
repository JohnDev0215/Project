package defpackage;

/* JADX INFO: compiled from: PrfValues.java */
/* JADX INFO: loaded from: classes.dex */
public final class s32 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public byte[] b;
    public byte[] c;
    public byte[] d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public s32(int i) {
        super(32);
    }

    public static s32 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            s32 s32Var = new s32(e70Var.c(e).b);
            s32Var.b = e70Var.f(8, 1, -1);
            s32Var.c = e70Var.f(16, 0, 32);
            s32Var.d = e70Var.f(24, 1, 32);
            return s32Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.c(8, 1, -1, this.b);
        qh0VarT.c(16, 0, 32, this.c);
        qh0VarT.c(24, 1, 32, this.d);
    }
}
