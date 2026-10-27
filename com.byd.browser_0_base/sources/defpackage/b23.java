package defpackage;

import J.N;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabModelSelectorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b23 implements e23 {
    public final /* synthetic */ d23 e;

    public b23(d23 d23Var) {
        this.e = d23Var;
    }

    @Override // defpackage.e23
    public final void i(Tab tab) {
        d23 d23Var = this.e;
        if (z7.F(d23Var.z(), tab.getId()) != null) {
            TabContentManager tabContentManager = d23Var.s;
            int id = tab.getId();
            GURL url = tab.getUrl();
            long j = tabContentManager.e;
            if (j != 0) {
                N.MO5IR90z(j, id, url);
            }
        }
    }
}
