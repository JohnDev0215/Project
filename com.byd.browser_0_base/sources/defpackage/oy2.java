package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabBrowserControlsConstraintsHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oy2 extends nh0 {
    public final /* synthetic */ py2 e;

    public oy2(py2 py2Var) {
        this.e = py2Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        tab.t(this);
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            this.e.f();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Q(Tab tab, boolean z, boolean z2) {
        py2 py2Var = this.e;
        if (py2Var.b() == 1 && qy2.y1(tab).f == 0) {
            py2Var.c(1, false);
        } else {
            py2Var.e();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        boolean z = navigationHandle.g;
        py2 py2Var = this.e;
        if (py2Var.b() == 1 && z && qy2.y1(tabImpl).f == 0) {
            py2Var.c(1, false);
        } else {
            py2Var.e();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void y0(String str) {
        this.e.f();
    }
}
