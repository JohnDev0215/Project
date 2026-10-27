package defpackage;

import org.chromium.chrome.browser.tab.TabImpl;

/* JADX INFO: compiled from: CustomTabActivityTabController.java */
/* JADX INFO: loaded from: classes.dex */
public final class y20 extends nh0 {
    public final /* synthetic */ x20 e;

    public y20(x20 x20Var) {
        this.e = x20Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Z(TabImpl tabImpl) {
        tabImpl.t(this);
        this.e.j.get().getCompositorView().i(new l91(this, 11, tabImpl));
    }
}
