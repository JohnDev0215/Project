package defpackage;

/* JADX INFO: renamed from: bG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0540bG extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1208nF b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C0540bG() {
        super(16, 0);
    }

    public static C0540bG c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0540bG c0540bG = new C0540bG(ri.c(c).b);
            c0540bG.b = C1208nF.c(ri.s(8, true));
            return c0540bG;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).i(this.b, 8, true);
    }

    public C0540bG(int i) {
        super(16, i);
    }
}
