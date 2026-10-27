package defpackage;

import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: TabModelSelectorProfileSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f23 extends aq1<Profile> implements e23, w80 {
    public y13 h;

    public f23(g23 g23Var) {
        g23Var.p(new jb1(10, this));
    }

    @Override // defpackage.w80
    public final void destroy() {
        y13 y13Var = this.h;
        if (y13Var != null) {
            y13Var.l(this);
            this.h = null;
        }
    }

    @Override // defpackage.e23
    public final void g() {
        r(this.h.z().f());
    }

    @Override // defpackage.e23
    public final void k(TabModel tabModel) {
        Profile profileF = tabModel.f();
        if (profileF == null) {
            return;
        }
        r(profileF);
    }

    @Override // defpackage.e23
    public final void b() {
    }

    @Override // defpackage.e23
    public final void o() {
    }

    @Override // defpackage.e23
    public final void i(Tab tab) {
    }
}
