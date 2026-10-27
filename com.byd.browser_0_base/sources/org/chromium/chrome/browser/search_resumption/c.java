package org.chromium.chrome.browser.search_resumption;

import defpackage.bq;
import defpackage.oj2;
import defpackage.z7;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.SyncService;

/* JADX INFO: compiled from: SearchResumptionModuleUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    public static void a(int i) {
        oj2.U(i, 9, "NewTabPage.SearchResumptionModule.NotShow");
    }

    public static void b(boolean z) {
        oj2.U(SharedPreferencesManager.getInstance().readBoolean("Chrome.SearchResumptionModule.Collapse", false) ? 1 : 0, 2, z ? "NewTabPage.SearchResumptionModule.Show.Cached" : "NewTabPage.SearchResumptionModule.Show");
    }

    public static boolean c(Profile profile) {
        if (!bq.b("SearchResumptionModuleAndroid")) {
            a(1);
            return false;
        }
        if (!oj2.q(profile).e()) {
            a(4);
            return false;
        }
        z7.m().getClass();
        if (!z7.v(profile).b(1)) {
            a(2);
            return false;
        }
        if (SyncService.get().hasKeepEverythingSynced()) {
            return true;
        }
        a(3);
        return false;
    }
}
