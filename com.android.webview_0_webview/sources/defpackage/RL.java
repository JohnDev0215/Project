package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RL extends AbstractC1651vJ {
    public static final PI[] f;
    public static final PI g;
    public String b;
    public SG c;
    public int d;
    public RG[] e;

    static {
        PI[] piArr = {new PI(40, 0)};
        f = piArr;
        g = piArr[0];
    }

    public RL() {
        super(40, 0);
    }

    public static RL c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            RL rl = new RL(ri.c(f).b);
            rl.b = ri.u(8, false);
            rl.c = SG.c(ri.s(16, false));
            int iM = ri.m(24);
            rl.d = iM;
            AbstractC0546bM.a(iM);
            RI riS = ri.s(32, false);
            PI piI = riS.i(-1);
            rl.e = new RG[piI.b];
            for (int i = 0; i < piI.b; i++) {
                rl.e[i] = RG.c(riS.s((i * 8) + 8, false));
            }
            return rl;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(g);
        wiU.e(this.b, 8, false);
        wiU.i(this.c, 16, false);
        wiU.c(this.d, 24);
        RG[] rgArr = this.e;
        if (rgArr == null) {
            wiU.q(32, false);
            return;
        }
        WI wiR = wiU.r(rgArr.length, 32, -1);
        int i = 0;
        while (true) {
            RG[] rgArr2 = this.e;
            if (i >= rgArr2.length) {
                return;
            }
            wiR.i(rgArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public RL(int i) {
        super(40, i);
    }
}
