package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class LL extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public int[] b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public LL() {
        super(16, 0);
    }

    public static LL c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            LL ll = new LL(ri.c(c).b);
            ll.b = ri.o(8, 0, -1);
            for (int i = 0; i < ll.b.length; i++) {
                AbstractC0546bM.a(ll.b[i]);
            }
            return ll;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).n(this.b, 8, 0, -1);
    }

    public LL(int i) {
        super(16, i);
    }
}
