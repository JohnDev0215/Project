package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class HF extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public int b;
    public C0934iJ c;

    static {
        PI[] piArr = {new PI(16, 0)};
        d = piArr;
        e = piArr[0];
    }

    public HF() {
        super(16, 0);
    }

    public static HF c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            HF hf = new HF(ri.c(d).b);
            hf.b = ri.m(8);
            hf.c = ri.n(12, false);
            return hf;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.c(this.b, 8);
        wiU.h(this.c, 12, false);
    }

    public HF(int i) {
        super(16, i);
    }
}
