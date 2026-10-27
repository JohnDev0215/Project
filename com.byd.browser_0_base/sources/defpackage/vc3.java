package defpackage;

import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vc3 implements ym0 {
    public final /* synthetic */ g e;

    public vc3(g gVar) {
        this.e = gVar;
    }

    @Override // defpackage.ym0
    public final void c() {
        g gVar = this.e;
        gVar.k.a.h(true);
        vj vjVar = gVar.r0;
        if (vjVar != null) {
            gVar.t0 = vjVar.w(gVar.t0);
        }
    }

    @Override // defpackage.ym0
    public final void d() {
        g gVar = this.e;
        gVar.k.a.h(false);
        vj vjVar = gVar.r0;
        if (vjVar != null) {
            vjVar.u(gVar.t0);
        }
    }
}
