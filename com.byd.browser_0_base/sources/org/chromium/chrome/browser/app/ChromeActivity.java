package org.chromium.chrome.browser.app;

import J.N;
import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.assist.AssistContent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.a23;
import defpackage.a3;
import defpackage.a62;
import defpackage.a8;
import defpackage.ab2;
import defpackage.ai3;
import defpackage.aj3;
import defpackage.ap;
import defpackage.aq1;
import defpackage.as0;
import defpackage.at;
import defpackage.b3;
import defpackage.b62;
import defpackage.bi1;
import defpackage.bi3;
import defpackage.bj;
import defpackage.bk3;
import defpackage.bp;
import defpackage.bq;
import defpackage.br1;
import defpackage.bs;
import defpackage.bs0;
import defpackage.c3;
import defpackage.c33;
import defpackage.cl3;
import defpackage.cp;
import defpackage.cr1;
import defpackage.d3;
import defpackage.d70;
import defpackage.dd0;
import defpackage.di1;
import defpackage.dp;
import defpackage.dt2;
import defpackage.du3;
import defpackage.dx2;
import defpackage.e00;
import defpackage.e23;
import defpackage.e32;
import defpackage.e7;
import defpackage.eb2;
import defpackage.ef3;
import defpackage.ei2;
import defpackage.ep;
import defpackage.er1;
import defpackage.f23;
import defpackage.fa1;
import defpackage.fb2;
import defpackage.fj;
import defpackage.fk2;
import defpackage.fp;
import defpackage.fq3;
import defpackage.fr1;
import defpackage.fu0;
import defpackage.fz2;
import defpackage.g23;
import defpackage.g3;
import defpackage.g80;
import defpackage.g9;
import defpackage.g90;
import defpackage.gg3;
import defpackage.gk2;
import defpackage.gp;
import defpackage.gz2;
import defpackage.h61;
import defpackage.h9;
import defpackage.hj;
import defpackage.hj3;
import defpackage.hp;
import defpackage.hu0;
import defpackage.hz2;
import defpackage.i61;
import defpackage.i70;
import defpackage.i9;
import defpackage.ie2;
import defpackage.ii2;
import defpackage.ik;
import defpackage.ip;
import defpackage.iq2;
import defpackage.iv;
import defpackage.j23;
import defpackage.j70;
import defpackage.je2;
import defpackage.jf1;
import defpackage.jl3;
import defpackage.k11;
import defpackage.ku2;
import defpackage.l23;
import defpackage.l91;
import defpackage.lh0;
import defpackage.lp;
import defpackage.m01;
import defpackage.m7;
import defpackage.mm0;
import defpackage.mq0;
import defpackage.mq3;
import defpackage.n3;
import defpackage.nc2;
import defpackage.nj3;
import defpackage.nq0;
import defpackage.nq3;
import defpackage.nw2;
import defpackage.ny2;
import defpackage.o7;
import defpackage.o90;
import defpackage.oj2;
import defpackage.oq3;
import defpackage.pp;
import defpackage.pq2;
import defpackage.pr1;
import defpackage.q11;
import defpackage.q52;
import defpackage.qe3;
import defpackage.qq0;
import defpackage.r2;
import defpackage.rk;
import defpackage.rq0;
import defpackage.rs1;
import defpackage.s13;
import defpackage.s50;
import defpackage.s51;
import defpackage.sk;
import defpackage.so;
import defpackage.t30;
import defpackage.t52;
import defpackage.tk;
import defpackage.tp2;
import defpackage.u52;
import defpackage.u73;
import defpackage.uh3;
import defpackage.uk;
import defpackage.um1;
import defpackage.uo;
import defpackage.uq0;
import defpackage.ur;
import defpackage.v13;
import defpackage.v42;
import defpackage.v52;
import defpackage.v73;
import defpackage.vb1;
import defpackage.vj1;
import defpackage.vq;
import defpackage.vq0;
import defpackage.w52;
import defpackage.wb;
import defpackage.wp;
import defpackage.wp2;
import defpackage.wq;
import defpackage.ws1;
import defpackage.wv2;
import defpackage.wz;
import defpackage.x10;
import defpackage.x52;
import defpackage.x7;
import defpackage.xe3;
import defpackage.xm0;
import defpackage.xo;
import defpackage.xp2;
import defpackage.xr;
import defpackage.xr1;
import defpackage.xw;
import defpackage.xz;
import defpackage.y13;
import defpackage.y51;
import defpackage.y52;
import defpackage.y6;
import defpackage.ya0;
import defpackage.yj;
import defpackage.yo;
import defpackage.yr0;
import defpackage.yy2;
import defpackage.z2;
import defpackage.z23;
import defpackage.z52;
import defpackage.z7;
import defpackage.zb;
import defpackage.zb2;
import defpackage.zc0;
import defpackage.zf1;
import defpackage.zj;
import defpackage.zo;
import defpackage.zo3;
import defpackage.zp1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.RejectedExecutionException;
import java.util.function.Consumer;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.BundleUtils;
import org.chromium.base.PowerMonitor;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.ChromeTabbedActivity2;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.dom_distiller.DomDistillerUIUtils;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.feed.FeedPlaceholderLayout;
import org.chromium.chrome.browser.findinpage.FindToolbar;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.chrome.browser.infobar.InfoBarContainerView;
import org.chromium.chrome.browser.init.AsyncInitializationActivity;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.metrics.a;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.password_manager.PasswordManagerLifecycleHelper;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.share.qrcode.QrCodeDialog;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.ui.BottomContainer;
import org.chromium.chrome.features.start_surface.StartSurfaceConfiguration;
import org.chromium.components.browser_ui.accessibility.FontSizePrefs;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.components.browser_ui.widget.InsetObserverView;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.policy.CombinedPolicyProvider;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.webapps.AddToHomescreenCoordinator;
import org.chromium.components.webapps.bottomsheet.PwaBottomSheetController;
import org.chromium.components.webapps.bottomsheet.PwaBottomSheetControllerProvider;
import org.chromium.content.browser.ScreenOrientationProviderImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.AsyncViewStub;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.resources.ResourceManager;
import org.chromium.ui.widget.Toast;
import org.chromium.url.GURL;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class ChromeActivity<C extends ip> extends AsyncInitializationActivity implements gz2, wp2.c, ie2, dt2.a, e7, y6, jf1, CompositorViewHolder.e, s13 {
    public static final k11 Z0 = new k11(5, "FoldableJankFix", "content_visibility_delay");
    public final m01 A0;
    public wp2 B0;
    public long C0;
    public long D0;
    public boolean E0;
    public org.chromium.chrome.browser.metrics.a F0;
    public final z2 G0;
    public boolean H0;
    public Bundle I0;
    public Configuration J0;
    public final rs1<c33> K0;
    public boolean L0;
    public c M0;
    public nc2 N0;
    public BottomContainer O0;
    public s51 P0;
    public yr0 Q0;
    public final ArrayList R0;
    public final zb S0;
    public v73 T0;
    public ei2 U0;
    public c3 V0;
    public wv2 W0;
    public boolean X0;
    public zo Y0;
    public C c0;
    public final gk2 d0 = new gk2();
    public final aq1<v13> e0 = new aq1<>();
    public final g23 f0;
    public final hz2 g0;
    public final vb1 h0;
    public final hj i0;
    public final f23 j0;
    public final aq1<BookmarkModel> k0;
    public final aq1<ny2> l0;
    public v13 m0;
    public a n0;
    public aq1<TabContentManager> o0;
    public TabContentManager p0;
    public UmaSessionStats q0;
    public boolean r0;
    public final org.chromium.chrome.browser.b s0;
    public boolean t0;
    public boolean u0;
    public boolean v0;
    public boolean w0;
    public vq0 x0;
    public final aq1<CompositorViewHolder> y0;
    public final aq1<h61> z0;

    public class a extends j23 {
        public a(a23 a23Var) {
            super(a23Var);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void I0(TabImpl tabImpl) {
            ChromeActivity.this.V2();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void L0(TabImpl tabImpl, boolean z) {
            ChromeActivity.this.V2();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void a1(Tab tab, GURL gurl) {
            ChromeActivity.this.V2();
            d.c cVar = org.chromium.chrome.browser.offlinepages.d.a;
            WindowAndroid windowAndroidD = tab.D();
            if (fr1.l == null) {
                fr1.l = new HashMap();
                ApplicationStatus.g(new cr1());
            }
            Activity activity = (Activity) windowAndroidD.k().get();
            fr1 fr1Var = (fr1) fr1.l.get(activity);
            if (fr1Var == null) {
                y13 y13Var = g23.t(windowAndroidD).get();
                fr1 fr1Var2 = new fr1(y13Var, xp2.a(windowAndroidD), new er1(y13Var));
                fr1.l.put(activity, fr1Var2);
                fr1Var = fr1Var2;
            }
            boolean zE = org.chromium.chrome.browser.offlinepages.d.e(tab);
            br1 br1Var = (br1) tab.B().b(br1.class);
            if (br1Var == null) {
                br1Var = (br1) tab.B().d(br1.class, new br1());
            }
            br1Var.e = zE;
            br1Var.f = org.chromium.chrome.browser.offlinepages.d.f(tab.c());
            if (zE) {
                fr1Var.k = tab;
                if (!fr1Var.y1(tab)) {
                    fr1Var.i.put(Integer.valueOf(tab.getId()), new fr1.a());
                    tab.x(fr1Var);
                }
                if (!fr1Var.j) {
                    NetworkChangeNotifier.f.b.a(fr1Var);
                    fr1Var.j = true;
                }
            }
            fr1Var.z1(tab, false);
        }
    }

    public class b implements e23 {
        public final /* synthetic */ y13 e;

        public b(a23 a23Var) {
            this.e = a23Var;
        }

        @Override // defpackage.e23
        public final void g() {
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            boolean zMudil8Bg = N.Mudil8Bg("RequestDesktopSiteExceptions");
            y13 y13Var = this.e;
            if (zMudil8Bg) {
                SharedPreferencesManager.getInstance().removeKey("Chrome.RequestDesktopSiteExceptionsDowngrade.TabSettingSet");
                SharedPreferencesManager.getInstance().removeKey("Chrome.RequestDesktopSiteExceptionsDowngrade.GlobalSettingEnabled");
            } else if (N.ManEQDnV("RequestDesktopSiteExceptionsDowngrade") && !sharedPreferencesManager.contains("Chrome.RequestDesktopSiteExceptionsDowngrade.TabSettingSet")) {
                HashSet hashSet = new HashSet();
                for (TabModel tabModel : y13Var.y()) {
                    for (int i = 0; i < tabModel.getCount(); i++) {
                        hashSet.add(String.valueOf(tabModel.getTabAt(i).getId()));
                    }
                }
                sharedPreferencesManager.p("Chrome.RequestDesktopSiteExceptionsDowngrade.TabSettingSet", hashSet);
                sharedPreferencesManager.l("Chrome.RequestDesktopSiteExceptionsDowngrade.GlobalSettingEnabled", N.MJSt3Ocq(y13Var.z().f(), 73));
            }
            y13Var.l(this);
        }
    }

    public class c implements ya0.a {
        public c() {
        }

        @Override // ya0.a
        public final void G() {
            k11 k11Var = ChromeActivity.Z0;
            ChromeActivity.this.Q2();
        }

        @Override // ya0.a
        public final void c() {
            zo zoVar;
            sk skVar = bq.a;
            boolean zM09VlOh_ = N.M09VlOh_("FoldableJankFix");
            ChromeActivity chromeActivity = ChromeActivity.this;
            if (zM09VlOh_ && !chromeActivity.X0 && chromeActivity.r2()) {
                chromeActivity.X0 = true;
                chromeActivity.findViewById(R.id.content).setVisibility(4);
                if (chromeActivity.X0 && (zoVar = chromeActivity.Y0) != null) {
                    chromeActivity.I.postDelayed(zoVar, ChromeActivity.Z0.c());
                }
            }
            k11 k11Var = ChromeActivity.Z0;
            chromeActivity.Q2();
        }
    }

    public class e extends xr1 {
        public e() {
            super(true);
        }

        @Override // defpackage.xr1
        public final void a() {
            ChromeActivity chromeActivity = ChromeActivity.this;
            if (chromeActivity.J2()) {
                return;
            }
            b(false);
            chromeActivity.k.c();
            b(true);
        }
    }

    public ChromeActivity() {
        g23 g23Var = new g23();
        this.f0 = g23Var;
        this.g0 = new hz2();
        vb1 vb1Var = new vb1();
        this.h0 = vb1Var;
        this.i0 = new hj();
        this.j0 = new f23(g23Var);
        this.k0 = new aq1<>();
        this.l0 = new aq1<>();
        this.o0 = new aq1<>();
        this.y0 = new aq1<>();
        this.z0 = new aq1<>();
        this.A0 = new m01();
        this.G0 = new z2();
        this.K0 = new rs1<>();
        this.R0 = new ArrayList();
        this.S0 = new zb(new zo(this, 0));
        this.s0 = new org.chromium.chrome.browser.b(this, k2());
        vb1Var.r(new org.chromium.chrome.browser.keyboard_accessory.b());
    }

    @Deprecated
    public static ChromeActivity u2(WebContents webContents) {
        Activity activityN = z7.n(webContents);
        if (activityN instanceof ChromeActivity) {
            return (ChromeActivity) activityN;
        }
        return null;
    }

    public int A2() {
        return -1;
    }

    public int B2() {
        return -1;
    }

    public m7 C0() {
        return new o7(this, this.G0, this.L, G2(), this.N0.x, getWindow().getDecorView(), null, null, this.k0, null);
    }

    public fz2 C2() {
        return p0(G2().r());
    }

    public final TabModel D2() {
        y13 y13VarG2 = G2();
        return y13VarG2 == null ? lh0.a.a : y13VarG2.z();
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public bi1 E1() {
        return new bi1(new AppModalPresenter(this));
    }

    public final rq0 E2() {
        return z2().y;
    }

    public final s51 F2() {
        if (this.P0 == null) {
            this.P0 = l2();
        }
        return this.P0;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public void G0() {
        TraceEvent.q("ChromeActivity:CompositorInitialization", null);
        TabContentManager tabContentManager = this.p0;
        Context context = tabContentManager.i;
        int integer = context.getResources().getInteger(R$integer.default_compression_queue_size);
        int integer2 = context.getResources().getInteger(R$integer.default_write_queue_size);
        tabContentManager.e = N.MtRahKHu(tabContentManager, tabContentManager.c, TabContentManager.c(R$integer.default_approximation_thumbnail_cache_size, context, "approximation-thumbnails"), integer, integer2, (DeviceFormFactor.a(context) || TabContentManager.j.a()) ? false : true, oj2.z(context), tabContentManager.d());
        CompositorViewHolder compositorViewHolder = this.y0.f;
        g3 g3Var = this.O;
        TabContentManager tabContentManager2 = this.p0;
        PrefService prefServiceK = oj2.k(Profile.d());
        compositorViewHolder.m.c(SysUtils.isLowEndDevice(), g3Var, tabContentManager2);
        if (compositorViewHolder.x != null) {
            compositorViewHolder.m.getResourceManager().a().d(R$id.control_container, compositorViewHolder.x.getToolbarResourceAdapter());
        }
        compositorViewHolder.a0 = prefServiceK;
        TraceEvent.C("ChromeActivity:CompositorInitialization");
    }

    @Deprecated
    public y13 G2() {
        if (h2()) {
            return this.m0.b;
        }
        throw new IllegalStateException("Attempting to access TabModelSelector before initialization");
    }

    public final l23 H2() {
        nc2 nc2Var = this.N0;
        if (nc2Var == null) {
            return null;
        }
        return (l23) nc2Var.e.f;
    }

    public int I2() {
        return -1;
    }

    public final boolean J2() {
        boolean z;
        xm0 xm0Var;
        Tab tabP;
        h61 layoutManager;
        boolean z2;
        n3.X0(this.v0 ? "SystemBack" : "SystemBackBeforeNativeInitialized");
        if (d()) {
            n3.X0("SystemBackOnActivityFinishingOrDestroyed");
        }
        Integer num = u73.p.f;
        boolean z3 = false;
        if (num != null && num.intValue() > 0) {
            u73.c();
            zb.c(0);
        }
        du3 du3VarA = a8.A();
        if (du3VarA != null && du3VarA.a()) {
            zb.c(2);
            return true;
        }
        aq1<CompositorViewHolder> aq1Var = this.y0;
        if (aq1Var.f() && (layoutManager = aq1Var.f.getLayoutManager()) != null) {
            int i = 0;
            while (true) {
                ArrayList arrayList = layoutManager.L;
                if (i >= arrayList.size()) {
                    y51 y51Var = layoutManager.q;
                    if (y51Var == null || !y51Var.t()) {
                        z2 = false;
                    }
                } else {
                    if (((je2) arrayList.get(i)).q1() && ((je2) arrayList.get(i)).a()) {
                        zb.c(3);
                        break;
                    }
                    i++;
                }
            }
            z2 = true;
            if (z2) {
                return true;
            }
        }
        WebContents webContentsC = (h2() && (tabP = z7.p(D2())) != null) ? tabP.c() : null;
        SelectionPopupControllerImpl selectionPopupControllerImplJ = webContentsC != null ? SelectionPopupControllerImpl.j(webContentsC) : null;
        if (selectionPopupControllerImplJ != null && selectionPopupControllerImplJ.m()) {
            selectionPopupControllerImplJ.d();
            zb.c(5);
            return true;
        }
        if (((org.chromium.chrome.browser.keyboard_accessory.a) this.h0.f).a()) {
            zb.c(6);
            return true;
        }
        qq0 qq0Var = (qq0) E2();
        if (qq0Var.g()) {
            qq0Var.i();
            z = true;
        } else {
            z = false;
        }
        if (z) {
            zb.c(7);
            return true;
        }
        f fVar = this.N0.B;
        if (fVar != null && fVar.r()) {
            zb.c(8);
            return true;
        }
        if (yj.a() && (xm0Var = this.N0.m) != null) {
            FindToolbar findToolbar = xm0Var.a;
            if (findToolbar != null && findToolbar.getVisibility() == 0) {
                z3 = true;
            }
            if (z3) {
                zb.c(13);
                this.N0.m.a(true);
                return true;
            }
        }
        return m();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public void K() {
        this.v0 = true;
        z7.L();
        R2();
        DownloadManagerService downloadManagerServiceG = DownloadManagerService.g();
        dd0 dd0Var = new dd0();
        if (!downloadManagerServiceG.q) {
            downloadManagerServiceG.m = new org.chromium.chrome.browser.download.e(dd0Var);
            SharedPreferencesManager.getInstance().removeKey("ResumptionAttemptLeft");
            DownloadManagerService downloadManagerServiceG2 = DownloadManagerService.g();
            N.MQ35Y$D$(downloadManagerServiceG2.k(), downloadManagerServiceG2, ProfileKey.a());
            downloadManagerServiceG.q = true;
        }
        PowerMonitor.a();
        Object obj = BundleUtils.a;
        super.K();
        org.chromium.chrome.browser.keyboard_accessory.a aVar = (org.chromium.chrome.browser.keyboard_accessory.a) this.h0.f;
        g3 g3Var = this.O;
        f fVar = this.N0.B;
        aVar.O0((AsyncViewStub) findViewById(R$id.keyboard_accessory_sheet_stub), (AsyncViewStub) findViewById(R$id.keyboard_accessory_stub));
        this.K0.a(new c33(new ab2(G2()), h9.a));
        ya0 ya0Var = this.O.h;
        c cVar = new c();
        this.M0 = cVar;
        ya0Var.a.put(cVar, null);
    }

    public void K2() {
        int i = 5;
        g80.b().a(new l91(this, i, getClass().getSimpleName()));
        if (!SysUtils.isLowEndDevice()) {
            g80.b().a(new zo(this, 2));
        }
        g80.b().a(new wz(i));
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final g3 L1() {
        return new at(this, this.y0, this.D, this.h0, this.M);
    }

    public void L2() {
        boolean zB = zb.b();
        OnBackPressedDispatcher onBackPressedDispatcher = this.k;
        if (!zB) {
            onBackPressedDispatcher.a(this, new e());
            return;
        }
        zb zbVar = this.S0;
        onBackPressedDispatcher.a(this, zbVar.e);
        v73 v73Var = new v73();
        this.T0 = v73Var;
        zbVar.a(v73Var, 0);
        int i = 2;
        if (a8.A() != null) {
            zbVar.a(a8.A(), 2);
        }
        this.z0.p(new wb(i, this));
        int i2 = 1;
        c3 c3Var = new c3(i2, this);
        this.V0 = c3Var;
        this.f0.p(c3Var);
        this.i0.p(new xr(i2, this));
    }

    public final boolean M2() {
        return x2() == 1 || x2() == 2;
    }

    public boolean N2() {
        return false;
    }

    @Override // defpackage.nj
    public final boolean O() {
        return true;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final long O1() {
        return this.N;
    }

    public final void O2() {
        UmaSessionStats umaSessionStats = this.q0;
        if (umaSessionStats == null) {
            return;
        }
        if (umaSessionStats.a != null) {
            umaSessionStats.c.unregisterComponentCallbacks(umaSessionStats.d);
            umaSessionStats.b.destroy();
            umaSessionStats.a = null;
        }
        N.MAKAEiev(UmaSessionStats.f, umaSessionStats);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final View P1() {
        View viewFindViewById = findViewById(R$id.control_container);
        return viewFindViewById != null ? viewFindViewById : super.P1();
    }

    public final void P2() {
        if (this.q0 == null) {
            this.q0 = new UmaSessionStats(this);
        }
        UmaSessionStats.b();
        UmaSessionStats umaSessionStats = this.q0;
        y13 y13VarG2 = G2();
        g3 g3Var = this.O;
        if (UmaSessionStats.f == 0) {
            umaSessionStats.getClass();
            UmaSessionStats.f = N.MU4tSmY3();
        }
        umaSessionStats.a = y13VarG2;
        if (y13VarG2 != null) {
            ai3 ai3Var = new ai3(umaSessionStats);
            umaSessionStats.d = ai3Var;
            Context context = umaSessionStats.c;
            context.registerComponentCallbacks(ai3Var);
            umaSessionStats.e = context.getResources().getConfiguration().keyboard != 1;
            umaSessionStats.b = new bi3(umaSessionStats, umaSessionStats.a);
        }
        N.M950EFso(UmaSessionStats.f, umaSessionStats);
        v42 v42VarG = v42.g();
        v42VarG.b.l("in_metrics_sample", N.MGJFzlge());
        N.MmqfIJ4g(v42VarG.a());
        UmaSessionStats.b();
        Object obj = j70.a;
        try {
            new i70().c(i9.e);
        } catch (RejectedExecutionException unused) {
        }
        if (g3Var != null) {
            oj2.U(g3Var.hasPermission("android.permission.RECORD_AUDIO") ? 0 : g3Var.canRequestPermission("android.permission.RECORD_AUDIO") ? 1 : 2, 3, "VoiceInteraction.AudioPermissionEvent.SessionStart");
        }
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void Q1() {
        HashMap map = lp.a().b;
        g23 g23Var = this.f0;
        map.put(this, g23Var);
        this.F0 = new org.chromium.chrome.browser.metrics.a(g23Var);
    }

    public final boolean Q2() {
        if (r2()) {
            rs1<c33> rs1Var = this.K0;
            if (rs1Var.get() != null && !this.L0) {
                rs1Var.get().a();
                this.L0 = true;
                if (!isFinishing()) {
                    recreate();
                    this.I.removeCallbacks(this.Y0);
                    return true;
                }
            }
        }
        return false;
    }

    public final void R2() {
        if (!this.w0 && this.v0 && hasWindowFocus()) {
            int i = 1;
            if (DeviceFormFactor.a(this)) {
                new Handler().post(new uo(this, i));
            } else {
                i2();
            }
            this.w0 = true;
        }
    }

    @Override // defpackage.e7
    public boolean S0(int i, Bundle bundle) {
        this.I0 = bundle;
        vb1 vb1Var = this.h0;
        if (vb1Var.f()) {
            ((org.chromium.chrome.browser.keyboard_accessory.a) vb1Var.f).dismiss();
        }
        return h1(i, true);
    }

    @Override // org.chromium.chrome.browser.compositor.CompositorViewHolder.e
    public final void T(h61 h61Var, View view, e00 e00Var) {
        g3 g3Var = this.O;
        aj3<i61.a> aj3Var = i61.a;
        i61.a.a(g3Var.r, h61Var);
        this.z0.r(h61Var);
        h61Var.p.a(this);
        CompositorViewHolder compositorViewHolder = this.y0.f;
        compositorViewHolder.setLayoutManager(h61Var);
        compositorViewHolder.setFocusable(false);
        compositorViewHolder.setControlContainer(e00Var);
        compositorViewHolder.setBrowserControlsManager((fj) this.i0.f);
        compositorViewHolder.setUrlBar(view);
        compositorViewHolder.setInsetObserverView((InsetObserverView) this.A0.f);
        compositorViewHolder.setAutofillUiBottomInsetSupplier(((org.chromium.chrome.browser.keyboard_accessory.a) this.h0.f).t0());
        compositorViewHolder.setApplicationViewportInsetSupplier(this.O.m);
        compositorViewHolder.setTopUiThemeColorProvider(this.N0.v);
        y13 y13VarG2 = G2();
        compositorViewHolder.l.M(y13VarG2, this, compositorViewHolder.x, compositorViewHolder.m.getResourceManager().a(), compositorViewHolder.V);
        compositorViewHolder.t = y13VarG2;
        y13VarG2.m(new xw(compositorViewHolder));
        compositorViewHolder.t();
        compositorViewHolder.j = true;
        dx2.a aVarI = h61Var.I();
        if (e00Var != null && g90.c().e && aVarI != null) {
            e00Var.setSwipeHandler(aVarI);
        }
        z2 z2Var = this.G0;
        z2Var.h = h61Var;
        h61Var.j(z2Var.i);
        if (bq.F.a()) {
            if (z2Var.j == null || !h61Var.q(1)) {
                z2Var.s(null);
            }
        }
    }

    public final void T2(boolean z) {
        if (this.H0 || ik.g().k()) {
            return;
        }
        R2();
        Tab tabV2 = v2();
        if (tabV2 != null) {
            if (tabV2.isHidden() && Z2()) {
                tabV2.l(3, 2);
            } else {
                tabV2.C(1);
            }
        }
        vj1.h.getClass();
        if (tabV2 != null && !tabV2.isIncognito() && tabV2.c() != null) {
            N.MCnWTXic(tabV2.c(), "Android.MultiWindowState", "WindowState", vj1.o(this) ? 1 : 0);
        }
        if (this.r0) {
            this.r0 = false;
            PartnerBrowserCustomizations.b().c(getApplicationContext());
            PartnerBrowserCustomizations.b().h(new uo(this, 0));
        }
        aq1<CompositorViewHolder> aq1Var = this.y0;
        if (aq1Var.f()) {
            CompositorViewHolder compositorViewHolder = aq1Var.f;
            fj fjVar = compositorViewHolder.u;
            if (fjVar != null) {
                fjVar.o(compositorViewHolder);
            }
            compositorViewHolder.f(null);
        }
        Log.i("BydPCoordinator  ", "onStartTab: isStart   " + z);
        this.H0 = true;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void U1() {
        uk ukVar = CachedFeatureFlags.d;
        if (ukVar.d.get()) {
            return;
        }
        if (ukVar.c.getAndSet(false)) {
            int iD = SharedPreferencesManager.getInstance().d(0, "Chrome.Flags.CrashStreakBeforeCache") - 1;
            SharedPreferencesManager.getInstance().m(iD >= 0 ? iD : 0, "Chrome.Flags.CrashStreakBeforeCache");
            oj2.U(ukVar.b.get(), 5, "Variations.SafeModeCachedFlags.Pause");
        }
    }

    public final void U2(boolean z) {
        CompositorViewHolder compositorViewHolder;
        fj fjVar;
        h61 layoutManager;
        if (this.H0) {
            Tab tabV2 = v2();
            a23 a23Var = this.m0.b;
            if (a23Var != null && !a23Var.k && tabV2 != null) {
                tabV2.hide(1);
            }
            boolean z2 = this.v0;
            aq1<CompositorViewHolder> aq1Var = this.y0;
            if (z2) {
                sk skVar = bq.a;
                if (N.M09VlOh_("KeepAndroidTintedResources") && aq1Var.f() && (layoutManager = aq1Var.f.getLayoutManager()) != null && layoutManager.H() != null) {
                    ResourceManager resourceManagerH = layoutManager.H();
                    long j = resourceManagerH.d;
                    if (j != 0) {
                        N.MnAVuP1v(j, resourceManagerH);
                    }
                }
            }
            this.r0 = true;
            if (aq1Var.f() && (fjVar = (compositorViewHolder = aq1Var.f).u) != null) {
                fjVar.s(compositorViewHolder);
            }
            Log.i("BydPCoordinator  ", "onStopTab: isActivity   " + z);
            this.H0 = false;
        }
    }

    public final void V2() {
        if (!this.v0) {
            this.t0 = true;
            return;
        }
        this.t0 = false;
        if (this.u0) {
            return;
        }
        this.u0 = true;
        K2();
        b62 b62VarB = b62.b();
        b62VarB.getClass();
        ThreadUtils.b();
        if (!b62VarB.c) {
            b62VarB.c = true;
            g80 g80VarB = g80.b();
            g80VarB.a(new t52(b62VarB));
            g80VarB.a(new u52(b62VarB));
            g80VarB.a(new v52());
            g80VarB.a(new w52());
            g80VarB.a(new x52());
            g80VarB.a(new y52());
            g80VarB.a(new z52());
            g80VarB.a(new a62(b62VarB));
            g80VarB.a(new q52());
            int i = 5;
            g80VarB.a(new gg3(i));
            int i2 = 14;
            g80VarB.a(new wz(i2));
            int i3 = 13;
            g80VarB.a(new zc0(i3, ((s50) pp.d()).e()));
            int i4 = 6;
            g80VarB.a(new o90(i4));
            g80VarB.a(new qe3(i2));
            int i5 = 15;
            g80VarB.a(new wz(i5));
            int i6 = 7;
            g80VarB.a(new gg3(i6));
            g80VarB.a(new o90(i6));
            g80VarB.a(new qe3(i5));
            g80VarB.a(new wz(16));
            g80VarB.a(new o90(4));
            g80VarB.a(new qe3(12));
            g80VarB.a(new wz(i3));
            g80VarB.a(new gg3(i4));
            g80VarB.a(new o90(i));
            g80VarB.a(new qe3(i3));
        }
        final g80 g80VarB2 = g80.b();
        g80VarB2.getClass();
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: f80
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                g80 g80Var = g80VarB2;
                LinkedList linkedList = g80Var.a;
                try {
                    Runnable runnable = (Runnable) linkedList.poll();
                    if (runnable != null) {
                        runnable.run();
                    }
                    if (linkedList.isEmpty()) {
                        if (g80.b == g80Var) {
                            g80.b = null;
                        }
                        return false;
                    }
                } catch (Throwable th) {
                    new Handler().post(new wv(12, th));
                }
                new Handler().post(new qe3(2));
                return true;
            }
        });
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void W1() {
        this.D0 = SystemClock.elapsedRealtime();
        this.N0.q0.g();
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().getRootView();
        aq1<CompositorViewHolder> aq1Var = this.y0;
        aq1Var.r((CompositorViewHolder) findViewById(R$id.compositor_view_holder));
        aq1Var.f.setRootView(viewGroup);
        viewGroup.setFitsSystemWindows(false);
        int i = InsetObserverView.k;
        InsetObserverView.c cVar = new InsetObserverView.c(this);
        m01 m01Var = this.A0;
        m01Var.r(cVar);
        viewGroup.addView((View) m01Var.f, 0);
        super.W1();
    }

    public void W2(long j) {
        oj2.b0(j, "MobileStartup.IntentToCreationTime");
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, on1.a
    public void X0() {
        rs1<c33> rs1Var = this.K0;
        if (rs1Var.get() != null) {
            rs1Var.get().a();
        }
        super.X0();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final boolean X1(String str) {
        wp2 wp2Var = this.B0;
        if (wp2Var != null) {
            tp2 tp2VarA = tp2.a(str, null, 1, 45);
            tp2VarA.i = false;
            tp2VarA.j = 8000;
            wp2Var.c(tp2VarA);
        }
        return true;
    }

    public final void X2(boolean z, boolean z2) {
        vj1 vj1Var = vj1.h;
        boolean z3 = !(this instanceof ChromeTabbedActivity2);
        Tab tabV2 = v2();
        if (z3) {
            boolean z4 = vj1Var.e;
            if (z) {
                if (z4) {
                    SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                    if (sharedPreferencesManager.f("Chrome.MultiWindow.StartTime") == 0) {
                        n3.X0("Android.MultiWindowMode.Enter2");
                        sharedPreferencesManager.n(System.currentTimeMillis(), "Chrome.MultiWindow.StartTime");
                    }
                } else {
                    n3.X0("Android.MultiWindowMode.Enter2");
                }
            } else if (z4) {
                SharedPreferencesManager sharedPreferencesManager2 = SharedPreferencesManager.getInstance();
                long jF = sharedPreferencesManager2.f("Chrome.MultiWindow.StartTime");
                if (jF > 0) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    n3.X0("Android.MultiWindowMode.Exit2");
                    oj2.X(jCurrentTimeMillis - jF, "Android.MultiWindowMode.TotalDuration");
                    sharedPreferencesManager2.n(0L, "Chrome.MultiWindow.StartTime");
                }
            } else {
                n3.X0("Android.MultiWindowMode.Exit2");
            }
        } else {
            vj1Var.getClass();
            if (z2) {
                n3.X0("Android.MultiWindowMode.MultiInstance.Enter");
            } else if (z) {
                n3.X0("Android.MultiWindowMode.Enter-SecondInstance");
            } else {
                n3.X0("Android.MultiWindowMode.Exit-SecondInstance");
            }
        }
        if (tabV2 == null || tabV2.isIncognito() || tabV2.c() == null) {
            return;
        }
        N.MCnWTXic(tabV2.c(), "Android.MultiWindowChangeActivity", "ActivityType", !z ? 1 : 0);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public void Y1() {
        uk ukVar = CachedFeatureFlags.d;
        if (!ukVar.d.get() && !ukVar.c.getAndSet(true)) {
            SharedPreferencesManager.getInstance().c("Chrome.Flags.CrashStreakBeforeCache");
            oj2.U(ukVar.b.get(), 5, "Variations.SafeModeCachedFlags.WillCache");
        }
        L2();
    }

    public boolean Y2() {
        return v2() == null || !v2().k();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public void Z1(Configuration configuration) {
        if (this.J0 != null) {
            if (this.K0.get() != null && Q2()) {
                return;
            }
            Configuration configuration2 = this.J0;
            if (((configuration2.uiMode & 15) == 4) != ((configuration.uiMode & 15) == 4)) {
                recreate();
                return;
            }
            int i = configuration.orientation;
            if (i != configuration2.orientation) {
                int i2 = i == 2 ? 1 : 0;
                Tab tabV2 = v2();
                if (tabV2 != null && !tabV2.isIncognito() && tabV2.c() != null) {
                    N.MCnWTXic(tabV2.c(), "Android.ScreenRotation", "TargetDeviceOrientation", i2 ^ 1);
                }
            }
        }
        this.J0 = configuration;
    }

    public boolean Z2() {
        return true;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    @SuppressLint({"NewApi"})
    public void a2() {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeActivity.performPostInflationStartup", null);
        try {
            super.a2();
            int i = 1;
            if ((getIntent().getFlags() & 1048576) != 0) {
                F2().e.c = true;
            } else {
                s51 s51VarF2 = F2();
                s51.c cVar = s51VarF2.e;
                Activity activity = s51.i;
                cVar.b = activity != s51VarF2.g && (activity instanceof ChromeActivity);
                cVar.a = true;
            }
            BottomContainer bottomContainer = (BottomContainer) findViewById(R$id.bottom_container);
            this.O0 = bottomContainer;
            wp2 wp2Var = new wp2(this, bottomContainer, this.O);
            this.B0 = wp2Var;
            g3 g3Var = this.O;
            aj3<wp2> aj3Var = xp2.a;
            xp2.a.a(g3Var.r, wp2Var);
            CombinedPolicyProvider.a().f.add(this);
            this.E0 = true;
            g3 g3Var2 = this.O;
            aq1<CompositorViewHolder> aq1Var = this.y0;
            g3Var2.k = aq1Var.f.getCompositorView();
            g3Var2.n = g3Var2.l.isTouchExplorationEnabled();
            g3Var2.s();
            g3Var2.o = new WindowAndroid.f(g3Var2);
            c1();
            if (isFinishing()) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                    return;
                }
                return;
            }
            a23 a23Var = this.m0.b;
            TabContentManager tabContentManager = new TabContentManager(this, aq1Var.f, !SysUtils.isLowEndDevice(), a23Var != null ? new pq2(4, a23Var) : null);
            this.p0 = tabContentManager;
            new yy2(tabContentManager, (qq0) E2(), G2());
            this.o0.r(tabContentManager);
            z2().w((e00) findViewById(R$id.control_container), this.G0, G2(), A2());
            BottomContainer bottomContainer2 = this.O0;
            fj fjVarZ2 = z2();
            x7 x7Var = this.O.m;
            zp1<Integer> zp1VarT0 = ((org.chromium.chrome.browser.keyboard_accessory.a) this.h0.f).t0();
            bottomContainer2.f = fjVarZ2;
            fjVarZ2.o(bottomContainer2);
            bottomContainer2.g = x7Var;
            bottomContainer2.h = zp1VarT0;
            x10 x10Var = bottomContainer2.e;
            x7Var.p(x10Var);
            bottomContainer2.h.p(x10Var);
            bottomContainer2.setTranslationY(bottomContainer2.i);
            this.d0.r(new fk2(this.N0.B, this.K, this.G0, this.f0, this.j0, new fk2.a(), M2()));
            aq1<BookmarkModel> aq1Var2 = this.k0;
            nc2 nc2Var = this.N0;
            Objects.requireNonNull(nc2Var);
            this.l0.r(new ny2(this, aq1Var2, new bs(nc2Var, i), new bp(this, 2), M2()));
            this.Y0 = new zo(this, i);
            if (this.H0) {
                CompositorViewHolder compositorViewHolder = aq1Var.f;
                fj fjVar = compositorViewHolder.u;
                if (fjVar != null) {
                    fjVar.o(compositorViewHolder);
                }
                compositorViewHolder.f(null);
            }
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

    public final void a3(String str, String str2, Profile profile) {
        ((hu0) hu0.a()).c(this, TextUtils.isEmpty(str) ? getString(R$string.help_context_general) : str.startsWith("chrome-native://bookmarks/") ? getString(R$string.help_context_bookmarks) : str.equals("chrome://history/") ? getString(R$string.help_context_history) : N.M$l72hrq(str) ? getString(R$string.help_context_search_results) : D2().isIncognito() ? getString(R$string.help_context_incognito) : str.equals("chrome-native://newtab/") ? getString(R$string.help_context_new_tab) : getString(R$string.help_context_webpage), str, profile);
        n3.X0(str2);
    }

    /* JADX WARN: Type inference failed for: r0v23, types: [vo] */
    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public void b2() {
        org.chromium.base.c cVar = this.O.r;
        gk2 gk2Var = this.d0;
        gk2Var.s(cVar);
        org.chromium.base.c cVar2 = this.O.r;
        g23 g23Var = this.f0;
        g23Var.s(cVar2);
        this.g0.s(this.O.r);
        org.chromium.base.c cVar3 = this.O.r;
        vb1 vb1Var = this.h0;
        vb1Var.s(cVar3);
        this.A0.s(this.O.r);
        org.chromium.base.c cVar4 = this.O.r;
        hj hjVar = this.i0;
        hjVar.s(cVar4);
        hjVar.r(new fj(this));
        this.J0 = getResources().getConfiguration();
        this.N0 = m2();
        this.W0 = new wv2(this, this.K, this.G0);
        nc2 nc2Var = this.N0;
        Objects.requireNonNull(nc2Var);
        fp fpVar = new fp(nc2Var, 0);
        z2();
        fj fjVarZ2 = z2();
        fj fjVarZ22 = z2();
        rq0 rq0VarE2 = E2();
        aq1<h61> aq1Var = this.z0;
        r2 r2Var = this.K;
        dp dpVar = new dp(this, 2);
        z2 z2Var = this.G0;
        g3 g3Var = this.O;
        aq1<CompositorViewHolder> aq1Var2 = this.y0;
        bp bpVar = new bp(this, 4);
        ep epVar = new ep(this, 2);
        dt2 dt2Var = this.N0.q0;
        ScreenOrientationProviderImpl.getInstance();
        this.c0 = (C) j2(new hp(this, fpVar, g23Var, fjVarZ2, fjVarZ22, (qq0) rq0VarE2, aq1Var, r2Var, dpVar, z2Var, g3Var, aq1Var2, this, bpVar, epVar, dt2Var, new nw2() { // from class: vo
            @Override // defpackage.nw2
            public final Object get() {
                k11 k11Var = ChromeActivity.Z0;
                return new NotificationManagerProxyImpl(this.e.getApplicationContext());
            }
        }, this.o0, new dp(this, 3), this, this, this.D, z2(), new bp(this, 5), ((org.chromium.chrome.browser.keyboard_accessory.a) vb1Var.f).t0(), gk2Var, this, x2()));
        v13 v13VarO2 = o2();
        this.m0 = v13VarO2;
        this.e0.r(v13VarO2);
        this.j0.p(new so(2, this));
        super.b2();
        this.r0 = !PartnerBrowserCustomizations.b().d;
        iv ivVarE = iv.e();
        if (!ivVarE.g("disable-fullscreen")) {
            TypedValue typedValue = new TypedValue();
            getResources().getValue(R$dimen.top_controls_show_threshold, typedValue, true);
            ivVarE.b("top-controls-show-threshold", typedValue.coerceToString().toString());
            getResources().getValue(R$dimen.top_controls_hide_threshold, typedValue, true);
            ivVarE.b("top-controls-hide-threshold", typedValue.coerceToString().toString());
        }
        getWindow().setBackgroundDrawable(y2());
    }

    public boolean b3() {
        return I2() != -1;
    }

    @Override // defpackage.s13
    public final void c1() {
        if (h2()) {
            return;
        }
        p2();
        a23 a23Var = this.m0.b;
        if (a23Var == null) {
            return;
        }
        this.f0.r(a23Var);
        z2 z2Var = this.G0;
        z2Var.j = a23Var;
        z2Var.k = new a3(z2Var, a23Var);
        b3 b3Var = new b3(z2Var);
        z2Var.l = b3Var;
        z2Var.j.m(b3Var);
        dt2 dt2Var = this.N0.q0;
        dt2Var.j = a23Var;
        a23Var.m(dt2Var.i);
        dt2Var.j.r();
        dt2Var.g();
        final Pair<? extends fz2, ? extends fz2> pairN2 = n2();
        this.g0.r(new gz2() { // from class: wo
            @Override // defpackage.gz2
            public final fz2 p0(boolean z) {
                k11 k11Var = ChromeActivity.Z0;
                Pair pair = pairN2;
                return (fz2) (z ? pair.second : pair.first);
            }
        });
        d.C0097d c0097d = (d.C0097d) org.chromium.chrome.browser.offlinepages.d.b.put(this, new d.C0097d(a23Var));
        if (c0097d != null) {
            c0097d.a();
        } else {
            ApplicationStatus.f(new org.chromium.chrome.browser.offlinepages.c(), this);
        }
        a aVar = this.n0;
        if (aVar != null) {
            aVar.destroy();
        }
        this.n0 = new a(a23Var);
        a23Var.m(new b(a23Var));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f() {
        /*
            Method dump skipped, instruction units count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.app.ChromeActivity.f():void");
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public void g0() {
        org.chromium.chrome.browser.b.f = iv.e().g("enable-test-intents");
    }

    public boolean g1() {
        if (d()) {
            return false;
        }
        int iC = ApplicationStatus.c(this);
        vj1.h.getClass();
        boolean zO = vj1.o(this);
        if (iC != 3) {
            return zO && iC == 4;
        }
        return true;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void g2() {
        this.C0 = SystemClock.elapsedRealtime();
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeActivity.triggerLayoutInflation", null);
        boolean z = true;
        try {
            SelectionPopupControllerImpl.M = true;
            if (!SysUtils.isLowEndDevice()) {
                getWindow().addFlags(16777216);
                this.a0 = true;
            }
            int i = SysUtils.isLowEndDevice() ? R$style.Theme_Chromium_WithWindowAnimation_LowEnd : R$style.Theme_Chromium_WithWindowAnimation;
            int i2 = R$style.Theme_Chromium_WithWindowAnimation_LowEnd;
            if (i == i2) {
                setTheme(i2);
            }
            zo3 zo3VarA = zo3.a();
            int iB2 = B2();
            zo3VarA.getClass();
            Object obj = ThreadUtils.a;
            if (zo3VarA.d == null || zo3VarA.c != iB2) {
                z = false;
            }
            if (z) {
                View view = new View(this);
                setContentView(view);
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                ViewGroup viewGroup2 = zo3VarA.d;
                zo3VarA.d = null;
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeView(childAt);
                        viewGroup.addView(childAt);
                    }
                }
                viewGroup.removeView(view);
                W1();
            } else {
                zo3VarA.d = null;
                t2();
            }
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

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public void h() {
        n3.X0("MobileGoToBackground");
        Tab tabV2 = v2();
        if (tabV2 != null) {
            this.p0.a(tabV2);
        }
        ((org.chromium.chrome.browser.keyboard_accessory.a) this.h0.f).onPause();
        O2();
        super.h();
    }

    /* JADX WARN: Type inference failed for: r5v5, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v0, types: [T, eb2] */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v5, types: [T, java.lang.String] */
    public boolean h1(int i, boolean z) {
        Iterator it = this.R0.iterator();
        while (it.hasNext()) {
            if (((jf1.a) it.next()).a(i, z)) {
                return true;
            }
        }
        int iC = Profile.c(D2().f());
        if (i == R$id.preferences_id) {
            Intent intent = new Intent();
            intent.setClass(this, SettingsActivity.class);
            ComponentName componentName = q11.a;
            try {
                startActivity(intent, null);
            } catch (ActivityNotFoundException unused) {
            }
            n3.X0("MobileMenuSettings");
            oj2.U(iC, 6, "Settings.OpenSettingsFromMenu.PerProfileType");
            return true;
        }
        if (i == R$id.update_menu_id) {
            hj3 hj3VarA = hj3.a();
            nj3.c cVar = hj3VarA.c;
            if (cVar != null && cVar.a == 1 && !TextUtils.isEmpty(cVar.b)) {
                try {
                    nj3.a.a.a(this);
                } catch (ActivityNotFoundException unused2) {
                    fa1.b("UpdateMenuItemHelper", "Failed to launch Activity for: %s", hj3VarA.c.b);
                }
                if (hj3VarA.c.c != null) {
                    oj2.k(Profile.d()).f("omaha.latest_version_when_clicked_upate_menu_item", hj3VarA.c.c);
                }
                hj3VarA.b();
            }
            return true;
        }
        Tab tabV2 = v2();
        if (i == R$id.help_id) {
            a3(tabV2 != null ? tabV2.getUrl().j() : "", "MobileMenuFeedback", G2().r() ? Profile.d().f(true) : Profile.d());
            return true;
        }
        if (i == R$id.open_history_menu_id) {
            if (tabV2 != null && bk3.l(tabV2.getUrl())) {
                um1.a(5);
            }
            n3.X0("MobileMenuHistory");
            zb2.g("Chrome.StartSurface.OpenHistoryCount");
            z7.j0(this, tabV2, G2().r());
            oj2.U(iC, 6, "Android.OpenHistoryFromMenu.PerProfileType");
            return true;
        }
        boolean z2 = false;
        z2 = false;
        if (tabV2 == null) {
            return false;
        }
        if (i == R$id.forward_menu_id) {
            if (!tabV2.i()) {
                return false;
            }
            tabV2.h();
            n3.X0("MobileMenuForward");
            return true;
        }
        int i2 = R$id.bookmark_this_page_id;
        aq1<ny2> aq1Var = this.l0;
        if (i == i2 || i == R$id.add_bookmark_menu_id || i == R$id.edit_bookmark_menu_id) {
            aq1Var.f.a(tabV2, false);
            oj2.t(Profile.d()).notifyEvent("app_menu_bookmark_star_icon_pressed");
            n3.X0("MobileMenuAddToBookmarks");
            return true;
        }
        if (i == R$id.enable_price_tracking_menu_id) {
            aq1Var.f.b(tabV2);
            n3.X0("MobileMenuEnablePriceTracking");
            oj2.t(Profile.d()).notifyEvent("shopping_list_track_price_from_menu");
            return true;
        }
        if (i == R$id.disable_price_tracking_menu_id) {
            BookmarkModel bookmarkModel = this.k0.f;
            e32.b(bookmarkModel, bookmarkModel.p(tabV2), false, this.B0, getResources(), new xo(z2 ? 1 : 0));
            n3.X0("MobileMenuDisablePriceTracking");
            return true;
        }
        if (i == R$id.offline_page_id) {
            DownloadUtils.c(this, tabV2);
            n3.X0("MobileMenuDownloadPage");
            return true;
        }
        if (i == R$id.reload_menu_id) {
            if (tabV2.k()) {
                tabV2.y();
                n3.X0("MobileMenuStop");
            } else {
                tabV2.a();
                n3.X0("MobileMenuReload");
            }
            return true;
        }
        if (i == R$id.info_menu_id) {
            aq1<bi1> aq1Var2 = this.D;
            aq1<zf1> aq1Var3 = this.N0.K;
            Objects.requireNonNull(aq1Var3);
            yo yoVar = new yo(z2 ? 1 : 0, aq1Var3);
            nw2<EphemeralTabCoordinator> nw2Var = this.N0.B0;
            wq wqVarA = wq.a();
            WebContents webContentsC = tabV2.c();
            if (webContentsC != null && ProfileManager.b) {
                Activity activityB = TabUtils.b(tabV2);
                PageInfoController.g(activityB, webContentsC, null, 1, new vq(activityB, webContentsC, aq1Var2, new d.e(tabV2), yoVar, nw2Var, wqVarA), wqVarA);
            }
            return true;
        }
        if (i == R$id.translate_id) {
            n3.X0("MobileMenuTranslate");
            oj2.t(Profile.b(tabV2.c())).notifyEvent("translate_menu_button_clicked");
            N.M0540rIu(tabV2.c());
            return true;
        }
        if (i == R$id.add_to_homescreen_id) {
            n3.X0("MobileMenuAddToHomescreen");
            s2(tabV2);
            return true;
        }
        if (i == R$id.install_webapp_id) {
            n3.X0("InstallWebAppFromMenu");
            s2(tabV2);
            return true;
        }
        if (i == R$id.open_webapk_id) {
            n3.X0("MobileMenuOpenWebApk");
            Context context = xz.a;
            try {
                context.startActivity(z7.d(fq3.d(context, tabV2.getUrl().j()), tabV2.getUrl().j(), false));
            } catch (ActivityNotFoundException unused3) {
                Toast.c(context, R$string.open_webapk_failed, 0).e();
            }
            return true;
        }
        if (i != R$id.request_desktop_site_id && i != R$id.request_desktop_site_check_id) {
            if (i != R$id.auto_dark_web_contents_id && i != R$id.auto_dark_web_contents_check_id) {
                if (i == R$id.reader_mode_prefs_id) {
                    DomDistillerUIUtils.openSettings(tabV2.c());
                    return true;
                }
                if (i != R$id.managed_by_menu_id) {
                    return false;
                }
                Tab tabV22 = v2();
                if (tabV22 != null && tabV22.isIncognito()) {
                    z2 = true;
                }
                fz2 fz2VarP0 = p0(z2);
                if (fz2VarP0 != null) {
                    fz2VarP0.b(2, v2(), new LoadUrlParams("chrome://management/", 6));
                }
                return true;
            }
            Profile profileF = D2().f();
            GURL url = tabV2.getUrl();
            boolean zA = mq3.a(profileF, url);
            boolean z3 = !zA;
            N.M3qJKRL4(profileF, 72, url, url, z3 ? 0 : 2);
            n3.Y0(2, z3);
            tabV2.c().K();
            WebContents webContentsC2 = tabV2.c();
            if (!z3) {
                N.M$ejnyHh(webContentsC2, "Android.DarkTheme.AutoDarkMode", "DisabledByUser");
            }
            if (!zA) {
                return true;
            }
            String strJ = url.j();
            bi1 bi1VarP0 = P0();
            n3 n3Var = new n3();
            fu0 fu0VarA = hu0.a();
            xe3 xe3VarT = oj2.t(profileF);
            xe3VarT.notifyEvent("auto_dark_disabled_in_app_menu");
            if (xe3VarT.shouldTriggerHelpUI("IPH_AutoDarkOptOut")) {
                Resources resources = getResources();
                sk skVar = bq.a;
                boolean zM6bsIDpc = N.M6bsIDpc("DarkenWebsitesCheckboxInThemesSetting", "feedback_dialog", false);
                int i3 = zM6bsIDpc ? R$string.auto_dark_dialog_title : R$string.auto_dark_dialog_no_feedback_title;
                Object objA = zM6bsIDpc ? iq2.a(getResources().getString(R$string.auto_dark_dialog_message), new iq2.a(new oq3(this, n3Var), "<link>", "</link>")) : resources.getString(R$string.auto_dark_dialog_no_feedback_message);
                int i4 = zM6bsIDpc ? R$string.auto_dark_dialog_positive_button : R$string.auto_dark_dialog_no_feedback_positive_button;
                nq3 nq3Var = new nq3(zM6bsIDpc, fu0VarA, this, profileF, strJ, n3Var, bi1VarP0, xe3VarT);
                PropertyModel.b bVar = new PropertyModel.b(di1.B);
                bVar.e(di1.a, nq3Var);
                bVar.d(di1.c, resources, i3);
                bVar.e(di1.e, AppCompatResources.a(this, R$drawable.ic_brightness_medium_24dp));
                bVar.e(di1.f, objA);
                bVar.d(di1.j, resources, i4);
                bVar.d(di1.m, resources, R$string.cancel);
                bi1VarP0.j(bVar.a(), 0, false);
            }
            return true;
        }
        boolean z4 = !tabV2.c().m().l();
        if (N.Mudil8Bg("RequestDesktopSiteExceptions")) {
            Profile profileF2 = D2().f();
            fb2.c(profileF2, tabV2.getUrl(), z4);
            TabUtils.d(0, tabV2, z4, false);
            bi1 bi1VarP02 = P0();
            if (Profile.c(profileF2) != 1) {
                xe3 xe3VarT2 = oj2.t(profileF2);
                if (xe3VarT2.shouldTriggerHelpUI("IPH_RequestDesktopSiteAppMenu")) {
                    Resources resources2 = getResources();
                    ?? eb2Var = new eb2(bi1VarP02, xe3VarT2);
                    HashMap mapE = PropertyModel.e(di1.B);
                    PropertyModel.l<di1.a> lVar = di1.a;
                    int i5 = zj.a;
                    PropertyModel.g gVar = new PropertyModel.g();
                    gVar.a = eb2Var;
                    mapE.put(lVar, gVar);
                    PropertyModel.r<String> rVar = di1.c;
                    ?? string = resources2.getString(R$string.rds_app_menu_user_education_dialog_title);
                    PropertyModel.g gVar2 = new PropertyModel.g();
                    gVar2.a = string;
                    mapE.put(rVar, gVar2);
                    PropertyModel.r<CharSequence> rVar2 = di1.f;
                    ?? string2 = resources2.getString(R$string.rds_app_menu_user_education_dialog_message);
                    PropertyModel.g gVar3 = new PropertyModel.g();
                    gVar3.a = string2;
                    mapE.put(rVar2, gVar3);
                    PropertyModel.r<String> rVar3 = di1.j;
                    ?? string3 = resources2.getString(R$string.got_it);
                    PropertyModel.g gVar4 = new PropertyModel.g();
                    gVar4.a = string3;
                    mapE.put(rVar3, gVar4);
                    PropertyModel.n nVar = di1.q;
                    PropertyModel.a aVar = new PropertyModel.a();
                    aVar.a = true;
                    mapE.put(nVar, aVar);
                    bi1VarP02.j(new PropertyModel(mapE), 1, true);
                }
            }
            oj2.t(profileF2).notifyEvent("app_menu_desktop_site_exception_added");
        } else {
            TabUtils.d(0, tabV2, z4, true);
            oj2.t(Profile.d()).notifyEvent("app_menu_desktop_site_for_tab_clicked");
        }
        Tab tabV23 = v2();
        if (!bq.a.a() || z4) {
            n3.X0("MobileMenuRequestDesktopSite");
        } else {
            n3.X0("MobileMenuRequestMobileSite");
        }
        oj2.O("Android.RequestDesktopSite.UserSwitchToDesktop", z4);
        if (tabV23 == null || tabV23.isIncognito() || tabV23.c() == null) {
            return true;
        }
        N.MCnWTXic(tabV23.c(), "Android.UserRequestedUserAgentChange", "UserAgentType", !z4 ? 1 : 0);
        return true;
    }

    public final boolean h2() {
        v13 v13Var = this.m0;
        return v13Var != null && v13Var.d;
    }

    public void i2() {
        getWindow().setBackgroundDrawable(new ColorDrawable(ii2.f(this)));
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public void j() {
        super.j();
        lp lpVarA = lp.a();
        lpVarA.getClass();
        Object obj = ThreadUtils.a;
        if (!lpVarA.e) {
            lpVarA.e = true;
            TraceEvent traceEventH0 = TraceEvent.H0("ChromeActivitySessionTracker.onForegroundSessionStart", null);
            try {
                UmaUtils.d();
                boolean z = Settings.System.getInt(xz.a.getContentResolver(), "show_password", 1) == 1;
                if (oj2.k(Profile.d()).a("webkit.webprefs.password_echo_enabled") != z) {
                    oj2.k(Profile.d()).e("webkit.webprefs.password_echo_enabled", z);
                }
                Profile profileD = Profile.d();
                if (FontSizePrefs.c == null) {
                    FontSizePrefs.c = new FontSizePrefs(profileD);
                }
                FontSizePrefs fontSizePrefs = FontSizePrefs.c;
                float fA = fontSizePrefs.a();
                if (fA != 0.0f) {
                    fontSizePrefs.b(xz.a.getResources().getConfiguration().fontScale * fA);
                }
                a8.c0();
                lp.b();
                cl3 cl3Var = lpVarA.c;
                if (!cl3Var.a || cl3Var.b != null) {
                    cl3Var.a = true;
                    if (cl3Var.b != null) {
                        N.McCUpqFO(cl3Var, cl3Var.b);
                    } else {
                        cl3Var.b = "";
                        N.McCUpqFO(cl3Var, cl3Var.b);
                    }
                }
                lpVarA.a.a();
                AppHooks.get().getClass();
                N.M$Y7OM3S();
                Iterator<Long> it = PasswordManagerLifecycleHelper.getInstance().a.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        N.MmoiIbfp(((Long) aVar.next()).longValue());
                    }
                }
                oj2.O("Startup.BringToForegroundReason", NotificationPlatformBridge.f != null && System.currentTimeMillis() - NotificationPlatformBridge.f.c < 5000);
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
        wp wpVar = wp.b;
        if (!wpVar.a) {
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            boolean z2 = sharedPreferencesManager.readBoolean("first_run_tos_accepted", false);
            boolean zMfrb03wj = N.Mfrb03wj();
            boolean zT = z7.t();
            if (z2 || zMfrb03wj || zT) {
                if (!z2) {
                    sharedPreferencesManager.l("first_run_tos_accepted", true);
                }
                if (!zMfrb03wj) {
                    N.MSb7o$8Q();
                }
            }
            sk[] skVarArr = {bq.a, bq.c, bq.d, bq.e, bq.f, bq.g, bq.h, bq.i, bq.j, bq.k, bq.l, bq.m, bq.n, bq.o, bq.p, bq.q, bq.r, bq.s, bq.t, bq.u, bq.v, bq.w, bq.x, bq.z, bq.A, bq.B, bq.C, bq.D, bq.E, bq.F, bq.G, bq.H, bq.I, bq.f26J, bq.K, bq.L, bq.M, bq.O, bq.P, bq.Q, bq.R, bq.S, bq.V, bq.U, bq.T, bq.W, bq.X, bq.Y, bq.Z, bq.a0, bq.b0, bq.c0, bq.d0, bq.e0, bq.g0, bq.h0, bq.i0, bq.j0, bq.k0};
            ArrayList arrayList = new ArrayList(59);
            for (int i = 0; i < 59; i++) {
                sk skVar = skVarArr[i];
                Objects.requireNonNull(skVar);
                arrayList.add(skVar);
            }
            CachedFeatureFlags.a(Collections.unmodifiableList(arrayList));
            SharedPreferencesManager.getInstance().l("network_service_warm_up_enabled", N.MV4Nj$t6());
            CachedFeatureFlags.d.getClass();
            SharedPreferencesManager sharedPreferencesManager2 = SharedPreferencesManager.getInstance();
            sk skVar2 = bq.a;
            sharedPreferencesManager2.l("Chrome.Flags.SafeModeEnabled", N.M09VlOh_("SafeModeForCachedFlags"));
            if (CachedFeatureFlags.e == null) {
                CachedFeatureFlags.getReachedCodeProfilerTrialGroup();
            }
            SharedPreferencesManager.getInstance().o("reached_code_profiler_group", N.MdC43qwX("ReachedCodeProfiler"));
            boolean zM09VlOh_ = N.M09VlOh_("ReachedCodeProfiler");
            int iM37SqSAy = N.M37SqSAy("ReachedCodeProfiler", "sampling_interval_us", 0);
            org.chromium.base.library_loader.a aVar2 = org.chromium.base.library_loader.a.l;
            if (zM09VlOh_ && iM37SqSAy == 0) {
                iM37SqSAy = TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE;
            } else if (!zM09VlOh_) {
                iM37SqSAy = 0;
            }
            SharedPreferences sharedPreferences = xz.a.a;
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.remove("reached_code_profiler_enabled");
            editorEdit.putInt("reached_code_sampling_interval", iM37SqSAy).apply();
            sharedPreferences.edit().putBoolean("background_thread_pool_enabled", N.M09VlOh_("BackgroundThreadPool")).apply();
            rk[] rkVarArr = {bj.m, bj.l, bj.n, bj.o, a8.g, StartSurfaceConfiguration.h, FeedPlaceholderLayout.q, mm0.h, ws1.b, pr1.a, pr1.b, pr1.c, n3.w, t30.N, t30.O, t30.P, t30.Q, t30.R, StartSurfaceConfiguration.o, StartSurfaceConfiguration.p, StartSurfaceConfiguration.q, StartSurfaceConfiguration.j, StartSurfaceConfiguration.k, StartSurfaceConfiguration.g, StartSurfaceConfiguration.l, StartSurfaceConfiguration.m, StartSurfaceConfiguration.n, StartSurfaceConfiguration.b, StartSurfaceConfiguration.c, StartSurfaceConfiguration.d, StartSurfaceConfiguration.e, StartSurfaceConfiguration.a, StartSurfaceConfiguration.f, StartSurfaceConfiguration.i, TabContentManager.k, z23.B, oj2.j, oj2.h, oj2.i, oj2.g, oj2.d, oj2.e, oj2.f, oj2.k, oj2.m, oj2.l, oj2.n, oj2.b, oj2.c, jl3.a, Z0};
            ArrayList arrayList2 = new ArrayList(51);
            for (int i2 = 0; i2 < 51; i2++) {
                rk rkVar = rkVarArr[i2];
                Objects.requireNonNull(rkVar);
                arrayList2.add(rkVar);
            }
            Iterator it2 = Collections.unmodifiableList(arrayList2).iterator();
            while (it2.hasNext()) {
                ((rk) it2.next()).a();
            }
            uk ukVar = CachedFeatureFlags.d;
            if (!ukVar.d.getAndSet(true)) {
                int i3 = ukVar.b.get();
                if (i3 == 2 || i3 == 4 || i3 == 3) {
                    SharedPreferencesManager.getInstance().m(1, "Chrome.Flags.CrashStreakBeforeCache");
                } else {
                    int iD = SharedPreferencesManager.getInstance().d(0, "Chrome.Flags.CrashStreakBeforeCache") - 2;
                    SharedPreferencesManager.getInstance().m(iD >= 0 ? iD : 0, "Chrome.Flags.CrashStreakBeforeCache");
                }
                new tk(ukVar, CachedFeatureFlags.b).c(i9.e);
            }
            wpVar.a = true;
        }
        if (this.t0 || Y2()) {
            V2();
        }
    }

    public C j2(hp hpVar) {
        ((s50) pp.d()).getClass();
        return new s50.b();
    }

    public b.a k2() {
        return new d();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public void l() {
        yr0 yr0Var;
        if (bs0.a().c() && !SysUtils.isLowEndDevice() && (yr0Var = this.Q0) != null) {
            int i = yr0Var.a - 1;
            yr0Var.a = i;
            as0 as0Var = yr0Var.b;
            if (as0Var != null && i == 0 && as0Var.g != null) {
                as0Var.e.unbindService(as0Var.c);
                as0Var.g = null;
                as0Var.b.removeCallbacksAndMessages(null);
            }
        }
        super.l();
    }

    public abstract s51 l2();

    public abstract boolean m();

    public nc2 m2() {
        return new nc2(this, null, this.d0, this.G0, this.j0, this.k0, this.l0, this.f0, new rs1(), new rs1(), new rs1(), new rs1(), new ap(0), (fj) this.i0.f, this.O, new d70(), this.K, this.z0, this, new bp(this, 0), this.D, this, new ur(this, 1), new cp(0, this), this.g0, (qq0) E2(), this.y0, this.o0, new dp(this, 0), x2(), new bp(this, 1), new ep(this, 0), this, this, this.M, this.K0, new aq1(), false, this.S0);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public void n0(Intent intent) {
        vq0 vq0Var = this.x0;
        if (vq0Var != null) {
            vq0Var.c(0);
        }
        s51 s51VarF2 = F2();
        s51.c cVar = s51VarF2.e;
        Activity activity = s51.i;
        cVar.b = activity != s51VarF2.g && (activity instanceof ChromeActivity);
        cVar.a = true;
        boolean zM2 = M2();
        org.chromium.chrome.browser.b bVar = this.s0;
        if (bVar.z(intent, false, zM2)) {
            return;
        }
        bVar.w(intent);
    }

    public abstract Pair<? extends fz2, ? extends fz2> n2();

    public abstract v13 o2();

    @Override // android.app.Activity
    public final void onAttachFragment(Fragment fragment) {
        nc2 nc2Var = this.N0;
        if (nc2Var == null) {
            return;
        }
        nc2Var.getClass();
        if (fragment instanceof QrCodeDialog) {
            ((QrCodeDialog) fragment).a(nc2Var.j);
        }
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    @SuppressLint({"NewApi"})
    public final void onDestroy() {
        wp2 wp2Var = this.B0;
        if (wp2Var != null) {
            xp2.a.b(wp2Var);
        }
        zb zbVar = this.S0;
        if (zbVar != null) {
            zbVar.destroy();
        }
        a aVar = this.n0;
        if (aVar != null) {
            aVar.destroy();
            this.n0 = null;
        }
        h61 h61Var = this.z0.f;
        if (h61Var != null) {
            i61.a.b(h61Var);
        }
        aq1<CompositorViewHolder> aq1Var = this.y0;
        if (aq1Var.f()) {
            CompositorViewHolder compositorViewHolder = aq1Var.f;
            if (compositorViewHolder.getLayoutManager() != null) {
                compositorViewHolder.getLayoutManager().p.c(this);
            }
            compositorViewHolder.w();
            aq1Var.r(null);
        }
        S2();
        if (this.E0) {
            CombinedPolicyProvider.a().f.remove(this);
            this.E0 = false;
        }
        TabContentManager tabContentManager = this.p0;
        if (tabContentManager != null) {
            HashSet hashSet = tabContentManager.a;
            if (hashSet != null) {
                hashSet.clear();
            }
            long j = tabContentManager.e;
            if (j != 0) {
                N.MKivVmLp(j);
                tabContentManager.e = 0L;
            }
            this.p0 = null;
        }
        if (this.o0 != null) {
            this.o0 = null;
        }
        vb1 vb1Var = this.h0;
        if (vb1Var.f()) {
            ((org.chromium.chrome.browser.keyboard_accessory.a) vb1Var.f).destroy();
        }
        vb1Var.destroy();
        hj hjVar = this.i0;
        if (hjVar.f()) {
            fj fjVar = (fj) hjVar.f;
            fjVar.z = null;
            qq0 qq0Var = fjVar.y;
            qq0Var.t = null;
            qq0Var.m(null);
            mq0 mq0Var = qq0Var.r;
            if (mq0Var != null) {
                mq0Var.destroy();
            }
            nq0 nq0Var = qq0Var.s;
            if (nq0Var != null) {
                nq0Var.destroy();
            }
            qq0Var.j.clear();
            fj.b bVar = fjVar.w;
            if (bVar != null) {
                bVar.destroy();
            }
            fjVar.f.destroy();
            fj.c cVar = fjVar.j;
            if (cVar != null) {
                cVar.destroy();
            }
        }
        hjVar.destroy();
        org.chromium.chrome.browser.metrics.a aVar2 = this.F0;
        if (aVar2 != null) {
            aVar2.f = false;
            d3 d3Var = aVar2.d;
            if (d3Var != null) {
                d3Var.destroy();
                aVar2.d = null;
            }
            a.C0094a c0094a = aVar2.e;
            if (c0094a != null) {
                org.chromium.base.b<PageLoadMetrics.a> bVar2 = PageLoadMetrics.a;
                Object obj = ThreadUtils.a;
                PageLoadMetrics.a.c(c0094a);
                PageLoadMetrics.b.c(c0094a);
                aVar2.e = null;
            }
            Object obj2 = ThreadUtils.a;
            UmaUtils.a = null;
            this.F0 = null;
        }
        q2();
        this.k0.r(null);
        gk2 gk2Var = this.d0;
        if (gk2Var != null) {
            gk2Var.destroy();
        }
        g23 g23Var = this.f0;
        if (g23Var != null) {
            g23Var.destroy();
        }
        BottomContainer bottomContainer = this.O0;
        if (bottomContainer != null) {
            bottomContainer.destroy();
            this.O0 = null;
        }
        c cVar2 = this.M0;
        if (cVar2 != null) {
            this.O.h.a.remove(cVar2);
            this.M0 = null;
        }
        v73 v73Var = this.T0;
        if (v73Var != null) {
            u73.p.d(v73Var.f);
            this.T0 = null;
        }
        ei2 ei2Var = this.U0;
        if (ei2Var != null) {
            ei2Var.destroy();
            this.U0 = null;
        }
        wv2 wv2Var = this.W0;
        if (wv2Var != null) {
            wv2Var.g.e(wv2Var);
            wv2Var.e.a();
            this.W0 = null;
        }
        this.G0.destroy();
        lp.a().b.remove(this);
        this.c0 = null;
        super.onDestroy();
    }

    @Override // android.app.Activity
    @UsedByReflection
    public void onGetDirectActions(CancellationSignal cancellationSignal, Consumer consumer) {
        if (this.N0.E == null) {
            return;
        }
        consumer.accept(Collections.emptyList());
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onMultiWindowModeChanged(boolean z) {
        if (this.v0) {
            X2(z, false);
            if (!z && ApplicationStatus.c(this) == 3) {
                O2();
                P2();
                vj1.h.getClass();
                N.MJuAVRUC(vj1.o(this));
            }
        }
        super.onMultiWindowModeChanged(z);
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || !S0(menuItem.getItemId(), null)) {
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    @Override // android.app.Activity
    @UsedByReflection
    public void onPerformDirectAction(String str, Bundle bundle, CancellationSignal cancellationSignal, Consumer<Bundle> consumer) {
        if (this.N0.E == null) {
            return;
        }
        consumer.accept(Bundle.EMPTY);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        super.onPictureInPictureModeChanged(z, configuration);
        int i = 0;
        if (!z) {
            vq0 vq0Var = this.x0;
            if (vq0Var != null) {
                vq0Var.c(0);
                return;
            }
            return;
        }
        if (this.x0 == null) {
            this.x0 = new vq0(this, this.G0, (qq0) E2());
        }
        vq0 vq0Var2 = this.x0;
        vq0Var2.getClass();
        Log.i("cr_VideoPersist", "Entered Picture-in-picture.");
        z2 z2Var = vq0Var2.e;
        Tab tab = (Tab) z2Var.f;
        WebContents webContentsC = tab == null ? null : tab.c();
        if (webContentsC == null) {
            return;
        }
        webContentsC.k0(true);
        Tab tab2 = (Tab) z2Var.f;
        InfoBarContainer infoBarContainerD = InfoBarContainer.d(tab2);
        infoBarContainerD.o = true;
        InfoBarContainerView infoBarContainerView = infoBarContainerD.q;
        if (infoBarContainerView != null) {
            infoBarContainerView.setHidden(true);
        }
        LinkedList linkedList = vq0Var2.a;
        linkedList.add(new l91(webContentsC, 19, tab2));
        vq0Var2.b();
        linkedList.add(new uq0(i, SystemClock.elapsedRealtime()));
    }

    @Override // android.app.Activity
    public final void onProvideAssistContent(AssistContent assistContent) {
        Tab tabV2 = v2();
        boolean zN2 = N2();
        String string = null;
        if (!ef3.a.a()) {
            ef3.a(0);
        } else if (zN2) {
            ef3.a(3);
        } else if (tabV2 == null) {
            ef3.a(1);
        } else if (tabV2.isIncognito()) {
            ef3.a(2);
        } else {
            try {
                JSONObject jSONObjectPut = new JSONObject().put("@type", "WebPage").put("url", tabV2.getUrl().j());
                if (N.Mx5ZGJOG(tabV2.c(), false)) {
                    String strMkdg1mTW = N.Mkdg1mTW(tabV2.c());
                    if (TextUtils.isEmpty(strMkdg1mTW)) {
                        ef3.a(5);
                        string = jSONObjectPut.toString();
                    } else {
                        String strMijf24vV = N.Mijf24vV(tabV2.c());
                        if (TextUtils.isEmpty(strMijf24vV)) {
                            ef3.a(6);
                            string = jSONObjectPut.toString();
                        } else {
                            String strMMKf4EpW = N.MMKf4EpW();
                            jSONObjectPut.put("inLanguage", strMijf24vV);
                            if (strMijf24vV.equals(strMkdg1mTW)) {
                                if (!TextUtils.isEmpty(strMMKf4EpW)) {
                                    jSONObjectPut.put("workTranslation", new JSONObject().put("inLanguage", strMMKf4EpW));
                                }
                                ef3.a(8);
                            } else {
                                jSONObjectPut.put("translationOfWork", new JSONObject().put("inLanguage", strMkdg1mTW));
                                ef3.a(9);
                            }
                            string = jSONObjectPut.toString();
                        }
                    }
                } else {
                    ef3.a(4);
                    string = jSONObjectPut.toString();
                }
            } catch (JSONException unused) {
                ef3.a(7);
            }
        }
        if (tabV2 == null || tabV2.isIncognito() || zN2) {
            return;
        }
        assistContent.setWebUri(Uri.parse(tabV2.getUrl().j()));
        if (string != null) {
            assistContent.setStructuredData(string);
        }
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        g9 g9Var = h9.a;
        if (g9Var.d()) {
            Log.i("cr_ChromeActivity", "#onStart, num async tabs: " + g9Var.a.size());
            aq1<CompositorViewHolder> aq1Var = this.y0;
            if (aq1Var.f()) {
                aq1Var.f.v();
            }
        }
        super.onStart();
        T2(true);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        U2(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (pp.e(i)) {
            View viewFindViewById = findViewById(R$id.control_container);
            try {
                e00 e00Var = (e00) viewFindViewById;
                if (e00Var != null) {
                    e00Var.getToolbarResourceAdapter().i.b();
                }
            } catch (ClassCastException e2) {
                Log.e("cr_ChromeActivity", "crbug.com/1236981", e2);
                viewFindViewById.getSourceLayoutResId();
            }
        }
    }

    @Override // android.app.Activity
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        F2().f.a = true;
        d();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Clipboard.getInstance().d(z);
    }

    public fz2 p0(boolean z) {
        if (h2()) {
            return ((gz2) this.g0.f).p0(z);
        }
        throw new IllegalStateException("Attempting to access TabCreator before initialization");
    }

    public abstract void p2();

    @Override // wp2.c
    public final wp2 q0() {
        nc2 nc2Var = this.N0;
        f fVar = nc2Var == null ? null : nc2Var.B;
        return (nc2Var == null || fVar == null || !fVar.m() || fVar.s()) ? this.B0 : this.N0.C;
    }

    public abstract void q2();

    public final boolean r2() {
        int iN1 = AsyncInitializationActivity.N1(this);
        boolean z = (this.J0.smallestScreenWidthDp >= 600) != (iN1 >= 600);
        if (z && Build.VERSION.SDK_INT >= 30) {
            Log.i("cr_ChromeActivity", "Current smallest screen width is: " + iN1);
        }
        return z;
    }

    public final void s2(Tab tab) {
        g3 g3Var = this.O;
        aj3<PwaBottomSheetController> aj3Var = PwaBottomSheetControllerProvider.a;
        if (((PwaBottomSheetController) PwaBottomSheetControllerProvider.a.e(g3Var.r)) == null || !N.Mwi0P9Xp(tab.c(), 3)) {
            AddToHomescreenCoordinator.b(this, this.O, P0(), tab.c(), this.I0);
            sk skVar = bq.a;
            if (N.M09VlOh_("AddToHomescreenIPH")) {
                oj2.t(Profile.b(tab.c())).notifyEvent("add_to_homescreen_dialog_shown");
            }
        }
    }

    @SuppressLint({"MissingInflatedId"})
    public final void t2() {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeActivity.doLayoutInflation", null);
        try {
            ku2 ku2VarC = ku2.C();
            try {
                TraceEvent.q("setContentView(R.layout.main)", null);
                setContentView(R$layout.main);
                TraceEvent.C("setContentView(R.layout.main)");
                if (B2() != -1) {
                    ViewStub viewStub = (ViewStub) findViewById(R$id.control_container_stub);
                    viewStub.setLayoutResource(B2());
                    TraceEvent.q("toolbarContainerStub.inflate", null);
                    viewStub.inflate();
                    TraceEvent.C("toolbarContainerStub.inflate");
                }
                e00 e00Var = (e00) findViewById(R$id.control_container);
                if (e00Var == null) {
                    uh3.l(findViewById(R$id.omnibox_results_container_stub));
                }
                int iI2 = I2();
                if (iI2 != -1 && e00Var != null) {
                    e00Var.b(iI2);
                }
                ku2VarC.close();
                W1();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } catch (Throwable th) {
                try {
                    ku2VarC.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th2;
        }
    }

    public Tab v2() {
        if (h2()) {
            return z7.p(D2());
        }
        return null;
    }

    public int w2() {
        return 0;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public final boolean x0(int i, int i2, Intent intent) {
        return super.x0(i, i2, intent);
    }

    public abstract int x2();

    public Drawable y2() {
        return new ColorDrawable(ii2.f(this));
    }

    @Deprecated
    public final fj z2() {
        hj hjVar = this.i0;
        if (hjVar.f() || !d()) {
            return (fj) hjVar.f;
        }
        throw new IllegalStateException();
    }

    public void S2() {
    }

    public void c3() {
    }

    @Override // defpackage.ie2
    public final void e(int i) {
    }

    public void p1(y51 y51Var) {
    }

    public class d implements b.a {
        public d() {
        }

        @Override // org.chromium.chrome.browser.b.a
        public final long a() {
            return 0L;
        }

        @Override // org.chromium.chrome.browser.b.a
        public final void c(String str) {
            Intent intent = new Intent("android.intent.action.WEB_SEARCH");
            intent.putExtra("query", str);
            LocaleManager.getInstance().b(ChromeActivity.this, new gp(this, 0, intent));
        }

        @Override // org.chromium.chrome.browser.b.a
        public final void b(String str, String str2) {
        }

        @Override // org.chromium.chrome.browser.b.a
        public final void d(LoadUrlParams loadUrlParams, int i, String str, int i2, Intent intent) {
        }
    }
}
