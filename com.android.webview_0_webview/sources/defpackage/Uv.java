package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Uv extends AbstractC1651vJ {
    public static final PI[] k;
    public static final PI l;
    public C1213nK b;
    public C0910hw[] c;
    public C1877zP d;
    public long e;
    public long f;
    public long g;
    public boolean h;
    public C1378qK i;
    public Wv[] j;

    static {
        PI[] piArr = {new PI(88, 0)};
        k = piArr;
        l = piArr[0];
    }

    public Uv() {
        super(88, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Uv c(RI ri) {
        Wv[] wvArr = null;
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            Uv uv = new Uv(ri.c(k).b);
            uv.b = C1213nK.b(ri, 8);
            RI riS = ri.s(24, false);
            PI piI = riS.i(-1);
            uv.c = new C0910hw[piI.b];
            for (int i = 0; i < piI.b; i++) {
                uv.c[i] = C0910hw.c(riS.s((i * 8) + 8, false));
            }
            uv.d = C1877zP.c(ri.s(32, true));
            uv.e = ri.p(40);
            uv.f = ri.p(48);
            uv.g = ri.p(56);
            uv.h = ri.d(64, 0);
            uv.i = C1378qK.c(ri.s(72, true));
            AbstractC0599cJ abstractC0599cJ = Wv.d;
            AbstractC0599cJ abstractC0599cJ2 = AbstractC0798fw.a;
            RI riS2 = ri.s(80, JI.b(0));
            if (riS2 != null) {
                int i2 = riS2.h(8L, -1).b;
                Wv[] wvArr2 = new Wv[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    wvArr2[i3] = riS2.t((i3 * 8) + 8, JI.c(0), abstractC0599cJ2);
                }
                wvArr = wvArr2;
            }
            uv.j = wvArr;
            return uv;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(l);
        wiU.j(this.b, 8, false);
        C0910hw[] c0910hwArr = this.c;
        if (c0910hwArr != null) {
            WI wiR = wiU.r(c0910hwArr.length, 24, -1);
            int i = 0;
            while (true) {
                C0910hw[] c0910hwArr2 = this.c;
                if (i >= c0910hwArr2.length) {
                    break;
                }
                wiR.i(c0910hwArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            wiU.q(24, false);
        }
        wiU.i(this.d, 32, true);
        wiU.d(this.e, 40);
        wiU.d(this.f, 48);
        wiU.d(this.g, 56);
        wiU.l(this.h, 64, 0);
        wiU.i(this.i, 72, true);
        Wv[] wvArr = this.j;
        AbstractC0599cJ abstractC0599cJ = Wv.d;
        AbstractC0599cJ abstractC0599cJ2 = AbstractC0798fw.a;
        if (wvArr == null) {
            wiU.q(80, JI.b(0));
            return;
        }
        WI wiS = wiU.s(8, wvArr.length, 80, -1);
        for (int i2 = 0; i2 < wvArr.length; i2++) {
            wiS.g(wvArr[i2], (i2 * 8) + 8, JI.c(0), abstractC0599cJ2);
        }
    }

    public Uv(int i) {
        super(88, i);
    }
}
