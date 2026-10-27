package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabThemeColorHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e53 extends nh0 {
    public final Tab e;
    public final Callback f;

    public e53(dr drVar, Tab tab) {
        this.e = tab;
        this.f = drVar;
        tab.x(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        tab.t(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void R0(TabImpl tabImpl) {
        y1(tabImpl, false);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (navigationHandle.l != 0) {
            y1(tabImpl, true);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void x0(TabImpl tabImpl) {
        y1(tabImpl, false);
    }

    public final void y1(TabImpl tabImpl, boolean z) {
        int iJ = tabImpl.E;
        if (z) {
            iJ = tabImpl.g.j();
        }
        this.f.onResult(Integer.valueOf(iJ));
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
    }
}
