package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: StylusWritingCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vv2 extends nh0 {
    public final /* synthetic */ wv2 e;

    public vv2(wv2 wv2Var) {
        this.e = wv2Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        if (tab.c() == null) {
            return;
        }
        this.e.h.b(tab.c());
    }
}
