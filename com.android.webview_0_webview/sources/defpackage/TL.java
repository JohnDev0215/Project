package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class TL extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public GM b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public TL() {
        super(16, 0);
    }

    public static TL c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            TL tl = new TL(ri.c(c).b);
            tl.b = GM.c(ri.s(8, false));
            return tl;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).i(this.b, 8, false);
    }

    public TL(int i) {
        super(16, i);
    }
}
