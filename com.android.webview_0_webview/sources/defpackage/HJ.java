package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class HJ extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public GJ b;

    static {
        PI[] piArr = {new PI(24, 0)};
        c = piArr;
        d = piArr[0];
    }

    public HJ(int i) {
        super(24, i);
    }

    public static HJ c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            HJ hj = new HJ(ri.c(c).b);
            hj.b = GJ.b(ri, 8);
            return hj;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).j(this.b, 8, false);
    }
}
