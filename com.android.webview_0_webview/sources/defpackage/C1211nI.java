package defpackage;

import org.chromium.gfx.mojom.Rect;

/* JADX INFO: renamed from: nI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1211nI extends AbstractC1651vJ {
    public static final PI[] f;
    public static final PI g;
    public C1378qK b;
    public Rect c;
    public boolean d;
    public boolean e;

    static {
        PI[] piArr = {new PI(32, 0)};
        f = piArr;
        g = piArr[0];
    }

    public C1211nI(int i) {
        super(32, i);
    }

    public static C1211nI c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1211nI c1211nI = new C1211nI(ri.c(f).b);
            c1211nI.b = C1378qK.c(ri.s(8, false));
            c1211nI.c = Rect.c(ri.s(16, false));
            c1211nI.d = ri.d(24, 0);
            c1211nI.e = ri.d(24, 1);
            return c1211nI;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(g);
        wiU.i(this.b, 8, false);
        wiU.i(this.c, 16, false);
        wiU.l(this.d, 24, 0);
        wiU.l(this.e, 24, 1);
    }
}
