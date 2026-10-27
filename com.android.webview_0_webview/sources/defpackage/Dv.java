package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Dv extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public long b;
    public C1323pK c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public Dv() {
        super(24, 0);
    }

    public static Dv c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            Dv dv = new Dv(ri.c(d).b);
            dv.b = ri.p(8);
            dv.c = C1323pK.c(ri.s(16, true));
            return dv;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.d(this.b, 8);
        wiU.i(this.c, 16, true);
    }

    public Dv(int i) {
        super(24, i);
    }
}
