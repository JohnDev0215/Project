package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rc3 implements e23 {
    public final /* synthetic */ g e;

    public rc3(g gVar) {
        this.e = gVar;
    }

    @Override // defpackage.e23
    public final void g() {
        g gVar = this.e;
        gVar.u0 = true;
        if (gVar.v0) {
            gVar.k.a.u();
        }
    }

    @Override // defpackage.e23
    public final void k(TabModel tabModel) {
        g gVar = this.e;
        y13 y13Var = gVar.u;
        if (y13Var != null) {
            gVar.u(y13Var.i());
        }
    }
}
