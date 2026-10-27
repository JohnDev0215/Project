package defpackage;

/* JADX INFO: renamed from: oF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1263oF extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1318pF[] b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C1263oF() {
        super(16, 0);
    }

    public static C1263oF c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1263oF c1263oF = new C1263oF(ri.c(c).b);
            RI riS = ri.s(8, false);
            PI piI = riS.i(-1);
            c1263oF.b = new C1318pF[piI.b];
            for (int i = 0; i < piI.b; i++) {
                c1263oF.b[i] = C1318pF.c(riS.s((i * 8) + 8, false));
            }
            return c1263oF;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(d);
        C1318pF[] c1318pFArr = this.b;
        if (c1318pFArr == null) {
            wiU.q(8, false);
            return;
        }
        WI wiR = wiU.r(c1318pFArr.length, 8, -1);
        int i = 0;
        while (true) {
            C1318pF[] c1318pFArr2 = this.b;
            if (i >= c1318pFArr2.length) {
                return;
            }
            wiR.i(c1318pFArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public C1263oF(int i) {
        super(16, i);
    }
}
