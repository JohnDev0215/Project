package defpackage;

import J.N;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.animation.OvershootInterpolator;
import com.github.benmanes.caffeine.cache.a;
import defpackage.ei3;
import defpackage.nj3;
import defpackage.zf2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.BuildInfo;
import org.chromium.base.Callback;
import org.chromium.base.LocaleUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.ApplicationLifetime;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.history.HistoryDeletionBridge;
import org.chromium.chrome.browser.offlinepages.AutoFetchNotifier;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.quickactionsearchwidget.QuickActionSearchWidgetProvider;
import org.chromium.chrome.browser.searchwidget.SearchWidgetProvider;
import org.chromium.chrome.browser.ui.tablet.emptybackground.EmptyBackgroundViewTablet;
import org.chromium.components.browser_ui.photo_picker.c;
import org.chromium.components.policy.PolicyService;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.components.webapps.AppBannerManager;
import org.chromium.ui.accessibility.AccessibilityState;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.ResourceBundle;
import org.chromium.ui.base.SelectFileDialog;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wz implements Runnable {
    public final /* synthetic */ int e;

    public /* synthetic */ wz(int i) {
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Locale localeForLanguageTag;
        nj3 nj3Var;
        nj3.c cVar;
        int i = 2;
        int i2 = 9;
        final int i3 = 1;
        int i4 = 0;
        switch (this.e) {
            case 0:
                System.exit(0);
                Process.killProcess(Process.myPid());
                return;
            case 1:
                AtomicBoolean atomicBoolean = x61.a;
                N.MLXZo1U6();
                return;
            case 2:
                n3 n3Var = ChromeTabbedActivity.S1;
                mo0 mo0VarA = mo0.a();
                mo0VarA.a.getClass();
                mo0VarA.b("ChromeTabbedActivity");
                return;
            case 3:
                n3 n3Var2 = ChromeTabbedActivity.S1;
                return;
            case 4:
                try {
                    try {
                        ArrayList arrayListB = ApplicationStatus.b();
                        for (int i5 = 0; i5 < arrayListB.size(); i5++) {
                            Activity activity = (Activity) arrayListB.get(i5);
                            if (activity != null && !z7.M(activity)) {
                                activity.finish();
                            }
                        }
                    } finally {
                        Log.e("ActivityUtils  ", "killAppProcess:  ");
                        System.exit(0);
                    }
                    break;
                } catch (Exception e) {
                    Log.e("ActivityUtils", e.toString());
                }
                return;
            case 5:
                k11 k11Var = ChromeActivity.Z0;
                xe1 xe1Var = xe1.i;
                xe1Var.getClass();
                Object obj = ThreadUtils.a;
                if (xe1Var.e) {
                    return;
                }
                xe1Var.e = true;
                if (N.MA9sJ3RR("BrowserProcessMemoryPurge")) {
                    ApplicationStatus.e(xe1Var);
                    xe1Var.v(ApplicationStatus.getStateForApplication());
                    return;
                }
                return;
            case 6:
                SparseIntArray sparseIntArray = zb.k;
                return;
            case 7:
                HashSet hashSet = CustomTabsConnection.o;
                TraceEvent traceEventH0 = TraceEvent.H0("InitializeViewHierarchy", null);
                try {
                    zo3.a().b(xz.a, R$layout.custom_tabs_control_container, R$layout.custom_tabs_toolbar);
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (traceEventH0 != null) {
                        try {
                            traceEventH0.close();
                            break;
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            case 8:
                k11 k11Var2 = bj.l;
                if (pl2.b == null) {
                    pl2.b = new pl2();
                }
                oj2.Q(pl2.b.a().getAll().size(), "CustomTabs.Branding.NumberOfClients");
                return;
            case 9:
            case 10:
                return;
            case 11:
                if (n3.J()) {
                    return;
                }
                n3.F();
                if (qj.a().g()) {
                    Profile profileD = Profile.d();
                    if (N.MrGvO7pv(profileD.b, profileD)) {
                        Profile profileF = Profile.d().f(false);
                        N.MScIZBOB(profileF.b, profileF);
                        return;
                    }
                    return;
                }
                return;
            case 12:
                b62 b62VarB = b62.b();
                b62VarB.getClass();
                ThreadUtils.b();
                if (b62VarB.b) {
                    return;
                }
                lp lpVarA = lp.a();
                lpVarA.getClass();
                if (!lpVarA.d) {
                    lpVarA.d = true;
                    lpVarA.c.getClass();
                    ApplicationStatus.e(new kp(i4, lpVarA));
                }
                SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                long jE = sharedPreferencesManager.e(0L, "com.google.android.apps.chrome.ChromeMobileApplication.BOOT_TIMESTAMP");
                long jCurrentTimeMillis = System.currentTimeMillis() - SystemClock.uptimeMillis();
                if (Math.abs(jCurrentTimeMillis - jE) > 1000) {
                    N.M4n4n4_y();
                    sharedPreferencesManager.n(jCurrentTimeMillis, "com.google.android.apps.chrome.ChromeMobileApplication.BOOT_TIMESTAMP");
                }
                AppHooks.get().getClass();
                AppBannerManager.a aVar = AppBannerManager.a;
                if (fq.j == null) {
                    fq fqVar = new fq();
                    fq.j = fqVar;
                    ApplicationLifetime.a.a(fqVar);
                }
                Clipboard.getInstance().e(new fu());
                c.v = new se1(5);
                SelectFileDialog.n = new s52();
                int i6 = 10;
                n3.j = new wd0(i6);
                zf2 zf2VarA = zf2.a();
                TemplateUrlService templateUrlServiceQ = oj2.q(Profile.d());
                templateUrlServiceQ.h(zf2VarA);
                templateUrlServiceQ.a(zf2VarA);
                if (!templateUrlServiceQ.f()) {
                    N.MVKcMDBb(templateUrlServiceQ.c, templateUrlServiceQ);
                }
                SearchWidgetProvider.SearchWidgetProviderDelegate searchWidgetProviderDelegateB = SearchWidgetProvider.b();
                zf2 zf2VarA2 = zf2.a();
                if (!zf2VarA2.e.b(searchWidgetProviderDelegateB)) {
                    zf2VarA2.e.a(searchWidgetProviderDelegateB);
                    searchWidgetProviderDelegateB.accept(zf2VarA2.f);
                }
                u82 u82Var = QuickActionSearchWidgetProvider.a;
                final QuickActionSearchWidgetProvider.QuickActionSearchWidgetProviderDino quickActionSearchWidgetProviderDino = new QuickActionSearchWidgetProvider.QuickActionSearchWidgetProviderDino();
                final QuickActionSearchWidgetProvider.QuickActionSearchWidgetProviderSearch quickActionSearchWidgetProviderSearch = new QuickActionSearchWidgetProvider.QuickActionSearchWidgetProviderSearch();
                PostTask.c(d73.f, new o90(i2));
                Consumer<zf2.a> consumer = new Consumer() { // from class: di3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        AppWidgetManager appWidgetManager;
                        int i7 = i3;
                        Object obj3 = quickActionSearchWidgetProviderSearch;
                        Object obj4 = quickActionSearchWidgetProviderDino;
                        switch (i7) {
                            case 0:
                                ei3.a aVar2 = (ei3.a) obj4;
                                Map.Entry entry = (Map.Entry) obj2;
                                aVar2.getClass();
                                Object key = entry.getKey();
                                Object value = entry.getValue();
                                aVar2.getClass();
                                ((Consumer) obj3).accept(new a(null, key, value));
                                break;
                            default:
                                QuickActionSearchWidgetProvider quickActionSearchWidgetProvider = (QuickActionSearchWidgetProvider) obj4;
                                QuickActionSearchWidgetProvider quickActionSearchWidgetProvider2 = (QuickActionSearchWidgetProvider) obj3;
                                zf2.a aVar3 = (zf2.a) obj2;
                                u82 u82Var2 = QuickActionSearchWidgetProvider.a;
                                Context context = xz.a;
                                if (context != null && (appWidgetManager = AppWidgetManager.getInstance(context)) != null) {
                                    quickActionSearchWidgetProvider.c(context, appWidgetManager, aVar3, null);
                                    quickActionSearchWidgetProvider2.c(context, appWidgetManager, aVar3, null);
                                }
                                break;
                        }
                    }
                };
                zf2 zf2VarA3 = zf2.a();
                if (!zf2VarA3.e.b(consumer)) {
                    zf2VarA3.e.a(consumer);
                    consumer.accept(zf2VarA3.f);
                }
                if (HistoryDeletionBridge.b == null) {
                    HistoryDeletionBridge.b = new HistoryDeletionBridge();
                }
                HistoryDeletionBridge.b.a.a(new ky(new m30(i3)));
                org.chromium.chrome.browser.feature_guide.notifications.a.a = new uk0();
                v42 v42VarG = v42.g();
                if (!v42VarG.e) {
                    v42VarG.e = true;
                    if (v42VarG.c == null) {
                        PolicyService policyService = (PolicyService) N.MXHPjU6q();
                        v42VarG.c = policyService;
                        v42VarG.d = new u42(v42VarG);
                        if (N.MCCtS0px(policyService.a, policyService)) {
                            v42VarG.h();
                        }
                        PolicyService policyService2 = v42VarG.c;
                        u42 u42Var = v42VarG.d;
                        b<PolicyService.a> bVar = policyService2.b;
                        if (bVar.isEmpty()) {
                            N.M4YsjnbO(policyService2.a, policyService2);
                        }
                        bVar.a(u42Var);
                    }
                }
                ((ph2) N.MGkN3uZ4(Profile.d())).a("feed_user_segment", new ps(6));
                sk skVar = bq.a;
                if (N.M09VlOh_("QueryTilesSegmentation")) {
                    ((ph2) N.MGkN3uZ4(Profile.d())).a("query_tiles", new ps(i6));
                }
                b62.e(true);
                b62VarB.b = true;
                return;
            case 13:
                Object obj2 = b62.d;
                xs0 xs0Var = xs0.d;
                xs0Var.getClass();
                if (yj.a()) {
                    xs0Var.b = null;
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Locale.getDefault());
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            localeForLanguageTag = (Locale) it.next();
                            String languageTag = localeForLanguageTag.toLanguageTag();
                            if (TextUtils.equals(languageTag, null) || Arrays.binarySearch(ResourceBundle.a, languageTag, u6.a) >= 0) {
                            }
                        } else {
                            localeForLanguageTag = Locale.forLanguageTag("en-US");
                        }
                    }
                    xs0Var.a = localeForLanguageTag;
                }
                xs0 xs0Var2 = xs0.d;
                N.MUccXkXt("LanguageUsage.UI.Android.OverrideLanguage", TextUtils.equals(xs0Var2.b, null) ? "" : xs0Var2.b);
                String str = xs0Var2.b;
                String strD = LocaleUtils.d(xs0Var2.a);
                if (TextUtils.equals(str, null)) {
                    i = 3;
                } else if (!TextUtils.equals(str, strD)) {
                    i = TextUtils.equals(LocaleUtils.c(str), LocaleUtils.c(strD)) ? 1 : 0;
                }
                oj2.U(i, 4, "LanguageUsage.UI.Android.OverrideLanguage.IsSystemLanguage");
                return;
            case 14:
                Object obj3 = b62.d;
                PostTask.c(d73.g, new gg3(i2));
                return;
            case 15:
                Object obj4 = b62.d;
                hq1.a();
                return;
            case 16:
                Object obj5 = b62.d;
                SharedPreferencesManager sharedPreferencesManager2 = SharedPreferencesManager.getInstance();
                sharedPreferencesManager2.removeKey("Chrome.OfflineMeasurements.LastCheckMillis");
                sharedPreferencesManager2.removeKey("Chrome.OfflineMeasurements.CurrentTaskMeasurementIntervalInMinutes");
                sharedPreferencesManager2.removeKey("Chrome.OfflineMeasurements.UserAgentString");
                sharedPreferencesManager2.removeKey("Chrome.OfflineMeasurements.HttpProbeUrl");
                sharedPreferencesManager2.removeKey("Chrome.OfflineMeasurements.HttpProbeTimeoutMs");
                sharedPreferencesManager2.removeKey("Chrome.OfflineMeasurements.HttpProbeMethod");
                sharedPreferencesManager2.removeKey("Chrome.OfflineMeasurements.SystemStateList");
                return;
            case 17:
                int i7 = AutoFetchNotifier.InProgressCancelReceiver.a;
                N.MytEgqET(Profile.d());
                return;
            case 18:
                xz.a.getSharedPreferences("com.google.android.apps.chrome.omaha", 0).edit().remove("UpdateProtos_Tracking").apply();
                return;
            case 19:
                return;
            case 20:
                oj2.U(1, 3, "NewTabPage.SuggestTiles.SelectedTileType");
                return;
            case 21:
            default:
                AccessibilityState.a();
                return;
            case 22:
                nj3.c cVar2 = hj3.a().c;
                if (cVar2 == null || cVar2.a != 2 || (cVar = (nj3Var = nj3.a.a).d) == null) {
                    return;
                }
                String str2 = BuildInfo.a.a.e;
                String str3 = cVar.d;
                if (str3 != null && str3.equals(str2)) {
                    return;
                }
                SharedPreferencesManager.getInstance().o("android_os_unsupported_chrome_version", str2);
                nj3Var.d.d = str2;
                Iterator<Callback<nj3.c>> it2 = nj3Var.a.iterator();
                while (true) {
                    b.a aVar2 = (b.a) it2;
                    if (!aVar2.hasNext()) {
                        return;
                    } else {
                        ((Callback) aVar2.next()).onResult(nj3Var.d);
                    }
                }
                break;
            case 23:
                int i8 = EmptyBackgroundViewTablet.k;
                n3.X0("MobileToolbarShowMenu");
                return;
            case 24:
                Context context = xz.a;
                fp3 fp3Var = fp3.b;
                if (fp3Var != null) {
                    fp3Var.a.b(context);
                }
                rp3 rp3Var = rp3.b;
                if (rp3Var == null) {
                    return;
                }
                rp3Var.a.b(xz.a);
                return;
            case 25:
                OvershootInterpolator overshootInterpolator = mg1.s;
                return;
            case 26:
                N.M8I$ZbIR();
                Object obj6 = ThreadUtils.a;
                xz.a.registerComponentCallbacks(new we1("ChildService"));
                return;
        }
    }
}
