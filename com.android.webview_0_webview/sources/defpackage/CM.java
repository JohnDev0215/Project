package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class CM extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1764xM[] b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public CM() {
        super(16, 0);
    }

    public static CM c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            CM cm = new CM(ri.c(c).b);
            RI riS = ri.s(8, false);
            PI piI = riS.i(-1);
            cm.b = new C1764xM[piI.b];
            for (int i = 0; i < piI.b; i++) {
                cm.b[i] = C1764xM.c(riS.s((i * 8) + 8, false));
            }
            return cm;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(d);
        C1764xM[] c1764xMArr = this.b;
        if (c1764xMArr == null) {
            wiU.q(8, false);
            return;
        }
        WI wiR = wiU.r(c1764xMArr.length, 8, -1);
        int i = 0;
        while (true) {
            C1764xM[] c1764xMArr2 = this.b;
            if (i >= c1764xMArr2.length) {
                return;
            }
            wiR.i(c1764xMArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public CM(int i) {
        super(16, i);
    }
}
