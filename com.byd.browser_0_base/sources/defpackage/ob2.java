package defpackage;

/* JADX INFO: compiled from: ResolveErrorInfo.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ob2 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public int b;
    public boolean c;

    static {
        t50 t50Var = new t50(16, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public ob2(int i) {
        super(16);
        this.c = false;
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.b(this.b, 8);
        qh0VarT.d(12, 0, this.c);
    }
}
