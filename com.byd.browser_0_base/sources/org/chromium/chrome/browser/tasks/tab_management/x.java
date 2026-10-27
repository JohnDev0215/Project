package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.j23;
import defpackage.oj2;
import defpackage.y13;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabGroupUiMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x extends j23 {
    public final /* synthetic */ a0 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(a0 a0Var, y13 y13Var) {
        super(y13Var);
        this.i = a0Var;
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            destroy();
            this.i.u = null;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        a0 a0Var = this.i;
        if (a0Var.i.s(tab.getId()) == null) {
            return;
        }
        int size = a0Var.d(tab.getId()).size();
        if (!a0Var.w || size == 1) {
            size = 0;
        }
        oj2.R(size, "TabStrip.TabCountOnPageLoad");
    }
}
