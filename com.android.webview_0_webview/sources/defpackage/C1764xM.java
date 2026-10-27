package defpackage;

/* JADX INFO: renamed from: xM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1764xM extends AbstractC1651vJ {
    public static final PI[] e;
    public static final PI f;
    public String b;
    public SG c;
    public RG[] d;

    static {
        PI[] piArr = {new PI(32, 0)};
        e = piArr;
        f = piArr[0];
    }

    public C1764xM() {
        super(32, 0);
    }

    public static C1764xM c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1764xM c1764xM = new C1764xM(ri.c(e).b);
            c1764xM.b = ri.u(8, false);
            c1764xM.c = SG.c(ri.s(16, false));
            RI riS = ri.s(24, false);
            PI piI = riS.i(-1);
            c1764xM.d = new RG[piI.b];
            for (int i = 0; i < piI.b; i++) {
                c1764xM.d[i] = RG.c(riS.s((i * 8) + 8, false));
            }
            return c1764xM;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(f);
        wiU.e(this.b, 8, false);
        wiU.i(this.c, 16, false);
        RG[] rgArr = this.d;
        if (rgArr == null) {
            wiU.q(24, false);
            return;
        }
        WI wiR = wiU.r(rgArr.length, 24, -1);
        int i = 0;
        while (true) {
            RG[] rgArr2 = this.d;
            if (i >= rgArr2.length) {
                return;
            }
            wiR.i(rgArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public C1764xM(int i) {
        super(32, i);
    }
}
