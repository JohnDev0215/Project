package defpackage;

import android.app.Activity;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.offlinepages.RequestCoordinatorBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: MostVisitedTileNavigationDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ri1 extends hw2 {
    public final nw2<Tab> f;
    public final iz2 g;

    public ri1(Activity activity, Profile profile, nw2<Tab> nw2Var) {
        super(activity, profile, null, null, null);
        this.f = nw2Var;
        this.g = new iz2(false);
    }

    @Override // defpackage.hw2
    public final void c(String str, int i, boolean z) {
        nw2<Tab> nw2Var = this.f;
        if (i == 1 || i == 4) {
            zb2.b(new LoadUrlParams(str, 2), i == 4, null, nw2Var.get());
            return;
        }
        if (i == 6) {
            LoadUrlParams loadUrlParams = new LoadUrlParams(str, 2);
            int id = nw2Var.get() == null ? -1 : nw2Var.get().getId();
            Activity activity = this.d;
            this.g.j(loadUrlParams, activity, id, vj1.f(activity));
            return;
        }
        if (i != 7) {
            if (i != 8) {
                return;
            }
            zb2.b(new LoadUrlParams(str, 2), false, Boolean.TRUE, nw2Var.get());
        } else {
            Profile profileD = Profile.d();
            Object obj = ThreadUtils.a;
            (profileD.h() ? null : new RequestCoordinatorBridge(profileD)).a(str, "ntp_suggestions");
        }
    }
}
