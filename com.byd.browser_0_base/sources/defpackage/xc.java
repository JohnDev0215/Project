package defpackage;

/* JADX INFO: compiled from: BarcodeDetectorOptions.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xc extends hv2 {
    public static final t50[] c;
    public static final t50 d;
    public int[] b;

    static {
        t50 t50Var = new t50(16, 0);
        c = new t50[]{t50Var};
        d = t50Var;
    }

    public xc(int i) {
        super(16);
    }

    public static xc d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            xc xcVar = new xc(e70Var.c(c).b);
            int i = 0;
            xcVar.b = e70Var.r(8, 0);
            while (true) {
                int[] iArr = xcVar.b;
                if (i >= iArr.length) {
                    return xcVar;
                }
                a8.l0(iArr[i]);
                i++;
            }
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0Var.t(d).o(this.b, 8, 0);
    }
}
