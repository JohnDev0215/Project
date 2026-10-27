package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import defpackage.kj;
import defpackage.l23;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.components.browser_ui.modaldialog.TabModalPresenter;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ChromeTabModalPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pr extends TabModalPresenter implements kj.a {
    public l23.b A;
    public final Activity m;
    public final nw2<l23> n;
    public final nw2<g> o;
    public final rq0 p;
    public final mj q;
    public final a r;
    public final y13 s;
    public Tab t;
    public ViewGroup u;
    public boolean v;
    public boolean w;
    public View x;
    public int y;
    public boolean z;

    /* JADX INFO: compiled from: ChromeTabModalPresenter.java */
    public static class a extends lj {
    }

    public pr(Activity activity, nw2 nw2Var, nw2 nw2Var2, rq0 rq0Var, mj mjVar, y13 y13Var) {
        super(activity);
        this.m = activity;
        this.n = nw2Var;
        this.o = nw2Var2;
        this.p = rq0Var;
        this.q = mjVar;
        mjVar.o(this);
        this.r = new a();
        this.s = y13Var;
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        if (this.f != null && this.w && a8.g(this.q)) {
            this.w = false;
            g();
        }
    }

    @Override // org.chromium.components.browser_ui.modaldialog.TabModalPresenter, bi1.b
    public final void e(PropertyModel propertyModel) {
        this.w = false;
        this.n.get().f(this.A);
        this.A = null;
        super.e(propertyModel);
    }

    @Override // org.chromium.components.browser_ui.modaldialog.TabModalPresenter
    public final ViewGroup f() {
        int i = R$id.tab_modal_dialog_container_stub;
        Activity activity = this.m;
        ViewStub viewStub = (ViewStub) activity.findViewById(i);
        viewStub.setLayoutResource(R$layout.modal_dialog_container);
        ViewGroup viewGroup = (ViewGroup) viewStub.inflate();
        viewGroup.setVisibility(8);
        viewGroup.setClickable(true);
        this.u = (ViewGroup) viewGroup.getParent();
        this.x = activity.findViewById(R$id.tab_modal_dialog_container_sibling_view);
        Resources resources = activity.getResources();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
        marginLayoutParams.width = -1;
        marginLayoutParams.height = -1;
        int i2 = R$dimen.tab_modal_scrim_vertical_margin;
        int dimensionPixelSize = resources.getDimensionPixelSize(i2);
        mj mjVar = this.q;
        marginLayoutParams.topMargin = mjVar.l() - dimensionPixelSize;
        marginLayoutParams.bottomMargin = mjVar.g();
        viewGroup.setLayoutParams(marginLayoutParams);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(i2);
        View viewFindViewById = viewGroup.findViewById(R$id.scrim);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) viewFindViewById.getLayoutParams();
        marginLayoutParams2.width = -1;
        marginLayoutParams2.height = -1;
        marginLayoutParams2.topMargin = dimensionPixelSize2;
        viewFindViewById.setLayoutParams(marginLayoutParams2);
        return viewGroup;
    }

    @Override // org.chromium.components.browser_ui.modaldialog.TabModalPresenter
    public final void i(boolean z) {
        nw2<g> nw2Var = this.o;
        if (nw2Var.f()) {
            ImageButton imageButtonH = nw2Var.get().h();
            if (!z) {
                WebContents webContentsC = this.t.c();
                if (webContentsC != null) {
                    h(webContentsC, false);
                }
                o(false);
                imageButtonH.setEnabled(true);
                this.t = null;
                return;
            }
            Tab tabI = this.s.i();
            this.t = tabI;
            WebContents webContentsC2 = tabI.c();
            if (webContentsC2 != null) {
                h(webContentsC2, true);
            }
            o(true);
            nw2Var.get().v(12, false);
            imageButtonH.setEnabled(false);
        }
    }

    @Override // org.chromium.components.browser_ui.modaldialog.TabModalPresenter
    public final void k() {
        boolean z = this.z;
        mj mjVar = this.q;
        if (z) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.h.getLayoutParams();
            marginLayoutParams.topMargin = mjVar.l() - this.m.getResources().getDimensionPixelSize(R$dimen.tab_modal_scrim_vertical_margin);
            marginLayoutParams.bottomMargin = this.y;
            this.h.setLayoutParams(marginLayoutParams);
            this.z = false;
        }
        if (a8.g(mjVar)) {
            g();
        } else {
            this.w = true;
        }
        this.A = this.n.get().d(1);
    }

    @Override // kj.a
    public final void l(int i) {
        this.y = i;
        this.z = true;
    }

    @Override // org.chromium.components.browser_ui.modaldialog.TabModalPresenter
    public final void m(boolean z) {
        super.m(z);
        if (z == this.v) {
            return;
        }
        this.v = z;
        if (z) {
            this.h.bringToFront();
        } else {
            uh3.l(this.h);
            uh3.i(this.u, this.h, this.x, false);
        }
    }

    @Override // kj.a
    public final void n() {
        this.z = true;
    }

    public final void o(boolean z) {
        ly2.a(this.t).c(Boolean.valueOf(z), "isTabModalDialogShowing");
        Tab tab = this.t;
        a aVar = this.r;
        aVar.getClass();
        if (tab != null) {
            ly2 ly2VarA = ly2.a(tab);
            Object objB = Boolean.FALSE;
            if (ly2VarA.e.containsKey("isTabModalDialogShowing")) {
                objB = ly2VarA.b("isTabModalDialogShowing");
            }
            aVar.r(Integer.valueOf(((Boolean) objB).booleanValue() ? 1 : 3));
        }
        du3 du3VarA = a8.A();
        boolean z2 = du3VarA != null && du3VarA.i0();
        if (!z2) {
            this.p.b(this.t);
        }
        mj mjVar = this.q;
        if (z && (this.t.c().y().i() || z2)) {
            mjVar.n(true);
        } else if (z || !z2) {
            py2.d(this.t, !mjVar.h());
        } else {
            mjVar.m();
        }
    }
}
