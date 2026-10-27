package defpackage;

import J.N;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.dt2;
import defpackage.ef2;
import defpackage.l61;
import defpackage.ll;
import defpackage.z2;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.SysUtils;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.banners.AppBannerInProductHelpControllerProvider;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.toolbar.bottom.ScrollingBottomViewResourceFrameLayout;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.components.browser_ui.widget.InsetObserverView;
import org.chromium.components.webapps.bottomsheet.PwaBottomSheetController;
import org.chromium.components.webapps.bottomsheet.PwaBottomSheetControllerProvider;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: TabbedRootUiCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g63 extends nc2 {
    public final e F0;
    public k63 G0;
    public ah0 H0;
    public ft2 I0;
    public h63 J0;
    public mq1 K0;
    public nq1 L0;
    public b92 M0;
    public org.chromium.chrome.browser.feed.webfeed.e N0;
    public i63 O0;
    public yb3 P0;
    public t3 Q0;
    public i6 R0;
    public PwaBottomSheetController S0;
    public to1 T0;
    public iv0 U0;
    public fl1 V0;
    public bw W0;
    public h61 X0;
    public kv Y0;
    public ni3 Z0;
    public final int a1;
    public final nw2<InsetObserverView> b1;
    public final Function<Tab, Boolean> c1;
    public b d1;
    public final aq1<EphemeralTabCoordinator> e1;
    public int f1;
    public ll g1;

    /* JADX INFO: compiled from: TabbedRootUiCoordinator.java */
    public class a implements tb {
        public a() {
        }
    }

    /* JADX INFO: compiled from: TabbedRootUiCoordinator.java */
    public class b implements l61.a {
        public b() {
        }

        @Override // l61.a
        public final void g(int i, boolean z) {
            wk1 wk1Var;
            if (i != 2 || (wk1Var = g63.this.U0.n) == null) {
                return;
            }
            wk1Var.e();
        }
    }

    /* JADX INFO: compiled from: TabbedRootUiCoordinator.java */
    public class c implements xy0 {
        public c() {
        }
    }

    /* JADX INFO: compiled from: TabbedRootUiCoordinator.java */
    public class d implements ef2.a {
        public d() {
        }

        @Override // ef2.a
        public final void a(float f) {
            dt2 dt2Var = g63.this.q0;
            dt2Var.p = f;
            dt2Var.g();
        }

        @Override // ef2.a
        public final void b(float f) {
            z53 z53Var = g63.this.G0.a;
            if (z53Var == null) {
                return;
            }
            z53Var.q = f;
            boolean z = z53Var.n;
            Context context = z53Var.c;
            Window window = z53Var.a;
            int color = z ? context.getColor(R$color.toolbar_background_primary_dark) : ii2.a(window.getContext());
            int i = z53Var.e;
            window.setNavigationBarColor(z7.o(color, i & (-16777216), z53Var.q * ((i >>> 24) / 255.0f), true));
            window.setNavigationBarDividerColor(z7.o(z53Var.n ? context.getColor(R$color.bottom_system_nav_divider_color_light) : ii2.e(window.getContext()), i & (-16777216), z53Var.q * ((i >>> 24) / 255.0f), true));
            boolean zH = a8.h(1.0f, f);
            ViewGroup viewGroup = z53Var.b;
            if (zH) {
                uh3.m(viewGroup, false);
            } else if (a8.h(0.0f, f)) {
                uh3.m(viewGroup, true);
            }
        }

        @Override // ef2.a
        public final void c(int i) {
            g63.this.q0.o = i;
        }
    }

    /* JADX INFO: compiled from: TabbedRootUiCoordinator.java */
    public class e extends z2.b {
        public Tab h;

        public e(z2 z2Var) {
            super(z2Var, false);
        }

        @Override // z2.b
        public final void destroy() {
            b bVar;
            g63 g63Var = g63.this;
            l61 l61Var = g63Var.r;
            if (l61Var != null && (bVar = g63Var.d1) != null) {
                l61Var.f(bVar);
            }
            super.destroy();
            z1(null);
        }

        @Override // z2.b
        public final void y1(Tab tab) {
            z1(tab);
        }

        public final void z1(Tab tab) {
            Tab tab2 = this.h;
            if (tab2 != null && !tab2.isDestroyed()) {
                fx2.g(this.h).m = null;
            }
            this.h = tab;
            if (tab != null) {
                fx2.g(tab).m = g63.this.U0;
            }
        }
    }

    public g63(AppCompatActivity appCompatActivity, wb wbVar, gk2 gk2Var, z2 z2Var, f23 f23Var, aq1 aq1Var, aq1 aq1Var2, g23 g23Var, rs1 rs1Var, rs1 rs1Var2, rs1 rs1Var3, rs1 rs1Var4, aq1 aq1Var3, fj fjVar, g3 g3Var, j31 j31Var, r2 r2Var, aq1 aq1Var4, jf1 jf1Var, ks ksVar, aq1 aq1Var5, y6 y6Var, ur urVar, vr vrVar, hz2 hz2Var, qq0 qq0Var, aq1 aq1Var6, aq1 aq1Var7, js jsVar, ks ksVar2, ls lsVar, e7 e7Var, dt2.a aVar, aq1 aq1Var8, org.chromium.ui.base.d dVar, int i, m01 m01Var, n91 n91Var, rs1 rs1Var5, boolean z, zb zbVar) {
        super(appCompatActivity, wbVar, gk2Var, z2Var, f23Var, aq1Var, aq1Var2, g23Var, rs1Var, rs1Var2, rs1Var3, rs1Var4, aq1Var3, fjVar, g3Var, j31Var, r2Var, aq1Var4, jf1Var, ksVar, aq1Var5, y6Var, urVar, vrVar, hz2Var, qq0Var, aq1Var6, aq1Var7, jsVar, 0, ksVar2, lsVar, e7Var, aVar, dVar, rs1Var5, aq1Var8, z, zbVar);
        this.a1 = i;
        this.b1 = m01Var;
        this.c1 = n91Var;
        this.e1 = aq1Var8;
        this.y = new e63(this, 0);
        if (this.W0 == null) {
            this.W0 = new bw(new lj[0]);
        }
        bw bwVar = this.W0;
        vj vjVar = fjVar.f;
        bwVar.h.add(vjVar);
        vjVar.p(bwVar.i);
        this.F0 = new e(z2Var);
    }

    @Override // defpackage.nc2, defpackage.hk1
    public final void G0() {
        q2 q2Var;
        ll llVar;
        nw2<wp2> nw2Var;
        g3 g3Var;
        g63 g63Var;
        boolean zM6bsIDpc;
        super.G0();
        nw2<CompositorViewHolder> nw2Var2 = this.p0;
        CompositorViewHolder compositorViewHolder = nw2Var2.get();
        eu1 eu1Var = new eu1(22, this);
        ll llVar2 = this.Q;
        ll.c cVarC = llVar2.c(eu1Var);
        InsetObserverView insetObserverView = this.b1.get();
        a aVar = new a();
        CompositorViewHolder compositorViewHolder2 = nw2Var2.get();
        Objects.requireNonNull(compositorViewHolder2);
        sw swVar = new sw(compositorViewHolder2, 1);
        CompositorViewHolder compositorViewHolder3 = nw2Var2.get();
        Objects.requireNonNull(compositorViewHolder3);
        tw twVar = new tw(compositorViewHolder3, 1);
        h61 h61Var = this.X0;
        iv0 iv0Var = new iv0();
        g3 g3Var2 = this.j;
        iv0Var.o = new pt1(g3Var2, compositorViewHolder, cVarC);
        iv0Var.g = new org.chromium.chrome.browser.gesturenav.a(compositorViewHolder.getContext(), new yq2(12, iv0Var), iv0Var.o, new dm0(8, iv0Var));
        iv0Var.f = compositorViewHolder;
        q2 q2Var2 = this.g0;
        iv0Var.j = q2Var2;
        iv0Var.k = aVar;
        q2Var2.f(iv0Var);
        compositorViewHolder.addView(iv0Var.g);
        hv0 hv0Var = new hv0(iv0Var);
        c3 c3Var = new c3(27, iv0Var);
        z2 z2Var = this.k;
        iv0Var.i = new i20(z2Var, hv0Var, c3Var);
        iv0Var.p = swVar;
        iv0Var.q = twVar;
        Object obj = z2Var.f;
        if (obj != null) {
            iv0Var.l = (Tab) obj;
            boolean z = iv0Var.m;
            boolean zB = iv0Var.b();
            iv0Var.m = zB;
            if (zB != z) {
                iv0Var.c();
            }
        } else {
            iv0Var.m = iv0Var.b();
        }
        iv0Var.h = insetObserverView;
        insetObserverView.f.a(iv0Var);
        h61Var.h(iv0Var.o);
        oj2.O("GestureNavigation.Type", iv0Var.b());
        this.U0 = iv0Var;
        this.d1 = new b();
        this.F0.z1((Tab) z2Var.f);
        boolean zA = DeviceFormFactor.a(this.g);
        nw2<wp2> nw2Var3 = this.r0;
        if (zA) {
            d7 d7Var = this.h;
            ah0 ah0Var = new ah0(this.T.get(), this.n0.get().p0(false), this.g, d7Var == null ? null : d7Var.c, nw2Var3.get(), this.i0);
            this.H0 = ah0Var;
            y13 y13Var = ah0Var.b;
            Iterator it = y13Var.y().iterator();
            while (it.hasNext()) {
                ((TabModel) it.next()).j(ah0Var.d);
            }
            y13Var.m(ah0Var.e);
        }
        if (DeviceFormFactor.a(this.g) || !oj2.D(this.g)) {
            q2Var = q2Var2;
            llVar = llVar2;
            nw2Var = nw2Var3;
            g3Var = g3Var2;
        } else {
            g gVar = this.x;
            View viewInflate = ((ViewStub) gVar.T.findViewById(R$id.bottom_controls_stub)).inflate();
            k13 k13VarR = z7.r();
            AppCompatActivity appCompatActivity = gVar.T;
            ViewGroup viewGroup = (ViewGroup) viewInflate.findViewById(R$id.bottom_container_slot);
            bz0 bz0Var = gVar.e;
            ef2 ef2Var = gVar.D0;
            zp1<Boolean> zp1Var = gVar.r;
            ji jiVar = gVar.i0;
            q2 q2Var3 = gVar.h0;
            nw2<Boolean> nw2Var4 = gVar.j0;
            y13 y13Var2 = gVar.u;
            TabContentManager tabContentManager = gVar.k0;
            nw2Var = nw2Var3;
            CompositorViewHolder compositorViewHolder4 = gVar.W;
            Objects.requireNonNull(compositorViewHolder4);
            q2Var = q2Var2;
            g3Var = g3Var2;
            llVar = llVar2;
            gVar.K0 = k13VarR.b(appCompatActivity, viewGroup, bz0Var, ef2Var, zp1Var, jiVar, q2Var3, nw2Var4, y13Var2, tabContentManager, compositorViewHolder4, new fs(compositorViewHolder4, 1), gVar.l0, gVar.O, gVar.n0);
            ai aiVar = new ai(gVar.T, gVar.U, gVar.G, compositorViewHolder4.getResourceManager(), gVar.X, gVar.Y, (ScrollingBottomViewResourceFrameLayout) viewInflate, gVar.K0, gVar.m0, gVar.J0, gVar.s);
            gVar.t.r(aiVar);
            aiVar.getHandleBackPressChangedSupplier().p(new ec3(gVar, 0));
        }
        if (!SysUtils.isLowEndDevice()) {
            g63Var = this;
            AppCompatActivity appCompatActivity2 = g63Var.g;
            g63Var.e1.r(new EphemeralTabCoordinator(appCompatActivity2, g63Var.j, appCompatActivity2.getWindow().getDecorView(), g63Var.k, new p11(8, g63Var), g63Var.B, true));
        } else {
            g63Var = this;
        }
        g63Var.a0.h(llVar.b(new dr(5, g63Var)));
        PwaBottomSheetController pwaBottomSheetController = new PwaBottomSheetController(g63Var.g);
        g63Var.S0 = pwaBottomSheetController;
        aj3<PwaBottomSheetController> aj3Var = PwaBottomSheetControllerProvider.a;
        PwaBottomSheetControllerProvider.a.a(g3Var.r, pwaBottomSheetController);
        if (z7.B()) {
            new lv();
            Profile profileD = Profile.d();
            HashMap map = lv.a;
            kv kvVar = (kv) map.get(profileD);
            if (kvVar == null) {
                kv kvVar2 = new kv(n3.Y(profileD), n3.B());
                map.put(profileD, kvVar2);
                kvVar = kvVar2;
            }
            g63Var.Y0 = kvVar;
            y13 y13Var3 = g63Var.T.get();
            q2 q2Var4 = q2Var;
            kvVar.d = q2Var4;
            jv jvVar = new jv(kvVar);
            kvVar.e = jvVar;
            q2Var4.f(jvVar);
            if (a8.P()) {
                sk skVar = bq.a;
                zM6bsIDpc = N.M6bsIDpc("CommercePriceTracking", "implicit_subscriptions_enabled", false);
            } else {
                zM6bsIDpc = false;
            }
            if (zM6bsIDpc && kvVar.c == null) {
                kvVar.c = new ox0(y13Var3, kvVar.f);
            }
        }
        if (oj2.D(g63Var.g)) {
            g63Var.Z0 = new ni3(g63Var.g, g63Var.T.get(), nw2Var.get());
        } else {
            g63Var.Z0 = null;
        }
    }

    @Override // defpackage.nc2, defpackage.sz0
    public final void Y0() {
        super.Y0();
        this.G0 = new k63(this.g.getWindow(), this.T.get(), this.i0, this.o0);
    }

    @Override // defpackage.nc2
    public final ef2 c() {
        ViewGroup viewGroup = (ViewGroup) this.g.findViewById(R$id.coordinator);
        return new ef2(this.g, new d(), viewGroup, viewGroup.getContext().getColor(R$color.omnibox_focused_fading_background_color));
    }

    @Override // defpackage.nc2
    public final ly0 e() {
        ly0 ly0Var = new ly0(this.g, this.T.get(), this.j0.get(), new ny0(), new n3(), new c(), this.X0, null, true);
        ll llVar = new ll();
        this.g1 = llVar;
        this.U.h(llVar.b(new on0(16, ly0Var)));
        this.V.h(this.g1.b(new jb1(8, ly0Var)));
        return ly0Var;
    }

    @Override // defpackage.nc2
    public final void m() {
        EphemeralTabCoordinator ephemeralTabCoordinator = this.e1.f;
        if (ephemeralTabCoordinator != null) {
            if (ephemeralTabCoordinator.t || ephemeralTabCoordinator.u) {
                ephemeralTabCoordinator.j.c(ephemeralTabCoordinator.o, true);
            }
        }
    }

    @Override // defpackage.nc2
    public final void n(h61 h61Var) {
        super.n(h61Var);
        if (!DeviceFormFactor.a(this.g)) {
            fj fjVar = this.R;
            AppCompatActivity appCompatActivity = this.g;
            ResourceManager resourceManager = this.p0.get().getResourceManager();
            l23 l23Var = (l23) this.e.f;
            dt2 dt2Var = this.q0;
            Objects.requireNonNull(dt2Var);
            ft2 ft2Var = new ft2(appCompatActivity, resourceManager, fjVar, l23Var, new yq2(18, dt2Var), this.y, new jb1(9, h61Var));
            this.I0 = ft2Var;
            h61Var.h(ft2Var.b);
            h63 h63Var = new h63(this);
            this.J0 = h63Var;
            this.I0.a.g.add(h63Var);
            this.I0.a.g.add(dt2Var);
            aq1 aq1Var = new aq1();
            g gVar = this.x;
            aq1Var.r(Boolean.valueOf(gVar.E.i() == null ? false : gVar.E.i().O()));
            this.O0 = new i63(aq1Var);
            this.K0 = new mq1(this.g, this.I0, aq1Var, this.y);
            if (this.x.E.i() != null) {
                this.x.E.i().C(this.O0);
            }
        }
        this.X0 = h61Var;
    }

    @Override // defpackage.nc2
    public final void o(l61 l61Var) {
        super.o(l61Var);
        b bVar = this.d1;
        if (bVar != null) {
            l61Var.j(bVar);
        }
    }

    @Override // defpackage.nc2, defpackage.v80
    public final void onDestroy() {
        ll llVar;
        z53 z53Var;
        vk0.c(1);
        k63 k63Var = this.G0;
        if (k63Var != null && (z53Var = k63Var.a) != null) {
            y13 y13Var = z53Var.g;
            if (y13Var != null) {
                y13Var.l(z53Var.h);
            }
            z51 z51Var = z53Var.j;
            if (z51Var != null) {
                z51Var.f(z53Var.k);
            }
            ll llVar2 = z53Var.l;
            if (llVar2 != null) {
                llVar2.a();
                z53Var.l = null;
            }
            z53Var.d.c(z53Var.i);
        }
        ah0 ah0Var = this.H0;
        if (ah0Var != null && (llVar = ah0Var.g) != null) {
            llVar.a();
            ah0Var.g = null;
        }
        mq1 mq1Var = this.K0;
        if (mq1Var != null) {
            iq1 iq1Var = mq1Var.d;
            if (iq1Var != null) {
                org.chromium.base.b<ApplicationStatus.d> bVar = ApplicationStatus.f;
                if (bVar != null) {
                    bVar.c(iq1Var);
                }
                jx jxVar = iq1Var.f;
                if (jxVar != null) {
                    NetworkChangeNotifier.f(jxVar);
                    jxVar.g();
                    jxVar.e = null;
                    iq1Var.f = null;
                }
                iq1Var.j.removeCallbacks(iq1Var.k);
                mq1Var.d = null;
            }
            zp1<Boolean> zp1Var = mq1Var.e;
            if (zp1Var != null) {
                zp1Var.d(mq1Var.g);
                mq1Var.e = null;
            }
            mq1Var.g = null;
            Handler handler = mq1Var.c;
            if (handler != null) {
                handler.removeCallbacks(mq1Var.j);
                handler.removeCallbacks(mq1Var.l);
            }
        }
        g gVar = this.x;
        if (gVar != null) {
            gVar.E.i().H(this.O0);
        }
        nq1 nq1Var = this.L0;
        if (nq1Var != null) {
            nq1Var.i.a.g.remove(nq1Var);
        }
        ft2 ft2Var = this.I0;
        if (ft2Var != null) {
            ft2Var.a.g.remove(this.J0);
            this.I0.a.g.remove(this.q0);
            ft2 ft2Var2 = this.I0;
            if (ft2Var2.k) {
                ft2Var2.d.run();
            }
            boolean z = ft2Var2.h;
            if (z && z) {
                ft2Var2.f.i.b();
                ft2Var2.g.a().e(ft2Var2.e);
                ft2Var2.h = false;
            }
            qt2 qt2Var = ft2Var2.a;
            ValueAnimator valueAnimator = qt2Var.p;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator valueAnimator2 = qt2Var.q;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            AnimatorSet animatorSet = qt2Var.r;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            AnimatorSet animatorSet2 = qt2Var.s;
            if (animatorSet2 != null) {
                animatorSet2.cancel();
            }
            qt2Var.f.s(qt2Var);
            qt2Var.h.e(qt2Var);
        }
        yb3 yb3Var = this.P0;
        if (yb3Var != null) {
            vk0.c(3);
            yb3Var.e.a();
            yb3Var.h.e(yb3Var);
        }
        org.chromium.chrome.browser.feed.webfeed.e eVar = this.N0;
        if (eVar != null) {
            eVar.c.a();
        }
        e eVar2 = this.F0;
        if (eVar2 != null) {
            eVar2.destroy();
        }
        t3 t3Var = this.Q0;
        if (t3Var != null) {
            t3Var.a = null;
        }
        i6 i6Var = this.R0;
        if (i6Var != null) {
            AppBannerInProductHelpControllerProvider.a.b(i6Var);
        }
        PwaBottomSheetController pwaBottomSheetController = this.S0;
        if (pwaBottomSheetController != null) {
            PwaBottomSheetControllerProvider.a.b(pwaBottomSheetController);
        }
        iv0 iv0Var = this.U0;
        if (iv0Var != null) {
            wk1 wk1Var = iv0Var.n;
            nw2<CompositorViewHolder> nw2Var = this.p0;
            if (nw2Var.f() && wk1Var != null) {
                nw2Var.get().f.c(wk1Var);
            }
            iv0 iv0Var2 = this.U0;
            i20 i20Var = iv0Var2.i;
            if (i20Var != null) {
                i20Var.a();
                iv0Var2.i = null;
            }
            InsetObserverView insetObserverView = iv0Var2.h;
            if (insetObserverView != null) {
                insetObserverView.f.c(iv0Var2);
                iv0Var2.h = null;
            }
            iv0Var2.g = null;
            iv0Var2.f.removeCallbacks(iv0Var2.e);
            pt1 pt1Var = iv0Var2.o;
            if (pt1Var != null) {
                pt1Var.f.destroy();
                iv0Var2.o = null;
            }
            wk1 wk1Var2 = iv0Var2.n;
            if (wk1Var2 != null) {
                wk1Var2.g = null;
                wk1Var2.b.removeOnAttachStateChangeListener(wk1Var2.e);
                wk1Var2.d = null;
                iv0Var2.q.onResult(iv0Var2.n);
                iv0Var2.n = null;
            }
            q2 q2Var = iv0Var2.j;
            if (q2Var != null) {
                q2Var.e(iv0Var2);
                iv0Var2.j = null;
            }
            this.U0 = null;
        }
        ni3 ni3Var = this.Z0;
        if (ni3Var != null) {
            y13 y13Var2 = ni3Var.f;
            if (y13Var2 != null) {
                y13Var2.l(ni3Var.i);
                g03 g03Var = (g03) y13Var2.u().d(false);
                ki3 ki3Var = ni3Var.h;
                g03Var.W(ki3Var);
                ((g03) y13Var2.u().d(true)).W(ki3Var);
            }
            ni3Var.j.a();
        }
        kv kvVar = this.Y0;
        if (kvVar != null) {
            kvVar.a();
            this.Y0 = null;
        }
        to1 to1Var = this.T0;
        if (to1Var != null) {
            to1.g.b(to1Var);
            this.T0 = null;
        }
        ll llVar3 = this.g1;
        if (llVar3 != null) {
            llVar3.a();
        }
        super.onDestroy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0149  */
    /* JADX WARN: Type inference failed for: r4v14, types: [int] */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean t() {
        /*
            Method dump skipped, instruction units count: 1021
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.g63.t():boolean");
    }
}
