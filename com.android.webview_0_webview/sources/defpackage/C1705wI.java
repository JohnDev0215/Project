package defpackage;

import org.chromium.gfx.mojom.Rect;

/* JADX INFO: renamed from: wI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1705wI extends AbstractC0543bJ implements InterfaceC0709eI, InterfaceC0710eJ {
    public C1705wI(LJ lj, InterfaceC1157mJ interfaceC1157mJ) {
        super(lj, interfaceC1157mJ);
    }

    @Override // defpackage.InterfaceC0709eI
    public void n(Rect rect) {
        C1650vI c1650vI = new C1650vI();
        c1650vI.b = rect;
        C0487aJ c0487aJ = this.s;
        c0487aJ.t.q(c1650vI.b(c0487aJ.s, new C1045kJ(0)));
    }

    @Override // defpackage.InterfaceC0710eJ
    public InterfaceC0655dJ w() {
        return this.s;
    }
}
