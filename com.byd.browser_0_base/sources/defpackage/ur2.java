package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: StartSurfaceMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ur2 implements e23 {
    public final /* synthetic */ yr2 e;

    public ur2(yr2 yr2Var) {
        this.e = yr2Var;
    }

    @Override // defpackage.e23
    public final void b() {
        yr2 yr2Var = this.e;
        yr2Var.g.l(this);
        TabModel tabModelG = yr2Var.g.g(false);
        yr2Var.F = tabModelG;
        if (yr2Var.N) {
            yr2Var.N = false;
            tabModelG.j(yr2Var.G);
        }
    }
}
