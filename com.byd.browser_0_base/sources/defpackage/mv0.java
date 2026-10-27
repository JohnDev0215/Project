package defpackage;

import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: HomeButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mv0 extends nh0 {
    public final /* synthetic */ nv0 e;

    public mv0(nv0 nv0Var) {
        this.e = nv0Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        TraceEvent traceEventH0 = TraceEvent.H0("HomeButtonCoordinator::onPageLoadFinished", null);
        try {
            this.e.a(gurl);
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
