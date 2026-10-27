package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Mv extends AbstractC1651vJ {
    public static final PI[] f;
    public static final PI g;
    public long b;
    public long c;
    public MJ d;
    public InterfaceC1571tv e;

    static {
        PI[] piArr = {new PI(40, 0)};
        f = piArr;
        g = piArr[0];
    }

    public Mv() {
        super(40, 0);
        this.d = PJ.s;
    }

    public static Mv c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            Mv mv = new Mv(ri.c(f).b);
            mv.b = ri.p(8);
            mv.c = ri.p(16);
            mv.d = ri.v(24, false).i();
            AbstractC0599cJ abstractC0599cJ = InterfaceC1571tv.c;
            mv.e = (InterfaceC1571tv) ri.t(28, true, AbstractC1901zv.a);
            return mv;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(g);
        wiU.d(this.b, 8);
        wiU.d(this.c, 16);
        wiU.k(this.d, 24, false);
        InterfaceC1571tv interfaceC1571tv = this.e;
        AbstractC0599cJ abstractC0599cJ = InterfaceC1571tv.c;
        wiU.g(interfaceC1571tv, 28, true, AbstractC1901zv.a);
    }

    public Mv(int i) {
        super(40, i);
        this.d = PJ.s;
    }
}
