package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.features.start_surface.StartSurfaceConfiguration;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: ReturnToChromeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zb2 {
    public static boolean a;

    /* JADX INFO: compiled from: ReturnToChromeUtil.java */
    public static class a implements vb, w80 {
        public final aq1<Boolean> e;
        public final Runnable f;
        public final yb2 g;
        public final z2 h;

        public a(z2 z2Var, ns nsVar) {
            aq1<Boolean> aq1Var = new aq1<>();
            this.e = aq1Var;
            this.h = z2Var;
            this.g = new yb2(this, z2Var);
            this.f = nsVar;
            Tab tab = (Tab) z2Var.f;
            aq1Var.r(Boolean.valueOf(tab != null && zb2.f(tab)));
        }

        @Override // defpackage.w80
        public final void destroy() {
            this.g.destroy();
        }

        @Override // defpackage.vb
        public final zp1<Boolean> getHandleBackPressChangedSupplier() {
            return this.e;
        }

        @Override // defpackage.vb
        public final int o() {
            Tab tab = (Tab) this.h.f;
            int i = (tab == null || tab.g() || !zb2.f(tab)) ? 0 : 1;
            this.f.run();
            return i ^ 1;
        }
    }

    public static String a(String str) {
        str.getClass();
        switch (str) {
            case "open_new_tab":
            case "model_open_new_tab":
                return "Chrome.StartSurface.OpenNewTabPageCount";
            case "model_mv_tiles":
            case "mv_tiles":
                return "Chrome.StartSurface.TapMVTilesCount";
            case "feeds":
            case "model_feeds":
                return "Chrome.StartSurface.TapFeedCardsCount";
            case "open_recent_tabs":
            case "model_open_recent_tabs":
                return "Chrome.StartSurface.OpenRecentTabCount";
            case "open_history":
            case "model_open_history":
                return "Chrome.StartSurface.OpenHistoryCount";
            default:
                return null;
        }
    }

    public static Tab b(LoadUrlParams loadUrlParams, boolean z, Boolean bool, Tab tab) {
        TraceEvent traceEventH0 = TraceEvent.H0("StartSurface.LoadUrl", null);
        try {
            Tab tabC = c(loadUrlParams, null, null, z, bool, tab);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return tabC;
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

    /* JADX WARN: Removed duplicated region for block: B:13:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.chrome.browser.tab.Tab c(org.chromium.content_public.browser.LoadUrlParams r4, java.lang.String r5, byte[] r6, boolean r7, java.lang.Boolean r8, org.chromium.chrome.browser.tab.Tab r9) {
        /*
            java.lang.String r0 = r4.a
            android.app.Activity r1 = org.chromium.base.ApplicationStatus.c
            r2 = 0
            if (r1 == 0) goto L20
            boolean r3 = d(r1)
            if (r3 == 0) goto L20
            boolean r3 = r1 instanceof org.chromium.chrome.browser.app.ChromeActivity
            if (r3 != 0) goto L12
            goto L20
        L12:
            org.chromium.chrome.browser.app.ChromeActivity r1 = (org.chromium.chrome.browser.app.ChromeActivity) r1
            boolean r3 = r1.N2()
            if (r3 != 0) goto L21
            boolean r0 = defpackage.bk3.k(r0)
            if (r0 != 0) goto L21
        L20:
            r1 = r2
        L21:
            if (r1 != 0) goto L24
            return r2
        L24:
            if (r8 != 0) goto L2f
            org.chromium.chrome.browser.tabmodel.TabModel r8 = r1.D2()
            boolean r8 = r8.isIncognito()
            goto L33
        L2f:
            boolean r8 = r8.booleanValue()
        L33:
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            r2 = 1
            if (r0 != 0) goto L55
            if (r6 == 0) goto L55
            int r0 = r6.length
            if (r0 == 0) goto L55
            java.lang.String r0 = "Content-Type: "
            java.lang.String r5 = defpackage.C0175b.j(r0, r5)
            r4.g = r5
            byte[] r5 = J.N.MugoAW_d(r6)
            org.chromium.content_public.common.ResourceRequestBody r5 = org.chromium.content_public.common.ResourceRequestBody.createFromEncodedNativeForm(r5)
            r4.i = r5
            if (r5 == 0) goto L55
            r4.c = r2
        L55:
            fz2 r5 = r1.p0(r8)
            r6 = 5
            if (r7 == 0) goto L5e
            r8 = r6
            goto L60
        L5e:
            r8 = 12
        L60:
            org.chromium.chrome.browser.tab.Tab r5 = r5.b(r8, r9, r4)
            if (r7 == 0) goto L69
            defpackage.ds2.c(r5)
        L69:
            int r4 = r4.d
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r2) goto L71
            if (r4 != r6) goto L88
        L71:
            java.lang.String r4 = "MobileOmniboxUse.StartSurface"
            defpackage.n3.X0(r4)
            r4 = 2
            defpackage.n3.Z0(r4, r2)
            java.lang.String r4 = "MobileOmniboxUse"
            defpackage.n3.X0(r4)
            org.chromium.chrome.browser.locale.LocaleManager r4 = org.chromium.chrome.browser.locale.LocaleManager.getInstance()
            t91 r4 = r4.a
            r4.getClass()
        L88:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.zb2.c(org.chromium.content_public.browser.LoadUrlParams, java.lang.String, byte[], boolean, java.lang.Boolean, org.chromium.chrome.browser.tab.Tab):org.chromium.chrome.browser.tab.Tab");
    }

    public static boolean d(Context context) {
        if (StartSurfaceConfiguration.a()) {
            return ((qo.f().c() && (!bq.W.a() || !oj2.C(context))) || DeviceFormFactor.a(context)) ? false : true;
        }
        return false;
    }

    public static boolean e(Context context) {
        return bq.U.a() && oj2.z(context);
    }

    public static boolean f(Tab tab) {
        if (tab.getLaunchType() != 12) {
            ds2 ds2VarA = ds2.a(tab);
            if (!(ds2VarA == null ? false : ds2VarA.f)) {
                return false;
            }
        }
        return true;
    }

    public static void g(String str) {
        String strC = StartSurfaceConfiguration.h.c();
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        if (TextUtils.equals("all", strC) || TextUtils.equals(a(strC), str)) {
            SharedPreferencesManager.getInstance().m(SharedPreferencesManager.getInstance().d(0, str) + 1, str);
        }
    }

    public static void h(String str) {
        n3.X0("StartSurface.ShownFromBackNavigation.".concat(str));
    }

    public static void i(boolean z) {
        long jC = (((long) (z ? StartSurfaceConfiguration.j.c() : StartSurfaceConfiguration.k.c())) * 86400000) + System.currentTimeMillis();
        SharedPreferencesManager.getInstance().l("Chrome.StartSurface.ShownOnStartup", z);
        SharedPreferencesManager.getInstance().n(jC, "Chrome.StartSurface.ShownOnStartupDecisionMs");
    }

    public static boolean j(Context context) {
        return bq.S.a() && !SharedPreferencesManager.getInstance().readBoolean("Chrome.Feed.ArticlesListVisible", true) && d(context);
    }

    public static boolean k(AppCompatActivity appCompatActivity) {
        return d(appCompatActivity) && CachedFeatureFlags.b(ar.d.b("StartSurfaceAndroid:open_start_as_homepage"), false) && n();
    }

    public static boolean l(Context context, boolean z, boolean z2) {
        if (!z && !z2 && d(context)) {
            if (!CachedFeatureFlags.b(ar.d.b("StartSurfaceAndroid:open_ntp_instead_of_start"), true)) {
                return true;
            }
        }
        return false;
    }

    public static void m() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        boolean z = zk0.b;
        sharedPreferencesManager.l("Chrome.Feed.ArticlesListVisible", N.MpwNAobK() && gq2.l("ntp_snippets.list_visible"));
    }

    public static boolean n() {
        String strC = qv0.c();
        return qv0.e() && rv0.a().g && (TextUtils.isEmpty(strC) || bk3.f(strC));
    }
}
