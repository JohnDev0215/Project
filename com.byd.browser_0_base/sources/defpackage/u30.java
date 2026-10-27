package defpackage;

import defpackage.a30;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: CustomTabIntentHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class u30 extends a30.a {
    public final /* synthetic */ v30 a;

    public u30(v30 v30Var) {
        this.a = v30Var;
    }

    @Override // a30.a
    public final void b(Tab tab) {
        v30 v30Var = this.a;
        Runnable runnable = v30Var.f;
        if (runnable != null) {
            runnable.run();
            v30Var.f = null;
        }
        v30Var.a.a.c(this);
    }
}
