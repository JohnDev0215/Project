package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Yv extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C0934iJ b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public Yv() {
        super(16, 0);
    }

    public static Yv c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            Yv yv = new Yv(ri.c(c).b);
            yv.b = ri.n(8, false);
            return yv;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).h(this.b, 8, false);
    }

    public Yv(int i) {
        super(16, i);
    }
}
