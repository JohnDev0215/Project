package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabStateAttributes.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o33 implements z10 {
    public final /* synthetic */ p33 e;

    public o33(p33 p33Var) {
        this.e = p33Var;
    }

    @Override // defpackage.z10
    public final void H(int i, Tab tab) {
        if (tab.isInitialized()) {
            this.e.f(2);
        }
    }
}
