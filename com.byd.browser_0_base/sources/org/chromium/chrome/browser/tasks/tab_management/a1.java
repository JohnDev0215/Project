package org.chromium.chrome.browser.tasks.tab_management;

import J.N;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import defpackage.ac2;
import defpackage.aq1;
import defpackage.bq;
import defpackage.bz;
import defpackage.e33;
import defpackage.j33;
import defpackage.mw;
import defpackage.nm1;
import defpackage.oj2;
import defpackage.sk;
import defpackage.v62;
import defpackage.wp2;
import defpackage.y13;
import defpackage.yp;
import defpackage.yp2;
import defpackage.z7;
import defpackage.zp1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$color;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorToolbar;
import org.chromium.chrome.browser.tasks.tab_management.v0;
import org.chromium.chrome.browser.tasks.tab_management.z0;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabSelectionEditorMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a1 implements z0.d, v0.a {
    public final Context e;
    public final y13 f;
    public final c0 g;
    public final z0.c h;
    public final PropertyModel i;
    public final org.chromium.components.browser_ui.widget.selectable_list.a<Integer> j;
    public final TabSelectionEditorToolbar k;
    public final boolean l;
    public final b1 m;
    public final c1 n;
    public w0 o;
    public z0.e p;
    public final aq1<Boolean> q;
    public final ArrayList r;
    public org.chromium.ui.modelutil.h<PropertyModel, v62> s;
    public d1 t;
    public final wp2 u;
    public final TabSelectionEditorLayout v;

    /* JADX INFO: compiled from: TabSelectionEditorMediator.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            a1.this.p.a();
        }
    }

    /* JADX INFO: compiled from: TabSelectionEditorMediator.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            y13 y13Var;
            ArrayList arrayList = new ArrayList();
            a1 a1Var = a1.this;
            Iterator<Integer> it = a1Var.j.c.iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                y13Var = a1Var.f;
                if (!zHasNext) {
                    break;
                }
                arrayList.add(z7.F(y13Var.z(), it.next().intValue()));
            }
            w0 w0Var = a1Var.o;
            if (w0Var == null) {
                return;
            }
            w0Var.a(arrayList, y13Var);
        }
    }

    public a1(Context context, y13 y13Var, c0 c0Var, z0.b bVar, PropertyModel propertyModel, org.chromium.components.browser_ui.widget.selectable_list.a aVar, TabSelectionEditorToolbar tabSelectionEditorToolbar, boolean z, wp2 wp2Var, TabSelectionEditorLayout tabSelectionEditorLayout) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.q = aq1Var;
        this.r = new ArrayList();
        a aVar2 = new a();
        b bVar2 = new b();
        this.e = context;
        this.f = y13Var;
        this.g = c0Var;
        this.h = bVar;
        this.i = propertyModel;
        this.j = aVar;
        this.k = tabSelectionEditorToolbar;
        this.l = z;
        this.u = wp2Var;
        this.v = tabSelectionEditorLayout;
        propertyModel.p(e1.e, aVar2);
        propertyModel.p(e1.b, bVar2);
        if (z) {
            propertyModel.p(e1.l, new TabSelectionEditorToolbar.a() { // from class: i33
                @Override // org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorToolbar.a
                public final int a(ArrayList arrayList) {
                    return v0.a(arrayList, this.a.f);
                }
            });
        }
        this.m = new b1(this, y13Var);
        c1 c1Var = new c1(this);
        this.n = c1Var;
        y13Var.m(c1Var);
        h(y13Var.r());
        this.o = new w0(this, 1);
        this.p = new z0.e(context, this);
        aq1Var.r(Boolean.valueOf(e()));
        propertyModel.a(new mw(1, this));
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.z0.d
    public final void A(List<Tab> list, int i, TabListRecyclerView.b bVar) {
        wp2 wp2Var = this.u;
        TabSelectionEditorLayout tabSelectionEditorLayout = this.v;
        wp2Var.k = tabSelectionEditorLayout;
        yp2 yp2Var = wp2Var.f;
        if (yp2Var != null) {
            yp2Var.b(tabSelectionEditorLayout);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long l = ac2.g;
        if (l != null) {
            oj2.b0(jCurrentTimeMillis - l.longValue(), "Android.TabMultiSelectV2.TimeSinceLastShown");
        }
        ac2.g = Long.valueOf(jCurrentTimeMillis);
        this.g.e();
        ArrayList arrayList = this.r;
        arrayList.clear();
        arrayList.addAll(list);
        org.chromium.components.browser_ui.widget.selectable_list.a<Integer> aVar = this.j;
        aVar.b = true;
        aVar.f();
        if (i > 0) {
            HashSet hashSet = new HashSet();
            for (int i2 = 0; i2 < i; i2++) {
                hashSet.add(Integer.valueOf(list.get(i2).getId()));
            }
            aVar.h(hashSet);
        }
        ((z0.b) this.h).a(list, i, bVar, false);
        this.i.l(e1.a, true);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.z0.d
    public final void B(String str, Integer num, w0 w0Var, int i, z0.e eVar) {
        org.chromium.ui.modelutil.h<PropertyModel, v62> hVar = this.s;
        if (hVar != null) {
            hVar.clear();
        }
        PropertyModel propertyModel = this.i;
        if (str != null) {
            propertyModel.p(e1.c, str);
        }
        if (w0Var != null) {
            this.o = w0Var;
        }
        if (i != -1) {
            propertyModel.n(e1.d, i);
        }
        if (eVar != null) {
            this.p = eVar;
        }
        if (num != null) {
            propertyModel.n(e1.j, num.intValue());
        }
        propertyModel.n(e1.k, 0);
        h(this.f.r());
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.z0.d
    public final void F(List<v0> list, z0.e eVar) {
        y13 y13Var;
        org.chromium.ui.modelutil.h<PropertyModel, v62> hVar = this.s;
        org.chromium.components.browser_ui.widget.selectable_list.a<Integer> aVar = this.j;
        if (hVar == null) {
            this.s = new org.chromium.ui.modelutil.h<>();
            d1 d1Var = new d1(this.e, this.k.D0);
            this.t = d1Var;
            aVar.a(d1Var);
            org.chromium.ui.modelutil.h<PropertyModel, v62> hVar2 = this.s;
            hVar2.g(new org.chromium.ui.modelutil.c(hVar2, this.t, new j33()));
        }
        this.s.clear();
        Iterator<v0> it = list.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            y13Var = this.f;
            if (!zHasNext) {
                break;
            }
            v0 next = it.next();
            next.c = y13Var;
            next.e = aVar;
            next.d = this;
            next.f = Boolean.valueOf(this.l);
            org.chromium.ui.modelutil.h<PropertyModel, v62> hVar3 = this.s;
            PropertyModel propertyModel = next.b;
            hVar3.t(propertyModel);
            propertyModel.a(hVar3.g);
        }
        if (eVar != null) {
            this.p = eVar;
        }
        this.i.n(e1.k, 8);
        h(y13Var.r());
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.z0.d
    public final void b() {
        d(false);
    }

    public final void d(boolean z) {
        if (e()) {
            wp2 wp2Var = this.u;
            wp2Var.k = null;
            yp2 yp2Var = wp2Var.f;
            if (yp2Var != null) {
                yp2Var.b(null);
            }
            ac2.d(this.e, 0);
            if (!z) {
                f();
            }
            c0 c0Var = this.g;
            nm1 nm1Var = c0Var.p;
            if (nm1Var != null) {
                c0Var.f.removeOnLayoutChangeListener(nm1Var);
            }
            this.r.clear();
            z0.b bVar = (z0.b) this.h;
            bVar.a(null, 0, null, false);
            this.i.l(e1.a, false);
            z0 z0Var = z0.this;
            z0Var.c.d();
            z0Var.c.k();
        }
    }

    public final boolean e() {
        return this.i.k(e1.a);
    }

    public final void f() {
        z0 z0Var;
        Callback<TabListRecyclerView.b> callback;
        z0.b bVar = (z0.b) this.h;
        bVar.getClass();
        sk skVar = bq.a;
        if (!N.M09VlOh_("TabSelectionEditorV2") || (callback = (z0Var = z0.this).g) == null) {
            return;
        }
        callback.onResult(z0Var.c.a());
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.q;
    }

    public final void h(boolean z) {
        Context context = this.e;
        int iB = yp.b(context, z);
        int color = z ? context.getColor(R$color.incognito_tab_selection_editor_toolbar_bg_color) : oj2.n(R$attr.colorSurface, context, "TabUiThemeProvider");
        ColorStateList colorStateListB = bz.b(context, z ? R$color.default_text_color_light_list : R$color.default_text_color_list);
        PropertyModel.p pVar = e1.f;
        PropertyModel propertyModel = this.i;
        propertyModel.n(pVar, iB);
        propertyModel.n(e1.g, color);
        propertyModel.p(e1.h, colorStateListB);
        propertyModel.p(e1.i, colorStateListB);
        org.chromium.ui.modelutil.h<PropertyModel, v62> hVar = this.s;
        if (hVar == null) {
            return;
        }
        for (PropertyModel propertyModel2 : hVar) {
            propertyModel2.p(e33.m, colorStateListB);
            propertyModel2.p(e33.n, colorStateListB);
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.z0.d
    public final boolean isVisible() {
        return e();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.z0.d
    public final boolean m() {
        if (!e()) {
            return false;
        }
        this.p.a();
        return true;
    }

    @Override // defpackage.vb
    public final int o() {
        int i = !e() ? 1 : 0;
        this.p.a();
        return i;
    }
}
