package defpackage;

import defpackage.rq0;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabbedNavigationBarColorController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x53 implements rq0.a {
    public final /* synthetic */ z53 e;

    public x53(z53 z53Var) {
        this.e = z53Var;
    }

    @Override // rq0.a
    public final void a(sq0 sq0Var, Tab tab) {
        z53 z53Var = this.e;
        z53Var.p = true;
        z53Var.a();
    }

    @Override // rq0.a
    public final void b(Tab tab) {
        z53 z53Var = this.e;
        z53Var.p = false;
        z53Var.a();
    }
}
