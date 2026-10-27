package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: FullscreenHtmlApiHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class nq0 extends j23 {
    public final /* synthetic */ y13 i;
    public final /* synthetic */ qq0 j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nq0(qq0 qq0Var, y13 y13Var, y13 y13Var2) {
        super(y13Var);
        this.j = qq0Var;
        this.i = y13Var2;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void S0(Tab tab, boolean z) {
        if (z && tab == this.i.i()) {
            this.j.getClass();
            Runnable runnable = tab != null ? (Runnable) ly2.a(tab).b("EnterFullscreen") : null;
            if (runnable != null) {
                qq0.n(tab, null);
            }
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (navigationHandle.c || tabImpl != this.i.i()) {
            return;
        }
        this.j.i();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        this.j.m(tab.d());
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
    }
}
