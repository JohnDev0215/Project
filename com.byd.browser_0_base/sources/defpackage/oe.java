package defpackage;

/* JADX INFO: compiled from: BigBufferSharedMemoryRegion.java */
/* JADX INFO: loaded from: classes3.dex */
public final class oe extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public jl2 b;
    public int c;

    static {
        t50 t50Var = new t50(16, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public oe(int i) {
        super(16);
        this.b = l21.e;
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.h(this.b, 8, false);
        qh0VarT.b(this.c, 12);
    }
}
