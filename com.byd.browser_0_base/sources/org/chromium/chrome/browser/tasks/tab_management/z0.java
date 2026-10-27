package org.chromium.chrome.browser.tasks.tab_management;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ac2;
import defpackage.bg;
import defpackage.bq;
import defpackage.g33;
import defpackage.g72;
import defpackage.ke0;
import defpackage.o61;
import defpackage.sk;
import defpackage.v62;
import defpackage.vb;
import defpackage.wp2;
import defpackage.xa1;
import defpackage.y13;
import defpackage.zj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.n0;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabSelectionEditorCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z0 {
    public final y13 a;
    public final TabSelectionEditorLayout b;
    public final c0 c;
    public final org.chromium.components.browser_ui.widget.selectable_list.a<Integer> d;
    public final org.chromium.ui.modelutil.j e;
    public final a1 f;
    public final Callback<TabListRecyclerView.b> g;
    public h h;

    /* JADX INFO: compiled from: TabSelectionEditorCoordinator.java */
    public class a extends GridLayoutManager.b {
        public final /* synthetic */ RecyclerView.m c;

        public a(RecyclerView.m mVar) {
            this.c = mVar;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.b
        public final int c(int i) {
            if (z0.this.c.f.getAdapter().m(i) == 4) {
                return ((GridLayoutManager) this.c).F;
            }
            return 1;
        }
    }

    /* JADX INFO: compiled from: TabSelectionEditorCoordinator.java */
    public class b implements c {
        public b() {
        }

        public final void a(List<Tab> list, int i, TabListRecyclerView.b bVar, boolean z) {
            z0 z0Var = z0.this;
            z0Var.getClass();
            ArrayList arrayListD = g72.d(list);
            c0 c0Var = z0Var.c;
            c0Var.i(arrayListD, z, false);
            if (list != null && i > 0 && i < list.size()) {
                PropertyModel.j jVar = n0.a.a;
                HashMap mapE = PropertyModel.e(new v62[]{jVar});
                int i2 = zj.a;
                PropertyModel.d dVar = new PropertyModel.d();
                dVar.a = 3;
                mapE.put(jVar, dVar);
                PropertyModel propertyModel = new PropertyModel(mapE);
                j0 j0Var = c0Var.e;
                j0Var.getClass();
                j0Var.f.B(i, new xa1(4, propertyModel));
            }
            sk skVar = bq.a;
            if (!N.M09VlOh_("TabSelectionEditorV2") || bVar == null) {
                return;
            }
            ((LinearLayoutManager) c0Var.f.getLayoutManager()).g1(bVar.a, bVar.b);
        }
    }

    /* JADX INFO: compiled from: TabSelectionEditorCoordinator.java */
    public interface c {
    }

    /* JADX INFO: compiled from: TabSelectionEditorCoordinator.java */
    public interface d extends vb {
        void A(List<Tab> list, int i, TabListRecyclerView.b bVar);

        void B(String str, Integer num, w0 w0Var, int i, e eVar);

        void F(List<v0> list, e eVar);

        void b();

        boolean isVisible();

        boolean m();
    }

    /* JADX INFO: compiled from: TabSelectionEditorCoordinator.java */
    public static class e {
        public final d a;
        public final Context b;

        public e(Context context, d dVar) {
            this.b = context;
            this.a = dVar;
        }

        public void a() {
            ac2.d(this.b, 2);
            this.a.b();
        }
    }

    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v6 */
    public z0(Activity activity, ViewGroup viewGroup, y13 y13Var, TabContentManager tabContentManager, Callback<TabListRecyclerView.b> callback, int i, ViewGroup viewGroup2, boolean z, wp2 wp2Var) {
        j0.p ke0Var;
        ?? r11;
        org.chromium.components.browser_ui.widget.selectable_list.a<Integer> aVar = new org.chromium.components.browser_ui.widget.selectable_list.a<>();
        this.d = aVar;
        TraceEvent traceEventH0 = TraceEvent.H0("TabSelectionEditorCoordinator.constructor", null);
        try {
            this.a = y13Var;
            this.g = callback;
            TabSelectionEditorLayout tabSelectionEditorLayout = (TabSelectionEditorLayout) LayoutInflater.from(activity).inflate(R$layout.tab_selection_editor_layout, viewGroup, false).findViewById(R$id.selectable_list);
            this.b = tabSelectionEditorLayout;
            if (z) {
                h hVar = new h(activity, tabContentManager, y13Var);
                this.h = hVar;
                ke0Var = hVar;
            } else {
                ke0Var = new ke0(22, tabContentManager);
            }
            c0 c0Var = new c0(i, activity, y13Var, ke0Var, z ? new ke0(21, this) : null, z, null, null, 0, new g33(this), null, tabSelectionEditorLayout, false, "TabSelectionEditor", viewGroup2, null, null);
            TabListRecyclerView tabListRecyclerView = c0Var.f;
            this.c = c0Var;
            c0Var.c(null);
            h hVar2 = this.h;
            if (hVar2 != null) {
                r11 = 0;
                hVar2.q.f(hVar2.f.g(false).f());
            } else {
                r11 = 0;
            }
            c0Var.g.H(4, new o61(R$layout.divider_preference), new bg(23));
            RecyclerView.m layoutManager = tabListRecyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                ((GridLayoutManager) layoutManager).K = new a(layoutManager);
            }
            RecyclerView.e adapter = tabListRecyclerView.getAdapter();
            tabSelectionEditorLayout.getClass();
            tabSelectionEditorLayout.j(adapter, tabListRecyclerView);
            tabSelectionEditorLayout.s = (TabSelectionEditorToolbar) tabSelectionEditorLayout.k(R$layout.tab_selection_editor_toolbar, aVar, 0, 0, 0, null, true);
            tabSelectionEditorLayout.t = viewGroup;
            aVar.b = true;
            aVar.f();
            HashMap mapE = PropertyModel.e(e1.m);
            PropertyModel.n nVar = e1.a;
            int i2 = zj.a;
            PropertyModel.a aVar2 = new PropertyModel.a();
            aVar2.a = r11;
            mapE.put(nVar, aVar2);
            PropertyModel propertyModel = new PropertyModel(mapE);
            this.e = new org.chromium.ui.modelutil.j(propertyModel, tabSelectionEditorLayout, new y0(r11), r11);
            this.f = new a1(activity, y13Var, c0Var, new b(), propertyModel, aVar, tabSelectionEditorLayout.s, z, wp2Var, tabSelectionEditorLayout);
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

    public final void a() {
        this.c.onDestroy();
        this.b.q();
        a1 a1Var = this.f;
        a1Var.m.a();
        y13 y13Var = a1Var.f;
        if (y13Var != null) {
            y13Var.l(a1Var.n);
        }
        this.e.c();
        h hVar = this.h;
        if (hVar != null) {
            hVar.f.l(hVar.g);
        }
    }
}
