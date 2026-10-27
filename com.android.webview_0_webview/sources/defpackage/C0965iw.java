package defpackage;

/* JADX INFO: renamed from: iw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0965iw extends AbstractC1651vJ {
    public static final PI[] j;
    public static final PI k;
    public Uv b;
    public VJ[] c;
    public VJ[] d;
    public C0854gw[] e;
    public GM[] f;
    public C1021jw g;
    public boolean h;
    public boolean i;

    static {
        PI[] piArr = {new PI(64, 0)};
        j = piArr;
        k = piArr[0];
    }

    public C0965iw() {
        super(64, 0);
    }

    public static C0965iw c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0965iw c0965iw = new C0965iw(ri.c(j).b);
            c0965iw.b = Uv.c(ri.s(8, false));
            c0965iw.c = ri.r(16, 0, -1);
            c0965iw.d = ri.r(24, 0, -1);
            RI riS = ri.s(32, false);
            PI piI = riS.i(-1);
            c0965iw.e = new C0854gw[piI.b];
            for (int i = 0; i < piI.b; i++) {
                c0965iw.e[i] = C0854gw.c(riS.s((i * 8) + 8, false));
            }
            RI riS2 = ri.s(40, false);
            PI piI2 = riS2.i(-1);
            c0965iw.f = new GM[piI2.b];
            for (int i2 = 0; i2 < piI2.b; i2++) {
                c0965iw.f[i2] = GM.c(riS2.s((i2 * 8) + 8, false));
            }
            c0965iw.g = C1021jw.c(ri.s(48, true));
            c0965iw.h = ri.d(56, 0);
            c0965iw.i = ri.d(56, 1);
            return c0965iw;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(k);
        wiU.i(this.b, 8, false);
        wiU.o(this.c, 16, 0, -1);
        wiU.o(this.d, 24, 0, -1);
        C0854gw[] c0854gwArr = this.e;
        if (c0854gwArr != null) {
            WI wiR = wiU.r(c0854gwArr.length, 32, -1);
            int i = 0;
            while (true) {
                C0854gw[] c0854gwArr2 = this.e;
                if (i >= c0854gwArr2.length) {
                    break;
                }
                wiR.i(c0854gwArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            wiU.q(32, false);
        }
        GM[] gmArr = this.f;
        if (gmArr != null) {
            WI wiR2 = wiU.r(gmArr.length, 40, -1);
            int i2 = 0;
            while (true) {
                GM[] gmArr2 = this.f;
                if (i2 >= gmArr2.length) {
                    break;
                }
                wiR2.i(gmArr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            wiU.q(40, false);
        }
        wiU.i(this.g, 48, true);
        wiU.l(this.h, 56, 0);
        wiU.l(this.i, 56, 1);
    }

    public C0965iw(int i) {
        super(64, i);
    }
}
