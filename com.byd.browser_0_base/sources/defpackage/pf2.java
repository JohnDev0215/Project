package defpackage;

import android.view.View;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ScrollCaptureManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pf2 extends nh0 implements Callback<Tab> {
    public final zp1<Tab> e;
    public final qf2 f;
    public Tab g;
    public View h;

    public pf2(z2 z2Var) {
        rf2 rf2Var = new rf2();
        this.e = z2Var;
        this.f = rf2Var;
        z2Var.p(this);
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Tab tab) {
        Tab tab2 = tab;
        Tab tab3 = this.g;
        if (tab3 != null) {
            tab3.t(this);
        }
        this.g = tab2;
        ((rf2) this.f).a.a.b = tab2;
        if (tab2 != null) {
            tab2.x(this);
            u(tab2);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        View view = this.h;
        qf2 qf2Var = this.f;
        if (view != null) {
            ((rf2) qf2Var).getClass();
            view.setScrollCaptureHint(0);
            view.setScrollCaptureCallback(null);
            this.h = null;
        }
        if (tab.isNativePage() || tab.s() || tab.m()) {
            return;
        }
        View view2 = tab.getView();
        this.h = view2;
        if (view2 != null) {
            of2 of2Var = ((rf2) qf2Var).a;
            view2.setScrollCaptureHint(of2Var != null ? 2 : 0);
            view2.setScrollCaptureCallback(of2Var);
        }
    }
}
