package defpackage;

/* JADX INFO: renamed from: rF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1427rF extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public boolean b;
    public boolean c;

    static {
        PI[] piArr = {new PI(16, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1427rF(int i) {
        super(16, i);
    }

    public static C1427rF c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1427rF c1427rF = new C1427rF(ri.c(d).b);
            c1427rF.b = ri.d(8, 0);
            c1427rF.c = ri.d(8, 1);
            return c1427rF;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.l(this.b, 8, 0);
        wiU.l(this.c, 8, 1);
    }
}
