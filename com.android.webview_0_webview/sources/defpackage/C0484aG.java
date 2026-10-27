package defpackage;

/* JADX INFO: renamed from: aG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0484aG extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public C1263oF b;
    public C1427rF c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C0484aG() {
        super(24, 0);
    }

    public static C0484aG c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0484aG c0484aG = new C0484aG(ri.c(d).b);
            c0484aG.b = C1263oF.c(ri.s(8, false));
            c0484aG.c = C1427rF.c(ri.s(16, true));
            return c0484aG;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.i(this.b, 8, false);
        wiU.i(this.c, 16, true);
    }

    public C0484aG(int i) {
        super(24, i);
    }
}
