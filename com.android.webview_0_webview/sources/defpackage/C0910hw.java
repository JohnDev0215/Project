package defpackage;

/* JADX INFO: renamed from: hw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0910hw extends AbstractC1651vJ {
    public static final PI[] f;
    public static final PI g;
    public String b;
    public String c;
    public long d;
    public InterfaceC1516sv e;

    static {
        PI[] piArr = {new PI(40, 0)};
        f = piArr;
        g = piArr[0];
    }

    public C0910hw(int i) {
        super(40, i);
    }

    public static C0910hw c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C0910hw c0910hw = new C0910hw(ri.c(f).b);
            c0910hw.b = ri.u(8, false);
            c0910hw.c = ri.u(16, false);
            c0910hw.d = ri.p(24);
            AbstractC0599cJ abstractC0599cJ = InterfaceC1516sv.b;
            c0910hw.e = (InterfaceC1516sv) ri.t(32, false, Tv.a);
            return c0910hw;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(g);
        wiU.e(this.b, 8, false);
        wiU.e(this.c, 16, false);
        wiU.d(this.d, 24);
        InterfaceC1516sv interfaceC1516sv = this.e;
        AbstractC0599cJ abstractC0599cJ = InterfaceC1516sv.b;
        wiU.g(interfaceC1516sv, 32, false, Tv.a);
    }
}
