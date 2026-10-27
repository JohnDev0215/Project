package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class GM extends AbstractC1651vJ {
    public static final PI[] e;
    public static final PI f;
    public HM b;
    public long c;
    public C1213nK d;

    static {
        PI[] piArr = {new PI(40, 0)};
        e = piArr;
        f = piArr[0];
    }

    public GM(int i) {
        super(40, i);
    }

    public static GM c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            GM gm = new GM(ri.c(e).b);
            gm.b = HM.c(ri.s(8, false));
            gm.c = ri.p(16);
            gm.d = C1213nK.b(ri, 24);
            return gm;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(f);
        wiU.i(this.b, 8, false);
        wiU.d(this.c, 16);
        wiU.j(this.d, 24, false);
    }
}
