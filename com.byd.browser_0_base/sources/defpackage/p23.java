package defpackage;

import android.content.Intent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TabUtils;

/* JADX INFO: compiled from: TabParentIntent.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p23 extends nh0 implements lk3 {
    public final Tab e;
    public nw2<Tab> f;
    public Intent g;

    public p23(TabImpl tabImpl) {
        this.e = tabImpl;
        tabImpl.x(this);
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.e.t(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void s(TabImpl tabImpl) {
        if (!(this.f.get() == tabImpl) || this.g == null || TabUtils.b(tabImpl).getIntent() == this.g) {
            return;
        }
        TabUtils.b(tabImpl).startActivity(this.g);
    }
}
