package defpackage;

/* JADX INFO: compiled from: TimeDelta.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qa3 extends hv2 {
    public static final t50[] c;
    public static final t50 d;
    public long b;

    static {
        t50 t50Var = new t50(16, 0);
        c = new t50[]{t50Var};
        d = t50Var;
    }

    public qa3(int i) {
        super(16);
    }

    public static qa3 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            qa3 qa3Var = new qa3(e70Var.c(c).b);
            qa3Var.b = e70Var.s(8);
            return qa3Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0Var.t(d).f(8, this.b);
    }
}
