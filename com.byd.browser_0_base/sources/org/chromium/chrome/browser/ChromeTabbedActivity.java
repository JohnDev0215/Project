package org.chromium.chrome.browser;

import J.N;
import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ShortcutManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import defpackage.C0173a;
import defpackage.C0188h0;
import defpackage.a23;
import defpackage.aq1;
import defpackage.ar;
import defpackage.bi1;
import defpackage.bk3;
import defpackage.bq;
import defpackage.bs;
import defpackage.bw;
import defpackage.by1;
import defpackage.c61;
import defpackage.cs;
import defpackage.ct;
import defpackage.cz0;
import defpackage.d61;
import defpackage.d73;
import defpackage.dc3;
import defpackage.dq;
import defpackage.ds;
import defpackage.ds2;
import defpackage.dz0;
import defpackage.e23;
import defpackage.e3;
import defpackage.e31;
import defpackage.ef2;
import defpackage.es;
import defpackage.ey0;
import defpackage.f61;
import defpackage.fb1;
import defpackage.ff3;
import defpackage.fj;
import defpackage.fs;
import defpackage.fz2;
import defpackage.g23;
import defpackage.g3;
import defpackage.g53;
import defpackage.g63;
import defpackage.g80;
import defpackage.g9;
import defpackage.g90;
import defpackage.gb1;
import defpackage.gk2;
import defpackage.gl1;
import defpackage.gs;
import defpackage.gx;
import defpackage.gy0;
import defpackage.gz2;
import defpackage.h23;
import defpackage.h61;
import defpackage.h9;
import defpackage.hq0;
import defpackage.hs;
import defpackage.i92;
import defpackage.ie0;
import defpackage.ip;
import defpackage.is;
import defpackage.iv;
import defpackage.j23;
import defpackage.j31;
import defpackage.ji3;
import defpackage.jk1;
import defpackage.js;
import defpackage.jy2;
import defpackage.k13;
import defpackage.k92;
import defpackage.kj1;
import defpackage.kk1;
import defpackage.ks;
import defpackage.ks2;
import defpackage.ku;
import defpackage.l6;
import defpackage.l61;
import defpackage.lh;
import defpackage.lj;
import defpackage.lk1;
import defpackage.ll;
import defpackage.lr2;
import defpackage.ls;
import defpackage.ls2;
import defpackage.ly2;
import defpackage.m13;
import defpackage.m6;
import defpackage.m7;
import defpackage.mo0;
import defpackage.ms;
import defpackage.ms1;
import defpackage.n3;
import defpackage.n63;
import defpackage.n91;
import defpackage.nc2;
import defpackage.nh0;
import defpackage.nj1;
import defpackage.nr2;
import defpackage.ns;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.or;
import defpackage.os;
import defpackage.p53;
import defpackage.pp;
import defpackage.pr;
import defpackage.ps;
import defpackage.q11;
import defpackage.q2;
import defpackage.q53;
import defpackage.qi3;
import defpackage.qo;
import defpackage.qq0;
import defpackage.qv0;
import defpackage.r2;
import defpackage.r53;
import defpackage.r6;
import defpackage.rh3;
import defpackage.rq0;
import defpackage.rr;
import defpackage.rs1;
import defpackage.ru0;
import defpackage.s51;
import defpackage.s53;
import defpackage.sk;
import defpackage.su1;
import defpackage.t6;
import defpackage.t91;
import defpackage.tr;
import defpackage.ts2;
import defpackage.uj1;
import defpackage.um1;
import defpackage.ur;
import defpackage.v13;
import defpackage.v82;
import defpackage.vj1;
import defpackage.vr;
import defpackage.vt;
import defpackage.wb;
import defpackage.wp2;
import defpackage.wz;
import defpackage.xr;
import defpackage.y13;
import defpackage.y51;
import defpackage.yh1;
import defpackage.yj;
import defpackage.z2;
import defpackage.z7;
import defpackage.z8;
import defpackage.zb;
import defpackage.zb2;
import defpackage.zm0;
import defpackage.zp1;
import defpackage.zy0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.incognito.IncognitoTabLauncher;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.metrics.AndroidSessionDurationsServiceState;
import org.chromium.chrome.browser.multiwindow.MultiInstanceChromeTabbedActivity;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class ChromeTabbedActivity extends ChromeActivity<ip> implements C0188h0.b {
    public static final n3 S1 = new n3();
    public static final Set<String> T1;
    public long A1;
    public boolean B1;
    public boolean C1;
    public Boolean I1;
    public r53 K1;
    public final t6 L1;
    public zb2.a M1;
    public k92 N1;
    public yh1 O1;
    public int P1;
    public UrlBar R1;
    public kj1 b1;
    public ji3 c1;
    public c61 d1;
    public ViewGroup e1;
    public ToolbarControlContainer f1;
    public s53 g1;
    public a23 h1;
    public f i1;
    public c j1;
    public ru0 k1;
    public m13 l1;
    public boolean m1;
    public Boolean n1;
    public LocaleManager o1;
    public m6 p1;
    public rr q1;
    public CompositorViewHolder r1;
    public org.chromium.chrome.browser.accessibility_tab_switcher.b s1;
    public boolean t1;
    public boolean u1;
    public boolean v1;
    public dq w1;
    public Boolean x1;
    public j31 y1;
    public final rs1<dc3> z1 = new rs1<>();
    public final ls2 D1 = new ls2();
    public final rs1<l61> E1 = new rs1<>();
    public final rs1<lr2> F1 = new rs1<>();
    public final rs1<n1> G1 = new rs1<>();
    public final aq1<Tab> H1 = new aq1<>();
    public ll J1 = new ll();
    public final a Q1 = new a();
    public final gb1 a1 = new gb1();

    @UsedByReflection
    public static class Preload extends ChromeTabbedActivity {
        public org.chromium.chrome.browser.a U1;

        @UsedByReflection
        public Preload() {
        }

        @Override // org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.app.ChromeActivity
        public final /* bridge */ /* synthetic */ fz2 C2() {
            return C2();
        }

        @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, defpackage.b71
        public final androidx.lifecycle.g j1() {
            if (this.U1 == null) {
                this.U1 = new org.chromium.chrome.browser.a(this);
            }
            return this.U1;
        }

        @Override // org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.app.ChromeActivity, defpackage.gz2
        public final /* bridge */ /* synthetic */ fz2 p0(boolean z) {
            return p0(z);
        }
    }

    public class a implements cz0 {
        public a() {
        }

        @Override // defpackage.cz0
        public final void a() {
            ChromeTabbedActivity chromeTabbedActivity = ChromeTabbedActivity.this;
            if (chromeTabbedActivity.d()) {
                return;
            }
            if (chromeTabbedActivity.h2() && chromeTabbedActivity.v0) {
                ((a23) chromeTabbedActivity.G2()).g(true).b();
            } else {
                chromeTabbedActivity.finish();
            }
        }

        @Override // defpackage.cz0
        public final boolean b() {
            return ((a23) ChromeTabbedActivity.this.G2()).g(true).getCount() > 0;
        }

        @Override // defpackage.cz0
        public final boolean isActiveModel() {
            return ((a23) ChromeTabbedActivity.this.G2()).g(true).isActiveModel();
        }
    }

    public class b implements e23 {
        public b() {
        }

        @Override // defpackage.e23
        public final void k(TabModel tabModel) {
            ChromeTabbedActivity.f3(ChromeTabbedActivity.this);
        }
    }

    public class c extends h23 {
        public c(a23 a23Var) {
            super(a23Var);
        }

        @Override // defpackage.t13
        public final void V0(Tab tab) {
            d(false);
            ChromeTabbedActivity.f3(ChromeTabbedActivity.this);
        }

        public final void d(boolean z) {
            ChromeTabbedActivity chromeTabbedActivity = ChromeTabbedActivity.this;
            boolean z2 = false;
            if (((a23) chromeTabbedActivity.G2()).p() == 0) {
                if (qv0.e() && !bk3.k(qv0.c())) {
                    chromeTabbedActivity.finish();
                } else if (z) {
                    oj2.U(1, 2, "Android.NTP.Impression");
                }
            }
            boolean z3 = oj2.z(chromeTabbedActivity);
            boolean z4 = chromeTabbedActivity.d1.q(2) || chromeTabbedActivity.d1.q(16);
            boolean z5 = ((a23) chromeTabbedActivity.G2()).p() != 0 && (((a23) chromeTabbedActivity.G2()).r() || ((a23) chromeTabbedActivity.G2()).g(false).getCount() != 0);
            vj1.h.getClass();
            if (vj1.a(chromeTabbedActivity)) {
                SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
                for (Activity activity : ApplicationStatus.b()) {
                    if (activity instanceof ChromeTabbedActivity) {
                        sparseBooleanArray.put(activity.getTaskId(), true);
                    }
                }
                SparseBooleanArray sparseBooleanArrayK = vj1.k();
                int i = 0;
                for (int i2 = 0; i2 < sparseBooleanArrayK.size(); i2++) {
                    if (sparseBooleanArray.get(sparseBooleanArrayK.keyAt(i2)) && sparseBooleanArrayK.valueAt(i2)) {
                        i++;
                    }
                }
                if (i > 1) {
                    z2 = true;
                }
            }
            if (!z3 || z4 || z5 || chromeTabbedActivity.S || z2) {
                return;
            }
            chromeTabbedActivity.finish();
        }

        @Override // defpackage.t13
        public final void f1(Tab tab, int i, int i2, boolean z) {
            if (i == 5 || i == 14 || (i == 17 && !g90.b())) {
                Toast.c(ChromeTabbedActivity.this, R$string.open_in_new_tab_toast, 0).e();
            }
        }

        @Override // defpackage.t13
        public final void h0(Tab tab) {
            d(true);
            ChromeTabbedActivity.f3(ChromeTabbedActivity.this);
        }

        @Override // defpackage.t13
        public final void n0(Tab tab) {
            d(false);
            ChromeTabbedActivity.f3(ChromeTabbedActivity.this);
        }

        @Override // defpackage.t13
        public final void w0(List<Tab> list, boolean z) {
            d(true);
            ChromeTabbedActivity.f3(ChromeTabbedActivity.this);
        }
    }

    public class d extends nh0 {
        public d() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            tab.t(this);
            t6 t6Var = ChromeTabbedActivity.this.L1;
            if (!t6Var.l) {
                t6Var.a(true, false);
            }
            t6Var.k = false;
        }
    }

    public class e implements e23 {
        public e() {
        }

        @Override // defpackage.e23
        public final void g() {
            int iIntValue;
            ChromeTabbedActivity chromeTabbedActivity = ChromeTabbedActivity.this;
            if (chromeTabbedActivity.t1) {
                TabModel tabModelG = chromeTabbedActivity.h1.g(false);
                HashMap map = new HashMap();
                HashMap map2 = new HashMap();
                int i = 0;
                for (int i2 = 0; i2 < tabModelG.getCount(); i2++) {
                    Tab tabAt = tabModelG.getTabAt(i2);
                    String strJ = tabAt.getUrl().j();
                    if (map2.containsKey(strJ)) {
                        i++;
                        iIntValue = ((Integer) map2.get(strJ)).intValue();
                    } else {
                        iIntValue = 0;
                    }
                    map2.put(strJ, Integer.valueOf(iIntValue + 1));
                    int i3 = CriticalPersistedTabData.m(tabAt).p;
                    if (!map.containsKey(Integer.valueOf(i3))) {
                        map.put(Integer.valueOf(i3), new ArrayList());
                    }
                    ((List) map.get(Integer.valueOf(i3))).add(Integer.valueOf(tabAt.getId()));
                }
                int count = tabModelG.getCount();
                if (count != 0 && i < count) {
                    oj2.R(i, "Tabs.Tasks.DuplicatedTab.DuplicatedTabCount");
                    oj2.Z((i * 100) / count, "Tabs.Tasks.DuplicatedTab.DuplicatedTabRatio");
                }
                List list = (List) map.get(-1);
                if (list == null) {
                    return;
                }
                int i4 = 0;
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    int iU0 = n3.u0(((Integer) list.get(i6)).intValue(), map);
                    if (iU0 > 1) {
                        i5 += iU0;
                        i4++;
                    }
                }
                int count2 = tabModelG.getCount();
                if (count2 == 0) {
                    return;
                }
                oj2.R(i4, "Tabs.Tasks.TabGroupCount");
                oj2.R(i5, "Tabs.Tasks.TabsInGroupCount");
                double d = count2;
                oj2.Z((int) (((((double) i5) * 1.0d) / d) * 100.0d), "Tabs.Tasks.TabsInGroupRatio");
                if (i4 != 0) {
                    oj2.R(i5 / i4, "Tabs.Tasks.AverageTabGroupSize");
                }
                oj2.Z((int) (((((double) i4) * 1.0d) / d) * 100.0d), "Tabs.Tasks.TabGroupDensity");
            }
        }
    }

    public class f extends j23 {
        public f(a23 a23Var) {
            super(a23Var);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
            if (navigationHandle.g) {
                TraceEvent traceEventH0 = TraceEvent.H0("updateActiveWebContents", null);
                try {
                    N.MGBuAcfs(tabImpl.g);
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
        }
    }

    public class g implements or.a {
        public g() {
        }
    }

    static {
        Object[] objArr = {ChromeTabbedActivity.class.getName(), MultiInstanceChromeTabbedActivity.class.getName(), ChromeTabbedActivity2.class.getName(), "com.google.android.apps.chrome.Main"};
        HashSet hashSet = new HashSet(4);
        for (int i = 0; i < 4; i++) {
            Object obj = objArr[i];
            Objects.requireNonNull(obj);
            if (!hashSet.add(obj)) {
                throw new IllegalArgumentException("duplicate element: " + obj);
            }
        }
        T1 = Collections.unmodifiableSet(hashSet);
    }

    public ChromeTabbedActivity() {
        r2 r2Var = this.K;
        int i = 1;
        js jsVar = new js(this, i);
        ks ksVar = new ks(this, i);
        ls lsVar = new ls(this, i);
        int i2 = 2;
        this.L1 = new t6(r2Var, jsVar, ksVar, lsVar, new cs(this, i2), new js(this, i2), new ks(this, i2), new su1(new ls(this, i2), this.f0));
    }

    public static r53 d3(ChromeTabbedActivity chromeTabbedActivity) {
        ChromeTabbedActivity chromeTabbedActivity2;
        if (chromeTabbedActivity.K1 == null) {
            g63 g63Var = (g63) chromeTabbedActivity.N0;
            int i = 0;
            if (g63Var.W0 == null) {
                g63Var.W0 = new bw(new lj[0]);
            }
            bw bwVar = g63Var.W0;
            gk2 gk2Var = chromeTabbedActivity.d0;
            nc2 nc2Var = chromeTabbedActivity.N0;
            nw2<EphemeralTabCoordinator> nw2Var = nc2Var.B0;
            ds dsVar = new ds((g63) nc2Var, i);
            org.chromium.components.browser_ui.bottomsheet.f fVar = nc2Var.B;
            fj fjVarZ2 = chromeTabbedActivity.z2();
            rq0 rq0VarE2 = chromeTabbedActivity.E2();
            g23 g23Var = chromeTabbedActivity.f0;
            aq1<CompositorViewHolder> aq1Var = chromeTabbedActivity.y0;
            aq1<bi1> aq1Var2 = chromeTabbedActivity.D;
            ks ksVar = new ks(chromeTabbedActivity, 5);
            fj fjVarZ22 = chromeTabbedActivity.z2();
            z2 z2Var = chromeTabbedActivity.G0;
            r2 r2Var = chromeTabbedActivity.K;
            g3 g3Var = chromeTabbedActivity.O;
            ls lsVar = new ls(chromeTabbedActivity, 7);
            vr vrVar = new vr(chromeTabbedActivity, 1);
            j31 j31Var = chromeTabbedActivity.y1;
            org.chromium.chrome.browser.toolbar.g gVar = chromeTabbedActivity.N0.x;
            Objects.requireNonNull(gVar);
            qq0 qq0Var = (qq0) rq0VarE2;
            chromeTabbedActivity2 = chromeTabbedActivity;
            chromeTabbedActivity2.K1 = new r53(chromeTabbedActivity, bwVar, gk2Var, nw2Var, dsVar, fVar, chromeTabbedActivity, fjVarZ2, qq0Var, chromeTabbedActivity, g23Var, aq1Var, aq1Var2, ksVar, fjVarZ22, z2Var, r2Var, g3Var, lsVar, vrVar, j31Var, new es(gVar, 0));
        } else {
            chromeTabbedActivity2 = chromeTabbedActivity;
        }
        return chromeTabbedActivity2.K1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void e3(ChromeTabbedActivity chromeTabbedActivity, LoadUrlParams loadUrlParams, String str, boolean z, Intent intent) {
        if (chromeTabbedActivity.m1 && !bk3.k(loadUrlParams.a)) {
            chromeTabbedActivity.m3().t(1, false);
            org.chromium.chrome.browser.toolbar.g gVar = chromeTabbedActivity.N0.x;
            if (gVar.v0) {
                gVar.k.m();
            }
        }
        Log.i("ChromeTabbedActivity", "launchIntent: ");
        if (!q11.g(intent)) {
            if (i92.A1()) {
                if ((q11.k("org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT", -1, intent.getExtras()) != -1) != false) {
                    Bundle extras = intent.getExtras();
                    int iK = q11.k("org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT", -1, extras);
                    extras.remove("org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT");
                    if (iK != -1 && chromeTabbedActivity.h1 != null) {
                        chromeTabbedActivity.C2().j(new LoadUrlParams(loadUrlParams.a, 0), 0, chromeTabbedActivity.h1.s(iK), null);
                        return;
                    }
                }
            }
            or orVarP0 = chromeTabbedActivity.p0(false);
            boolean zEquals = TextUtils.equals(str, orVarP0.a.getPackageName());
            if (z || zEquals) {
                orVarP0.j(loadUrlParams, !zEquals ? 1 : 0, null, intent);
                return;
            }
            if (str == null) {
                str = "com.google.android.apps.chrome.unknown_app";
            }
            for (int i = 0; i < orVarP0.d.getCount(); i++) {
                Tab tabAt = orVarP0.d.getTabAt(i);
                jy2 jy2Var = (jy2) tabAt.B().b(jy2.class);
                if (str.equals(jy2Var != null ? jy2Var.f : null)) {
                    jy2.d(orVarP0.i(loadUrlParams, 1, null, i, intent)).f = str;
                    orVarP0.d.m(tabAt, false, false);
                    return;
                }
            }
            jy2.d(orVarP0.j(loadUrlParams, 1, null, intent)).f = str;
            return;
        }
        boolean zI = q11.i(intent, "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", false);
        Integer num = (Integer) q11.o(intent, "org.chromium.chrome.browser.tab_launch_type");
        if (num == null) {
            num = q11.i(intent, "com.android.chrome.invoked_from_shortcut", false) ? 7 : q11.i(intent, "com.android.chrome.invoked_from_app_widget", false) ? 15 : IncognitoTabLauncher.a(intent) ? 10 : 0;
        }
        or orVarP02 = chromeTabbedActivity.p0(zI);
        Tab tabJ = orVarP02.j(loadUrlParams, num.intValue(), null, intent);
        List list = (List) q11.o(intent, "org.chromium.chrome.browser.additional_urls");
        boolean zI2 = q11.i(intent, "org.chromium.chrome.browser.open_additional_urls_in_tab_group", false);
        if (list != null) {
            Tab tab = zI2 ? tabJ : null;
            int i2 = zI2 ? 14 : 3;
            for (int i3 = 0; i3 < list.size(); i3++) {
                String str2 = (String) list.get(i3);
                LoadUrlParams loadUrlParams2 = new LoadUrlParams(loadUrlParams.a, 0);
                loadUrlParams2.b = loadUrlParams.b;
                loadUrlParams2.c = loadUrlParams.c;
                loadUrlParams2.d = loadUrlParams.d;
                loadUrlParams2.e = loadUrlParams.e;
                if (loadUrlParams.f != null) {
                    loadUrlParams2.f = new HashMap(loadUrlParams.f);
                }
                loadUrlParams2.g = loadUrlParams.g;
                loadUrlParams2.h = loadUrlParams.h;
                loadUrlParams2.i = loadUrlParams.i;
                loadUrlParams2.j = loadUrlParams.j;
                loadUrlParams2.k = loadUrlParams.k;
                loadUrlParams2.l = loadUrlParams.l;
                loadUrlParams2.m = loadUrlParams.m;
                loadUrlParams2.n = loadUrlParams.n;
                loadUrlParams2.o = loadUrlParams.o;
                loadUrlParams2.p = loadUrlParams.p;
                loadUrlParams2.q = loadUrlParams.q;
                loadUrlParams2.r = loadUrlParams.r;
                loadUrlParams2.s = loadUrlParams.s;
                loadUrlParams2.a = str2;
                orVarP02.b(i2, tab, loadUrlParams2);
            }
        }
    }

    public static void f3(ChromeTabbedActivity chromeTabbedActivity) {
        if (chromeTabbedActivity.S && chromeTabbedActivity.d1 != null && chromeTabbedActivity.h2()) {
            boolean zI = oj2.I(chromeTabbedActivity);
            boolean zQ = chromeTabbedActivity.d1.q(2);
            boolean z = ((a23) chromeTabbedActivity.G2()).p() != 0 && (((a23) chromeTabbedActivity.G2()).r() || ((a23) chromeTabbedActivity.G2()).g(false).getCount() != 0);
            if (!zI || zQ || z) {
                return;
            }
            chromeTabbedActivity.d1.t(2, false);
        }
    }

    public static boolean h3(Tab tab) {
        if (!tab.isInitialized()) {
            return false;
        }
        int launchType = tab.getLaunchType();
        return launchType == 0 || launchType == 1 || launchType == 18 || launchType == 4 || launchType == 16 || launchType == 5 || launchType == 14 || launchType == 17 || (launchType == 3 && CriticalPersistedTabData.m(tab).p != -1);
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final int A2() {
        return R$dimen.control_container_height;
    }

    public final void A3(boolean z) {
        boolean z2;
        if (this.v1) {
            return;
        }
        this.v1 = true;
        boolean zN2 = N2();
        t6 t6Var = this.L1;
        if (z && !zN2) {
            this.B1 = true;
            B3(false);
            G3(5, 0);
            z2 = this.B1 && !R1();
            if (t6Var.l) {
                t6Var.a(false, z2);
            }
            t6Var.l = false;
            return;
        }
        if (v2() == null && !zN2) {
            this.B1 = true;
            B3(false);
            G3(5, 0);
        }
        if (q11.f(getIntent()) && N2()) {
            n3.X0("MobileStartup.UserEnteredTabSwitcher");
        }
        z2 = this.B1 && !R1();
        if (t6Var.l) {
            t6Var.a(false, z2);
        }
        t6Var.l = false;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final int B2() {
        return R$layout.control_container;
    }

    public final void B3(boolean z) {
        if (z) {
            org.chromium.chrome.browser.metrics.a aVar = this.F0;
            aVar.c = 0;
            aVar.f = true;
        } else {
            org.chromium.chrome.browser.metrics.a aVar2 = this.F0;
            if (aVar2.f) {
                aVar2.f = false;
            }
        }
        ks2.e = z;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, defpackage.e7
    public final m7 C0() {
        return new q53(this, this.G0, this.L, (a23) G2(), this.N0.x, getWindow().getDecorView(), this.E1, zb2.d(this) ? this.F1 : null, this.k0, new gx(5, this), P0(), q0(), this.N0.u);
    }

    public final void C3() {
        if (this.S) {
            return;
        }
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.setupCompositorContentPreNativeForPhone", null);
        try {
            CompositorViewHolder compositorViewHolder = this.y0.f;
            if (oj2.z(this)) {
                k3(compositorViewHolder, compositorViewHolder);
            }
            ViewGroup viewGroup = this.e1;
            rs1<lr2> rs1Var = this.F1;
            rs1<n1> rs1Var2 = this.G1;
            aq1<TabContentManager> aq1Var = this.o0;
            nc2 nc2Var = this.N0;
            Objects.requireNonNull(nc2Var);
            d61 d61Var = new d61(compositorViewHolder, viewGroup, rs1Var, rs1Var2, aq1Var, new bs(nc2Var, 0), this.y1);
            this.d1 = d61Var;
            this.E1.a(d61Var);
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

    /* JADX WARN: Type inference failed for: r13v0, types: [as] */
    /* JADX WARN: Type inference failed for: r8v0, types: [zr] */
    public final void D3() {
        if (this.S) {
            TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.setupCompositorContentPreNativeForTablet", null);
            try {
                CompositorViewHolder compositorViewHolder = this.y0.f;
                if (oj2.z(this) && !CachedFeatureFlags.b(ar.d.b("GridTabSwitcherForTablets:delay_creation"), true)) {
                    i3();
                }
                ViewGroup viewGroup = (ViewGroup) findViewById(R$id.custom_grid_tab_switcher_view_holder);
                ViewGroup viewGroup2 = this.e1;
                rs1<lr2> rs1Var = this.F1;
                rs1<n1> rs1Var2 = this.G1;
                aq1<TabContentManager> aq1Var = this.o0;
                final nc2 nc2Var = this.N0;
                Objects.requireNonNull(nc2Var);
                f61 f61Var = new f61(compositorViewHolder, viewGroup2, rs1Var, rs1Var2, aq1Var, new nw2() { // from class: zr
                    @Override // defpackage.nw2
                    public final Object get() {
                        return nc2Var.v;
                    }
                }, this.y1, viewGroup, this.N0.D, this.K, new Callable() { // from class: as
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        n3 n3Var = ChromeTabbedActivity.S1;
                        return this.a.i3();
                    }
                });
                this.d1 = f61Var;
                this.E1.a(f61Var);
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
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final bi1 E1() {
        bi1 bi1VarE1 = super.E1();
        int i = 0;
        this.l1 = new m13(this, this.K, bi1VarE1, new cs(this, i), new js(this, i), new ks(this, i), this.f0, new ls(this, i), new cs(this, 1), this.S0);
        return bi1VarE1;
    }

    public final boolean E3() {
        if (this.x1 == null) {
            this.x1 = Boolean.valueOf(this.s0.z(getIntent(), true, false));
        }
        return this.x1.booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean F3() {
        /*
            Method dump skipped, instruction units count: 532
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.F3():boolean");
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void G0() {
        try {
            TraceEvent.q("ChromeTabbedActivity.initializeCompositor", null);
            super.G0();
            LocaleManager localeManager = LocaleManager.getInstance();
            this.o1 = localeManager;
            localeManager.a.d = S1;
            localeManager.b(this, null);
            this.g1.d(this.p0);
            this.k1 = new ru0(this.h1.g(false));
            if (oj2.I(this)) {
                this.h1.m(new b());
            }
            this.j1 = new c(this.h1);
        } finally {
            TraceEvent.C("ChromeTabbedActivity.initializeCompositor");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void G3(int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.G3(int, int):void");
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final int I2() {
        return this.S ? R$layout.toolbar_tablet : R$layout.toolbar_phone;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void K() {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.finishNativeInitialization", null);
        try {
            super.K();
            this.r1 = this.y0.f;
            this.s1 = this.d1.Q;
            H2().b(this.r1);
            H2().b(this.s1);
            qo.f().a(this.d1);
            if (this.S) {
                qo.f().a(this.r1);
            }
            if (zb.b()) {
                p3();
            }
            dq dqVar = this.w1;
            long jCurrentTimeMillis = System.currentTimeMillis();
            dqVar.getClass();
            dq.a(jCurrentTimeMillis);
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

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void K2() {
        super.K2();
        g80.b().a(new ns(this, 4));
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void M1() {
        super.M1();
        if (zb2.d(this) && R1() && !this.T) {
            TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.prepareToShowStartPagePreNative", null);
            try {
                C3();
                this.y0.f.setLayoutManager(this.d1);
                if (F3()) {
                    this.d1.X(this.h1);
                    this.n1 = Boolean.valueOf(g90.a(this));
                    A3(true);
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
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final boolean N2() {
        c61 c61Var = this.d1;
        return c61Var != null && (c61Var.q(2) || this.d1.q(16));
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ad A[RETURN] */
    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean S1(android.content.Intent r7) {
        /*
            r6 = this;
            r0 = 0
            r6.P1 = r0
            android.os.Bundle r1 = r6.P
            java.lang.String r2 = "org.chromium.chrome.browser.window_id"
            r3 = -1
            int r2 = defpackage.q11.l(r7, r2, r3)
            boolean r4 = defpackage.q11.g(r7)
            if (r4 == 0) goto L13
            goto L14
        L13:
            r2 = r3
        L14:
            if (r1 == 0) goto L26
            java.lang.String r4 = "window_index"
            boolean r5 = r1.containsKey(r4)
            if (r5 == 0) goto L26
            int r7 = r1.getInt(r4, r0)
            r6.P1 = r7
            goto L3c
        L26:
            kj1 r1 = r6.b1
            if (r1 == 0) goto L3c
            java.lang.String r1 = "com.android.chrome.prefer_new"
            boolean r7 = defpackage.q11.i(r7, r1, r0)
            kj1 r1 = r6.b1
            int r4 = r6.getTaskId()
            int r7 = r1.e(r2, r4, r7)
            r6.P1 = r7
        L3c:
            int r7 = r6.P1
            r1 = 1
            if (r7 != r3) goto L52
            java.lang.String r7 = "cr_ChromeTabbedActivity"
            java.lang.String r2 = "Window ID not allocated. Finishing the activity"
            android.util.Log.i(r7, r2)
            int r7 = org.chromium.chrome.R$string.max_number_of_windows
            org.chromium.ui.widget.Toast r6 = org.chromium.ui.widget.Toast.c(r6, r7, r1)
            r6.e()
            return r0
        L52:
            kj1 r7 = r6.b1
            if (r7 == 0) goto Lae
            int r6 = r6.getTaskId()
            r7.l = r6
            int r2 = defpackage.kj1.s
            if (r2 == 0) goto L64
            if (r2 == r6) goto L64
            r6 = r1
            goto L65
        L64:
            r6 = r0
        L65:
            boolean r2 = r7.k()
            if (r2 == 0) goto L9d
            int r2 = defpackage.kj1.s
            if (r2 != 0) goto L70
            goto L9d
        L70:
            android.app.Activity r7 = r7.g
            java.lang.String r2 = "activity"
            java.lang.Object r7 = r7.getSystemService(r2)
            android.app.ActivityManager r7 = (android.app.ActivityManager) r7
            java.util.List r7 = r7.getAppTasks()
            java.util.Iterator r7 = r7.iterator()
        L82:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L9d
            java.lang.Object r2 = r7.next()
            android.app.ActivityManager$AppTask r2 = (android.app.ActivityManager.AppTask) r2
            android.app.ActivityManager$RecentTaskInfo r2 = defpackage.k5.c(r2)
            if (r2 != 0) goto L95
            goto L82
        L95:
            int r2 = r2.id
            int r3 = defpackage.kj1.s
            if (r2 != r3) goto L82
            r7 = r1
            goto L9e
        L9d:
            r7 = r0
        L9e:
            if (r6 == 0) goto La6
            if (r7 == 0) goto La6
            defpackage.kj1.s = r0
            r6 = r0
            goto Lab
        La6:
            if (r7 != 0) goto Laa
            defpackage.kj1.s = r0
        Laa:
            r6 = r1
        Lab:
            if (r6 != 0) goto Lae
            return r0
        Lae:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.S1(android.content.Intent):boolean");
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void S2() {
        lk1 lk1Var;
        um1 um1Var;
        um1.a aVar;
        zb2.a aVar2 = this.M1;
        if (aVar2 != null) {
            aVar2.destroy();
            this.M1 = null;
        }
        k92 k92Var = this.N1;
        if (k92Var != null) {
            k92Var.destroy();
            this.N1 = null;
        }
        yh1 yh1Var = this.O1;
        if (yh1Var != null) {
            yh1Var.destroy();
            this.O1 = null;
        }
        ll llVar = this.J1;
        if (llVar != null) {
            llVar.a();
            this.J1 = null;
        }
        f fVar = this.i1;
        if (fVar != null) {
            fVar.destroy();
            this.i1 = null;
        }
        ru0 ru0Var = this.k1;
        if (ru0Var != null) {
            ru0Var.e.l(ru0Var);
        }
        c cVar = this.j1;
        if (cVar != null) {
            cVar.a();
        }
        ji3 ji3Var = this.c1;
        if (ji3Var != null) {
            TabModel tabModelG = ji3Var.e.g(false);
            if (tabModelG != null) {
                tabModelG.l(ji3Var.f);
            }
            this.c1 = null;
        }
        m6 m6Var = this.p1;
        if (m6Var != null) {
            l6 l6Var = m6Var.b;
            if (l6Var != null) {
                l6Var.destroy();
            }
            this.p1 = null;
        }
        rs1<lr2> rs1Var = this.F1;
        if (rs1Var.get() != null) {
            rs1Var.get().destroy();
        }
        ls2 ls2Var = this.D1;
        if (ls2Var != null) {
            ls2Var.destroy();
        }
        j31 j31Var = this.y1;
        if (j31Var != null) {
            e31 e31Var = j31Var.a;
            e31Var.g.getClass();
            ApplicationStatus.i(e31Var);
            e31Var.a();
            hq0 hq0Var = e31Var.e;
            hq0Var.b.set(false);
            e31Var.c();
            Activity activity = e31Var.i.get();
            if (activity != null) {
                activity.getWindow().removeOnFrameMetricsAvailableListener(hq0Var);
            }
            e31Var.h.e = true;
            this.y1 = null;
        }
        dz0.a().a.remove(this.Q1);
        if (H2() != null) {
            H2().e(this.r1);
            H2().e(this.s1);
        }
        if (this.S) {
            qo qoVarF = qo.f();
            CompositorViewHolder compositorViewHolder = this.r1;
            if (qoVarF.c == null) {
                qoVarF.c = new org.chromium.base.b<>();
            }
            qoVarF.c.c(compositorViewHolder);
        }
        qo qoVarF2 = qo.f();
        if (qoVarF2.c == null) {
            qoVarF2.c = new org.chromium.base.b<>();
        }
        qoVarF2.c.c(this);
        qo qoVarF3 = qo.f();
        c61 c61Var = this.d1;
        if (qoVarF3.c == null) {
            qoVarF3.c = new org.chromium.base.b<>();
        }
        qoVarF3.c.c(c61Var);
        r53 r53Var = this.K1;
        if (r53Var != null && (lk1Var = r53Var.w) != null && (um1Var = lk1Var.n) != null && (aVar = um1Var.e) != null) {
            um1Var.a.l(aVar);
        }
        t6 t6Var = this.L1;
        r6 r6Var = t6Var.b;
        q2 q2Var = t6Var.a;
        q2Var.e(r6Var);
        q2Var.e(t6Var.c);
        zy0 zy0Var = t6Var.j;
        zy0Var.c.e(zy0Var.h);
        zp1<y13> zp1Var = zy0Var.b;
        zp1Var.d(zy0Var.j);
        if (zp1Var.get() != null) {
            zp1Var.get().l(zy0Var.i);
        }
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public int T1(Intent intent, Bundle bundle) {
        if (bundle != null && h9.a.d()) {
            return 0;
        }
        if (getClass().equals(ChromeTabbedActivity.class) && "android.intent.action.MAIN".equals(intent.getAction())) {
            return new org.chromium.chrome.browser.c(this, intent).f();
        }
        int iT3 = t3(intent, 1);
        if (iT3 != 0) {
            return iT3;
        }
        return 0;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void W2(long j) {
        super.W2(j);
        oj2.T("MobileStartup.IntentToCreationTime.TabbedMode", j, 1L, 30000L, 50);
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, on1.a
    public final void X0() {
        Tab tabV2;
        if (!this.V && (tabV2 = v2()) != null) {
            tabV2.a();
        }
        super.X0();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void Y1() {
        super.Y1();
        aq1<v13> aq1Var = this.e0;
        uj1 uj1Var = this.L;
        r2 r2Var = this.K;
        this.b1 = vj1.p() ? new nj1(this, aq1Var, uj1Var, r2Var, this.D, this) : new kj1(this, aq1Var, uj1Var, r2Var, this);
        if (ds2.h) {
            ds2.a.a.getClass();
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void Z1(Configuration configuration) {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.performOnConfigurationChanged", null);
        try {
            super.Z1(configuration);
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

    /* JADX WARN: Type inference failed for: r6v0, types: [qs] */
    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void a2() {
        super.a2();
        if (isFinishing()) {
            return;
        }
        mo0 mo0VarA = mo0.a();
        mo0VarA.a.getClass();
        mo0VarA.c("ChromeTabbedActivity");
        a23 a23Var = (a23) G2();
        a23Var.h(new gy0(a23Var));
        a23Var.h(new ey0());
        getWindow().setSoftInputMode(19);
        this.e1 = (ViewGroup) findViewById(R.id.content);
        this.f1 = (ToolbarControlContainer) findViewById(R$id.control_container);
        new zm0(this.e1, new wz(2)).a(true);
        int i = 3;
        this.c1 = new ji3(this, this.h1, new wp2.c() { // from class: qs
            @Override // wp2.c
            public final wp2 q0() {
                return this.e.q0();
            }
        }, oj2.D(this) ? new js(this, i) : null);
        this.w1 = new dq();
        new g53(this.K, a23Var);
        aq1<h61> aq1Var = this.z0;
        boolean z = this.S;
        if (DeviceFormFactor.a(this) && bq.e0.a()) {
            new qi3(a23Var, aq1Var, z);
        }
        boolean zF3 = F3();
        if (zF3) {
            ds2.a.a.g = true;
        }
        ks2 ks2Var = new ks2(this.O, this.N, z2(), (a23) G2(), zF3, new ls(this, i));
        this.D1.r(ks2Var);
        org.chromium.chrome.browser.metrics.a aVar = this.F0;
        aVar.getClass();
        ks2Var.d.a(new e3(aVar));
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void b2() {
        this.y1 = new j31(this);
        super.b2();
        if (!org.chromium.base.library_loader.a.l.d()) {
            B3(true);
        }
        A1().x(10);
        dz0.a().a.add(this.Q1);
        this.D1.s(this.O.r);
    }

    @Override // defpackage.nj
    public final void c0() {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.startNativeInitialization", null);
        try {
            TraceEvent traceEventH02 = TraceEvent.H0("ChromeTabbedActivity.setupCompositorContentPostNative", null);
            try {
                int i = 1;
                if (!(this.d1 != null)) {
                    if (this.S) {
                        D3();
                    } else {
                        C3();
                    }
                }
                this.d1.W = g90.b();
                if (traceEventH02 != null) {
                    traceEventH02.close();
                }
                sk skVar = bq.a;
                if (!N.M09VlOh_("SplitCompositorTask") || (DeviceFormFactor.isTablet() && !N.M6bsIDpc("SplitCompositorTask", "enable_on_tablet", false))) {
                    q3();
                } else {
                    PostTask.c(rh3.a, this.J1.c(new rr(this, i)));
                }
                d73 d73Var = rh3.a;
                PostTask.c(d73Var, this.J1.c(new ms(this, i)));
                PostTask.b(d73Var, this.J1.c(new ns(this, i)), 0L);
                PostTask.b(d73Var, this.J1.c(new os(this, i)), 0L);
                int i2 = 2;
                PostTask.b(d73Var, this.J1.c(new rr(this, i2)), 0L);
                PostTask.b(d73Var, this.J1.c(new ms(this, i2)), 0L);
                PostTask.b(d73Var, this.J1.c(new ns(this, i2)), 0L);
                qo.f().a(this);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } catch (Throwable th) {
                if (traceEventH02 != null) {
                    try {
                        traceEventH02.close();
                    } catch (Throwable unused) {
                    }
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

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void c3() {
        ((a23) G2()).g(true).b();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Boolean boolG = n3.G(keyEvent, this.m1, (qq0) E2(), this);
        return boolG != null ? boolG.booleanValue() : super.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0090  */
    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f() {
        /*
            Method dump skipped, instruction units count: 630
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.f():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00ad  */
    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g0() {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.g0():void");
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, defpackage.y6
    public final boolean g1() {
        if (!this.m1) {
            return false;
        }
        Tab tabV2 = v2();
        if (tabV2 != null) {
            ly2 ly2VarA = ly2.a(tabV2);
            Object objB = Boolean.FALSE;
            if (ly2VarA.e.containsKey("isTabModalDialogShowing")) {
                objB = ly2VarA.b("isTabModalDialogShowing");
            }
            if (((Boolean) objB).booleanValue()) {
                return false;
            }
        }
        return super.g1();
    }

    public final void g3() {
        rs1<lr2> rs1Var = this.F1;
        Tab tabV2 = rs1Var.f() && rs1Var.get().B() ? null : v2();
        Tab tab = (Tab) this.G0.f;
        rs1<l61> rs1Var2 = this.E1;
        d();
        if (tabV2 != null && h3(tabV2)) {
            jy2.f(tabV2);
        }
        if (tab != null && h3(tab)) {
            jy2.f(tab);
        }
        l61 l61Var = rs1Var2.get();
        if (l61Var == null) {
            return;
        }
        l61Var.d();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public final void h() {
        this.h1.c();
        try {
            N.M6wmuchs();
        } catch (RuntimeException e2) {
            Log.e("CookiesFetcher", "persistCookies: " + e2);
        }
        t91 t91Var = this.o1.a;
        t91Var.getClass();
        t91Var.b = new WeakReference<>(null);
        this.o1.a.getClass();
        N.MnSIHeV3();
        dq dqVar = this.w1;
        long jCurrentTimeMillis = System.currentTimeMillis();
        dqVar.getClass();
        SharedPreferencesManager.getInstance().n(jCurrentTimeMillis, "ChromeTabbedActivity.BackgroundTimeMs");
        super.h();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.chromium.chrome.browser.app.ChromeActivity, defpackage.jf1
    public final boolean h1(int i, boolean z) {
        Tab tabV2 = v2();
        int i2 = 0;
        Object[] objArr = tabV2 != null && bk3.l(tabV2.getUrl());
        if (i == R$id.new_tab_menu_id) {
            ((a23) G2()).g(false).c();
            n3.X0("MobileMenuNewTab");
            n3.X0("MobileNewTabOpened");
            zb2.g("Chrome.StartSurface.OpenNewTabPageCount");
            w3(false);
            if (z) {
                n3.X0("MobileMenuNewTab.AppMenu");
            }
            p0(false).f(2);
            this.o1.b(this, null);
        } else if (i == R$id.new_incognito_tab_menu_id) {
            if (N.M$3vpOHw()) {
                ((a23) G2()).g(false).c();
                n3.X0("MobileMenuNewIncognitoTab");
                n3.X0("MobileNewTabOpened");
                zb2.g("Chrome.StartSurface.OpenNewTabPageCount");
                w3(true);
                if (z) {
                    n3.X0("MobileMenuNewIncognitoTab.AppMenu");
                }
                p0(true).f(2);
                oj2.t(Profile.d()).notifyEvent("app_menu_new_incognito_tab_clicked");
            }
        } else if (i == R$id.all_bookmarks_menu_id) {
            this.y0.f.p(new ms(this, i2));
            if (objArr != false) {
                um1.a(6);
            }
            oj2.U(D2().isIncognito() ? 1 : 0, 6, "Bookmarks.OpenBookmarkManager.PerProfileType");
            n3.X0("MobileMenuAllBookmarks");
        } else if (i == R$id.recent_tabs_menu_id) {
            LoadUrlParams loadUrlParams = new LoadUrlParams("chrome-native://recent-tabs/", 2);
            boolean zN2 = N2();
            if (zN2 && !this.S && zb2.d(this)) {
                zb2.b(loadUrlParams, false, Boolean.valueOf(D2().isIncognito()), null);
            } else if (tabV2 != null) {
                tabV2.b(loadUrlParams);
            } else {
                p0(D2().isIncognito()).b(2, null, loadUrlParams);
            }
            if (zN2) {
                this.d1.t(1, true);
            }
            if (objArr != false) {
                um1.a(4);
            }
            n3.X0("MobileMenuRecentTabs");
            zb2.g("Chrome.StartSurface.OpenRecentTabCount");
        } else if (i == R$id.close_tab) {
            D2().m(tabV2, true, true);
            n3.X0("MobileTabClosed");
        } else {
            int i3 = R$id.close_all_tabs_menu_id;
            aq1<bi1> aq1Var = this.D;
            if (i == i3) {
                ku.a(this, aq1Var, new ns(this, i2), false);
                n3.X0("MobileMenuCloseAllTabs");
            } else if (i == R$id.close_all_incognito_tabs_menu_id) {
                ku.a(this, aq1Var, new os(this, i2), true);
                n3.X0("MobileMenuCloseAllIncognitoTabs");
            } else if (i == R$id.focus_url_bar) {
                if (!N2() && (!this.S || D2().getCount() != 0)) {
                    i2 = 1;
                }
                if (i2 != 0) {
                    this.N0.x.v(11, true);
                }
            } else if (i == R$id.open_recently_closed_tab) {
                TabModel tabModelZ = this.h1.z();
                if (!tabModelZ.isIncognito()) {
                    tabModelZ.d();
                }
                n3.X0("MobileTabClosedUndoShortCut");
            } else {
                if (i != R$id.quick_delete_menu_id || !v82.b.a()) {
                    return super.h1(i, z);
                }
                oj2.U(0, 2, "Privacy.QuickDelete");
                P0();
                new v82(q0()).a.getClass();
            }
        }
        return true;
    }

    public final ViewGroup i3() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        CompositorViewHolder compositorViewHolder = this.y0.f;
        ViewGroup viewGroup = (ViewGroup) ((ViewStub) findViewById(R$id.grid_tab_switcher_view_holder_stub)).inflate();
        int i = 0;
        int i2 = 1;
        if (viewGroup != null) {
            if (n3.Q0()) {
                viewGroup.setLayoutDirection(1);
            } else if (n3.O0()) {
                viewGroup.setLayoutDirection(0);
            } else {
                viewGroup.setLayoutDirection(3);
            }
        }
        if (viewGroup == null) {
            Log.i("cr_ChromeTabbedActivity", "addOrDeleteOnClickListener----->containerView is null");
        } else if (G2() == null) {
            Log.i("cr_ChromeTabbedActivity", "tabModelSelector is null");
        } else {
            n63 n63Var = new n63(this, (a23) G2());
            LinearLayout linearLayout = (LinearLayout) viewGroup.findViewById(R$id.ll_add_site_landscape);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new ie0(i2, n63Var));
            }
            LinearLayout linearLayout2 = (LinearLayout) viewGroup.findViewById(R$id.ll_add_site_vertical);
            int i3 = 2;
            if (linearLayout2 != null) {
                linearLayout2.setOnClickListener(new by1(i3, n63Var));
            }
            LinearLayout linearLayout3 = (LinearLayout) viewGroup.findViewById(R$id.ll_delete_page_landscape);
            if (linearLayout3 != null) {
                linearLayout3.setOnClickListener(new vt(i3, n63Var));
            }
            LinearLayout linearLayout4 = (LinearLayout) viewGroup.findViewById(R$id.ll_delete_page_vertical);
            if (linearLayout4 != null) {
                linearLayout4.setOnClickListener(new gs(i, n63Var));
            }
        }
        org.chromium.chrome.browser.toolbar.g gVar = this.N0.x;
        gVar.getClass();
        ViewStub viewStub = (ViewStub) viewGroup.findViewById(R$id.fullscreen_tab_switcher_toolbar_stub);
        org.chromium.chrome.browser.toolbar.top.h hVar = gVar.k.b;
        if (hVar != null) {
            hVar.b = viewStub;
        }
        k3(compositorViewHolder, viewGroup);
        oj2.b0(SystemClock.uptimeMillis() - jUptimeMillis, "Android.TabSwitcher.CreationTime");
        return viewGroup;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public final void j() {
        gb1 gb1Var = this.a1;
        gb1Var.getClass();
        if (!gb1.c) {
            gb1.c = true;
            ApplicationStatus.e(new fb1());
        }
        if (!gb1.b) {
            ThreadUtils.c().postDelayed(gb1Var.a, 10000L);
        }
        super.j();
        if (!this.u1 && !this.C1) {
            A3(F3());
        }
        Bundle bundle = this.P;
        if (bundle != null && bundle.getBoolean("is_incognito_selected", false)) {
            Profile profileF = D2().f().f(true);
            long j = bundle.getLong("incognito_session_startup_time", -1L);
            if (j != -1) {
                N.MG2ZhetJ(profileF, new AndroidSessionDurationsServiceState(j, bundle.getLong("incognito_session_last_reported_duration", -1L)));
            }
        }
        this.P = null;
        zb2.m();
        new PrefChangeRegistrar().a("ntp_snippets.list_visible", new PrefChangeRegistrar.a() { // from class: xb2
            @Override // org.chromium.chrome.browser.preferences.PrefChangeRegistrar.a
            public final void f() {
                zb2.m();
            }
        });
        this.w1.getClass();
        long jE = SharedPreferencesManager.getInstance().e(-1L, "ChromeTabbedActivity.BackgroundTimeMs");
        if ((jE != -1 ? System.currentTimeMillis() - jE : -1L) > lh.a) {
            SharedPreferencesManager.getInstance().removeKey("enhanced_bookmark_last_used_url");
        }
    }

    public final void j3(CompositorViewHolder compositorViewHolder, ViewGroup viewGroup) {
        nc2 nc2Var = this.N0;
        ef2 ef2Var = nc2Var.D;
        org.chromium.components.browser_ui.bottomsheet.f fVar = nc2Var.B;
        rs1<lr2> rs1Var = this.F1;
        aq1<Tab> aq1Var = this.H1;
        boolean z = this.T;
        g3 g3Var = this.O;
        Objects.requireNonNull(compositorViewHolder);
        hs hsVar = new hs(0, compositorViewHolder);
        y13 y13VarG2 = G2();
        fj fjVarZ2 = z2();
        wp2 wp2VarQ0 = q0();
        gk2 gk2Var = this.d0;
        org.chromium.chrome.browser.toolbar.g gVar = this.N0.x;
        Objects.requireNonNull(gVar);
        is isVar = new is(gVar, 0);
        TabContentManager tabContentManager = this.p0;
        bi1 bi1VarP0 = P0();
        r2 r2Var = this.K;
        gz2 gz2Var = (gz2) this.g0.f;
        uj1 uj1Var = this.L;
        j31 j31Var = this.y1;
        org.chromium.chrome.browser.toolbar.g gVar2 = this.N0.x;
        Objects.requireNonNull(gVar2);
        int i = 1;
        new nr2(this, ef2Var, fVar, rs1Var, aq1Var, z, g3Var, viewGroup, hsVar, (a23) y13VarG2, fjVarZ2, wp2VarQ0, gk2Var, isVar, tabContentManager, bi1VarP0, this, r2Var, gz2Var, this, uj1Var, j31Var, new es(gVar2, i), this.S0, this.N0.u, new tr(this, i));
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final b.a k2() {
        return new h();
    }

    public final void k3(CompositorViewHolder compositorViewHolder, ViewGroup viewGroup) {
        if (!r3()) {
            if (oj2.z(this)) {
                j3(compositorViewHolder, viewGroup);
                return;
            }
            return;
        }
        rs1<n1> rs1Var = this.G1;
        k13 k13VarR = z7.r();
        r2 r2Var = this.K;
        y13 y13VarG2 = G2();
        TabContentManager tabContentManager = this.p0;
        fj fjVarZ2 = z2();
        gz2 gz2Var = (gz2) this.g0.f;
        uj1 uj1Var = this.L;
        ef2 ef2Var = this.N0.D;
        Objects.requireNonNull(compositorViewHolder);
        rs1Var.a(k13VarR.e(this, r2Var, y13VarG2, tabContentManager, fjVarZ2, gz2Var, this, viewGroup, uj1Var, ef2Var, viewGroup, new fs(compositorViewHolder, 0), q0(), P0(), this.N0.u, this.S0));
        if (zb2.d(this)) {
            j3(compositorViewHolder, viewGroup);
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public final void l() {
        super.l();
        y3();
        this.v1 = false;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final s51 l2() {
        return new p53(this);
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    /* JADX INFO: renamed from: l3, reason: merged with bridge method [inline-methods] */
    public final or C2() {
        return (or) super.C2();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final boolean m() {
        boolean z;
        RenderFrameHost renderFrameHostU;
        org.chromium.chrome.browser.toolbar.g gVar;
        boolean z2;
        if (!this.m1) {
            n3.X0("SystemBackBeforeUINativeInitialized");
            return false;
        }
        if (yj.a() && (gVar = this.N0.x) != null) {
            if (gVar.r.get().booleanValue()) {
                gVar.v(12, false);
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                zb.c(9);
                return true;
            }
        }
        pr prVar = this.l1.q;
        if ((prVar == null || prVar.f == null) ? false : true) {
            prVar.d(5);
            z = true;
        } else {
            z = false;
        }
        if (z) {
            zb.c(10);
            return true;
        }
        rs1<lr2> rs1Var = this.F1;
        Tab tabV2 = rs1Var.f() && rs1Var.get().B() ? null : v2();
        if (tabV2 == null) {
            zb.c(17);
            yh1.d(0);
            g3();
            moveTaskToBack(true);
            return true;
        }
        WebContents webContentsC = tabV2.c();
        if (webContentsC != null && (renderFrameHostU = webContentsC.U()) != null && renderFrameHostU.k()) {
            zb.c(12);
            return true;
        }
        if (this.N0.x.c0.a()) {
            zb.c(14);
            return true;
        }
        if (!N2() && !this.S && zb2.f(tabV2)) {
            x3();
            zb.c(15);
            return true;
        }
        if (tabV2.getLaunchType() == 18) {
            lh.d(null, new BookmarkId(2, 0L), tabV2.isIncognito());
            zb.c(16);
            if (webContentsC != null) {
                webContentsC.I();
            }
            return true;
        }
        jk1 jk1VarU = tabV2.u();
        if (jk1VarU != null) {
            jk1VarU.o();
        }
        boolean zH3 = h3(tabV2);
        boolean z3 = !zH3 || jy2.f(tabV2);
        zb.c(17);
        g3();
        if (!z3) {
            if (!zH3) {
                return false;
            }
            yh1.d(1);
            if (webContentsC != null) {
                webContentsC.I();
            }
            return true;
        }
        if (zH3) {
            yh1.d(2);
            z3(tabV2);
            return true;
        }
        yh1.d(0);
        z3(null);
        return true;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final nc2 m2() {
        int i = 4;
        return new g63(this, new wb(1, this), this.d0, this.G0, this.j0, this.k0, this.l0, this.f0, this.F1, this.G1, this.z1, this.E1, this.H1, z2(), this.O, this.y1, this.K, this.z0, this, new ks(this, 3), this.D, this, new ur(this, 0), new vr(this, 0), this.g0, (qq0) E2(), this.y0, this.o0, new js(this, i), new ks(this, i), new ls(this, 5), this, this, new aq1(), this.M, R$dimen.control_container_height, this.A0, new n91(1, this), this.K0, org.chromium.chrome.browser.b.q(getIntent().getExtras()), this.S0);
    }

    public final c61 m3() {
        return (c61) this.y0.f.getLayoutManager();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity
    public final boolean moveTaskToBack(boolean z) {
        try {
            return super.moveTaskToBack(z);
        } catch (NullPointerException unused) {
            this.finish();
            return true;
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public final void n0(Intent intent) {
        try {
            TraceEvent.q("ChromeTabbedActivity.onNewIntentWithNative", null);
            super.n0(intent);
            if (q11.f(intent)) {
                s3();
            }
            if (iv.e().g("enable-test-intents")) {
                o3(intent);
            }
        } finally {
            TraceEvent.C("ChromeTabbedActivity.onNewIntentWithNative");
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final Pair<or, or> n2() {
        g gVar = zb2.d(this) ? new g() : null;
        g3 g3Var = this.O;
        ls lsVar = new ls(this, 6);
        g9 g9Var = h9.a;
        g23 g23Var = this.f0;
        aq1<CompositorViewHolder> aq1Var = this.y0;
        g gVar2 = gVar;
        return Pair.create(new or(this, g3Var, lsVar, false, gVar2, g9Var, g23Var, aq1Var), new or(this, this.O, new cs(this, 3), true, gVar2, g9Var, g23Var, aq1Var));
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, defpackage.gz2
    /* JADX INFO: renamed from: n3, reason: merged with bridge method [inline-methods] */
    public final or p0(boolean z) {
        return (or) super.p0(z);
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final v13 o2() {
        kj1 kj1Var = this.b1;
        s53 s53Var = new s53(kj1Var != null && kj1Var.k());
        this.g1 = s53Var;
        return s53Var;
    }

    public final void o3(Intent intent) {
        if ("com.google.android.apps.chrome.ACTION_CLOSE_TABS".equals(intent.getAction())) {
            ((a23) G2()).e(false);
            return;
        }
        String action = intent.getAction();
        Object obj = ThreadUtils.a;
        if ("org.chromium.base.ACTION_LOW_MEMORY".equals(action)) {
            getApplication().onLowMemory();
            onLowMemory();
            return;
        }
        if ("org.chromium.base.ACTION_TRIM_MEMORY".equals(action)) {
            getApplication().onTrimMemory(80);
            onTrimMemory(80);
        } else if ("org.chromium.base.ACTION_TRIM_MEMORY_RUNNING_CRITICAL".equals(action)) {
            getApplication().onTrimMemory(15);
            onTrimMemory(15);
        } else if ("org.chromium.base.ACTION_TRIM_MEMORY_MODERATE".equals(action)) {
            getApplication().onTrimMemory(60);
            onTrimMemory(60);
        }
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.onActivityResult", null);
        try {
            super.onActivityResult(i, i2, intent);
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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        boolean z;
        TabModel tabModelD2 = D2();
        if (tabModelD2 == null || tabModelD2.getCount() != 0) {
            z = false;
        } else {
            tabModelD2.c();
            C2().f(19);
            z = true;
        }
        if (z) {
            return;
        }
        super.onBackPressed();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!this.m1) {
            return super.onKeyDown(i, keyEvent);
        }
        int i2 = 0;
        if (i != 4 || this.S || ((qq0) E2()).g()) {
            return n3.S0(keyEvent, !N2() && (!this.S || D2().getCount() != 0), true, (a23) G2(), this, this.N0.x) || super.onKeyDown(i, keyEvent);
        }
        if (this.q1 == null) {
            this.q1 = new rr(this, i2);
        }
        this.I.postDelayed(this.q1, ViewConfiguration.getLongPressTimeout());
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4 && !this.S) {
            this.I.removeCallbacks(this.q1);
            this.q1 = null;
            if (keyEvent.getEventTime() - keyEvent.getDownTime() >= ViewConfiguration.getLongPressTimeout()) {
                org.chromium.components.browser_ui.bottomsheet.f fVar = this.N0.B;
                if (fVar != null && (fVar.h() instanceof gl1) && fVar.m()) {
                    return true;
                }
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.onNewIntent", null);
        try {
            Intent intent2 = new Intent(intent);
            intent2.addFlags(268435456);
            if (t3(intent2, 2) != 0) {
                moveTaskToBack(true);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                    return;
                }
                return;
            }
            this.A1 = SystemClock.uptimeMillis();
            super.onNewIntent(intent);
            if (zb2.d(this) && D2().getCount() > 0 && !this.S && !F3() && !N2()) {
                rs1<lr2> rs1Var = this.F1;
                if (rs1Var.get() != null) {
                    rs1Var.get().y(0, 0);
                }
            }
            if (q11.i(intent, "org.chromium.chrome.browser.open_regular_overview_mode", false) && q11.g(intent)) {
                this.h1.d(false);
                this.d1.t(2, false);
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

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onPause() {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.onPause", null);
        try {
            super.onPause();
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

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
        ArrayList arrayList = new ArrayList();
        KeyboardShortcutGroup keyboardShortcutGroup = new KeyboardShortcutGroup(getString(R$string.keyboard_shortcut_tab_group_header));
        n3.j(this, keyboardShortcutGroup, R$string.keyboard_shortcut_open_new_tab, 42, 4096);
        n3.j(this, keyboardShortcutGroup, R$string.keyboard_shortcut_reopen_new_tab, 48, 4097);
        n3.j(this, keyboardShortcutGroup, R$string.keyboard_shortcut_new_incognito_tab, 42, 4097);
        n3.j(this, keyboardShortcutGroup, R$string.keyboard_shortcut_next_tab, 61, 4096);
        n3.j(this, keyboardShortcutGroup, R$string.keyboard_shortcut_prev_tab, 61, 4097);
        n3.j(this, keyboardShortcutGroup, R$string.keyboard_shortcut_close_tab, 51, 4096);
        arrayList.add(keyboardShortcutGroup);
        KeyboardShortcutGroup keyboardShortcutGroup2 = new KeyboardShortcutGroup(getString(R$string.keyboard_shortcut_chrome_feature_group_header));
        n3.j(this, keyboardShortcutGroup2, R$string.keyboard_shortcut_open_menu, 33, 2);
        n3.j(this, keyboardShortcutGroup2, R$string.keyboard_shortcut_bookmark_manager, 30, 4097);
        n3.j(this, keyboardShortcutGroup2, R$string.keyboard_shortcut_history_manager, 36, 4096);
        n3.j(this, keyboardShortcutGroup2, R$string.keyboard_shortcut_find_bar, 34, 4096);
        n3.j(this, keyboardShortcutGroup2, R$string.keyboard_shortcut_address_bar, 40, 4096);
        arrayList.add(keyboardShortcutGroup2);
        KeyboardShortcutGroup keyboardShortcutGroup3 = new KeyboardShortcutGroup(getString(R$string.keyboard_shortcut_webpage_group_header));
        n3.j(this, keyboardShortcutGroup3, R$string.keyboard_shortcut_print_page, 44, 4096);
        n3.j(this, keyboardShortcutGroup3, R$string.keyboard_shortcut_reload_page, 46, 4096);
        n3.j(this, keyboardShortcutGroup3, R$string.keyboard_shortcut_reload_no_cache, 46, 4097);
        n3.j(this, keyboardShortcutGroup3, R$string.keyboard_shortcut_bookmark_page, 32, 4096);
        n3.j(this, keyboardShortcutGroup3, R$string.keyboard_shortcut_zoom_in, 70, 4096);
        n3.j(this, keyboardShortcutGroup3, R$string.keyboard_shortcut_zoom_out, 69, 4096);
        n3.j(this, keyboardShortcutGroup3, R$string.keyboard_shortcut_reset_zoom, 7, 4096);
        n3.j(this, keyboardShortcutGroup3, R$string.keyboard_shortcut_help_center, 76, 4097);
        arrayList.add(keyboardShortcutGroup3);
        list.addAll(arrayList);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        Log.i("cr_ChromeTabbedActivity", "onResume start");
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.onResume", null);
        try {
            super.onResume();
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            if (this.R1 == null) {
                this.R1 = (UrlBar) findViewById(R$id.url_bar);
            }
            UrlBar urlBar = this.R1;
            if (urlBar != null) {
                urlBar.clearFocus();
            }
            Log.i("cr_ChromeTabbedActivity", "onResume end");
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

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        byte[] encoded;
        byte[] bArr;
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.onSaveInstanceState", null);
        try {
            super.onSaveInstanceState(bundle);
            ct.b bVarB = ct.c.a.b(false);
            if (bVarB != null && (encoded = bVarB.a.getEncoded()) != null && (bArr = bVarB.b) != null) {
                bundle.putByteArray("org.chromium.content.browser.crypto.CipherFactory.KEY", encoded);
                bundle.putByteArray("org.chromium.content.browser.crypto.CipherFactory.IV", bArr);
            }
            bundle.putInt("window_index", oj2.r().b(this));
            Boolean boolValueOf = Boolean.valueOf(D2().isIncognito());
            bundle.putBoolean("is_incognito_selected", boolValueOf.booleanValue());
            if (boolValueOf.booleanValue() && ProfileManager.b) {
                AndroidSessionDurationsServiceState androidSessionDurationsServiceState = (AndroidSessionDurationsServiceState) N.M2YjxH3n(D2().f());
                bundle.putLong("incognito_session_startup_time", androidSessionDurationsServiceState.getSessionStartTime());
                bundle.putLong("incognito_session_last_reported_duration", androidSessionDurationsServiceState.getLastReportedDuration());
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

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStart() {
        Log.i("cr_ChromeTabbedActivity", "onStart start");
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.onStart", null);
        try {
            super.onStart();
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            Log.i("cr_ChromeTabbedActivity", "onStart end");
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

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.onStop", null);
        try {
            super.onStop();
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

    @Override // org.chromium.chrome.browser.app.ChromeActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (!pp.e(i)) {
            return;
        }
        kk1 kk1Var = kk1.b;
        int i2 = 0;
        while (true) {
            ArrayList<WeakReference<Tab>> arrayList = kk1Var.a;
            if (i2 >= arrayList.size()) {
                arrayList.clear();
                return;
            }
            Tab tab = arrayList.get(i2).get();
            if (tab != null) {
                tab.w();
            }
            i2++;
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, defpackage.ie2
    public final void p1(y51 y51Var) {
        y51Var.getClass();
        if (y51Var instanceof ts2) {
            return;
        }
        this.h1.getClass();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0076  */
    @Override // org.chromium.chrome.browser.app.ChromeActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p2() {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.p2():void");
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [wr] */
    public final void p3() {
        zb2.a aVar = this.M1;
        int i = 3;
        zb zbVar = this.S0;
        z2 z2Var = this.G0;
        if (aVar == null && !this.S) {
            zb2.a aVar2 = new zb2.a(z2Var, new ns(this, i));
            this.M1 = aVar2;
            zbVar.a(aVar2, 15);
        }
        if (this.N1 == null && !this.S) {
            k92 k92Var = new k92(z2Var);
            this.N1 = k92Var;
            zbVar.a(k92Var, 16);
        }
        if (this.O1 == null) {
            yh1 yh1Var = new yh1(z2Var, new Predicate() { // from class: wr
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    this.a.getClass();
                    return ChromeTabbedActivity.h3((Tab) obj);
                }
            }, new xr(0, this), new ms(this, i));
            this.O1 = yh1Var;
            zbVar.a(yh1Var, 17);
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void q2() {
        s53 s53Var = this.g1;
        if (s53Var != null) {
            s53Var.b();
        }
    }

    public final void q3() {
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.initializeCompositorContent", null);
        try {
            T(this.d1, findViewById(R$id.url_bar), this.f1);
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

    public final boolean r3() {
        if (this.I1 == null) {
            this.I1 = Boolean.valueOf(zb2.e(this));
        }
        return this.I1.booleanValue();
    }

    public final void s3() {
        ll llVar;
        sk skVar = bq.a;
        if (N.M09VlOh_("ReengagementNotification") && (llVar = this.J1) != null) {
            new ms1(this.j0, llVar.b(new ps(0)));
        }
        this.w1.getClass();
        long jE = SharedPreferencesManager.getInstance().e(-1L, "ChromeTabbedActivity.BackgroundTimeMs");
        long jCurrentTimeMillis = jE != -1 ? System.currentTimeMillis() - jE : -1L;
        gb1 gb1Var = this.a1;
        gb1Var.getClass();
        n3.X0("MobileStartup.MainIntentReceived");
        if (jCurrentTimeMillis >= 86400000) {
            n3.X0("MobileStartup.MainIntentReceived.After24Hours");
        } else if (jCurrentTimeMillis >= 43200000) {
            n3.X0("MobileStartup.MainIntentReceived.After12Hours");
        } else if (jCurrentTimeMillis >= 21600000) {
            n3.X0("MobileStartup.MainIntentReceived.After6Hours");
        } else if (jCurrentTimeMillis >= 3600000) {
            n3.X0("MobileStartup.MainIntentReceived.After1Hour");
        }
        gb1Var.a(true);
    }

    public final int t3(Intent intent, int i) {
        if (!getClass().equals(ChromeTabbedActivity.class) || !"android.intent.action.VIEW".equals(intent.getAction()) || intent.getComponent() == null || !"com.google.android.apps.chrome.Main".equals(intent.getComponent().getClassName())) {
            return 0;
        }
        org.chromium.chrome.browser.c cVar = new org.chromium.chrome.browser.c(this, intent);
        int i2 = (org.chromium.chrome.browser.c.g(cVar.b) && cVar.h(new org.chromium.chrome.browser.b(this, cVar))) ? 1 : 0;
        if (i2 != 0 || org.chromium.chrome.browser.b.c(intent) != 5 || (getApplicationInfo().flags & 2) == 0 || iv.e().g("dont-crash-on-view-main-intents")) {
            return i2;
        }
        String string = intent.toString();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            StringBuilder sbK = C0173a.k(string, ", extras.keySet = [");
            sbK.append(TextUtils.join(", ", extras.keySet()));
            sbK.append("]");
            string = sbK.toString();
        }
        throw new IllegalStateException(String.format(null, "VIEW intent sent to .Main activity alias was not dispatched. PLEASE report the following info to crbug.com/789732: \"%s\". Use --%s flag to disable this check.", string, "dont-crash-on-view-main-intents"));
    }

    public final void u3() {
        Boolean bool;
        if (this.m1) {
            boolean zA = g90.a(this);
            if (N2() && ((bool = this.n1) == null || bool.booleanValue() != g90.a(this))) {
                rs1<lr2> rs1Var = this.F1;
                if (rs1Var.get() == null || !rs1Var.get().B() || !zb2.l(this, D2().isIncognito(), this.S)) {
                    this.d1.t(1, true);
                    if (((a23) G2()).z().getCount() == 0) {
                        C2().f(2);
                    }
                }
            }
            this.n1 = Boolean.valueOf(zA);
        }
    }

    public final void v3() {
        Profile profile = (Profile) this.j0.f;
        if (profile != null) {
            oj2.t(profile).notifyEvent("tab_switcher_button_clicked");
        }
        if (((qq0) E2()).g()) {
            return;
        }
        boolean zN2 = N2();
        Tab tabI = ((a23) G2()).i();
        if (zN2) {
            n3.Z0(2, 4);
        } else if (tabI != null && !tabI.isIncognito() && bk3.l(tabI.getUrl())) {
            n3.Z0(1, 4);
        }
        G3(4, 0);
    }

    public final void w3(boolean z) {
        ((ShortcutManager) getSystemService(ShortcutManager.class)).reportShortcutUsed(z ? "new-incognito-tab-shortcut" : "new-tab-shortcut");
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final int x2() {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void x3() {
        /*
            r3 = this;
            org.chromium.chrome.browser.tab.Tab r0 = r3.v2()
            boolean r1 = r0.isIncognito()
            r2 = 0
            if (r1 == 0) goto L10
            a23 r1 = r3.h1
            r1.d(r2)
        L10:
            ds2 r1 = defpackage.ds2.a(r0)
            if (r1 != 0) goto L18
            r1 = r2
            goto L1a
        L18:
            boolean r1 = r1.e
        L1a:
            if (r1 != 0) goto L2e
            ds2 r1 = defpackage.ds2.a(r0)
            if (r1 != 0) goto L24
            r1 = r2
            goto L26
        L24:
            boolean r1 = r1.f
        L26:
            if (r1 == 0) goto L29
            goto L2e
        L29:
            r1 = 7
            r3.G3(r1, r2)
            goto L37
        L2e:
            r1 = 6
            r3.G3(r1, r2)
            java.lang.String r1 = "FromTab"
            defpackage.zb2.h(r1)
        L37:
            boolean r1 = r0.n()
            if (r1 == 0) goto L3e
            return
        L3e:
            boolean r1 = r0.isIncognito()
            if (r1 != 0) goto L56
            ds2 r1 = defpackage.ds2.a(r0)
            if (r1 != 0) goto L4b
            goto L4d
        L4b:
            boolean r2 = r1.e
        L4d:
            if (r2 != 0) goto L62
            int r1 = r0.getLaunchType()
            r2 = 3
            if (r1 == r2) goto L62
        L56:
            c61 r1 = r3.m3()
            ss r2 = new ss
            r2.<init>(r3, r0)
            r1.j(r2)
        L62:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.x3():void");
    }

    @Override // defpackage.C0188h0.b
    public final void y0(boolean z) {
        u3();
    }

    public final void y3() {
        this.g1.f();
        boolean z = false;
        Tab tabP = z7.p(this.h1.g(false));
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        if (tabP != null && N.M$l72hrq(tabP.getUrl().j())) {
            z = true;
        }
        sharedPreferencesManager.l("Chrome.StartSurface.IsLastVisitedTabSRP", z);
    }

    public final void z3(Tab tab) {
        Log.i("cr_ChromeTabbedActivity", "sendToBackground(): " + tab);
        moveTaskToBack(true);
        if (tab != null) {
            this.I.postDelayed(new z8(this, 5, tab), 500L);
        }
    }

    public class h implements b.a {
        public h() {
        }

        @Override // org.chromium.chrome.browser.b.a
        public final long a() {
            return ChromeTabbedActivity.this.A1;
        }

        @Override // org.chromium.chrome.browser.b.a
        public final void b(String str, String str2) {
            Tab tabV2 = ChromeTabbedActivity.this.v2();
            if (tabV2 == null) {
                ff3.a(2);
                return;
            }
            if (tabV2.isIncognito()) {
                ff3.a(3);
                return;
            }
            if (str2 == null || !str2.equals(tabV2.getUrl().j())) {
                ff3.a(4);
                return;
            }
            if (!n3.r(tabV2, false)) {
                ff3.a(5);
                return;
            }
            if (str == null || TextUtils.isEmpty(str)) {
                N.M0540rIu(tabV2.c());
            } else {
                N.Mm4YgQEb(tabV2.c(), str);
            }
            ff3.a(6);
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x0097  */
        @Override // org.chromium.chrome.browser.b.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void d(org.chromium.content_public.browser.LoadUrlParams r11, int r12, java.lang.String r13, int r14, android.content.Intent r15) {
            /*
                Method dump skipped, instruction units count: 602
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.h.d(org.chromium.content_public.browser.LoadUrlParams, int, java.lang.String, int, android.content.Intent):void");
        }

        @Override // org.chromium.chrome.browser.b.a
        public final void c(String str) {
        }
    }
}
