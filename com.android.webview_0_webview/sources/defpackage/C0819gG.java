package defpackage;

/* JADX INFO: renamed from: gG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0819gG extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1208nF b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C0819gG() {
        super(16, 0);
    }

    public static C0819gG c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0819gG c0819gG = new C0819gG(ri.c(c).b);
            c0819gG.b = C1208nF.c(ri.s(8, true));
            return c0819gG;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).i(this.b, 8, true);
    }

    public C0819gG(int i) {
        super(16, i);
    }
}
