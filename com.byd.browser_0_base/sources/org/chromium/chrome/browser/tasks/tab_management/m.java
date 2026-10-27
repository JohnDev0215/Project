package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ar;
import defpackage.bz;
import defpackage.c01;
import defpackage.f33;
import defpackage.gs;
import defpackage.gz2;
import defpackage.h03;
import defpackage.ie0;
import defpackage.l33;
import defpackage.mz2;
import defpackage.n3;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.on0;
import defpackage.oz2;
import defpackage.pz2;
import defpackage.sf;
import defpackage.t41;
import defpackage.vb;
import defpackage.wp2;
import defpackage.y13;
import defpackage.zx1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.p;
import org.chromium.chrome.browser.tasks.tab_management.q;
import org.chromium.chrome.browser.tasks.tab_management.s1;
import org.chromium.chrome.browser.tasks.tab_management.x0;
import org.chromium.chrome.browser.tasks.tab_management.z0;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabGridDialogMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m implements wp2.b, TabGridDialogView.f, p.a {
    public final Activity e;
    public final PropertyModel f;
    public final y13 g;
    public final l h;
    public final k i;
    public final gz2 j;
    public final b k;
    public final s1.d l;
    public final nw2<TabListRecyclerView.b> m;
    public final a n;
    public final zx1 p;
    public final String q;
    public h03 r;
    public nw2<z0.d> s;
    public boolean t;
    public oz2 u;
    public boolean w;
    public String x;
    public on0 y;
    public final Activity z;
    public int v = -1;
    public final c o = new c();

    /* JADX INFO: compiled from: TabGridDialogMediator.java */
    public interface a {
    }

    /* JADX INFO: compiled from: TabGridDialogMediator.java */
    public interface b extends vb {
        void N0(List<Tab> list);

        void W(boolean z);

        boolean isVisible();

        void k1();

        boolean m();

        void p();
    }

    /* JADX INFO: compiled from: TabGridDialogMediator.java */
    public class c implements j0.n {
        public c() {
        }
    }

    public m(Activity activity, b bVar, PropertyModel propertyModel, y13 y13Var, gz2 gz2Var, o1 o1Var, mz2 mz2Var, c01 c01Var, wp2 wp2Var, String str) {
        this.e = activity;
        this.f = propertyModel;
        this.g = y13Var;
        this.j = gz2Var;
        this.k = bVar;
        this.l = o1Var;
        this.m = mz2Var;
        this.n = c01Var;
        this.q = str;
        this.z = activity;
        this.i = new k(this, wp2Var);
        l lVar = new l(this);
        this.h = lVar;
        y13Var.m(lVar);
        k(activity, y13Var.r());
        this.p = new zx1(18, this);
        propertyModel.l(q.h, false);
        if (DeviceFormFactor.a(activity)) {
            propertyModel.p(q.i, this);
        }
    }

    public static int c(Tab tab) {
        return CriticalPersistedTabData.m(tab).q;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView.f
    public final void a() {
        b bVar = this.k;
        bVar.N0(null);
        bVar.p();
        this.f.p(q.k, null);
    }

    public final List<Tab> b(int i) {
        return this.g.u().c().B(i);
    }

    @Override // wp2.b
    public final void d(Object obj) {
        TabModel tabModelJ;
        boolean z = obj instanceof Integer;
        y13 y13Var = this.g;
        if (z) {
            int iIntValue = ((Integer) obj).intValue();
            TabModel tabModelJ2 = y13Var.j(iIntValue);
            if (tabModelJ2 == null) {
                return;
            }
            tabModelJ2.x(iIntValue);
            return;
        }
        List list = (List) obj;
        if (list.isEmpty() || (tabModelJ = y13Var.j(((Tab) list.get(0)).getId())) == null) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            tabModelJ.x(((Tab) it.next()).getId());
        }
    }

    public final void e(boolean z) {
        int i;
        PropertyModel.n nVar = q.h;
        PropertyModel propertyModel = this.f;
        if (propertyModel.k(nVar)) {
            if (z) {
                a aVar = this.n;
                if (aVar != null && (i = this.v) != -1) {
                    PropertyModel.r<View> rVar = q.k;
                    c0 c0Var = ((o1) ((c01) aVar).f).i;
                    RecyclerView.a0 a0VarF = c0Var.f.F(c0Var.e.f.F(i));
                    propertyModel.p(rVar, a0VarF == null ? null : a0VarF.e);
                }
            } else {
                propertyModel.p(q.k, null);
            }
            nw2<z0.d> nw2Var = this.s;
            if (nw2Var != null && nw2Var.f()) {
                this.s.get().b();
            }
            g();
            if (oj2.C(this.e)) {
                propertyModel.l(q.w, false);
            }
            if (propertyModel.j(q.i) != null) {
                propertyModel.l(nVar, false);
            } else {
                this.k.N0(null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v7, types: [oz2, t41$b] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void f(mz2 mz2Var, j0.f fVar) {
        this.s = mz2Var;
        this.r = fVar;
        this.g.u().a(this.i);
        this.y = new on0(17, this);
        PropertyModel.r<View.OnClickListener> rVar = q.a;
        ie0 ie0Var = new ie0(21, this);
        PropertyModel propertyModel = this.f;
        propertyModel.p(rVar, ie0Var);
        propertyModel.p(q.b, new gs(8, this));
        Activity activity = this.e;
        int i = 9;
        if (!oj2.C(activity)) {
            if (oj2.E(activity)) {
                propertyModel.p(q.s, new gs(i, this.y));
                return;
            }
            return;
        }
        ?? r5 = new t41.b() { // from class: oz2
            @Override // t41.b
            public final void a(boolean z) {
                m mVar = this.e;
                mVar.getClass();
                PropertyModel.n nVar = q.v;
                PropertyModel propertyModel2 = mVar.f;
                propertyModel2.l(nVar, z);
                if (z) {
                    return;
                }
                propertyModel2.l(q.w, false);
                mVar.g();
            }
        };
        this.u = r5;
        t41.f.a(r5);
        propertyModel.p(q.t, new pz2(this));
        propertyModel.p(q.u, new sf(3, this));
        if (!oj2.E(activity)) {
            i();
        }
        propertyModel.p(q.s, new gs(i, this.y));
    }

    public final void g() {
        if (b(this.v).size() < 2) {
            this.x = null;
        }
        if (this.x == null) {
            return;
        }
        Tab tabS = this.g.s(this.v);
        int length = this.x.length();
        PropertyModel propertyModel = this.f;
        Activity activity = this.e;
        if (length == 0) {
            this.r.a(c(tabS));
            int size = b(this.v).size();
            String quantityString = activity.getResources().getQuantityString(R$plurals.bottom_tab_grid_title_placeholder, size, Integer.valueOf(size));
            if (oj2.C(activity)) {
                propertyModel.p(q.y, activity.getResources().getQuantityString(R$plurals.accessibility_dialog_back_button, size, Integer.valueOf(size)));
            }
            propertyModel.p(q.c, quantityString);
            this.r.d(tabS, quantityString);
            return;
        }
        this.r.c(c(tabS), this.x);
        this.r.d(tabS, this.x);
        if (oj2.C(activity)) {
            int size2 = b(this.v).size();
            propertyModel.p(q.y, activity.getResources().getQuantityString(R$plurals.accessibility_dialog_back_button_with_group_name, size2, this.x, Integer.valueOf(size2)));
        }
        propertyModel.p(q.c, this.x);
        n3.X0("TabGridDialog.TabGroupNamedInDialog");
        this.x = null;
    }

    public final boolean h(int i) {
        if (this.s == null) {
            return false;
        }
        List<Tab> listB = b(i);
        i();
        this.s.get().A(listB, 0, oj2.E(this.e) ? this.m.get() : null);
        return true;
    }

    public final void i() {
        if (!this.s.f() || this.t) {
            return;
        }
        this.t = true;
        Activity activity = this.e;
        if (!oj2.E(activity)) {
            w0 w0Var = new w0(this.s.get(), 2);
            this.s.get().B(activity.getString(R$string.tab_grid_dialog_selection_mode_remove), Integer.valueOf(R$plurals.accessibility_tab_selection_dialog_remove_button), w0Var, 1, null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new f1(AppCompatResources.a(activity, R$drawable.ic_select_all_24dp), AppCompatResources.a(activity, R$drawable.ic_deselect_all_24dp)));
        arrayList.add(new f33(AppCompatResources.a(activity, R$drawable.ic_close_tabs_24dp)));
        arrayList.add(new l33(AppCompatResources.a(activity, R$drawable.ic_widgets)));
        if (CachedFeatureFlags.b(ar.d.b("TabSelectionEditorV2:enable_bookmarks"), false)) {
            int i = R$drawable.btn_star;
            Activity activity2 = this.z;
            arrayList.add(new x0(activity2, AppCompatResources.a(activity2, i), new x0.b()));
        }
        this.s.get().F(arrayList, null);
    }

    @Override // wp2.b
    public final void j(Object obj) {
        TabModel tabModelJ;
        boolean z = obj instanceof Integer;
        y13 y13Var = this.g;
        if (z) {
            int iIntValue = ((Integer) obj).intValue();
            TabModel tabModelJ2 = y13Var.j(iIntValue);
            if (tabModelJ2 == null) {
                return;
            }
            tabModelJ2.n(iIntValue);
            return;
        }
        List list = (List) obj;
        if (list.isEmpty() || (tabModelJ = y13Var.j(((Tab) list.get(0)).getId())) == null) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            tabModelJ.n(((Tab) it.next()).getId());
        }
    }

    public final void k(Context context, boolean z) {
        int color = z ? context.getColor(R$color.incognito_tab_grid_dialog_background_color) : oj2.n(R$attr.colorSurface, context, "TabUiThemeProvider");
        Activity activity = this.e;
        ColorStateList colorStateListB = z ? bz.b(activity, R$color.default_icon_color_light_tint_list) : bz.b(activity, R$color.default_icon_color_tint_list);
        int iR0 = n3.r0(context, z, false);
        int iR02 = n3.r0(context, z, true);
        int iS0 = n3.s0(context, z, false);
        int iS02 = n3.s0(context, z, true);
        PropertyModel.p pVar = q.f;
        PropertyModel propertyModel = this.f;
        propertyModel.n(pVar, color);
        propertyModel.p(q.g, colorStateListB);
        propertyModel.n(q.m, iR0);
        propertyModel.n(q.n, iR02);
        propertyModel.n(q.o, iS0);
        propertyModel.n(q.p, iS02);
    }

    public final void l() {
        String strB;
        List<Tab> listB = b(this.v);
        int size = listB.size();
        if (size == 0) {
            e(true);
            return;
        }
        h03 h03Var = this.r;
        PropertyModel propertyModel = this.f;
        Activity activity = this.e;
        if (h03Var == null || (strB = this.r.b(c(this.g.s(this.v)))) == null || listB.size() <= 1) {
            if (oj2.C(activity)) {
                propertyModel.p(q.y, activity.getResources().getQuantityString(R$plurals.accessibility_dialog_back_button, listB.size(), Integer.valueOf(listB.size())));
            }
            propertyModel.p(q.c, activity.getResources().getQuantityString(R$plurals.bottom_tab_grid_title_placeholder, size, Integer.valueOf(size)));
        } else {
            if (oj2.C(activity)) {
                propertyModel.p(q.y, activity.getResources().getQuantityString(R$plurals.accessibility_dialog_back_button_with_group_name, listB.size(), strB, Integer.valueOf(listB.size())));
            }
            propertyModel.p(q.c, strB);
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.p.a
    public final void o(int i) {
        if (h(i)) {
            n3.X0("TabMultiSelectV2.OpenLongPressInDialog");
        }
    }
}
