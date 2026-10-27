package defpackage;

/* JADX INFO: renamed from: gM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0825gM extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public C0934iJ b;
    public C1544tM c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C0825gM() {
        super(24, 0);
    }

    public static C0825gM c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0825gM c0825gM = new C0825gM(ri.c(d).b);
            c0825gM.b = ri.n(8, false);
            c0825gM.c = C1544tM.c(ri.s(16, false));
            return c0825gM;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.h(this.b, 8, false);
        wiU.i(this.c, 16, false);
    }

    public C0825gM(int i) {
        super(24, i);
    }
}
