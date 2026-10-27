package defpackage;

import J.N;
import android.app.Activity;
import defpackage.lk1;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.RequestCoordinatorBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: NativePageNavigationDelegateImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class ok1 implements nk1 {
    public final Profile a;
    public final y13 b;
    public final Tab c;
    public final Activity d;
    public final mk1 e;

    public ok1(Activity activity, Profile profile, lk1.b bVar, y13 y13Var, Tab tab) {
        this.d = activity;
        this.a = profile;
        this.e = bVar;
        this.b = y13Var;
        this.c = tab;
    }

    @Override // defpackage.nk1
    public final boolean a() {
        return vj1.h.q(this.d) || vj1.b();
    }

    @Override // defpackage.nk1
    public final Tab b(int i, LoadUrlParams loadUrlParams) {
        y13 y13Var = this.b;
        Tab tab = this.c;
        mk1 mk1Var = this.e;
        if (i == 1) {
            ((lk1.b) mk1Var).b(loadUrlParams, y13Var.r());
            return tab;
        }
        if (i == 3) {
            return y13Var.x(loadUrlParams, i == 3 ? 4 : 5, tab, false);
        }
        if (i == 4) {
            return y13Var.x(loadUrlParams, i == 3 ? 4 : 5, tab, false);
        }
        if (i == 6) {
            iz2 iz2Var = new iz2(false);
            int i2 = CriticalPersistedTabData.m(((lk1.b) mk1Var).a).p;
            Activity activity = this.d;
            iz2Var.j(loadUrlParams, activity, i2, vj1.f(activity));
        } else if (i == 7) {
            String str = loadUrlParams.a;
            Profile profile = this.a;
            if (tab != null) {
                OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(profile);
                WebContents webContentsC = tab.c();
                offlinePageBridgeA.getClass();
                N.MNR_O1IV(offlinePageBridgeA.a, offlinePageBridgeA, webContentsC, "ntp_suggestions", str, 65535, new ar1().a());
            } else {
                Object obj = ThreadUtils.a;
                (profile.h() ? null : new RequestCoordinatorBridge(profile)).a(str, "ntp_suggestions");
            }
        } else if (i == 8) {
            ((lk1.b) mk1Var).b(loadUrlParams, true);
        }
        return null;
    }
}
