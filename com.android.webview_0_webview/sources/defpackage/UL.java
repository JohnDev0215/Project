package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class UL extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public RL[] b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public UL() {
        super(16, 0);
    }

    public static UL c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            UL ul = new UL(ri.c(c).b);
            RI riS = ri.s(8, false);
            PI piI = riS.i(-1);
            ul.b = new RL[piI.b];
            for (int i = 0; i < piI.b; i++) {
                ul.b[i] = RL.c(riS.s((i * 8) + 8, false));
            }
            return ul;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(d);
        RL[] rlArr = this.b;
        if (rlArr == null) {
            wiU.q(8, false);
            return;
        }
        WI wiR = wiU.r(rlArr.length, 8, -1);
        int i = 0;
        while (true) {
            RL[] rlArr2 = this.b;
            if (i >= rlArr2.length) {
                return;
            }
            wiR.i(rlArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public UL(int i) {
        super(16, i);
    }
}
