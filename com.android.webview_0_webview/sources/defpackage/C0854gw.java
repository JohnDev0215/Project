package defpackage;

/* JADX INFO: renamed from: gw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0854gw extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1213nK b;

    static {
        PI[] piArr = {new PI(24, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C0854gw(int i) {
        super(24, i);
    }

    public static C0854gw c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C0854gw c0854gw = new C0854gw(ri.c(c).b);
            c0854gw.b = C1213nK.b(ri, 8);
            return c0854gw;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).j(this.b, 8, false);
    }
}
