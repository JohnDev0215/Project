package defpackage;

import J.N;
import defpackage.wp2;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.suggestions.mostvisited.MostVisitedSitesBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TileGroupDelegateImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ba3 implements wp2.b {
    public final /* synthetic */ Callback e;
    public final /* synthetic */ ca3 f;

    public ba3(ca3 ca3Var, lj1 lj1Var) {
        this.f = ca3Var;
        this.e = lj1Var;
    }

    @Override // wp2.b
    public final void j(Object obj) {
        ca3 ca3Var = this.f;
        if (ca3Var.e) {
            return;
        }
        GURL gurl = (GURL) obj;
        this.e.onResult(gurl);
        MostVisitedSitesBridge mostVisitedSitesBridge = ca3Var.d;
        long j = mostVisitedSitesBridge.a;
        if (j == 0) {
            return;
        }
        N.MQm3a0t7(j, mostVisitedSitesBridge, gurl, false);
    }

    @Override // wp2.b
    public final void d(Object obj) {
    }
}
