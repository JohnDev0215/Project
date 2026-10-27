package defpackage;

/* JADX INFO: renamed from: aM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0490aM extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public int[] b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C0490aM(int i) {
        super(16, i);
    }

    public static C0490aM c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C0490aM c0490aM = new C0490aM(ri.c(c).b);
            c0490aM.b = ri.o(8, 0, -1);
            for (int i = 0; i < c0490aM.b.length; i++) {
                AbstractC0546bM.a(c0490aM.b[i]);
            }
            return c0490aM;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).n(this.b, 8, 0, -1);
    }
}
