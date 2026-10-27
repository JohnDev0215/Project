package defpackage;

/* JADX INFO: renamed from: kM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1048kM extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public SG b;
    public C1599uM[] c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1048kM() {
        super(24, 0);
    }

    public static C1048kM c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1048kM c1048kM = new C1048kM(ri.c(d).b);
            c1048kM.b = SG.c(ri.s(8, false));
            RI riS = ri.s(16, false);
            PI piI = riS.i(-1);
            c1048kM.c = new C1599uM[piI.b];
            for (int i = 0; i < piI.b; i++) {
                c1048kM.c[i] = C1599uM.c(riS.s((i * 8) + 8, false));
            }
            return c1048kM;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.i(this.b, 8, false);
        C1599uM[] c1599uMArr = this.c;
        if (c1599uMArr == null) {
            wiU.q(16, false);
            return;
        }
        WI wiR = wiU.r(c1599uMArr.length, 16, -1);
        int i = 0;
        while (true) {
            C1599uM[] c1599uMArr2 = this.c;
            if (i >= c1599uMArr2.length) {
                return;
            }
            wiR.i(c1599uMArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public C1048kM(int i) {
        super(24, i);
    }
}
