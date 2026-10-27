package defpackage;

/* JADX INFO: renamed from: mM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1160mM extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public GM b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C1160mM() {
        super(16, 0);
    }

    public static C1160mM c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C1160mM c1160mM = new C1160mM(ri.c(c).b);
            c1160mM.b = GM.c(ri.s(8, false));
            return c1160mM;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).i(this.b, 8, false);
    }

    public C1160mM(int i) {
        super(16, i);
    }
}
