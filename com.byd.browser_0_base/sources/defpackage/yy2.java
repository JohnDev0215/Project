package defpackage;

import J.N;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabContentManagerHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class yy2 extends j23 {
    public final TabContentManager i;
    public final rq0 j;
    public final xy2 k;
    public boolean l;
    public Tab m;

    public yy2(TabContentManager tabContentManager, qq0 qq0Var, y13 y13Var) {
        super(y13Var);
        this.i = tabContentManager;
        this.j = qq0Var;
        xy2 xy2Var = new xy2(this);
        this.k = xy2Var;
        qq0Var.h(xy2Var);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void S0(Tab tab, boolean z) {
        Tab tab2;
        if (z && this.l && (tab2 = this.m) != null) {
            this.i.f(tab2.getId());
            this.l = false;
            this.m = null;
        }
    }

    @Override // defpackage.j23
    public final void destroy() {
        super.destroy();
        this.j.c(this.k);
    }

    @Override // defpackage.j23
    public final void y1(Tab tab) {
        TabContentManager tabContentManager = this.i;
        if (tabContentManager.e == 0 || TabContentManager.j.a()) {
            return;
        }
        N.MRvrWXuI(tabContentManager.e, tab, tab.getId());
    }

    @Override // defpackage.j23
    public final void z1(Tab tab) {
        TabContentManager tabContentManager = this.i;
        if (tabContentManager.e == 0 || TabContentManager.j.a()) {
            return;
        }
        N.M6M_espa(tabContentManager.e, tab, tab.getId());
    }
}
