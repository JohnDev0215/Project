package defpackage;

import org.chromium.chrome.browser.tab.TabImpl;

/* JADX INFO: compiled from: CustomTabDelegateFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class n30 extends r33 {
    public final /* synthetic */ o30 k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n30(o30 o30Var, TabImpl tabImpl) {
        super(tabImpl);
        this.k = o30Var;
    }

    @Override // defpackage.r33
    public final int t() {
        int iT = super.t();
        if (iT != 3 || this.k.b) {
            return iT;
        }
        return 1;
    }
}
