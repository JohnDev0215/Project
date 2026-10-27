package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class HM extends AbstractC1651vJ {
    public static final PI[] g;
    public static final PI h;
    public int b;
    public int c;
    public byte[] d;
    public int e;
    public int f;

    static {
        PI[] piArr = {new PI(32, 0)};
        g = piArr;
        h = piArr[0];
    }

    public HM(int i) {
        super(32, i);
    }

    public static HM c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            HM hm = new HM(ri.c(g).b);
            int iM = ri.m(8);
            hm.b = iM;
            boolean z = true;
            if (!(iM >= 0 && iM <= 7)) {
                throw new TI("Invalid enum value.");
            }
            int iM2 = ri.m(12);
            hm.c = iM2;
            if (iM2 < 0 || iM2 > 3) {
                z = false;
            }
            if (!z) {
                throw new TI("Invalid enum value.");
            }
            hm.d = ri.e(16, 0, -1);
            hm.e = ri.m(24);
            hm.f = ri.m(28);
            return hm;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(h);
        wiU.c(this.b, 8);
        wiU.c(this.c, 12);
        wiU.m(this.d, 16, 0, -1);
        wiU.c(this.e, 24);
        wiU.c(this.f, 28);
    }
}
