package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.ar;
import defpackage.l61;
import defpackage.n03;
import defpackage.oj2;
import defpackage.t13;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabGroupUiMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v implements t13 {
    public final /* synthetic */ a0 e;

    public v(a0 a0Var) {
        this.e = a0Var;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        a0 a0Var = this.e;
        if (oj2.D(a0Var.e) && a0Var.d(i2).contains(tab)) {
            return;
        }
        a0Var.e(tab.getId());
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        a0 a0Var = this.e;
        if (a0Var.w) {
            return;
        }
        a0Var.e(a0Var.i.i().getId());
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        if (i == 2 || i == 3 || i == 11) {
            return;
        }
        if (i != 5 || CachedFeatureFlags.b(ar.d.b("TabGridLayoutAndroid:enable_tab_group_auto_creation"), true)) {
            a0 a0Var = this.e;
            if (i == 13 && a0Var.w) {
                a0Var.f.p(n03.f, Integer.valueOf(a0Var.d(tab.getId()).size() - 1));
            }
            if (a0Var.w) {
                return;
            }
            a0Var.e(tab.getId());
        }
    }

    @Override // defpackage.t13
    public final void p0() {
        a0 a0Var = this.e;
        Tab tabI = a0Var.i.i();
        if (tabI != null) {
            l61 l61Var = a0Var.s;
            if (l61Var == null || !(l61Var.q(2) || a0Var.s.q(16))) {
                a0Var.e(tabI.getId());
            }
        }
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        a0 a0Var = this.e;
        if (a0Var.w && a0Var.d(tab.getId()).size() == 1) {
            a0Var.e(-1);
        }
    }
}
