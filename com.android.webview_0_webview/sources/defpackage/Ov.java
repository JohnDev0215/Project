package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Ov extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1213nK b;

    static {
        PI[] piArr = {new PI(24, 0)};
        c = piArr;
        d = piArr[0];
    }

    public Ov() {
        super(24, 0);
    }

    public static Ov c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            Ov ov = new Ov(ri.c(c).b);
            ov.b = C1213nK.b(ri, 8);
            return ov;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).j(this.b, 8, true);
    }

    public Ov(int i) {
        super(24, i);
    }
}
