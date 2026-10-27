package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Gv extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C0934iJ b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public Gv() {
        super(16, 0);
    }

    public static Gv c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            Gv gv = new Gv(ri.c(c).b);
            gv.b = ri.n(8, false);
            return gv;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).h(this.b, 8, false);
    }

    public Gv(int i) {
        super(16, i);
    }
}
