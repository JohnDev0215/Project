package defpackage;

import org.chromium.gfx.mojom.Rect;

/* JADX INFO: renamed from: vI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1650vI extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public Rect b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C1650vI() {
        super(16, 0);
    }

    public static C1650vI c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C1650vI c1650vI = new C1650vI(ri.c(c).b);
            c1650vI.b = Rect.c(ri.s(8, false));
            return c1650vI;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).i(this.b, 8, false);
    }

    public C1650vI(int i) {
        super(16, i);
    }
}
