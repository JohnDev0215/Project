package defpackage;

import J.N;
import android.content.Context;
import android.view.animation.OvershootInterpolator;
import defpackage.mm0;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.PathUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.ChromeBaseAppCompatActivity;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragmentBasic;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.enterprise.util.EnterpriseInfo;
import org.chromium.chrome.browser.firstrun.a;
import org.chromium.chrome.browser.firstrun.b;
import org.chromium.chrome.browser.incognito.IncognitoTabLauncher;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.policy.PolicyService;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qe3 implements Runnable {
    public final /* synthetic */ int e;

    public /* synthetic */ qe3(int i) {
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 2;
        int i2 = 1;
        boolean z = false;
        z = false;
        switch (this.e) {
            case 0:
                TraceEvent.f.b();
                return;
            case 1:
                int i3 = ChromeBaseAppCompatActivity.H;
                boolean zB = bf0.b();
                oj2.O("Android.DynamicColors.IsAvailable", zB);
                N.MT4iKtWs("IsDynamicColorAvailable", zB ? "Enabled" : "Disabled", 0);
                return;
            case 2:
                return;
            case 3:
                p90.a(Runtime.getRuntime());
                return;
            case 4:
                int i4 = ClearBrowsingDataFragmentBasic.q0;
                new iz2(false).g(2, "https://myactivity.google.com/myactivity?utm_source=chrome_cbd");
                return;
            case 5:
                return;
            case 6:
                HashSet hashSet = CustomTabsConnection.o;
                if (qj.a().g()) {
                    TraceEvent traceEventH0 = TraceEvent.H0("CreateSpareWebContents", null);
                    try {
                        CustomTabsConnection.b();
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
                }
                return;
            case 7:
                CustomTabsConnection.b();
                return;
            case 8:
            case 9:
                return;
            case 10:
                n3.x();
                return;
            case 11:
                HashSet hashSet2 = DownloadManagerService.r;
                xz.a.getSharedPreferences("DownloadRetryCount", 0);
                return;
            case 12:
                Object obj = b62.d;
                Context context = xz.a;
                Profile profileD = Profile.d();
                zi0 zi0Var = zi0.b;
                TemplateUrlService templateUrlServiceQ = oj2.q(profileD);
                bs0 bs0VarA = bs0.a();
                SharedPreferencesManager.getInstance();
                z7.m().getClass();
                new u8(context, zi0Var, templateUrlServiceQ, bs0VarA, null, z7.v(profileD), AccountManagerFacadeProvider.getInstance()).c(".Startup");
                return;
            case 13:
                Object obj2 = b62.d;
                HashMap map = l02.l;
                mm0 mm0VarE = m02.e();
                mm0VarE.c = true;
                LinkedList<mm0.f> linkedList = mm0VarE.e;
                for (mm0.f fVar : linkedList) {
                    LinkedList<mm0.g> linkedList2 = mm0VarE.d;
                    linkedList2.remove(fVar);
                    linkedList2.add(fVar);
                    mm0VarE.l();
                }
                linkedList.clear();
                return;
            case 14:
                Object obj3 = b62.d;
                int i5 = IncognitoTabLauncher.e;
                sk skVar = bq.a;
                if (N.M09VlOh_("AllowNewIncognitoTabIntents") && N.M$3vpOHw()) {
                    z = true;
                }
                PostTask.c(d73.h, new ig3(i2, z));
                return;
            case 15:
                Object obj4 = b62.d;
                Object obj5 = ThreadUtils.a;
                if (SharedPreferencesManager.getInstance().readBoolean("Chrome.FirstRun.SkippedByPolicy", false)) {
                    fn0 fn0Var = fn0.g;
                    if (fn0Var == null) {
                        fn0Var = new fn0();
                    } else {
                        fn0.g = null;
                    }
                    rs1 rs1Var = new rs1();
                    rs1Var.a((PolicyService) N.MXHPjU6q());
                    new b(new a(fn0Var, rs1Var, EnterpriseInfo.b()), fn0Var);
                    return;
                }
                return;
            case 16:
                n3.X0("Android.WindowManager.SecondaryMenu");
                return;
            case 17:
                PathUtils.c();
                return;
            case 18:
                oj2.U(2, 3, "NewTabPage.SuggestTiles.DeletedTileType");
                return;
            case 19:
                File[] fileArrListFiles = new File(xz.a.getCacheDir() + "/traces").listFiles();
                if (fileArrListFiles != null) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - 3600000;
                    for (File file : fileArrListFiles) {
                        if (file.lastModified() <= jCurrentTimeMillis) {
                            file.delete();
                        }
                    }
                    return;
                }
                return;
            case 20:
                oj2.O("Blink.FedCm.SignUp.PrivacyPolicyClicked", true);
                return;
            case 21:
                return;
            case 22:
                OvershootInterpolator overshootInterpolator = mg1.s;
                return;
            case 23:
                if (ChildProcessLauncherHelperImpl.x != null) {
                    return;
                }
                ChildProcessLauncherHelperImpl.w = ApplicationStatus.hasVisibleActivities();
                ar2 ar2Var = new ar2(i);
                ChildProcessLauncherHelperImpl.x = ar2Var;
                ApplicationStatus.e(ar2Var);
                return;
            default:
                re reVar = ChildProcessLauncherHelperImpl.v;
                if (reVar != null) {
                    oj2.P(reVar.j, "Android.BindingManger.ConnectionsDroppedDueToMaxSize");
                    reVar.j = 0;
                    if (reVar.e.isEmpty()) {
                        return;
                    }
                    LauncherThread.b.postDelayed(reVar.h, 10000L);
                    return;
                }
                return;
        }
    }
}
