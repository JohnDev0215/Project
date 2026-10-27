package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.appbar.AppBarLayout;
import defpackage.ds2;
import defpackage.es2;
import defpackage.l43;
import defpackage.lr2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.b;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.o1;
import org.chromium.chrome.browser.tasks.tab_management.p1;
import org.chromium.chrome.features.tasks.TasksView;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.prefs.PrefService;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: StartSurfaceCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nr2 implements lr2 {
    public final n1 A;
    public ri0 B;
    public PropertyModel C;
    public yr2 D;
    public boolean E;
    public boolean F;
    public boolean G;
    public final b<sf2> H = new b<>();
    public final long I = SystemClock.elapsedRealtimeNanos();

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final boolean f50J;
    public mr2 K;
    public FeedSwipeRefreshLayout L;
    public n1 M;
    public TasksView N;
    public si1 O;
    public qi1 P;
    public ca3 Q;
    public boolean R;
    public final Activity a;
    public final ef2 b;
    public final yr2 c;
    public final boolean d;
    public final ji e;
    public final nw2<Tab> f;
    public final WindowAndroid g;
    public ViewGroup h;
    public final nw2<df0> i;
    public final y13 j;
    public final fj k;
    public final wp2 l;
    public final nw2<ck2> m;
    public final nw2<sr1> n;
    public final TabContentManager o;
    public final bi1 p;
    public final jp q;
    public final q2 r;
    public final gz2 s;
    public final jf1 t;
    public final tj1 u;
    public final nw2<qb3> v;
    public final aq1<p1> w;
    public final qs1<hy0> x;
    public g73 y;
    public g73 z;

    /* JADX INFO: compiled from: StartSurfaceCoordinator.java */
    public class a implements uf2 {
        public a() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public nr2(Activity activity, ef2 ef2Var, f fVar, rs1 rs1Var, aq1 aq1Var, boolean z, g3 g3Var, ViewGroup viewGroup, hs hsVar, a23 a23Var, fj fjVar, wp2 wp2Var, gk2 gk2Var, is isVar, TabContentManager tabContentManager, bi1 bi1Var, jp jpVar, r2 r2Var, gz2 gz2Var, jf1 jf1Var, uj1 uj1Var, j31 j31Var, es esVar, zb zbVar, rs1 rs1Var2, tr trVar) {
        boolean z2;
        n1.a aVar;
        Activity activity2;
        Runnable ve3Var;
        View viewFindViewById;
        ViewGroup bodyViewContainer;
        n1.a aVar2;
        this.a = activity;
        this.b = ef2Var;
        boolean zD = zb2.d(activity);
        this.d = zD;
        this.e = fVar;
        this.f = aq1Var;
        this.g = g3Var;
        this.h = viewGroup;
        this.i = hsVar;
        this.j = a23Var;
        this.k = fjVar;
        this.l = wp2Var;
        this.m = gk2Var;
        this.n = isVar;
        this.o = tabContentManager;
        this.p = bi1Var;
        this.q = jpVar;
        this.r = r2Var;
        this.s = gz2Var;
        this.t = jf1Var;
        this.u = uj1Var;
        this.v = esVar;
        this.x = rs1Var2;
        aq1<p1> aq1Var2 = new aq1<>();
        this.w = aq1Var2;
        boolean z3 = (zD && bq.P.a()) ? false : true;
        boolean zE = zb2.e(activity);
        this.f50J = zE;
        if (zD || zE) {
            z2 = zD;
            if (zE) {
                f();
                ArrayList arrayList = new ArrayList(Arrays.asList(n73.D));
                arrayList.addAll(Arrays.asList(zr2.i));
                this.C = new PropertyModel(arrayList);
                char c = CachedFeatureFlags.b(ar.d.b("StartSurfaceAndroid:show_last_active_tab_only"), true) ? (char) 2 : (char) 1;
                TasksView tasksView = (TasksView) LayoutInflater.from(activity).inflate(R$layout.tasks_view_layout, (ViewGroup) null);
                this.N = tasksView;
                tasksView.setId(R$id.primary_tasks_surface_view);
                TasksView tasksView2 = this.N;
                if (aq1Var.f()) {
                    ((Tab) aq1Var.f).isIncognito();
                }
                ag2 ag2Var = tasksView2.G;
                cg2 cg2Var = ag2Var.c;
                cg2Var.i = r2Var;
                r2Var.f(cg2Var);
                if (cg2Var.i.h()) {
                    cg2Var.G0();
                }
                ag2Var.d = g3Var;
                if (c == 1) {
                    aVar = null;
                    this.M = z7.r().c(activity, r2Var, a23Var, tabContentManager, fjVar, gz2Var, jf1Var, this.N.getCarouselTabSwitcherContainer(), uj1Var, ef2Var, this.N, hsVar, wp2Var, bi1Var);
                    activity2 = activity;
                } else {
                    aVar = null;
                    activity2 = activity;
                    this.M = new org.chromium.chrome.features.tasks.b(activity2, this.N.getCarouselTabSwitcherContainer(), a23Var);
                }
                boolean z4 = !zb2.j(activity);
                this.O = new si1(activity, r2Var, this.N.findViewById(R$id.mv_tiles_container), g3Var, oj2.f0(activity2, DeviceFormFactor.a(activity)), z4, z4 ? Integer.MAX_VALUE : SharedPreferencesManager.getInstance().readBoolean("Chrome.QueryTiles.ShownOnStartSurface", false) ? q82.b(activity) : 2);
                if (z3) {
                    SharedPreferencesManager.getInstance().l("Chrome.QueryTiles.ShownOnStartSurface", false);
                } else if (ProfileManager.b) {
                    m(Profile.d());
                } else {
                    new org.chromium.chrome.browser.profiles.a().h(new x10(20, this));
                }
                l();
                addHeaderOffsetChangeListener(this.K);
                j.b(this.C, new es2.a(this.h, this.N, this.L), new cg(23));
                j.b(this.C, this.N, new ee3(9));
                ve3Var = new ve3(5, this);
                viewFindViewById = this.N.findViewById(R$id.logo_container);
                bodyViewContainer = (ViewGroup) this.N.findViewById(R$id.tasks_surface_body);
                aVar2 = aVar;
            } else {
                f();
                ArrayList arrayList2 = new ArrayList(Arrays.asList(n73.D));
                arrayList2.addAll(Arrays.asList(zr2.i));
                this.C = new PropertyModel(arrayList2);
                g73 g73VarH = h(activity, ef2Var, this.C, CachedFeatureFlags.b(ar.d.b("StartSurfaceAndroid:show_last_active_tab_only"), true) ? 2 : 1, aq1Var, true, !z3, g3Var, r2Var, a23Var, wp2Var, hsVar, tabContentManager, bi1Var, fjVar, gz2Var, jf1Var, uj1Var, this.h, null);
                this.y = g73VarH;
                g73VarH.b.setId(R$id.primary_tasks_surface_view);
                l();
                addHeaderOffsetChangeListener(this.K);
                j.b(this.C, new es2.a(this.h, this.y.b, this.L), new cg(24));
                n1.a aVarB = this.y.b();
                g73 g73Var = this.y;
                Objects.requireNonNull(g73Var);
                es3 es3Var = new es3(1, g73Var);
                aVar = aVarB;
                viewFindViewById = this.y.b.findViewById(R$id.logo_container);
                ve3Var = es3Var;
                bodyViewContainer = this.y.b.getBodyViewContainer();
                aVar2 = null;
            }
        } else {
            z2 = zD;
            o1 o1VarE = z7.r().e(activity, r2Var, a23Var, tabContentManager, fjVar, gz2Var, jf1Var, viewGroup, uj1Var, ef2Var, viewGroup, hsVar, wp2Var, bi1Var, rs1Var2, zbVar);
            aVar2 = null;
            this.A = o1VarE;
            aq1Var2.r(o1VarE.M);
            aVar = o1VarE.j;
            ve3Var = null;
            viewFindViewById = null;
            bodyViewContainer = null;
        }
        this.c = new yr2(aVar, viewGroup, this.M, a23Var, this.C, this.y != null ? new im2(this) : aVar2, z2, activity, fjVar, new gc3(this), z3, rs1Var, z, j31Var, ve3Var, aq1Var, viewFindViewById, this.A == null ? zbVar : aVar2, bodyViewContainer, r2Var, trVar);
        rs1Var.a(this);
    }

    public static g73 h(Activity activity, ef2 ef2Var, PropertyModel propertyModel, int i, nw2 nw2Var, boolean z, boolean z2, WindowAndroid windowAndroid, q2 q2Var, y13 y13Var, wp2 wp2Var, nw2 nw2Var2, TabContentManager tabContentManager, bi1 bi1Var, kj kjVar, gz2 gz2Var, jf1 jf1Var, tj1 tj1Var, ViewGroup viewGroup, qs1 qs1Var) {
        return new g73(activity, ef2Var, propertyModel, i, nw2Var, z, z2, windowAndroid, q2Var, y13Var, wp2Var, nw2Var2, tabContentManager, bi1Var, kjVar, gz2Var, jf1Var, tj1Var, viewGroup, qs1Var);
    }

    @Override // defpackage.lr2
    public final n1.c A() {
        if (!this.d) {
            return this.A.s();
        }
        if (this.f50J) {
            return null;
        }
        if (this.z == null) {
            this.c.l0(J());
        }
        return this.z.d();
    }

    @Override // defpackage.lr2
    public final boolean B() {
        return this.c.H();
    }

    @Override // defpackage.lr2
    public final int C() {
        return this.c.D;
    }

    @Override // defpackage.lr2
    public final void D() {
        PropertyModel propertyModel = this.c.h;
        if (propertyModel == null) {
            return;
        }
        PropertyModel.r rVar = n73.C;
        Boolean bool = Boolean.TRUE;
        propertyModel.p(rVar, bool);
        propertyModel.p(zr2.h, bool);
        ds2.a.a.getClass();
    }

    @Override // defpackage.lr2
    public final aq1 E() {
        return this.w;
    }

    @Override // defpackage.lr2
    public final void F(int i) {
        this.c.Z(i);
    }

    @Override // defpackage.lr2
    public final n1.c G() {
        if (this.d) {
            return this.f50J ? this.M.s() : this.y.d();
        }
        return null;
    }

    @Override // defpackage.lr2
    public final void H(l43.a aVar) {
        this.c.e.c(aVar);
    }

    @Override // defpackage.lr2
    public final void I() {
        this.c.f.t1();
    }

    public final n1.a J() {
        PropertyModel propertyModel = new PropertyModel(n73.D);
        yr2 yr2Var = this.c;
        yr2Var.w = propertyModel;
        propertyModel.l(n73.b, yr2Var.y);
        yr2Var.w.l(n73.t, false);
        yr2Var.w.l(n73.u, false);
        yr2Var.w.l(n73.i, false);
        yr2Var.w.l(n73.e, false);
        g73 g73VarH = h(this.a, this.b, propertyModel, 0, this.f, false, false, this.g, this.r, this.j, this.l, this.i, this.o, this.p, this.k, this.s, this.t, this.u, this.h, this.x);
        this.z = g73VarH;
        if (this.E) {
            g73VarH.h(this.n.get());
            this.z.f();
        } else {
            this.G = true;
        }
        this.z.b.setId(R$id.secondary_tasks_surface_view);
        j.b(this.C, new es2.a(this.h, this.z.b, null), new ag(22));
        yr2 yr2Var2 = this.D;
        if (yr2Var2 != null) {
            this.z.setOnTabSelectingListener(yr2Var2);
            this.D = null;
        }
        aq1<p1> aq1Var = this.w;
        if (!aq1Var.f()) {
            aq1Var.r(this.z.e());
        }
        return this.z.b();
    }

    @Override // defpackage.lr2
    public final boolean a() {
        n1 n1Var = this.A;
        if (n1Var != null) {
            return n1Var.a();
        }
        boolean zQ = this.c.Q();
        if (zQ) {
            zb.c(4);
        }
        return zQ;
    }

    @Override // defpackage.lr2
    public void addHeaderOffsetChangeListener(AppBarLayout.f fVar) {
        g73 g73Var = this.y;
        if (g73Var != null) {
            g73Var.addHeaderOffsetChangeListener(fVar);
            return;
        }
        TasksView tasksView = this.N;
        if (tasksView != null) {
            tasksView.addHeaderOffsetChangeListener(fVar);
        }
    }

    @Override // defpackage.lr2
    public final void b() {
        g(false);
        e();
    }

    @Override // defpackage.lr2
    public final void c(boolean z) {
        G().t();
        yr2 yr2Var = this.c;
        PropertyModel propertyModel = yr2Var.h;
        if (propertyModel == null) {
            return;
        }
        yr2Var.M = true;
        n1.a aVar = yr2Var.f;
        aVar.v0();
        yr2Var.O();
        y13 y13Var = yr2Var.g;
        boolean zR = y13Var.r();
        yr2Var.y = zR;
        propertyModel.l(n73.b, zR);
        yr2Var.h0(!yr2Var.y);
        yr2Var.g0(!yr2Var.y);
        boolean z2 = false;
        yr2Var.s0(yr2Var.D() > 0 && !yr2Var.y);
        yr2Var.V((yr2Var.y || yr2Var.u == null) ? false : true);
        yr2Var.k0(false);
        yr2Var.X(!yr2Var.y);
        yr2Var.x0();
        kj kjVar = yr2Var.I;
        yr2Var.w0(kjVar.a());
        yr2Var.T(kjVar.g());
        if (yr2Var.y && y13Var.g(true).getCount() <= 0) {
            z2 = true;
        }
        yr2Var.Y(z2);
        if (propertyModel.k(zr2.c) && propertyModel.j(zr2.f) == null && !((gc3) yr2Var.K).e() && yr2Var.u != null) {
            yr2Var.v();
        }
        propertyModel.l(zr2.e, true);
        TabModel tabModel = yr2Var.F;
        if (tabModel != null) {
            tabModel.j(yr2Var.G);
        } else {
            yr2Var.N = true;
        }
        y13Var.m(yr2Var.H);
        vr2 vr2Var = yr2Var.f103J;
        if (vr2Var != null) {
            kjVar.o(vr2Var);
        }
        sr1 sr1Var = yr2Var.z;
        if (sr1Var != null) {
            sr1Var.C(yr2Var.B);
        }
        aVar.Z0(z);
        n3.X0("StartSurface.Shown");
        n3.X0("StartSurface.SinglePane.Home");
        yr2Var.K();
    }

    @Override // defpackage.lr2
    public final void d() {
        boolean z;
        if (this.E) {
            return;
        }
        this.E = true;
        boolean z2 = this.d;
        if (z2) {
            TasksView tasksView = this.N;
            z = z2;
            this.B = new ri0(this.a, tasksView != null ? tasksView.getBodyViewContainer() : this.y.b.getBodyViewContainer(), this.C, this.e, this.f, new a(), this.l, this.m, this.g, this.j, this.v, this.I, this.L);
        } else {
            z = z2;
        }
        nw2<sr1> nw2Var = this.n;
        sr1 sr1Var = z ? nw2Var.get() : null;
        ri0 ri0Var = this.B;
        PrefService prefServiceK = oj2.k(Profile.d());
        yr2 yr2Var = this.c;
        yr2Var.X = true;
        yr2Var.z = sr1Var;
        yr2Var.u = ri0Var;
        yr2Var.W = this.l;
        n1 n1Var = yr2Var.V;
        PropertyModel propertyModel = yr2Var.h;
        if (propertyModel != null) {
            propertyModel.l(n73.i, sr1Var.d().a());
            yr2Var.z.t();
            propertyModel.l(n73.e, false);
            if (yr2Var.f.w1()) {
                yr2Var.z.C(yr2Var.B);
                if (yr2Var.H()) {
                    if (yr2Var.u != null) {
                        yr2Var.V(!yr2Var.y);
                    }
                    Runnable runnable = yr2Var.m;
                    if (runnable != null) {
                        runnable.run();
                    }
                    org.chromium.chrome.browser.logo.a aVar = yr2Var.x;
                    if (aVar != null) {
                        aVar.b();
                    }
                }
            }
            if (n1Var != null) {
                propertyModel.p(n73.p, new ie0(26, yr2Var));
                propertyModel.p(n73.q, new xr2(yr2Var));
                propertyModel.p(n73.v, new by1(22, yr2Var));
                propertyModel.p(n73.r, new vt(24, yr2Var));
            }
        }
        if (n1Var != null) {
            n1Var.d();
        }
        yr2Var.O = Boolean.valueOf(prefServiceK.a("ntp_snippets.list_visible"));
        n1 n1Var2 = this.A;
        if (n1Var2 != null) {
            n1Var2.d();
        }
        g73 g73Var = this.y;
        if (g73Var != null) {
            sr1 sr1Var2 = nw2Var.get();
            yr2Var.C();
            g73Var.h(sr1Var2);
        }
        if (this.F) {
            o();
        }
        if (!this.f50J && this.G) {
            this.G = false;
            this.z.h(nw2Var.get());
            this.z.f();
        }
    }

    @Override // defpackage.lr2
    public final void destroy() {
        e();
        mr2 mr2Var = this.K;
        if (mr2Var != null) {
            removeHeaderOffsetChangeListener(mr2Var);
            this.K = null;
        }
        yr2 yr2Var = this.c;
        if (yr2Var != null) {
            org.chromium.chrome.browser.logo.a aVar = yr2Var.x;
            if (aVar != null) {
                aVar.a();
                yr2Var.x = null;
            }
            ll llVar = yr2Var.q;
            if (llVar != null) {
                llVar.a();
            }
            yr2Var.M();
            yr2Var.t.e(yr2Var);
        }
    }

    @Override // defpackage.lr2
    public final void e() {
        boolean z = this.E;
        yr2 yr2Var = this.c;
        if (z) {
            if (this.y != null) {
                yr2Var.M();
                this.y.i();
            }
            g73 g73Var = this.z;
            if (g73Var != null) {
                g73Var.i();
            }
            qi1 qi1Var = this.P;
            if (qi1Var != null) {
                qi1Var.a();
                this.P = null;
            }
            ca3 ca3Var = this.Q;
            if (ca3Var != null) {
                ca3Var.c();
                this.Q = null;
            }
            si1 si1Var = this.O;
            if (si1Var != null) {
                si1Var.a();
                this.R = false;
            }
        }
        yr2Var.S();
    }

    public final void f() {
        int i = R$id.toolbar_container;
        Activity activity = this.a;
        FeedSwipeRefreshLayout feedSwipeRefreshLayoutJ = FeedSwipeRefreshLayout.j(activity, i);
        this.L = feedSwipeRefreshLayoutJ;
        this.h.addView(feedSwipeRefreshLayoutJ);
        FrameLayout frameLayout = new FrameLayout(activity);
        this.L.addView(frameLayout);
        this.L.setVisibility(8);
        this.h = frameLayout;
    }

    @Override // defpackage.lr2
    public final void g(boolean z) {
        this.c.f.g(z);
    }

    @Override // defpackage.lr2
    public final void i(ViewGroup viewGroup) {
        wp2 wp2Var = this.c.W;
        if (wp2Var == null) {
            return;
        }
        wp2Var.k = viewGroup;
        yp2 yp2Var = wp2Var.f;
        if (yp2Var != null) {
            yp2Var.b(viewGroup);
        }
    }

    public final int j(int i) {
        return this.a.getResources().getDimensionPixelSize(i);
    }

    @Override // defpackage.lr2
    public final nw2<Boolean> k() {
        n1 n1Var = this.A;
        return n1Var != null ? n1Var.k() : this.M != null ? new p11(15, this) : new yo(18, this);
    }

    /* JADX WARN: Type inference failed for: r0v18, types: [mr2] */
    public final void l() {
        final int iJ = j(R$dimen.location_bar_vertical_margin);
        final int iJ2 = j(R$dimen.ntp_logo_margin_bottom) + j(R$dimen.ntp_logo_margin_top) + j(R$dimen.ntp_logo_height);
        final int iJ3 = j(R$dimen.ntp_search_box_height);
        final int iJ4 = iJ3 - (j(R$dimen.toolbar_height_no_shadow) - (iJ * 2));
        final int iJ5 = j(R$dimen.search_box_end_padding);
        final int iJ6 = (j(R$dimen.fake_search_box_lateral_padding) - j(R$dimen.search_box_start_padding)) + j(R$dimen.location_bar_icon_end_padding_focused) + j(R$dimen.location_bar_status_icon_width);
        final int iJ7 = j(R$dimen.tasks_surface_location_bar_url_button_size);
        final int iJ8 = j(R$dimen.location_bar_action_icon_width);
        final int iJ9 = j(R$dimen.tasks_surface_location_bar_url_button_start_margin);
        this.K = new AppBarLayout.f() { // from class: mr2
            @Override // com.google.android.material.appbar.AppBarLayout.b
            public final void a(int i) {
                nr2 nr2Var = this.a;
                Iterator<sf2> it = nr2Var.H.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((sf2) aVar.next()).a(i);
                    }
                }
                int i2 = R$dimen.control_container_height;
                yr2 yr2Var = nr2Var.c;
                Context context = yr2Var.A;
                int dimensionPixelSize = (-i) - (((context.getResources().getDimensionPixelSize(i2) + (yr2Var.I() ? 0 : context.getResources().getDimensionPixelSize(R$dimen.start_surface_fake_search_box_top_margin))) + (yr2Var.I() ? iJ2 : 0)) - iJ);
                int i3 = iJ4;
                int iQ = a8.q(dimensionPixelSize, 0, i3);
                float f = iQ / i3;
                g73 g73Var = nr2Var.y;
                TasksView tasksView = g73Var != null ? g73Var.b : nr2Var.N;
                int i4 = iJ3 - iQ;
                float f2 = 1.0f - f;
                int i5 = (int) (iJ5 * f2);
                jg2.a().getClass();
                float f3 = iJ6 * f;
                int i6 = (int) (((iJ8 - r6) * f) + iJ7);
                int i7 = (int) (iJ9 * f2);
                if (tasksView.G.b.getVisibility() != 0) {
                    return;
                }
                tasksView.G.c.f.n(dg2.o, i4);
                tasksView.G.c.f.n(dg2.p, iQ);
                tasksView.G.c.f.n(dg2.q, i5);
                tasksView.G.c.f.m(dg2.r, f3);
                tasksView.G.c.f.n(dg2.s, i6);
                tasksView.G.c.f.n(dg2.t, i6);
                tasksView.G.c.f.n(dg2.u, i7);
            }
        };
    }

    public final void m(Profile profile) {
        if (!r82.d()) {
            SharedPreferencesManager.getInstance().l("Chrome.QueryTiles.ShownOnStartSurface", false);
        } else {
            new q82((ViewGroup) this.N.findViewById(R$id.query_tiles_layout), profile, new l51(4, this));
            SharedPreferencesManager.getInstance().l("Chrome.QueryTiles.ShownOnStartSurface", true);
        }
    }

    @Override // defpackage.lr2
    public final ViewGroup n() {
        return this.c.U;
    }

    @Override // defpackage.lr2
    public final void o() {
        if (!this.E) {
            this.F = true;
            return;
        }
        this.F = false;
        g73 g73Var = this.y;
        if (g73Var != null) {
            g73Var.f();
        }
    }

    @Override // defpackage.lr2
    public final void p(l43.a aVar) {
        this.c.e.a(aVar);
    }

    @Override // defpackage.lr2
    public final void q(lr2.b bVar) {
        this.c.k.a(bVar);
    }

    @Override // defpackage.lr2
    public final void r(boolean z) {
        yr2 yr2Var = this.c;
        PropertyModel propertyModel = yr2Var.h;
        if (propertyModel != null) {
            n3.X0("StartSurface.Shown");
            y13 y13Var = yr2Var.g;
            boolean zR = y13Var.r();
            yr2Var.y = zR;
            propertyModel.l(n73.b, zR);
            if (yr2Var.C == 0) {
                yr2Var.C = 4;
            }
            yr2Var.p0(yr2Var.u());
            if (propertyModel.k(zr2.c) && propertyModel.j(zr2.f) == null && !((gc3) yr2Var.K).e() && yr2Var.u != null) {
                yr2Var.v();
            }
            y13Var.m(yr2Var.H);
            vr2 vr2Var = yr2Var.f103J;
            if (vr2Var != null) {
                yr2Var.I.o(vr2Var);
            }
            propertyModel.l(zr2.e, true);
            sr1 sr1Var = yr2Var.z;
            if (sr1Var != null) {
                sr1Var.C(yr2Var.B);
            }
        }
        yr2Var.K();
        yr2Var.f.Z0(z);
    }

    @Override // defpackage.lr2
    public void removeHeaderOffsetChangeListener(AppBarLayout.f fVar) {
        g73 g73Var = this.y;
        if (g73Var != null) {
            g73Var.removeHeaderOffsetChangeListener(fVar);
            return;
        }
        TasksView tasksView = this.N;
        if (tasksView != null) {
            tasksView.removeHeaderOffsetChangeListener(fVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    @Override // defpackage.lr2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s(long r11, boolean r13) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nr2.s(long, boolean):void");
    }

    @Override // defpackage.lr2
    public void setOnTabSelectingListener(lr2.a aVar) {
        yr2 yr2Var = this.c;
        yr2Var.setOnTabSelectingListener(aVar);
        g73 g73Var = this.y;
        if (g73Var != null) {
            g73Var.setOnTabSelectingListener(yr2Var);
        } else {
            n1 n1Var = this.A;
            if (n1Var != null) {
                n1Var.setOnTabSelectingListener(yr2Var);
            } else {
                this.M.setOnTabSelectingListener(yr2Var);
            }
        }
        if (!this.f50J && this.d) {
            g73 g73Var2 = this.z;
            if (g73Var2 == null) {
                this.D = yr2Var;
            } else {
                g73Var2.setOnTabSelectingListener(yr2Var);
            }
        }
    }

    @Override // defpackage.lr2
    public final int t() {
        return this.c.C;
    }

    @Override // defpackage.lr2
    public final void u(lr2.b bVar) {
        this.c.k.c(bVar);
    }

    @Override // defpackage.lr2
    public final TasksView v() {
        g73 g73Var = this.y;
        return g73Var != null ? g73Var.b : this.N;
    }

    @Override // defpackage.lr2
    public final void w() {
        FeedSwipeRefreshLayout feedSwipeRefreshLayout = this.L;
        if (feedSwipeRefreshLayout != null) {
            feedSwipeRefreshLayout.setVisibility(0);
        }
    }

    @Override // defpackage.lr2
    public final boolean x() {
        int i;
        yr2 yr2Var = this.c;
        int i2 = yr2Var.C;
        if (i2 == 1 || i2 == 6 || i2 == 5) {
            return true;
        }
        return i2 == 7 && ((i = yr2Var.D) == 1 || i == 0);
    }

    @Override // defpackage.lr2
    public final void y(int i, int i2) {
        this.c.q0(i, i2);
    }

    @Override // defpackage.lr2
    public final void z() {
        this.c.p0(4);
    }
}
