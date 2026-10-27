package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.bz0;
import defpackage.df0;
import defpackage.ef2;
import defpackage.fs;
import defpackage.g03;
import defpackage.g72;
import defpackage.gz2;
import defpackage.hy1;
import defpackage.i03;
import defpackage.j03;
import defpackage.ji;
import defpackage.l61;
import defpackage.n03;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.q13;
import defpackage.q2;
import defpackage.qs1;
import defpackage.r03;
import defpackage.y13;
import defpackage.zp1;
import java.util.List;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tasks.tab_management.a0;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabGroupUiCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u implements a0.a, j03, hy1, a0.b {
    public final Activity e;
    public final Context f;
    public final PropertyModel g;
    public final bz0 h;
    public final TabGroupUiToolbarView i;
    public final ViewGroup j;
    public final ef2 k;
    public final zp1<Boolean> l;
    public final ji m;
    public final q2 n;
    public final nw2<Boolean> o;
    public final ViewGroup p;
    public final y13 q;
    public final qs1<l61> r;
    public final gz2 s;
    public final nw2<df0> t;
    public final TabContentManager u;
    public org.chromium.ui.modelutil.j v;
    public j w;
    public t x;
    public c0 y;
    public a0 z;

    public u(AppCompatActivity appCompatActivity, ViewGroup viewGroup, bz0 bz0Var, ef2 ef2Var, zp1 zp1Var, ji jiVar, q2 q2Var, nw2 nw2Var, y13 y13Var, TabContentManager tabContentManager, CompositorViewHolder compositorViewHolder, fs fsVar, gz2 gz2Var, qs1 qs1Var) {
        TraceEvent traceEventH0 = TraceEvent.H0("TabGroupUiCoordinator.constructor", null);
        try {
            this.e = appCompatActivity;
            Context context = viewGroup.getContext();
            this.f = context;
            this.h = bz0Var;
            this.k = ef2Var;
            this.l = zp1Var;
            this.g = new PropertyModel(n03.i);
            TabGroupUiToolbarView tabGroupUiToolbarView = (TabGroupUiToolbarView) LayoutInflater.from(context).inflate(R$layout.bottom_tab_strip_toolbar, viewGroup, false);
            this.i = tabGroupUiToolbarView;
            this.j = tabGroupUiToolbarView.getViewContainer();
            this.m = jiVar;
            this.n = q2Var;
            q2Var.f(this);
            this.o = nw2Var;
            this.q = y13Var;
            this.r = qs1Var;
            this.p = compositorViewHolder;
            this.s = gz2Var;
            this.t = fsVar;
            this.u = tabContentManager;
            viewGroup.addView(tabGroupUiToolbarView);
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

    public final void d() {
        Tab tabI;
        y13 y13Var = this.q;
        if (y13Var.u().d(false) instanceof g03) {
            l61 l61Var = this.r.get();
            if ((l61Var == null || !l61Var.q(2)) && (tabI = y13Var.i()) != null) {
                ((g03) y13Var.u().c()).V(tabI);
            }
        }
    }

    public final void e() {
        y13 y13Var = this.q;
        if (y13Var == null) {
            return;
        }
        q13 q13VarU = y13Var.u();
        if (q13VarU.d(false) instanceof g03) {
            g03 g03Var = (g03) q13VarU.d(false);
            g03 g03Var2 = (g03) q13VarU.d(true);
            oj2.R(g03Var.o + g03Var2.o, "TabGroups.UserGroupCount");
            if (oj2.C(this.f)) {
                int i = 0;
                for (int i2 = 0; i2 < g03Var.o; i2++) {
                    if (i03.a(CriticalPersistedTabData.m(g03Var.getTabAt(i2)).q) != null) {
                        i++;
                    }
                }
                for (int i3 = 0; i3 < g03Var2.o; i3++) {
                    if (i03.a(CriticalPersistedTabData.m(g03Var2.getTabAt(i3)).q) != null) {
                        i++;
                    }
                }
                oj2.R(i, "TabGroups.UserNamedGroupCount");
            }
        }
    }

    @Override // defpackage.hy1
    public final void f() {
        if (this.o.get().booleanValue()) {
            e();
            d();
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.z.getHandleBackPressChangedSupplier();
    }

    public final void k(List<Tab> list) {
        if (list != null && list.size() > 1) {
            ji jiVar = this.m;
            if (jiVar.j() == 0) {
                TabListRecyclerView tabListRecyclerView = this.y.f;
                if (!oj2.C(this.f)) {
                    jiVar = null;
                }
                r03.b("IPH_TabGroupsTapToSeeAnotherTab", tabListRecyclerView, jiVar);
            }
        }
        c0 c0Var = this.y;
        c0Var.getClass();
        c0Var.i(g72.d(list), false, false);
    }

    @Override // defpackage.vb
    public final int o() {
        return this.z.o();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.a0.b
    public void setupLeftButtonOnClickListener(View.OnClickListener onClickListener) {
        this.z.setupLeftButtonOnClickListener(onClickListener);
    }

    @Override // defpackage.hy1
    public final void h() {
    }
}
