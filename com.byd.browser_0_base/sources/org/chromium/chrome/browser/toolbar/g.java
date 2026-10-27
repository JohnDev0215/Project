package org.chromium.chrome.browser.toolbar;

import J.N;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.C0188h0;
import defpackage.ad3;
import defpackage.af1;
import defpackage.ai;
import defpackage.aq1;
import defpackage.bn0;
import defpackage.bq;
import defpackage.bz0;
import defpackage.c01;
import defpackage.c33;
import defpackage.cc3;
import defpackage.cf1;
import defpackage.cp;
import defpackage.cs2;
import defpackage.d92;
import defpackage.dr;
import defpackage.dt2;
import defpackage.dz2;
import defpackage.e63;
import defpackage.e7;
import defpackage.e91;
import defpackage.ec3;
import defpackage.ef2;
import defpackage.ez2;
import defpackage.f2;
import defpackage.fc3;
import defpackage.fj;
import defpackage.fm3;
import defpackage.fs;
import defpackage.g3;
import defpackage.gc2;
import defpackage.gc3;
import defpackage.gx;
import defpackage.gz2;
import defpackage.h61;
import defpackage.h7;
import defpackage.hc3;
import defpackage.hd2;
import defpackage.hj3;
import defpackage.hs;
import defpackage.i31;
import defpackage.i7;
import defpackage.i83;
import defpackage.ib3;
import defpackage.ic3;
import defpackage.is;
import defpackage.it3;
import defpackage.j03;
import defpackage.j7;
import defpackage.jb1;
import defpackage.jc3;
import defpackage.jg2;
import defpackage.ji;
import defpackage.jj;
import defpackage.jj2;
import defpackage.jk1;
import defpackage.k2;
import defpackage.k42;
import defpackage.kc3;
import defpackage.ke0;
import defpackage.km1;
import defpackage.l23;
import defpackage.l61;
import defpackage.lc3;
import defpackage.lj;
import defpackage.ll;
import defpackage.lr2;
import defpackage.ls;
import defpackage.m62;
import defpackage.m7;
import defpackage.mc3;
import defpackage.n3;
import defpackage.n62;
import defpackage.nc3;
import defpackage.nv0;
import defpackage.nw2;
import defpackage.o7;
import defpackage.oc3;
import defpackage.oj2;
import defpackage.on0;
import defpackage.p11;
import defpackage.pc3;
import defpackage.pd;
import defpackage.pk3;
import defpackage.ps;
import defpackage.py2;
import defpackage.q2;
import defpackage.qc3;
import defpackage.qh1;
import defpackage.qn3;
import defpackage.qo;
import defpackage.qp;
import defpackage.qs1;
import defpackage.qv0;
import defpackage.rb3;
import defpackage.rc3;
import defpackage.rq0;
import defpackage.rs1;
import defpackage.s20;
import defpackage.sc3;
import defpackage.se1;
import defpackage.sk;
import defpackage.sn1;
import defpackage.so3;
import defpackage.sr;
import defpackage.sr1;
import defpackage.sw0;
import defpackage.tc3;
import defpackage.tq;
import defpackage.tr;
import defpackage.u7;
import defpackage.uc3;
import defpackage.v42;
import defpackage.v91;
import defpackage.vb;
import defpackage.vc3;
import defpackage.vj;
import defpackage.vj3;
import defpackage.vt;
import defpackage.w91;
import defpackage.wp2;
import defpackage.ww;
import defpackage.wz;
import defpackage.x10;
import defpackage.x6;
import defpackage.xd3;
import defpackage.xl2;
import defpackage.xm0;
import defpackage.y13;
import defpackage.ya1;
import defpackage.yp;
import defpackage.z2;
import defpackage.z6;
import defpackage.z7;
import defpackage.zb;
import defpackage.zb2;
import defpackage.zp1;
import defpackage.zq2;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.chrome.browser.toolbar.top.CustomTabSwitcherModeTopToolbar;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.toolbar.top.ToolbarLayout;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;
import org.chromium.chrome.browser.toolbar.top.i;
import org.chromium.chrome.browser.toolbar.top.m;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements vj3, ThemeColorProvider.a, ThemeColorProvider.b, C0188h0.b, l23.a, vb {
    public zp1<BookmarkModel> A;
    public int A0;
    public final jb1 B;
    public final nw2<Boolean> B0;
    public TemplateUrlService C;
    public nw2<Boolean> C0;
    public oc3 D;
    public final ef2 D0;
    public v91 E;
    public lr2 E0;
    public xm0 F;
    public jc3 F0;
    public h61 G;
    public kc3 G0;
    public final sc3 H;
    public final qs1<Boolean> H0;
    public final vc3 I;
    public final oj2 I0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f83J;
    public final aq1<Boolean> J0;
    public final boolean K;
    public j03 K0;
    public final boolean L;
    public final aq1<Boolean> L0;
    public l61 M;
    public boolean M0;
    public final lc3 N;
    public qs1<l61> O;
    public ll P;
    public final k2 Q;
    public final Callback<Boolean> R;
    public final Handler S;
    public final AppCompatActivity T;
    public final WindowAndroid U;
    public final e7 V;
    public final CompositorViewHolder W;
    public final jj X;
    public final rq0 Y;
    public final w91 Z;
    public pc3 a0;
    public final e91 b0;
    public final ad3 c0;
    public af1 d0;
    public final bz0 e;
    public af1 e0;
    public final org.chromium.chrome.browser.toolbar.a f;
    public ic3 f0;
    public final xd3 g;
    public final dt2 g0;
    public final nw2<EphemeralTabCoordinator> h;
    public final q2 h0;
    public u7 i;
    public final ji i0;
    public final SettableThemeColorProvider j;
    public final nw2<Boolean> j0;
    public final m k;
    public final TabContentManager k0;
    public final ToolbarControlContainer l;
    public final gz2 l0;
    public final tc3 m;
    public final l23 m0;
    public final uc3 n;
    public final wp2 n0;
    public final aq1<Boolean> o;
    public final qs1<c33> o0;
    public final aq1<Boolean> p;
    public nv0 p0;
    public final aq1<Boolean> q;
    public ib3 q0;
    public final zp1<Boolean> r;
    public final vj r0;
    public final a s;
    public int s0;
    public aq1<ai> t;
    public int t0;
    public y13 u;
    public boolean u0;
    public final rc3 v;
    public boolean v0;
    public zp1<y13> w;
    public d92 w0;
    public qc3 x;
    public d92 x0;
    public final z2 y;
    public boolean y0;
    public final LocationBarModel z;
    public int z0;

    /* JADX INFO: compiled from: ToolbarManager.java */
    public static class a extends aq1<Integer> implements Callback<Integer> {
        public lj h;

        public a(int i) {
        }

        @Override // org.chromium.base.Callback
        public final void onResult(Integer num) {
            r(num);
        }
    }

    /* JADX INFO: compiled from: ToolbarManager.java */
    public static class b implements Callback<Boolean> {
        public final l23 e;
        public l23.b f;

        public b(l23 l23Var) {
            this.e = l23Var;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(Boolean bool) {
            boolean zBooleanValue = bool.booleanValue();
            l23 l23Var = this.e;
            if (zBooleanValue) {
                l23.b bVar = this.f;
                this.f = l23Var.d(1);
                if (bVar != null) {
                    l23Var.f(bVar);
                    return;
                }
                return;
            }
            l23.b bVar2 = this.f;
            if (bVar2 != null) {
                l23Var.f(bVar2);
                this.f = null;
            }
        }
    }

    /* JADX INFO: compiled from: ToolbarManager.java */
    public abstract class c implements km1 {
        public h b;

        public c() {
        }

        @Override // defpackage.km1
        public final boolean a() {
            g gVar = g.this;
            return gVar.j() != null && gVar.j().u.getHeight() > 0;
        }

        @Override // defpackage.km1
        public final void b(float f) {
            h hVarJ = g.this.j();
            if (hVarJ != null) {
                hVarJ.u.setUrlFocusChangeAnimationPercent(f);
            }
        }

        @Override // defpackage.km1
        public void c(x10 x10Var) {
            h hVarJ = g.this.j();
            h hVar = this.b;
            if (hVar != null) {
                hVar.setSearchBoxScrollListener(null);
            }
            this.b = hVarJ;
            if (hVarJ == null || !l()) {
                return;
            }
            this.b.setSearchBoxScrollListener(new c01(26, x10Var));
        }

        @Override // defpackage.km1
        public final void d(Rect rect, Point point) {
            g gVar = g.this;
            if (gVar.j() != null) {
                h hVarJ = gVar.j();
                NewTabPageLayout newTabPageLayout = hVarJ.u;
                View view = hVarJ.getView();
                int x = (int) newTabPageLayout.getSearchBoxView().getX();
                int y = (int) newTabPageLayout.getSearchBoxView().getY();
                rect.set(x, y, newTabPageLayout.getSearchBoxView().getWidth() + x, newTabPageLayout.getSearchBoxView().getHeight() + y);
                point.set(0, 0);
                if (newTabPageLayout.d()) {
                    point.y = Integer.MIN_VALUE;
                } else {
                    View searchBoxView = newTabPageLayout.getSearchBoxView();
                    while (true) {
                        searchBoxView = (View) searchBoxView.getParent();
                        if (searchBoxView == null) {
                            point.y = Integer.MIN_VALUE;
                            break;
                        }
                        point.offset(-searchBoxView.getScrollX(), -searchBoxView.getScrollY());
                        if (searchBoxView == view) {
                            break;
                        } else {
                            point.offset((int) searchBoxView.getX(), (int) searchBoxView.getY());
                        }
                    }
                }
                rect.offset(point.x, point.y);
                if (point.y != Integer.MIN_VALUE) {
                    rect.inset(0, newTabPageLayout.B);
                }
            }
        }

        @Override // defpackage.km1
        public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return this.b.getView().dispatchTouchEvent(motionEvent);
        }

        @Override // defpackage.km1
        public final boolean e() {
            h hVar = this.b;
            return (hVar == null || !hVar.u() || j()) ? false : true;
        }

        @Override // defpackage.km1
        public final void f(float f) {
            g gVar = g.this;
            if (gVar.j() != null) {
                gVar.j().u.setSearchProviderLogoAlpha(f);
            }
        }

        @Override // defpackage.km1
        public final boolean g() {
            return this.b != null;
        }

        @Override // defpackage.km1
        public final void h(ToolbarPhone.e eVar) {
            g gVar = g.this;
            if (gVar.j() != null) {
                gVar.j().u.setSearchBoxBackground(eVar);
            }
        }

        @Override // defpackage.km1
        public final boolean i() {
            return g.this.j() != null;
        }

        @Override // defpackage.km1
        public final boolean j() {
            h hVarJ;
            l61 l61Var;
            g gVar = g.this;
            if (gVar.L && (l61Var = gVar.M) != null && l61Var.d() == 16) {
                return false;
            }
            return ((gVar.f83J == 1 && zb2.d(gVar.T)) || (hVarJ = gVar.j()) == null || !hVarJ.u()) ? false : true;
        }

        @Override // defpackage.km1
        public final void k(float f) {
            g gVar = g.this;
            if (gVar.j() != null) {
                gVar.j().u.setSearchBoxAlpha(f);
            }
        }

        public abstract boolean l();
    }

    /* JADX WARN: Type inference failed for: r1v36, types: [ic3] */
    public g(AppCompatActivity appCompatActivity, fj fjVar, rq0 rq0Var, ToolbarControlContainer toolbarControlContainer, CompositorViewHolder compositorViewHolder, gc2 gc2Var, xd3 xd3Var, l23 l23Var, zp1 zp1Var, sw0 sw0Var, List list, z2 z2Var, ef2 ef2Var, rb3 rb3Var, xm0 xm0Var, zp1 zp1Var2, zp1 zp1Var3, e63 e63Var, qs1 qs1Var, rs1 rs1Var, boolean z, zp1 zp1Var4, qs1 qs1Var2, aq1 aq1Var, rs1 rs1Var2, g3 g3Var, nw2 nw2Var, zp1 zp1Var5, dt2 dt2Var, e7 e7Var, q2 q2Var, nw2 nw2Var2, org.chromium.components.browser_ui.bottomsheet.f fVar, nw2 nw2Var3, TabContentManager tabContentManager, gz2 gz2Var, wp2 wp2Var, i31 i31Var, aq1 aq1Var2, qs1 qs1Var3, f2 f2Var, nw2 nw2Var4, boolean z2, zb zbVar) {
        LocationBarModel locationBarModel;
        ToolbarLayout toolbarLayout;
        dt2 dt2Var2;
        bz0 bz0Var;
        aq1<Boolean> aq1Var3 = new aq1<>();
        this.o = aq1Var3;
        aq1<Boolean> aq1Var4 = new aq1<>();
        this.p = aq1Var4;
        this.q = new aq1<>();
        a aVar = new a(0);
        this.s = aVar;
        this.t = new aq1<>();
        this.f83J = 0;
        this.P = new ll();
        Handler handler = new Handler();
        this.S = handler;
        this.s0 = -1;
        this.t0 = -1;
        this.y0 = true;
        this.J0 = new aq1<>();
        this.L0 = new aq1<>();
        TraceEvent.q("ToolbarManager.ToolbarManager", null);
        this.T = appCompatActivity;
        this.U = g3Var;
        this.W = compositorViewHolder;
        this.X = fjVar;
        this.Y = rq0Var;
        qn3 qn3Var = new qn3(appCompatActivity.B1(), toolbarControlContainer, appCompatActivity.findViewById(R$id.action_bar_black_background));
        this.B0 = e63Var;
        this.D0 = ef2Var;
        this.w = zp1Var4;
        this.r = aq1Var;
        this.H0 = rs1Var2;
        this.V = e7Var;
        this.g0 = dt2Var;
        this.R = gc2Var;
        this.h0 = q2Var;
        this.i0 = fVar;
        this.j0 = nw2Var3;
        this.k0 = tabContentManager;
        this.l0 = gz2Var;
        this.m0 = l23Var;
        this.n0 = wp2Var;
        this.o0 = qs1Var3;
        this.h = nw2Var4;
        ToolbarLayout toolbarLayout2 = (ToolbarLayout) appCompatActivity.findViewById(R$id.toolbar);
        LocationBarModel locationBarModel2 = new LocationBarModel(appCompatActivity, toolbarLayout2 instanceof ToolbarPhone ? new d(this) : toolbarLayout2 instanceof ToolbarTablet ? new e(this) : km1.a, new qh1(13), new i83(18), new f(), jg2.a());
        this.z = locationBarModel2;
        this.l = toolbarControlContainer;
        this.A = zp1Var3;
        jb1 jb1Var = new jb1(12, this);
        this.B = jb1Var;
        zp1Var3.p(jb1Var);
        this.O = qs1Var;
        qs1Var.h(this.P.b(new dr(14, this)));
        pc3 pc3Var = new pc3(this);
        this.a0 = pc3Var;
        appCompatActivity.registerComponentCallbacks(pc3Var);
        bz0 bz0Var2 = new bz0();
        this.e = bz0Var2;
        org.chromium.chrome.browser.toolbar.a aVar2 = new org.chromium.chrome.browser.toolbar.a();
        this.f = aVar2;
        this.g = xd3Var;
        xd3Var.h.a(this);
        u7 u7Var = new u7(appCompatActivity);
        this.i = u7Var;
        u7Var.i.a(this);
        SettableThemeColorProvider settableThemeColorProvider = new SettableThemeColorProvider(appCompatActivity);
        this.j = settableThemeColorProvider;
        this.y = z2Var;
        boolean zD = zb2.d(appCompatActivity);
        this.K = zD;
        boolean zE = zb2.e(appCompatActivity);
        this.L = zE;
        ad3 ad3Var = new ad3(new hs(11, locationBarModel2), new is(this, 1), new p11(11, zp1Var2), this.t, new se1(10), new hc3(this, 1), z2Var);
        this.c0 = ad3Var;
        if (zbVar != null && zb.b()) {
            zbVar.a(this, 14);
        }
        vj vjVar = fjVar.f;
        this.r0 = vjVar;
        ThemeColorProvider themeColorProvider = DeviceFormFactor.a(appCompatActivity) ? this.i : xd3Var;
        u7 u7Var2 = this.i;
        Objects.requireNonNull(compositorViewHolder);
        ww wwVar = new ww(compositorViewHolder, 1);
        boolean z3 = toolbarLayout2 instanceof CustomTabToolbar;
        ThemeColorProvider themeColorProvider2 = z3 ? settableThemeColorProvider : themeColorProvider;
        zq2 zq2Var = new zq2(5);
        wz wzVar = new wz(22);
        af1 af1Var = new af1(rs1Var, vjVar, g3Var, new ke0(24, this), wwVar, z, nw2Var, themeColorProvider2, zq2Var, wzVar, R$id.menu_button_wrapper);
        this.d0 = af1Var;
        if (z) {
            cf1 cf1Var = af1Var.c;
            this.w0 = cf1Var != null ? new d92(14, cf1Var) : null;
        }
        af1 af1Var2 = new af1(rs1Var, vjVar, g3Var, new gx(23, this), wwVar, z, nw2Var, u7Var2, zq2Var, wzVar, 0);
        this.e0 = af1Var2;
        if (zD && z) {
            cf1 cf1Var2 = af1Var2.c;
            this.x0 = cf1Var2 != null ? new d92(14, cf1Var2) : null;
        }
        boolean z4 = oj2.z(appCompatActivity);
        boolean zH = oj2.H();
        boolean zC = oj2.C(appCompatActivity);
        ll.b bVarB = this.P.b(new n62(this, 17, nw2Var2));
        org.chromium.chrome.browser.compositor.a invalidator = compositorViewHolder.getInvalidator();
        ViewStub viewStub = (ViewStub) appCompatActivity.findViewById(R$id.tab_switcher_toolbar_stub);
        pk3 pk3Var = new pk3(appCompatActivity, handler);
        u7 u7Var3 = this.i;
        af1 af1Var3 = this.d0;
        af1 af1Var4 = this.e0;
        cf1 cf1Var3 = af1Var3.c;
        aq1<z6> aq1Var5 = cf1Var3 == null ? null : cf1Var3.c;
        zp1<y13> zp1Var6 = this.w;
        on0 on0Var = new on0(21, invalidator);
        int i = 2;
        jj2 jj2Var = new jj2(this, i, sw0Var);
        fs fsVar = new fs(compositorViewHolder, i);
        bn0 bn0Var = new bn0(2);
        pd pdVar = new pd(18);
        PartnerBrowserCustomizations partnerBrowserCustomizationsB = PartnerBrowserCustomizations.b();
        Objects.requireNonNull(partnerBrowserCustomizationsB);
        m mVar = new m(toolbarControlContainer, viewStub, toolbarLayout2, locationBarModel2, ad3Var, pk3Var, list, themeColorProvider, u7Var3, af1Var3, af1Var4, aq1Var5, zp1Var6, aq1Var3, sw0Var, on0Var, jj2Var, fsVar, bn0Var, z4, zH, zD, zC, pdVar, new s20(6, partnerBrowserCustomizationsB), new qh1(15), z2, bVarB, zE, aVar, compositorViewHolder.getInMotionSupplier(), vjVar, !zb2.j(appCompatActivity));
        this.f0 = new qv0.a() { // from class: ic3
            @Override // qv0.a
            public final void a() {
                g gVar = this.a;
                gVar.getClass();
                gVar.o.r(Boolean.valueOf(qv0.e()));
                gVar.q.r(Boolean.valueOf(zb2.k(gVar.T)));
                gVar.p.r(Boolean.valueOf(rv0.b()));
            }
        };
        qv0.d().b.a(this.f0);
        a();
        HomeButton homeButton = toolbarLayout2.getHomeButton();
        if (homeButton != null) {
            qv0 qv0VarD = qv0.d();
            Objects.requireNonNull(qv0VarD);
            on0 on0Var2 = new on0(22, qv0VarD);
            dr drVar = new dr(13, homeButton);
            aq1Var3.p(drVar);
            aq1Var4.p(drVar);
            homeButton.r = on0Var2;
            homeButton.s = aq1Var4;
            homeButton.h();
        }
        this.k = mVar;
        k2 k2Var = new k2(appCompatActivity, qn3Var, rb3Var);
        this.Q = k2Var;
        k2Var.c = mVar.g();
        l23Var.b(this);
        if (z3) {
            toolbarLayout = toolbarLayout2;
            CustomTabToolbar customTabToolbar = (CustomTabToolbar) toolbarLayout;
            locationBarModel = locationBarModel2;
            customTabToolbar.H = locationBarModel;
            CustomTabToolbar.b bVar = customTabToolbar.G;
            bVar.f = locationBarModel;
            bVar.g = nw2Var4;
            locationBarModel.l(bVar);
            bVar.h = zp1Var5;
            bVar.i = new org.chromium.chrome.browser.omnibox.h((UrlBar) bVar.j, null, rb3Var, new ps(4), bVar, new sn1(), locationBarModel.n, new fm3(3));
            bVar.A();
            bVar.C();
            bVar.B();
            bVar.F();
            customTabToolbar.I = vjVar;
            this.E = bVar;
        } else {
            locationBarModel = locationBarModel2;
            toolbarLayout = toolbarLayout2;
            gc3 gc3Var = new gc3(nw2Var2);
            Objects.requireNonNull(aq1Var2);
            tq tqVar = new tq(zp1Var5, new p11(10, aq1Var2), nw2Var4);
            View viewFindViewById = appCompatActivity.findViewById(R$id.location_bar);
            v42.g();
            int i2 = 17;
            int i3 = 6;
            org.chromium.chrome.browser.omnibox.c cVar = new org.chromium.chrome.browser.omnibox.c(viewFindViewById, toolbarLayout, zp1Var2, locationBarModel, rb3Var, new it3(appCompatActivity.getWindow()), g3Var, z2Var, zp1Var5, zp1Var, bz0Var2, q2Var, gc3Var, new n3(), jg2.a(), new gx(24, tqVar), new ps(i2), new pd(i2), new qh1(14), new zq2(i3), new gc3(this), new s20(5, this), i31Var, aq1Var2, f2Var, vjVar, new m62(i3), zbVar, toolbarLayout);
            toolbarLayout.setLocationBarCoordinator(cVar);
            toolbarLayout.setBrowserControlsVisibilityDelegate(vjVar);
            this.E = cVar;
        }
        w91 w91Var = new w91(ef2Var, new b(l23Var), appCompatActivity, locationBarModel, new hc3(this, 0), compositorViewHolder);
        this.Z = w91Var;
        sr1 sr1VarI = this.E.i();
        if (sr1VarI != null) {
            sr1VarI.C(this);
            dt2Var2 = dt2Var;
            sr1VarI.C(dt2Var2);
            sr1VarI.C(w91Var);
        } else {
            dt2Var2 = dt2Var;
        }
        this.b0 = new e91(z2Var, mVar.i(), zD);
        toolbarLayout.g.a(dt2Var2);
        i iVar = mVar.l;
        iVar.e = dt2Var2;
        iVar.a();
        this.x = new qc3(this, z2Var);
        this.v = new rc3(this);
        this.H = new sc3(this);
        tc3 tc3Var = new tc3(this);
        this.m = tc3Var;
        fjVar.o(tc3Var);
        uc3 uc3Var = new uc3(this);
        this.n = uc3Var;
        rq0Var.h(uc3Var);
        vc3 vc3Var = new vc3(this);
        this.I = vc3Var;
        this.N = new lc3(this);
        this.I0 = new oj2(this);
        mVar.q(aVar2);
        org.chromium.chrome.browser.toolbar.top.h hVar = mVar.b;
        if (hVar != null) {
            bz0Var = bz0Var2;
            hVar.g = bz0Var;
            CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = hVar.j;
            if (customTabSwitcherModeTopToolbar != null) {
                customTabSwitcherModeTopToolbar.setIncognitoStateProvider(bz0Var);
            }
        } else {
            bz0Var = bz0Var2;
            org.chromium.chrome.browser.toolbar.top.d dVar = mVar.c;
            if (dVar != null) {
                dVar.a.e.p(cs2.a, bz0Var);
            }
        }
        iVar.g = bz0Var;
        iVar.a();
        qo.f().a(this);
        if (locationBarModel.p != zD) {
            locationBarModel.p = zD;
            locationBarModel.w();
        }
        this.F = xm0Var;
        xm0Var.f.a(vc3Var);
        qs1Var2.h(this.P.b(new on0(20, this)));
        zp1Var2.p(new mc3(this, zp1Var2));
        TraceEvent.C("ToolbarManager.ToolbarManager");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void C() {
        /*
            r8 = this;
            zp1<org.chromium.chrome.browser.bookmarks.BookmarkModel> r0 = r8.A
            if (r0 != 0) goto L5
            return
        L5:
            org.chromium.chrome.browser.toolbar.LocationBarModel r0 = r8.z
            org.chromium.chrome.browser.tab.Tab r0 = r0.d()
            zp1<org.chromium.chrome.browser.bookmarks.BookmarkModel> r1 = r8.A
            java.lang.Object r1 = r1.get()
            org.chromium.chrome.browser.bookmarks.BookmarkModel r1 = (org.chromium.chrome.browser.bookmarks.BookmarkModel) r1
            if (r1 != 0) goto L21
            boolean r2 = org.chromium.chrome.browser.profiles.ProfileManager.b
            if (r2 == 0) goto L21
            org.chromium.chrome.browser.profiles.Profile r1 = org.chromium.chrome.browser.profiles.Profile.d()
            org.chromium.chrome.browser.bookmarks.BookmarkModel r1 = org.chromium.chrome.browser.bookmarks.BookmarkModel.v(r1)
        L21:
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L3f
            if (r1 == 0) goto L3f
            java.lang.Object r4 = org.chromium.base.ThreadUtils.a
            long r4 = r1.a
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L32
            goto L3a
        L32:
            org.chromium.components.bookmarks.BookmarkId r4 = r1.p(r0)
            if (r4 == 0) goto L3a
            r4 = r3
            goto L3b
        L3a:
            r4 = r2
        L3b:
            if (r4 == 0) goto L3f
            r4 = r3
            goto L40
        L3f:
            r4 = r2
        L40:
            if (r0 == 0) goto L4a
            if (r1 == 0) goto L4a
            boolean r0 = r1.q()
            if (r0 == 0) goto L4b
        L4a:
            r2 = r3
        L4b:
            org.chromium.chrome.browser.toolbar.top.m r8 = r8.k
            org.chromium.chrome.browser.toolbar.top.ToolbarLayout r8 = r8.a
            r8.E(r4, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.g.C():void");
    }

    public final void D() {
        m7 m7Var;
        ya1 ya1Var;
        View childAt;
        LocationBarModel locationBarModel = this.z;
        Tab tabD = locationBarModel.d();
        if (tabD == null) {
            Log.e("ToolbarManager", "currentTAb is null");
            return;
        }
        boolean zZ1 = hd2.z1(tabD);
        m mVar = this.k;
        mVar.s();
        mVar.a.D(tabD.g());
        s();
        mVar.a.H(tabD.i());
        Tab tabD2 = locationBarModel.d();
        boolean z = !zZ1 && ((tabD2 != null && tabD2.k()) || !this.v0);
        mVar.d(z);
        cf1 cf1Var = this.d0.c;
        if (cf1Var != null && (m7Var = cf1Var.a) != null && cf1Var.d != null) {
            ((o7) m7Var).k(z);
            h7 h7Var = cf1Var.d;
            int i = R$id.icon_row_menu_id;
            x6 x6Var = ((i7) h7Var).e;
            if (x6Var != null && x6Var.l != null && (ya1Var = x6Var.t) != null && x6Var.j != null && x6Var.k != null) {
                int size = ya1Var.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        i2 = -1;
                        break;
                    } else if (x6Var.t.get(i2).b.h(j7.a) == i) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i2 != -1) {
                    int firstVisiblePosition = x6Var.k.getFirstVisiblePosition();
                    int lastVisiblePosition = x6Var.k.getLastVisiblePosition();
                    if (i2 >= firstVisiblePosition && i2 <= lastVisiblePosition && (childAt = x6Var.k.getChildAt(i2 - firstVisiblePosition)) != null) {
                        x6Var.l.getView(i2, childAt, x6Var.k);
                    }
                }
            }
        }
        C();
        MenuButton menuButton = mVar.e.e;
        if (menuButton != null) {
            menuButton.setVisibility(0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void E(int r9, boolean r10) {
        /*
            r8 = this;
            boolean r0 = r8.L
            r1 = 16
            org.chromium.chrome.browser.toolbar.top.m r2 = r8.k
            r3 = 2
            r4 = 0
            r5 = 1
            if (r0 == 0) goto L31
            if (r9 == r3) goto L28
            if (r9 != r1) goto L26
            v91 r0 = r8.E
            sr1 r0 = r0.i()
            if (r0 != 0) goto L19
            r0 = r4
            goto L23
        L19:
            v91 r0 = r8.E
            sr1 r0 = r0.i()
            boolean r0 = r0.O()
        L23:
            if (r0 != 0) goto L26
            goto L28
        L26:
            r0 = r4
            goto L29
        L28:
            r0 = r5
        L29:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)
            r7 = 0
            r2.t(r7, r0, r6)
        L31:
            if (r9 == r3) goto L35
            if (r9 != r1) goto L69
        L35:
            if (r9 != r3) goto L39
            r0 = r5
            goto L3a
        L39:
            r0 = r4
        L3a:
            if (r9 != r1) goto L3e
            r1 = r5
            goto L3f
        L3e:
            r1 = r4
        L3f:
            org.chromium.chrome.browser.toolbar.LocationBarModel r3 = r8.z
            r3.q = r0
            r3.r = r1
            r3.y()
            r3.z()
            r3.w()
            r3.x()
            r2.r(r5, r10, r4)
            r8.D()
            boolean r10 = r3.p
            if (r10 == 0) goto L69
            v91 r8 = r8.E
            org.chromium.chrome.browser.omnibox.c r8 = (org.chromium.chrome.browser.omnibox.c) r8
            boolean r10 = r8.r
            if (r10 != 0) goto L64
            goto L69
        L64:
            org.chromium.chrome.browser.omnibox.suggestions.c r8 = r8.i
            r8.a()
        L69:
            if (r9 != r5) goto L6c
            r4 = r5
        L6c:
            org.chromium.chrome.browser.toolbar.top.ToolbarLayout r8 = r2.a
            r8.setContentAttached(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.g.E(int, boolean):void");
    }

    public final void H() {
        if (this.M0) {
            return;
        }
        LocationBarModel locationBarModel = this.z;
        locationBarModel.x();
        locationBarModel.z();
        D();
    }

    @Override // org.chromium.chrome.browser.theme.ThemeColorProvider.a
    public final void d(int i, boolean z) {
        if (this.y0) {
            if (this.z0 != i) {
                this.z0 = i;
                LocationBarModel locationBarModel = this.z;
                locationBarModel.l = i;
                locationBarModel.A();
                locationBarModel.w();
                this.k.a.s(z);
                this.j.a(i, z);
            }
        }
    }

    @Override // org.chromium.chrome.browser.theme.ThemeColorProvider.b
    public final void e(int i, ColorStateList colorStateList) {
        C();
        if (this.y0) {
            this.j.b(i, colorStateList);
        }
    }

    public final void f() {
        y13 y13Var;
        h hVarJ = j();
        if (hVarJ != null) {
            sr1 sr1VarI = this.E.i();
            hVarJ.y = sr1VarI;
            if (sr1VarI != null) {
                hVarJ.u.setUrlFocusChangeAnimationPercent(sr1VarI.O() ? 1.0f : 0.0f);
                hVarJ.t.getClass();
            }
            sr1 sr1Var = hVarJ.y;
            if (sr1Var != null) {
                hVarJ.z = sr1Var.d();
            }
            so3 so3Var = hVarJ.z;
            if (so3Var != null) {
                so3Var.f.a(hVarJ);
                hVarJ.u.l();
            }
            Iterator<f.a> it = this.z.u.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((f.a) aVar.next()).j();
                }
            }
            if (!k42.c() || !z7.O() || (y13Var = this.u) == null || y13Var.r()) {
                return;
            }
            TabModel tabModelZ = this.u.z();
            for (int i = 0; i < tabModelZ.getCount(); i++) {
                xl2.o(new ec3(this, 1), tabModelZ.getTabAt(i));
            }
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.L0;
    }

    public final ImageButton h() {
        return this.d0.e.getImageButton();
    }

    public final h j() {
        LocationBarModel locationBarModel = this.z;
        if (!locationBarModel.q()) {
            return null;
        }
        jk1 jk1VarU = locationBarModel.d().u();
        if (jk1VarU instanceof h) {
            return (h) jk1VarU;
        }
        return null;
    }

    @Override // defpackage.vj3
    public final void k(boolean z) {
        l61 l61Var;
        m mVar = this.k;
        mVar.o(z);
        if (this.L && (l61Var = this.M) != null && l61Var.q(16)) {
            mVar.t(null, !z, 16);
        }
        xm0 xm0Var = this.F;
        if (xm0Var != null && z) {
            xm0Var.a(true);
        }
        vj vjVar = this.r0;
        if (vjVar == null) {
            return;
        }
        if (z) {
            this.s0 = vjVar.w(this.s0);
        } else {
            vjVar.u(this.s0);
        }
        this.R.onResult(Boolean.valueOf(z));
    }

    public final void l(h61 h61Var, sr srVar, sr srVar2, vt vtVar, tr trVar, vt vtVar2, ls lsVar) {
        TraceEvent.q("ToolbarManager.initializeWithNative", null);
        this.u = this.w.get();
        this.C0 = lsVar;
        m mVar = this.k;
        Objects.requireNonNull(h61Var);
        mVar.n(new d92(12, h61Var), srVar, srVar2, vtVar, trVar, vtVar2, this.V, h61Var, this.y, this.X, this.g);
        nc3 nc3Var = new nc3(this);
        m mVar2 = this.k;
        mVar2.addOnAttachStateChangeListener(nc3Var);
        LocationBarModel locationBarModel = this.z;
        locationBarModel.getClass();
        sk skVar = bq.a;
        locationBarModel.j = N.M09VlOh_("AndroidScrollOptimizations");
        locationBarModel.y = N.M09VlOh_("OmniboxUpdatedConnectionSecurityIndicators");
        locationBarModel.h = Profile.d();
        locationBarModel.t = N.M8_Iwqb0(locationBarModel);
        if (locationBarModel.j) {
            locationBarModel.i = new LruCache<>(10);
            locationBarModel.g = new qp(locationBarModel.f());
            long j = locationBarModel.t;
            locationBarModel.w = j == 0 ? "" : N.MvJvjGzq(j, locationBarModel);
            long j2 = locationBarModel.t;
            locationBarModel.x = j2 != 0 ? N.Ml$ZWVQn(j2, locationBarModel) : "";
        }
        this.G = h61Var;
        h61Var.y.b.a(this.I0);
        if (this.w0 != null) {
            hj3.a().c(this.w0);
        }
        if (this.x0 != null) {
            hj3.a().c(this.x0);
        }
        this.v0 = true;
        this.u.m(this.v);
        u((Tab) this.y.f);
        if (this.u.t()) {
            this.u0 = true;
        }
        if (this.u0 && this.v0) {
            mVar2.a.u();
        }
        y13 y13Var = this.u;
        org.chromium.chrome.browser.toolbar.a aVar = this.f;
        aVar.b = y13Var;
        dz2 dz2Var = new dz2(aVar);
        aVar.c = dz2Var;
        y13Var.m(dz2Var);
        aVar.d = new ez2(aVar);
        aVar.b.u().a(aVar.d);
        aVar.b();
        y13 y13Var2 = this.u;
        bz0 bz0Var = this.e;
        bz0Var.c = y13Var2;
        y13Var2.m(bz0Var.b);
        boolean zR = bz0Var.c.r();
        Iterator<bz0.b> it = bz0Var.a.iterator();
        while (true) {
            b.a aVar2 = (b.a) it;
            if (!aVar2.hasNext()) {
                break;
            } else {
                ((bz0.b) aVar2.next()).i(zR);
            }
        }
        u7 u7Var = this.i;
        u7Var.l = bz0Var;
        bz0Var.a(u7Var);
        Tab tabI = this.u.i();
        ToolbarControlContainer toolbarControlContainer = this.l;
        if (tabI != null && tabI.c() != null && !tabI.getUrl().k()) {
            toolbarControlContainer.setReadyForBitmapCapture(true);
        }
        pk3 pk3Var = new pk3(this.T, this.S);
        this.p0 = new nv0(this.T, toolbarControlContainer.findViewById(R$id.home_button), pk3Var, new s20(4, bz0Var), this.H0, new se1(9), new bn0(1), this.y);
        this.q0 = new ib3(this.T, (ToggleTabStackButton) toolbarControlContainer.findViewById(R$id.tab_switcher_button), pk3Var, new cp(3, bz0Var), this.H0, this.O, new fc3(mVar2, 0), this.y);
        TraceEvent.C("ToolbarManager.initializeWithNative");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.vb
    public final int o() {
        boolean zA = this.c0.a();
        if (!zA) {
            aq1<ai> aq1Var = this.t;
            ai aiVar = aq1Var != null ? aq1Var.f : null;
            Tab tab = (Tab) this.y.f;
            y13 y13Var = this.u;
            if (y13Var != null) {
                y13Var.i();
            }
            if (aiVar != null) {
                Boolean.TRUE.equals(aiVar.getHandleBackPressChangedSupplier().get());
            }
            this.z.d();
            if (tab != null) {
                tab.isInitialized();
            }
            if (tab != null) {
                tab.isDestroyed();
            }
        }
        return !zA ? 1 : 0;
    }

    public final void q() {
        Tab tabD;
        if (DeviceFormFactor.a(this.T) && (tabD = this.z.d()) != null) {
            jk1 jk1VarU = tabD.u();
            boolean z = jk1VarU instanceof h;
            if ((z || (jk1VarU instanceof org.chromium.chrome.browser.ntp.d)) && qo.f().c() && z) {
                this.E.s();
            }
        }
    }

    @Override // l23.a
    public final void r(boolean z, boolean z2) {
        this.l.setImportantForAccessibility(z2 ? 4 : 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s() {
        /*
            r5 = this;
            z2 r0 = r5.y
            E r0 = r0.f
            org.chromium.chrome.browser.tab.Tab r0 = (org.chromium.chrome.browser.tab.Tab) r0
            r1 = 0
            if (r0 == 0) goto L46
            ad3 r0 = r5.c0
            zp1<ai> r2 = r0.d
            java.lang.Object r2 = r2.get()
            ai r2 = (defpackage.ai) r2
            r3 = 1
            if (r2 == 0) goto L27
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            zp1 r2 = r2.getHandleBackPressChangedSupplier()
            java.lang.Object r2 = r2.get()
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L27
            goto L40
        L27:
            boolean r2 = defpackage.zb.b()
            if (r2 == 0) goto L30
            nw2<org.chromium.chrome.browser.tab.Tab> r0 = r0.g
            goto L32
        L30:
            nw2<org.chromium.chrome.browser.tab.Tab> r0 = r0.a
        L32:
            java.lang.Object r0 = r0.get()
            org.chromium.chrome.browser.tab.Tab r0 = (org.chromium.chrome.browser.tab.Tab) r0
            if (r0 == 0) goto L42
            boolean r0 = r0.g()
            if (r0 == 0) goto L42
        L40:
            r0 = r3
            goto L43
        L42:
            r0 = r1
        L43:
            if (r0 == 0) goto L46
            r1 = r3
        L46:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            aq1<java.lang.Boolean> r5 = r5.L0
            r5.r(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.g.s():void");
    }

    public final void t() {
        this.k.a.w();
        f();
    }

    public final void u(Tab tab) {
        LocationBarModel locationBarModel = this.z;
        boolean z = locationBarModel.n;
        Tab tabD = locationBarModel.d();
        boolean zIsIncognito = tab != null ? tab.isIncognito() : this.u.r();
        locationBarModel.k = tab;
        if (locationBarModel.n != zIsIncognito) {
            locationBarModel.n = zIsIncognito;
            Iterator<f.a> it = locationBarModel.u.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((f.a) aVar.next()).r();
                }
            }
        }
        locationBarModel.A();
        locationBarModel.y();
        locationBarModel.z();
        locationBarModel.w();
        locationBarModel.x();
        H();
        AppCompatActivity appCompatActivity = this.T;
        if (tabD != null && z != zIsIncognito && DeviceFormFactor.a(appCompatActivity)) {
            this.Q.b();
        }
        if (tabD != tab || z != zIsIncognito) {
            int iA = yp.a(appCompatActivity, zIsIncognito);
            if (tab != null) {
                iA = this.g.c(tab.j(), tab);
            }
            d(iA, false);
            t();
            if (tab != null) {
                this.k.a.r();
            }
            v(12, false);
            q();
        }
        D();
        a aVar2 = this.s;
        aVar2.getClass();
        if (cc3.b()) {
            lj ljVar = aVar2.h;
            if (ljVar != null) {
                ljVar.d(aVar2);
                aVar2.h = null;
            }
            if (tab != null) {
                py2 py2VarA = py2.a(tab);
                lj ljVar2 = py2VarA != null ? py2VarA.h : null;
                if (ljVar2 != null) {
                    Integer numP = ljVar2.p(aVar2);
                    aVar2.h = ljVar2;
                    aVar2.r(numP);
                }
            }
        }
    }

    public final void v(int i, boolean z) {
        if (this.v0 && this.E.i() != null) {
            boolean zO = this.E.i().O();
            this.E.i().s(null, i, z);
            if (zO && z) {
                this.E.g();
            }
        }
    }

    @Override // defpackage.C0188h0.b
    public final void y0(boolean z) {
        AppCompatActivity appCompatActivity = this.T;
        if (this.K != zb2.d(appCompatActivity)) {
            qs1<c33> qs1Var = this.o0;
            if (qs1Var.get() != null) {
                qs1Var.get().a();
            }
            appCompatActivity.recreate();
            return;
        }
        m mVar = this.k;
        org.chromium.chrome.browser.toolbar.top.h hVar = mVar.b;
        if (hVar != null) {
            hVar.i = z;
            CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = hVar.j;
            if (customTabSwitcherModeTopToolbar != null) {
                customTabSwitcherModeTopToolbar.n();
                return;
            }
            return;
        }
        org.chromium.chrome.browser.toolbar.top.d dVar = mVar.c;
        if (dVar != null) {
            org.chromium.chrome.browser.toolbar.top.g gVar = dVar.a;
            gVar.e.l(cs2.j, z);
            gVar.g();
        }
    }
}
