package defpackage;

import defpackage.bb0;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.browser_ui.widget.InsetObserverView;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: DisplayCutoutTabHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class cb0 implements lk3 {
    public final Tab e;
    public final bb0 f;
    public final a g;

    /* JADX INFO: compiled from: DisplayCutoutTabHelper.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
            bb0 bb0Var = cb0.this.f;
            if (windowAndroid != null) {
                bb0Var.b();
                return;
            }
            InsetObserverView insetObserverView = bb0Var.g;
            if (insetObserverView != null) {
                insetObserverView.f.c(bb0Var);
                bb0Var.g = null;
            }
            bb0Var.d(null);
            bb0Var.e = null;
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void S0(Tab tab, boolean z) {
            cb0.this.f.c();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void l0(int i, Tab tab) {
            cb0.this.f.c();
        }
    }

    /* JADX INFO: compiled from: DisplayCutoutTabHelper.java */
    public static class b implements bb0.a {
        public final Tab a;

        public b(TabImpl tabImpl) {
            this.a = tabImpl;
        }
    }

    public cb0(TabImpl tabImpl) {
        a aVar = new a();
        this.g = aVar;
        this.e = tabImpl;
        tabImpl.x(aVar);
        this.f = new bb0(new b(tabImpl));
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.e.t(this.g);
        bb0 bb0Var = this.f;
        InsetObserverView insetObserverView = bb0Var.g;
        if (insetObserverView != null) {
            insetObserverView.f.c(bb0Var);
            bb0Var.g = null;
        }
        bb0Var.d(null);
        bb0Var.e = null;
    }
}
