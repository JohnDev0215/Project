package defpackage;

/* JADX INFO: renamed from: qF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1373qF extends AbstractC1651vJ {
    public static final PI[] e;
    public static final PI f;
    public String b;
    public String c;
    public String d;

    static {
        PI[] piArr = {new PI(32, 0)};
        e = piArr;
        f = piArr[0];
    }

    public C1373qF(int i) {
        super(32, i);
    }

    public static C1373qF c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1373qF c1373qF = new C1373qF(ri.c(e).b);
            c1373qF.b = ri.u(8, true);
            c1373qF.c = ri.u(16, true);
            c1373qF.d = ri.u(24, true);
            return c1373qF;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(f);
        wiU.e(this.b, 8, true);
        wiU.e(this.c, 16, true);
        wiU.e(this.d, 24, true);
    }
}
