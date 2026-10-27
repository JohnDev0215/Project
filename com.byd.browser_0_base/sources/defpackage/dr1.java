package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: OfflinePageTabObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dr1 extends h23 {
    public final /* synthetic */ fr1 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public dr1(fr1 fr1Var, y13 y13Var) {
        super(y13Var);
        this.g = fr1Var;
    }

    @Override // defpackage.t13
    public final void n0(Tab tab) {
        fr1 fr1Var = this.g;
        fr1Var.A1(tab);
        fr1Var.e.a(fr1Var.f);
    }
}
