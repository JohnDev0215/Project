package defpackage;

import org.chromium.base.SysUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: AppIndexingUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class l6 extends j23 {
    public final /* synthetic */ m6 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l6(m6 m6Var, a23 a23Var) {
        super(a23Var);
        this.i = m6Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Z(TabImpl tabImpl) {
        this.i.getClass();
        if ((SysUtils.isLowEndDevice() ^ true) && !tabImpl.isIncognito() && bk3.i(tabImpl.getUrl())) {
            a8 a8VarC = a8.C();
            tabImpl.getUrl().j();
            tabImpl.getTitle();
            a8VarC.getClass();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        TraceEvent traceEventH0 = TraceEvent.H0("AppIndexingUtil::onPageLoadFinished", null);
        try {
            this.i.a(tab);
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
