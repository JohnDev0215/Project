package defpackage;

/* JADX INFO: renamed from: qI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1376qI extends AbstractC1651vJ {
    public static final PI[] e;
    public static final PI f;
    public C0934iJ b;
    public InterfaceC0765fI c;
    public C1211nI d;

    static {
        PI[] piArr = {new PI(32, 0)};
        e = piArr;
        f = piArr[0];
    }

    public C1376qI() {
        super(32, 0);
    }

    public static C1376qI c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C1376qI c1376qI = new C1376qI(ri.c(e).b);
            c1376qI.b = ri.n(8, false);
            AbstractC0599cJ abstractC0599cJ = InterfaceC0765fI.k;
            c1376qI.c = (InterfaceC0765fI) ri.t(12, false, AbstractC1156mI.a);
            c1376qI.d = C1211nI.c(ri.s(24, false));
            return c1376qI;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(f);
        wiU.h(this.b, 8, false);
        InterfaceC0765fI interfaceC0765fI = this.c;
        AbstractC0599cJ abstractC0599cJ = InterfaceC0765fI.k;
        wiU.g(interfaceC0765fI, 12, false, AbstractC1156mI.a);
        wiU.i(this.d, 24, false);
    }

    public C1376qI(int i) {
        super(32, i);
    }
}
