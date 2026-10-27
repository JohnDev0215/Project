package defpackage;

/* JADX INFO: renamed from: tM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1544tM extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public int b;
    public boolean c;

    static {
        PI[] piArr = {new PI(16, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1544tM() {
        super(16, 0);
    }

    public static C1544tM c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1544tM c1544tM = new C1544tM(ri.c(d).b);
            c1544tM.b = ri.m(8);
            c1544tM.c = ri.d(12, 0);
            return c1544tM;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.c(this.b, 8);
        wiU.l(this.c, 12, 0);
    }

    public C1544tM(int i) {
        super(16, i);
    }
}
