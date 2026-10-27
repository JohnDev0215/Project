package defpackage;

/* JADX INFO: renamed from: qK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1378qK extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public long b;
    public long c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1378qK(int i) {
        super(24, i);
    }

    public static C1378qK c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1378qK c1378qK = new C1378qK(ri.c(d).b);
            c1378qK.b = ri.p(8);
            c1378qK.c = ri.p(16);
            return c1378qK;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.d(this.b, 8);
        wiU.d(this.c, 16);
    }
}
