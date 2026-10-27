package defpackage;

/* JADX INFO: renamed from: nM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1215nM extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1048kM[] b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C1215nM() {
        super(16, 0);
    }

    public static C1215nM c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C1215nM c1215nM = new C1215nM(ri.c(c).b);
            RI riS = ri.s(8, false);
            PI piI = riS.i(-1);
            c1215nM.b = new C1048kM[piI.b];
            for (int i = 0; i < piI.b; i++) {
                c1215nM.b[i] = C1048kM.c(riS.s((i * 8) + 8, false));
            }
            return c1215nM;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(d);
        C1048kM[] c1048kMArr = this.b;
        if (c1048kMArr == null) {
            wiU.q(8, false);
            return;
        }
        WI wiR = wiU.r(c1048kMArr.length, 8, -1);
        int i = 0;
        while (true) {
            C1048kM[] c1048kMArr2 = this.b;
            if (i >= c1048kMArr2.length) {
                return;
            }
            wiR.i(c1048kMArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public C1215nM(int i) {
        super(16, i);
    }
}
