package defpackage;

import J.N;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: UmaSessionStats.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bi3 extends j23 {
    public final /* synthetic */ UmaSessionStats i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bi3(UmaSessionStats umaSessionStats, y13 y13Var) {
        super(y13Var);
        this.i = umaSessionStats;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        UmaSessionStats umaSessionStats = this.i;
        umaSessionStats.getClass();
        WebContents webContentsC = tab.c();
        N.MZTfl9lI(webContentsC != null && webContentsC.m().l());
        if (umaSessionStats.e) {
            N.MeATiwBk();
        }
        y13 y13Var = umaSessionStats.a;
        if (y13Var == null) {
            return;
        }
        TabModel tabModelG = y13Var.g(false);
        N.MP6JTEGK(tabModelG != null ? tabModelG.getCount() : 0);
    }
}
