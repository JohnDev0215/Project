package defpackage;

import J.N;
import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.bi1;
import defpackage.ck;
import defpackage.dt2;
import defpackage.ef2;
import defpackage.fh1;
import defpackage.jf1;
import defpackage.l61;
import defpackage.so3;
import defpackage.uo3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.findinpage.FindToolbar;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.a0;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.u;
import org.chromium.chrome.browser.tasks.tab_management.z;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.chrome.browser.toolbar.top.CustomTabSwitcherModeTopToolbar;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.toolbar.top.h;
import org.chromium.chrome.browser.toolbar.top.m;
import org.chromium.components.browser_ui.accessibility.R$dimen;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.browser_ui.bottomsheet.h;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: RootUiCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public class nc2 implements v80, sz0, hk1, jf1.a, y6 {
    public ui A;
    public final boolean A0;
    public org.chromium.components.browser_ui.bottomsheet.f B;
    public final nw2<EphemeralTabCoordinator> B0;
    public wp2 C;
    public final zb C0;
    public ef2 D;
    public cv1 D0;
    public ea0 E;
    public ic2 E0;
    public List<ck> F;
    public a00 G;
    public sw0 H;
    public final rb3 I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final aq1<Boolean> f47J;
    public final aq1<zf1> K;
    public final zp1<Profile> L;
    public final zp1<BookmarkModel> M;
    public final zp1<ny2> N;
    public final rs1<c7> O;
    public kc2 P;
    public final ll Q;
    public final fj R;
    public lc2 S;
    public zp1<y13> T;
    public final qs1<lr2> U;
    public final qs1<n1> V;
    public org.chromium.components.messages.a W;
    public sg1 X;
    public c Y;
    public jq Z;
    public final qs1<dc3> a0;
    public final rs1<Boolean> b0;
    public final nw2<Tab> c0;
    public hc2 d0;
    public final m23 e;
    public zc1 e0;
    public final i31 f;
    public pf2 f0;
    public AppCompatActivity g;
    public final q2 g0;
    public d7 h;
    public final zp1<h61> h0;
    public final jf1 i;
    public final aq1<z51> i0;
    public final g3 j;
    public final zp1<bi1> j0;
    public final z2 k;
    public final y6 k0;
    public final zp1<ck2> l;
    public final BooleanSupplier l0;
    public xm0 m;
    public final BooleanSupplier m0;
    public jc2 n;
    public final nw2<gz2> n0;
    public nt1 o;
    public final rq0 o0;
    public d p;
    public final nw2<CompositorViewHolder> p0;
    public final qs1<l61> q;
    public final dt2 q0;
    public l61 r;
    public final nw2<wp2> r0;
    public g s;
    public final int s0;
    public iy0 t;
    public final nw2<Boolean> t0;
    public final rs1<hy0> u;
    public final nw2<Boolean> u0;
    public xd3 v;
    public final e7 v0;
    public final Callback<Boolean> w;
    public final nw2<TabContentManager> w0;
    public org.chromium.chrome.browser.toolbar.g x;
    public final org.chromium.ui.base.c x0;
    public e63 y;
    public final qs1<c33> y0;
    public a z;
    public final f2 z0;

    /* JADX INFO: compiled from: RootUiCoordinator.java */
    public class a implements bi1.a {
        public a() {
        }

        @Override // bi1.a
        public final void e() {
            nc2.this.h.c.a();
        }
    }

    /* JADX INFO: compiled from: RootUiCoordinator.java */
    public class b implements View.OnLayoutChangeListener {
        public final /* synthetic */ ViewGroup e;

        public b(ViewGroup viewGroup) {
            this.e = viewGroup;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ViewGroup viewGroup = this.e;
            viewGroup.setVisibility(8);
            viewGroup.removeOnLayoutChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: RootUiCoordinator.java */
    public class d {
        public d(nc2 nc2Var) {
        }
    }

    /* JADX INFO: compiled from: RootUiCoordinator.java */
    public class e implements uo3.a {
        public e() {
        }
    }

    /* JADX INFO: compiled from: RootUiCoordinator.java */
    public class g implements l61.a {
        public g() {
        }

        @Override // l61.a
        public final void f(int i) {
            if (i != 2) {
                nc2.b(nc2.this);
            }
        }

        @Override // l61.a
        public final void g(int i, boolean z) {
            if (i == 2) {
                nc2 nc2Var = nc2.this;
                xm0 xm0Var = nc2Var.m;
                if (xm0Var != null) {
                    xm0Var.a(true);
                }
                nc2.b(nc2Var);
            }
        }

        @Override // l61.a
        public final void h(int i) {
            if (i == 2) {
                nc2.b(nc2.this);
            }
        }

        @Override // l61.a
        public final void i(int i, boolean z, boolean z2) {
            if (i == 2) {
                nc2.b(nc2.this);
            }
        }
    }

    public nc2(AppCompatActivity appCompatActivity, wb wbVar, gk2 gk2Var, z2 z2Var, f23 f23Var, aq1 aq1Var, aq1 aq1Var2, g23 g23Var, rs1 rs1Var, rs1 rs1Var2, rs1 rs1Var3, rs1 rs1Var4, nw2 nw2Var, fj fjVar, g3 g3Var, i31 i31Var, r2 r2Var, aq1 aq1Var3, jf1 jf1Var, nw2 nw2Var2, aq1 aq1Var4, y6 y6Var, ur urVar, BooleanSupplier booleanSupplier, hz2 hz2Var, qq0 qq0Var, aq1 aq1Var5, aq1 aq1Var6, nw2 nw2Var3, int i, nw2 nw2Var4, nw2 nw2Var5, e7 e7Var, dt2.a aVar, org.chromium.ui.base.d dVar, rs1 rs1Var5, nw2 nw2Var6, boolean z, zb zbVar) {
        m23 m23Var = new m23();
        this.e = m23Var;
        this.u = new rs1<>();
        aq1<Boolean> aq1Var7 = new aq1<>();
        this.f47J = aq1Var7;
        this.K = new aq1<>();
        this.b0 = new rs1<>();
        rs1 rs1Var6 = new rs1();
        this.f = i31Var;
        ll llVar = new ll();
        this.Q = llVar;
        this.g = appCompatActivity;
        this.j = g3Var;
        m23Var.s(g3Var.r);
        this.w = wbVar;
        this.R = fjVar;
        this.j0 = aq1Var4;
        this.g0 = r2Var;
        r2Var.f(this);
        this.k0 = y6Var;
        this.l0 = urVar;
        this.m0 = booleanSupplier;
        this.n0 = hz2Var;
        this.o0 = qq0Var;
        this.p0 = aq1Var5;
        this.w0 = aq1Var6;
        this.r0 = nw2Var3;
        this.s0 = i;
        this.t0 = nw2Var4;
        this.u0 = nw2Var5;
        this.v0 = e7Var;
        this.x0 = dVar;
        this.y0 = rs1Var5;
        this.A0 = z;
        this.C0 = zbVar;
        this.i = jf1Var;
        ((ChromeActivity) jf1Var).R0.add(this);
        this.k = z2Var;
        this.z0 = new f2(this.g, rs1Var6, aq1Var4);
        aq1<z51> aq1Var8 = new aq1<>();
        this.i0 = aq1Var8;
        se3 se3Var = new se3(3, this);
        this.h0 = aq1Var3;
        aq1Var3.p(se3Var);
        this.l = gk2Var;
        m23Var.r(new l23());
        new C0192j0(r2Var, z2Var, (l23) m23Var.f);
        new kb(appCompatActivity, r2Var, z2Var);
        this.L = f23Var;
        this.M = aq1Var;
        this.N = aq1Var2;
        this.O = new rs1<>();
        this.I = new rb3();
        this.T = g23Var;
        aq1Var7.r(Boolean.FALSE);
        this.q = rs1Var4;
        rs1Var4.h(llVar.b(new gc2(this, 1)));
        this.U = rs1Var;
        this.V = rs1Var2;
        this.a0 = rs1Var3;
        this.c0 = nw2Var;
        this.v = new xd3(this.g, z2Var, nw2Var2, DeviceFormFactor.a(appCompatActivity), s(), r());
        this.q0 = new dt2(this.g.getWindow(), DeviceFormFactor.a(this.g), this.g, aq1Var8, r2Var, z2Var);
        this.B0 = nw2Var6;
        this.D0 = new cv1(new mc2(this));
    }

    public static void b(nc2 nc2Var) {
        d7 d7Var = nc2Var.h;
        if (d7Var != null) {
            d7Var.c.a();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x028d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void G0() {
        /*
            Method dump skipped, instruction units count: 818
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nc2.G0():void");
    }

    public void Y0() {
        int i = 2;
        if (this.l0.getAsBoolean()) {
            AppCompatActivity appCompatActivity = this.g;
            d7 d7Var = new d7(appCompatActivity, this.g0, this.v0, appCompatActivity.getWindow().getDecorView(), this.g.getWindow().getDecorView().findViewById(R$id.menu_anchor_stub), new fc2(this, i));
            this.h = d7Var;
            x6.u = new ps(19);
            ArrayList arrayList = d7Var.c.g;
            if (!arrayList.contains(this)) {
                arrayList.add(this);
            }
            ArrayList arrayList2 = this.h.c.g;
            y6 y6Var = this.k0;
            if (!arrayList2.contains(y6Var)) {
                arrayList2.add(y6Var);
            }
            this.O.a(this.h);
            ic2 ic2Var = new ic2(this);
            this.E0 = ic2Var;
            this.h.c.h.add(ic2Var);
        }
        int i2 = this.s0;
        jf1 jf1Var = this.i;
        AppCompatActivity appCompatActivity2 = this.g;
        Objects.requireNonNull(appCompatActivity2);
        ea0 ea0Var = new ea0(i2, jf1Var, new ve3(1, appCompatActivity2), this.T.get(), this.m);
        this.E = ea0Var;
        this.g0.f(ea0Var);
        org.chromium.components.browser_ui.bottomsheet.f fVar = this.B;
        if (fVar != null) {
            kc2 kc2Var = new kc2(this);
            this.P = kc2Var;
            fVar.g(kc2Var);
        }
        this.r0.get().n.p(new ec2(this, i));
        lc2 lc2Var = new lc2(this);
        this.S = lc2Var;
        this.R.o(lc2Var);
        if (this.h != null) {
            zp1<bi1> zp1Var = this.j0;
            if (zp1Var.f()) {
                this.z = new a();
                bi1 bi1Var = zp1Var.get();
                bi1Var.h.a(this.z);
            }
        }
        new to(this.k, new ec2(this, 0), this.l);
        this.e0 = new zc1(this.j, this.g.findViewById(R$id.capture_overlay));
        ViewGroup viewGroup = (ViewGroup) this.g.findViewById(R$id.sheet_container);
        if (viewGroup.isLaidOut()) {
            viewGroup.setVisibility(8);
        } else {
            viewGroup.addOnLayoutChangeListener(new b(viewGroup));
        }
    }

    @Override // jf1.a
    public final boolean a(int i, boolean z) {
        d7 d7Var;
        if (i == R$id.show_menu && (d7Var = this.h) != null) {
            i7 i7Var = d7Var.c;
            if (i7Var != null && i7Var.d()) {
                Context context = d7Var.a;
                Intent intent = new Intent(context, (Class<?>) SettingsActivity.class);
                intent.addFlags(268435456);
                intent.addFlags(67108864);
                context.startActivity(intent);
            }
            return true;
        }
        int i2 = R$id.find_in_page_id;
        z2 z2Var = this.k;
        if (i == i2) {
            xm0 xm0Var = this.m;
            if (xm0Var == null) {
                return false;
            }
            if (xm0Var.a == null) {
                FindToolbar findToolbar = (FindToolbar) xm0Var.b.inflate();
                xm0Var.a = findToolbar;
                findToolbar.setTabModelSelector(xm0Var.c);
                xm0Var.a.setWindowAndroid(xm0Var.d);
                xm0Var.a.setActionModeCallbackForTextEdit(xm0Var.e);
                xm0Var.a.setObserver(new wm0(xm0Var));
            }
            zb zbVar = xm0Var.g;
            if (zbVar != null && zb.b()) {
                if (zbVar.f[13] != null) {
                    zbVar.d(13);
                }
                zbVar.a(xm0Var.a, 13);
            }
            xm0Var.a.e();
            Tab tab = (Tab) z2Var.f;
            if (z) {
                n3.X0("MobileMenuFindInPage");
                N.M$ejnyHh(tab.c(), "MobileMenu.FindInPage", "HasOccurred");
            } else {
                n3.X0("MobileShortcutFindInPage");
            }
            return true;
        }
        if (i == R$id.share_menu_id || i == R$id.direct_share_menu_id) {
            boolean z2 = i == R$id.direct_share_menu_id;
            this.T.get().r();
            ck2 ck2Var = this.l.get();
            Tab tab2 = (Tab) z2Var.f;
            if (ck2Var != null && tab2 != null) {
                if (z2) {
                    n3.X0("MobileMenuDirectShare");
                    N.M$ejnyHh(tab2.c(), "MobileMenu.DirectShare", "HasOccurred");
                } else {
                    n3.X0("MobileMenuShare");
                    N.M$ejnyHh(tab2.c(), "MobileMenu.Share", "HasOccurred");
                }
                ck2Var.b(0, tab2, z2);
            }
            return true;
        }
        if (i == R$id.paint_preview_show_id) {
            Tab tab3 = (Tab) z2Var.f;
            if (tab3 != null) {
                l80 l80Var = new l80(tab3);
                N.MJ3oAy5s();
                a63 a63Var = l80Var.b;
                a63Var.h.b(new ko0(25, l80Var), a63Var.e);
            }
            return true;
        }
        if (i == R$id.get_image_descriptions_id) {
            vw0 vw0VarA = vw0.a();
            AppCompatActivity appCompatActivity = this.g;
            bi1 bi1Var = this.j0.get();
            WebContents webContentsC = ((Tab) z2Var.f).c();
            vw0VarA.getClass();
            Profile profileD = Profile.d();
            boolean zA = oj2.k(profileD).a("settings.a11y.enable_accessibility_image_labels_android");
            uw0 uw0Var = vw0VarA.a;
            if (zA) {
                if (!oj2.k(profileD).a("settings.a11y.enable_accessibility_image_labels_only_on_wifi") || h90.b(appCompatActivity) == 2) {
                    uw0Var.a.getClass();
                    oj2.k(profileD).e("settings.a11y.enable_accessibility_image_labels_android", false);
                    Toast.c(appCompatActivity, R$string.image_descriptions_toast_off, 1).e();
                } else {
                    uw0Var.a(webContentsC, false);
                    Toast.c(appCompatActivity, R$string.image_descriptions_toast_just_once, 1).e();
                }
            } else if (SharedPreferencesManager.getInstance().readBoolean("Chrome.ImageDescriptions.DontAskAgain", false)) {
                uw0Var.a(webContentsC, true);
                Toast.c(appCompatActivity, R$string.image_descriptions_toast_just_once, 1).e();
            } else {
                bi1Var.j(new zw0(appCompatActivity, bi1Var, uw0Var, SharedPreferencesManager.getInstance().d(0, "Chrome.ImageDescriptions.JustOnceCount") >= 3, webContentsC).g, 1, false);
            }
            return true;
        }
        if (i == R$id.page_zoom_id) {
            Tab tab4 = (Tab) z2Var.f;
            oj2.t(Profile.b(tab4.c())).notifyEvent("page_zoom_opened");
            cv1 cv1Var = this.D0;
            WebContents webContentsC2 = tab4.c();
            cv1Var.getClass();
            int i3 = hv1.a;
            oj2.O("Accessibility.Android.PageZoom.AppMenuSliderOpened", true);
            View view = cv1Var.g;
            PropertyModel propertyModel = cv1Var.b;
            dv1 dv1Var = cv1Var.a;
            if (view == null) {
                View viewInflate = ((ViewStub) ((mc2) dv1Var).a.g.findViewById(R$id.page_zoom_container)).inflate();
                cv1Var.g = viewInflate;
                j.b(propertyModel, viewInflate, new ee3(10));
                View view2 = cv1Var.g;
                view2.startAnimation(AnimationUtils.makeInChildBottomAnimation(view2.getContext()));
            } else if (view.getVisibility() != 0) {
                cv1Var.g.setVisibility(0);
                View view3 = cv1Var.g;
                view3.startAnimation(AnimationUtils.makeInChildBottomAnimation(view3.getContext()));
            }
            if (cv1Var.h == null) {
                ((mc2) dv1Var).getClass();
                cv1Var.h = Profile.d();
            }
            PropertyModel.r<Double> rVar = gv1.i;
            Profile profile = cv1Var.h;
            int i4 = iv1.a;
            propertyModel.p(rVar, Double.valueOf(N.MNitnPWj(profile)));
            View view4 = cv1Var.g;
            if (view4 != null) {
                int i5 = view4.getContext().getResources().getDisplayMetrics().widthPixels;
                int dimensionPixelSize = cv1Var.g.getContext().getResources().getDimensionPixelSize(R$dimen.page_zoom_view_tablet_mode_min_width);
                int dimensionPixelSize2 = cv1Var.g.getContext().getResources().getDimensionPixelSize(R$dimen.page_zoom_view_padding);
                if (i5 > dimensionPixelSize) {
                    int dimensionPixelSize3 = (i5 - cv1Var.g.getContext().getResources().getDimensionPixelSize(R$dimen.page_zoom_view_max_width)) / 2;
                    cv1Var.g.setPadding(dimensionPixelSize3, dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize2);
                } else {
                    cv1Var.g.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
                }
            }
            cv1Var.g.setOnHoverListener(new View.OnHoverListener() { // from class: av1
                @Override // android.view.View.OnHoverListener
                public final boolean onHover(View view5, MotionEvent motionEvent) {
                    return true;
                }
            });
            cv1Var.b(cv1Var.e);
            fv1 fv1Var = cv1Var.c;
            fv1Var.b = webContentsC2;
            double dA = fv1.a(webContentsC2);
            PropertyModel.p pVar = gv1.h;
            int iC = iv1.c(dA);
            PropertyModel propertyModel2 = fv1Var.a;
            propertyModel2.n(pVar, iC);
            fv1Var.d = ((Double) propertyModel2.j(rVar)).doubleValue();
            fv1Var.c(dA);
            fv1Var.c = 0.0d;
            cv1Var.d = new bv1(cv1Var, webContentsC2);
            View view5 = cv1Var.g;
            d92 d92Var = cv1Var.f;
            view5.removeCallbacks(d92Var);
            cv1Var.g.postDelayed(d92Var, 5000L);
        }
        return false;
    }

    public ef2 c() {
        ViewGroup viewGroup = (ViewGroup) this.g.findViewById(R$id.coordinator);
        return new ef2(this.g, new f(), viewGroup, viewGroup.getContext().getColor(R$color.omnibox_focused_fading_background_color));
    }

    public Rect d() {
        Rect rect = new Rect();
        this.g.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect;
    }

    public ly0 e() {
        return null;
    }

    @Override // defpackage.sz0
    public final void e0() throws Throwable {
        this.D = c();
        if (this.m0.getAsBoolean()) {
            int i = R$id.find_toolbar_stub;
            if (DeviceFormFactor.a(this.g)) {
                i = R$id.find_toolbar_tablet_stub;
            }
            xm0 xm0Var = new xm0((ViewStub) this.g.findViewById(i), this.T.get(), this.j, this.I, this.C0);
            this.m = xm0Var;
            jc2 jc2Var = new jc2(this);
            this.n = jc2Var;
            xm0Var.f.a(jc2Var);
        }
        i();
    }

    public final double g() {
        ya0 ya0VarA = ya0.a(this.g);
        Point point = ya0VarA.c;
        double d2 = point.x / ya0VarA.e;
        return Math.sqrt(Math.pow(point.y / ya0VarA.f, 2.0d) + Math.pow(d2, 2.0d));
    }

    @Override // defpackage.y6
    public final boolean g1() {
        xm0 xm0Var = this.m;
        if (xm0Var != null) {
            FindToolbar findToolbar = xm0Var.a;
            if ((findToolbar != null && findToolbar.getVisibility() == 0) && !DeviceFormFactor.a(this.g)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v30, types: [hc2, java.lang.Object] */
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
    public void i() throws Throwable {
        TraceEvent traceEvent;
        zp1<bi1> zp1Var = this.j0;
        TraceEvent traceEventH0 = TraceEvent.H0("RootUiCoordinator.initializeToolbar", null);
        try {
            ToolbarControlContainer toolbarControlContainer = (ToolbarControlContainer) this.g.findViewById(R$id.control_container);
            int i = 0;
            gc2 gc2Var = new gc2(this, i);
            fc2 fc2Var = new fc2(this, 1);
            this.H = new sw0(this.g, this.g0, this.L);
            j42 j42Var = new j42(this.g, this.k, zp1Var.get(), this.B, AppCompatResources.a(this.g, R$drawable.price_tracking_disabled), this.N);
            j92 j92Var = new j92(this.g, this.k, zp1Var.get(), AppCompatResources.a(this.g, R$drawable.ic_mobile_friendly));
            AppCompatActivity appCompatActivity = this.g;
            zj2 zj2Var = new zj2(appCompatActivity, AppCompatResources.a(appCompatActivity, R$drawable.ic_toolbar_share_offset_24dp), this.k, this.l, fc2Var, new n3(i), zp1Var.get(), new f52(18, this));
            e eVar = new e();
            AppCompatActivity appCompatActivity2 = this.g;
            final uo3 uo3Var = new uo3(appCompatActivity2, AppCompatResources.a(appCompatActivity2, R$drawable.btn_mic), this.k, fc2Var, zp1Var.get(), eVar);
            AppCompatActivity appCompatActivity3 = this.g;
            dt1 dt1Var = new dt1(appCompatActivity3, AppCompatResources.a(appCompatActivity3, R$drawable.new_tab_icon), this.g0, this.n0, this.k, fc2Var);
            m3 m3Var = new m3(this.g, new n3(), this.g0, new j3(), this.j, SharedPreferencesManager.getInstance());
            m3Var.e(2, dt1Var);
            m3Var.e(3, zj2Var);
            m3Var.e(4, uo3Var);
            m3Var.e(6, j42Var);
            m3Var.e(7, j92Var);
            this.G = new a00(this.L, this.k, m3Var, new fp(this, 3), this.M);
            this.F = Arrays.asList(this.H, m3Var);
            traceEvent = traceEventH0;
            try {
                this.x = new org.chromium.chrome.browser.toolbar.g(this.g, this.R, this.o0, toolbarControlContainer, this.p0.get(), gc2Var, this.v, (l23) this.e.f, this.l, this.H, this.F, this.k, this.D, this.I, this.m, this.L, this.M, this.y, this.q, this.O, this instanceof g63, this.T, this.U, this.f47J, this.b0, this.j, this.t0, this.j0, this.q0, this.v0, this.g0, this.c0, this.B, this.u0, this.w0.get(), this.n0.get(), this.r0.get(), this.f, this.K, this.y0, this.z0, this.B0, this.A0, this.C0);
                if (!this.l0.getAsBoolean()) {
                    m mVar = this.x.k;
                    af1 af1Var = mVar.e;
                    MenuButton menuButton = af1Var.e;
                    if (menuButton != null) {
                        uh3.l(menuButton);
                        af1Var.a();
                    }
                    mVar.a.p();
                }
                so3 so3VarD = this.x.E.d();
                if (so3VarD != null) {
                    ?? r2 = new so3.b() { // from class: hc2
                        @Override // so3.b
                        public final void E() {
                            uo3 uo3Var2 = uo3Var;
                            boolean zG = uo3Var2.g(uo3Var2.f.get());
                            uo3Var2.e.a = zG;
                            uo3Var2.e(zG);
                        }
                    };
                    this.d0 = r2;
                    so3VarD.f.a(r2);
                }
                if (traceEvent != null) {
                    traceEvent.close();
                }
            } catch (Throwable th) {
                th = th;
                if (traceEvent != null) {
                    try {
                        traceEvent.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            traceEvent = traceEventH0;
        }
    }

    public void n(h61 h61Var) {
        nt1 nt1Var = this.o;
        if (nt1Var != null) {
            nt1Var.b.c(this.p);
        }
        nt1 nt1Var2 = h61Var.y;
        this.o = nt1Var2;
        if (this.p == null) {
            this.p = new d(this);
        }
        nt1Var2.b.a(this.p);
    }

    public void o(l61 l61Var) {
        this.r = l61Var;
        g gVar = new g();
        this.s = gVar;
        l61Var.j(gVar);
    }

    @Override // defpackage.v80
    public void onDestroy() {
        SparseArray<Tab> sparseArray;
        Animator animator;
        org.chromium.chrome.browser.toolbar.top.f fVar;
        org.chromium.chrome.browser.toolbar.top.e eVar;
        h.a aVar;
        this.Q.a();
        ((ChromeActivity) this.i).R0.remove(this);
        this.e.destroy();
        this.g0.e(this);
        org.chromium.components.messages.a aVar2 = this.W;
        if (aVar2 != null) {
            fh1 fh1Var = aVar2.e;
            HashMap map = fh1Var.c;
            Iterator it = map.values().iterator();
            while (it.hasNext()) {
                fh1Var.a(7, (fh1.a) it.next());
            }
            map.clear();
            xg1.a.b(this.W);
            this.W = null;
        }
        jq jqVar = this.Z;
        if (jqVar != null) {
            jqVar.m.e(jqVar.s);
            jqVar.m = null;
            jqVar.n.a();
            jqVar.g.s(jqVar.i);
            jqVar.e(null);
            bi1 bi1Var = jqVar.l;
            if (bi1Var != null) {
                bi1Var.h.c(jqVar.r);
            }
            jqVar.l = null;
            jqVar.k = null;
            jqVar.e = null;
            jqVar.f = null;
            int i = jqVar.h;
            if (i != -1) {
                jqVar.g.f.u(i);
            }
            jqVar.h = -1;
            jqVar.g = null;
            jqVar.o = -1;
            jqVar.p.removeCallbacksAndMessages(null);
            jqVar.p = null;
            this.Z = null;
        }
        sg1 sg1Var = this.X;
        if (sg1Var != null) {
            c cVar = this.Y;
            if (cVar != null) {
                sg1Var.g.c(cVar);
            }
            sg1 sg1Var2 = this.X;
            sg1Var2.f.s(sg1Var2);
            sg1Var2.e = null;
            sg1Var2.f = null;
            sg1Var2.g.clear();
            this.X = null;
        }
        nt1 nt1Var = this.o;
        if (nt1Var != null) {
            nt1Var.b.c(this.p);
        }
        l61 l61Var = this.r;
        if (l61Var != null) {
            l61Var.f(this.s);
            this.r = null;
        }
        org.chromium.chrome.browser.toolbar.g gVar = this.x;
        if (gVar != null) {
            if (this.d0 != null && gVar.E.d() != null) {
                this.x.E.d().f.c(this.d0);
            }
            org.chromium.chrome.browser.toolbar.g gVar2 = this.x;
            gVar2.M0 = true;
            sr1 sr1VarI = gVar2.E.i();
            dt2 dt2Var = gVar2.g0;
            if (sr1VarI != null) {
                sr1VarI.H(gVar2);
                sr1VarI.H(dt2Var);
                sr1VarI.H(gVar2.Z);
            }
            boolean z = gVar2.v0;
            vc3 vc3Var = gVar2.I;
            if (z) {
                gVar2.F.f.c(vc3Var);
            }
            if (gVar2.w != null) {
                gVar2.w = null;
            }
            y13 y13Var = gVar2.u;
            if (y13Var != null) {
                y13Var.l(gVar2.v);
            }
            zp1<BookmarkModel> zp1Var = gVar2.A;
            if (zp1Var != null) {
                BookmarkModel bookmarkModel = zp1Var.get();
                if (bookmarkModel != null) {
                    bookmarkModel.r(gVar2.H);
                }
                gVar2.A.d(gVar2.B);
                gVar2.A = null;
            }
            oc3 oc3Var = gVar2.D;
            if (oc3Var != null) {
                gVar2.C.i(oc3Var);
                gVar2.D = null;
            }
            l61 l61Var2 = gVar2.M;
            if (l61Var2 != null) {
                l61Var2.f(gVar2.N);
                gVar2.M = null;
            }
            if (gVar2.O != null) {
                gVar2.O = null;
            }
            h61 h61Var = gVar2.G;
            if (h61Var != null) {
                h61Var.y.b.c(gVar2.I0);
                gVar2.G = null;
            }
            qv0.d().b.c(gVar2.f0);
            ai aiVar = gVar2.t.f;
            if (aiVar != null) {
                ai aiVar2 = aiVar;
                zh zhVar = aiVar2.f;
                if (zhVar != null) {
                    u uVar = (u) zhVar;
                    if (uVar.e != null) {
                        uVar.y.onDestroy();
                        org.chromium.chrome.browser.tasks.tab_management.j jVar = uVar.w;
                        if (jVar != null) {
                            jVar.destroy();
                        }
                        uVar.v.c();
                        a0 a0Var = uVar.z;
                        y13 y13Var2 = a0Var.i;
                        if (y13Var2 != null) {
                            y13Var2.u().e(a0Var.g);
                            y13Var2.l(a0Var.o);
                            z zVar = a0Var.t;
                            if (zVar != null) {
                                ((g03) y13Var2.u().d(false)).W(zVar);
                                ((g03) y13Var2.u().d(true)).W(zVar);
                            }
                        }
                        j23 j23Var = a0Var.u;
                        if (j23Var != null) {
                            j23Var.destroy();
                        }
                        l61 l61Var3 = a0Var.s;
                        if (l61Var3 != null) {
                            l61Var3.f(a0Var.r);
                        }
                        ll llVar = a0Var.q;
                        if (llVar != null) {
                            llVar.a();
                            a0Var.q = null;
                        }
                        dr drVar = a0Var.v;
                        if (drVar != null) {
                            a0Var.p.d(drVar);
                        }
                        a0Var.l.a.c(a0Var.n);
                        q2 q2Var = uVar.n;
                        if (q2Var != null) {
                            q2Var.e(uVar);
                        }
                    }
                }
                bi biVar = aiVar2.e;
                biVar.i.a();
                biVar.g.s(biVar);
                biVar.k.m().h(biVar);
                l61 l61Var4 = biVar.p;
                if (l61Var4 != null) {
                    l61Var4.f(biVar);
                    biVar.p = null;
                }
                biVar.h.e(biVar);
                gVar2.t = null;
            }
            v91 v91Var = gVar2.E;
            if (v91Var != null) {
                v91Var.destroy();
                gVar2.E = null;
            }
            m mVar = gVar2.k;
            mVar.a.g.c(dt2Var);
            pd3 pd3Var = mVar.j;
            if (pd3Var != null) {
                nw nwVar = pd3Var.f;
                nwVar.b.d(nwVar.e);
                nwVar.d.d(nwVar.f);
                td3 td3Var = pd3Var.g;
                td3Var.e.a();
                td3Var.b.f(td3Var.c);
                td3Var.f.s(td3Var.g);
                pd3Var.e.destroy();
                mVar.j = null;
            }
            mVar.a.destroy();
            h hVar = mVar.b;
            if (hVar != null) {
                CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = hVar.j;
                if (customTabSwitcherModeTopToolbar != null) {
                    bz0 bz0Var = customTabSwitcherModeTopToolbar.i;
                    if (bz0Var != null) {
                        bz0Var.a.c(customTabSwitcherModeTopToolbar);
                        customTabSwitcherModeTopToolbar.i = null;
                    }
                    ToggleTabStackButton toggleTabStackButton = customTabSwitcherModeTopToolbar.l;
                    if (toggleTabStackButton != null) {
                        org.chromium.chrome.browser.toolbar.a aVar3 = toggleTabStackButton.i;
                        if (aVar3 != null) {
                            aVar3.a.c(toggleTabStackButton);
                        }
                        customTabSwitcherModeTopToolbar.l = null;
                    }
                    IncognitoToggleTabLayout incognitoToggleTabLayout = customTabSwitcherModeTopToolbar.k;
                    if (incognitoToggleTabLayout != null) {
                        y13 y13Var3 = incognitoToggleTabLayout.j0;
                        if (y13Var3 != null) {
                            y13Var3.l(incognitoToggleTabLayout.l0);
                        }
                        org.chromium.chrome.browser.toolbar.a aVar4 = incognitoToggleTabLayout.k0;
                        if (aVar4 != null) {
                            aVar4.a.c(incognitoToggleTabLayout);
                        }
                        customTabSwitcherModeTopToolbar.k = null;
                    }
                    if (customTabSwitcherModeTopToolbar.n != null) {
                        customTabSwitcherModeTopToolbar.n = null;
                    }
                    if (customTabSwitcherModeTopToolbar.t != null) {
                        customTabSwitcherModeTopToolbar.t = null;
                    }
                    org.chromium.chrome.browser.toolbar.a aVar5 = customTabSwitcherModeTopToolbar.g;
                    if (aVar5 != null) {
                        aVar5.a.c(customTabSwitcherModeTopToolbar);
                        customTabSwitcherModeTopToolbar.g = null;
                    }
                    hVar.j = null;
                }
                y13 y13Var4 = hVar.f;
                if (y13Var4 != null && (aVar = hVar.n) != null) {
                    y13Var4.w(aVar);
                }
                af1 af1Var = hVar.h;
                if (af1Var != null) {
                    af1Var.a();
                    hVar.h = null;
                }
            } else {
                org.chromium.chrome.browser.toolbar.top.d dVar = mVar.c;
                if (dVar != null) {
                    org.chromium.chrome.browser.toolbar.top.g gVar3 = dVar.a;
                    y13 y13Var5 = gVar3.v;
                    if (y13Var5 != null && (eVar = gVar3.n) != null) {
                        y13Var5.l(eVar);
                    }
                    y13 y13Var6 = gVar3.v;
                    if (y13Var6 != null && (fVar = gVar3.o) != null) {
                        y13Var6.w(fVar);
                    }
                    org.chromium.chrome.browser.logo.a aVar6 = gVar3.C;
                    if (aVar6 != null) {
                        aVar6.a();
                        gVar3.C = null;
                    }
                    ll llVar2 = gVar3.z;
                    if (llVar2 != null) {
                        llVar2.a();
                        gVar3.z = null;
                    }
                    if (gVar3.F != null) {
                        gVar3.F = null;
                    }
                    gVar3.s.b(gVar3);
                    org.chromium.chrome.browser.toolbar.c cVar2 = dVar.h;
                    if (cVar2 != null) {
                        ThemeColorProvider themeColorProvider = cVar2.b;
                        if (themeColorProvider != null) {
                            themeColorProvider.i.c(cVar2.c);
                            cVar2.b = null;
                        }
                        org.chromium.chrome.browser.toolbar.a aVar7 = cVar2.d;
                        if (aVar7 != null) {
                            aVar7.a.c(cVar2.e);
                            cVar2.d = null;
                        }
                    }
                    af1 af1Var2 = dVar.n;
                    if (af1Var2 != null) {
                        af1Var2.a();
                        dVar.n = null;
                    }
                    ll llVar3 = dVar.o;
                    if (llVar3 != null) {
                        llVar3.a();
                        dVar.o = null;
                    }
                    dVar.h = null;
                    dVar.i = null;
                    dVar.j = null;
                    dVar.k = null;
                    dVar.l = null;
                    dVar.m = null;
                }
            }
            zs1 zs1Var = mVar.d;
            if (zs1Var != null) {
                HashMap map2 = zs1Var.b;
                for (Map.Entry entry : map2.entrySet()) {
                    ((ck) entry.getKey()).b((ck.a) entry.getValue());
                }
                map2.clear();
                mVar.d = null;
            }
            if (mVar.f != null) {
                mVar.f = null;
            }
            if (mVar.g != null) {
                mVar.g = null;
            }
            if (mVar.h != null) {
                mVar.h = null;
            }
            bz0 bz0Var2 = gVar2.e;
            y13 y13Var7 = bz0Var2.c;
            if (y13Var7 != null) {
                y13Var7.l(bz0Var2.b);
                bz0Var2.c = null;
            }
            bz0Var2.a.clear();
            org.chromium.chrome.browser.toolbar.a aVar8 = gVar2.f;
            if (aVar8.d != null) {
                aVar8.b.u().e(aVar8.d);
            }
            y13 y13Var8 = aVar8.b;
            if (y13Var8 != null) {
                y13Var8.l(aVar8.c);
                aVar8.b = null;
            }
            aVar8.a.clear();
            LocationBarModel locationBarModel = gVar2.z;
            qp qpVar = locationBarModel.g;
            if (qpVar != null) {
                qpVar.a();
                locationBarModel.g = null;
            }
            long j = locationBarModel.t;
            if (j != 0) {
                N.MltVHpYK(j, locationBarModel);
                locationBarModel.t = 0L;
            }
            gVar2.S.removeCallbacksAndMessages(null);
            gVar2.X.s(gVar2.m);
            gVar2.Y.c(gVar2.n);
            xd3 xd3Var = gVar2.g;
            if (xd3Var != null) {
                xd3Var.h.c(gVar2);
            }
            u7 u7Var = gVar2.i;
            if (u7Var != null) {
                u7Var.i.c(gVar2);
                gVar2.i.c();
                gVar2.i = null;
            }
            qc3 qc3Var = gVar2.x;
            if (qc3Var != null) {
                qc3Var.destroy();
                gVar2.x = null;
            }
            e91 e91Var = gVar2.b0;
            if (e91Var != null) {
                e91Var.a.b.a();
            }
            xm0 xm0Var = gVar2.F;
            if (xm0Var != null) {
                xm0Var.f.c(vc3Var);
                gVar2.F = null;
            }
            if (gVar2.d0 != null) {
                if (gVar2.w0 != null) {
                    hj3.a().a.c(gVar2.w0);
                    gVar2.w0 = null;
                }
                gVar2.d0.a();
                gVar2.d0 = null;
            }
            if (gVar2.e0 != null) {
                if (gVar2.x0 != null) {
                    hj3.a().a.c(gVar2.x0);
                    gVar2.x0 = null;
                }
                gVar2.e0.a();
                gVar2.e0 = null;
            }
            nv0 nv0Var = gVar2.p0;
            if (nv0Var != null) {
                nv0Var.f.a();
                gVar2.p0 = null;
            }
            ib3 ib3Var = gVar2.q0;
            if (ib3Var != null) {
                ib3Var.a.a();
                ib3Var.h.a();
                l61 l61Var5 = ib3Var.i;
                if (l61Var5 != null) {
                    l61Var5.f(ib3Var.j);
                    ib3Var.i = null;
                    ib3Var.j = null;
                }
                gVar2.q0 = null;
            }
            ll llVar4 = gVar2.P;
            if (llVar4 != null) {
                llVar4.a();
                gVar2.P = null;
            }
            lr2 lr2Var = gVar2.E0;
            if (lr2Var != null) {
                lr2Var.u(gVar2.F0);
                gVar2.E0.removeHeaderOffsetChangeListener(gVar2.G0);
                gVar2.E0 = null;
                gVar2.F0 = null;
                gVar2.G0 = null;
            }
            gVar2.m0.e(gVar2);
            gVar2.T.unregisterComponentCallbacks(gVar2.a0);
            gVar2.a0 = null;
            qo qoVarF = qo.f();
            if (qoVarF.c == null) {
                qoVarF.c = new org.chromium.base.b<>();
            }
            qoVarF.c.c(gVar2);
            ToolbarControlContainer.b bVar = (ToolbarControlContainer.b) gVar2.l.getToolbarResourceAdapter();
            tx txVar = bVar.s;
            if (txVar != null) {
                txVar.g.removeCallbacksAndMessages(null);
            }
            zp1<Boolean> zp1Var2 = bVar.u;
            if (zp1Var2 != null) {
                zp1Var2.d(bVar.p);
            }
            g.a aVar9 = gVar2.s;
            lj ljVar = aVar9.h;
            if (ljVar != null) {
                ljVar.d(aVar9);
                aVar9.h = null;
            }
            this.x = null;
        }
        a00 a00Var = this.G;
        if (a00Var != null) {
            i20 i20Var = a00Var.d;
            if (i20Var != null) {
                i20Var.a();
            }
            this.G = null;
        }
        d7 d7Var = this.h;
        if (d7Var != null) {
            d7Var.c.g.remove(this);
            this.h.c.g.remove(this.k0);
            ic2 ic2Var = this.E0;
            if (ic2Var != null) {
                this.h.c.h.remove(ic2Var);
            }
            d7 d7Var2 = this.h;
            i7 i7Var = d7Var2.c;
            if (i7Var != null) {
                i7Var.a();
                i7Var.n.e(i7Var);
            }
            o7 o7Var = (o7) d7Var2.b;
            ll llVar5 = o7Var.i;
            if (llVar5 != null) {
                llVar5.a();
                o7Var.i = null;
            }
            qs1<lr2> qs1Var = o7Var.p;
            if (qs1Var != null) {
                if (qs1Var.get() != null) {
                    o7Var.p.get().u(o7Var.q);
                }
                o7Var.p = null;
                o7Var.q = null;
            }
        }
        xd3 xd3Var2 = this.v;
        if (xd3Var2 != null) {
            xd3Var2.d();
            this.v = null;
        }
        xm0 xm0Var2 = this.m;
        if (xm0Var2 != null) {
            xm0Var2.f.c(this.n);
        }
        if (this.z != null) {
            zp1<bi1> zp1Var3 = this.j0;
            if (zp1Var3.f()) {
                zp1Var3.get().h.c(this.z);
            }
        }
        ui uiVar = this.A;
        if (uiVar != null) {
            uiVar.onDestroy();
        }
        org.chromium.components.browser_ui.bottomsheet.f fVar2 = this.B;
        if (fVar2 != null) {
            kc2 kc2Var = this.P;
            if (kc2Var != null) {
                fVar2.e(kc2Var);
            }
            org.chromium.components.browser_ui.bottomsheet.h.a.b(this.B);
            this.B.q();
        }
        List<ck> list = this.F;
        if (list != null) {
            Iterator<ck> it2 = list.iterator();
            while (it2.hasNext()) {
                it2.next().destroy();
            }
            this.F = null;
        }
        ef2 ef2Var = this.D;
        if (ef2Var != null && (animator = ef2Var.b.e) != null) {
            animator.end();
        }
        this.D = null;
        if (this.T != null) {
            this.T = null;
        }
        zc1 zc1Var = this.e0;
        if (zc1Var != null) {
            int i2 = 0;
            while (true) {
                sparseArray = zc1Var.g;
                if (i2 >= sparseArray.size()) {
                    break;
                }
                sparseArray.valueAt(i2).t(zc1Var.e);
                i2++;
            }
            sparseArray.clear();
            zc1Var.f.setVisibility(8);
            zc1Var.h = null;
            zc1Var.f = null;
            zc1.i.b(zc1Var);
            this.e0 = null;
        }
        aq1<zf1> aq1Var = this.K;
        if (aq1Var.f()) {
            zf1 zf1Var = aq1Var.f;
            zf1Var.a.a.a();
            zf1Var.g.getClass();
            dg1.b.clear();
            aq1Var.r(null);
        }
        pf2 pf2Var = this.f0;
        if (pf2Var != null) {
            zp1<Tab> zp1Var4 = pf2Var.e;
            if (zp1Var4 != null) {
                zp1Var4.d(pf2Var);
            }
            Tab tab = pf2Var.g;
            if (tab != null) {
                tab.t(pf2Var);
            }
            View view = pf2Var.h;
            if (view != null) {
                ((rf2) pf2Var.f).getClass();
                view.setScrollCaptureHint(0);
                view.setScrollCaptureCallback(null);
            }
            this.f0 = null;
        }
        iy0 iy0Var = this.t;
        if (iy0Var != null) {
            org.chromium.base.b<ApplicationStatus.e> bVar2 = ApplicationStatus.h;
            if (bVar2 != null) {
                bVar2.c(iy0Var);
            }
            iy0Var.l.e(iy0Var);
            y13 y13Var9 = iy0Var.m;
            y13Var9.v(null);
            y13Var9.w(iy0Var.g);
            y13Var9.l(iy0Var.h);
            iy0Var.n.d(iy0Var.j);
            iy0Var.k.a();
            iy0Var.o.getClass();
            iy0Var.r.b(false);
            l61 l61Var6 = iy0Var.u;
            if (l61Var6 != null) {
                l61Var6.f(iy0Var.i);
            }
            ky0 ky0Var = iy0Var.t;
            if (ky0Var != null) {
                ky0Var.c.c();
                iy0Var.t = null;
            }
        }
        cv1 cv1Var = this.D0;
        if (cv1Var != null) {
            bv1 bv1Var = cv1Var.d;
            if (bv1Var != null) {
                bv1Var.destroy();
            }
            View view2 = cv1Var.g;
            if (view2 != null) {
                view2.removeCallbacks(cv1Var.f);
            }
            this.D0 = null;
        }
        lc2 lc2Var = this.S;
        if (lc2Var != null) {
            this.R.s(lc2Var);
        }
        this.g = null;
    }

    public void p(float f2) {
        dt2 dt2Var = this.q0;
        dt2Var.p = f2;
        dt2Var.g();
    }

    public void q() {
        f.a aVar;
        int i = 1;
        ec2 ec2Var = new ec2(this, i);
        fp fpVar = new fp(this, i);
        int i2 = 2;
        bs bsVar = new bs(this, i2);
        Window window = this.g.getWindow();
        g3 g3Var = this.j;
        this.B = new org.chromium.components.browser_ui.bottomsheet.f(bsVar, ec2Var, window, (p2) g3Var.f, new fc2(this, 0));
        org.chromium.components.browser_ui.bottomsheet.d.a(new ps(18));
        org.chromium.components.browser_ui.bottomsheet.f fVar = this.B;
        aj3<h.a> aj3Var = org.chromium.components.browser_ui.bottomsheet.h.a;
        org.chromium.components.browser_ui.bottomsheet.h.a.a(g3Var.r, fVar);
        this.A = new ui(this.B, this.k, this.R, this.j0, new fp(this, i2), (l23) this.e.f, this.f47J, fpVar, this.U, this.q, zb2.e(this.g));
        if (!zb.b() || (aVar = this.B.r) == null) {
            return;
        }
        this.C0.a(aVar, 8);
    }

    public boolean r() {
        return false;
    }

    public boolean s() {
        return false;
    }

    /* JADX INFO: compiled from: RootUiCoordinator.java */
    public class c implements tg1 {
        public c() {
        }

        @Override // defpackage.tg1
        public final void b() {
            cv1 cv1Var = nc2.this.D0;
            if (cv1Var != null) {
                cv1Var.a();
            }
        }

        @Override // defpackage.tg1
        public final void a() {
        }
    }

    public void k() {
    }

    public void m() {
    }

    /* JADX INFO: compiled from: RootUiCoordinator.java */
    public class f implements ef2.a {
        public f() {
        }

        @Override // ef2.a
        public final void a(float f) {
            nc2.this.p(f);
        }

        @Override // ef2.a
        public final void b(float f) {
        }
    }
}
