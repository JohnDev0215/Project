package defpackage;

/* JADX INFO: renamed from: aw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0519aw extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1378qK b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C0519aw() {
        super(16, 0);
    }

    public static C0519aw c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0519aw c0519aw = new C0519aw(ri.c(c).b);
            c0519aw.b = C1378qK.c(ri.s(8, false));
            return c0519aw;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).i(this.b, 8, false);
    }

    public C0519aw(int i) {
        super(16, i);
    }
}
