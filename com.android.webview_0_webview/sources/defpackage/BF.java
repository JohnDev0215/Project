package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BF extends AbstractC1651vJ {
    public static final PI[] e;
    public static final PI f;
    public int[] b;
    public String c;
    public C1263oF d;

    static {
        PI[] piArr = {new PI(32, 0)};
        e = piArr;
        f = piArr[0];
    }

    public BF() {
        super(32, 0);
    }

    public static BF c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            BF bf = new BF(ri.c(e).b);
            bf.b = ri.o(8, 0, -1);
            bf.c = ri.u(16, true);
            bf.d = C1263oF.c(ri.s(24, false));
            return bf;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(f);
        wiU.n(this.b, 8, 0, -1);
        wiU.e(this.c, 16, true);
        wiU.i(this.d, 24, false);
    }

    public BF(int i) {
        super(32, i);
    }
}
