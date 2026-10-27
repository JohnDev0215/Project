package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.aq1;
import defpackage.c01;
import defpackage.ef2;
import defpackage.g72;
import defpackage.gz2;
import defpackage.n3;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.p13;
import defpackage.t41;
import defpackage.wp2;
import defpackage.y13;
import defpackage.zp1;
import java.util.List;
import java.util.Objects;
import org.chromium.base.SysUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.c0;
import org.chromium.chrome.browser.tasks.tab_management.j;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.r;
import org.chromium.chrome.browser.tasks.tab_management.z0;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: TabGridDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j implements m.b {
    public final c0 e;
    public final m f;
    public final org.chromium.ui.modelutil.j g;
    public final ViewGroup h;
    public final aq1<Boolean> i;
    public final Activity j;
    public final y13 k;
    public final TabContentManager l;
    public z0 m;
    public final TabGridDialogView n;
    public final wp2 o;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [mz2] */
    /* JADX WARN: Type inference failed for: r4v0, types: [mz2] */
    public j(Activity activity, y13 y13Var, TabContentManager tabContentManager, gz2 gz2Var, ViewGroup viewGroup, o1 o1Var, s1 s1Var, c01 c01Var, ef2 ef2Var, j0.f fVar, ViewGroup viewGroup2) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.i = aq1Var;
        TraceEvent traceEventH0 = TraceEvent.H0("TabGridDialogCoordinator.constructor", null);
        try {
            this.j = activity;
            String str = c01Var == null ? "TabGridDialogFromStrip" : "TabGridDialogInSwitcher";
            this.k = y13Var;
            this.l = tabContentManager;
            PropertyModel propertyModel = new PropertyModel(q.z);
            this.h = viewGroup2;
            int i = R$id.dialog_parent_view;
            TabGridDialogView tabGridDialogView = (TabGridDialogView) viewGroup.findViewById(i);
            this.n = tabGridDialogView;
            if (tabGridDialogView == null) {
                LayoutInflater.from(activity).inflate(R$layout.tab_grid_dialog_layout, viewGroup, true);
                TabGridDialogView tabGridDialogView2 = (TabGridDialogView) viewGroup.findViewById(i);
                this.n = tabGridDialogView2;
                tabGridDialogView2.setupScrimCoordinator(ef2Var);
            }
            wp2 wp2Var = new wp2(activity, this.n.getSnackBarContainer(), null);
            this.o = wp2Var;
            final int i2 = 0;
            m mVar = new m(activity, this, propertyModel, y13Var, gz2Var, o1Var, new nw2(this) { // from class: mz2
                public final /* synthetic */ j f;

                {
                    this.f = this;
                }

                @Override // defpackage.nw2
                public final Object get() {
                    int i3 = i2;
                    j jVar = this.f;
                    switch (i3) {
                        case 0:
                            return jVar.e.a();
                        default:
                            Activity activity2 = jVar.j;
                            if (!oj2.C(activity2) && !oj2.E(activity2)) {
                                return null;
                            }
                            if (jVar.m == null) {
                                int i4 = 3;
                                int i5 = SysUtils.isLowEndDevice() ? 3 : 0;
                                Activity activity3 = jVar.j;
                                ViewGroup viewGroup3 = (ViewGroup) jVar.n.findViewById(R$id.dialog_container_view);
                                y13 y13Var2 = jVar.k;
                                TabContentManager tabContentManager2 = jVar.l;
                                c0 c0Var = jVar.e;
                                Objects.requireNonNull(c0Var);
                                jVar.m = new z0(activity3, viewGroup3, y13Var2, tabContentManager2, new x10(i4, c0Var), i5, jVar.h, false, jVar.o);
                            }
                            return jVar.m.f;
                    }
                }
            }, c01Var, wp2Var, str);
            this.f = mVar;
            c0 c0Var = new c0(0, activity, y13Var, new c01(23, tabContentManager), null, false, s1Var, mVar.o, 1, null, null, viewGroup, false, str, viewGroup2, null, mVar);
            this.e = c0Var;
            TabListRecyclerView tabListRecyclerView = c0Var.f;
            TabGroupUiToolbarView tabGroupUiToolbarView = (TabGroupUiToolbarView) LayoutInflater.from(activity).inflate(R$layout.bottom_tab_grid_toolbar, (ViewGroup) tabListRecyclerView, false);
            tabGroupUiToolbarView.a();
            if (!oj2.C(activity)) {
                tabGroupUiToolbarView.k.setFocusable(false);
                tabGroupUiToolbarView.k.setBackgroundColor(0);
            }
            if (!oj2.C(activity) && !oj2.E(activity)) {
                tabGroupUiToolbarView.l.removeView(tabGroupUiToolbarView.g);
            }
            this.g = org.chromium.ui.modelutil.j.b(propertyModel, new r.a(tabGroupUiToolbarView, tabListRecyclerView, this.n), new y0(2));
            aq1Var.r(Boolean.valueOf(isVisible()));
            propertyModel.a(new k.a() { // from class: nz2
                @Override // org.chromium.ui.modelutil.k.a
                public final void a(k kVar, Object obj) {
                    j jVar = this.e;
                    jVar.i.r(Boolean.valueOf(jVar.isVisible()));
                }
            });
            final int i3 = 1;
            mVar.f(new nw2(this) { // from class: mz2
                public final /* synthetic */ j f;

                {
                    this.f = this;
                }

                @Override // defpackage.nw2
                public final Object get() {
                    int i32 = i3;
                    j jVar = this.f;
                    switch (i32) {
                        case 0:
                            return jVar.e.a();
                        default:
                            Activity activity2 = jVar.j;
                            if (!oj2.C(activity2) && !oj2.E(activity2)) {
                                return null;
                            }
                            if (jVar.m == null) {
                                int i4 = 3;
                                int i5 = SysUtils.isLowEndDevice() ? 3 : 0;
                                Activity activity3 = jVar.j;
                                ViewGroup viewGroup3 = (ViewGroup) jVar.n.findViewById(R$id.dialog_container_view);
                                y13 y13Var2 = jVar.k;
                                TabContentManager tabContentManager2 = jVar.l;
                                c0 c0Var2 = jVar.e;
                                Objects.requireNonNull(c0Var2);
                                jVar.m = new z0(activity3, viewGroup3, y13Var2, tabContentManager2, new x10(i4, c0Var2), i5, jVar.h, false, jVar.o);
                            }
                            return jVar.m.f;
                    }
                }
            }, fVar);
            c0Var.c(null);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.m.b
    public final void N0(List<Tab> list) {
        c0 c0Var = this.e;
        c0Var.getClass();
        c0Var.i(g72.d(list), false, false);
        m mVar = this.f;
        y13 y13Var = mVar.g;
        if (list == null) {
            mVar.v = -1;
        } else {
            p13 p13VarC = y13Var.u().c();
            mVar.v = p13VarC.getTabAt(p13VarC.r(list.get(0))).getId();
        }
        int i = mVar.v;
        m.b bVar = mVar.k;
        PropertyModel propertyModel = mVar.f;
        if (i == -1) {
            PropertyModel.n nVar = q.h;
            if (propertyModel.k(nVar)) {
                propertyModel.l(nVar, false);
                bVar.p();
                propertyModel.p(q.k, null);
                return;
            }
            return;
        }
        m.a aVar = mVar.n;
        if (aVar != null) {
            PropertyModel.r<View> rVar = q.k;
            c0 c0Var2 = ((o1) ((c01) aVar).f).i;
            RecyclerView.a0 a0VarF = c0Var2.f.F(c0Var2.e.f.F(i));
            propertyModel.p(rVar, a0VarF != null ? a0VarF.e : null);
        } else {
            propertyModel.p(q.k, null);
        }
        mVar.l();
        if (mVar.v != y13Var.k()) {
            propertyModel.p(q.q, 0);
        } else {
            propertyModel.p(q.q, Integer.valueOf(mVar.b(mVar.v).indexOf(y13Var.s(mVar.v))));
        }
        propertyModel.p(q.j, mVar.p);
        bVar.k1();
        propertyModel.l(q.h, true);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.m.b
    public final void W(boolean z) {
        this.f.e(false);
    }

    public final void destroy() {
        this.e.onDestroy();
        m mVar = this.f;
        k kVar = mVar.i;
        y13 y13Var = mVar.g;
        if (kVar != null) {
            y13Var.u().e(kVar);
        }
        y13Var.l(mVar.h);
        t41.f.h(mVar.u);
        this.g.c();
        z0 z0Var = this.m;
        if (z0Var != null) {
            z0Var.a();
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.i;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.m.b
    public final boolean isVisible() {
        return this.f.f.k(q.h);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.m.b
    public final void k1() {
        this.e.e();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.m.b
    public final boolean m() {
        if (!isVisible()) {
            return false;
        }
        o();
        return true;
    }

    @Override // defpackage.vb
    public final int o() {
        this.f.e(true);
        n3.X0("TabGridDialog.Exit");
        return isVisible() ? 1 : 0;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.m.b
    public final void p() {
        c0 c0Var = this.e;
        c0Var.d();
        c0Var.k();
    }
}
