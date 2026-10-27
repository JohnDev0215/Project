package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class AF extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1208nF b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public AF() {
        super(16, 0);
    }

    public static AF c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            AF af = new AF(ri.c(c).b);
            af.b = C1208nF.c(ri.s(8, false));
            return af;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).i(this.b, 8, false);
    }

    public AF(int i) {
        super(16, i);
    }
}
