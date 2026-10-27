package defpackage;

import J.N;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabModelSelectorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c23 extends j23 {
    public final /* synthetic */ d23 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c23(d23 d23Var, a23 a23Var) {
        super(a23Var);
        this.i = d23Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I0(TabImpl tabImpl) {
        if (hd2.z1(tabImpl)) {
            this.i.s.f(tabImpl.getId());
        }
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            d23 d23Var = this.i;
            if (d23Var.k) {
                return;
            }
            d23Var.g(tab.isIncognito()).o(tab);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        TabContentManager tabContentManager = this.i.s;
        int id = tab.getId();
        GURL url = tab.getUrl();
        long j = tabContentManager.e;
        if (j != 0) {
            N.MO5IR90z(j, id, url);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void s(TabImpl tabImpl) {
        this.i.a(tabImpl);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void x0(TabImpl tabImpl) {
        int id = tabImpl.getId();
        d23 d23Var = this.i;
        if (d23Var.j(id) == d23Var.z()) {
            TabContentManager tabContentManager = d23Var.s;
            int id2 = tabImpl.getId();
            GURL url = tabImpl.getUrl();
            long j = tabContentManager.e;
            if (j != 0) {
                N.MO5IR90z(j, id2, url);
            }
        }
    }
}
