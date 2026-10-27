package defpackage;

/* JADX INFO: renamed from: nF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1208nF extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public int b;
    public String c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1208nF() {
        super(24, 0);
    }

    public static C1208nF c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1208nF c1208nF = new C1208nF(ri.c(d).b);
            int iM = ri.m(8);
            c1208nF.b = iM;
            if (!(iM >= 0 && iM <= 7)) {
                throw new TI("Invalid enum value.");
            }
            c1208nF.c = ri.u(16, false);
            return c1208nF;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.c(this.b, 8);
        wiU.e(this.c, 16, false);
    }

    public C1208nF(int i) {
        super(24, i);
    }
}
