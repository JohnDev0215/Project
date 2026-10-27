package defpackage;

import defpackage.rq0;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabContentManagerHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class xy2 implements rq0.a {
    public final /* synthetic */ yy2 e;

    public xy2(yy2 yy2Var) {
        this.e = yy2Var;
    }

    @Override // rq0.a
    public final void a(sq0 sq0Var, Tab tab) {
        boolean zIsUserInteractable = tab.isUserInteractable();
        yy2 yy2Var = this.e;
        if (!zIsUserInteractable) {
            yy2Var.i.f(tab.getId());
        } else {
            yy2Var.m = tab;
            yy2Var.l = true;
        }
    }
}
