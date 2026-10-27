package defpackage;

/* JADX INFO: renamed from: pF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1318pF extends AbstractC1651vJ {
    public static final PI[] j;
    public static final PI k;
    public int b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public byte[] h;
    public C1263oF i;

    static {
        PI[] piArr = {new PI(72, 0)};
        j = piArr;
        k = piArr[0];
    }

    public C1318pF() {
        super(72, 0);
    }

    public static C1318pF c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1318pF c1318pF = new C1318pF(ri.c(j).b);
            int iM = ri.m(8);
            c1318pF.b = iM;
            if (!(iM >= 0 && iM <= 2)) {
                throw new TI("Invalid enum value.");
            }
            c1318pF.c = ri.u(16, false);
            c1318pF.d = ri.u(24, true);
            c1318pF.e = ri.u(32, true);
            c1318pF.f = ri.u(40, true);
            c1318pF.g = ri.u(48, true);
            c1318pF.h = ri.e(56, 0, -1);
            c1318pF.i = C1263oF.c(ri.s(64, true));
            return c1318pF;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(k);
        wiU.c(this.b, 8);
        wiU.e(this.c, 16, false);
        wiU.e(this.d, 24, true);
        wiU.e(this.e, 32, true);
        wiU.e(this.f, 40, true);
        wiU.e(this.g, 48, true);
        wiU.m(this.h, 56, 0, -1);
        wiU.i(this.i, 64, true);
    }

    public C1318pF(int i) {
        super(72, i);
    }
}
