package defpackage;

import defpackage.z2;
import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: StatusBarColorController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class at2 extends z2.b {
    public final /* synthetic */ dt2 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public at2(dt2 dt2Var, z2 z2Var) {
        super(z2Var, false);
        this.h = dt2Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        dt2 dt2Var = this.h;
        dt2Var.m = null;
        dt2Var.q = false;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void M(int i, Tab tab) {
        this.h.g();
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            this.h.m = null;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, Tab tab) {
        this.h.g();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        dt2 dt2Var = this.h;
        Tab tab2 = dt2Var.m;
        boolean z = tab2 != null && (tab2.u() instanceof h);
        if (dt2Var.q || z) {
            dt2Var.g();
        }
        dt2Var.q = z;
    }

    @Override // z2.b
    public final void y1(Tab tab) {
        dt2 dt2Var = this.h;
        dt2Var.m = tab;
        dt2Var.q = tab != null && (tab.u() instanceof h);
        if (tab == null) {
            return;
        }
        dt2Var.g();
    }
}
