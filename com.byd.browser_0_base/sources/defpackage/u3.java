package defpackage;

import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: AddToHomescreenMostVisitedTileClickObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u3 extends nh0 {
    public final /* synthetic */ t3 e;
    public final /* synthetic */ v3 f;

    public u3(v3 v3Var, t3 t3Var) {
        this.f = v3Var;
        this.e = t3Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d8  */
    @Override // defpackage.nh0, defpackage.n23
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a1(org.chromium.chrome.browser.tab.Tab r9, org.chromium.url.GURL r10) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.u3.a1(org.chromium.chrome.browser.tab.Tab, org.chromium.url.GURL):void");
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        v3 v3Var = this.f;
        v3Var.getClass();
        if (tab != null && (tab.u() instanceof h)) {
            ((h) tab.u()).q.c(v3Var);
        }
    }
}
