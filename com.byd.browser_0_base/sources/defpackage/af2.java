package defpackage;

import J.N;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ScreenshotTabObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class af2 extends nh0 implements lk3 {
    public int e;
    public int f;
    public Tab g;

    public af2(Tab tab) {
        this.g = tab;
        tab.x(this);
        this.f = 0;
    }

    public static af2 y1(Tab tab) {
        if (tab == null || !tab.isInitialized()) {
            return null;
        }
        af2 af2Var = tab.isInitialized() ? (af2) tab.B().b(af2.class) : null;
        if (af2Var != null) {
            return af2Var;
        }
        af2 af2Var2 = (af2) tab.B().d(af2.class, new af2(tab));
        tab.x(af2Var2);
        return af2Var2;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void D0(Tab tab, boolean z) {
        z1(tab);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        z1(tab);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void K(TabImpl tabImpl, boolean z) {
        z1(tabImpl);
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.g.t(this);
        this.g = null;
    }

    public final void z1(Tab tab) {
        int i = this.e;
        if (i > 0) {
            oj2.R(i, "Tab.Screenshot.ScreenshotsPerPage");
            oj2.U(this.f, 3, "Tab.Screenshot.Action");
            WebContents webContentsC = tab.c();
            if (webContentsC != null) {
                N.M$ejnyHh(webContentsC, "Tab.Screenshot", "HasOccurred");
            }
        }
        this.e = 0;
        this.f = 0;
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
    }
}
