package defpackage;

import android.content.res.Resources;
import android.view.View;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.permissions.PermissionDialogController;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: StatusCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class et2 implements View.OnClickListener, f.a {
    public final StatusView e;
    public final wt2 f;
    public final PropertyModel g;
    public final boolean h;
    public final a i;
    public f j;
    public boolean k;

    /* JADX INFO: compiled from: StatusCoordinator.java */
    public interface a {
        void m(Tab tab, wq wqVar);
    }

    public et2(boolean z, StatusView statusView, f fVar, rs1 rs1Var, jg2 jg2Var, nw2 nw2Var, g3 g3Var, a aVar, aq1 aq1Var, vj vjVar) {
        this.h = z;
        this.e = statusView;
        this.j = fVar;
        this.i = aVar;
        PropertyModel propertyModel = new PropertyModel(xt2.q);
        this.g = propertyModel;
        j.b(propertyModel, statusView, new cu2());
        wt2 wt2Var = new wt2(propertyModel, statusView.getResources(), statusView.getContext(), z, fVar, PermissionDialogController.a.a, jg2Var, rs1Var, nw2Var, new su1(xz.a(statusView.getContext()), statusView.getSecurityView()), g3Var, aq1Var);
        this.f = wt2Var;
        Resources resources = statusView.getResources();
        wt2Var.p = resources.getDimensionPixelSize(R$dimen.location_bar_end_padding) + resources.getDimensionPixelSize(pr1.e(statusView.getContext()) ? R$dimen.location_bar_start_padding_modern : R$dimen.location_bar_start_padding) + resources.getDimensionPixelSize(R$dimen.location_bar_status_icon_bg_size) + resources.getDimensionPixelSize(R$dimen.location_bar_min_url_width);
        wt2Var.q = resources.getDimensionPixelSize(R$dimen.location_bar_status_separator_spacer) + resources.getDimensionPixelSize(R$dimen.location_bar_status_separator_width);
        wt2Var.r = resources.getDimensionPixelSize(R$dimen.location_bar_min_verbose_status_text_width);
        a();
        b();
        this.j.l(this);
        statusView.setBrowserControlsVisibilityDelegate(vjVar);
    }

    public final void a() {
        int iC = this.j.c(this.h);
        int iM = this.j.m();
        int iP = this.j.p();
        wt2 wt2Var = this.f;
        wt2Var.u = iC;
        wt2Var.v = iM;
        wt2Var.w = iP;
        wt2Var.f(0);
    }

    public final void b() {
        boolean z;
        int iR = this.j.r();
        boolean zS = this.j.s();
        boolean zH = this.j.h();
        wt2 wt2Var = this.f;
        boolean z2 = true;
        if (wt2Var.s != iR) {
            wt2Var.s = iR;
            z = true;
        } else {
            z = false;
        }
        if (wt2Var.l != zH) {
            wt2Var.l = zH;
            z = true;
        }
        if (wt2Var.m != zS) {
            wt2Var.m = zS;
        } else {
            z2 = z;
        }
        if (z2) {
            wt2Var.h();
            wt2Var.f(0);
            wt2Var.e();
        }
    }

    @Override // org.chromium.chrome.browser.omnibox.f.a
    public final void f() {
        a();
        b();
    }

    @Override // org.chromium.chrome.browser.omnibox.f.a
    public final void j() {
        this.f.g();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.k || !this.j.q() || this.j.d().c() == null || this.j.e()) {
            return;
        }
        Tab tabD = this.j.d();
        wt2 wt2Var = this.f;
        int i = wt2Var.E;
        this.i.m(tabD, i != -1 ? new wq(i, false) : wt2Var.f101J ? new wq(-1, true) : wq.a());
        int i2 = wt2Var.E;
        qu1 qu1Var = wt2Var.G;
        if (i2 != -1) {
            qu1Var.a(1);
        } else if (wt2Var.f101J) {
            qu1Var.a(5);
        }
        wt2Var.c();
        wt2Var.f(0);
    }

    @Override // org.chromium.chrome.browser.omnibox.f.a
    public final void r() {
        wt2 wt2Var = this.f;
        boolean z = wt2Var.A.isIncognito() && !wt2Var.o;
        PropertyModel.n nVar = xt2.c;
        PropertyModel propertyModel = wt2Var.e;
        propertyModel.l(nVar, z);
        propertyModel.p(xt2.l, null);
        propertyModel.m(xt2.j, 1.0f);
        propertyModel.l(xt2.e, false);
    }
}
