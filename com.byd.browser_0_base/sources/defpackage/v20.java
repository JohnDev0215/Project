package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsSessionToken;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: CustomTabActivityLifecycleUmaTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class v20 implements hy1, kr2, hk1 {
    public final oj e;
    public final nw2<Bundle> f;
    public final Activity g;
    public final CustomTabsConnection h;
    public ex1 i;
    public boolean j = true;

    public v20(q2 q2Var, oj ojVar, Activity activity, nw2<Bundle> nw2Var, CustomTabsConnection customTabsConnection) {
        this.e = ojVar;
        this.g = activity;
        this.f = nw2Var;
        this.h = customTabsConnection;
        q2Var.f(this);
    }

    public static String a(Activity activity) {
        if (activity == null || activity.getIntent() == null) {
            return "";
        }
        Intent intent = activity.getIntent();
        String strR = q11.r(intent, "org.chromium.chrome.browser.activity_referrer");
        if (strR != null) {
            return strR;
        }
        Uri referrer = activity.getReferrer();
        return referrer != null ? referrer.toString() : b.i(intent);
    }

    @Override // defpackage.hk1
    public final void G0() {
        ex1 ex1Var = this.i;
        if (ex1Var != null) {
            oj2.X(SystemClock.elapsedRealtime() - ex1Var.a, "CustomTab.SessionDuration".concat(ex1Var.b != 3 ? ".Other" : ".MediaLauncherActivity"));
        }
    }

    @Override // defpackage.hy1
    public final void f() {
        int i;
        Bundle bundle = this.f.get();
        oj ojVar = this.e;
        if (bundle == null && this.j) {
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            String strG = sharedPreferencesManager.g("pref_last_custom_tab_url", null);
            String strK = ojVar.K();
            boolean z = strG != null && strG.equals(strK);
            if (z) {
                n3.X0("CustomTabsMenuOpenSameUrl");
            } else {
                sharedPreferencesManager.o("pref_last_custom_tab_url", strK);
            }
            String strJ = ojVar.j();
            Activity activity = this.g;
            String strA = a(activity);
            int taskId = activity.getTaskId();
            String strG2 = sharedPreferencesManager.g("Chrome.CustomTabs.LastClientPackage", null);
            String strG3 = sharedPreferencesManager.g("Chrome.CustomTabs.LastReferrer", null);
            int iD = sharedPreferencesManager.d(0, "Chrome.CustomTabs.LastTaskId");
            sharedPreferencesManager.m(taskId, "Chrome.CustomTabs.LastTaskId");
            if (TextUtils.isEmpty(strJ) && TextUtils.isEmpty(strA)) {
                sharedPreferencesManager.removeKey("Chrome.CustomTabs.LastClientPackage");
                sharedPreferencesManager.removeKey("Chrome.CustomTabs.LastReferrer");
            } else if (TextUtils.isEmpty(strJ)) {
                sharedPreferencesManager.removeKey("Chrome.CustomTabs.LastClientPackage");
                sharedPreferencesManager.o("Chrome.CustomTabs.LastReferrer", strA);
            } else {
                sharedPreferencesManager.o("Chrome.CustomTabs.LastClientPackage", strJ);
                sharedPreferencesManager.removeKey("Chrome.CustomTabs.LastReferrer");
            }
            if (z && sharedPreferencesManager.readBoolean("Chrome.CustomTabs.LastCloseTabInteraction", false)) {
                String strU = n3.U(strJ, strG2, strA, strG3, taskId, iD);
                long jUptimeMillis = SystemClock.uptimeMillis();
                long jF = sharedPreferencesManager.f("Chrome.CustomTabs.LastCloseTimestamp");
                if (jF != 0 && jF < jUptimeMillis) {
                    oj2.X(jUptimeMillis - jF, "CustomTabs.RetainableSessionsV2.TimeBetweenLaunch".concat(strU));
                }
            }
            SharedPreferencesManager sharedPreferencesManager2 = SharedPreferencesManager.getInstance();
            sharedPreferencesManager2.removeKey("Chrome.CustomTabs.LastCloseTimestamp");
            sharedPreferencesManager2.removeKey("Chrome.CustomTabs.LastCloseTabInteraction");
            if (ojVar.N()) {
                n3.X0("ChromeGeneratedCustomTab.StartedInitially");
            } else {
                n3.X0("CustomTabs.StartedInitially");
            }
            if (ojVar instanceof xx0) {
                xx0 xx0Var = (xx0) ojVar;
                Intent intent = xx0Var.a;
                if (q11.g(intent)) {
                    i = 2;
                    int iL = q11.l(intent, "org.chromium.chrome.browser.customtabs.EXTRA_INCOGNITO_CCT_CALLER_ID", 2);
                    if (iL > 2 && iL < 5) {
                        i = iL;
                    }
                } else {
                    xx0.f0(intent);
                    i = 0;
                }
                oj2.U(i, 5, "CustomTabs.IncognitoCCTCallerId");
                if (i == 1) {
                    int iT = b.t(xx0Var.k);
                    if (iT != 0) {
                        oj2.U(iT, 16, "CustomTabs.ClientAppId.Incognito");
                    } else {
                        oj2.U(b.c(xx0Var.a), 16, "CustomTabs.ClientAppId.Incognito");
                    }
                }
            } else {
                oj2.U(b.c(ojVar.t()), 16, "CustomTabs.ClientAppId");
            }
        } else if (ojVar.N()) {
            n3.X0("ChromeGeneratedCustomTab.StartedReopened");
        } else {
            n3.X0("CustomTabs.StartedReopened");
        }
        this.j = false;
        this.i = new ex1(ojVar.t().getIntExtra("org.chromium.chrome.browser.customtabs.EXTRA_BROWSER_LAUNCH_SOURCE", -1));
    }

    @Override // defpackage.hy1
    public final void h() {
        ex1 ex1Var = this.i;
        if (ex1Var != null) {
            oj2.X(SystemClock.elapsedRealtime() - ex1Var.a, "CustomTab.SessionDuration".concat(ex1Var.b != 3 ? ".Other" : ".MediaLauncherActivity"));
        }
    }

    @Override // defpackage.kr2
    public final void j() {
        CustomTabsSessionToken customTabsSessionTokenZ = this.e.z();
        a aVar = this.h.c;
        synchronized (aVar) {
            a.e eVar = (a.e) aVar.c.get(customTabsSessionTokenZ);
            if (eVar == null) {
                return;
            }
            eVar.o = true;
        }
    }

    @Override // defpackage.kr2
    public final void l() {
        CustomTabsSessionToken customTabsSessionTokenZ = this.e.z();
        a aVar = this.h.c;
        synchronized (aVar) {
            a.e eVar = (a.e) aVar.c.get(customTabsSessionTokenZ);
            if (eVar == null) {
                return;
            }
            eVar.o = false;
        }
    }
}
