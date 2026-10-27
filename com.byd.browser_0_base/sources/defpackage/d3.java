package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.metrics.a;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ActivityTabStartupMetricsTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d3 extends j23 {
    public boolean i;
    public final /* synthetic */ a j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d3(a aVar, y13 y13Var) {
        super(y13Var);
        this.j = aVar;
        this.i = true;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        if (this.i) {
            this.i = false;
        } else {
            this.j.f = false;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        boolean z = navigationHandle.g && !navigationHandle.i && !navigationHandle.c && bk3.i(navigationHandle.e);
        a aVar = this.j;
        if (aVar.f) {
            long j = aVar.a;
            if (z && UmaUtils.c() && !UmaUtils.b()) {
                aVar.b = SystemClock.uptimeMillis() - j;
                oj2.Y(aVar.b, "Startup.Android.Cold.TimeToFirstNavigationCommit".concat(aVar.c == 0 ? ".Tabbed" : ".WebApk"));
                if (aVar.c == 0) {
                    long j2 = aVar.b;
                    if (!aVar.g) {
                        aVar.g = true;
                        oj2.Y(j2, "Startup.Android.Cold.TimeToFirstVisibleContent");
                    }
                    long andSet = aVar.k.getAndSet(0L);
                    if (andSet != 0) {
                        oj2.Y(andSet / 1000, "Startup.Android.Cold.FirstSafeBrowsingResponseTime.Tabbed");
                    }
                }
                oj2.O("Startup.Android.Cold.FirstNavigationCommitOccurredPreForeground", false);
            } else if (z && !UmaUtils.c() && !UmaUtils.b()) {
                aVar.i = true;
            }
            if (aVar.c == 0 && z) {
                if (n3.u && !n3.t) {
                    long jUptimeMillis = SystemClock.uptimeMillis() - j;
                    aVar.b = jUptimeMillis;
                    oj2.Y(jUptimeMillis, "Startup.Android.Cold.TimeToFirstNavigationCommit2.Tabbed");
                }
            }
            aVar.f = false;
        }
    }
}
