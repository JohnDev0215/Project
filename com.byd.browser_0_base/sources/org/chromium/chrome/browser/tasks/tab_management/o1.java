package org.chromium.chrome.browser.tasks.tab_management;

import J.N;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.a8;
import defpackage.ac2;
import defpackage.ar;
import defpackage.bg;
import defpackage.bi1;
import defpackage.bq;
import defpackage.c01;
import defpackage.d43;
import defpackage.df0;
import defpackage.dr;
import defpackage.ef2;
import defpackage.f13;
import defpackage.f33;
import defpackage.g72;
import defpackage.gz2;
import defpackage.h33;
import defpackage.hy0;
import defpackage.i41;
import defpackage.jf1;
import defpackage.k42;
import defpackage.ke0;
import defpackage.kj;
import defpackage.ky2;
import defpackage.ll;
import defpackage.n3;
import defpackage.nr;
import defpackage.nw2;
import defpackage.ny0;
import defpackage.o61;
import defpackage.oj2;
import defpackage.p11;
import defpackage.p13;
import defpackage.q2;
import defpackage.qs1;
import defpackage.s62;
import defpackage.se1;
import defpackage.sk;
import defpackage.sy0;
import defpackage.t43;
import defpackage.tj1;
import defpackage.uj1;
import defpackage.v43;
import defpackage.v80;
import defpackage.vj1;
import defpackage.vz2;
import defpackage.wp2;
import defpackage.x10;
import defpackage.xa1;
import defpackage.y13;
import defpackage.z7;
import defpackage.zb;
import defpackage.zx1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.price_tracking.PriceDropNotificationManagerImpl;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.d;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.n0;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.p;
import org.chromium.chrome.browser.tasks.tab_management.s1;
import org.chromium.chrome.browser.tasks.tab_management.x0;
import org.chromium.chrome.browser.tasks.tab_management.z0;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabSwitcherCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o1 implements v80, n1, n1.c, s1.d, s1.b, s1.c, p.a {
    public final ViewGroup A;
    public final gz2 B;
    public boolean C;
    public i D;
    public final ke0 E;
    public final ViewGroup F;
    public final ViewGroup G;
    public final TabContentManager H;
    public org.chromium.chrome.browser.tasks.tab_management.a I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public ny0 f80J;
    public final c K;
    public o L;
    public final p1 M;
    public final wp2 N;
    public final Activity e;
    public final org.chromium.ui.modelutil.j f;
    public final q2 g;
    public final jf1 h;
    public final c0 i;
    public final s1 j;
    public final h k;
    public final ef2 l;
    public final boolean m;
    public j n;
    public final y13 o;
    public final int p;
    public final org.chromium.chrome.browser.tasks.tab_management.c q;
    public final tj1 r;
    public final nw2<df0> s;
    public final wp2 t;
    public final bi1 u;
    public z0 v;
    public e w;
    public ArrayList x;
    public d43 y;
    public final ky2 z;

    /* JADX INFO: compiled from: TabSwitcherCoordinator.java */
    public class a implements qs1<m.b> {
        public final s62<m.b> e = new s62<>();
        public final ThreadUtils.a f = new ThreadUtils.a();

        public a() {
        }

        @Override // defpackage.nw2
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final m.b get() {
            boolean z;
            this.f.getClass();
            o1 o1Var = o1.this;
            if (o1Var.n != null) {
                z = false;
            } else {
                o1Var.n = new j(o1Var.e, o1Var.o, o1Var.H, o1Var.B, o1Var.F, o1Var, o1Var.j, new c01(24, o1Var), o1Var.l, o1Var.i.e.E, o1Var.G);
                z = true;
            }
            s62<m.b> s62Var = this.e;
            if (z) {
                j jVar = o1Var.n;
                jVar.getClass();
                s62Var.b(jVar);
            }
            return s62Var.b;
        }

        @Override // defpackage.nw2
        public final boolean f() {
            return o1.this.n != null;
        }

        @Override // defpackage.qs1
        public final m.b h(Callback<m.b> callback) {
            this.f.getClass();
            this.e.g(callback);
            if (f()) {
                return get();
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: TabSwitcherCoordinator.java */
    public class c implements jf1.a {
        public c() {
        }

        @Override // jf1.a
        public final boolean a(int i, boolean z) {
            z0 z0Var;
            int i2 = R$id.menu_group_tabs;
            o1 o1Var = o1.this;
            if (i != i2 || (z0Var = o1Var.v) == null) {
                if (i != R$id.menu_select_tabs) {
                    return false;
                }
                o1Var.B();
                n3.X0("MobileMenuSelectTabs");
                return true;
            }
            a1 a1Var = z0Var.f;
            e eVar = o1Var.w;
            String str = eVar.a;
            Integer numValueOf = Integer.valueOf(eVar.b);
            e eVar2 = o1Var.w;
            a1Var.B(str, numValueOf, eVar2.c, 2, eVar2.d);
            o1Var.v.f.A(o1Var.o.u().c().D(), 0, null);
            n3.X0("MobileMenuGroupTabs");
            return true;
        }
    }

    /* JADX INFO: compiled from: TabSwitcherCoordinator.java */
    public interface d {
    }

    /* JADX INFO: compiled from: TabSwitcherCoordinator.java */
    public class e {
        public final String a;
        public final int b;
        public final w0 c;
        public final z0.e d;

        public e(String str, int i, w0 w0Var, z0.e eVar) {
            this.a = str;
            this.b = i;
            this.c = w0Var;
            this.d = eVar;
        }
    }

    public o1(final Activity activity, q2 q2Var, final y13 y13Var, TabContentManager tabContentManager, kj kjVar, gz2 gz2Var, jf1 jf1Var, ViewGroup viewGroup, tj1 tj1Var, ef2 ef2Var, int i, ViewGroup viewGroup2, nw2<df0> nw2Var, wp2 wp2Var, bi1 bi1Var, qs1<hy0> qs1Var, zb zbVar) {
        a aVar;
        ef2 ef2Var2;
        TraceEvent traceEventH0 = TraceEvent.H0("TabSwitcherCoordinator.constructor", null);
        try {
            this.e = activity;
            this.p = i;
            this.o = y13Var;
            this.A = viewGroup;
            int i2 = R$id.coordinator;
            this.F = (ViewGroup) activity.findViewById(i2);
            this.B = gz2Var;
            this.r = tj1Var;
            this.G = viewGroup2;
            this.H = tabContentManager;
            this.s = nw2Var;
            this.t = wp2Var;
            this.u = bi1Var;
            this.N = new wp2(activity, viewGroup2, null);
            PropertyModel propertyModel = new PropertyModel(b0.k);
            if (oj2.D(activity)) {
                if (DeviceFormFactor.a(viewGroup2.getContext())) {
                    ViewGroup viewGroup3 = (ViewGroup) activity.findViewById(i2);
                    ef2Var2 = new ef2(activity, new v43(), viewGroup3, viewGroup3.getContext().getColor(R$color.omnibox_focused_fading_background_color));
                } else {
                    ef2Var2 = ef2Var;
                }
                this.l = ef2Var2;
                this.m = true;
                aVar = new a();
            } else {
                this.l = null;
                this.m = false;
                this.n = null;
                aVar = null;
            }
            s1 s1Var = new s1(activity, this, propertyModel, y13Var, kjVar, viewGroup, this, this, tj1Var, i, qs1Var, zbVar, aVar);
            this.j = s1Var;
            this.M = new p1(s1Var);
            h hVar = new h(activity, tabContentManager, y13Var);
            this.k = hVar;
            g72.a aVar2 = new g72.a() { // from class: u43
                @Override // g72.a
                public final String e(Context context, g72 g72Var) {
                    int size = g72.e(context, g72Var, y13Var).size();
                    return size == 1 ? g72Var.i() : activity.getResources().getQuantityString(R$plurals.bottom_tab_grid_title_placeholder, size, Integer.valueOf(size));
                }
            };
            long jUptimeMillis = SystemClock.uptimeMillis();
            c0 c0Var = new c0(i, activity, y13Var, hVar, aVar2, true, s1Var, null, 1, null, this, viewGroup, true, "GridTabSwitcher", viewGroup2, null, this);
            org.chromium.ui.modelutil.m mVar = c0Var.g;
            this.i = c0Var;
            int i3 = 1;
            this.f = org.chromium.ui.modelutil.j.b(propertyModel, c0Var.f, new y0(i3));
            oj2.b0(SystemClock.uptimeMillis() - jUptimeMillis, "Android.TabSwitcher.SetupRecyclerView.Time");
            if (oj2.C(activity)) {
                s1Var.u0(new b());
            }
            this.q = new org.chromium.chrome.browser.tasks.tab_management.c(activity, new nr(1, y13Var), new sy0(2, this));
            this.h = jf1Var;
            if (i == 0) {
                if (bq.s.a() || (oj2.D(viewGroup2.getContext()) && !v(i))) {
                    mVar.H(3, new o61(R$layout.tab_grid_message_card_item), new bg(24));
                }
                if (!z7.R() && !ny0.b()) {
                    i3 = 0;
                }
                if (i3 != 0) {
                    mVar.H(6, new o61(R$layout.large_message_card_item), new y0(4));
                }
                if (z7.R()) {
                    if (a8.P() ? N.M6bsIDpc("CommercePriceTracking", "enable_price_tracking", false) : false) {
                        ke0 ke0Var = new ke0(23, this);
                        this.E = ke0Var;
                        SharedPreferencesManager.getInstance().a(ke0Var);
                    }
                }
            }
            if (i == 0 || i == 3) {
                c cVar = new c();
                this.K = cVar;
                ((ChromeActivity) jf1Var).R0.add(cVar);
            } else {
                this.K = null;
            }
            if (bq.F.a()) {
                this.z = new ky2(y13Var);
            }
            this.g = q2Var;
            q2Var.f(this);
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

    public static boolean v(int i) {
        i41 i41Var = ar.d;
        StringBuilder sb = new StringBuilder();
        sb.append("StartSurfaceAndroid");
        sb.append(":");
        sb.append("show_tabs_in_mru_order");
        return CachedFeatureFlags.b(i41Var.b(sb.toString()), false) && i == 2;
    }

    public final boolean A(PropertyModel propertyModel) {
        Integer numValueOf = Integer.valueOf(propertyModel.h(f.u));
        int iIntValue = numValueOf != null ? numValueOf.intValue() : 0;
        if (iIntValue == 2) {
            return true;
        }
        if (this.o.r()) {
            if (iIntValue != 1) {
                return false;
            }
        } else if (iIntValue != 0) {
            return false;
        }
        return true;
    }

    public final void B() {
        z0 z0Var = this.v;
        c0 c0Var = this.i;
        if (z0Var == null) {
            int i = this.p;
            int i2 = i == 2 ? 0 : i;
            Activity activity = this.e;
            ViewGroup viewGroup = this.F;
            y13 y13Var = this.o;
            TabContentManager tabContentManager = this.H;
            Objects.requireNonNull(c0Var);
            z0 z0Var2 = new z0(activity, viewGroup, y13Var, tabContentManager, new dr(10, c0Var), i2, this.G, true, this.N);
            this.v = z0Var2;
            s1 s1Var = this.j;
            a1 a1Var = z0Var2.f;
            if (a1Var != null) {
                s1Var.B = a1Var;
                a1Var.q.p(s1Var.x);
            } else {
                s1Var.getClass();
            }
        }
        ArrayList arrayList = this.x;
        Activity activity2 = this.e;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList();
            this.x = arrayList2;
            arrayList2.add(new f1(AppCompatResources.a(activity2, R$drawable.ic_select_all_24dp), AppCompatResources.a(activity2, R$drawable.ic_deselect_all_24dp)));
            this.x.add(new f33(AppCompatResources.a(activity2, R$drawable.ic_close_tabs_24dp)));
            this.x.add(new h33(AppCompatResources.a(activity2, R$drawable.ic_widgets)));
            if (CachedFeatureFlags.b(ar.d.b("TabSelectionEditorV2:enable_bookmarks"), false)) {
                this.x.add(new x0(activity2, AppCompatResources.a(activity2, R$drawable.btn_star), new x0.b()));
            }
        }
        a1 a1Var2 = this.v.f;
        a1Var2.F(this.x, new z0.e(activity2, a1Var2));
        ArrayList arrayList3 = new ArrayList();
        p13 p13VarC = this.o.u().c();
        for (int i3 = 0; i3 < p13VarC.getCount(); i3++) {
            arrayList3.add(p13VarC.getTabAt(i3));
        }
        this.v.f.A(arrayList3, 0, c0Var.a());
        ac2.e(activity2, 0);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final boolean a() {
        return this.j.a();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
    public final long b() {
        return this.i.f.W0;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
    public final int c() {
        return this.i.f.P0;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final void d() {
        y13 y13Var = this.o;
        c0 c0Var = this.i;
        Activity activity = this.e;
        if (this.C) {
            return;
        }
        TraceEvent traceEventH0 = TraceEvent.H0("TabSwitcherCoordinator.initWithNative", null);
        try {
            c0Var.c(this.s.get());
            boolean zE = oj2.E(activity);
            int i = this.p;
            if (!zE) {
                if (oj2.D(activity) || bq.s.a()) {
                    this.v = new z0(this.e, this.F, this.o, this.H, null, i == 2 ? 0 : i, this.G, false, this.N);
                }
                if (oj2.D(activity)) {
                    z(activity);
                }
            }
            z0 z0Var = this.v;
            a1 a1Var = z0Var != null ? z0Var.f : null;
            org.chromium.chrome.browser.tasks.tab_management.c cVar = this.q;
            if (i == 0) {
                if (bq.s.a() && !oj2.E(activity)) {
                    this.y = new d43(y13Var, this.g);
                    m1 m1Var = new m1(activity, y13Var, a1Var);
                    this.y.l.a(m1Var);
                    cVar.b(m1Var);
                }
                if (oj2.D(activity) && !v(i)) {
                    o oVar = new o(activity, this.A, this.u);
                    this.L = oVar;
                    cVar.b(new org.chromium.chrome.browser.tasks.tab_management.b(oVar));
                }
                if (ny0.b() && this.I == null) {
                    this.f80J = new ny0();
                    org.chromium.chrome.browser.tasks.tab_management.a aVar = new org.chromium.chrome.browser.tasks.tab_management.a(Profile.d(), this.e, SharedPreferencesManager.getInstance(), this.f80J, this.t, new t43(), this.g);
                    this.I = aVar;
                    cVar.b(aVar);
                }
            }
            h hVar = this.k;
            hVar.q.f(hVar.f.g(false).f());
            wp2 wp2Var = this.t;
            s1 s1Var = this.j;
            if (a1Var != null) {
                s1Var.B = a1Var;
                a1Var.q.p(s1Var.x);
            } else {
                s1Var.getClass();
            }
            s1Var.O = wp2Var;
            if (z7.R()) {
                PriceDropNotificationManagerImpl priceDropNotificationManagerImplB = n3.B();
                if (i == 0) {
                    i iVar = new i(c0Var, s1Var, priceDropNotificationManagerImplB);
                    this.D = iVar;
                    cVar.b(iVar);
                    s1Var.D = this.D;
                }
            }
            this.C = true;
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

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final n1.a e() {
        return this.j;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
    public final void g(Runnable runnable) {
        TabListRecyclerView tabListRecyclerView = this.i.f;
        tabListRecyclerView.getClass();
        tabListRecyclerView.b1 = new o0(tabListRecyclerView, runnable);
        if (tabListRecyclerView.isAttachedToWindow() && tabListRecyclerView.isLayoutRequested()) {
            return;
        }
        o0 o0Var = tabListRecyclerView.b1;
        tabListRecyclerView.b1 = null;
        o0Var.run();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
    public final void i() {
        y13 y13Var = this.o;
        if (y13Var.t()) {
            RecyclerView.a0 a0VarF = this.i.f.F(y13Var.u().c().index());
            if (a0VarF == null) {
                return;
            }
            View view = a0VarF.e;
            view.requestFocus();
            view.sendAccessibilityEvent(8);
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final nw2<Boolean> k() {
        return this.m ? new p11(9, this) : new se1(8);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final p1 m() {
        return this.M;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
    public final Rect n(boolean z) {
        j jVar = this.n;
        c0 c0Var = this.i;
        if (jVar == null || !jVar.isVisible()) {
            if (z) {
                c0Var.m();
            }
            return c0Var.i;
        }
        c0 c0Var2 = this.n.e;
        c0Var2.m();
        Rect rect = new Rect();
        c0Var2.f.getGlobalVisibleRect(rect);
        int i = rect.left;
        int i2 = rect.top;
        Rect rect2 = c0Var2.i;
        rect2.offset(i, i2);
        c0Var.getClass();
        Rect rect3 = new Rect();
        c0Var.f.getGlobalVisibleRect(rect3);
        rect2.offset(-rect3.left, -rect3.top);
        return rect2;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.p.a
    public final void o(int i) {
        B();
        n3.X0("TabMultiSelectV2.OpenLongPressInGrid");
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        c cVar = this.K;
        if (cVar != null) {
            ((ChromeActivity) this.h).R0.remove(cVar);
        }
        this.i.onDestroy();
        int i = 0;
        while (true) {
            org.chromium.chrome.browser.tasks.tab_management.c cVar2 = this.q;
            ArrayList arrayList = cVar2.b;
            if (i >= arrayList.size()) {
                break;
            }
            ((g) arrayList.get(i)).e.c(cVar2.a);
            i++;
        }
        this.f.c();
        j jVar = this.n;
        if (jVar != null) {
            jVar.destroy();
        }
        o oVar = this.L;
        if (oVar != null) {
            oVar.a.getViewTreeObserver().removeOnGlobalLayoutListener(oVar.e);
        }
        h hVar = this.k;
        hVar.f.l(hVar.g);
        z0 z0Var = this.v;
        if (z0Var != null) {
            z0Var.a();
        }
        s1 s1Var = this.j;
        z0.d dVar = s1Var.B;
        x10 x10Var = s1Var.x;
        if (dVar != null) {
            dVar.getHandleBackPressChangedSupplier().d(x10Var);
        }
        qs1<m.b> qs1Var = s1Var.A;
        if (qs1Var != null && qs1Var.f()) {
            qs1Var.get().getHandleBackPressChangedSupplier().d(x10Var);
        }
        hy0 hy0Var = s1Var.E;
        if (hy0Var != null) {
            hy0Var.c(s1Var.y);
        }
        ll llVar = s1Var.z;
        if (llVar != null) {
            llVar.a();
        }
        y13 y13Var = s1Var.j;
        y13Var.l(s1Var.l);
        s1Var.n.s(s1Var.o);
        y13Var.u().e(s1Var.k);
        ((uj1) s1Var.t).b.c(s1Var.u);
        this.g.e(this);
        ky2 ky2Var = this.z;
        if (ky2Var != null) {
            ky2Var.c.destroy();
            y13 y13Var2 = ky2Var.a;
            p13 p13VarD = y13Var2.u().d(false);
            if (p13VarD != null) {
                p13VarD.f.c(ky2Var.b);
            }
            y13Var2.l(ky2Var.d);
        }
        ke0 ke0Var = this.E;
        if (ke0Var != null) {
            SharedPreferencesManager.getInstance().k(ke0Var);
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
    public final void p() {
        int iM37SqSAy;
        this.i.d();
        s1 s1Var = this.j;
        s1Var.getClass();
        org.chromium.base.library_loader.a aVar = org.chromium.base.library_loader.a.l;
        if (aVar.d()) {
            sk skVar = bq.a;
            N.M37SqSAy("TabGridLayoutAndroid", "soft-cleanup-delay", 3000);
        }
        int iM37SqSAy2 = 0;
        if (aVar.d()) {
            sk skVar2 = bq.a;
            iM37SqSAy = N.M37SqSAy("TabGridLayoutAndroid", "soft-cleanup-delay", 3000);
        } else {
            iM37SqSAy = 0;
        }
        Handler handler = s1Var.e;
        handler.postDelayed(s1Var.f, iM37SqSAy);
        if (aVar.d()) {
            sk skVar3 = bq.a;
            N.M37SqSAy("TabGridLayoutAndroid", "cleanup-delay", 30000);
        }
        if (aVar.d()) {
            sk skVar4 = bq.a;
            iM37SqSAy2 = N.M37SqSAy("TabGridLayoutAndroid", "cleanup-delay", 30000);
        }
        handler.postDelayed(s1Var.g, iM37SqSAy2);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
    public final int r() {
        return this.i.b();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public void setOnTabSelectingListener(n1.b bVar) {
        this.j.setOnTabSelectingListener(bVar);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
    public final boolean t() {
        boolean zW;
        df0 df0Var;
        s1 s1Var = this.j;
        zx1 zx1Var = s1Var.f;
        Handler handler = s1Var.e;
        handler.removeCallbacks(zx1Var);
        handler.removeCallbacks(s1Var.g);
        y13 y13Var = s1Var.j;
        if (y13Var.t()) {
            zW = oj2.H() ? ((o1) s1Var.h).w(y13Var.u().c(), false, s1Var.L) : false;
            s1Var.t();
        } else {
            zW = false;
        }
        c0 c0Var = this.i;
        vz2 vz2Var = c0Var.o;
        TabListRecyclerView tabListRecyclerView = c0Var.f;
        if (vz2Var != null) {
            tabListRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(vz2Var);
        }
        c0Var.e();
        ValueAnimator valueAnimator = tabListRecyclerView.Q0;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        ValueAnimator valueAnimator2 = tabListRecyclerView.R0;
        if (valueAnimator2 != null) {
            valueAnimator2.end();
        }
        if (!tabListRecyclerView.V0 && (df0Var = tabListRecyclerView.T0) != null) {
            df0Var.d(tabListRecyclerView.P0, tabListRecyclerView.U0);
            tabListRecyclerView.V0 = true;
        }
        tabListRecyclerView.a1 = tabListRecyclerView.getItemAnimator();
        tabListRecyclerView.setItemAnimator(null);
        j0 j0Var = c0Var.e;
        j0Var.getClass();
        if (oj2.H() && j0Var.h.t()) {
            int i = 0;
            while (true) {
                n0 n0Var = j0Var.f;
                if (i >= n0Var.size()) {
                    break;
                }
                if (n0Var.get(i).b.h(n0.a.a) == 0) {
                    PropertyModel propertyModel = n0Var.get(i).b;
                    PropertyModel.n nVar = u0.k;
                    propertyModel.k(nVar);
                    n0Var.get(i).b.l(nVar, false);
                }
                i++;
            }
        }
        if (k42.c() && (z7.O() || z7.N())) {
            j0Var.x = tabListRecyclerView;
            f13 f13Var = new f13(j0Var);
            j0Var.y = f13Var;
            j0Var.x.addOnScrollListener(f13Var);
        }
        return zW;
    }

    public final void u(int i) {
        d.a aVar;
        org.chromium.chrome.browser.tasks.tab_management.d dVar = this.q.a;
        LinkedHashMap linkedHashMap = dVar.d;
        if (linkedHashMap.containsKey(Integer.valueOf(i))) {
            d.a aVar2 = (d.a) linkedHashMap.get(Integer.valueOf(i));
            aVar2.b.l(f.s, dVar.b.get().booleanValue());
            aVar = aVar2;
        } else {
            LinkedHashMap linkedHashMap2 = dVar.c;
            if (linkedHashMap2.containsKey(Integer.valueOf(i))) {
                List list = (List) linkedHashMap2.get(Integer.valueOf(i));
                linkedHashMap.put(Integer.valueOf(i), (d.a) list.remove(0));
                if (list.size() == 0) {
                    linkedHashMap2.remove(Integer.valueOf(i));
                }
                d.a aVar22 = (d.a) linkedHashMap.get(Integer.valueOf(i));
                aVar22.b.l(f.s, dVar.b.get().booleanValue());
                aVar = aVar22;
            } else {
                aVar = null;
            }
        }
        if (aVar != null) {
            PropertyModel propertyModel = aVar.b;
            if (A(propertyModel)) {
                c0 c0Var = this.i;
                if (i != 3) {
                    int size = c0Var.k.size();
                    j0 j0Var = c0Var.e;
                    j0Var.getClass();
                    j0Var.f.B(size, new xa1(3, propertyModel));
                    return;
                }
                j0 j0Var2 = c0Var.e;
                int i2 = j0Var2.w.F;
                int iIndex = j0Var2.h.u().c().index();
                n0 n0Var = j0Var2.f;
                int iMin = Math.min(((n0Var.G(iIndex) / i2) + 1) * i2, n0Var.E(n0Var.size()) + 1);
                j0 j0Var3 = c0Var.e;
                j0Var3.getClass();
                j0Var3.f.B(iMin, new xa1(6, propertyModel));
            }
        }
    }

    public final boolean w(p13 p13Var, boolean z, boolean z2) {
        ArrayList arrayList;
        LinkedHashMap linkedHashMap = g72.c;
        if (p13Var != null) {
            arrayList = new ArrayList();
            for (int i = 0; i < p13Var.getCount(); i++) {
                Tab tabAt = p13Var.getTabAt(i);
                if (tabAt != null) {
                    arrayList.add(g72.a(tabAt));
                }
            }
        } else {
            arrayList = null;
        }
        return x(arrayList, z, z2);
    }

    public final boolean x(ArrayList arrayList, boolean z, boolean z2) {
        i iVar = this.D;
        if (iVar != null) {
            iVar.j = null;
            iVar.g();
        }
        c0 c0Var = this.i;
        boolean zI = c0Var.i(arrayList, z, z2);
        if (zI) {
            c0Var.g(3, 5);
            c0Var.g(6, 4);
        }
        if (arrayList != null && arrayList.size() > 0) {
            if (this.D != null && k42.a()) {
                this.D.i(1, null);
            }
            int size = arrayList.size();
            uj1 uj1Var = (uj1) this.r;
            uj1Var.getClass();
            vj1.h.getClass();
            if (!vj1.o(uj1Var.a)) {
                ArrayList arrayListA = this.q.a();
                for (int i = 0; i < arrayListA.size(); i++) {
                    if (A(((d.a) arrayListA.get(i)).b)) {
                        int i2 = ((d.a) arrayListA.get(i)).a;
                        j0 j0Var = c0Var.e;
                        if (i2 == 3) {
                            PropertyModel propertyModel = ((d.a) arrayListA.get(i)).b;
                            j0Var.getClass();
                            j0Var.f.B(size, new xa1(6, propertyModel));
                        } else if (((d.a) arrayListA.get(i)).a == 4) {
                            PropertyModel propertyModel2 = ((d.a) arrayListA.get(i)).b;
                            if (this.I.k(Profile.d())) {
                                int size2 = c0Var.k.size();
                                j0Var.getClass();
                                j0Var.f.B(size2, new xa1(6, propertyModel2));
                                org.chromium.chrome.browser.tasks.tab_management.a aVar = this.I;
                                if (aVar.k.d(0, "Chrome.IncognitoReauth.PromoShowCount") > aVar.g) {
                                    aVar.i();
                                } else {
                                    SharedPreferencesManager sharedPreferencesManager = aVar.k;
                                    sharedPreferencesManager.m(sharedPreferencesManager.d(0, "Chrome.IncognitoReauth.PromoShowCount") + 1, "Chrome.IncognitoReauth.PromoShowCount");
                                }
                            }
                        } else {
                            PropertyModel propertyModel3 = ((d.a) arrayListA.get(i)).b;
                            j0Var.getClass();
                            j0Var.f.B(size, new xa1(3, propertyModel3));
                        }
                        size++;
                    }
                }
                arrayListA.size();
            }
        }
        return zI;
    }

    public final void y() {
        ArrayList arrayListA = this.q.a();
        for (int i = 0; i < arrayListA.size(); i++) {
            if (A(((d.a) arrayListA.get(i)).b) && ((d.a) arrayListA.get(i)).a != 3) {
                int i2 = ((d.a) arrayListA.get(i)).a;
                c0 c0Var = this.i;
                if (i2 == 4) {
                    PropertyModel propertyModel = ((d.a) arrayListA.get(i)).b;
                    int size = c0Var.k.size();
                    j0 j0Var = c0Var.e;
                    j0Var.getClass();
                    j0Var.f.B(size, new xa1(6, propertyModel));
                } else {
                    PropertyModel propertyModel2 = ((d.a) arrayListA.get(i)).b;
                    int size2 = c0Var.k.size();
                    j0 j0Var2 = c0Var.e;
                    j0Var2.getClass();
                    j0Var2.f.B(size2, new xa1(3, propertyModel2));
                }
            }
        }
        arrayListA.size();
    }

    public final void z(Activity activity) {
        TraceEvent traceEventH0 = TraceEvent.H0("TabSwitcherCoordintor.setUpTabGroupManualSelectionMode", null);
        try {
            String string = activity.getString(R$string.tab_selection_editor_group);
            int i = R$plurals.accessibility_tab_selection_editor_group_button;
            a1 a1Var = this.v.f;
            this.w = new e(string, i, new w0(a1Var, 1), new z0.e(this.e, a1Var));
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

    /* JADX INFO: compiled from: TabSwitcherCoordinator.java */
    public class b implements n1.d {
        public b() {
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
        public final void e() {
            o1 o1Var = o1.this;
            if (o1Var.o.t()) {
                RecyclerView.a0 a0VarF = o1Var.i.f.F(o1Var.o.u().c().index());
                if (a0VarF == null) {
                    return;
                }
                View view = a0VarF.e;
                view.requestFocus();
                view.sendAccessibilityEvent(8);
            }
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
        public final void d() {
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
        public final void k() {
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
        public final void s() {
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final n1.c s() {
        return this;
    }
}
