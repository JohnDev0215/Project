package defpackage;

import defpackage.wp2;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: OfflinePageTabObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class er1 implements wp2.b {
    public final /* synthetic */ y13 e;

    public er1(y13 y13Var) {
        this.e = y13Var;
    }

    @Override // wp2.b
    public final void d(Object obj) {
        n3.X0("OfflinePages.ReloadButtonNotClicked");
    }

    @Override // wp2.b
    public final void j(Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        n3.X0("OfflinePages.ReloadButtonClicked");
        Tab tabS = this.e.s(iIntValue);
        if (tabS == null) {
            return;
        }
        if (!d.f(tabS.c())) {
            n3.X0("OfflinePages.ReloadButtonClickedViewingUntrustedPage");
        }
        tabS.a();
    }
}
