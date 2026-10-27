package defpackage;

/* JADX INFO: renamed from: uM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1599uM extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public RG[] b;
    public int c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1599uM() {
        super(24, 0);
    }

    public static C1599uM c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1599uM c1599uM = new C1599uM(ri.c(d).b);
            boolean z = false;
            RI riS = ri.s(8, false);
            PI piI = riS.i(-1);
            c1599uM.b = new RG[piI.b];
            for (int i = 0; i < piI.b; i++) {
                c1599uM.b[i] = RG.c(riS.s((i * 8) + 8, false));
            }
            int iM = ri.m(16);
            c1599uM.c = iM;
            if (iM >= 0 && iM <= 2) {
                z = true;
            }
            if (z) {
                return c1599uM;
            }
            throw new TI("Invalid enum value.");
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        RG[] rgArr = this.b;
        if (rgArr != null) {
            WI wiR = wiU.r(rgArr.length, 8, -1);
            int i = 0;
            while (true) {
                RG[] rgArr2 = this.b;
                if (i >= rgArr2.length) {
                    break;
                }
                wiR.i(rgArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            wiU.q(8, false);
        }
        wiU.c(this.c, 16);
    }

    public C1599uM(int i) {
        super(24, i);
    }
}
