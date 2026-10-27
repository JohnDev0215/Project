package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Lv extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public MJ b;
    public InterfaceC1571tv c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public Lv() {
        super(24, 0);
        this.b = PJ.s;
    }

    public static Lv c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            Lv lv = new Lv(ri.c(d).b);
            lv.b = ri.v(8, false).i();
            AbstractC0599cJ abstractC0599cJ = InterfaceC1571tv.c;
            lv.c = (InterfaceC1571tv) ri.t(12, true, AbstractC1901zv.a);
            return lv;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.k(this.b, 8, false);
        InterfaceC1571tv interfaceC1571tv = this.c;
        AbstractC0599cJ abstractC0599cJ = InterfaceC1571tv.c;
        wiU.g(interfaceC1571tv, 12, true, AbstractC1901zv.a);
    }

    public Lv(int i) {
        super(24, i);
        this.b = PJ.s;
    }
}
