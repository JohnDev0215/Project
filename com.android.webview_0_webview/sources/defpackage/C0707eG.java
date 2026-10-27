package defpackage;

/* JADX INFO: renamed from: eG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0707eG extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public InterfaceC1812yF b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C0707eG() {
        super(16, 0);
    }

    public static C0707eG c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0707eG c0707eG = new C0707eG(ri.c(c).b);
            AbstractC0599cJ abstractC0599cJ = InterfaceC1812yF.g;
            c0707eG.b = (InterfaceC1812yF) ri.t(8, false, EF.a);
            return c0707eG;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(d);
        InterfaceC1812yF interfaceC1812yF = this.b;
        AbstractC0599cJ abstractC0599cJ = InterfaceC1812yF.g;
        wiU.g(interfaceC1812yF, 8, false, EF.a);
    }

    public C0707eG(int i) {
        super(16, i);
    }
}
