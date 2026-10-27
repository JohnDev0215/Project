package defpackage;

import android.graphics.Insets;
import android.graphics.Rect;
import android.view.ViewGroup;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.gesturenav.a;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.widget.InsetObserverView;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: HistoryNavigationCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class iv0 implements InsetObserverView.d, hy1 {
    public final ql0 e = new ql0(6, this);
    public ViewGroup f;
    public a g;
    public InsetObserverView h;
    public i20 i;
    public q2 j;
    public tb k;
    public Tab l;
    public boolean m;
    public wk1 n;
    public pt1 o;
    public Callback<zd3> p;
    public Callback<zd3> q;

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.d
    public final void a(int i) {
        boolean z = this.m;
        boolean zB = b();
        this.m = zB;
        if (zB != z) {
            c();
        }
    }

    public final boolean b() {
        ViewGroup viewGroup = this.f;
        if (viewGroup == null || !viewGroup.isAttachedToWindow()) {
            return this.m;
        }
        Insets systemGestureInsets = this.f.getRootWindowInsets().getSystemGestureInsets();
        return systemGestureInsets.left == 0 && systemGestureInsets.right == 0;
    }

    public final void c() {
        if (this.m) {
            Tab tab = this.l;
            WebContents webContentsC = tab != null ? tab.c() : null;
            if (this.l == null || webContentsC != null) {
                if (this.n == null) {
                    PropertyModel propertyModel = new PropertyModel(PropertyModel.e(ns0.h));
                    j.b(propertyModel, this.g, new cg(7));
                    wk1 wk1Var = new wk1(propertyModel, this.g, this.k);
                    this.n = wk1Var;
                    this.p.onResult(wk1Var);
                }
                wk1 wk1Var2 = this.n;
                Tab tab2 = this.l;
                wk1Var2.g = tab2 == null || tab2.c() == null || tab2.c().V() == null ? null : this.l;
            }
        }
    }

    @Override // defpackage.hy1
    public final void f() {
        this.f.post(this.e);
    }

    @Override // defpackage.hy1
    public final void h() {
    }

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.d
    public final void m(Rect rect) {
    }
}
