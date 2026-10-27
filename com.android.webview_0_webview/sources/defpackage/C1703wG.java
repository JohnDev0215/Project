package defpackage;

/* JADX INFO: renamed from: wG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1703wG extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public long b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C1703wG() {
        super(16, 0);
    }

    public static C1703wG c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C1703wG c1703wG = new C1703wG(ri.c(c).b);
            c1703wG.b = ri.p(8);
            return c1703wG;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).d(this.b, 8);
    }

    public C1703wG(int i) {
        super(16, i);
    }
}
