package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: ActivityTabProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class b3 implements e23 {
    public final /* synthetic */ z2 e;

    public b3(z2 z2Var) {
        this.e = z2Var;
    }

    @Override // defpackage.e23
    public final void k(TabModel tabModel) {
        if (tabModel.getCount() == 0) {
            this.e.s(null);
        }
    }
}
